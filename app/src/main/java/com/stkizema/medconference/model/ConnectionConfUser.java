package com.stkizema.medconference.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ConnectionConfUser {

    @Id
    private Long Id;
    private Long userId;
    private Long confId;

    @Generated(hash = 811618672)
    public ConnectionConfUser(Long Id, Long userId, Long confId) {
        this.Id = Id;
        this.userId = userId;
        this.confId = confId;
    }

    @Generated(hash = 1324319288)
    public ConnectionConfUser() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getConfId() {
        return this.confId;
    }

    public void setConfId(Long confId) {
        this.confId = confId;
    }
}
