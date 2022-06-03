package com.epam.ld.module2.testing;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.PrintWriter;
import java.util.Optional;

public class MessengerTestWatcher implements TestWatcher, AfterAllCallback {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        if (context != null && context.getTestClass().isPresent()) {
            builder.append(context.getTestClass().get().getSimpleName()).append(" > ").append(context.getDisplayName());
        }
        builder.append(" DISABLED").append(System.lineSeparator());
        reason.ifPresent(s -> builder.append("  reason: ").append(s).append(System.lineSeparator()));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        if (context != null && context.getTestClass().isPresent()) {
            builder.append(context.getTestClass().get().getSimpleName()).append(" > ").append(context.getDisplayName());
        }
        builder.append(" PASSED").append(System.lineSeparator());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        if (context != null && context.getTestClass().isPresent()) {
            builder.append(context.getTestClass().get().getSimpleName()).append(" > ").append(context.getDisplayName());
        }
        builder.append(" ABORTED").append(System.lineSeparator());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (context != null && context.getTestClass().isPresent()) {
            builder.append(context.getTestClass().get().getSimpleName()).append(" > ").append(context.getDisplayName());
        }
        if (cause != null) {
            builder.append(' ').append(cause);
        }
        builder.append(" FAIL").append(System.lineSeparator());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        PrintWriter logFile = new PrintWriter("report/test_report.txt", "ISO-8859-1");
        logFile.write(builder.toString());
        logFile.close();
    }
}
