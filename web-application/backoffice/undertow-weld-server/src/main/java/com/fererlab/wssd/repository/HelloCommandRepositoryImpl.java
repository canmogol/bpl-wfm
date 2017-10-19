package com.fererlab.wssd.repository;

import com.fererlab.wssd.common.repository.CRUDRepository;
import com.fererlab.wssd.common.repository.TransactionInterceptor;
import com.fererlab.wssd.model.HelloModel;

import javax.interceptor.Interceptors;

public class HelloCommandRepositoryImpl extends CRUDRepository<HelloModel, Integer> implements HelloCommandRepository {
}
