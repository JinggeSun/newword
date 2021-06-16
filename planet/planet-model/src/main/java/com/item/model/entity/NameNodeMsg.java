package com.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NameNodeMsg {

    private NamenodeJvmmetrics namenodeJvmmetrics;
    private NameNodeFSNamesystem nameNodeFSNamesystem;

}
