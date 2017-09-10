package net.hrsoft.transparent_factory_manager.home.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author abtion.
 * @since 17/9/5 10:48.
 * email caiheng@hrsoft.net
 */

public class GetProcedureResponse extends BaseModel {
    private ArrayList<ProcedureModel> procedures;

    public GetProcedureResponse(ArrayList<ProcedureModel> procedures) {
        this.procedures = procedures;
    }

    public ArrayList<ProcedureModel> getProcedures() {
        return procedures;
    }

    public void setProcedures(ArrayList<ProcedureModel> procedures) {
        this.procedures = procedures;
    }

    public void addAll(Collection<ProcedureModel> procedureModelCollections){
        this.procedures.addAll(procedureModelCollections);
    }
}
