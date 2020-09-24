package com.item.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbModel {

    private String driverName;
    private String ipAddr;
    private String username;
    private String password;
    private String port;
    private String tableLabel;
}
