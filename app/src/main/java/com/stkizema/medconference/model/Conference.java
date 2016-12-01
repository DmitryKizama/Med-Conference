package com.stkizema.medconference.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinProperty;
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

    @ToMany(referencedJoinProperty = "topicId")
    private List<Topic> listTopics;

    @ToMany(joinProperties = {
            @JoinProperty(name = "conferenceId", referencedName = "id")
    })
    private List<User> listInvitedDoctors;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 394449787)
    private transient ConferenceDao myDao;

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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1117894799)
    public List<Topic> getListTopics() {
        if (listTopics == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TopicDao targetDao = daoSession.getTopicDao();
            List<Topic> listTopicsNew = targetDao
                    ._queryConference_ListTopics(conferenceId);
            synchronized (this) {
                if (listTopics == null) {
                    listTopics = listTopicsNew;
                }
            }
        }
        return listTopics;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 2076803589)
    public synchronized void resetListTopics() {
        listTopics = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 499412570)
    public List<User> getListInvitedDoctors() {
        if (listInvitedDoctors == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> listInvitedDoctorsNew = targetDao
                    ._queryConference_ListInvitedDoctors(conferenceId);
            synchronized (this) {
                if (listInvitedDoctors == null) {
                    listInvitedDoctors = listInvitedDoctorsNew;
                }
            }
        }
        return listInvitedDoctors;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 677689293)
    public synchronized void resetListInvitedDoctors() {
        listInvitedDoctors = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1463206369)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getConferenceDao() : null;
    }

}
