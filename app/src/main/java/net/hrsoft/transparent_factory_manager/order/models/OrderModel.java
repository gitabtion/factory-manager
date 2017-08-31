package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/8/28 10:05.
 * email caiheng@hrsoft.net
 */

public class OrderModel extends BaseModel{
    private int id;
    private String title;
    private int status;
    private int type;
    private String description;
    private String orderCode;
    private String totalCount;
    private String customerInfo;
    private String startTime;
    private String endTime;

    public OrderModel(String title) {
        this.title = title;
    }

    public OrderModel(int id, String title, int status, int type, String description, String orderCode, String
            totalCount, String customerInfo, String startTime, String endTime) {
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
