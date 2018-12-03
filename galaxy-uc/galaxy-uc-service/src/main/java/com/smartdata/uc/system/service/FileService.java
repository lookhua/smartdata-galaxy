package com.smartdata.uc.system.service;

import com.smartdata.uc.system.domain.File;

/**
 * @author 小懒虫
 * @date 2018/11/02
 */
public interface FileService {
    File isFile(String sha1);

    File save(File file);
}

