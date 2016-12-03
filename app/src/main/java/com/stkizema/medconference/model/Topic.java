package com.stkizema.medconference.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

@Entity(indexes = {
        @Index(value = "topicId", unique = true)
})
public class Topic {

    @Id
    private Long topicId;

    @NotNull
    @Property(nameInDb = "NAME")
    private String name;

    @NotNull
    @Property(nameInDb = "DESCRIPTON")
    private String description;

    @NotNull
    @Property(nameInDb = "CONFERENCEID")
    private Long conferenceId;

    @NotNull
    @Property(nameInDb = "CREATORID")
    private Long creatorId;

    @Generated(hash = 874632280)
    public Topic(Long topicId, @NotNull String name, @NotNull String description,
            @NotNull Long conferenceId, @NotNull Long creatorId) {
        this.topicId = topicId;
        this.name = name;
        this.description = description;
        this.conferenceId = conferenceId;
        this.creatorId = creatorId;
    }

    @Generated(hash = 849012203)
    public Topic() {
    }

    public Long getTopicId() {
        return this.topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getConferenceId() {
        return this.conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

}
