package com.igevin.terminaltodo.supporting.id;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicIdGenerator implements IdGenerator{
    private final AtomicLong atomicId = new AtomicLong(0);
    @Override
    public long nextId() {
        return atomicId.incrementAndGet();
    }
}
