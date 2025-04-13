package com.aks.utils;

import java.util.function.Supplier;

public class Utils {

    public synchronized static <T> T getIfNull(T object, Supplier<T> supplier) {
        if (object == null) {
            return supplier.get();
        }
        return object;
    }
}
