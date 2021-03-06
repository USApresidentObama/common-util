package com.commom.distributed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Cloud<R> {
//    private static final Logger logger = Logger.getLogger(Cloud.class);

    private final static long TIME_OUT = -1;

    private Executor executor;

    public Cloud(Executor executor) {
        this.executor = executor;
    }

    public R computeWithTimeOut(List<Func<R>> funcs, Merge<R> merge, long timeout, TimeUnit unit) throws Exception {
        return exec(funcs, merge, timeout, unit);
    }

    public R computeWithOutTimeOut(List<Func<R>> funcs, Merge<R> merge) throws Exception {
        return exec(funcs, merge, TIME_OUT, TimeUnit.SECONDS);
    }

    private R exec(List<Func<R>> funcs, Merge<R> merge, long timeout, TimeUnit unit) throws Exception {

        Processor<R> processor = new Processor<R>(merge);

        CountDownLatch doneSignal = new CountDownLatch(funcs.size());

        List<FutureTask<Void>> tasks = new ArrayList<FutureTask<Void>>(funcs.size());

//        Map<String, Object> context = ThreadLocalUtil.getInstance().getAll();

//        Transaction peek = Cat.getManager().getPeekTransaction();

        for (Func<R> func : funcs) {
            CloudCallable<R> call = new CloudCallable<R>(doneSignal, processor, func);
            FutureTask<Void> task = new FutureTask<Void>(call);
            tasks.add(task);
            commitTask(task);
        }

        await(doneSignal, timeout, unit, tasks);

        R result = processor.getResult();

//        logger.info("result=" + result);
        return result;
    }

    /**
     * 如果计数到达零，则返回 true； 如果在计数到达零之前超过了等待时间，或当前线程在等待时被中断。则返回 false
     *
     * @param timeout
     * @param unit
     * @return
     */
    private boolean await(CountDownLatch doneSignal, long timeout, TimeUnit unit, List<FutureTask<Void>> futureTasks) {
        boolean result = false;
        try {
            if (TIME_OUT == timeout) {
                doneSignal.await();
                result = true;
            } else {
                result = doneSignal.await(timeout, unit);
            }
        } catch (InterruptedException e) {
            result = false;
//            logger.error("InterruptedException:  timeout= " + timeout + " , unit =" + unit, e);
        } finally {
            // 取消那些已经创建但是又没有执行的线程
            for (FutureTask<Void> task : futureTasks) {
                if (!task.isDone()) {// 判断线程是否被执完成或取消过或中断过
                    task.cancel(true);
                }
            }
            // 强制销毁对象
            futureTasks.clear();
            futureTasks = null;
        }
        return result;
    }

    private void commitTask(Runnable task) {
        boolean success;
        do {
            success = executeTask(task);
        } while (!success);
    }

    private boolean executeTask(Runnable task) {
        try {
            executor.execute(task);
        } catch (Exception e) {
//            logger.error("execute task error: " + e.getMessage(), e);
            return false;
        }
        return true;
    }
}
