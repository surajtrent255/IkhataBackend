CREATE TABLE users(
   "id" SERIAL PRIMARY KEY,
   "firstname" VARCHAR(50) NOT NULL,
   "lastname" VARCHAR(50) NOT NULL,
   "email" VARCHAR(50) UNIQUE NOT NULL,
   "phone" bigint NOT NULL,
   "password" VARCHAR(600) DEFAULT 0,
   "deleted" BOOLEAN DEFAULT FALSE,
   "create_date" DATE DEFAULT NULL,
   "edit_date" DATE DEFAULT NULL,
   constraint valid_number
         check (phone <= 9999999999)
);

CREATE TABLE "public"."role"(
   "id" SERIAL PRIMARY KEY,
   "role" VARCHAR(15) NOT NULL,
   "description" VARCHAR(15) NOT NULL,
   "deleted" BOOLEAN DEFAULT FALSE
);

CREATE TABLE user_role (
  id SERIAL,
  user_id int NOT NULL,
  role_id int NOT NULL,
  company_id int DEFAULT NULL,
  status BOOLEAN DEFAULT TRUE,
   deleted BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (user_id,role_id,id)
);
CREATE TABLE  token (
  id SERIAL NOT NULL ,
  token varchar(300) NOT NULL,
  token_type varchar(30) NOT NULL,
  revoked boolean NOT NULL DEFAULT false,
  expired boolean NOT NULL DEFAULT false,
   deleted BOOLEAN DEFAULT FALSE,

  user_id int NOT NULL,
  PRIMARY KEY (id)
) ;

CREATE TABLE  company (
  company_id SERIAL NOT NULL ,
  name varchar(50) NOT NULL,
  description text NOT NULL,
  pan_no bigint NOT NULL ,
  state int Default NULL  ,
  district varchar(50) NOT NULL ,
  mun_vdc varchar(50) NOT NULL ,
  ward_no int NOT NULL ,
  deleted BOOLEAN DEFAULT FALSE,
   customer boolean default false,
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
  status boolean DEFAULT TRUE,
  PRIMARY KEY (id)
);

CREATE TABLE "product"(
   "id" SERIAL PRIMARY KEY ,
   "name"  varchar(250) NOT NULL ,
   "description" TEXT  NOT NULL,
   "selling_price" REAL NOT NULL,
   "cost_price" REAL NOT NULL,
   "create_date" date DEFAULT CURRENT_DATE NOT NULL,
   "update_date" timestamp default current_timestamp NOT NULL,
   "user_id" integer NOT NULL,
   "company_id" integer NOT NULL,
   "branch_id" integer not null,
   "seller_id" integer NOT NULL,
   "category_id" integer NOT NULL,
    "barcode" varchar(250) not null,
    "discount" real default 0.0 not null,
    "tax" real default 13.0 not null,
    "deleted" boolean DEFAULT FALSE NOT NULL
--       FOREIGN KEY(user_id)
--       REFERENCES users(id),
--       FOREIGN KEY(category_id)
--       REFERENCES category(id)
--     ,
--     FOREIGN KEY(company_id)
--     REFERENCES company(id)
);


CREATE TABLE "public"."category"(
   "id" serial primary key,
   "name" varchar(250) NOT NULL,
   "description" text,
   "parent_id" integer NOT NULL,
   "user_id" integer NOT NULL,
   "company_id" integer NOT NULL,
   "branch_id" integer not null,
   "create_date" date DEFAULT CURRENT_DATE NOT NULL,
   "edit_date" timestamp  DEFAULT current_timestamp NOT NULL,
    "deleted" boolean default false not null
);



create table sales_bill (
    id serial not null,
    fiscal_year varchar(50) not null,
    bill_no varchar(50)  ,
    customer_id int not null,
    customer_name varchar(50),
    customer_pan varchar(50) ,
    bill_date Date not null,
    amount real not null,
    discount real not null,
    discount_approach int not null,
    taxable_amount real not null,
    tax_amount real not null,
    total_amount real not null,
    sync_with_ird boolean default false not null,
    is_bill_printed boolean default false not null,
    print_count int default 0 not null;
    is_bill_active boolean default false not null,
    printed_time varchar ,
    entered_by varchar(50) not null,
    printed_by varchar(50) ,
    is_realtime boolean not null,
    payment_method varchar(50) not null,
    vat_refund_amount real ,
    transaction_id varchar(50) ,
    status boolean default true not null,
    company_id int not null,
    branch_id int not null,
    draft boolean default false,
    tax_approach int not null,
    customer_search_method int not null,
    sale_type int not null
);


CREATE TABLE "sales_bill_detail"(
   "id" SERIAL PRIMARY KEY ,
   "product_id" integer NOT NULL,
   "qty" real NOT NULL,
   "date" date DEFAULT CURRENT_DATE NOT NULL,
   "discount_per_unit" real NOT NULL,
   "rate" real NOT NULL,
   "bill_id" int NOT NULL,
   "company_id" integer NOT NULL,
   "branch_id" int not null,
   "tax_rate" int not null,
   "row_total" bigint not null
--       FOREIGN KEY(bill_id)
--       REFERENCES bill(id)
--     ,
--     FOREIGN KEY(company_id)
--     REFERENCES company(id)
);

create table bill_no_generator (
    id serial primary key,
    fiscal_year varchar(50) not null,
    bill_no int not null,
    active boolean not null,
    company_id int not null,
    branch_id int not null
);

create table purchase_bill (
    fiscal_year varchar(50) not null,
    purchase_bill_no int  not null,
	seller_id int  not null,
	company_id int not null,
	branch_id int not null,
    seller_name varchar(50) not null,
    seller_pan varchar(50) not null,
    bill_date Date default current_date not null,
    amount real not null,
    discount real not null,
    taxable_amount real not null,
    tax_amount real not null,
    total_amount real not null,
    sync_with_ird boolean default false not null,
    is_bill_printed boolean default false not null,
    is_bill_active boolean default false not null,
    printed_time varchar(50) ,
    entered_by varchar(50) not null,
    printed_by int ,
    is_realtime boolean not null,
    payment_method varchar(50) not null,
    vat_refund_amount real ,
    transaction_id varchar(50) ,
    status boolean default true not null,
	user_id int not null
);

CREATE TABLE "purchase_bill_detail"(
   "id" SERIAL PRIMARY KEY ,
   "product_id" integer NOT NULL,
   "qty" real NOT NULL,
   "date" date DEFAULT CURRENT_DATE NOT NULL,
   "discount_per_unit" real NOT NULL,
   "rate" real NOT NULL,
   "purchase_bill_id" integer NOT NULL,
   "company_id" integer NOT NULL,
   "branch_id" integer not null
--       FOREIGN KEY(bill_id)
--       REFERENCES bill(id)
--     ,
--     FOREIGN KEY(company_id)
--     REFERENCES company(id)
);


create table stock (
    id serial primary key,
    product_id int not null,
    qty int not null,
    company_id int not null,
    branch_id int not null,
    create_date date default current_date,
    update_date timestamp default current_timestamp,
    deleted boolean default false
    );

    CREATE TABLE  branch (
      id SERIAL NOT NULL ,
      company_id int NOT NULL,
      name varchar(50) NOT NULL,
      abbrv varchar(20) NOT NULL,
      description text NOT NULL,
      pan_no bigint NOT NULL ,
      state int Default NULL  ,
      district varchar(50) NOT NULL ,
      mun_vdc varchar(50) NOT NULL ,
      ward_no int NOT NULL ,
      deleted BOOLEAN DEFAULT FALSE,

       phone bigint DEFAULT NULL,
      PRIMARY KEY (id),
    	constraint valid_number
          check (phone <= 9999999999)
    ) ;


CREATE TABLE user_branch (
  id SERIAL,
  user_id int NOT NULL,
  branch_id int NOT NULL,
  company_id int NOT NULL,
  status boolean DEFAULT TRUE,
  create_date DATE DEFAULT NULL,
  PRIMARY KEY (id)
);
create table vat_rate_type (
	id serial not null,
	vate_rate varchar(50) not null,
	vat_rate_num real not null
);




CREATE TABLE  districts (
  district_id   SERIAL ,
  district_name varchar(50) ,
  province_id int NOT NULL,
  disabled bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (district_id)
) ;


CREATE TABLE province (
  province_id SERIAL,
  province_name varchar(50) ,
  disabled bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (province_id)
);

CREATE TABLE municipality (
  municipality_id SERIAL,
  municipality_name varchar(150)   NOT NULL,
  province_id int NOT NULL,
  district_id int NOT NULL,
  disabled bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (municipality_id)
);

-- DROP TABLE IF EXISTS public.bank;
CREATE TABLE  bank (
bank_id  SERIAL ,
company_id  int  NOT NULL ,
branch_id  int NOT NULL,
bank_name  VARCHAR(50) NOT NULL ,
account_number BIGINT NOT NULL,
initial_amount real ,
create_date date default current_date,
account_type   CHAR(50)
);
-- DROP TABLE IF EXISTS public.bank_deposit;
CREATE TABLE  bank_deposit (
deposit_id SERIAL ,
bank_id  int  NOT NULL ,
company_id  int  NOT NULL ,
branch_id  int NOT NULL,
deposit_amount real NOT NULL,
deposit_type VARCHAR(50) NOT NULL,
submit_date date default current_date,
cheque_number  VARCHAR(50)
);
-- DROP TABLE IF EXISTS public.bank_withdraw;
CREATE TABLE  bank_withdraw(
withdraw_id SERIAL,
company_id  int  NOT NULL ,
branch_id  int NOT NULL,
withdraw_amount real NOT NULL,
withdraw_type VARCHAR(50) NOT NULL,
withdraw_date date default current_date,
cheque_number  VARCHAR(50)
);


-- DROP TABLE IF EXISTS public.type_of_payment;
CREATE TABLE type_of_payment(
id SERIAL,
name VARCHAR(50)
);

-- DROP TABLE IF EXISTS public.account_type;
CREATE TABLE account_type(
id SERIAL,
name varchar(50)
);

CREATE TABLE bank_list(
id SERIAL,
name varchar(50),
location VARCHAR(100)
)

create table public.loan (
	id  serial not null,
	company_id int not null,
	branch_id int not null,
	bank_id int not null,
	lender_id int not null,
	loan_type int not null,
	loan_number int not null,
	loan_name int not null,
	loan_amount real not null,
	received_amount real not null,
	service_charge real ,
	other_expenses real,
	deleted boolean default false not null
);

create table loan_type (
	id serial not null,
	loan_type_index int not null,
	loan_type varchar(40) not null
);

create table loan_name(
	id serial not null,
	loan_name_index int not null,
	loan_name varchar(40) not null

);

create table sale_type(
	id serial not null,
	sale_type_index int not null,
	sale_type varchar(50) not null
)

