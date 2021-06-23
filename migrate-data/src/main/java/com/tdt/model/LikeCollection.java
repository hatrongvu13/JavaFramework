package com.tdt.model;

public class LikeCollection {

  public User[] likes;
  public int offset;
  public int limit;
  public int size;
  public User[] getLikes() {
    return likes;
  }
  public void setLikes(User[] likes) {
    this.likes = likes;
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
