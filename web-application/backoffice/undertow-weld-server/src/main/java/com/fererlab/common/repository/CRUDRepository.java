package com.fererlab.common.repository;

import com.fererlab.common.model.Model;
import com.fererlab.common.repository.command.CRepository;
import com.fererlab.common.repository.query.QRepository;

import java.io.Serializable;
import java.util.List;

public abstract class CRUDRepository<T extends Model<PK>, PK extends Serializable> implements QRepository<T, PK>, CRepository<T, PK> {

    final CRepository<T, PK> commandRepository;

    final QRepository<T, PK> queryRepository;

    protected CRUDRepository(CRepository<T, PK> commandRepository, QRepository<T, PK> queryRepository) {
        this.commandRepository = commandRepository;
        this.queryRepository = queryRepository;
    }

    @Override
    public T findById(PK id) {
        return queryRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return queryRepository.findAll();
    }

    @Override
    public List<T> findAll(Integer index, Integer numberOfRecords) {
        return queryRepository.findAll(index, numberOfRecords);
    }

    @Override
    public Long count() {
        return queryRepository.count();
    }

    @Override
    public void create(T helloModel) {
        commandRepository.create(helloModel);
    }

    @Override
    public void update(T helloModel) {
        commandRepository.update(helloModel);
    }

    @Override
    public void delete(T helloModel) {
        commandRepository.delete(helloModel);
    }

    @Override
    public void delete(PK id) {
        commandRepository.delete(id);
    }
}
