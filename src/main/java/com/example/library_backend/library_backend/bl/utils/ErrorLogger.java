package com.example.library_backend.library_backend.bl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorLogger {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLogger.class);

    public static void logError(String message, Object context, Exception e){
        logger.error(message + ": {}", context, e.getMessage());
    }

    public static void logError(String message, Exception e){
        logger.error(message + ": {}", e.getMessage());
    }
}
