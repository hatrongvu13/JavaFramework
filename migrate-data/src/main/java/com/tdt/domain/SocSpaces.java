package com.tdt.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SOC_SPACES")
public class SocSpaces {
    private long spaceId;
    private String prettyName;
    private String displayName;
    private byte registration;
    private String description;
    private Timestamp avatarLastUpdated;
    private byte visibility;
    private Byte priority;
    private String groupId;
    private String url;
    private Timestamp createdDate;
    private String template;
    private Timestamp bannerLastUpdated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPACE_ID", nullable = false)
    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
    }

    @Basic
    @Column(name = "PRETTY_NAME", nullable = false, length = 200)
    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    @Basic
    @Column(name = "DISPLAY_NAME", nullable = false, length = 200)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Basic
    @Column(name = "REGISTRATION", nullable = false)
    public byte getRegistration() {
        return registration;
    }

    public void setRegistration(byte registration) {
        this.registration = registration;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "AVATAR_LAST_UPDATED", nullable = true)
    public Timestamp getAvatarLastUpdated() {
        return avatarLastUpdated;
    }

    public void setAvatarLastUpdated(Timestamp avatarLastUpdated) {
        this.avatarLastUpdated = avatarLastUpdated;
    }

    @Basic
    @Column(name = "VISIBILITY", nullable = false)
    public byte getVisibility() {
        return visibility;
    }

    public void setVisibility(byte visibility) {
        this.visibility = visibility;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = true)
    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "GROUP_ID", nullable = true, length = 250)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "URL", nullable = true, length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "CREATED_DATE", nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "TEMPLATE", nullable = true, length = 200)
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Basic
    @Column(name = "BANNER_LAST_UPDATED", nullable = true)
    public Timestamp getBannerLastUpdated() {
        return bannerLastUpdated;
    }

    public void setBannerLastUpdated(Timestamp bannerLastUpdated) {
        this.bannerLastUpdated = bannerLastUpdated;
    }
}
