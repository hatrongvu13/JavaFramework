package com.tdt.model;

public class SpaceMembershipCollection {

  public SpaceMembership[] spacesMemberships;
  public int offset;
  public int limit;
  public int size;
  public SpaceMembership[] getSpacesMemberships() {
    return spacesMemberships;
  }
  public void setSpacesMemberships(SpaceMembership[] spacesMemberships) {
    this.spacesMemberships = spacesMemberships;
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
