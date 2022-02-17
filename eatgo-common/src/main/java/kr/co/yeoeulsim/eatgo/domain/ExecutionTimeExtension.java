package kr.co.yeoeulsim.eatgo.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ExecutionTimeExtension  implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put("@TIME@", System.nanoTime());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        final long duration = System.nanoTime() - getStore(context).get("@TIME@", long.class);
        log.info(context.getRequiredTestMethod().getName() + " - " + TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS));
    }

    private ExtensionContext.Store getStore(final ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}
