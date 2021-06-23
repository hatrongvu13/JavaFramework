package com.tdt.model;

public class ActivityCollection {

  public Activity[] activities;
  public int offset;
  public int limit;
  public int size;
  public Activity[] getActivities() {
    return activities;
  }
  public void setActivities(Activity[] activities) {
    this.activities = activities;
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
