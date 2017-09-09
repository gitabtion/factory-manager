package net.hrsoft.transparent_factory_manager.mine.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;
import net.hrsoft.transparent_factory_manager.order.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abtion.
 * @since 17/9/9 21:15.
 * email caiheng@hrsoft.net
 */

public class GetGroupLIstResponse extends BaseModel {
    private ArrayList<GroupModel> records;
    private int count;

    public GetGroupLIstResponse(ArrayList<GroupModel> records, int count) {
        this.records = records;
        this.count = count;
    }

    public ArrayList<GroupModel> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<GroupModel> records) {
        this.records = records;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
