package com.igevin.terminaltodo.supporting.id;

public class TimestampIdGenerator implements IdGenerator{
    @Override
    public long nextId() {
        return System.currentTimeMillis();
    }
}
