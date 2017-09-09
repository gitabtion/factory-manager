package net.hrsoft.transparent_factory_manager.mine.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/8 20:19.
 * email caiheng@hrsoft.net
 */

public class UpdatePasswordModel extends BaseModel {
    private String password;

    public UpdatePasswordModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
