package net.hrsoft.transparent_factory_manager.home.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/5 10:48.
 * email caiheng@hrsoft.net
 */

public class GetProcedureResponse extends BaseModel {
    private ProcedureModel[] procedures;

    public GetProcedureResponse(ProcedureModel[] procedures) {
        this.procedures = procedures;
    }

    public ProcedureModel[] getProcedures() {
        return procedures;
    }

    public void setProcedures(ProcedureModel[] procedures) {
        this.procedures = procedures;
    }
}
