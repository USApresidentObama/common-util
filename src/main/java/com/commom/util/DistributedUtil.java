package com.commom.util;

import com.commom.distributed.Cloud;
import com.commom.distributed.Func;
import com.commom.distributed.Merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DistributedUtil {
    //todo 日志
//    private static final Logger catalinaLog = Logger.getLogger(DistributedUtil.class);
    private static Executor executor = null;

    static {
        synchronized (DistributedUtil.class) {
            executor = newCachedThreadPool();
        }
    }

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(//
//                PropUtil.getInstance().getInt("distributed.threadpool.workqueue.minsize", 20),//
//                PropUtil.getInstance().getInt("distributed.threadpool.workqueue.maxsize", 200), //
//                PropUtil.getInstance().getLong("distributed.threadpool.idletime", 60000), //
                20, 200, 60000,
                TimeUnit.MILLISECONDS,//
//                new ArrayBlockingQueue<Runnable>(PropUtil.getInstance().getInt(
//                        "distributed.threadpool.blockqueue.maxsize", 2000)), //
                new ArrayBlockingQueue<Runnable>(2000),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //todo 日志
//                        catalinaLog.error("distributed threadpool full " + ThreadLocalUtil.getInstance().getAll());
                    }
                });
    }

    /**
     * 分组执行并发任务
     *
     * @param taskList
     *            任务列表
     * @param taskUnit
     *            分组单位
     * @return Map<String, Object> 并发的执行结果集合
     */
    public static Map<String, Object> splitParallelCompute(List<Func<Map<String, Object>>> taskList, int taskUnit) {
        if (taskList == null || taskList.isEmpty()) {
            return null;
        }
        if (taskUnit <= 0) {
            taskUnit = 1;
        }
        Map<Integer, List<Func<Map<String, Object>>>> groupTaskMapList = new HashMap<Integer, List<Func<Map<String, Object>>>>();
        // 分成几组
        int taskTotalSize = taskList.size();
        int groupCount = taskTotalSize % taskUnit == 0 ? taskTotalSize / taskUnit : taskTotalSize / taskUnit + 1;

        Func<Map<String, Object>> func = null;
        List<Func<Map<String, Object>>> groupList = null;
        for (int i = 0; i < groupCount; i++) {
            int groupIndex = i * taskUnit;
            groupList = new ArrayList<Func<Map<String, Object>>>();
            int j = 0;
            while (j < taskUnit && groupIndex < taskTotalSize) {
                func = taskList.get(groupIndex++);
                if (func == null) {
                    continue;
                }
                groupList.add(func);
                j++;
            }
            groupTaskMapList.put(i + 1, groupList);
        }

        // 分批次调用
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (Map.Entry<Integer, List<Func<Map<String, Object>>>> entry : groupTaskMapList.entrySet()) {
            Map<String, Object> parallelResultMap = parallelCompute(entry.getValue());
            if (parallelResultMap == null || parallelResultMap.isEmpty()) {
                continue;
            }
            resultMap.putAll(parallelResultMap);
        }

        return resultMap;
    }

    /**
     * 并发执行任务<br>
     * 使用默认的Executor
     *
     * @param tasks
     *            任务列表
     * @return
     */
    public static Map<String, Object> parallelCompute(List<Func<Map<String, Object>>> tasks) {
        return parallelCompute(tasks, executor);
    }

    /**
     * 并发执行任务
     *
     * @param tasks
     *            任务列表
     * @param executor
     *            Executor
     * @return
     */
    public static Map<String, Object> parallelCompute(List<Func<Map<String, Object>>> tasks, Executor executor) {
        Map<String, Object> resultMap = null;
        try {
            resultMap = new Cloud<Map<String, Object>>(executor).computeWithOutTimeOut(tasks, MapMerge.INSTANCE);
        } catch (Exception e) {
            //todo 日志
//            catalinaLog.warn("usereId:" + ThreadLocalUtil.getInstance().getCurrentUserId(), e);
        }
        return resultMap;
    }

    /**
     * MapMerge类
     */
    enum MapMerge implements Merge<Map<String, Object>> {
        INSTANCE;
        @Override
        public Map<String, Object> merge(Map<String, Object> a, Map<String, Object> b) {
            if (a == null) {
                return b;
            }
            if (b == null || b.isEmpty()) {
                return a;
            }
            a.putAll(b);
            return a;
        }
    }
}
