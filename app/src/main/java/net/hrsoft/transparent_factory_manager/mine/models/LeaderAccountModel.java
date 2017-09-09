package net.hrsoft.transparent_factory_manager.mine.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

/**
 * @author abtion.
 * @since 17/9/9 23:21.
 * email caiheng@hrsoft.net
 */

public class LeaderAccountModel extends BaseModel {
    private String name;
    private String mobile;
    private String password;

    public LeaderAccountModel(String name, String mobile, String password) {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
