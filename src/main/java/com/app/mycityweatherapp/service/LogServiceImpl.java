package com.app.mycityweatherapp.service;

import com.app.mycityweatherapp.persistence.entity.LogsEntity;
import com.app.mycityweatherapp.persistence.crud.LogsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogsService{

    LogsRepository logsRepository;

    public LogServiceImpl(LogsRepository logsRepository){
        this.logsRepository = logsRepository;
    }

    @Override
    public LogsEntity saveLog(LogsEntity log) {
        return this.logsRepository.save(log);
    }

    @Override
    public List<LogsEntity> fetchLogList() {
        return (List<LogsEntity>) this.logsRepository.findAll();
    }

    @Override
    public LogsEntity updateLog(LogsEntity department, String requestKey) {
        LogsEntity depDB
                = this.logsRepository.findByRequestKey(requestKey)
                .get();

        if (Objects.nonNull(depDB) ) {
            depDB.setResponse(department.getResponse());
            depDB.setUpdatedTime(department.getUpdatedTime());
        }
        return this.logsRepository.save(depDB);
    }

    @Override
    public void deleteLogById(Long logId) {
        this.logsRepository.deleteById(logId);
    }

    @Override
    public Optional<LogsEntity> findByRequestKey(String requestKey) {
        return this.logsRepository.findByRequestKey(requestKey);
    }

    @Override
    public Iterable<LogsEntity> findAllByOrderByUpdatedTimeDesc() {
        Sort sortBy = Sort.by(new Sort.Order(Sort.Direction.DESC, "updatedTime").ignoreCase());
        return this.logsRepository.findAll(sortBy);
    }
}
