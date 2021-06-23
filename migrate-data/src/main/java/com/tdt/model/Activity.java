package com.tdt.model;

import java.util.List;

public class Activity {

  public String id;
  public String title;
  public String body;
  public String link;
  public String type;
  public String href;
  public String identity;
  private List<ActivityFileRestIn> files;
  public Owner owner;
  public class Owner {
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

  public String comments;
  public String likes;
  public String createDate;
  public String updateDate;

  public ActivityStream activityStream;
  public class ActivityStream {
    public String type;
    public String id;
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
  public Owner getOwner() {
    return owner;
  }
  public void setOwner(Owner owner) {
    this.owner = owner;
  }
  public Mention[] getMentions() {
    return mentions;
  }
  public void setMentions(Mention[] mentions) {
    this.mentions = mentions;
  }
  public String getComments() {
    return comments;
  }
  public void setComments(String comments) {
    this.comments = comments;
  }
  public String getLikes() {
    return likes;
  }
  public void setLikes(String likes) {
    this.likes = likes;
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
  public ActivityStream getActivityStream() {
    return activityStream;
  }
  public void setActivityStream(ActivityStream activityStream) {
    this.activityStream = activityStream;
  }

  public List<ActivityFileRestIn> getFiles() {
    return files;
  }

  public void setFiles(List<ActivityFileRestIn> files) {
    this.files = files;
  }
}
