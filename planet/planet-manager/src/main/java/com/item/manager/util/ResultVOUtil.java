package com.item.manager.util;


import com.item.manager.enums.ResultEnums;
import com.item.manager.vo.ResultPageVO;
import com.item.manager.vo.ResultVO;

/**
 * 返回的工具类
 * @author zcm
 */
public class ResultVOUtil {

    /**
     * 成功
     * @param object
     * @return
     */
    public static ResultVO<Object> getSuccess(Object object){
        return new ResultVO<Object>(ResultEnums.SUCCESS.getCode(), ResultEnums.SUCCESS.getMessage(),object);
    }

    /**
     * 失败
     * @param object
     * @return
     */
    public static ResultVO<Object> getFailure(Object object){
        return new ResultVO<Object>(ResultEnums.FAILURE.getCode(),ResultEnums.FAILURE.getMessage(),object);
    }

    /**
     * 失败
     * @param msg
     * @return
     */
    public static ResultVO<Object> getFailureMsg(String msg){
        return new ResultVO<Object>(ResultEnums.FAILURE.getCode(),msg,null);
    }

    /**
     * 其他情况
     * @param resultEnums
     * @param object
     * @return
     */
    public static ResultVO<Object> getFailure(ResultEnums resultEnums,Object object){
        return new ResultVO<Object>(resultEnums.getCode(),resultEnums.getMessage(),object);
    }

    /**
     * 分页成功
     * @param rows
     * @param total
     * @return
     */
    public static ResultPageVO<Object> getPageSuccess(Object rows, Long total){
        return new ResultPageVO<Object>(ResultEnums.SUCCESS.getCode(), ResultEnums.SUCCESS.getMessage(),rows,total);
    }


    /**
     * 验证失败，请重新登陆
     * @return
     */
    public static ResultVO<Object> getLoginVerification(){
        return new ResultVO<Object>(ResultEnums.LOGIN_VERIFICATION.getCode(), ResultEnums.LOGIN_VERIFICATION.getMessage(),null);
    }

}
