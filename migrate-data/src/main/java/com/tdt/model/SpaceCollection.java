package com.tdt.model;

public class SpaceCollection {

  public Space[] spaces;
  public int offset;
  public int limit;
  public int size;
  public Space[] getSpaces() {
    return spaces;
  }
  public void setSpaces(Space[] spaces) {
    this.spaces = spaces;
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
