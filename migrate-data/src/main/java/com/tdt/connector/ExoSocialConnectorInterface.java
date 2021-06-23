package com.tdt.connector;

import com.tdt.SpaceModel;
import com.tdt.model.*;

public interface ExoSocialConnectorInterface {

  //User
  public UserCollection getUsers() throws Exception;
  public User getUserById(String username) throws Exception;
  public String createUser(User user) throws Exception;
  public int updateUser(User user) throws Exception;
  public int deleteUser(String username) throws Exception;
  public UserCollection getUserConnections(String username) throws Exception;
  public SpaceCollection getUserSpaces(String username) throws Exception;
  public ActivityCollection getUserActivities(String username) throws Exception;
  public ActivityCollection getUserActivities(String username, int offset, String after, String before) throws Exception;
  public String postSimpleActivity(String username, String message) throws Exception;

  //UserRelationship
  public UserRelationshipCollection getUserRelationships() throws Exception;
  public UserRelationship getUserRelationshipById(String id) throws Exception;
  public UserRelationship sendConnectionRequest(String sender, String receiver) throws Exception;
  public int acceptConnectionRequest(String id) throws Exception;
  public UserRelationship createUserRelationship(UserRelationship connection) throws Exception;
  public int deleteConnection(String id) throws Exception;

  //Space
  public SpaceModel createSpace(SpaceModel spaceModel) throws Exception;

  //SpaceMembership
  public String createSpaceMembership(SpaceMembership membership) throws Exception;

  //Activity
  public Activity createUserActivity(String username, Activity activity) throws Exception;
  public String likeActivity(String activity_id) throws Exception;

  //Comment
  public String createComment(String activity_id, Comment comment) throws Exception;

}
