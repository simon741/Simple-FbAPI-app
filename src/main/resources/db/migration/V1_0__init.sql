CREATE TABLE FB_USER (
  id BIGINT auto_increment primary key,
  first_name varchar(255),
  last_name varchar(255),
  gender varchar(255),
  fb_id varchar(255)
);

CREATE TABLE LIKED_PAGE (
  id BIGINT auto_increment primary key,
  name varchar(255),
  description varchar(255),
  fb_id varchar(255),
  fb_user_id BIGINT
);

