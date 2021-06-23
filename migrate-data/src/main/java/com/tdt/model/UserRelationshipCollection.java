package com.tdt.model;

public class UserRelationshipCollection {

  public UserRelationship[] usersRelationships;
  public int offset;
  public int limit;
  public int size;
  public UserRelationship[] getUsersRelationships() {
    return usersRelationships;
  }
  public void setUsersRelationships(UserRelationship[] usersRelationships) {
    this.usersRelationships = usersRelationships;
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
