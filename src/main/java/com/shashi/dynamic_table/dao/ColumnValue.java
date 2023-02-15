package com.shashi.dynamic_table.dao;

import lombok.Data;

@Data
public class ColumnValue {

    public String columnName;

    public String columnType;

    public Integer columnSize;
}