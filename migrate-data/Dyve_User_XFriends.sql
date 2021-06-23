-- SOC_ARTICLES definition

-- Drop table

-- DROP TABLE SOC_ARTICLES GO

CREATE TABLE SOC_ARTICLES
(
    ARTICLE_ID             int IDENTITY(1,1) NOT NULL,
    ENTITY_TYPE_ID         int NULL,
    ARTICLE_TYPE_ID        int NULL,
    ARTICLE_TITLE          nvarchar COLLATE Vietnamese_CI_AS NULL,
    ARTICLE_STUB           nvarchar COLLATE Vietnamese_CI_AS NULL,
    ARTICLE_BODY           nvarchar COLLATE Vietnamese_CI_AS NULL,
    UPDATE_BY_USER_ID      int NULL,
    SPACE_ID               bigint DEFAULT 0 NULL,
    CATEGORY_ID            int    DEFAULT 0 NULL,
    STATUS_ID              int    DEFAULT 1 NULL,
    DATE_CREATED           datetime NULL,
    DATE_UPDATED           datetime NULL,
    DATE_PUBLISHED         datetime NULL,
    VIEW_COUNT             int    DEFAULT 0 NULL,
    COMMENT_COUNT          int    DEFAULT 0 NULL,
    META_TITLE             nvarchar COLLATE Vietnamese_CI_AS NULL,
    META_DESCRIPTION       nvarchar COLLATE Vietnamese_CI_AS NULL,
    ARTICLE_FEATURED_IMAGE nvarchar COLLATE Vietnamese_CI_AS NULL,
    ARTICLE_THUMB_IMAGE    nvarchar COLLATE Vietnamese_CI_AS NULL,
    IDENTITY_ID            bigint DEFAULT 0 NULL,
    RATING                 float NULL
) GO;


-- SOC_ARTICLES_CATEGORY definition

-- Drop table

-- DROP TABLE SOC_ARTICLES_CATEGORY GO

CREATE TABLE SOC_ARTICLES_CATEGORY
(
    CATEGORY_ID          int IDENTITY(1,1) NOT NULL,
    CATEGORY_NAME        nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CATEGORY_DESCRIPTION nvarchar(1000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    PARENT_ID            int NOT NULL,
    ACTIVE_STATUS        int NOT NULL,
    SEO_NAME             nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    META_TITLE           nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    META_DESCRIPTION     nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    META_KEYWORDS        nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    UTIL_STRING1         nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    UTIL_STRING2         nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    SPACE_ID             int DEFAULT 0 NULL,
    CONSTRAINT PK_SOC_ARTICLES_CATEGORY PRIMARY KEY (CATEGORY_ID)
) GO;


-- SOC_ARTICLE_COMMENTS definition

-- Drop table

-- DROP TABLE SOC_ARTICLE_COMMENTS GO

CREATE TABLE SOC_ARTICLE_COMMENTS
(
    COMMENT_ID    int IDENTITY(1,1) NOT NULL,
    COMMENT_TEXT  nvarchar COLLATE Vietnamese_CI_AS NULL,
    NAME          nvarchar(75) COLLATE Vietnamese_CI_AS NOT NULL,
    EMAIL_ADDRESS nvarchar(75) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    WEBSITE_URL   nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    IP_ADDRESS    nvarchar(75) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    DATE_ADDED    datetime DEFAULT getdate() NOT NULL,
    ARTICLE_ID    int                        NOT NULL,
    STATUS_ID     int                        NOT NULL,
    USER_ID       int NULL,
    PARENT_ID     int NULL,
    RATING        float NULL,
    CONSTRAINT PK_SOC_ARTICLE_COMMENTS PRIMARY KEY (COMMENT_ID)
) GO;


-- SOC_BLOGS definition

-- Drop table

-- DROP TABLE SOC_BLOGS GO

CREATE TABLE SOC_BLOGS
(
    BLOG_ID             int IDENTITY(1,1) NOT NULL,
    ENTITY_TYPE_ID      int NULL,
    BLOG_TYPE_ID        int NULL,
    BLOG_TITLE          nvarchar COLLATE Vietnamese_CI_AS NULL,
    BLOG_STUB           nvarchar COLLATE Vietnamese_CI_AS NULL,
    BLOG_BODY           nvarchar COLLATE Vietnamese_CI_AS NULL,
    UPDATE_BY_USER_ID   int NULL,
    SPACE_ID            int DEFAULT 0 NULL,
    STATUS_ID           int DEFAULT 1 NULL,
    DATE_CREATED        datetime NULL,
    DATE_UPDATED        datetime NULL,
    DATE_PUBLISHED      datetime NULL,
    VIEW_COUNT          int DEFAULT 0 NULL,
    COMMENT_COUNT       int DEFAULT 0 NULL,
    META_TITLE          nvarchar COLLATE Vietnamese_CI_AS NULL,
    META_DESCRIPTION    nvarchar COLLATE Vietnamese_CI_AS NULL,
    META_KEYWORDS       nvarchar COLLATE Vietnamese_CI_AS NULL,
    RATING              float NULL,
    BLOG_FEATURED_IMAGE nvarchar COLLATE Vietnamese_CI_AS NULL,
    BLOG_THUMB_IMAGE    nvarchar COLLATE Vietnamese_CI_AS NULL,
    IDENTITY_ID         int DEFAULT 0 NULL,
    CATEGORY_ID         int NOT NULL
) GO;


-- SOC_BLOG_COMMENTS definition

-- Drop table

-- DROP TABLE SOC_BLOG_COMMENTS GO

CREATE TABLE SOC_BLOG_COMMENTS
(
    CommentID   int IDENTITY(1,1) NOT NULL,
    UserName    nvarchar COLLATE Vietnamese_CI_AS NULL,
    UserEmail   nvarchar COLLATE Vietnamese_CI_AS NULL,
    UserWebsite nvarchar COLLATE Vietnamese_CI_AS NULL,
    CommentText nvarchar COLLATE Vietnamese_CI_AS NULL,
    DatePosted  datetime NULL,
    UserID      int NULL,
    UserIP      varchar(MAX
) COLLATE Vietnamese_CI_AS NULL,
	BlogEntryID int NULL,
	ParentID int NULL,
	Status int DEFAULT 0 NULL,
	Rating float NULL
) GO;


-- SOC_CATEGORIES definition

-- Drop table

-- DROP TABLE SOC_CATEGORIES GO

CREATE TABLE SOC_CATEGORIES
(
    CATEGORY_ID          int IDENTITY(1,1) NOT NULL,
    OLD_CATEGORY_ID      int NULL,
    ENTITY_TYPE_ID       int           NOT NULL,
    CATEGORY_NAME        nvarchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    CATEGORY_DESCRIPTION nvarchar(2048) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    PARENT_CATEGORY_ID   int DEFAULT 0 NOT NULL,
    STATUS_ID            int DEFAULT 1 NOT NULL,
    CATEGORY_STUB        nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    SPACE_ID             int DEFAULT 0 NOT NULL,
    TENANT_ID            int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_SOC_CATEGORY PRIMARY KEY (CATEGORY_ID)
) GO;


-- SOC_EVENTS definition

-- Drop table

-- DROP TABLE SOC_EVENTS GO

CREATE TABLE SOC_EVENTS
(
    EVENT_ID                  int IDENTITY(1,1) NOT NULL,
    TITLE                     nvarchar(400) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    DESCRIPTION               nvarchar COLLATE Vietnamese_CI_AS NULL,
    START_DATE                datetime      NOT NULL,
    END_DATE                  datetime      NOT NULL,
    VIEWS                     int           NOT NULL,
    IS_FEATURED               bit           NOT NULL,
    USER_ID                   int           NOT NULL,
    ADDRESS                   nvarchar(600) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    ADDRESS_2                 nvarchar(600) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CITY                      nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    METRO                     nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    STATE                     nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    COUNTRY_ID                int           NOT NULL,
    ZIP                       varchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    EVENT_STATUS_ID           int           NOT NULL,
    TELE_PHONE                varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    DATE_CREATED              datetime      NOT NULL,
    EVENT_TYPE_ID             int           NOT NULL,
    VENUE_NAME                nvarchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    WEBSITE                   nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    ORGANIZATION              nvarchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    WHO_SHOULD_ATT_END        nvarchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    KEY_WORDS                 nvarchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    IS_ONLINE                 bit NULL,
    SPACE_ID                  int NULL,
    IS_PRIVATE_SPACE          bit NULL,
    CURRENT_WORK_FLOW_STEP_ID int NULL,
    WORK_FLOW_ID              int NULL,
    STATE_ID                  int NULL,
    METRO_ID                  int NULL,
    CUSTOM_FIELDONE           nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    MAIN_IMAGE_FILE_NAME      nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    MEDIA_SERVER_ID           int NULL,
    ALBUM_ID                  int NULL,
    IS_PRIVATE                bit DEFAULT 0 NULL,
    IS_EVENT                  bit DEFAULT 1 NOT NULL,
    IS_CLASS_PERIOD           bit DEFAULT 0 NOT NULL,
    BACKGROUND_COLOR          varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    BORDER_COLOR              varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    TEXT_COLOR                varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    IS_REPEAT                 bit DEFAULT 0 NULL,
    FREQUENCY                 int NULL,
    DAYS_OF_WEEK              int NULL,
    DAYS_OF_MONTH             int NULL,
    WEEKLY_INTERVAL           int NULL,
    QUARTER_INTERVAL          int NULL,
    QUARTERLY_INTERVAL        int NULL,
    MONTH_INTERVAL            int NULL,
    MONTHLY_INTERVAL          int NULL,
    DAY_INTERVAL              int NULL,
    RANGE_START_MONTH         int NULL,
    RANGE_END_MONTH           int NULL,
    REPEAT_START_DATE         datetime NULL,
    REPEAT_END_DATE           datetime NULL,
    IS_ALL_DAY                bit DEFAULT 0 NULL,
    MONTH_REPEAT_BY           int NULL,
    END_AFTER_OCCURENCES      int NULL,
    YEAR_INTERVAL             int NULL,
    CALENDAR_ID               int NULL,
    CAN_INVITE_OTHERS         bit NULL,
    INVITED_BY_CRM_CONTACT_ID int NULL,
    CHANGE_KEY                nvarchar(4000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    EVENT_ID_EXTERNAL         nvarchar(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    OUT_LOOK_LOCATION_DETAIL  nvarchar(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    COLOR_CODE                tinyint NULL,
    IS_BUSY                   bit DEFAULT 1 NULL,
    ICALU_ID                  nvarchar(1) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    RATING                    float NULL,
    CONSTRAINT PK_Dyve_Events PRIMARY KEY (EVENT_ID)
) GO;


-- SOC_EVENT_COMMENT definition

-- Drop table

-- DROP TABLE SOC_EVENT_COMMENT GO

CREATE TABLE SOC_EVENT_COMMENT
(
    COMMENT_ID    int IDENTITY(1,1) NOT NULL,
    COMMENT_TEXT  nvarchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    NAME          nvarchar(75) COLLATE Vietnamese_CI_AS NULL,
    EMAIL_ADDRESS nvarchar(75) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    WEBSITE_URL   nvarchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    IP_ADDRESS    nvarchar(75) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    DATE_ADDED    datetime NOT NULL,
    EVENT_ID      int      NOT NULL,
    STATUS        int DEFAULT 0 NULL,
    USER_ID       int NULL,
    PARENT_ID     int NULL,
    RATING        float NULL,
    TENANT_ID     int NULL,
    CONSTRAINT PK_SOC_Event_Comment PRIMARY KEY (COMMENT_ID)
) GO;


-- SOC_EVENT_TYPE definition

-- Drop table

-- DROP TABLE SOC_EVENT_TYPE GO

CREATE TABLE SOC_EVENT_TYPE
(
    EVENT_TYPE_ID   int IDENTITY(1,1) NOT NULL,
    EVENT_TYPE_NAME nvarchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    SPACE_ID        int NULL,
    CONSTRAINT PK_SOC_EVENT_TYPE PRIMARY KEY (EVENT_TYPE_ID)
) GO;


-- SOC_IT_ISSUE definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE GO

CREATE TABLE SOC_IT_ISSUE
(
    IssueID                int IDENTITY(1,1) NOT NULL,
    IssueTitle             nvarchar(600) COLLATE Vietnamese_CI_AS NOT NULL,
    IssueDescription       nvarchar COLLATE Vietnamese_CI_AS NOT NULL,
    DateCreated            datetime NULL,
    ReportedByUserID       int NULL,
    AssignedToUserID       int NULL,
    ProjectID              int NULL,
    ProjectSectionID       int NULL,
    IssueStatusID          int NULL,
    IssuePriorityID        int NULL,
    DateUpdated            datetime NULL,
    LastUpdatedByUserID    int NULL,
    DateTaskStart          datetime NULL,
    DateTaskEnd            datetime NULL,
    TaskSortOrder          int NULL,
    PredecessorIssueID     int NULL,
    IsTaskItem             bit DEFAULT 0 NULL,
    MilestoneID            int NULL,
    ParentID               int NULL,
    DateTaskDue            datetime NULL,
    TaskEstimatedHours     float NULL,
    IsBillableTask         bit NULL,
    TenantID               int DEFAULT 1                     NOT NULL,
    Rating                 float NULL,
    ReferencedEntityID     int NULL,
    ReferencedEntityTypeID int NULL,
    Latitude               decimal(20, 6) NULL,
    Longitude              decimal(20, 6) NULL,
    WorkflowID             int DEFAULT 0 NULL,
    CurrentWorkflowStepID  int DEFAULT 0 NULL,
    StatusID               int DEFAULT 1 NULL,
    SpaceID                bigint NULL,
    CONSTRAINT PK_Soc_IT_Issue PRIMARY KEY (IssueID)
) GO;


-- SOC_IT_ISSUE_ASSIGNMENT definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_ASSIGNMENT GO

CREATE TABLE SOC_IT_ISSUE_ASSIGNMENT
(
    AssignmentID     int IDENTITY(1,1) NOT NULL,
    IssueID          int           NOT NULL,
    UserID           int           NOT NULL,
    DateAssigned     datetime      NOT NULL,
    Remarks          nvarchar(2000) COLLATE Vietnamese_CI_AS NULL,
    AssignedByUserID int NULL,
    TenantID         int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_IssueAssignment PRIMARY KEY (AssignmentID)
) GO;


-- SOC_IT_ISSUE_COMMENT definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_COMMENT GO

CREATE TABLE SOC_IT_ISSUE_COMMENT
(
    CommentID                int IDENTITY(1,1) NOT NULL,
    CommentText              nvarchar COLLATE Vietnamese_CI_AS NOT NULL,
    IssueID                  int                               NOT NULL,
    CommentDate              datetime                          NOT NULL,
    UserID                   int                               NOT NULL,
    Status                   int NULL,
    IsInternal               bit NULL,
    CommentTextFormatted     nvarchar COLLATE Vietnamese_CI_AS NULL,
    CommentTextHtmlFormatted nvarchar COLLATE Vietnamese_CI_AS NULL,
    SpaceID                  int DEFAULT 0 NULL,
    TenantID                 int DEFAULT 1                     NOT NULL,
    CONSTRAINT PK_Soc_IT_IssueComment PRIMARY KEY (CommentID)
) GO;


-- SOC_IT_ISSUE_COMMENT_TAG definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_COMMENT_TAG GO

CREATE TABLE SOC_IT_ISSUE_COMMENT_TAG
(
    TagID    int IDENTITY(1,1) NOT NULL,
    TagName  nvarchar(200) COLLATE Vietnamese_CI_AS NOT NULL,
    TenantID int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_IssueCommentTag PRIMARY KEY (TagID)
) GO;


-- SOC_IT_ISSUE_COMMENT_XTAG definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_COMMENT_XTAG GO

CREATE TABLE SOC_IT_ISSUE_COMMENT_XTAG
(
    TagID          int           NOT NULL,
    IssueCommentID int           NOT NULL,
    TenantID       int DEFAULT 1 NOT NULL
) GO;


-- SOC_IT_ISSUE_PRIORITY definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_PRIORITY GO

CREATE TABLE SOC_IT_ISSUE_PRIORITY
(
    IssuePriorityID       int IDENTITY(1,1) NOT NULL,
    IssuePriorityName     nvarchar(50) COLLATE Vietnamese_CI_AS NOT NULL,
    SpaceID               int           NOT NULL,
    IsDefault             bit NULL,
    TenantID              int DEFAULT 1 NOT NULL,
    IsTaskPriority        bit DEFAULT 0 NOT NULL,
    PriorityColorCssClass nvarchar(100) COLLATE Vietnamese_CI_AS NULL,
    CONSTRAINT PK_Soc_BT_BugPriority PRIMARY KEY (IssuePriorityID)
) GO;


-- SOC_IT_ISSUE_STATUS definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_STATUS GO

CREATE TABLE SOC_IT_ISSUE_STATUS
(
    IssueStatusID       int IDENTITY(1,1) NOT NULL,
    SpaceID             int           NOT NULL,
    IssueStatusTypeID   int NULL,
    IssueStatusNameKey  nvarchar(100) COLLATE Vietnamese_CI_AS NULL,
    StatusColorCssClass varchar(100) COLLATE Vietnamese_CI_AS NULL,
    IsDefault           bit NULL,
    StatusResourceKey   nvarchar(512) COLLATE Vietnamese_CI_AS NULL,
    TenantID            int DEFAULT 1 NOT NULL,
    StatusIconCssClass  varchar(100) COLLATE Vietnamese_CI_AS NULL,
    IsTaskStatus        bit DEFAULT 0 NOT NULL,
    CONSTRAINT PK_Soc_BT_BugStatus PRIMARY KEY (IssueStatusID)
) GO;


-- SOC_IT_ISSUE_STATUS_LOCALE definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_STATUS_LOCALE GO

CREATE TABLE SOC_IT_ISSUE_STATUS_LOCALE
(
    IssueStatusID   int                                  NOT NULL,
    IssueStatusName nvarchar(50) COLLATE Vietnamese_CI_AS NOT NULL,
    CultureName     varchar(10) COLLATE Vietnamese_CI_AS NOT NULL,
    TenantID        int DEFAULT 1                        NOT NULL
) GO
CREATE
NONCLUSTERED INDEX Soc_IT_IssueStatus_Locale_CultureName ON dbo.SOC_IT_ISSUE_STATUS_LOCALE (  CultureName ASC  )
	 INCLUDE ( IssueStatusID , IssueStatusName ) 
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ]  GO;


-- SOC_IT_ISSUE_TAG definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_TAG GO

CREATE TABLE SOC_IT_ISSUE_TAG
(
    TagID    int IDENTITY(1,1) NOT NULL,
    TagName  nvarchar(200) COLLATE Vietnamese_CI_AS NOT NULL,
    TenantID int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_IssueTag PRIMARY KEY (TagID)
) GO;


-- SOC_IT_ISSUE_TIME_LOG definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_TIME_LOG GO

CREATE TABLE SOC_IT_ISSUE_TIME_LOG
(
    LogID                int IDENTITY(1,1) NOT NULL,
    IssueID              int           NOT NULL,
    UserID               int           NOT NULL,
    DateCreated          datetime      NOT NULL,
    StartTime            datetime      NOT NULL,
    EndTime              datetime      NOT NULL,
    TimeSpentHours       float         NOT NULL,
    IsBillable           bit DEFAULT 0 NOT NULL,
    IsTaskMarkedComplete bit DEFAULT 0 NOT NULL,
    Notes                nvarchar(1000) COLLATE Vietnamese_CI_AS NULL,
    TenantID             int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_Issue_TimeLog PRIMARY KEY (LogID)
) GO;


-- SOC_IT_ISSUE_XREFERENCE definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_XREFERENCE GO

CREATE TABLE SOC_IT_ISSUE_XREFERENCE
(
    IssueID     int           NOT NULL,
    ReferenceID int           NOT NULL,
    TenantID    int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_Issue_XReference PRIMARY KEY (IssueID, ReferenceID)
) GO;


-- SOC_IT_ISSUE_XTAG definition

-- Drop table

-- DROP TABLE SOC_IT_ISSUE_XTAG GO

CREATE TABLE SOC_IT_ISSUE_XTAG
(
    TagID    int           NOT NULL,
    IssueID  int           NOT NULL,
    TenantID int DEFAULT 1 NOT NULL
) GO;


-- SOC_IT_MILESTONE definition

-- Drop table

-- DROP TABLE SOC_IT_MILESTONE GO

CREATE TABLE SOC_IT_MILESTONE
(
    MilestoneID         int IDENTITY(1,1) NOT NULL,
    Title               nvarchar(1000) COLLATE Vietnamese_CI_AS NOT NULL,
    Description         nvarchar(2000) COLLATE Vietnamese_CI_AS NULL,
    DateCreated         datetime      NOT NULL,
    DateDue             datetime NULL,
    DateUpdated         datetime NULL,
    StatusID            int           NOT NULL,
    CreatedByUserID     int           NOT NULL,
    ProjectID           int           NOT NULL,
    SpaceID             int           NOT NULL,
    OpenIssuesCount     int NULL,
    ClosedIssuesCount   int NULL,
    PercentageCompleted int NULL,
    TenantID            int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_Milestone PRIMARY KEY (MilestoneID)
) GO;


-- SOC_IT_PROJECT definition

-- Drop table

-- DROP TABLE SOC_IT_PROJECT GO

CREATE TABLE SOC_IT_PROJECT
(
    ProjectID                   int IDENTITY(1,1) NOT NULL,
    ProjectTitle                nvarchar(600) COLLATE Vietnamese_CI_AS NOT NULL,
    ProjectDescription          nvarchar(4000) COLLATE Vietnamese_CI_AS NULL,
    IsActive                    bit           NOT NULL,
    UserID                      int           NOT NULL,
    DateCreated                 datetime      NOT NULL,
    SpaceID                     int           NOT NULL,
    IsTaskProject               bit DEFAULT 0 NOT NULL,
    IsDefaultProject            bit DEFAULT 1 NOT NULL,
    AssignToRolesCSV            varchar(20) COLLATE Vietnamese_CI_AS NULL,
    AdditionalRecipientRolesCSV varchar(20) COLLATE Vietnamese_CI_AS NULL,
    TenantID                    int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_Project PRIMARY KEY (ProjectID)
) GO;


-- SOC_IT_PROJECT_SECTION definition

-- Drop table

-- DROP TABLE SOC_IT_PROJECT_SECTION GO

CREATE TABLE SOC_IT_PROJECT_SECTION
(
    ProjectSectionID   int IDENTITY(1,1) NOT NULL,
    ProjectSectionName nvarchar(100) COLLATE Vietnamese_CI_AS NOT NULL,
    ProjectID          int           NOT NULL,
    TenantID           int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_ProjectSection PRIMARY KEY (ProjectSectionID)
) GO;


-- SOC_IT_PROJECT_XUSER definition

-- Drop table

-- DROP TABLE SOC_IT_PROJECT_XUSER GO

CREATE TABLE SOC_IT_PROJECT_XUSER
(
    ProjectID int           NOT NULL,
    UserID    int           NOT NULL,
    TenantID  int DEFAULT 1 NOT NULL
) GO
CREATE
NONCLUSTERED INDEX IX_Soc_IT_ProjectXUser_ProjectID_UserID ON dbo.SOC_IT_PROJECT_XUSER (  ProjectID ASC  )
	 INCLUDE ( UserID ) 
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ]  GO
 CREATE
NONCLUSTERED INDEX IX_Soc_IT_ProjectXUser_UserID_TenantID ON dbo.SOC_IT_PROJECT_XUSER (  UserID ASC  , TenantID ASC  )
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ]  GO;


-- SOC_IT_RECIPIENTS definition

-- Drop table

-- DROP TABLE SOC_IT_RECIPIENTS GO

CREATE TABLE SOC_IT_RECIPIENTS
(
    RecipientID  int IDENTITY(1,1) NOT NULL,
    IssueID      int NULL,
    UserID       int NULL,
    DateAssigned datetime NULL,
    IsStarred    bit NULL,
    TenantID     int DEFAULT 1 NULL,
    CONSTRAINT PK_Soc_IT_Recipients PRIMARY KEY (RecipientID)
) GO
CREATE
NONCLUSTERED INDEX Soc_IT_Recipients_IssueID_UserID ON dbo.SOC_IT_RECIPIENTS (  IssueID ASC  , UserID ASC  )
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ]  GO;


-- SOC_IT_TASK_ASSIGNEE definition

-- Drop table

-- DROP TABLE SOC_IT_TASK_ASSIGNEE GO

CREATE TABLE SOC_IT_TASK_ASSIGNEE
(
    RecordID         int IDENTITY(1,1) NOT NULL,
    IssueID          int           NOT NULL,
    AssignedToUserID int           NOT NULL,
    TenantID         int DEFAULT 1 NOT NULL,
    CONSTRAINT PK_Soc_IT_Task_Assignee PRIMARY KEY (RecordID)
) GO;


-- SOC_LIKE definition

-- Drop table

-- DROP TABLE SOC_LIKE GO

CREATE TABLE SOC_LIKE
(
    LIKE_ID      int IDENTITY(1,1) NOT NULL,
    ENTITY_ID    int      NOT NULL,
    ENTITY_TYPE  int      NOT NULL,
    USER_ID      int      NOT NULL,
    DATE_CREATED datetime NOT NULL,
    IS_LIKE      bit      NOT NULL,
    CONSTRAINT PK_SOC_LIKE PRIMARY KEY (LIKE_ID)
) GO;