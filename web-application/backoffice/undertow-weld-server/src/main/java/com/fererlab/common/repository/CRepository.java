package com.fererlab.common.repository;


import com.fererlab.common.model.Model;

import java.io.Serializable;

public interface CRepository<T extends Model<PK>, PK extends Serializable> {

    void create(T t);

    void update(T t);

    void delete(T t);

    void delete(PK id);

}
