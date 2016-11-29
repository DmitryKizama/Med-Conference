package com.stkizema.medconference.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

@Entity(indexes = {
        @Index(value = "id", unique = true)
})
public class User {

    public static final String PERMISSIONDOCTOR = "Doctor";
    public static final String PERMISSIONADMIN = "Admin";

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Property(nameInDb = "PERMISSION")
    private String permission;

    @NotNull
    @Property(nameInDb = "USERNAME")
    private String login;

    @NotNull
    @Property(nameInDb = "EMAIL")
    private String email;

    @NotNull
    @Property(nameInDb = "PASSWORD")
    private String password;

    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 217871901)
    public User(Long id, @NotNull String permission, @NotNull String login,
            @NotNull String email, @NotNull String password) {
        this.id = id;
        this.permission = permission;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
