package com.smartdata.uc.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.smartdata.uc.core.utils.FileUpload;

/**
 * @author 小懒虫
 * @date 2018/11/3
 */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FileUpload.getPathPattern()).addResourceLocations("file:" + FileUpload.getUploadPath());
    }
}
