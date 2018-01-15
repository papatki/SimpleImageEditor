package com.pati.images.service;

import java.util.Stack;

public class SizedStack<T> extends Stack<T> {
    private final int MAXSIZE;

    public  SizedStack(int size) {
        super();
        this.MAXSIZE = size;
    }

    @Override
    public T push(T item) {
        while (this.size() > MAXSIZE) {
            this.remove(0);
        }
        return super.push(item);
    }
}
