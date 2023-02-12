package com.shashi.dynamic_table.service;

import com.shashi.dynamic_table.dao.ColumnValue;
import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.repo.MasterTableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterTableService {

    @Autowired
    private MasterTableRepository masterTablerepository;

    @PersistenceContext
    private EntityManager entityManager;

    public String createMaster(DynamicTable dynamicTable) {
        //Task -1: Insert in master

        //Task -2: Create new table

        StringBuilder tableCreationQuery = new StringBuilder();
        String tableName = dynamicTable.getTable();
        StringBuilder columnValues = new StringBuilder();
        tableCreationQuery.append("CREATE TABLE ");
        tableCreationQuery.append(tableName);
        tableCreationQuery.append(" (");
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        int len = cvs.size();
        int i=0;
        for(ColumnValue cv : cvs){
            i++;
            if(i !=len){
                tableCreationQuery.append(cv.columnName+" "+cv.columnType+"(255),");
            }else{
                tableCreationQuery.append(cv.columnName+" "+cv.columnType+"(255) ");
            }
        }
        tableCreationQuery.append(");");
        System.out.println(tableCreationQuery.toString());
//        queryValue = tableCreationQuery.toString();
//        String str = MasterTableService.getQuery();
//        masterTablerepository.createTable(dynamicTable);
        doSomething(tableCreationQuery.toString());
        return tableCreationQuery.toString();
    }

    private void doSomething(String myQuery) {

        EntityTransaction txn = null;
        try {
            txn = entityManager.getTransaction();
            txn.begin();
            entityManager.createNativeQuery( myQuery ).executeUpdate();
            txn.commit();
        }
        catch ( Throwable e ) {
            if ( txn != null && txn.isActive() ) {
                txn.rollback();
            }
            throw e;
        }
    }

}
