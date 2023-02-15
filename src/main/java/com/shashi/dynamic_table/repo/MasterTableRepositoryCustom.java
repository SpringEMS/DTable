package com.shashi.dynamic_table.repo;

import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.ColumnTable;

import java.util.HashMap;
import java.util.List;

public interface MasterTableRepositoryCustom {

    public String createDynamicTable(DynamicTable dynamicTable);

    public String addColumnInTable(DynamicTable dynamicTable);

    public String removeTableColumns(DynamicTable dynamicTable);

    String insertIntoTable(HashMap<Object, Object> request, List<ColumnTable> columnTableList);
}
