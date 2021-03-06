package com.smartdata.uc.system.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.smartdata.uc.system.domain.ActionLog;

import java.util.List;

public interface ActionLogService {

    Page<ActionLog> getPageList(Example<ActionLog> example, Integer pageIndex, Integer pageSize);

    ActionLog getId(Long id);

    List<ActionLog> getDataLogList(String model, Long recordId);

    ActionLog save(ActionLog actionLog);

    @Transactional
    void deleteId(Long id);

    @Transactional
    void emptyLog();
}
