package net.hrsoft.transparent_factory_manager.account.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

/**
 * @author abtion.
 * @since 17/9/4 20:00.
 * email caiheng@hrsoft.net
 */

public class UserModel extends BaseModel {
    private String name;
    private String password;
    private String mobile;
    private String email;
    private String role;
    private int id;

    public UserModel(String name, String password, String mobile, String email, String role, int id) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.role = role;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
