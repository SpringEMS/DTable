package com.shashi.dynamic_table.repo;


import com.shashi.dynamic_table.entity.MasterTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMasterTableRepository extends
        JpaRepository<MasterTable, Integer>, MasterTableRepositoryCustom {

    MasterTable findByTableName(String tableName);
}
