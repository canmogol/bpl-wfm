package com.fererlab.wssd.repository;

import com.fererlab.wssd.common.repository.CommandRepository;
import com.fererlab.wssd.common.repository.TransactionInterceptor;
import com.fererlab.wssd.model.HelloModel;

import javax.interceptor.Interceptors;

public interface HelloCommandRepository extends CommandRepository<HelloModel, Integer> {
}
