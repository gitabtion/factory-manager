package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/6 20:14.
 * email caiheng@hrsoft.net
 */

public class CurrentOrderModel extends OrderModel{

    private String CustomerInfo;
    private double capacity;

    public CurrentOrderModel(int id, String title, int status, int type, String description, String orderCode, String
            totalCount, String customerInfo, String startTime, String endTime, String customerInfo1, double capacity) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.type = type;
        this.description = description;
        this.orderCode = orderCode;
        this.totalCount = totalCount;
        this.customerInfo = customerInfo;
        this.startTime = startTime;
        this.endTime = endTime;
        CustomerInfo = customerInfo1;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
