package com.commom.distributed;

public interface Merge<O> {
    /**
     * merge b to a ,return result
     */
    public O merge(O a, O b);
}
