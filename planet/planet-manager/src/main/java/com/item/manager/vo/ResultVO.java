package com.item.manager.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 传送给前提的实体类
 * @author sunjg
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVO<T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T object;
}
