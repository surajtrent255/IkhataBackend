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
   "role_nepali" VARCHAR(15) NOT NULL
);

CREATE TABLE user_role (
  user_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id,role_id)
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
