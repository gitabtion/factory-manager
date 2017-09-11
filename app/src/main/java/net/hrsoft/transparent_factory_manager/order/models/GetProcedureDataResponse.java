package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

import java.util.ArrayList;

/**
 * @author abtion.
 * @since 17/9/11 19:39.
 * email caiheng@hrsoft.net
 */

public class GetProcedureDataResponse extends BaseModel {
    private ArrayList<ProcedureDataModel> logs;

    public GetProcedureDataResponse(ArrayList<ProcedureDataModel> logs) {
        this.logs = logs;
    }

    public ArrayList<ProcedureDataModel> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<ProcedureDataModel> logs) {
        this.logs = logs;
    }
}
