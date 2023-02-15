package com.shashi.dynamic_table.repo;

import com.shashi.dynamic_table.dao.ColumnValue;
import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.ColumnTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterTableRepositoryCustomImpl implements MasterTableRepositoryCustom {

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
            if(!colType.equalsIgnoreCase("date")){
                colType = colType +"("+ cv.getColumnSize()+")";
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
            if(!colType.equalsIgnoreCase("date")){
                colType = colType +"("+ cv.getColumnSize()+")";
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
//        tableCreationQuery.append("DROP ");
        List<ColumnValue> cvs = dynamicTable.getColumnValues();
        int len = cvs.size();
        int i=0;
        for(ColumnValue cv : cvs){
            i++;
            if(i !=len){
                tableCreationQuery.append("DROP ").append(cv.columnName).append(" ,");
            }else{
                tableCreationQuery.append("DROP ").append(cv.columnName).append(" ;");
            }
        }
        System.out.println(tableCreationQuery.toString());
        jdbcTemplate.execute(tableCreationQuery.toString());
        return "Columns in "+ tableName+" "+"removed Successfully with Query: \n"+ tableCreationQuery.toString();
    }

    @Override
    public String insertIntoTable(HashMap<Object, Object> request, List<ColumnTable> columnTableList) {
        String tableName = request.get("table").toString();
        HashMap<String,Object> columnValuesMap = (HashMap<String, Object>) request.get("columnValues");
        //Create HashMap
        HashMap<String,String> columnNameAndTypeMap = new HashMap<>();
        for(ColumnTable ct : columnTableList){
            columnNameAndTypeMap.put(ct.getColumnName(),ct.getColumnDataType());
        }

        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append("(");
        StringBuilder valueBuilder = new StringBuilder();
        valueBuilder.append("(");
        for (Map.Entry<String,Object> entry : columnValuesMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().toString();
            keyBuilder.append(key).append(" ,");
            if(columnNameAndTypeMap.get(key).equalsIgnoreCase("varchar")){
                valueBuilder.append("'").append(value).append("',");
            }else{
                if(value.equalsIgnoreCase("")){
                    valueBuilder.append("''").append(",");
                }else{
                    valueBuilder.append(value).append(",");
                }
            }
        }

        keyBuilder.deleteCharAt(keyBuilder.length()-1).append(")");
        valueBuilder.deleteCharAt(valueBuilder.length()-1).append(")");

        StringBuilder qy = new StringBuilder();
        qy.append("INSERT INTO ")
                .append(tableName)
                .append(" ")
                .append(keyBuilder)
                .append(" values ")
                .append(valueBuilder)
                .append(";");
        System.out.println(qy);
        jdbcTemplate.execute(qy.toString());
        return "Values inserted into table "+ tableName+ "with query: "+ qy.toString();
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
