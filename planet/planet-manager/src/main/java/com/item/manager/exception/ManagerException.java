package com.item.manager.exception;


import com.item.manager.enums.ResultEnums;

/**
 * 异常
 * @author zcm
 */
public class ManagerException extends RuntimeException{
    private Integer code;

    public ManagerException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public ManagerException(ResultEnums resultEnums){
        super(resultEnums.getMessage());
        this.code = resultEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
