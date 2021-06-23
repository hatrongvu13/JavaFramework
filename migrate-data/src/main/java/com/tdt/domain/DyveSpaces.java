package com.tdt.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Dyve_Spaces", schema = "dbo", catalog = "pnpprotest")
public class DyveSpaces {
    private int spaceId;
    private String spaceName;
    private String description;
    private int createdBy;
    private Timestamp dateCreated;
    private Integer statusId;
    private boolean membershipType;
    private Integer spaceVisibility;
    private String fileSharingPhysicalPath;
    private Integer workflowId;
    private Integer currentWorkflowStepId;
    private Boolean isArticleWorkflowActive;
    private Boolean isBlogWorkflowActive;
    private Boolean isEventWorkflowActive;
    private Boolean isForumWorkflowActive;
    private Boolean isWikiWorkflowActive;
    private Boolean isFileWorkflowActive;
    private Boolean isPhotoWorkFlowActive;
    private Boolean isJobWorkFlowActive;
    private Boolean isVideoWorkFlowActive;
    private Boolean enableArticles;
    private Boolean enableBlogs;
    private Boolean enableEvents;
    private Boolean enableFiles;
    private Boolean enableForums;
    private Boolean enableJobs;
    private Boolean enablePhotos;
    private Boolean enableVideos;
    private Boolean enableWiki;
    private Boolean enableIssueTracker;
    private String groupPictureFileName;
    private Integer mediaServerId;
    private Boolean enableFileControl;
    private Integer invitationExpiryInDays;
    private Integer parentSpaceId;
    private Boolean disableWallEmailNotifications;
    private Boolean enableSurveys;
    private Boolean enableTaskManager;
    private boolean isFeatured;
    private String stub;
    private String landingPage;
    private String spaceIconFileName;
    private Boolean isIdeaWorkFlowActive;
    private Boolean enableIdeas;
    private Boolean autoSubscribeEmail;
    private Boolean isArticleCommentWorkflowActive;
    private Boolean isBlogEntryCommentWorkflowActive;
    private Boolean isEventCommentWorkflowActive;
    private Boolean isPhotoCommentWorkflowActive;
    private Boolean isVideoCommentWorkflowActive;
    private Boolean isWikiCommentWorkflowActive;
    private Boolean isFileCommentWorkflowActive;
    private Boolean isIdeaCommentWorkflowActive;
    private Boolean isForumPostWorkflowActive;
    private Boolean isJobCommentWorkflowActive;
    private String spaceHeaderImageFileName;
    private Boolean enableUpcomingBirthdaysControl;
    private Boolean enableContentReadConfirmation;
    private Boolean enableCalendarImportFromExternalSources;
    private Boolean enableEventsFromOtherSpacesInCalendar;
    private Boolean autoSubscribeForums;
    private Boolean enableTaskRating;
    private Boolean enableContentExpiration;

    @Id
    @Column(name = "SpaceID", nullable = false)
    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    @Basic
    @Column(name = "SpaceName", nullable = false, length = 1000)
    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    @Basic
    @Column(name = "Description", nullable = false, length = 4000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = false)
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "DateCreated", nullable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "StatusID", nullable = true)
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "MembershipType", nullable = false)
    public boolean isMembershipType() {
        return membershipType;
    }

    public void setMembershipType(boolean membershipType) {
        this.membershipType = membershipType;
    }

    @Basic
    @Column(name = "SpaceVisibility", nullable = true)
    public Integer getSpaceVisibility() {
        return spaceVisibility;
    }

    public void setSpaceVisibility(Integer spaceVisibility) {
        this.spaceVisibility = spaceVisibility;
    }

    @Basic
    @Column(name = "FileSharingPhysicalPath", nullable = true, length = 600)
    public String getFileSharingPhysicalPath() {
        return fileSharingPhysicalPath;
    }

    public void setFileSharingPhysicalPath(String fileSharingPhysicalPath) {
        this.fileSharingPhysicalPath = fileSharingPhysicalPath;
    }

    @Basic
    @Column(name = "WorkflowID", nullable = true)
    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    @Basic
    @Column(name = "CurrentWorkflowStepID", nullable = true)
    public Integer getCurrentWorkflowStepId() {
        return currentWorkflowStepId;
    }

    public void setCurrentWorkflowStepId(Integer currentWorkflowStepId) {
        this.currentWorkflowStepId = currentWorkflowStepId;
    }

    @Basic
    @Column(name = "IsArticleWorkflowActive", nullable = true)
    public Boolean getArticleWorkflowActive() {
        return isArticleWorkflowActive;
    }

    public void setArticleWorkflowActive(Boolean articleWorkflowActive) {
        isArticleWorkflowActive = articleWorkflowActive;
    }

    @Basic
    @Column(name = "IsBlogWorkflowActive", nullable = true)
    public Boolean getBlogWorkflowActive() {
        return isBlogWorkflowActive;
    }

    public void setBlogWorkflowActive(Boolean blogWorkflowActive) {
        isBlogWorkflowActive = blogWorkflowActive;
    }

    @Basic
    @Column(name = "IsEventWorkflowActive", nullable = true)
    public Boolean getEventWorkflowActive() {
        return isEventWorkflowActive;
    }

    public void setEventWorkflowActive(Boolean eventWorkflowActive) {
        isEventWorkflowActive = eventWorkflowActive;
    }

    @Basic
    @Column(name = "IsForumWorkflowActive", nullable = true)
    public Boolean getForumWorkflowActive() {
        return isForumWorkflowActive;
    }

    public void setForumWorkflowActive(Boolean forumWorkflowActive) {
        isForumWorkflowActive = forumWorkflowActive;
    }

    @Basic
    @Column(name = "IsWikiWorkflowActive", nullable = true)
    public Boolean getWikiWorkflowActive() {
        return isWikiWorkflowActive;
    }

    public void setWikiWorkflowActive(Boolean wikiWorkflowActive) {
        isWikiWorkflowActive = wikiWorkflowActive;
    }

    @Basic
    @Column(name = "IsFileWorkflowActive", nullable = true)
    public Boolean getFileWorkflowActive() {
        return isFileWorkflowActive;
    }

    public void setFileWorkflowActive(Boolean fileWorkflowActive) {
        isFileWorkflowActive = fileWorkflowActive;
    }

    @Basic
    @Column(name = "IsPhotoWorkFlowActive", nullable = true)
    public Boolean getPhotoWorkFlowActive() {
        return isPhotoWorkFlowActive;
    }

    public void setPhotoWorkFlowActive(Boolean photoWorkFlowActive) {
        isPhotoWorkFlowActive = photoWorkFlowActive;
    }

    @Basic
    @Column(name = "IsJobWorkFlowActive", nullable = true)
    public Boolean getJobWorkFlowActive() {
        return isJobWorkFlowActive;
    }

    public void setJobWorkFlowActive(Boolean jobWorkFlowActive) {
        isJobWorkFlowActive = jobWorkFlowActive;
    }

    @Basic
    @Column(name = "IsVideoWorkFlowActive", nullable = true)
    public Boolean getVideoWorkFlowActive() {
        return isVideoWorkFlowActive;
    }

    public void setVideoWorkFlowActive(Boolean videoWorkFlowActive) {
        isVideoWorkFlowActive = videoWorkFlowActive;
    }

    @Basic
    @Column(name = "EnableArticles", nullable = true)
    public Boolean getEnableArticles() {
        return enableArticles;
    }

    public void setEnableArticles(Boolean enableArticles) {
        this.enableArticles = enableArticles;
    }

    @Basic
    @Column(name = "EnableBlogs", nullable = true)
    public Boolean getEnableBlogs() {
        return enableBlogs;
    }

    public void setEnableBlogs(Boolean enableBlogs) {
        this.enableBlogs = enableBlogs;
    }

    @Basic
    @Column(name = "EnableEvents", nullable = true)
    public Boolean getEnableEvents() {
        return enableEvents;
    }

    public void setEnableEvents(Boolean enableEvents) {
        this.enableEvents = enableEvents;
    }

    @Basic
    @Column(name = "EnableFiles", nullable = true)
    public Boolean getEnableFiles() {
        return enableFiles;
    }

    public void setEnableFiles(Boolean enableFiles) {
        this.enableFiles = enableFiles;
    }

    @Basic
    @Column(name = "EnableForums", nullable = true)
    public Boolean getEnableForums() {
        return enableForums;
    }

    public void setEnableForums(Boolean enableForums) {
        this.enableForums = enableForums;
    }

    @Basic
    @Column(name = "EnableJobs", nullable = true)
    public Boolean getEnableJobs() {
        return enableJobs;
    }

    public void setEnableJobs(Boolean enableJobs) {
        this.enableJobs = enableJobs;
    }

    @Basic
    @Column(name = "EnablePhotos", nullable = true)
    public Boolean getEnablePhotos() {
        return enablePhotos;
    }

    public void setEnablePhotos(Boolean enablePhotos) {
        this.enablePhotos = enablePhotos;
    }

    @Basic
    @Column(name = "EnableVideos", nullable = true)
    public Boolean getEnableVideos() {
        return enableVideos;
    }

    public void setEnableVideos(Boolean enableVideos) {
        this.enableVideos = enableVideos;
    }

    @Basic
    @Column(name = "EnableWiki", nullable = true)
    public Boolean getEnableWiki() {
        return enableWiki;
    }

    public void setEnableWiki(Boolean enableWiki) {
        this.enableWiki = enableWiki;
    }

    @Basic
    @Column(name = "EnableIssueTracker", nullable = true)
    public Boolean getEnableIssueTracker() {
        return enableIssueTracker;
    }

    public void setEnableIssueTracker(Boolean enableIssueTracker) {
        this.enableIssueTracker = enableIssueTracker;
    }

    @Basic
    @Column(name = "GroupPictureFileName", nullable = true, length = 600)
    public String getGroupPictureFileName() {
        return groupPictureFileName;
    }

    public void setGroupPictureFileName(String groupPictureFileName) {
        this.groupPictureFileName = groupPictureFileName;
    }

    @Basic
    @Column(name = "MediaServerID", nullable = true)
    public Integer getMediaServerId() {
        return mediaServerId;
    }

    public void setMediaServerId(Integer mediaServerId) {
        this.mediaServerId = mediaServerId;
    }

    @Basic
    @Column(name = "EnableFileControl", nullable = true)
    public Boolean getEnableFileControl() {
        return enableFileControl;
    }

    public void setEnableFileControl(Boolean enableFileControl) {
        this.enableFileControl = enableFileControl;
    }

    @Basic
    @Column(name = "InvitationExpiryInDays", nullable = true)
    public Integer getInvitationExpiryInDays() {
        return invitationExpiryInDays;
    }

    public void setInvitationExpiryInDays(Integer invitationExpiryInDays) {
        this.invitationExpiryInDays = invitationExpiryInDays;
    }

    @Basic
    @Column(name = "ParentSpaceID", nullable = true)
    public Integer getParentSpaceId() {
        return parentSpaceId;
    }

    public void setParentSpaceId(Integer parentSpaceId) {
        this.parentSpaceId = parentSpaceId;
    }

    @Basic
    @Column(name = "DisableWallEmailNotifications", nullable = true)
    public Boolean getDisableWallEmailNotifications() {
        return disableWallEmailNotifications;
    }

    public void setDisableWallEmailNotifications(Boolean disableWallEmailNotifications) {
        this.disableWallEmailNotifications = disableWallEmailNotifications;
    }

    @Basic
    @Column(name = "EnableSurveys", nullable = true)
    public Boolean getEnableSurveys() {
        return enableSurveys;
    }

    public void setEnableSurveys(Boolean enableSurveys) {
        this.enableSurveys = enableSurveys;
    }

    @Basic
    @Column(name = "EnableTaskManager", nullable = true)
    public Boolean getEnableTaskManager() {
        return enableTaskManager;
    }

    public void setEnableTaskManager(Boolean enableTaskManager) {
        this.enableTaskManager = enableTaskManager;
    }

    @Basic
    @Column(name = "IsFeatured", nullable = false)
    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    @Basic
    @Column(name = "Stub", nullable = true, length = 255)
    public String getStub() {
        return stub;
    }

    public void setStub(String stub) {
        this.stub = stub;
    }

    @Basic
    @Column(name = "LandingPage", nullable = true, length = 600)
    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    @Basic
    @Column(name = "SpaceIconFileName", nullable = true, length = 600)
    public String getSpaceIconFileName() {
        return spaceIconFileName;
    }

    public void setSpaceIconFileName(String spaceIconFileName) {
        this.spaceIconFileName = spaceIconFileName;
    }

    @Basic
    @Column(name = "IsIdeaWorkFlowActive", nullable = true)
    public Boolean getIdeaWorkFlowActive() {
        return isIdeaWorkFlowActive;
    }

    public void setIdeaWorkFlowActive(Boolean ideaWorkFlowActive) {
        isIdeaWorkFlowActive = ideaWorkFlowActive;
    }

    @Basic
    @Column(name = "EnableIdeas", nullable = true)
    public Boolean getEnableIdeas() {
        return enableIdeas;
    }

    public void setEnableIdeas(Boolean enableIdeas) {
        this.enableIdeas = enableIdeas;
    }

    @Basic
    @Column(name = "AutoSubscribeEmail", nullable = true)
    public Boolean getAutoSubscribeEmail() {
        return autoSubscribeEmail;
    }

    public void setAutoSubscribeEmail(Boolean autoSubscribeEmail) {
        this.autoSubscribeEmail = autoSubscribeEmail;
    }

    @Basic
    @Column(name = "IsArticleCommentWorkflowActive", nullable = true)
    public Boolean getArticleCommentWorkflowActive() {
        return isArticleCommentWorkflowActive;
    }

    public void setArticleCommentWorkflowActive(Boolean articleCommentWorkflowActive) {
        isArticleCommentWorkflowActive = articleCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsBlogEntryCommentWorkflowActive", nullable = true)
    public Boolean getBlogEntryCommentWorkflowActive() {
        return isBlogEntryCommentWorkflowActive;
    }

    public void setBlogEntryCommentWorkflowActive(Boolean blogEntryCommentWorkflowActive) {
        isBlogEntryCommentWorkflowActive = blogEntryCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsEventCommentWorkflowActive", nullable = true)
    public Boolean getEventCommentWorkflowActive() {
        return isEventCommentWorkflowActive;
    }

    public void setEventCommentWorkflowActive(Boolean eventCommentWorkflowActive) {
        isEventCommentWorkflowActive = eventCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsPhotoCommentWorkflowActive", nullable = true)
    public Boolean getPhotoCommentWorkflowActive() {
        return isPhotoCommentWorkflowActive;
    }

    public void setPhotoCommentWorkflowActive(Boolean photoCommentWorkflowActive) {
        isPhotoCommentWorkflowActive = photoCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsVideoCommentWorkflowActive", nullable = true)
    public Boolean getVideoCommentWorkflowActive() {
        return isVideoCommentWorkflowActive;
    }

    public void setVideoCommentWorkflowActive(Boolean videoCommentWorkflowActive) {
        isVideoCommentWorkflowActive = videoCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsWikiCommentWorkflowActive", nullable = true)
    public Boolean getWikiCommentWorkflowActive() {
        return isWikiCommentWorkflowActive;
    }

    public void setWikiCommentWorkflowActive(Boolean wikiCommentWorkflowActive) {
        isWikiCommentWorkflowActive = wikiCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsFileCommentWorkflowActive", nullable = true)
    public Boolean getFileCommentWorkflowActive() {
        return isFileCommentWorkflowActive;
    }

    public void setFileCommentWorkflowActive(Boolean fileCommentWorkflowActive) {
        isFileCommentWorkflowActive = fileCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsIdeaCommentWorkflowActive", nullable = true)
    public Boolean getIdeaCommentWorkflowActive() {
        return isIdeaCommentWorkflowActive;
    }

    public void setIdeaCommentWorkflowActive(Boolean ideaCommentWorkflowActive) {
        isIdeaCommentWorkflowActive = ideaCommentWorkflowActive;
    }

    @Basic
    @Column(name = "IsForumPostWorkflowActive", nullable = true)
    public Boolean getForumPostWorkflowActive() {
        return isForumPostWorkflowActive;
    }

    public void setForumPostWorkflowActive(Boolean forumPostWorkflowActive) {
        isForumPostWorkflowActive = forumPostWorkflowActive;
    }

    @Basic
    @Column(name = "IsJobCommentWorkflowActive", nullable = true)
    public Boolean getJobCommentWorkflowActive() {
        return isJobCommentWorkflowActive;
    }

    public void setJobCommentWorkflowActive(Boolean jobCommentWorkflowActive) {
        isJobCommentWorkflowActive = jobCommentWorkflowActive;
    }

    @Basic
    @Column(name = "SpaceHeaderImageFileName", nullable = true, length = 600)
    public String getSpaceHeaderImageFileName() {
        return spaceHeaderImageFileName;
    }

    public void setSpaceHeaderImageFileName(String spaceHeaderImageFileName) {
        this.spaceHeaderImageFileName = spaceHeaderImageFileName;
    }

    @Basic
    @Column(name = "EnableUpcomingBirthdaysControl", nullable = true)
    public Boolean getEnableUpcomingBirthdaysControl() {
        return enableUpcomingBirthdaysControl;
    }

    public void setEnableUpcomingBirthdaysControl(Boolean enableUpcomingBirthdaysControl) {
        this.enableUpcomingBirthdaysControl = enableUpcomingBirthdaysControl;
    }

    @Basic
    @Column(name = "EnableContentReadConfirmation", nullable = true)
    public Boolean getEnableContentReadConfirmation() {
        return enableContentReadConfirmation;
    }

    public void setEnableContentReadConfirmation(Boolean enableContentReadConfirmation) {
        this.enableContentReadConfirmation = enableContentReadConfirmation;
    }

    @Basic
    @Column(name = "EnableCalendarImportFromExternalSources", nullable = true)
    public Boolean getEnableCalendarImportFromExternalSources() {
        return enableCalendarImportFromExternalSources;
    }

    public void setEnableCalendarImportFromExternalSources(Boolean enableCalendarImportFromExternalSources) {
        this.enableCalendarImportFromExternalSources = enableCalendarImportFromExternalSources;
    }

    @Basic
    @Column(name = "EnableEventsFromOtherSpacesInCalendar", nullable = true)
    public Boolean getEnableEventsFromOtherSpacesInCalendar() {
        return enableEventsFromOtherSpacesInCalendar;
    }

    public void setEnableEventsFromOtherSpacesInCalendar(Boolean enableEventsFromOtherSpacesInCalendar) {
        this.enableEventsFromOtherSpacesInCalendar = enableEventsFromOtherSpacesInCalendar;
    }

    @Basic
    @Column(name = "AutoSubscribeForums", nullable = true)
    public Boolean getAutoSubscribeForums() {
        return autoSubscribeForums;
    }

    public void setAutoSubscribeForums(Boolean autoSubscribeForums) {
        this.autoSubscribeForums = autoSubscribeForums;
    }

    @Basic
    @Column(name = "EnableTaskRating", nullable = true)
    public Boolean getEnableTaskRating() {
        return enableTaskRating;
    }

    public void setEnableTaskRating(Boolean enableTaskRating) {
        this.enableTaskRating = enableTaskRating;
    }

    @Basic
    @Column(name = "EnableContentExpiration", nullable = true)
    public Boolean getEnableContentExpiration() {
        return enableContentExpiration;
    }

    public void setEnableContentExpiration(Boolean enableContentExpiration) {
        this.enableContentExpiration = enableContentExpiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DyveSpaces that = (DyveSpaces) o;
        return spaceId == that.spaceId &&
                createdBy == that.createdBy &&
                membershipType == that.membershipType &&
                isFeatured == that.isFeatured &&
                Objects.equals(spaceName, that.spaceName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(statusId, that.statusId) &&
                Objects.equals(spaceVisibility, that.spaceVisibility) &&
                Objects.equals(fileSharingPhysicalPath, that.fileSharingPhysicalPath) &&
                Objects.equals(workflowId, that.workflowId) &&
                Objects.equals(currentWorkflowStepId, that.currentWorkflowStepId) &&
                Objects.equals(isArticleWorkflowActive, that.isArticleWorkflowActive) &&
                Objects.equals(isBlogWorkflowActive, that.isBlogWorkflowActive) &&
                Objects.equals(isEventWorkflowActive, that.isEventWorkflowActive) &&
                Objects.equals(isForumWorkflowActive, that.isForumWorkflowActive) &&
                Objects.equals(isWikiWorkflowActive, that.isWikiWorkflowActive) &&
                Objects.equals(isFileWorkflowActive, that.isFileWorkflowActive) &&
                Objects.equals(isPhotoWorkFlowActive, that.isPhotoWorkFlowActive) &&
                Objects.equals(isJobWorkFlowActive, that.isJobWorkFlowActive) &&
                Objects.equals(isVideoWorkFlowActive, that.isVideoWorkFlowActive) &&
                Objects.equals(enableArticles, that.enableArticles) &&
                Objects.equals(enableBlogs, that.enableBlogs) &&
                Objects.equals(enableEvents, that.enableEvents) &&
                Objects.equals(enableFiles, that.enableFiles) &&
                Objects.equals(enableForums, that.enableForums) &&
                Objects.equals(enableJobs, that.enableJobs) &&
                Objects.equals(enablePhotos, that.enablePhotos) &&
                Objects.equals(enableVideos, that.enableVideos) &&
                Objects.equals(enableWiki, that.enableWiki) &&
                Objects.equals(enableIssueTracker, that.enableIssueTracker) &&
                Objects.equals(groupPictureFileName, that.groupPictureFileName) &&
                Objects.equals(mediaServerId, that.mediaServerId) &&
                Objects.equals(enableFileControl, that.enableFileControl) &&
                Objects.equals(invitationExpiryInDays, that.invitationExpiryInDays) &&
                Objects.equals(parentSpaceId, that.parentSpaceId) &&
                Objects.equals(disableWallEmailNotifications, that.disableWallEmailNotifications) &&
                Objects.equals(enableSurveys, that.enableSurveys) &&
                Objects.equals(enableTaskManager, that.enableTaskManager) &&
                Objects.equals(stub, that.stub) &&
                Objects.equals(landingPage, that.landingPage) &&
                Objects.equals(spaceIconFileName, that.spaceIconFileName) &&
                Objects.equals(isIdeaWorkFlowActive, that.isIdeaWorkFlowActive) &&
                Objects.equals(enableIdeas, that.enableIdeas) &&
                Objects.equals(autoSubscribeEmail, that.autoSubscribeEmail) &&
                Objects.equals(isArticleCommentWorkflowActive, that.isArticleCommentWorkflowActive) &&
                Objects.equals(isBlogEntryCommentWorkflowActive, that.isBlogEntryCommentWorkflowActive) &&
                Objects.equals(isEventCommentWorkflowActive, that.isEventCommentWorkflowActive) &&
                Objects.equals(isPhotoCommentWorkflowActive, that.isPhotoCommentWorkflowActive) &&
                Objects.equals(isVideoCommentWorkflowActive, that.isVideoCommentWorkflowActive) &&
                Objects.equals(isWikiCommentWorkflowActive, that.isWikiCommentWorkflowActive) &&
                Objects.equals(isFileCommentWorkflowActive, that.isFileCommentWorkflowActive) &&
                Objects.equals(isIdeaCommentWorkflowActive, that.isIdeaCommentWorkflowActive) &&
                Objects.equals(isForumPostWorkflowActive, that.isForumPostWorkflowActive) &&
                Objects.equals(isJobCommentWorkflowActive, that.isJobCommentWorkflowActive) &&
                Objects.equals(spaceHeaderImageFileName, that.spaceHeaderImageFileName) &&
                Objects.equals(enableUpcomingBirthdaysControl, that.enableUpcomingBirthdaysControl) &&
                Objects.equals(enableContentReadConfirmation, that.enableContentReadConfirmation) &&
                Objects.equals(enableCalendarImportFromExternalSources, that.enableCalendarImportFromExternalSources) &&
                Objects.equals(enableEventsFromOtherSpacesInCalendar, that.enableEventsFromOtherSpacesInCalendar) &&
                Objects.equals(autoSubscribeForums, that.autoSubscribeForums) &&
                Objects.equals(enableTaskRating, that.enableTaskRating) &&
                Objects.equals(enableContentExpiration, that.enableContentExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceId, spaceName, description, createdBy, dateCreated, statusId, membershipType, spaceVisibility, fileSharingPhysicalPath, workflowId, currentWorkflowStepId, isArticleWorkflowActive, isBlogWorkflowActive, isEventWorkflowActive, isForumWorkflowActive, isWikiWorkflowActive, isFileWorkflowActive, isPhotoWorkFlowActive, isJobWorkFlowActive, isVideoWorkFlowActive, enableArticles, enableBlogs, enableEvents, enableFiles, enableForums, enableJobs, enablePhotos, enableVideos, enableWiki, enableIssueTracker, groupPictureFileName, mediaServerId, enableFileControl, invitationExpiryInDays, parentSpaceId, disableWallEmailNotifications, enableSurveys, enableTaskManager, isFeatured, stub, landingPage, spaceIconFileName, isIdeaWorkFlowActive, enableIdeas, autoSubscribeEmail, isArticleCommentWorkflowActive, isBlogEntryCommentWorkflowActive, isEventCommentWorkflowActive, isPhotoCommentWorkflowActive, isVideoCommentWorkflowActive, isWikiCommentWorkflowActive, isFileCommentWorkflowActive, isIdeaCommentWorkflowActive, isForumPostWorkflowActive, isJobCommentWorkflowActive, spaceHeaderImageFileName, enableUpcomingBirthdaysControl, enableContentReadConfirmation, enableCalendarImportFromExternalSources, enableEventsFromOtherSpacesInCalendar, autoSubscribeForums, enableTaskRating, enableContentExpiration);
    }
}
