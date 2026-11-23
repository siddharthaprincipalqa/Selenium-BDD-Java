package org.example.runners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private final ConcurrentHashMap<Object, AtomicInteger> attempts =
            new ConcurrentHashMap<>();

    @Override
    public boolean retry(ITestResult result) {
        Object scenario = result.getParameters()[0];

        int retryCount = attempts
                .computeIfAbsent(scenario, key -> new AtomicInteger())
                .getAndIncrement();

        return retryCount < 1;
    }
}
