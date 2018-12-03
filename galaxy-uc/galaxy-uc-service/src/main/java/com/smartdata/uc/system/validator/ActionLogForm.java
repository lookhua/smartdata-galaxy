package com.smartdata.uc.system.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

import com.smartdata.uc.system.domain.ActionLog;

import java.io.Serializable;

@Data
public class ActionLogForm extends ActionLog implements Serializable {
    @NotEmpty(message = "标题不能为空")
    private String title;
}
