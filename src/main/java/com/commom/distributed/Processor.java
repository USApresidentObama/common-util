package com.commom.distributed;

import java.util.concurrent.locks.ReentrantLock;

public class Processor<O> {
//    private static Logger logger = Logger.getLogger(Processor.class);

    private O result;

    private Merge<O> merge;

    private final ReentrantLock lock = new ReentrantLock();

    public Processor(Merge<O> merge) {
        this.merge = merge;
    }

    public O getResult() {
        return result;
    }

    public void doResult(O o) {
        if (o == null) {
            return;
        }
        lock.lock();
        try {
            if (merge != null) {
                this.result = merge.merge(this.result, o);
            }
        } catch (Exception e) {
//            logger.error("doResult: " + e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }
}
