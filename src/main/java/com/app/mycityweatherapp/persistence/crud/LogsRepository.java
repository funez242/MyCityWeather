package com.app.mycityweatherapp.persistence.crud;

import com.app.mycityweatherapp.persistence.entity.LogsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogsRepository extends CrudRepository<LogsEntity,Long> {

    Optional<LogsEntity> findByRequestKey(String requestKey);

    Iterable<LogsEntity> findAll(Sort sort);
}
