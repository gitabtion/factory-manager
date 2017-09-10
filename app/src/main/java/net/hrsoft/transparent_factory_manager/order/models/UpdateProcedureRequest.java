package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/10 17:50.
 * email caiheng@hrsoft.net
 */

public class UpdateProcedureRequest extends BaseModel {
    private String name;
    private double weight;
    private String standard;
    private String description;
    private int orderId;
    private int totalCount;
    private int workGroupId;
    private String startTime;
    private String endTime;

    public UpdateProcedureRequest() {
    }

    public UpdateProcedureRequest(String name, double weight, String standard, String description, int orderId, int
            totalCount, int workGroupId, String startTime, String endTime) {
        this.name = name;
        this.weight = weight;
        this.standard = standard;
        this.description = description;
        this.orderId = orderId;
        this.totalCount = totalCount;
        this.workGroupId = workGroupId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(int workGroupId) {
        this.workGroupId = workGroupId;
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
