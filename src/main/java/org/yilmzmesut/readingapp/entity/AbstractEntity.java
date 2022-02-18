package org.yilmzmesut.readingapp.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity implements Serializable {

    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date modifyDate;

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}
