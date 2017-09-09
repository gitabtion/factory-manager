package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/9 15:04.
 * email caiheng@hrsoft.net
 */

public class ProcedureDataModel extends BaseModel {
    private int id;
    private int status;
    private int procedureId;
    private String startTime;
    private String endTime;
    private int workGroupId;
    private int totalCount;
    private int successCount;
    private String leaderName;

    public ProcedureDataModel() {
    }

    public ProcedureDataModel(int id, int status, int procedureId, String startTime, String endTime, int workGroupId,
                              int totalCount, int successCount, String leaderName) {
        this.id = id;
        this.status = status;
        this.procedureId = procedureId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workGroupId = workGroupId;
        this.totalCount = totalCount;
        this.successCount = successCount;
        this.leaderName = leaderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
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

    public int getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(int workGroupId) {
        this.workGroupId = workGroupId;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
