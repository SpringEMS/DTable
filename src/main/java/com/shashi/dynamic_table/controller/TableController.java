package com.shashi.dynamic_table.controller;

import com.shashi.dynamic_table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/insert")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping
    public String insertInTable(@RequestBody HashMap<Object, Object> request){
        return  tableService.insertInTable(request);
    }
}
