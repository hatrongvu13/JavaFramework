package com.tdt.connector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdt.SpaceModel;
import com.tdt.model.*;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URLEncoder;

public class ExoSocialConnector implements ExoSocialConnectorInterface {

  public String BASE_URL;

  public static final Gson gson = new GsonBuilder().create();

  // Constructor 1.
  public ExoSocialConnector(String base_url) {
    this.BASE_URL = base_url;
  }

  // Constructor 2.
  public ExoSocialConnector(String base_url, String username, String password) {
    this.BASE_URL = base_url;
    final String _username = username;
    final String _password = password;

    Authenticator.setDefault(new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(_username, _password.toCharArray());
      }
    });
  }

  // Constructor 3.
  public ExoSocialConnector(String base_url, Authenticator authenticator) {
    this.BASE_URL = base_url;
    Authenticator.setDefault(authenticator);
  }

  // User
  public UserCollection getUsers() throws Exception {
    String url = BASE_URL + ServiceInfo.getUsersUri() + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserCollection.class);
  }

  public User getUserById(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserUri(username);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, User.class);
  }

  public String createUser(User user) throws Exception {
    String url = BASE_URL + ServiceInfo.getUsersUri();
    String json = gson.toJson(user);
    return HttpUtils.post(json, url);
  }

  public String uploadAvatar(String username, String imageName) throws Exception {
    String url = ServiceInfo.uploadAvatar(username, imageName);
    return HttpUtils.get(url);
  }

  public String createMigrateUser(String username) throws Exception {
    String url = ServiceInfo.getMigrateUsersUri(username);
    return HttpUtils.get(url);
  }

  public int updateUser(User user) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserUri(user.getUsername());
    String json = gson.toJson(user);
    return HttpUtils.put(json, url);
  }

  public int deleteUser(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserUri(username);
    return HttpUtils.delete(url);
  }

  public UserCollection getUserConnections(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserConnectionsUri(username) + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserCollection.class);
  }

  public SpaceCollection getUserSpaces(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserSpacesUri(username) + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, SpaceCollection.class);
  }

  public ActivityCollection getUserActivities(String username) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserSpacesUri(username) + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ActivityCollection.class);
  }

  public ActivityCollection getUserActivities(String username, int offset, String after, String before) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username) + "?returnSize=true&offset=" + offset
        + "&after=" + URLEncoder.encode(after, "UTF-8") + "&before=" + URLEncoder.encode(before, "UTF-8");
    String json = HttpUtils.get(url);
    return gson.fromJson(json, ActivityCollection.class);
  }

  public String postSpaceActivity(Activity activity, String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getSpaceActivitiesUri(id);
    return (HttpUtils.post(gson.toJson(activity), url));
  }

  public String postSimpleActivity(String username, String message) throws Exception {
    Activity activity = new Activity();
    activity.setTitle(message);
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username);
    return (HttpUtils.post(gson.toJson(activity), url));
  }

  public String postSimpleActivitySpace(String username, String message) throws Exception {
    Activity activity = new Activity();
    activity.setTitle(message);
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username);
    return (HttpUtils.post(gson.toJson(activity), url));
  }

  //UserRelationship
  public UserRelationshipCollection getUserRelationships() throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipsUri() + "?returnSize=true";
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserRelationshipCollection.class);
  }

  public UserRelationship getUserRelationshipById(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipUri(id);
    String json = HttpUtils.get(url);
    return gson.fromJson(json, UserRelationship.class);
  }

  public UserRelationship sendConnectionRequest(String sender, String receiver) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipsUri();
    UserRelationship relationship = new UserRelationship();
    relationship.setSender(sender);
    relationship.setReceiver(receiver);
    relationship.setStatus("pending");
    String json = HttpUtils.post(gson.toJson(relationship), url);
    return gson.fromJson(json, UserRelationship.class);
  }

  public int acceptConnectionRequest(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipUri(id);
    UserRelationship relationship = new UserRelationship();
    relationship.setStatus("confirmed");
    return HttpUtils.put(gson.toJson(relationship), url);
  }

  public UserRelationship createUserRelationship(UserRelationship connection) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipsUri();
    String json = HttpUtils.post(gson.toJson(connection), url);
    return gson.fromJson(json, UserRelationship.class);
  }

  public int deleteConnection(String id) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserRelationshipUri(id);
    return HttpUtils.delete(url);
  }

  //Space
  public SpaceModel createSpace(SpaceModel spaceModel) throws Exception {
    String url = BASE_URL + ServiceInfo.getSpacesUri();
    String json = HttpUtils.post(gson.toJson(spaceModel), url);
    return gson.fromJson(json, SpaceModel.class);
  }

  public String createMigrateSpace(String displayName) throws Exception {
    String url = ServiceInfo.getMigrateSpacesUri(displayName);
    return HttpUtils.get(url);
  }

  public String getSpaces() throws Exception {
   String url = BASE_URL + ServiceInfo.getSpacesUri();
    String json = HttpUtils.get(url);
    return json;
  }

  //SpaceMembership
  public String createSpaceMembership(SpaceMembership membership) throws Exception {
    String url = BASE_URL + ServiceInfo.getSpaceMembershipsUri();
    return HttpUtils.post(gson.toJson(membership), url);
  }

  //Activity
  public Activity createUserActivity(String username, Activity activity) throws Exception {
    String url = BASE_URL + ServiceInfo.getUserActivitiesUri(username);
    String json = HttpUtils.post(gson.toJson(activity), url);
    return gson.fromJson(json, Activity.class);
  }

  public String likeActivity(String activity_id) throws Exception {
    String url = BASE_URL + ServiceInfo.getLikesUri(activity_id);
    return HttpUtils.post(url);
  }

  //Comment
  public String createComment(String activity_id, Comment comment) throws Exception {
    String url = BASE_URL + ServiceInfo.getCommentsUri(activity_id);
    return HttpUtils.post(gson.toJson(comment), url);
  }

  public FileUpload PostWiki(String fileName) throws Exception {
    String url = "http://10.1.20.186:8080/portal/rest/media/root/" + fileName;
    String json = HttpUtils.get(url);
    return gson.fromJson(json, com.tdt.model.FileUpload.class);
  }

}
