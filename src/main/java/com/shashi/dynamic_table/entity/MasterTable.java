package com.shashi.dynamic_table.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "X_MasterTable")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id", nullable = false)
    private Integer table_id;

    private String tableName;

    @OneToMany(cascade = CascadeType.ALL ,
            orphanRemoval = true ,
            mappedBy = "table_id")
    private List<ColumnTable> columnTableList;

    public MasterTable(String tableName) {
        this.tableName = tableName;
    }
}
