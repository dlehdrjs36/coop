package com.projectteam.coop.domain;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

}
