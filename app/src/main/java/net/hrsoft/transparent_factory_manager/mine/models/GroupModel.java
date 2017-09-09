package net.hrsoft.transparent_factory_manager.mine.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/8 21:29.
 * email caiheng@hrsoft.net
 */

public class GroupModel extends BaseModel {
    private int id;
    private int leaderId;
    private String name;
    private int status;
    private String description;
    private String addOn;
    private String leaderName;

    public GroupModel() {
    }

    public GroupModel(int id, int leaderId, String name, int status, String description, String addOn, String
            leaderName) {
        this.id = id;
        this.leaderId = leaderId;
        this.name = name;
        this.status = status;
        this.description = description;
        this.addOn = addOn;
        this.leaderName = leaderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddOn() {
        return addOn;
    }

    public void setAddOn(String addOn) {
        this.addOn = addOn;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
