package com.aks.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static final DateTimeFormatter formatterInMillis = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void log(String ... args) {
        StringBuilder builder = new StringBuilder();

        LocalDateTime now = LocalDateTime.now();
        builder.append(now.format(formatterInMillis));
        builder.append(" | ");

        builder.append(Thread.currentThread().getName());
        builder.append(" | ");

        for (var s : args) {
            builder.append(" | ");
            builder.append(s);
        }
        System.out.println(builder);
    }
}