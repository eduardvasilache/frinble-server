CREATE TABLE "users" (
    "id"                                SERIAL          PRIMARY KEY,
    "email"                             VARCHAR(256)    NULL UNIQUE,
    "first_name"                        VARCHAR(100)    NULL,
    "last_name"                         VARCHAR(100)    NULL,
    "born_at"                           TIMESTAMPTZ     NULL,
    "avatar_url"                        VARCHAR(100)    NULL,
    "password_hash"                     VARCHAR(256)    NULL,
    "email_confirmation_token"          VARCHAR(256)    NULL,
    "reset_password_token"              VARCHAR(256)    NULL,
    "reset_password_token_expires_at"   TIMESTAMPTZ     NULL,
    "status"                            INT             NOT NULL,
    "created_at"                        TIMESTAMPTZ     NOT NULL,
    "updated_at"                        TIMESTAMPTZ     NOT NULL,
    "last_login_at"                     TIMESTAMPTZ     NULL
);

CREATE TABLE "user_relationships" (
    "user_id_1"                         INT             NOT NULL,
    "user_id_2"                         INT             NOT NULL,
    "status"                            INT             NOT NULL,
    "last_action_user_id"               INT             NOT NULL,

    CONSTRAINT "user_relationships_user_id_1_user_id_2_status_PK" PRIMARY KEY ("user_id_1", "user_id_2", "status"),
    CONSTRAINT "user_relationships_user_id_1_FK" FOREIGN KEY ("user_id_1") REFERENCES "users" ("id"),
    CONSTRAINT "user_relationships_user_id_2_FK" FOREIGN KEY ("user_id_2") REFERENCES "users" ("id"),
    CONSTRAINT "user_relationships_last_action_user_id_FK" FOREIGN KEY ("last_action_user_id") REFERENCES "users" ("id")
);

CREATE TABLE "user_groups" (
    "id"                                SERIAL          PRIMARY KEY,
    "owner_user_id"                     INT             NOT NULL,
    "name"                              VARCHAR(256)    NOT NULL,

    CONSTRAINT "user_groups_owner_user_id_FK" FOREIGN KEY ("owner_user_id") REFERENCES "users" ("id")
);

CREATE TABLE "user_group_members" (
    "user_group_id"                     INT             NOT NULL,
    "user_id"                           INT             NOT NULL,
    "added_at"                          TIMESTAMPTZ     NOT NULL,

    CONSTRAINT "user_group_members_user_group_id_user_id_PK" PRIMARY KEY ("user_group_id", "user_id"),
    CONSTRAINT "user_group_members_user_group_id_FK" FOREIGN KEY ("user_group_id") REFERENCES "user_groups" ("id"),
    CONSTRAINT "user_group_members_user_id_FK" FOREIGN KEY ("user_id") REFERENCES "users" ("id")
);

CREATE TABLE "activities" (
    "id"                                SERIAL          PRIMARY KEY,
    "owner_user_id"                     INT             NOT NULL,
    "starts_at"                         TIMESTAMPTZ     NOT NULL,
    "ends_at"                           TIMESTAMPTZ     NOT NULL,
    "type"                              INT             NULL,
    "location"                          INT             NULL,
    "slots"                             INT             NULL,

    CONSTRAINT "activities_owner_user_id_FK" FOREIGN KEY ("owner_user_id") REFERENCES "users" ("id")
);

CREATE TABLE "activity_members" (
    "activity_id"                       INT             NOT NULL,
    "user_id"                           INT             NOT NULL,
    "status"                            INT             NOT NULL,

    CONSTRAINT "activity_members_PK" PRIMARY KEY ("activity_id", "user_id"),
    CONSTRAINT "ActivitiesMember_activity_id_FK" FOREIGN KEY ("activity_id") REFERENCES "activities" ("id"),
    CONSTRAINT "ActivitiesMember_user_id_FK" FOREIGN KEY ("user_id") REFERENCES "users" ("id")
);