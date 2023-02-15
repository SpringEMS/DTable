package com.shashi.dynamic_table.controller;

import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.service.MasterTableService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterTableController {

    @Autowired
    private MasterTableService masterTableService;

    @PostMapping("/create")
    public String createMaster(@RequestBody DynamicTable dynamicTable){
        return masterTableService.createMaster(dynamicTable);
    }

    @PostMapping("/addcolumn")
    public String addTableColumns(@RequestBody DynamicTable dynamicTable){
        return masterTableService.updateMaster(dynamicTable);
    }

    @PostMapping("/removecolumn")
    public String removeTableColumns(@RequestBody DynamicTable dynamicTable){
        return masterTableService.removeTableColumns(dynamicTable);
    }

    @GetMapping("/getall")
    public List<MasterTable> getAllTables(){
        return masterTableService.getAllTables();
    }
}
