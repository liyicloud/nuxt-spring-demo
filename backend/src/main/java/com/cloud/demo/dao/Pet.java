package com.cloud.demo.dao;

import jp.co.future.uroborosql.mapping.annotations.Table;

import java.util.Date;

/**
 * Pet model
 *
 * @author Kenichi Hoshi
 */
@Table(name = "PETS")
public class Pet extends BaseModel {

    private String name;

    private Date birthDate;

    private int typeId;

    private int ownerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
