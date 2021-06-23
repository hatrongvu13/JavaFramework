package com.tdt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdt.connector.ExoSocialConnector;
import com.tdt.domain.*;
import com.tdt.model.Activity;
import com.tdt.model.ActivityFileRestIn;
import com.tdt.model.FileUpload;
import com.tdt.model.User;
import com.tdt.repository.ActivitiesRepository;
import com.tdt.repository.BaseRepositoryImpl;
import com.tdt.service.EntityManagerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class,
        basePackages = {"com.tdt.repository"})
public class Application implements CommandLineRunner {

    public static final Gson gson = new GsonBuilder().create();

    public static ExoSocialConnector connector;

    public static String USERNAME, PASSWORD, BASE_URL;

    private final EntityManagerService entityManagerService;

    private final ActivitiesRepository activitiesRepository;

    private final String URL_PROFILE = "https://www.phillipconnect.com/people/";
    private final String URL_PROFILE_EXO = "https://phillipconnect.ecomedic.vn/portal/intranet/profile/";

    public Application(EntityManagerService entityManagerService, ActivitiesRepository activitiesRepository) {
        this.entityManagerService = entityManagerService;
        this.activitiesRepository = activitiesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static String covertStringToURL(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D")
                .replaceAll(" ", "_");
    }

    public static String covertStringToURL1(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D")
                .replaceAll(" ", "-");
    }

    @Override
    @Transactional("slaveTransactionManager")
    public void run(String... args) throws Exception {
        login();
        EntityManager entityManager = entityManagerService.getEntityManager();
        EntityManager entityManagerSlave = entityManagerService.getSlaveEntityManager();
//        migrateUser(entityManager, entityManagerSlave);
//        updateUserAvatar(entityManager, entityManagerSlave);
//        migrateUserConnection(entityManager, entityManagerSlave);
//        updateFullName(entityManager, entityManagerSlave);


//        migrateSpace1(entityManager, entityManagerSlave);
//        updateSpace(entityManager, entityManagerSlave);
//        migrateUserSpace(entityManager, entityManagerSlave);
//        migrateWallSpace(entityManager, entityManagerSlave);
//        migrateAuthorSpace(entityManager, entityManagerSlave);
//        migrateWallComment(entityManager, entityManagerSlave);
//        migrateWallSpaceFile(entityManager, entityManagerSlave);
//        migrateWallTextFormat(entityManager, entityManagerSlave);
//        updateSpaceAvatar(entityManager, entityManagerSlave);
//        updateVideo(entityManager, entityManagerSlave);


//        migrateWiki(entityManager, entityManagerSlave);
//        updateWikiRoot(entityManager, entityManagerSlave);
//        updateWiki(entityManager, entityManagerSlave);
//        insertActivitiesWiki(entityManager, entityManagerSlave);
//        insertActivitiesWikiComment(entityManager, entityManagerSlave);
//        updateActivitiesWiki(entityManager, entityManagerSlave);
        migrateAttachmentWiki(entityManager, entityManagerSlave);
//        updateWikiTitle(entityManager, entityManagerSlave);


//        List<ActivityFileRestIn> files = new ArrayList<>();
//        ActivityFileRestIn activityFileRestIn = new ActivityFileRestIn();
//        activityFileRestIn.setStorage("jcr");
//        activityFileRestIn.setUploadId("1");
//        files.add(activityFileRestIn);
//
//        Activity activity = new Activity();
//        activity.setTitle("Hello quang");
//        activity.setType("ks-wiki:spaces");
//        activity.setBody("body");
//        activity.setFiles(files);

//        String s1 = connector.postSpaceActivity(activity, "895");
//        System.out.println("sdsdsdsdsd");
//        String s = connector.postSimpleActivity("phillip_group_it_conference", "Hello quang");
//        System.out.println("sdsdsdsdsd");

    }

    private void migrateAuthorSpace(EntityManager entityManager, EntityManager entityManagerSlave) {
        Query query = entityManager.createNativeQuery("SELECT dsu.SpaceID, dsu.UserID, du.UserName " +
                "from Dyve_Spaces_Users as dsu Inner join Dyve_User as du on du.UserID = dsu.UserID Inner join [Dyve_Spaces] as ds on ds.[SpaceID] = dsu.SpaceID where dsu.[UserID] < 6000 ");
        List<Object[]> dyVyUserSpaces = query.getResultList();
        for (int i = 0; i < dyVyUserSpaces.size(); i++) {
            try {
                Object[] userSpace = dyVyUserSpaces.get(i);
                String userId = String.valueOf(userSpace[2]).toLowerCase();
                Query queryInsertUserSpace = entityManagerSlave.createNativeQuery("INSERT INTO SOC_SPACES_MEMBERS " +
                        "(SPACE_ID, USER_ID, STATUS, LAST_ACCESS, VISITED) VALUES(" + userSpace[0] + ", '" + userId + "', 0, '1970-01-02 07:00:00', 0)");
                queryInsertUserSpace.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Migrate UserSpace Done");
    }

    private void migrateWallTextFormat(EntityManager entityManager, EntityManager entityManagerSlave) {
        Query query = entityManager
                .createNativeQuery("SELECT * from Dyve_Wall WHERE SpaceID <> 0 ORDER BY WallID ASC");
        List<Object[]> dyVyWalls = query.getResultList();

        Query queryActivitiesEXOs = entityManagerSlave
                .createNativeQuery("select * from SOC_ACTIVITIES WHERE IS_COMMENT = 0", SocActivities.class);
        List<SocActivities> objectActivitiesEXOs = queryActivitiesEXOs.getResultList();

        dyVyWalls.forEach(wallItem -> {
            try {
                Long wallId = Long.valueOf(String.valueOf(wallItem[0]));
                String wallText = String.valueOf(wallItem[12]).replaceAll(URL_PROFILE, URL_PROFILE_EXO);
                SocActivities objectActivitiesEXO = objectActivitiesEXOs.stream()
                        .filter(item -> wallId == item.getActivityId()).findFirst().orElse(null);
                if (Objects.nonNull(objectActivitiesEXO)) {

                    objectActivitiesEXO.setTitle(wallText);
                    entityManagerSlave.persist(objectActivitiesEXO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Update WallTextFormat done");
    }

    private void updateFullName(EntityManager entityManager, EntityManager entityManagerSlave) {
        Query query = entityManager.createNativeQuery("select * from Dyve_User ORDER BY UserID ASC");
        List<Object[]> dyVyUsers = query.getResultList();
        dyVyUsers.forEach(user -> {
            Long userId = Long.valueOf(String.valueOf(user[0]));
            String firstName = String.valueOf(user[20]);
            String lastName = String.valueOf(user[22]);
            String fullName = String.valueOf(user[73]);
            Query queryIdentity = entityManagerSlave
                    .createNativeQuery("UPDATE SOC_IDENTITY_PROPERTIES SET " +
                            "VALUE = N'" + fullName + "' WHERE IDENTITY_ID = " + userId + " AND [NAME] = 'fullName'");
            queryIdentity.executeUpdate();

            Query queryIdentity1 = entityManagerSlave
                    .createNativeQuery("UPDATE SOC_IDENTITY_PROPERTIES SET " +
                            "VALUE = N'" + firstName + "' WHERE IDENTITY_ID = " + userId + " AND [NAME] = 'firstName'");
            queryIdentity1.executeUpdate();

            Query queryIdentity2 = entityManagerSlave
                    .createNativeQuery("UPDATE SOC_IDENTITY_PROPERTIES SET " +
                            "VALUE = N'" + lastName + "' WHERE IDENTITY_ID = " + userId + " AND [NAME] = 'lastName'");
            queryIdentity2.executeUpdate();
        });
    }

    private void login() {
        USERNAME = "root";
        PASSWORD = "111111";
        BASE_URL = "http://10.1.20.186:8080";
        connector = new ExoSocialConnector(BASE_URL, USERNAME, PASSWORD);
    }

    private void updateVideo(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryDyveContent = entityManager
                .createNativeQuery("SELECT dc.* FROM Dyve_Content dc WHERE EntityTypeID = 7 ORDER BY dc.ContentID ");
        List<Object[]> dyveContents = queryDyveContent.getResultList();
        Query querySpaceEXOs = entityManagerSlave.createNativeQuery("select * from SOC_SPACES");
        List<Object[]> objectSpaceEXOs = querySpaceEXOs.getResultList();

        Query querySpaces = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES where PROVIDER_ID = 'space'");
        List<Object[]> spaceExos = querySpaces.getResultList();
        dyveContents.forEach(dyveContent -> {
            String contentTitle = String.valueOf(dyveContent[4]);
            String contentBody = String.valueOf(dyveContent[7]);

            String spaceId = String.valueOf(dyveContent[13]);
            Object[] objectSpaceEXO = objectSpaceEXOs.stream()
                    .filter(item -> spaceId.equals(String.valueOf(item[0]))).findFirst().orElse(null);
            if (Objects.nonNull(objectSpaceEXO)) {
                String prettyName = String.valueOf(objectSpaceEXO[1]);

                Object[] spaceIdentity = spaceExos.stream()
                        .filter(item -> prettyName.equals(String.valueOf(item[2]))).findFirst().orElse(null);

                String ownerId = String.valueOf(spaceIdentity[0]);
                String posterId = String.valueOf(dyveContent[8]);

                Timestamp dateCreated = (Timestamp) dyveContent[18];
                long dateTime = dateCreated.getTime();
                String videoString = "<div class=\"fileShare\"><div id=\"Preview{{previewId}}-0\" class=\"clearfix isPreviewable previewable10\"><div id=\"MediaContent3-0\" class=\"mediaContent PlayerContianer\" style=\"padding: 5px\"><video src=\"https://phillipconnect.ecomedic.vn/rest/media/file/{{urlVideo}}\" controls=\"controls\" class=\"videoContent\" style=\"width: 100%\">your browser does not support the video tag</video></div></div></div>";

                String youtuUrl = "<div class=\"uiBox roundedBottom introBox linkShare\">\n" +
                        "<div class=\"clearfix\">\n" +
                        "\n" +
                        "  <div class=\" text\">\n" +
                        "<a class=\"linkTitle\" target=\"_blank\" href=\"https://youtu.be/x7qPAY9JqE4\">https://youtu.be/x7qPAY9JqE4</a>\n" +
                        "\n" +
                        "<div class=\"linkSource\">Source : <a href=\"https://youtu.be/x7qPAY9JqE4\" target=\"_blank\" title=\"https://youtu.be/x7qPAY9JqE4\">https://youtu.be/x7qPAY9JqE4</a></div>\n" +
                        "\n" +
                        " <div class=\"EmbedHtml\"><iframe id=\"player\" type=\"text/html\" width=\"330\" height=\"200\" frameborder=\"0\" allowfullscreen=\"true\" src=\"http://www.youtube.com/embed/x7qPAY9JqE4?enablejsapi=1\">&nbsp;</iframe></div>\n" +
                        "\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "</div>";
                String fileName = String.valueOf(dyveContent[53]);
                String urlVideo = "";
                String id = String.valueOf(dyveContent[0]);
                if (Objects.nonNull(fileName)) {
                    if (fileName.startsWith("https://")) {
                        urlVideo = fileName;
                    } else {
                        urlVideo = "/rest/media/file/" + fileName;
                    }

                    videoString = videoString
                            .replace("{{previewId}}", id)
                            .replace("{{urlVideo}}", urlVideo);

                    SocActivities objectActivitiesEXO = new SocActivities();
                    objectActivitiesEXO.setTitle("<h4>" + contentTitle + "</h4>" + "\n" + contentBody + "\n" + videoString);
                    objectActivitiesEXO.setHidden(false);
                    objectActivitiesEXO.setOwnerId(ownerId);
                    objectActivitiesEXO.setLocked(false);
                    objectActivitiesEXO.setPosterId(posterId);
                    objectActivitiesEXO.setUpdatedDate(dateTime);
                    objectActivitiesEXO.setPosted(dateTime);
                    objectActivitiesEXO.setType("DEFAULT_ACTIVITY");
                    objectActivitiesEXO.setComment(false);
                    entityManagerSlave.persist(objectActivitiesEXO);
                }
            }

        });

//        //Step 2
//        Query querySOC_STREAM_ITEMS = entityManagerSlave.createNativeQuery("INSERT INTO SOC_STREAM_ITEMS SELECT OWNER_ID, 0 as STREAM_TYPE, ACTIVITY_ID, POSTED as UPDATED_DATE FROM SOC_ACTIVITIES WHERE ACTIVITY_ID > 96742 and OWNER_ID <> ''");
//        querySOC_STREAM_ITEMS.executeUpdate();
//        Query querySOC_STREAM_ITEMS1 = entityManagerSlave.createNativeQuery("INSERT INTO SOC_STREAM_ITEMS SELECT POSTER_ID as OWNER_ID, 0 as STREAM_TYPE, ACTIVITY_ID, POSTED as UPDATED_DATE FROM SOC_ACTIVITIES WHERE ACTIVITY_ID > 96742 and OWNER_ID <> ''");
//        querySOC_STREAM_ITEMS1.executeUpdate();
//        System.out.println("Migrate WallSpace Done");
    }

    private void updateWikiTitle(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryDyveContent = entityManager
                .createNativeQuery("SELECT dc.* FROM Dyve_Content dc " +
                        "inner join Dyve_User du on du.UserID = dc.AuthorID WHERE EntityTypeID = 9 ORDER BY dc.ContentID ");
        List<Object[]> dyveContents = queryDyveContent.getResultList();

        Query queryWikiPages = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_PAGES where [NAME] <> 'WikiHome'", WikiPages.class);
        List<WikiPages> wikiPages = queryWikiPages.getResultList();

        dyveContents.forEach(dyveContent -> {
            try {
                String wikiId = String.valueOf(dyveContent[0]);

                WikiPages wikiPage = wikiPages.stream()
                        .filter(wikiP -> wikiId.equals(String.valueOf(wikiP.getPageId()))).findFirst().orElse(null);

                String title = String.valueOf(dyveContent[4]);
                String name = String.valueOf(dyveContent[5]);
                if (Objects.nonNull(wikiPage)) {
                    String content = wikiPage.getContent();
                    wikiPage.setTitle(title);
                    wikiPage.setName(name);
                    if (Objects.nonNull(content))
                        wikiPage.setContent(content.replaceAll(URL_PROFILE, URL_PROFILE_EXO));
                    entityManagerSlave.persist(wikiPage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Update wiki done");
    }

    private void updateUrlMention(EntityManager entityManagerSlave) {
//        Query queryActivitiesEXOs = entityManagerSlave
//                .createNativeQuery("select * from SOC_ACTIVITIES WHERE IS_COMMENT = 0", SocActivities.class);
//        List<SocActivities> objectActivitiesEXOs = queryActivitiesEXOs.getResultList();
//
//        objectActivitiesEXOs.forEach(item -> {
//            String title = item.getTitle().replaceAll(URL_PROFILE, URL_PROFILE_EXO);
//            item.setTitle(title);
//            entityManagerSlave.merge(item);
//        });

        Query queryWikiPagesEXOs = entityManagerSlave
                .createNativeQuery("select * from WIKI_PAGES", WikiPages.class);
        List<WikiPages> objectWikiPagesEXOs = queryWikiPagesEXOs.getResultList();

        objectWikiPagesEXOs.forEach(item -> {
            if (Objects.nonNull(item.getContent())) {
                String content = item.getContent().replaceAll(URL_PROFILE, URL_PROFILE_EXO);
                item.setTitle(content);
                entityManagerSlave.merge(item);
            }
        });

        System.out.println("Done");
    }

    public void updateSpaceAvatar(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("select * from Dyve_Spaces ORDER BY SpaceID ASC");
        List<Object[]> dyVySpaces = query.getResultList();

//        dyVySpaces.forEach(user -> {
//            try {
//                String avatarName = String.valueOf(user[30]);
//                String spacename = String.valueOf(user[1]);
//                if (Objects.nonNull(avatarName) && !"".equals(avatarName)) {
//                    System.out.println("spacename ===>>>: " + spacename);
//                    connector.uploadAvatar("root", avatarName);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });


        Query queryFile = entityManagerSlave.createNativeQuery("select * from FILES_FILES ORDER BY FILE_ID ASC");
        List<Object[]> files = queryFile.getResultList();

        Query queryspaceEXOs = entityManagerSlave.createNativeQuery("select * from SOC_SPACES ORDER BY SPACE_ID ASC");
        List<Object[]> spaceEXOs = queryspaceEXOs.getResultList();

        Query queryIdentities = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES where PROVIDER_ID = 'space'");
        List<Object[]> userExos = queryIdentities.getResultList();

        spaceEXOs.forEach(space -> {
            try {
                String spaceId = String.valueOf(space[0]);
                String prettyName = String.valueOf(space[1]);

                Object[] dyVySpace = dyVySpaces.stream()
                        .filter(item -> spaceId.equals(String.valueOf(item[0]))).findFirst().orElse(null);

                Object[] spaceIdentity = userExos.stream()
                        .filter(item -> prettyName.equals(String.valueOf(item[2]))).findFirst().orElse(null);

                if (Objects.nonNull(spaceIdentity)) {
                    long IDENTITY_ID = Long.parseLong(String.valueOf(spaceIdentity[0]));

                    if (Objects.nonNull(dyVySpace)) {
                        String imageName = String.valueOf(dyVySpace[30]);
                        Object[] file = files.stream()
                                .filter(item -> imageName.equals(String.valueOf(item[2]))).findFirst().orElse(null);
                        if (Objects.nonNull(file)) {
                            long AVATAR_FILE_ID = Long.parseLong(String.valueOf(file[0]));

                            Query queryFileFile = entityManagerSlave
                                    .createNativeQuery("UPDATE FILES_FILES SET " +
                                            "UPDATER = '" + prettyName + "' WHERE FILE_ID = " + AVATAR_FILE_ID);
                            queryFileFile.executeUpdate();

                            Query queryUpdateBanned = entityManagerSlave
                                    .createNativeQuery("UPDATE SOC_IDENTITIES SET " +
                                            "AVATAR_FILE_ID = " + AVATAR_FILE_ID + " WHERE IDENTITY_ID = " + IDENTITY_ID);
                            queryUpdateBanned.executeUpdate();
                        }
                    }
                } else {
                    System.out.println("Null identity " + space[2]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Update space avatar done");
    }

    public void updateUserAvatar(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("SELECT * FROM [Dyve_User] where ProfilePhoto <> '' " +
                "and ProfilePhoto <> 'profile-default.jpg' ORDER BY UserID ASC ");
        List<Object[]> dyVyUsers = query.getResultList();

        dyVyUsers.forEach(user -> {
            try {
                String profilePath = String.valueOf(user[56]);
                String username = String.valueOf(user[1]);
                if (Objects.nonNull(profilePath) && !"".equals(profilePath)) {
                    System.out.println("Username ===>>>: " + username);
                    connector.uploadAvatar("root", profilePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Query queryIdentities = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES where PROVIDER_ID = 'organization'");
        List<Object[]> userExo = queryIdentities.getResultList();

        Query queryFile = entityManagerSlave.createNativeQuery("select * from FILES_FILES ORDER BY FILE_ID ASC");
        List<Object[]> files = queryFile.getResultList();

        userExo.forEach(user -> {
            try {
                String userId = String.valueOf(user[0]);
                Object[] dyVyUser = dyVyUsers.stream()
                        .filter(item -> userId.equals(String.valueOf(item[0]))).findFirst().orElse(null);
                if (Objects.nonNull(dyVyUser)) {
                    String profilePath = String.valueOf(dyVyUser[56]);
                    Object[] file = files.stream()
                            .filter(item -> profilePath.equals(String.valueOf(item[2]))).findFirst().orElse(null);
                    if (Objects.nonNull(file)) {
                        Query queryUpdateBanned = entityManagerSlave
                                .createNativeQuery("UPDATE SOC_IDENTITIES SET " +
                                        "AVATAR_FILE_ID = " + Long.parseLong(String.valueOf(file[0])) + " WHERE IDENTITY_ID = " + userId);
                        queryUpdateBanned.executeUpdate();
                        System.out.println("userId => " + userId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Migrate avatar done");
    }

    @Transactional("slaveTransactionManager")
    public List<User> migrateUser(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("select * from Dyve_User ORDER BY UserID ASC");
        List<Object[]> dyVyUsers = query.getResultList();
        int a = 3;
        for (int i = 0; i < 5487; i++) {
            try {
                Object[] userDyve = checkUserExits(String.valueOf(a), dyVyUsers);
                User user = new User();
                if (Objects.nonNull(userDyve)) {
                    String username = String.valueOf(userDyve[1]).replaceAll("[^a-zA-Z0-9\\s+]", "");

                    if (username.length() <= 4)
                        username += "phillip";
                    user.setUsername(username.toLowerCase());
                    user.setPassword("111111");
                    user.setEmail(userDyve[4] + ".exo");
                    user.setFirstname(String.valueOf(userDyve[20]));
                    user.setLastname(String.valueOf(userDyve[22]));
                    user.setFullname(String.valueOf(userDyve[73]));
                    String json = connector.createUser(user);
                    User result = gson.fromJson(json, User.class);

                    if (Objects.nonNull(result)) {
                        boolean isBanned = Boolean.valueOf(String.valueOf(userDyve[37]));
                        int deleted = 0;
                        if (isBanned) {
                            deleted = 1;
                        }
                        Query queryUpdateBanned = entityManagerSlave
                                .createNativeQuery("UPDATE SOC_IDENTITIES SET DELETED = " + deleted +
                                        " WHERE REMOTE_ID = '" + user.getUsername() + "'");
                        queryUpdateBanned.executeUpdate();
                    } else {
                        connector.createMigrateUser("falseUser" + a);
                        System.out.println("Create false user: " + username);
                    }
                } else {
                    connector.createMigrateUser("phillipConnect" + a);
                }
                a++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Migrate User Done");
        return null;
    }

    @Transactional("slaveTransactionManager")
    public void migrateUserConnection(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryUserConnection = entityManager.createNativeQuery("SELECT * FROM Dyve_User_XFriends");
        List<Object[]> userConnections = queryUserConnection.getResultList();

        Query queryUserEXOs = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES WHERE PROVIDER_ID = 'organization'");
        List<Object[]> objectUserEXOs = queryUserEXOs.getResultList();

        userConnections.forEach(user -> {
            String userId = String.valueOf(user[0]);
            String userFriendId = String.valueOf(user[1]);
            Object[] userEXO = objectUserEXOs.stream()
                    .filter(item -> userId.equals(String.valueOf(item[0]))).findFirst().orElse(null);

            Object[] userFriend = objectUserEXOs.stream()
                    .filter(item -> userFriendId.equals(String.valueOf(item[0]))).findFirst().orElse(null);

            Boolean status = (Boolean) user[3];
            Timestamp updateDate = (Timestamp) user[2];

            if (Objects.nonNull(userFriend) && Objects.nonNull(userEXO)) {
                SocConnections socConnection = new SocConnections();
                socConnection.setSenderId(Long.valueOf(userId));
                socConnection.setReceiverId(Long.valueOf(userFriendId));
                socConnection.setStatus(status);
                socConnection.setUpdatedDate(updateDate);
                entityManagerSlave.persist(socConnection);
            }
        });
        System.out.println("migrate UserConnection Done");
    }

    @Transactional("slaveTransactionManager")
    public void migrateSpace2(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("select * from Dyve_Spaces where SpaceVisibility = 1 ORDER BY SpaceId ASC");
        List<Object[]> dyVySpaces = query.getResultList();

        dyVySpaces.forEach(item -> {
            Query queryUpdate = entityManagerSlave
                    .createNativeQuery("UPDATE SOC_SPACES SET VISIBILITY = 1 WHERE SPACE_ID = " + item[0]);
            queryUpdate.executeUpdate();
        });
    }

    @Transactional("slaveTransactionManager")
    public void migrateSpace1(EntityManager entityManager, EntityManager entityManagerSlave) throws
            Exception {
        Query query = entityManager.createNativeQuery("select * from Dyve_Spaces where SpaceID > 1231 ORDER BY SpaceId ASC");
        List<Object[]> dyVySpaces = query.getResultList();

        for (int i = 1231; i < 1501; i++) {
            int id = i + 1;
            Object[] spaceDyve = checkSpaceExits(String.valueOf(id), dyVySpaces);
            if (Objects.nonNull(spaceDyve)) {
                String displayName = String.valueOf(spaceDyve[1]);
                displayName = displayName
                        .replaceAll("[^a-zA-Z0-9\\s+]", "")
                        .replaceAll("&lt;", "<")
                        .replaceAll("&gt;", ">");
                SpaceModel spaceModel = new SpaceModel();
                spaceModel.setDisplayName(displayName);
                spaceModel.setSubscription("validation");
                spaceModel.setVisibility("private");
                SpaceModel result = connector.createSpace(spaceModel);

                if (Objects.nonNull(result) && Objects.nonNull(result.getId())) {
                    System.out.println("Insert Space success.");
                } else {
                    System.out.println("Create false Space: " + displayName);
                    spaceModel.setDisplayName("failSpace" + id);
                    spaceModel.setSubscription("validation");
                    spaceModel.setVisibility("private");
                    connector.createSpace(spaceModel);
                }
            } else {
                connector.createMigrateSpace("spaceNone" + id);
            }
        }
    }

    @Transactional("slaveTransactionManager")
    public List<SpaceModel> migrateSpace(EntityManager entityManager, EntityManager entityManagerSlave) throws
            Exception {
        Query query = entityManager.createNativeQuery("select * from Dyve_Spaces where SpaceId > 355 ORDER BY SpaceId ASC");
        List<Object[]> dyVySpaces = query.getResultList();
        List<SpaceModel> spaceModelCreated = new ArrayList<>();
        List<String> spaceErrors = new ArrayList<>();
        for (int i = 355; i < 400; i++) {
            int id = i + 1;
//            ALTER TABLE [SOC_SPACES] ADD [OLD_ID] [bigint] NULL;
            Object[] spaceDyve = checkSpaceExits(String.valueOf(id), dyVySpaces);
            SpaceModel spaceModel = new SpaceModel();
            try {
                if (Objects.nonNull(spaceDyve)) {
                    String displayName = String.valueOf(spaceDyve[1]);
                    displayName = displayName
                            .replaceAll("[^a-zA-Z0-9\\s+]", "")
                            .replaceAll("&lt;", "<")
                            .replaceAll("&gt;", ">");

                    String description = String.valueOf(spaceDyve[2]);
                    description = description
                            .replaceAll("[^a-zA-Z0-9\\s+]", "")
                            .replaceAll("&lt;p&gt;", "")
                            .replaceAll("&lt;/p&gt;", "")
                            .replaceAll("</strong>", "")
                            .replaceAll("</em>", "")
                            .replaceAll("</p>", "")
                            .replaceAll("</span>", "");
                    if (description.length() > 2000)
                        description = description.substring(0, 1998);
                    spaceModel.setDisplayName(displayName);
                    spaceModel.setDescription(description);
                    spaceModel.setSubscription("validation");
                    spaceModel.setVisibility("private");
                    SpaceModel spaceModel1 = connector.createSpace(spaceModel);
                    if (Objects.nonNull(spaceModel1) && Objects.nonNull(spaceModel1.getId())) {
                        spaceModelCreated.add(spaceModel1);
                        Query queryUpdate = entityManagerSlave
                                .createNativeQuery("UPDATE SOC_SPACES SET OLD_ID = " +
                                        Integer.parseInt(String.valueOf(spaceDyve[0])) + " WHERE SPACE_ID = " + spaceModel1.getId());
                        queryUpdate.executeUpdate();
                    } else {
                        System.out.println("Create false Space: " + displayName);
                        spaceModel.setDisplayName("failSpace" + id);
                        spaceModel.setDescription("Description" + id);
                        spaceModel.setSubscription("validation");
                        spaceModel.setVisibility("private");
                        SpaceModel spaceModel2 = connector.createSpace(spaceModel);
                        spaceModelCreated.add(spaceModel2);
                        spaceErrors.add(String.valueOf(spaceDyve[0]));
                    }
                } else {
                    connector.createMigrateSpace("DisplayName" + id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Migrate Space Done");
        return spaceModelCreated;
    }

    @Transactional("slaveTransactionManager")
    public void updateSpace(EntityManager entityManager, EntityManager entityManagerSlave) {
        Query query = entityManager.createNativeQuery("select * from Dyve_Spaces ORDER BY SpaceId ASC");
        List<Object[]> dyVySpaces = query.getResultList();

        for (Object[] dyVySpace : dyVySpaces) {
            try {
                String description = String.valueOf(dyVySpace[2]).replaceAll("'", "&apos;");
                String displayName = String.valueOf(dyVySpace[1]).replaceAll("'", "&apos;");
                int VISIBILITY = Integer.parseInt(String.valueOf(dyVySpace[7]));
                if (VISIBILITY == 3) {
                    VISIBILITY = 2;
                }
                Query queryInsertUserSpace = entityManagerSlave
                        .createNativeQuery("UPDATE SOC_SPACES SET DISPLAY_NAME = '" + displayName + "', CREATED_DATE = '" + dyVySpace[4] + "', " +
                                "DESCRIPTION = N'" + description + "', VISIBILITY = " + VISIBILITY + " WHERE SPACE_ID = " + dyVySpace[0]);
                queryInsertUserSpace.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Migrate updateSpace Done");
    }

    @Transactional("slaveTransactionManager")
    public void migrateUserSpace(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("SELECT dsu.SpaceID, dsu.UserID, du.UserName " +
                "from Dyve_Spaces_Users as dsu Inner join Dyve_User as du on du.UserID = dsu.UserID Inner join [Dyve_Spaces] as ds on ds.[SpaceID] = dsu.SpaceID where dsu.[UserID] < 6000 ");
        List<Object[]> dyVyUserSpaces = query.getResultList();
        for (int i = 0; i < dyVyUserSpaces.size(); i++) {
            try {
                Object[] userSpace = dyVyUserSpaces.get(i);
                String userId = String.valueOf(userSpace[2]).toLowerCase();
                Query queryInsertUserSpace = entityManagerSlave.createNativeQuery("INSERT INTO SOC_SPACES_MEMBERS " +
                        "(SPACE_ID, USER_ID, STATUS, LAST_ACCESS, VISITED) VALUES(" + userSpace[0] + ", '" + userId + "', 0, '1970-01-02 07:00:00', 0)");
                queryInsertUserSpace.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Migrate UserSpace Done");
    }

    @Transactional("slaveTransactionManager")
    public void migrateWallSpaceFile(EntityManager entityManager, EntityManager entityManagerSlave) {
        Query queryDyve_Content = entityManager
                .createNativeQuery("SELECT * FROM Dyve_Content WHERE SpaceID > 0 and EntityTypeID = 6 and (WallID is null or WallID = 0) ORDER BY WallID ASC");
        List<Object[]> dyVyContents = queryDyve_Content.getResultList();

        Query querySpaceEXOs = entityManagerSlave.createNativeQuery("select * from SOC_SPACES");
        List<Object[]> objectSpaceEXOs = querySpaceEXOs.getResultList();

        Query querySpaces = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES where PROVIDER_ID = 'space'");
        List<Object[]> spaceExos = querySpaces.getResultList();

        for (int i = 0; i < dyVyContents.size(); i++) {
            try {
                Object[] dyveContent = dyVyContents.get(i);
                String spaceId = String.valueOf(dyveContent[13]);
                Object[] objectSpaceEXO = objectSpaceEXOs.stream()
                        .filter(item -> spaceId.equals(String.valueOf(item[0]))).findFirst().orElse(null);

                String prettyName = String.valueOf(objectSpaceEXO[1]);

                Object[] spaceIdentity = spaceExos.stream()
                        .filter(item -> prettyName.equals(String.valueOf(item[2]))).findFirst().orElse(null);

                String ownerId = String.valueOf(spaceIdentity[0]);
                String posterId = String.valueOf(dyveContent[8]);

                Timestamp dateCreated = (Timestamp) dyveContent[18];
                long dateTime = dateCreated.getTime();

                SocActivities objectActivitiesEXO = new SocActivities();
                objectActivitiesEXO.setTitle("uploaded a file " + String.valueOf(""));
                objectActivitiesEXO.setHidden(false);
                objectActivitiesEXO.setOwnerId(ownerId);
                objectActivitiesEXO.setLocked(false);
                objectActivitiesEXO.setPosterId(posterId);
                objectActivitiesEXO.setUpdatedDate(dateTime);
                objectActivitiesEXO.setPosted(dateTime);
                objectActivitiesEXO.setType("DEFAULT_ACTIVITY");
                objectActivitiesEXO.setComment(false);
                entityManagerSlave.merge(objectActivitiesEXO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Migrate WallSpaceFile Done");
    }

    @Transactional("slaveTransactionManager")
    public void migrateWallSpace(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        //Step 1
        Query query = entityManager
                .createNativeQuery("SELECT * from Dyve_Wall WHERE SpaceID <> 0 ORDER BY WallID ASC");
        List<Object[]> dyVyWalls = query.getResultList();

        Query queryDyve_Content = entityManager
                .createNativeQuery("SELECT * from Dyve_Content WHERE WallID <> 0 ORDER BY WallID ASC");
        List<Object[]> dyVyContents = queryDyve_Content.getResultList();

        Query queryActivitiesEXOs = entityManagerSlave
                .createNativeQuery("select * from SOC_ACTIVITIES WHERE IS_COMMENT = 0", SocActivities.class);
        List<SocActivities> objectActivitiesEXOs = queryActivitiesEXOs.getResultList();

        Query querySpaceEXOs = entityManagerSlave
                .createNativeQuery("select * from SOC_SPACES");
        List<Object[]> objectSpaceEXOs = querySpaceEXOs.getResultList();

        Query queryUserEXOs = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES WHERE PROVIDER_ID = 'organization'");
        List<Object[]> objectUserEXOs = queryUserEXOs.getResultList();

        Query querySpaceOwnerIDEXOs = entityManagerSlave.createNativeQuery("select * from SOC_IDENTITIES WHERE PROVIDER_ID = 'space'");
        List<Object[]> objectSpaceOwnerIDEXOs = querySpaceOwnerIDEXOs.getResultList();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < dyVyWalls.size(); i++) {
            try {

                long activitiId = Long.valueOf(dyVyWalls.get(i)[0].toString());
                int spaceId = Integer.valueOf(dyVyWalls.get(i)[5].toString());
                int userId = Integer.valueOf(dyVyWalls.get(i)[2].toString());
                SocActivities objectActivitiesEXO = objectActivitiesEXOs.stream()
                        .filter(item -> activitiId == item.getActivityId()).findFirst().orElse(null);

                if (Objects.isNull(objectActivitiesEXO)) {
                    System.out.println("Activity not found ===>:" + activitiId);
                    continue;
                }

                Object[] objectSpaceEXO = objectSpaceEXOs.stream()
                        .filter(item -> spaceId == Integer.parseInt(String.valueOf(item[0]))).findFirst().orElse(null);

                if (Objects.isNull(objectSpaceEXO)) {
                    System.out.println("Space not found ===>:" + spaceId);
                    continue;
                }

                String prettyName = String.valueOf(objectSpaceEXO[1]);
                if (prettyName.contains("DisplayName"))
                    continue;

                Object[] objectSpaceOwnerIDEXO = objectSpaceOwnerIDEXOs.stream()
                        .filter(item -> prettyName.equals(String.valueOf(item[2]))).findFirst().orElse(null);

                if (Objects.isNull(objectSpaceOwnerIDEXO))
                    continue;

                Object[] objectUserEXO = objectUserEXOs.stream()
                        .filter(item -> userId == Integer.parseInt(String.valueOf(item[0]))).findFirst().orElse(null);

                if (Objects.isNull(objectUserEXO)) {
                    System.out.println("User not found ===>:" + userId);
                    continue;
                }
                Object[] dyVyWall = dyVyWalls.get(i);

                executorService.submit(() -> {
                    String ownerId = String.valueOf(objectSpaceOwnerIDEXO[0]);
                    Timestamp dateCreated = (Timestamp) dyVyWall[4];

                    long dateTime = dateCreated.getTime();

                    StringBuilder body = new StringBuilder(objectActivitiesEXO.getTitle());

                    List<Object[]> objectdyVyContents = dyVyContents.stream()
                            .filter(item -> activitiId == Integer.parseInt(String.valueOf(item[60]))).collect(Collectors.toList());
                    if (!objectdyVyContents.isEmpty()) {
                        objectdyVyContents.forEach(item -> {
                            String fileTitle = String.valueOf(item[27]);
                            String fileName = String.valueOf(item[53]);
                            File f = new File(fileName);
                            try {
                                String mimetype = Files.probeContentType(f.toPath());
                                if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                                    System.out.println("Có data image " + fileTitle);
                                    body.append("<br></br><p class=\"text-center\">" +
                                            "<img alt=\"\" data-plugin-name=\"selectImage\" referrerpolicy=\"no-referrer\" src=\"/rest/media/image/\"" + fileName + "\">" +
                                            "</p>");
                                } else {
                                    System.out.println("Có data file " + fileTitle);
                                    body.append("<br></br><p><a href=\"/rest/media/file/" + fileName + "\" rel=\"nofollow\" target=\"_blank\">" + fileTitle + "</a></p>");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    objectActivitiesEXO.setTitle(body.toString());
                    objectActivitiesEXO.setHidden(false);
                    objectActivitiesEXO.setOwnerId(ownerId);
                    objectActivitiesEXO.setLocked(false);
                    objectActivitiesEXO.setUpdatedDate(dateTime);
                    objectActivitiesEXO.setPosted(dateTime);
                    objectActivitiesEXO.setType("DEFAULT_ACTIVITY");
                    objectActivitiesEXO.setComment(false);
                    entityManagerSlave.persist(objectActivitiesEXO);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Step 2
        Query querySOC_STREAM_ITEMS = entityManagerSlave.createNativeQuery("INSERT INTO SOC_STREAM_ITEMS SELECT OWNER_ID, 0 as STREAM_TYPE, ACTIVITY_ID, POSTED as UPDATED_DATE FROM SOC_ACTIVITIES WHERE ACTIVITY_ID < 40000 and OWNER_ID <> ''");
        querySOC_STREAM_ITEMS.executeUpdate();
        Query querySOC_STREAM_ITEMS1 = entityManagerSlave.createNativeQuery("INSERT INTO SOC_STREAM_ITEMS SELECT POSTER_ID as OWNER_ID, 0 as STREAM_TYPE, ACTIVITY_ID, POSTED as UPDATED_DATE FROM SOC_ACTIVITIES WHERE ACTIVITY_ID < 40000 and OWNER_ID <> ''");
        querySOC_STREAM_ITEMS1.executeUpdate();
        System.out.println("Migrate WallSpace Done");
    }

    private void migrateWallComment(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("SELECT WallID as PARENT_ID, 0 as \"HIDDEN\", " +
                "(CAST(DATEDIFF(second, '1970-01-01', CAST(DatePosted AS datetime)) AS bigint)*1000) as UPDATED_DATE, 0 as LOCKED,\n" +
                "DatePosted as POSTED, FromUserID as POSTER_ID, CommentTextFormatted as TITLE, 'exosocial:spaces' as [TYPE],\n" +
                "1 as IS_COMMENT from Dyve_Wall_Comment ORDER BY WallID ASC");
        List<Object[]> dyVyWallComments = query.getResultList();

        Query queryActivity = entityManagerSlave.createNativeQuery("SELECT * from SOC_ACTIVITIES WHERE IS_COMMENT = 0 ORDER BY ACTIVITY_ID ASC");
        List<Object[]> activities = queryActivity.getResultList();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < dyVyWallComments.size(); i++) {
            try {
                Object[] dyVyWallComment = dyVyWallComments.get(i);
                long dyVyWallCommentId = Long.parseLong(String.valueOf(dyVyWallComment[0]));
                Object[] dyveActivity = activities.stream()
                        .filter(ac -> dyVyWallCommentId == Long.parseLong(String.valueOf(ac[17]))).findFirst().orElse(null);

                if (Objects.isNull(dyveActivity))
                    continue;
                executorService.submit(() -> {
                    Timestamp dateTimePosted = (Timestamp) dyVyWallComment[4];
                    SocActivities socActivities = new SocActivities();
                    socActivities.setHidden(false);
                    socActivities.setUpdatedDate(dateTimePosted.getTime());
                    socActivities.setLocked(false);
                    socActivities.setPosted(dateTimePosted.getTime());
                    socActivities.setPosterId(String.valueOf(dyVyWallComment[3]));
                    socActivities.setTitle(String.valueOf(dyVyWallComment[2]));
                    socActivities.setType("exosocial:spaces");
                    socActivities.setComment(true);
                    socActivities.setParentId(Long.parseLong(String.valueOf(dyveActivity[0])));
                    entityManagerSlave.merge(socActivities);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserfalse(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query query = entityManager.createNativeQuery("SELECT * FROM [Dyve_User] ORDER BY UserID ASC ");
        List<Object[]> dyVyUsers = query.getResultList();

        Query queryIdentityOwner = entityManagerSlave.createNativeQuery("SELECT * from SOC_IDENTITIES where REMOTE_ID like '%falseUser%'");
        List<Object[]> identityOwners = queryIdentityOwner.getResultList();

        identityOwners.forEach(user -> {
            try {
                String userId = String.valueOf(user[0]);
                Object[] dyVyUser = dyVyUsers.stream()
                        .filter(item -> userId.equals(String.valueOf(item[0]))).findFirst().orElse(null);

                if (Objects.nonNull(dyVyUser)) {
                    Query queryUpdateIdentity = entityManagerSlave
                            .createNativeQuery("UPDATE SOC_IDENTITIES SET REMOTE_ID = '" + dyVyUser[1] + "' WHERE IDENTITY_ID = " + Long.valueOf(userId));
                    queryUpdateIdentity.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void updateActivitiesWiki(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryActivitiesEXOs = entityManagerSlave
                .createNativeQuery("select * from SOC_ACTIVITIES WHERE ACTIVITY_ID > 109951", SocActivities.class);
        List<SocActivities> objectActivitiesEXOs = queryActivitiesEXOs.getResultList();

        Query queryidentitys = entityManagerSlave
                .createNativeQuery("select IDENTITY_ID, REMOTE_ID from SOC_IDENTITIES WHERE PROVIDER_ID = 'organization'");
        List<Object[]> identities = queryidentitys.getResultList();

        objectActivitiesEXOs.forEach(item -> {
            try {
                String posterId = item.getPosterId().replaceAll("[^a-zA-Z0-9\\s+]", "").toLowerCase();
                List<Object[]> identity = identities.stream()
                        .filter(user -> posterId.contains(String.valueOf(user[1]))).collect(Collectors.toList());


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Update ActivitiesWiki done.");
    }

    private void insertActivitiesWikiComment(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryActivity = entityManagerSlave.createNativeQuery("SELECT * from WIKI_PAGES WHERE NAME <> 'WikiHome'", WikiPages.class);
        List<WikiPages> wikiPages = queryActivity.getResultList();

        Query queryWikiComment = entityManager.createNativeQuery("SELECT * from Dyve_Entity_Comment where ParentEntityTypeID = 9");
        List<Object[]> wikiComments = queryWikiComment.getResultList();

        wikiComments.forEach(comment -> {
            long parentCommentId = Long.valueOf(String.valueOf(comment[8]));
            if (parentCommentId == 0) {
                Timestamp createdDate = (Timestamp) comment[12];
                String userId = String.valueOf(comment[9]);
                String commentText = String.valueOf(comment[1]);
                Long commentId = Long.valueOf(String.valueOf(comment[0]));


                try {
                    long wikiId = Long.valueOf(String.valueOf(comment[5]));
                    WikiPages wiki = wikiPages.stream()
                            .filter(wi -> wikiId == wi.getPageId()).findFirst().orElse(null);
                    if (Objects.nonNull(wiki)) {
                        SocActivities socActivities = new SocActivities();
                        socActivities.setHidden(false);
                        socActivities.setUpdatedDate(createdDate.getTime());
                        socActivities.setLocked(false);
                        socActivities.setPosted(createdDate.getTime());
                        socActivities.setTitle(commentText);
                        socActivities.setType("exosocial:spaces");
                        socActivities.setComment(true);
                        socActivities.setPosterId(userId);
                        socActivities.setParentId(Long.valueOf(wiki.getActivityId()));
                        socActivities.setOldCommentId(commentId);

                        socActivities = entityManagerSlave.merge(socActivities);

                        List<Object[]> commentChildList = wikiComments.stream()
                                .filter(wi -> commentId == Long.valueOf(String.valueOf(wi[8]))).collect(Collectors.toList());

                        if (!commentChildList.isEmpty())
                            System.out.println("have comment child");

                        if (!commentChildList.isEmpty()) {
                            for (int i = 0; i < commentChildList.size(); i++) {
                                Object[] commentChild = commentChildList.get(i);
                                Long commentIdChild = Long.valueOf(String.valueOf(commentChild[0]));
                                Timestamp createdDateChild = (Timestamp) comment[12];
                                String userIdChild = String.valueOf(comment[9]);
                                String commentTextChild = String.valueOf(comment[1]);

                                SocActivities socActivitieCommentChild = new SocActivities();
                                socActivitieCommentChild.setHidden(false);
                                socActivitieCommentChild.setUpdatedDate(createdDateChild.getTime());
                                socActivitieCommentChild.setLocked(false);
                                socActivitieCommentChild.setPosted(createdDateChild.getTime());
                                socActivitieCommentChild.setTitle(commentTextChild);
                                socActivitieCommentChild.setType("exosocial:spaces");
                                socActivitieCommentChild.setComment(true);
                                socActivitieCommentChild.setPosterId(userIdChild);
                                socActivitieCommentChild.setParentId(socActivities.getActivityId());
                                socActivitieCommentChild.setOldCommentId(commentIdChild);
                                entityManagerSlave.merge(socActivitieCommentChild);
                            }
                        }
                    } else {
                        System.out.println("Null wiki ===> " + wikiId);
                    }
                } catch (Exception e) {
                    System.out.println("Error insert comment id ===>>> " + comment[0]);
                    e.printStackTrace();
                }
            }
        });
    }

    private void insertActivitiesWiki(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryActivity = entityManagerSlave.createNativeQuery("SELECT * from WIKI_PAGES WHERE NAME <> 'WikiHome' and SPACE_ID > 0", WikiPages.class);
        List<WikiPages> wikiPages = queryActivity.getResultList();

        Query querySocSpaces = entityManagerSlave.createNativeQuery("SELECT * from SOC_SPACES", SocSpaces.class);
        List<SocSpaces> socSpaces = querySocSpaces.getResultList();

        Query queryidentitys = entityManagerSlave
                .createNativeQuery("select IDENTITY_ID, REMOTE_ID from SOC_IDENTITIES WHERE PROVIDER_ID = 'organization'");
        List<Object[]> identities = queryidentitys.getResultList();
        wikiPages.forEach(item -> {
            try {
                Long spaceId = item.getSpaceId();

                if (Objects.nonNull(spaceId)) {
                    String authorId = item.getAuthor().replaceAll("[^a-zA-Z0-9\\s+]", "").toLowerCase();
                    List<Object[]> identity = identities.stream()
                            .filter(user -> authorId.contains(String.valueOf(user[1]))).collect(Collectors.toList());
                    String posterId = "0";
                    if (!identity.isEmpty()) {
                        Object[] user;
                        if (identity.size() > 1) {
                            user = identity.stream()
                                    .filter(u -> authorId.equals(String.valueOf(u[1]))).findFirst().orElse(null);
                        } else {
                            user = identity.get(0);
                        }
                        posterId = String.valueOf(user[0]);
                    }

                    SocSpaces socSpace = socSpaces.stream()
                            .filter(space -> item.getSpaceId() == space.getSpaceId()).findFirst().orElse(null);

                    Query queryIdentityOwner = entityManagerSlave.createNativeQuery("SELECT * from SOC_IDENTITIES where REMOTE_ID = '" + socSpace.getPrettyName() + "'");
                    List<Object[]> identityOwners = queryIdentityOwner.getResultList();
                    Object[] identityOwner = identityOwners.get(0);

                    SocActivities socActivities = new SocActivities();
                    socActivities.setProviderId("space");
                    socActivities.setHidden(false);
                    socActivities.setUpdatedDate(item.getUpdatedDate().getTime());
                    socActivities.setLocked(false);
                    socActivities.setPosted(item.getCreatedDate().getTime());
                    socActivities.setTitle("<p><a href=\"https://phillipconnect.ecomedic.vn" + item.getUrl() + "\" " +
                            "rel=\"nofollow\" target=\"_blank\">" + item.getTitle() + "</a></p>");
                    socActivities.setType("DEFAULT_ACTIVITY");
                    socActivities.setComment(false);
                    socActivities.setPosterId(posterId);
                    if (Objects.nonNull(identityOwner)) {
                        socActivities.setOwnerId(String.valueOf(identityOwner[0]));
                    }
                    socActivities = entityManagerSlave.merge(socActivities);
                    System.out.println("Create activities wiki");
                    Query queryUpdateWikiPage = entityManagerSlave
                            .createNativeQuery("UPDATE WIKI_PAGES SET [ACTIVITY_ID] = '" + socActivities.getActivityId() + "' WHERE PAGE_ID = " + item.getPageId());
                    queryUpdateWikiPage.executeUpdate();
                    entityManagerSlave.flush();
                }
            } catch (Exception e) {
                System.out.println("Error space id = " + item.getSpaceId() + "- wiki id = " + item.getWikiId());
            }
        });
        System.out.println("Migrate ActivitiesWiki done.");
    }

    private void updateWiki(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryDyveContent = entityManager
                .createNativeQuery("SELECT dc.* FROM Dyve_Content dc " +
                        "inner join Dyve_User du on du.UserID = dc.AuthorID WHERE EntityTypeID = 9 AND ParentContentID <> 0 AND SpaceID <> 0 ORDER BY dc.ContentID");
        List<Object[]> dyveContents = queryDyveContent.getResultList();

        Query queryWikiPages = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_PAGES where [NAME] <> 'WikiHome'", WikiPages.class);
        List<WikiPages> wikiPages = queryWikiPages.getResultList();

        Query querySpaceEXOs = entityManagerSlave
                .createNativeQuery("select * from SOC_SPACES", SocSpaces.class);
        List<SocSpaces> objectSpaceEXOs = querySpaceEXOs.getResultList();

        dyveContents.forEach(dyveContent -> {
            try {
                String wikiId = String.valueOf(dyveContent[0]);
                String spaceId = String.valueOf(dyveContent[13]);

                WikiPages wikiPage = wikiPages.stream()
                        .filter(wikiP -> wikiId.equals(String.valueOf(wikiP.getPageId()))).findFirst().orElse(null);

                SocSpaces socSpaces = objectSpaceEXOs.stream()
                        .filter(wikiP -> spaceId.equals(String.valueOf(wikiP.getSpaceId()))).findFirst().orElse(null);

                String parentContentID = String.valueOf(dyveContent[11]);

                WikiPages wikiPageParent = wikiPages.stream()
                        .filter(wikiP -> parentContentID.equals(String.valueOf(wikiP.getPageId()))).findFirst().orElse(null);
                if (Objects.nonNull(wikiPage) && Objects.nonNull(socSpaces) && Objects.nonNull(wikiPageParent)) {
                    String content = "{{html wiki=\"true\"}}%s{{/html}}";
                    String contentHtml = String.valueOf(dyveContent[7]);
                    content = String.format(content, contentHtml);
                    wikiPage.setContent(content);
                    wikiPage.setName(covertStringToURL(wikiPage.getTitle()));
                    wikiPage.setUrl("/portal/g/:spaces:" + socSpaces.getPrettyName() + "/" + socSpaces.getPrettyName() + "/wiki/" + covertStringToURL(wikiPage.getTitle()));
                    wikiPage.setParentPageId(Long.valueOf(parentContentID));
                    entityManagerSlave.merge(wikiPage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Update wiki done");
    }

    @Transactional("slaveTransactionManager")
    public void migrateAttachmentWiki(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryDyveAttachment = entityManager.createNativeQuery("SELECT * FROM Dyve_Entity_Attachments  where EntityType = 9  AND EntityID > 0;");
        List<Object[]> dyveAttachments = queryDyveAttachment.getResultList();
        for (int i = 0; i < dyveAttachments.size(); i++)
            try {
                Object[] dyveAttachment = dyveAttachments.get(i);
                String caption = String.valueOf(dyveAttachment[5]);
                String fileName = String.valueOf(dyveAttachment[12]);
                Long wikiId = Long.valueOf(String.valueOf(dyveAttachment[1]));

                FileUpload fileItemId = connector.PostWiki(fileName);

                Query query = entityManagerSlave.createNativeQuery("INSERT INTO WIKI_PAGE_ATTACHMENTS " +
                        "(WIKI_PAGE_ID, CREATED_DATE, FULL_TITLE, ATTACHMENT_FILE_ID) " +
                        "VALUES(" + wikiId + ", CURRENT_TIMESTAMP, '" + caption + "', " + fileItemId.getId() + ")");
                query.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void updateWikiRoot(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryDyveContent = entityManager
                .createNativeQuery("SELECT dc.* FROM Dyve_Content dc " +
                        "inner join Dyve_User du on du.UserID = dc.AuthorID WHERE EntityTypeID = 9 AND ParentContentID = 0 AND SpaceID <> 0 ORDER BY dc.ContentID");
        List<Object[]> dyveContents = queryDyveContent.getResultList();

        Query queryWikiPages = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_PAGES where [NAME] <> 'WikiHome'", WikiPages.class);
        List<WikiPages> wikiPages = queryWikiPages.getResultList();

        Query queryWikiPageRoots = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_PAGES where [NAME] = 'WikiHome'", WikiPages.class);
        List<WikiPages> wikiPageRoots = queryWikiPageRoots.getResultList();

        Query querySpaceEXOs = entityManagerSlave
                .createNativeQuery("select * from SOC_SPACES", SocSpaces.class);
        List<SocSpaces> objectSpaceEXOs = querySpaceEXOs.getResultList();

        dyveContents.forEach(dyveContent -> {
            try {
                String wikiId = String.valueOf(dyveContent[0]);
                String spaceId = String.valueOf(dyveContent[13]);

                WikiPages wikiPage = wikiPages.stream()
                        .filter(wikiP -> wikiId.equals(String.valueOf(wikiP.getPageId()))).findFirst().orElse(null);

                WikiPages wikiPageRoot = wikiPageRoots.stream()
                        .filter(wikiP -> spaceId.equals(String.valueOf(wikiP.getSpaceId()))).findFirst().orElse(null);
                if (Objects.isNull(wikiPageRoot)) {
                    System.out.println("Null root wiki page for space " + spaceId);
                }

                SocSpaces socSpaces = objectSpaceEXOs.stream()
                        .filter(wikiP -> spaceId.equals(String.valueOf(wikiP.getSpaceId()))).findFirst().orElse(null);

                if (Objects.nonNull(wikiPage) && Objects.nonNull(wikiPageRoot) && Objects.nonNull(socSpaces)) {
                    String content = "{{html wiki=\"true\"}}%s{{/html}}";
                    String contentHtml = String.valueOf(dyveContent[7]);
                    content = String.format(content, contentHtml).replaceAll(URL_PROFILE, URL_PROFILE_EXO);
                    ;
                    wikiPage.setContent(content);
                    wikiPage.setName(covertStringToURL(wikiPage.getTitle()));
                    wikiPage.setUrl("/portal/g/:spaces:" + socSpaces.getPrettyName() + "/" + socSpaces.getPrettyName() + "/wiki/" + covertStringToURL(wikiPage.getTitle()));
                    wikiPage.setParentPageId(wikiPageRoot.getPageId());
                    entityManagerSlave.merge(wikiPage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("Update wiki done");
    }

    private void migrateWiki(EntityManager entityManager, EntityManager entityManagerSlave) throws Exception {
        Query queryExoSpaces = entityManagerSlave.createNativeQuery("SELECT * FROM SOC_SPACES");
        List<Object[]> exoSpaces = queryExoSpaces.getResultList();
//        createWikiWikis(exoSpaces, entityManagerSlave);

        Query queryWikiWikis = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_WIKIS");
        List<Object[]> wikiWikis = queryWikiWikis.getResultList();
//        createWikiPages(wikiWikis, entityManagerSlave);

        Query queryWikiPages = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_PAGES");
        List<Object[]> wikiPages = queryWikiPages.getResultList();

//        updateWikiWikis(wikiPages, entityManagerSlave);
//        insertWikiTemplate(wikiWikis, entityManagerSlave);

//        Query queryDyveContent = entityManager
//                .createNativeQuery("SELECT dc.* FROM Dyve_Content dc " +
//                        "inner join Dyve_User du on du.UserID = dc.AuthorID " +
//                        "WHERE dc.EntityTypeID = 9  and dc.SpaceID > 0 ORDER BY dc.ContentID ");
//        List<Object[]> dyveContents = queryDyveContent.getResultList();
//
//        exoSpaces.forEach(item -> {
//            String spaceId = String.valueOf(item[0]);
//            List<Object[]> dyveContentsByIdSpace = dyveContents.stream()
//                    .filter(dy -> spaceId.equals(String.valueOf(dy[13]))).collect(Collectors.toList());
//
//
//            dyveContentsByIdSpace.forEach(dyve -> {
//                try {
//                    String groupId = String.valueOf(item[8]);
//                    Object[] wikiWiki = wikiWikis.stream()
//                            .filter(wiki -> groupId.equals(String.valueOf(wiki[2]))).findFirst().orElse(null);
//                    String wikiId = String.valueOf(wikiWiki[0]);
//                    Object[] wikiPage = wikiPages.stream()
//                            .filter(page -> wikiId.equals(String.valueOf(page[1])) && String.valueOf(page[4]).equals("WikiHome")).findFirst().orElse(null);
////                    Query queryAuthor = entityManagerSlave.createNativeQuery("SELECT * FROM SOC_IDENTITIES WHERE IDENTITY_ID = " + Integer.parseInt(String.valueOf(dyve[8])));
////                    Object[] author = (Object[]) queryAuthor.getSingleResult();
//                    String auth = "root";
//                    Timestamp createdDate = (Timestamp) dyve[18];
//                    Timestamp updatedDate = (Timestamp) dyve[19];
//                    String content = String.valueOf(dyve[7])
//                            .replaceAll("'", "")
//                            .replaceAll("\"", "")
//                            .replaceAll("<div>", "")
//                            .replaceAll("</div>", "")
//                            .replaceAll("<p>", "")
//                            .replaceAll("</p>", "")
//                            .replaceAll("<span>", "")
//                            .replaceAll("</span>", "");
//                    Query queryInsertWikiHomePage = entityManagerSlave
//                            .createNativeQuery("INSERT INTO WIKI_PAGES (WIKI_ID,PARENT_PAGE_ID,AUTHOR,[NAME],OWNER,CREATED_DATE,UPDATED_DATE,CONTENT,SYNTAX,TITLE,EDITION_COMMENT,URL,MINOR_EDIT,ACTIVITY_ID,DELETED,SPACE_ID,OLD_ID) VALUES " +
//                                    " (" + wikiId + "," + wikiPage[0] + ",'" + auth + "','" + dyve[5] + "','" + auth + "','" + createdDate + "','" + updatedDate + "',N'" + content + "','xwiki/2.0',N'" + dyve[4] + "',NULL,NULL,NULL,0,NULL,0, " + item[0] + ");");
//                    queryInsertWikiHomePage.executeUpdate();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        });

        System.out.println("Migrate Wiki Done");
    }

    @Transactional("slaveTransactionManager")
    public void insertWikiTemplate(List<Object[]> wikiWikis, EntityManager entityManagerSlave) {
        wikiWikis.forEach(item -> {
            try {
                Query queryInsertWikiTemplate = entityManagerSlave.createNativeQuery(
                        "INSERT INTO WIKI_TEMPLATES (WIKI_ID,AUTHOR,NAME,DESCRIPTION,CONTENT,SYNTAX,TITLE,CREATED_DATE,UPDATED_DATE) VALUES\n" +
                                " (" + item[0] + ",NULL,'HOW-TO_Guide','Global','{{tip}}[[Get some tips>>http://conflatulence.blogspot.com/2010/03/how-to-guide-recipe.html]] on using this template{{/tip}}\n" +
                                "\n" +
                                "{{section justify=\"true\"}}\n" +
                                "{{column}}\n" +
                                "{{panel  title=\"Table of Contents\"}}\n" +
                                "{{toc}}{{/toc}}\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "\n" +
                                "{{column}}\n" +
                                "{{panel  title=\"Purpose\"}}\n" +
                                "{{excerpt}}__This section should provide the overall purpose of the HOW-TO Guide. (e.g. intended audience, lesson)__{{/excerpt}}\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "{{/section}}\n" +
                                "\n" +
                                "=== Requirements ===\n" +
                                "\n" +
                                "* __This short section should outline the requirements of the reader to use the guide.__\n" +
                                "\n" +
                                "=== Instructions ===\n" +
                                "\n" +
                                "1. __This section provides step-by-step instructions for the reader to follow to perform the particular act described in the HOW-TO.__\n" +
                                "\n" +
                                "=== Tips & Warnings ===\n" +
                                "\n" +
                                "* __List any particular common difficulties that the reader may come across__\n" +
                                "\n" +
                                "=== Related ===\n" +
                                "\n" +
                                "* __Link to any related HOW-TOs or External Links__\n" +
                                "* __One way to improve this template is by replacing \"Related\" with either the__ __[[related-labels>>http://confluence.atlassian.com/x/mDgC]]__ __or__ __[[contentbylabel>>http://confluence.atlassian.com/x/njgC]]__ __macro__',NULL,'HOW-TO Guide','2021-01-06 15:00:46.840','2021-01-06 15:00:46.840'),\n" +
                                " (" + item[0] + ",NULL,'Three-Column_Layout','Global','{{tip}}\n" +
                                "* This page has three columns each of an equal width\n" +
                                "* You can change the width of each column by placing you mouse cursor inside the column macro and clicking on _Insert/Edit Macro_ in icon in the toolbar\n" +
                                "{{/tip}}\n" +
                                "\n" +
                                "\n" +
                                "{{section justify=\"true\"}}\n" +
                                "{{column}}\n" +
                                "{{panel title=\"Replace This Text\"}}\n" +
                                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "\n" +
                                "\n" +
                                "{{column}}\n" +
                                "{{panel  title=\"And This Text\"}}\n" +
                                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "\n" +
                                "{{column}}\n" +
                                "{{panel title=\"And This Text Too\"}}\n" +
                                "\n" +
                                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "{{/section}}',NULL,'Three-Column Layout','2021-01-06 15:00:46.850','2021-01-06 15:00:46.850'),\n" +
                                " (" + item[0] + ",NULL,'Status_Meeting','Global','== Team A ==\n" +
                                "\n" +
                                "\n" +
                                "|= Work Streams in Progress  |= Status (R/Y/G)  |= Actions This Week  |= Risks/Issues & Mitigation Plans  |= Dependencies on Other Projects  |= Dogfood Targets (Milestone, System, Date) |= Original Target Release Date  |= New Expected Release Date  |=\\\\\n" +
                                "| Work stream 1 | Install the [[Project Status Lozenge user macro>>http://confluence.atlassian.com/x/FYedD]] to display a visualisation of a project''s status. | Work on the transmogrifier proceeding well  | Might not make it in time  | | | | |\\\\\n" +
                                "| Work stream 2  |  | | | | | | |\\\\\n" +
                                "\n" +
                                "\\\\\n" +
                                "\n" +
                                "== Team B ==\n" +
                                "\n" +
                                "|= Work Streams in Progress  |= Status (R/Y/G)  |= Actions This Week  |= Risks/Issues & Mitigation Plans  |= Dependencies on Other Projects  |= Dogfood Targets (Milestone, System, Date) |= Original Target Release Date  |= New Expected Release Date  |=\\\\\n" +
                                "| | | | | | | | |\\\\\n" +
                                "| | | | | | | | |\\\\\n" +
                                "\n" +
                                "\\\\\n" +
                                "\n" +
                                "== Team C ==\n" +
                                "\n" +
                                "|= Work Streams in Progress  |= Status (R/Y/G)  |= Actions This Week  |= Risks/Issues & Mitigation Plans  |= Dependencies on Other Projects  |= Dogfood Targets (Milestone, System, Date) |= Original Target Release Date  |= New Expected Release Date  |=\\\\\n" +
                                "| | | | | | | | |\\\\\n" +
                                "| | | | | | | | |\\\\',NULL,'Status Meeting','2021-01-06 15:00:46.857','2021-01-06 15:00:46.857'),\n" +
                                " (" + item[0] + ",NULL,'Leave_Planning','Global','{{tip}}\n" +
                                "The Confluence team uses tables to communicate scheduled leave times, an imperative part of resource planning and project management.\n" +
                                "* **[[Edit>>Using the menus]]** **this page and add your vacation plans to the table.** Try using wiki markup to do it.\n" +
                                "* The form on the right of this page was created using the [[Wufoo HTML Form Builder>>http://wufoo.com/]]. Try filling out the form or [[learn how>>http://blogs.atlassian.com/news/2009/05/confluence_wufo.html]] to create a one for yourself.\n" +
                                "{{/tip}}\n" +
                                "\n" +
                                "\\\\\n" +
                                "\n" +
                                "{{info}}\n" +
                                "This page summarises leave of Confluence team members. It is not a replacement for the normal leave process. To get leave approved, you must first complete the form on the right of this page.\n" +
                                "{{/info}}\n" +
                                "\n" +
                                "{{section justify=\"true\"}}\n" +
                                "{{column}}\n" +
                                "| (/) | Terry Johnson | 14/9 - 24/9 (Holiday), 4/10 - 12/10 (Personal) |\n" +
                                "| (/) | Ben Wilson | 28/8 - 17/9 |\n" +
                                "| (/) | Evan Booth | 8-15/10 |\n" +
                                "| (/) | Rudy Snow | 26/10 - 2/11 (annual leave) |\n" +
                                "| (/) | Mark Giles | 5/11 - 16/11 |\n" +
                                "| (/) | Judd Nelson | 23/11 - 11/12 |\n" +
                                "| (/) | Meredith Romano | 6/12 - 26/12 |\n" +
                                "| (/) | Patrick Norton | 18/12 - 4/1. Working remotely for two days somewhere in between, and check my mail daily. |\n" +
                                "| (/) | Arnold Kasper | 24/12 - 2/1 |\n" +
                                "| (/) | Ryan Forman | 24/12, 31/12 |\n" +
                                "| (/) | Michelle Longley | 27,28,31 DEC |\n" +
                                "| (/) | Donna Willis | 31/12 - 2/1 |\n" +
                                "| (/) | Ben Wilson | 4/1+7/1, 17+18+21/1 |\n" +
                                "| (/) | Evan Booth | 25/1 |\n" +
                                "| (/) | Michelle Longley | 29/1 - 1/2 |\n" +
                                "| (/) | Terry Johnson | 1,4,5/2 |\n" +
                                "| (/) | Ryan Forman | 15+18/2 |\n" +
                                "| (/) | Patrick Norton | 20/3 |\n" +
                                "| (/) | Daniel Kim | Thu 27th March to Wed 16th April |\n" +
                                "| (/) | Donna Willis | 11/4/8 - 14/4/8 (very long weekend) |\n" +
                                "| (/) | Meredith Romano | 29/4 |\n" +
                                "| (/) | Patrick Norton | 2/5 |\n" +
                                "| (/) | Ben Wilson | 22/5/08 |\n" +
                                "| (/) | Michelle Longley | 28/5/08 |\n" +
                                "| (/) | Chaz Gilbert | 2/7 - 23/7 |\n" +
                                "| (/) | Patrick Norton | 6/6 & 10/6 |\n" +
                                "| (y) | Patrick Norton | 28 July |\n" +
                                "| (?) | Arnold Kasper | up to three weeks in June/July |\n" +
                                "| (y) | Michelle Longley | 4-8 August |\n" +
                                "| (y) | Terry Johnson | 3-15 September |\n" +
                                "| (?) | Niles Tandem | 11-17 September |\n" +
                                "| (y) | Tom Zebrowski | 15 September \\-> 03 October 2008 |\n" +
                                "| (?) | Ben Wilson | 6 Oct - 20 Oct |\n" +
                                "| (y) | Scott Sampson | 15 August 2008 |\n" +
                                "| (y) | Chaz Gilbert | 3 November 2008 - 2 December 2008 |\n" +
                                "| (?) | Nolan Matthews | 9 February - 20 February 2009 |\n" +
                                "{{/column}}\n" +
                                "{{/section}}',NULL,'Leave Planning','2021-01-06 15:00:46.863','2021-01-06 15:00:46.863'),\n" +
                                " (" + item[0] + ",NULL,'Two-Column_Layout','Global','{{tip}}\n" +
                                "* This page has two columns each of an equal width\n" +
                                "* You can change the width of each column by placing you mouse cursor inside the column macro and clicking on _Insert/Edit Macro_ in icon in the toolbar\n" +
                                "{{/tip}}\n" +
                                "\n" +
                                "\\\\\n" +
                                "\n" +
                                "{{section justify=\"true\"}}\n" +
                                "{{column}}\n" +
                                "{{panel  title=\"Replace This Text\"}}\n" +
                                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "\n" +
                                "{{column}}\n" +
                                "{{panel  title=\"Replace This Text\"}}\n" +
                                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                                "{{/panel}}\n" +
                                "{{/column}}\n" +
                                "{{/section}}',NULL,'Two-Column Layout','2021-01-06 15:00:46.870','2021-01-06 15:00:46.870')");
                queryInsertWikiTemplate.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional("slaveTransactionManager")
    public void updateWikiWikis(List<Object[]> wikiPages, EntityManager entityManagerSlave) {
        wikiPages.forEach(item -> {
            try {
                Query queryInsertWikiWki = entityManagerSlave.createNativeQuery("UPDATE WIKI_WIKIS" +
                        " SET WIKI_HOME = " + item[0] +
                        " WHERE WIKI_ID = " + item[1]);
                queryInsertWikiWki.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional("slaveTransactionManager")
    public List<String> createWikiWikis(List<Object[]> exoSpaces, EntityManager entityManagerSlave) {
        List<String> owners = new ArrayList<>();
        // ALTER TABLE WIKI_WIKIS ADD SPACE_ID bigint NULL GO
        exoSpaces.forEach(item -> {
            try {
                String owner = String.valueOf(item[8]);

                Query queryCheckWikiWki = entityManagerSlave.createNativeQuery("SELECT * FROM WIKI_WIKIS WHERE OWNER = '" + owner + "'");
                List<Object[]> objectWikis = queryCheckWikiWki.getResultList();

                if (Objects.nonNull(owner) && objectWikis.isEmpty()) {
                    Query queryInsertWikiWki = entityManagerSlave.createNativeQuery("INSERT INTO WIKI_WIKIS " +
                            "(NAME, OWNER, [TYPE], WIKI_HOME, SYNTAX, ALLOW_MULTI_SYNTAX, SPACE_ID) " +
                            "VALUES('', '" + owner + "', 'group', null, 'xwiki/2.0', 0,  " + item[0] + ");");
                    queryInsertWikiWki.executeUpdate();
                }
                owners.add(owner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return owners;
    }

    @Transactional("slaveTransactionManager")
    public void createWikiPages(List<Object[]> wikiWikis, EntityManager entityManagerSlave) {
        // ALTER TABLE WIKI_PAGES ADD SPACE_ID bigint NULL GO
        wikiWikis.forEach(item -> {
            try {
                Query queryCheckWikiPage = entityManagerSlave
                        .createNativeQuery("SELECT * FROM WIKI_PAGES WHERE WIKI_ID = " + item[0] + " AND NAME = 'WikiHome'");
                List<Object[]> objectWikiPages = queryCheckWikiPage.getResultList();

                if (objectWikiPages.isEmpty()) {
                    Query queryInsertWikiHomePage = entityManagerSlave.createNativeQuery("INSERT INTO WIKI_PAGES (WIKI_ID,PARENT_PAGE_ID,AUTHOR,NAME,OWNER,CREATED_DATE,UPDATED_DATE,CONTENT,SYNTAX,TITLE,EDITION_COMMENT,URL,MINOR_EDIT,ACTIVITY_ID,DELETED, SPACE_ID) VALUES " +
                            " (" + item[0] + ",NULL,NULL,'WikiHome',NULL,'1970-01-01 01:01:01','1970-01-01 01:01:01','= Welcome to Space " + item[2] + " Wiki =','xwiki/2.0','Wiki Home',NULL,NULL,0,NULL,0, " + item[7] + ");");
                    queryInsertWikiHomePage.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional("slaveTransactionManager")
    public void createWikiPageVersions(List<Object[]> wikiPages, EntityManager entityManagerSlave) {
        wikiPages.forEach(item -> {
            try {
                Query queryInsertWikiHomePage = entityManagerSlave.createNativeQuery("INSERT INTO WIKI_PAGE_VERSIONS " +
                        "(VERSION_NUMBER, AUTHOR, NAME, TITLE, CREATED_DATE, UPDATED_DATE, CONTENT, SYNTAX, EDITION_COMMENT, MINOR_EDIT, PAGE_ID) " +
                        "VALUES(0, '', '', '', '', '', '', '', '', 0, 0)");
                queryInsertWikiHomePage.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional("slaveTransactionManager")
    public void createWikiPagePermissions(EntityManager entityManagerSlave) {
        try {
            Query queryInsertWikiHomePage = entityManagerSlave.createNativeQuery("INSERT INTO WIKI_PAGE_PERMISSIONS " +
                    "(PAGE_ID, WIKI_IDENTITY, IDENTITY_TYPE, PERMISSION) " +
                    "VALUES(0, '', '', '')");
            queryInsertWikiHomePage.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional("slaveTransactionManager")
    public void createWikiWikisPermissions(EntityManager entityManagerSlave) {
        try {
            Query queryInsertWikiHomePage = entityManagerSlave.createNativeQuery("INSERT INTO WIKI_PAGE_PERMISSIONS " +
                    "(PAGE_ID, WIKI_IDENTITY, IDENTITY_TYPE, PERMISSION) " +
                    "VALUES(0, '', '', '')");
            queryInsertWikiHomePage.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object[] checkUserExits(String id, List<Object[]> dyVyUsers) {
        for (Object[] item : dyVyUsers) {
            if (Integer.parseInt(id) == Integer.parseInt(String.valueOf(item[0]))) {
                return item;
            }
        }
        return null;
    }

    private Object[] checkSpaceExits(String id, List<Object[]> dyVySpaces) {
        for (Object[] item : dyVySpaces) {
            if (id.equals(String.valueOf(item[0]))) {
                return item;
            }
        }
        return null;
    }

    private void replaceUrl(EntityManager entityManager, EntityManager entityManagerSlave, String text) {
        Query querySpaces = entityManager.createNativeQuery("select * from Dyve_Spaces ORDER BY SpaceID ASC");
        List<Object[]> dyVySpaces = querySpaces.getResultList();

        text.replaceAll(URL_PROFILE, URL_PROFILE_EXO);
        dyVySpaces.forEach(item -> {
            String wikiURLOld = "https://www.phillipconnect.com/spaces/%s/%s/wiki/view/%s/%s";
            String wikiURLNew = "https://phillipconnect.ecomedic.vn/portal/g/:spaces:%s/%s/wiki/%s";
        });
    }
}
