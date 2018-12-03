package com.smartdata.uc.core.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.smartdata.uc.system.domain.User;

import java.util.Optional;

/**
 * 审核员自动赋值配置
 * @author 小懒虫
 * @date 2018/8/14
 */
@Configuration
public class AuditorConfig implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return Optional.ofNullable(user);
    }
}
