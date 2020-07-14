package com.commom.distributed;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CloudCallable<O> implements Callable<Void> {
//    private static final Logger logger = Logger.getLogger(CloudCallable.class);

    private CountDownLatch doneSignal;

    private Processor<O> processor;

    private Func<O> func;

    private Map<String, Object> threadLocalContext;

//    private Transaction peek;

    public CloudCallable(CountDownLatch doneSignal, Processor<O> processor, Func<O> func) {
        super();
        this.doneSignal = doneSignal;
        this.processor = processor;
        this.func = func;
//        this.threadLocalContext = threadLocalContext;
//        this.peek = peek;
    }

    public Void call() throws Exception {
//        if (threadLocalContext != null) {
//            ThreadLocalUtil.getInstance().set(threadLocalContext);
//        }
        String currentThreadName = Thread.currentThread().getName();
        String name = currentThreadName;
//        if (func instanceof Func2) {
//            name = ((Func2<O>) func).threadName();
//            Thread.currentThread().setName(name);
//        }
//        DefaultMessageProducer cat = (DefaultMessageProducer) Cat.getProducer();
//        Transaction t = cat.newTransaction(peek, "Distributed.execute", name);
        O o = null;
        try {
            o = func.method();
            processor.doResult(o);
//            t.setStatus(Message.SUCCESS);
        } catch (Exception e) {
//            t.setStatus(e);
//            Cat.logError(e);
//            logger.error("call error: " + e.getMessage(), e);
        } finally {
            doneSignal.countDown();
//            t.complete();
        }
//        if (threadLocalContext != null) {
//            ThreadLocalUtil.getInstance().remove();
//        }
        Thread.currentThread().setName(currentThreadName);
        return null;
    }
}
