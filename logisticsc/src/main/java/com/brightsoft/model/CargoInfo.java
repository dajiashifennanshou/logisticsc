package com.brightsoft.model;

import java.io.Serializable;

public class CargoInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private Integer number;

    private Double weight;

    private Double volume;

    private String packingInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getPackingInfo() {
        return packingInfo;
    }

    public void setPackingInfo(String packingInfo) {
        this.packingInfo = packingInfo == null ? null : packingInfo.trim();
    }
}