package com.fererlab.wssd.common.repository;


import com.fererlab.wssd.common.model.Model;

import java.io.Serializable;
import java.util.List;


public interface QueryRepository<T extends Model<PK>, PK extends Serializable> {

    T findById(PK id);

    List<T> findAll();

    List<T> findAll(Integer index, Integer numberOfRecords);

    Long count();

}
