package com.tdt.model;

public class Identity {

  public String id;
  public String href;
  public String providerId;
  public GlobalId globalId;

  public class GlobalId {
    public String localId;
    public String domain;
    public String getLocalId() {
      return localId;
    }
    public void setLocalId(String localId) {
      this.localId = localId;
    }
    public String getDomain() {
      return domain;
    }
    public void setDomain(String domain) {
      this.domain = domain;
    }
  }

  public Boolean deleted;
  public User profile;
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
  public String getProviderId() {
    return providerId;
  }
  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }
  public GlobalId getGlobalId() {
    return globalId;
  }
  public void setGlobalId(GlobalId globalId) {
    this.globalId = globalId;
  }
  public Boolean getDeleted() {
    return deleted;
  }
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
  public User getProfile() {
    return profile;
  }
  public void setProfile(User profile) {
    this.profile = profile;
  }

}
