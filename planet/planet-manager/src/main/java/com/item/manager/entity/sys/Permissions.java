package com.item.manager.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_permissions")
public class Permissions {

    @TableId(type = IdType.AUTO)
    private String id;

    private String permissionsName;
}
