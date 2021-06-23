package com.tdt.model;

public class CommentCollection {

  public Comment[] comments;
  public int offset;
  public int limit;
  public int size;
  public Comment[] getComments() {
    return comments;
  }
  public void setComments(Comment[] comments) {
    this.comments = comments;
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
