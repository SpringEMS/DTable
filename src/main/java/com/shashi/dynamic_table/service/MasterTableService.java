package com.shashi.dynamic_table.service;

import com.shashi.dynamic_table.dao.ColumnValue;
import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.repo.MasterTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterTableService {

    @Autowired
    private MasterTableRepository masterTablerepository;

    public String createMaster(DynamicTable dynamicTable) {
        //Task -1: Insert in master
        insertInMaster(dynamicTable);
        //Task -2: Create new table
        return masterTablerepository.createDynamicTable(dynamicTable);
    }

    private void insertInMaster(DynamicTable dynamicTable) {
        String tableName = dynamicTable.getTable();
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        List<MasterTable> masterTableEntryList = new ArrayList<>();
        for(ColumnValue cv : cvs){
            masterTableEntryList.add(new MasterTable(tableName, cv.columnName, cv.columnType));
        }
        masterTablerepository.saveAll(masterTableEntryList);
    }

    public String updateMaster(DynamicTable dynamicTable) {
        insertInMaster(dynamicTable);
        return masterTablerepository.addColumnInTable(dynamicTable);
    }

    public String removeTableColumns(DynamicTable dynamicTable) {
        DeleteInMaster(dynamicTable);
        return masterTablerepository.removeTableColumns(dynamicTable);
    }

    private void DeleteInMaster(DynamicTable dynamicTable) {
    }
}
