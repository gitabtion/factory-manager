package net.hrsoft.transparent_factory_manager.mine.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/8 20:17.
 * email caiheng@hrsoft.net
 */

public class UpdateUserNameModel extends BaseModel {
    private String name;

    public UpdateUserNameModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
