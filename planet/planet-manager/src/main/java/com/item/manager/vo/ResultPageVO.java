package com.item.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ResultPageVO<T> {

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码")
    private Integer code;
    /**
     * 返回消息
     */
    @ApiModelProperty(value = "消消息题")
    private String message;
    /**
     * 返回数据集合
     */
    @ApiModelProperty(value = "返回的数据")
    private Object rows;
    /**
     * 总数
     */
    private  Long total;
}
