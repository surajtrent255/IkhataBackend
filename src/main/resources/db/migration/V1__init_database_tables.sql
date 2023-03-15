CREATE TABLE "public.users"(
   "id" SERIAL PRIMARY KEY,
   "firstname" VARCHAR(50) NOT NULL,
   "lastname" VARCHAR(50) NOT NULL,
   "email" VARCHAR(50) UNIQUE NOT NULL,
   "password" VARCHAR(600) NOT NULL,
   "deleted" BOOLEAN DEFAULT FALSE
);

CREATE TABLE "public"."role"(
   "id" SERIAL PRIMARY KEY,
   "role" VARCHAR(15) NOT NULL,
   "description" VARCHAR(15) NOT NULL
);

CREATE TABLE user_role (
  id SERIAL,
  company_id int NOT NULL,
  user_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id,role_id,id)
);
CREATE TABLE  token (
  id SERIAL NOT NULL ,
  token varchar(300) NOT NULL,
  token_type varchar(30) NOT NULL,
  revoked boolean NOT NULL DEFAULT false,
  expired boolean NOT NULL DEFAULT false,
  user_id int NOT NULL,
  PRIMARY KEY (id)
) ;

CREATE TABLE  company (
  company_id SERIAL NOT NULL ,
  name varchar(50) NOT NULL,
  description text NOT NULL,
  pan_no bigint NOT NULL ,
  state int Default NULL  ,
  zone varchar(50) NOT NULL ,
  district varchar(50) NOT NULL ,
  mun_vdc varchar(50) NOT NULL ,
  ward_no int NOT NULL ,
   phone bigint DEFAULT NULL,
  PRIMARY KEY (company_id),
	constraint valid_number
      check (phone <= 9999999999)
) ;

--CREATE TABLE user_company (
--  id SERIAL,
--  company_id int NOT NULL,
--  user_id int NOT NULL,
--  PRIMARY KEY (id),
--	CONSTRAINT fk_user_company
--   FOREIGN KEY(company_id)
--      REFERENCES company(compnay_id)
--);

CREATE TABLE user_company (
  id SERIAL,
  company_id int NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (id)
);