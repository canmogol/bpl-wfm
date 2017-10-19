package com.fererlab.wssd.common.repository;


import com.fererlab.wssd.common.model.Model;

import javax.interceptor.Interceptors;
import java.io.Serializable;

public interface CommandRepository<T extends Model<PK>, PK extends Serializable> {

    void create(T t);

    void update(T t);

    void delete(T t);

    void delete(PK id);

}
