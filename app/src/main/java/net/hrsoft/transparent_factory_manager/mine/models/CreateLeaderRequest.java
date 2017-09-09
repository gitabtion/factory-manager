package net.hrsoft.transparent_factory_manager.mine.models;

import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

import java.util.ArrayList;

/**
 * @author abtion.
 * @since 17/9/9 23:08.
 * email caiheng@hrsoft.net
 */

public class CreateLeaderRequest extends BaseModel {
    private ArrayList<LeaderAccountModel> leaders;

    public CreateLeaderRequest() {
        leaders = new ArrayList<>();
    }

    public CreateLeaderRequest(ArrayList<LeaderAccountModel> leaders) {
        this.leaders = leaders;
    }

    public ArrayList<LeaderAccountModel> getLeaders() {
        return leaders;
    }

    public void setLeaders(ArrayList<LeaderAccountModel> leaders) {
        this.leaders = leaders;
    }

    public void add(LeaderAccountModel leaderAccountModel){
        leaders.add(leaderAccountModel);
    }
}
