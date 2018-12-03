package com.smartdata.uc.system.service;

import com.smartdata.uc.core.enums.StatusEnum;
import com.smartdata.uc.system.domain.Dict;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
public interface DictService {

    Dict getName(String name);

    Page<Dict> getPageList(Example<Dict> example, Integer pageIndex, Integer pageSize);

    Dict getId(Long id);

    Dict save(Dict dict);

    @Transactional
    Integer updateStatus(StatusEnum statusEnum, List<Long> idList);
}
