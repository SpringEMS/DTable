package com.shashi.dynamic_table.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "MasterTable")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String tableName;

    private String columnName;

    private String columnDataType;

}
