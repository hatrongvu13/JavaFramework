package com.tdt.model;

public class IdentityCollection {

  public Identity[] identities;
  public int offset;
  public int limit;
  public int size;
  public Identity[] getIdentities() {
    return identities;
  }
  public void setIdentities(Identity[] identities) {
    this.identities = identities;
  }
  public int getOffset() {
    return offset;
  }
  public void setOffset(int offset) {
    this.offset = offset;
  }
  public int getLimit() {
    return limit;
  }
  public void setLimit(int limit) {
    this.limit = limit;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }

}
