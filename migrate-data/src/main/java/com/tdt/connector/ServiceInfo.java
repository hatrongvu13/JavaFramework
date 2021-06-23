package com.tdt.connector;

public class ServiceInfo {

  public static final String REST_URL = "/rest/private/v1/social";

  //User
  public static String getUsersUri() {
    return REST_URL + "/users";
  }

  public static String uploadAvatar(String username, String imageName) {
    return "http://10.1.20.186:8080/portal/rest/media/" + username + "/" + imageName;
  }

  public static String getMigrateUsersUri(String username) {
    return "http://10.1.20.186:8080/portal/rest/demo/migrate/user/" + username;
  }

  public static String getMigrateSpacesUri(String displayName) {
    return "http://10.1.20.186:8080/portal/rest/demo/migrate/space/" + displayName;
  }

  public static String getUserUri(String username) {
    return REST_URL + "/users/" + username;
  }
  public static String getUserConnectionsUri(String username) {
    return REST_URL + "/users/" + username + "/connections";
  }
  public static String getUserSpacesUri(String username) {
    return REST_URL + "/users/" + username + "/spaces";
  }
  public static String getUserActivitiesUri(String username) {
    return REST_URL + "/users/" + username + "/activities";
  }

  public static String getSpaceActivitiesUri(String id) {
    return REST_URL + "/spaces/" + id + "/activities";
  }

  //UserRelationship
  public static String getUserRelationshipsUri() {
    return REST_URL + "/usersRelationships";
  }
  public static String getUserRelationshipUri(String id) {
    return REST_URL + "/usersRelationships/" + id;
  }

  //Space
  public static String getSpacesUri() {
    return REST_URL + "/spaces";
  }

  //SpaceMembership
  public static String getSpaceMembershipsUri() {
    return REST_URL + "/spacesMemberships";
  }

  //Activity
  public static String getLikesUri(String activity_id) {
    return REST_URL + "/activities/" + activity_id + "/likes";
  }
  public static String getCommentsUri(String activity_id) {
    return REST_URL + "/activities/" + activity_id + "/comments";
  }

}
