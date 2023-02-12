package com.shashi.dynamic_table.controller;

import com.shashi.dynamic_table.dao.DynamicTable;
import com.shashi.dynamic_table.entity.MasterTable;
import com.shashi.dynamic_table.service.MasterTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master")
public class MasterTableController {

    @Autowired
    private MasterTableService masterTableService;

    @PostMapping("/create")
    public String createMaster(@RequestBody DynamicTable dynamicTable){
        return masterTableService.createMaster(dynamicTable);
    }
}
