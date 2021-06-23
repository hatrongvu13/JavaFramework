package com.tdt.model;

public class Comment {

  public String id;
  public String href;
  public String identity;
  public String poster;
  public String body;
  public Mention[] mentions;

  public class Mention {
    public String id;
    public String href;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getHref() {
      return href;
    }
    public void setHref(String href) {
      this.href = href;
    }
  }

  public String createDate;
  public String updateDate;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getHref() {
    return href;
  }
  public void setHref(String href) {
    this.href = href;
  }
  public String getIdentity() {
    return identity;
  }
  public void setIdentity(String identity) {
    this.identity = identity;
  }
  public String getPoster() {
    return poster;
  }
  public void setPoster(String poster) {
    this.poster = poster;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public Mention[] getMentions() {
    return mentions;
  }
  public void setMentions(Mention[] mentions) {
    this.mentions = mentions;
  }
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  //add field title to work-around SOC-5129.
  public String title;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

}
