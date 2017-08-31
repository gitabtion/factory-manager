package net.hrsoft.transparent_factory_manager.account.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;
import net.hrsoft.transparent_factory_manager.common.Config;

/**
 * @author abtion.
 * @since 17/8/31 19:24.
 * email caiheng@hrsoft.net
 */

public class LoginRequest extends BaseModel {
    private String identifier;
    private String password;
    private int client;

    public LoginRequest(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
        client = Config.CLIENT_ID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
