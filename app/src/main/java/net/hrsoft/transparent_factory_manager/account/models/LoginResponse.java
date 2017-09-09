package net.hrsoft.transparent_factory_manager.account.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/5 10:11.
 * email caiheng@hrsoft.net
 */

public class LoginResponse extends BaseModel {
    private String token;
    private UserModel user;

    public LoginResponse(String token, UserModel user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
