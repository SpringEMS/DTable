package com.shashi.dynamic_table.service;

import com.shashi.dynamic_table.entity.ColumnTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.repo.ColumnTableRepository;
import com.shashi.dynamic_table.repo.MasterMasterTableRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private MasterMasterTableRepository masterTableRepository;

    @Autowired
    private ColumnTableRepository columnTableRepository;

    public String insertInTable(HashMap<Object, Object> request) {
        MasterTable masterTable = masterTableRepository.findByTableName(request.get("table").toString());
        Integer tableId = masterTable.getTable_id();
        List<ColumnTable> columnTableList = columnTableRepository.findColumnNameAndTypeByTableId(tableId);
        String str = masterTableRepository.insertIntoTable(request, columnTableList);
        return str;
    }
}
