package com.shashi.dynamic_table.repo;

import com.shashi.dynamic_table.dao.ColumnValue;
import com.shashi.dynamic_table.dao.DynamicTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MasterTableRepositoryCustomImpl implements MasterTableRepositoryCustom{

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String createDynamicTable(DynamicTable dynamicTable) {

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
            String colType = cv.columnType;
            if(colType.equalsIgnoreCase("varchar")){
                colType = colType +"(255)";
            }else{
                colType = colType +" ";
            }
            if(i !=len){
                tableCreationQuery.append(cv.columnName+" "+colType+" ,");
            }else{
                tableCreationQuery.append(cv.columnName+" "+colType+ ");");
            }
        }
        System.out.println(tableCreationQuery.toString());
        createTableWithQuery(tableCreationQuery.toString());
        return "Table created Successfully with Query: \n"+ tableCreationQuery.toString();
    }

    @Override
    public String addColumnInTable(DynamicTable dynamicTable) {

        StringBuilder tableCreationQuery = new StringBuilder();
        String tableName = dynamicTable.getTable();
        StringBuilder columnValues = new StringBuilder();
        tableCreationQuery.append("ALTER TABLE ");
        tableCreationQuery.append(tableName+ " ");
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        int len = cvs.size();
        int i=0;
        for(ColumnValue cv : cvs){
            i++;
            String colType = cv.columnType;
            if(colType.equalsIgnoreCase("varchar")){
                colType = colType +"(255)";
            }else{
                colType = colType +" ";
            }
            if(i !=len){
                tableCreationQuery.append("ADD ").append(cv.columnName).append(" ").append(colType).append(" ,");
            }else{
                tableCreationQuery.append("ADD ").append(cv.columnName).append(" ").append(colType).append(";");
            }
        }
        System.out.println(tableCreationQuery.toString());
        jdbcTemplate.execute(tableCreationQuery.toString());
        return "Columns in "+ tableName+" "+"Added Successfully with Query: \n"+ tableCreationQuery.toString();
    }

    @Override
    public String removeTableColumns(DynamicTable dynamicTable) {
        StringBuilder tableCreationQuery = new StringBuilder();
        String tableName = dynamicTable.getTable();
        StringBuilder columnValues = new StringBuilder();
        tableCreationQuery.append("ALTER TABLE ");
        tableCreationQuery.append(tableName).append(" ");
        tableCreationQuery.append("DROP COLUMN ");
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        int len = cvs.size();
        int i=0;
        for(ColumnValue cv : cvs){
            i++;
            if(i !=len){
                tableCreationQuery.append(cv.columnName).append(" ,");
            }else{
                tableCreationQuery.append(cv.columnName).append(" ;");
            }
        }
        System.out.println(tableCreationQuery.toString());
        jdbcTemplate.execute(tableCreationQuery.toString());
        return "Columns in "+ tableName+" "+"removed Successfully with Query: \n"+ tableCreationQuery.toString();
    }

    private void createTableWithQuery(String myQuery) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.createNativeQuery( myQuery ).executeUpdate();
            transaction.commit();
        }
        catch ( Throwable throwable ) {
            if ( transaction != null && transaction.isActive() ) {
                transaction.rollback();
            }
            throw throwable;
        }
    }
}
