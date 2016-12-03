package com.stkizema.medconference.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;

@Entity(indexes = {
        @Index(value = "conferenceId", unique = true)
})
public class Conference {

    @Id
    private Long conferenceId;

    @NotNull
    @Property(nameInDb = "NAMECONFERENCE")
    private String name;

    @NotNull
    @Property(nameInDb = "DATE")
    private Date date;

@Generated(hash = 1926992454)
public Conference(Long conferenceId, @NotNull String name, @NotNull Date date) {
    this.conferenceId = conferenceId;
    this.name = name;
    this.date = date;
}

@Generated(hash = 1436823095)
public Conference() {
}

public Long getConferenceId() {
    return this.conferenceId;
}

public void setConferenceId(Long conferenceId) {
    this.conferenceId = conferenceId;
}

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

public Date getDate() {
    return this.date;
}

public void setDate(Date date) {
    this.date = date;
}

    

}
