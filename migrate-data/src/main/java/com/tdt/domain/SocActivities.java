package com.tdt.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SOC_ACTIVITIES")
public class SocActivities  {
    private long activityId;
    private String appId;
    private String body;
    private String externalId;
    private String providerId;
    private boolean hidden;
    private long updatedDate;
    private boolean locked;
    private String ownerId;
    private String permalink;
    private long posted;
    private String posterId;
    private String title;
    private String titleId;
    private String type;
    private boolean isComment;
    private Long parentId;
    private Long oldWikiId;
    private Long oldCommentId;

    @Id
    @Column(name = "ACTIVITY_ID", nullable = false, unique = true)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "APP_ID", length = 200)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "BODY", length = 2147483647)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "EXTERNAL_ID", length = 200)
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Basic
    @Column(name = "PROVIDER_ID", length = 200)
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "HIDDEN", nullable = false)
    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Basic
    @Column(name = "UPDATED_DATE", nullable = false)
    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "LOCKED", nullable = false)
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "OWNER_ID", length = 200)
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "PERMALINK", length = 500)
    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    @Basic
    @Column(name = "POSTED", nullable = false)
    public long getPosted() {
        return posted;
    }

    public void setPosted(long posted) {
        this.posted = posted;
    }

    @Basic
    @Column(name = "POSTER_ID", nullable = false, length = 200)
    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    @Basic
    @Column(name = "TITLE", nullable = false, length = 2147483647)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "TITLE_ID", length = 1024)
    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Basic
    @Column(name = "TYPE", length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "IS_COMMENT", nullable = false)
    public boolean isComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }

    @Basic
    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "OLD_WIKI_ID")
    public Long getOldWikiId() {
        return oldWikiId;
    }

    public void setOldWikiId(Long oldWikiId) {
        this.oldWikiId = oldWikiId;
    }

    @Basic
    @Column(name = "OLD_COMMENT_ID")
    public Long getOldCommentId() {
        return oldCommentId;
    }

    public void setOldCommentId(Long oldCommentId) {
        this.oldCommentId = oldCommentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocActivities that = (SocActivities) o;
        return activityId == that.activityId &&
                hidden == that.hidden &&
                updatedDate == that.updatedDate &&
                locked == that.locked &&
                posted == that.posted &&
                isComment == that.isComment &&
                Objects.equals(appId, that.appId) &&
                Objects.equals(body, that.body) &&
                Objects.equals(externalId, that.externalId) &&
                Objects.equals(providerId, that.providerId) &&
                Objects.equals(ownerId, that.ownerId) &&
                Objects.equals(permalink, that.permalink) &&
                Objects.equals(posterId, that.posterId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(titleId, that.titleId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, appId, body, externalId, providerId, hidden, updatedDate, locked, ownerId, permalink, posted, posterId, title, titleId, type, isComment, parentId);
    }

}
