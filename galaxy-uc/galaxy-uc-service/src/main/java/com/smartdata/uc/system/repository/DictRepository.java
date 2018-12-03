package com.smartdata.uc.system.repository;

import com.smartdata.uc.system.domain.Dict;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
public interface DictRepository extends BaseRepository<Dict, Long> {

    /**
     * 根据字典标识查询
     * @param name 字典标识
     * @param status 状态
     */
    public Dict findByNameAndStatus(String name, Byte status);
}
