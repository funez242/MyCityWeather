package com.app.mycityweatherapp.repository;

import com.app.mycityweatherapp.entities.LogsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogsRepository extends CrudRepository<LogsEntity,Long> {

    Optional<LogsEntity> findByRequestKey(String requestKey);

    Iterable<LogsEntity> findAll(Sort sort);
}
