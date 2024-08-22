-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
CREATE TABLE `tbl_user`
(
    `user_id`    int                                                    NOT NULL AUTO_INCREMENT COMMENT 'Auto increment PK',
    `login_name` varchar(250)                                           NOT NULL COMMENT 'Login name',
    `password`   varchar(255)                                           NOT NULL COMMENT 'Login password',
    `mobile`     varchar(20)                                            NOT NULL COMMENT 'User mobile',
    `email`      varchar(100)                                           NOT NULL COMMENT 'User email',
    `nick_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'User nick name',
    `is_deleted` char(1)                                                NOT NULL COMMENT '0-active,1-soft delete',
    `rec_crt_ts` timestamp                                              NOT NULL COMMENT 'Record create time',
    `rec_upd_ts` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT 'Record update time',
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX login_name (login_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=COMPACT;


