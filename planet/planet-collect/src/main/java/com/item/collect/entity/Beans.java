package com.item.collect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beans<T> {

    private List<T> beans;
}
