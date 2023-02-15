package com.shashi.dynamic_table.repo;

import com.shashi.dynamic_table.entity.ColumnTable;
import com.shashi.dynamic_table.entity.MasterTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface ColumnTableRepository extends JpaRepository<ColumnTable, Integer>{

    @Query(value = "SELECT * from x_columnTable where table_id =:tableID",
            nativeQuery = true)
    List<ColumnTable> findColumnNameAndTypeByTableId(Integer tableID);


    @Query(value = "DELETE FROM x_columnTable WHERE table_id =:tableID AND columnName IN =:columnName",
            nativeQuery = true)
    List<ColumnTable> deleteSelectedColumns(Integer tableID, ArrayList<String> columnName);
}
