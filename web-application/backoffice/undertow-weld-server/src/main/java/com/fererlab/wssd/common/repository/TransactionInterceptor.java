package com.fererlab.wssd.common.repository;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TransactionInterceptor {

    @AroundInvoke
    public Object executeContracts(InvocationContext ctx) throws Exception {
        System.out.println(ctx.getTarget());
        return ctx.proceed();
    }
}