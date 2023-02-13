package com.shashi.dynamic_table.repo;

import com.shashi.dynamic_table.dao.DynamicTable;

public interface MasterTableRepositoryCustom {

    public String createDynamicTable(DynamicTable dynamicTable);

    public String addColumnInTable(DynamicTable dynamicTable);

    public String removeTableColumns(DynamicTable dynamicTable);
}
