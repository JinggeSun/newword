package com.item.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnums {

    /**
     * 成功
     */
    SUCCESS(200,"success"),
    FAILURE(400,"failure"),
    PARAM_ERROR(201,":参数错误"),
    SEARCH_ERROR(201,"查询参数返回失败"),
    LOGIN_VERIFICATION(202,"验证失败，请重新登陆"),

    ;

    private Integer code;
    private String message;
}
