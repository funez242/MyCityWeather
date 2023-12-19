package com.app.mycityweatherapp.service;

import com.app.mycityweatherapp.persistence.entity.LogsEntity;

import java.util.List;
import java.util.Optional;

public interface LogsService {
    // Save operation
    LogsEntity saveLog(LogsEntity log);

    // Read operation
    List<LogsEntity> fetchLogList();

    // Update operation
    LogsEntity updateLog(LogsEntity log, String requestKey);

    // Delete operation
    void deleteLogById(Long logId);

    Optional<LogsEntity> findByRequestKey(String requestKey);

    Iterable<LogsEntity> findAllByOrderByUpdatedTimeDesc();
}
