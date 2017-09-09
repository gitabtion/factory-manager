package net.hrsoft.transparent_factory_manager.home.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/8/29 21:57.
 * email caiheng@hrsoft.net
 */

public class ProcedureModel extends BaseModel {
    private int id;
    private String name;
    private double weight;
    private String standard;
    private String description;
    private int orderId;
    private int totalCount;
    private int leaderId;
    private int workGroupId;
    private String startTime;
    private String endTime;
    private String workGroupName;
    private int successCount;

    public ProcedureModel(int id, String name, double weight, String standard, String description, int orderId, int
            totalCount, int leaderId, int workGroupId, String startTime, String endTime, String workGroupName, int
            successCount) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.standard = standard;
        this.description = description;
        this.orderId = orderId;
        this.totalCount = totalCount;
        this.leaderId = leaderId;
        this.workGroupId = workGroupId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workGroupName = workGroupName;
        this.successCount = successCount;
    }

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

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
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

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }
}
