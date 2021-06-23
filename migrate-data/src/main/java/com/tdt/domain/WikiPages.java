package com.tdt.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "WIKI_PAGES")
public class WikiPages {
    private long pageId;
    private long wikiId;
    private Long parentPageId;
    private String author;
    private String name;
    private String owner;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String content;
    private String syntax;
    private String title;
    private String editionComment;
    private String url;
    private Boolean minorEdit;
    private String activityId;
    private Boolean deleted;
    private Long spaceId;
    private Long oldId;

    @Id
    @Column(name = "PAGE_ID", nullable = false)
    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    @Basic
    @Column(name = "WIKI_ID", nullable = false)
    public long getWikiId() {
        return wikiId;
    }

    public void setWikiId(long wikiId) {
        this.wikiId = wikiId;
    }

    @Basic
    @Column(name = "PARENT_PAGE_ID", nullable = true)
    public Long getParentPageId() {
        return parentPageId;
    }

    public void setParentPageId(Long parentPageId) {
        this.parentPageId = parentPageId;
    }

    @Basic
    @Column(name = "AUTHOR", nullable = true, length = 200)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 550)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "OWNER", nullable = true, length = 200)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "CREATED_DATE", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "UPDATED_DATE", nullable = true)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true, length = 2147483647)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "SYNTAX", nullable = true, length = 30)
    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 550)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "EDITION_COMMENT", nullable = true, length = 1000)
    public String getEditionComment() {
        return editionComment;
    }

    public void setEditionComment(String editionComment) {
        this.editionComment = editionComment;
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
    @Column(name = "MINOR_EDIT", nullable = true)
    public Boolean getMinorEdit() {
        return minorEdit;
    }

    public void setMinorEdit(Boolean minorEdit) {
        this.minorEdit = minorEdit;
    }

    @Basic
    @Column(name = "ACTIVITY_ID", nullable = true, length = 36)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "DELETED", nullable = true)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "SPACE_ID", nullable = true)
    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    @Basic
    @Column(name = "OLD_ID", nullable = true)
    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }
}
