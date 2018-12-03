package com.smartdata.uc.system.service;

import com.smartdata.uc.core.enums.StatusEnum;
import com.smartdata.uc.system.domain.User;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
public interface UserService {

    Page<User> getPageList(Example<User> example, Integer pageIndex, Integer pageSize);

    User save(User user);

    List<User> save(List<User> userList);

    User getByName(String username, Byte... statusEnums);

    User getId(Long id);

    List<User> getIdList(List<Long> ids);

    @Transactional
    Integer updateStatus(StatusEnum statusEnum, List<Long> idList);
}
