package com.shashi.dynamic_table.service;

import com.shashi.dynamic_table.dao.ColumnValue;
import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.ColumnTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.repo.ColumnTableRepository;
import com.shashi.dynamic_table.repo.MasterTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterTableService {

    @Autowired
    private MasterTableRepository masterTablerepository;

    @Autowired
    private ColumnTableRepository columnTableRepository;

    public String createMaster(DynamicTable dynamicTable) {
        //Task -1: Insert in master
        Integer tableID = insertInMaster(dynamicTable).getTable_id();

        //Task -2: InsertInColumnTable
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        List<ColumnTable> columnTableList = new ArrayList<>();
        for(ColumnValue cv : cvs){
            columnTableList.add(new ColumnTable(cv.getColumnName(),cv.getColumnType(),cv.getColumnSize(),tableID));
        }
        insertInColumnTable(columnTableList);
        //Task -3: Create new table
        return masterTablerepository.createDynamicTable(dynamicTable);
    }

    private void insertInColumnTable(List<ColumnTable> columnTableList) {
        columnTableRepository.saveAll(columnTableList);
    }

    private MasterTable insertInMaster(DynamicTable dynamicTable) {
        String tableName = dynamicTable.getTable();
        return masterTablerepository.save(new MasterTable(tableName));
    }

    public String updateMaster(DynamicTable dynamicTable) {
        MasterTable masterTable = masterTablerepository.findByTableName(dynamicTable.getTable());
        Integer tableId = masterTable.getTable_id();
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        List<ColumnTable> columnTableList = new ArrayList<>();
        for(ColumnValue cv : cvs){
            columnTableList.add(new ColumnTable(cv.getColumnName(),cv.getColumnType(),cv.getColumnSize(),tableId));
        }
        insertInColumnTable(columnTableList);
        return masterTablerepository.addColumnInTable(dynamicTable);
    }

    public String removeTableColumns(DynamicTable dynamicTable) {
        DeleteInMaster(dynamicTable);
        return masterTablerepository.removeTableColumns(dynamicTable);
    }

    private void DeleteInMaster(DynamicTable dynamicTable) {
        MasterTable masterTable = masterTablerepository.findByTableName(dynamicTable.getTable());
        Integer tableId = masterTable.getTable_id();
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        ArrayList<String> coulmnNames = new ArrayList<>();
        for(ColumnValue cv : cvs){
            coulmnNames.add(cv.getColumnName());
        }
        columnTableRepository.deleteSelectedColumns(tableId, coulmnNames);
    }

    public List<MasterTable> getAllTables() {
        return masterTablerepository.findAll();
    }
}
