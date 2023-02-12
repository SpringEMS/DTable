package com.shashi.dynamic_table.repo;


import com.shashi.dynamic_table.NativeQueries;
import com.shashi.dynamic_table.controller.MasterTableController;
import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.service.MasterTableService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTableRepository extends JpaRepository<MasterTable, Integer> {

//    @Modifying
//    @Transactional
//    @Query(value= "", nativeQuery=true)
//    void createTable(DynamicTable dynamicTable);

}
