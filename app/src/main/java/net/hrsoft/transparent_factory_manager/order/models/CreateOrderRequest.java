package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/10 16:22.
 * email caiheng@hrsoft.net
 */

public class CreateOrderRequest extends BaseModel {
    private String title;
    private String startTime;
    private String endTime;
    private int type;
    private String totalCount;
    private String description;
    private String customerInfo;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String title, String startTime, String endTime, int type, String totalCount, String
            description, String customerInfo) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.totalCount = totalCount;
        this.description = description;
        this.customerInfo = customerInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }
}
