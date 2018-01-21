package com.wrt.xinsilu.lerist.model;

/**
 * Created by Lerist on 2016/8/16, 0016.
 */

public class CargoPackType {

    /**
     * id : 41
     * name : 纸箱
     * type : cargo_package_type
     */

    private int id;
    private String name;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getName();
    }
}
