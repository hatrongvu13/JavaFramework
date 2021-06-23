package com.tdt.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "WIKI_WIKIS", schema = "dbo", catalog = "exoBackUp")
public class WikiWikis implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int WIKI_ID;

    @Basic
    @Column(name = "NAME", length = 550)
    private String name;

    @Basic
    @Column(name = "OWNER", nullable = false, length = 200)
    private String owner;

    @Basic
    @Column(name = "TYPE", nullable = false, length = 50)
    private String type;

    @Id
    @Column(name = "WIKI_HOME")
    private long wikiHome;

    @Id
    @Column(name = "SYNTAX")
    private String syntax;

    @Id
    @Column(name = "ALLOW_MULTI_SYNTAX")
    private Integer allowMultiSyntax;
    private long wikiId;
    private Long spaceId;

    public WikiWikis() {
    }

    public WikiWikis(int wikiId, String name, String owner, String type, long wikiHome, String syntax, Integer allowMultiSyntax) {
        this.WIKI_ID = wikiId;
        this.name = name;
        this.owner = owner;
        this.type = type;
        this.wikiHome = wikiHome;
        this.syntax = syntax;
        this.allowMultiSyntax = allowMultiSyntax;
    }

    @Id
    @Column(name = "WIKI_ID", nullable = false)
    public int getWikiId() {
        return WIKI_ID;
    }

    public void setWikiId(int wikiId) {
        this.WIKI_ID = wikiId;
    }

    public void setWikiId(long wikiId) {
        this.wikiId = wikiId;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 550)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "OWNER", nullable = false, length = 200)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "TYPE", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getWikiHome() {
        return wikiHome;
    }

    public void setWikiHome(Long wikiHome) {
        this.wikiHome = wikiHome;
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
    @Column(name = "ALLOW_MULTI_SYNTAX", nullable = true)
    public Integer getAllowMultiSyntax() {
        return allowMultiSyntax;
    }

    public void setAllowMultiSyntax(Integer allowMultiSyntax) {
        this.allowMultiSyntax = allowMultiSyntax;
    }

    @Basic
    @Column(name = "SPACE_ID", nullable = true)
    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiWikis wikiWikis = (WikiWikis) o;
        return WIKI_ID == wikiWikis.WIKI_ID &&
                Objects.equals(name, wikiWikis.name) &&
                Objects.equals(owner, wikiWikis.owner) &&
                Objects.equals(type, wikiWikis.type) &&
                Objects.equals(syntax, wikiWikis.syntax) &&
                Objects.equals(allowMultiSyntax, wikiWikis.allowMultiSyntax) &&
                Objects.equals(spaceId, wikiWikis.spaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WIKI_ID, name, owner, type, syntax, allowMultiSyntax, spaceId);
    }
}
