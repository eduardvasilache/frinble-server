CREATE TABLE "UserAccounts" (
    "Id"                            SERIAL          PRIMARY KEY,
    "Email"                         VARCHAR(256)    NULL,
    "FirstName"                     VARCHAR(100)    NULL,
    "LastName"                      VARCHAR(100)    NULL,
    "Birthdate"                     TIMESTAMP       NULL,
    "ImageUrl"                      VARCHAR(100)    NULL,
    "CreatedAt"                     TIMESTAMP       NOT NULL,
    "UpdatedAt"                     TIMESTAMP       NOT NULL,
    "LastLoginAt"                   TIMESTAMP       NULL,
    "AccountStatus"                 INT             NOT NULL,
    "PasswordHash"                  VARCHAR(256)    NULL,
    "PasswordSalt"                  VARCHAR(50)     NULL,
    "PasswordHashAlgorith"          VARCHAR(50)     NULL,
    "EmailConfirmationToken"        VARCHAR(256)    NULL,
    "ResetPasswordToken"            VARCHAR(256)    NULL,
    "ResetPasswordTokenExpiresAt"   TIMESTAMP       NULL,
    "FacebookId"                    VARCHAR(256)    NULL,
    "FacebookToken"                 VARCHAR(256)    NULL,
    "GoogleId"                      VARCHAR(256)    NULL,
    "GoogleToken"                   VARCHAR(256)    NULL
);

CREATE TABLE "UserRelationships" (
    "UserId1"                       INT             NOT NULL,
    "UserId2"                       INT             NOT NULL,
    "Status"                        INT             NOT NULL,
    "LastActionUserId"              INT             NOT NULL,

    CONSTRAINT "UserRelationships_UserId1_UserId2_Status_PK" PRIMARY KEY ("UserId1", "UserId2", "Status"),
    CONSTRAINT "UserRelationships_UserId1_FK" FOREIGN KEY ("UserId1") REFERENCES "UserAccounts" ("Id"),
    CONSTRAINT "UserRelationships_UserId2_FK" FOREIGN KEY ("UserId2") REFERENCES "UserAccounts" ("Id"),
    CONSTRAINT "UserRelationships_LastActionUserId_FK" FOREIGN KEY ("LastActionUserId") REFERENCES "UserAccounts" ("Id")
);

CREATE TABLE "UserGroups" (
    "Id"                            SERIAL             PRIMARY KEY,
    "OwnerUserId"                   INT             NOT NULL,
    "Name"                          VARCHAR(256)    NOT NULL,

    CONSTRAINT "UserGroups_OwnerUserId_FK" FOREIGN KEY ("OwnerUserId") REFERENCES "UserAccounts" ("Id")
);

CREATE TABLE "UserGroupsMembers" (
    "UserGroupId"                   INT             NOT NULL,
    "UserId"                        INT             NOT NULL,
    "AddedAt"                       TIMESTAMP       NOT NULL,

    CONSTRAINT "UserGroupsMembers_UserGroupId_UserId_PK" PRIMARY KEY ("UserGroupId", "UserId"),
    CONSTRAINT "UserGroupsMembers_UserGroupId_FK" FOREIGN KEY ("UserGroupId") REFERENCES "UserGroups" ("Id"),
    CONSTRAINT "UserGroupsMembers_UserId_FK" FOREIGN KEY ("UserId") REFERENCES "UserAccounts" ("Id")
);

CREATE TABLE "Activities" (
    "Id"                            SERIAL          PRIMARY KEY,
    "OwnerUserId"                   INT             NOT NULL,
    "StartTime"                     TIMESTAMP       NOT NULL,
    "EndTime"                       TIMESTAMP       NOT NULL,
    "Type"                          INT             NULL,
    "Location"                      INT             NULL,
    "Slots"                         INT             NULL,

    CONSTRAINT "Activities_OwnerUserId_FK" FOREIGN KEY ("OwnerUserId") REFERENCES "UserAccounts" ("Id")
);

CREATE TABLE "ActivitiesMembers" (
    "ActivityId"                    INT             NOT NULL,
    "UserId"                        INT             NOT NULL,
    "Status"                        INT             NOT NULL,

    CONSTRAINT "ActivitiesMembers_PK" PRIMARY KEY ("ActivityId", "UserId"),
    CONSTRAINT "ActivitiesMember_ActivityId_FK" FOREIGN KEY ("ActivityId") REFERENCES "Activities" ("Id"),
    CONSTRAINT "ActivitiesMember_UserId_FK" FOREIGN KEY ("UserId") REFERENCES "UserAccounts" ("Id")
);