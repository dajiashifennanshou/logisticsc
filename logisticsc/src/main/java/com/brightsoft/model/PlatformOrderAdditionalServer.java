package com.brightsoft.model;

import java.io.Serializable;

public class PlatformOrderAdditionalServer implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer isReceipt;

    private Integer isImExStore;

    private Integer isLoadCargo;

    private Integer loadCargoFloor;

    private Integer loadCargoIsElevator;

    private Integer isUnloadCargo;

    private Integer unloadCargoFloor;

    private Integer unloadCargoIsElevator;

    private Integer isCollectionDelivery;

    private Double collectionDeliveryMoney;

    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(Integer isReceipt) {
        this.isReceipt = isReceipt;
    }

    public Integer getIsImExStore() {
        return isImExStore;
    }

    public void setIsImExStore(Integer isImExStore) {
        this.isImExStore = isImExStore;
    }

    public Integer getIsLoadCargo() {
        return isLoadCargo;
    }

    public void setIsLoadCargo(Integer isLoadCargo) {
        this.isLoadCargo = isLoadCargo;
    }

    public Integer getLoadCargoFloor() {
        return loadCargoFloor;
    }

    public void setLoadCargoFloor(Integer loadCargoFloor) {
        this.loadCargoFloor = loadCargoFloor;
    }

    public Integer getLoadCargoIsElevator() {
        return loadCargoIsElevator;
    }

    public void setLoadCargoIsElevator(Integer loadCargoIsElevator) {
        this.loadCargoIsElevator = loadCargoIsElevator;
    }

    public Integer getIsUnloadCargo() {
        return isUnloadCargo;
    }

    public void setIsUnloadCargo(Integer isUnloadCargo) {
        this.isUnloadCargo = isUnloadCargo;
    }

    public Integer getUnloadCargoFloor() {
        return unloadCargoFloor;
    }

    public void setUnloadCargoFloor(Integer unloadCargoFloor) {
        this.unloadCargoFloor = unloadCargoFloor;
    }

    public Integer getUnloadCargoIsElevator() {
        return unloadCargoIsElevator;
    }

    public void setUnloadCargoIsElevator(Integer unloadCargoIsElevator) {
        this.unloadCargoIsElevator = unloadCargoIsElevator;
    }

    public Integer getIsCollectionDelivery() {
        return isCollectionDelivery;
    }

    public void setIsCollectionDelivery(Integer isCollectionDelivery) {
        this.isCollectionDelivery = isCollectionDelivery;
    }

    public Double getCollectionDeliveryMoney() {
        return collectionDeliveryMoney;
    }

    public void setCollectionDeliveryMoney(Double collectionDeliveryMoney) {
        this.collectionDeliveryMoney = collectionDeliveryMoney;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}