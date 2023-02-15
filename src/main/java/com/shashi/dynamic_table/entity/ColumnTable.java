package com.shashi.dynamic_table.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "X_ColumnTable")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String columnName;

    private String columnDataType;

    private Integer columnSize;

    @Column(name = "table_id")
    private Integer table_id;

    public ColumnTable(String columnName, String columnDataType, Integer columnSize, Integer table_id) {
        this.columnName = columnName;
        this.columnDataType = columnDataType;
        this.columnSize = columnSize;
        this.table_id = table_id;
    }
}

