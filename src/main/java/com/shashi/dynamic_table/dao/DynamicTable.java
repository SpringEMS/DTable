package com.shashi.dynamic_table.dao;

import com.shashi.dynamic_table.dao.ColumnValue;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicTable {

    public String table;

    public List<ColumnValue> columnValues;
}
