package kr.co.yeoeulsim.eatgo.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Slf4j
@Transactional
public class TestInstancePlay implements InvocationInterceptor {

    private static long start;
    @Override
    public <T> T interceptTestClassConstructor(Invocation<T> invocation, ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext) throws Throwable {
        log.info("constructed", invocationContext.getTargetClass());
        log.info("methodName" ,invocationContext.getExecutable().getName()); // 메소드 이름 가져오기
        return InvocationInterceptor.super.interceptTestClassConstructor(invocation, invocationContext, extensionContext);
    }
}