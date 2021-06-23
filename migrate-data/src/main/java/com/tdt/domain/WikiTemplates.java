package com.tdt.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "WIKI_TEMPLATES", schema = "dbo", catalog = "exoBackUp")
public class WikiTemplates {
    private long templateId;
    private String author;
    private String name;
    private String description;
    private String content;
    private String syntax;
    private String title;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    @Id
    @Column(name = "TEMPLATE_ID", nullable = false)
    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
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
    @Column(name = "DESCRIPTION", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiTemplates that = (WikiTemplates) o;
        return templateId == that.templateId &&
                Objects.equals(author, that.author) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(content, that.content) &&
                Objects.equals(syntax, that.syntax) &&
                Objects.equals(title, that.title) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, author, name, description, content, syntax, title, createdDate, updatedDate);
    }
}
