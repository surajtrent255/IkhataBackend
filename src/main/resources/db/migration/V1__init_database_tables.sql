-- DROP TABLE IF EXISTS split_product_log;
-- DROP TABLE IF EXISTS split_product;
-- DROP TABLE IF EXISTS unit;
-- DROP TABLE IF EXISTS sale_type;
-- DROP TABLE IF EXISTS user_feature;
-- DROP TABLE IF EXISTS feature_control;
-- DROP TABLE IF EXISTS counter;
-- DROP TABLE IF EXISTS loan_name;
-- DROP TABLE IF EXISTS loan_type;
-- DROP TABLE IF EXISTS loan;
-- DROP TABLE IF EXISTS bank_list;
-- DROP TABLE IF EXISTS account_type;
-- DROP TABLE IF EXISTS type_of_payment;
-- DROP TABLE IF EXISTS bank_withdraw;
-- DROP TABLE IF EXISTS bank_deposit;
-- DROP TABLE IF EXISTS bank;
-- DROP TABLE IF EXISTS receipt;
-- DROP TABLE IF EXISTS fixed_assets;
-- DROP TABLE IF EXISTS expenses;
-- DROP TABLE IF EXISTS payment_mode;
-- DROP TABLE IF EXISTS post_date_check;
-- DROP TABLE IF EXISTS payment;
-- DROP TABLE IF EXISTS municipality;
-- DROP TABLE IF EXISTS province;
-- DROP TABLE IF EXISTS districts;
-- DROP TABLE IF EXISTS user_branch;
-- DROP TABLE IF EXISTS branch;
-- DROP TABLE IF EXISTS stock;
-- DROP TABLE IF EXISTS purchase_bill_detail;
-- DROP TABLE IF EXISTS purchase_bill;
-- DROP TABLE IF EXISTS bill_no_generator;
-- DROP TABLE IF EXISTS sales_bill_detail;
-- DROP TABLE IF EXISTS sales_bill;
-- DROP TABLE IF EXISTS category;
-- DROP TABLE IF EXISTS product;
-- DROP TABLE IF EXISTS user_company;
-- DROP TABLE IF EXISTS company;
-- DROP TABLE IF EXISTS token;
-- DROP TABLE IF EXISTS user_company_role;
-- DROP TABLE IF EXISTS user_role;
-- DROP TABLE IF EXISTS user_company;
-- DROP TABLE IF EXISTS role;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS vat_rate_type;
-- DROP TABLE IF EXISTS credit_note;
-- DROP TABLE IF EXISTS credit_note_details;
-- DROP TABLE IF EXISTS user_counter;
-- DROP TABLE IF EXISTS debit_note_details;
-- DROP TABLE IF EXISTS debit_note;
-- DROP TABLE IF EXISTS merge_product;
-- DROP TABLE IF EXISTS merge_product_log;




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

CREATE TABLE role(
   "id" SERIAL PRIMARY KEY,
   "role" VARCHAR(15) NOT NULL,
   "description" VARCHAR(15) NOT NULL,
   "deleted" BOOLEAN DEFAULT FALSE
);

CREATE TABLE user_company_role (
  id SERIAL,
  user_id int NOT NULL,
  role_id int NOT NULL,
  company_id int DEFAULT NULL,
  status BOOLEAN DEFAULT TRUE,
   deleted BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (user_id,role_id,id)
);


CREATE TABLE user_counter(
id SERIAL ,
counter_id INT DEFAULT NULL,
user_id INT DEFAULT NULL,
company_id INT DEFAULT NULL,
branch_id INT DEFAULT NULL,
status BOOLEAN DEFAULT TRUE
);

CREATE TABLE user_role (
  id SERIAL,
  user_id int NOT NULL,
  role_id int NOT NULL,
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
  owner_name VARCHAR(100) DEFAULT NULL ,
  landline_number BIGINT DEFAULT NULL,
  registration_type VARCHAR(50) DEFAULT NULL,
  email VARCHAR(100) DEFAULT NUll,
  description text DEFAULT NULL,
  pan_no bigint UNIQUE NOT NULL ,
  state int Default NULL  ,
  district varchar(50) DEFAULT NULL ,
  mun_vdc varchar(50) DEFAULT NULL ,
  ward_no int DEFAULT NULL ,
  deleted BOOLEAN DEFAULT FALSE,
   customer boolean default false,
   phone bigint  DEFAULT NULL,
   status BOOLEAN DEFAULT TRUE,
   created_date DATE DEFAULT NULL,
   created_date_nepali VARCHAR(20) DEFAULT NULL,
   is_approved BOOLEAN DEFAULT FALSE,
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
--      REFERENCES company(company_id)
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
   "cost_price" REAL ,
   "product_type" boolean Default FALSE NOT NULL,
   "is_average_price" boolean DEFAULT FALSE NOT NULL,
   "rate_percentage" integer NOT Null ,
   "create_date" date DEFAULT CURRENT_DATE NOT NULL,
   "update_date" timestamp default current_timestamp NOT NULL,
   "user_id" integer NOT NULL,
   "company_id" integer NOT NULL,
   "branch_id" integer not null,
   "seller_id" integer ,
   "category_id" integer NOT NULL,
    "barcode" varchar(250),
    "discount" real default 0.0 not null,
    "tax" integer ,
    "tax_approch" integer,
    "unit" VARCHAR(100),
    "quantity_per_unit" integer,
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
    bill_date DATE DEFAULT null,
    bill_date_nepali varchar(50),
    amount real not null,
    discount real not null,
    discount_approach int not null,
    taxable_amount real not null,
    tax_amount real not null,
    total_amount real not null,
    sync_with_ird boolean default false not null,
    is_bill_printed boolean default false not null,
    print_count int default 0 not null,
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
    counter_id int not null,
    draft boolean default false,
    tax_approach int not null,
    customer_search_method int not null,
    sale_type int not null,
    has_abbr boolean not null,
    receipt boolean not null
);


CREATE TABLE "sales_bill_detail"(
   "id" SERIAL PRIMARY KEY ,
   "product_id" integer NOT NULL,
   "qty" real NOT NULL,
   "date" varchar(20)  NOT NULL,
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
    branch_id int not null,
    has_abbr boolean default false not null
);

create table purchase_bill (
    fiscal_year varchar(50) not null,
    purchase_bill_no int  not null ,
	seller_id int  not null,
	company_id int not null,
	branch_id int not null,
    seller_name varchar(50) not null,
    seller_pan varchar(50) not null,
    seller_address varchar(100) not null,
    bill_date Date default current_date not null,
    bill_date_nepali VARCHAR(20) DEFAULT NULL,
    transactional_date VARCHAR(50) DEFAULT NUll,
    transactional_date_nepali VARCHAR(50) DEFAULT NULL,
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
	user_id int not null,
	sale_type int not null,
	transportation real not null,
	transportation_tax_type int not null,
	insurance real not null,
	insurance_tax_type int not null,
	loading real not null,
	loading_tax_type int,
	other real,
	other_tax_type int

);

CREATE TABLE "purchase_bill_detail"(
   "id" SERIAL PRIMARY KEY ,
   "product_id" integer NOT NULL,
   "qty" real NOT NULL,
   "tax_type_id" int NOT NULL,
   "date" date DEFAULT CURRENT_DATE NOT NULL,
   "discount_per_unit" real NOT NULL,
   "rate" real NOT NULL,
   "purchase_bill_id" integer  NOT NULL,
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



CREATE TABLE payment (
  SN SERIAL,
  company_id int DEFAULT NULL,
  party_id int NOT NULL,
  amount REAL NOT NULL,
  payment_mode_id INT NOT NULL,
  check_no bigint  NOT NULL,
  bank_name VARCHAR DEFAULT NULL,
  tds_deducted boolean DEFAULT false,
  post_date_check boolean DEFAULT FALSE,
  branch_id int DEFAULT NULL,
  date DATE DEFAULT NULL,
  nepali_date DATE DEFAULT NULL,
  status boolean DEFAULT TRUE,
  PRIMARY KEY (SN)
);


CREATE TABLE post_date_check(

	Sn SERIAL,
	payment_id int NOT NULL,
	pay_date DATE DEFAULT NULL,
	pay_date_nepali DATE DEFAULT NULL,
	status BOOLEAN DEFAULT TRUE,
	PRIMARY KEY(Sn)
);

CREATE TABLE payment_mode(
	id SERIAL,
	mode_name 	VARCHAR(50) NOT NULL,
	description VARCHAR(50) DEFAULT NULL,
	status BOOLEAN DEFAULT TRUE,
	PRIMARY KEY(id)

);





CREATE TABLE expenses (
  SN SERIAL,
  company_id int DEFAULT NULL,
  amount REAL NOT NULL,
  topic int not null,
  bill_no VARCHAR(50),
  pay_to BIGINT not null,
  date DATE DEFAULT NULL,
  nepali_date VARCHAR(20) DEFAULT NULL,
  branch_id int DEFAULT NULL,
  status boolean DEFAULT TRUE,
  PRIMARY KEY (SN)
);


CREATE TABLE fixed_assets (
  SN SERIAL,
  company_id int DEFAULT NULL,
  name VARCHAR(50),
	amount REAL NOT NULL,
	date DATE DEFAULT NULL,
	nepali_date VARCHAR(50) DEFAULT NULL,
	bill_no VARCHAR(50),
	cash REAL ,
	loan VARCHAR(100),
	loan_id INT,
  branch_id int DEFAULT NULL,
  status boolean DEFAULT TRUE,
  PRIMARY KEY (SN)
);


CREATE TABLE receipt (
  SN SERIAL,
  company_id int DEFAULT NULL,
  party_id INT NOT  NULL,
	amount REAL NOT NULL,
	date DATE DEFAULT NULL,
	nepali_date VARCHAR(50) DEFAULT NULL,
	mode_id INT DEFAULT NULL,
	tds_deducted_amount REAL NOT NULL ,
	post_date_check BOOLEAN DEFAULT FALSE,
  branch_id int DEFAULT NULL,
  status boolean DEFAULT TRUE,
  PRIMARY KEY (SN)
);


CREATE TABLE  bank (
bank_id  SERIAL ,
company_id  int  NOT NULL ,
branch_id  int NOT NULL,
bank_type_id  int NOT NULL ,
account_number BIGINT NOT NULL,
initial_amount real ,
create_date date default current_date,
account_type  int not null
);

CREATE TABLE  bank_deposit (
deposit_id SERIAL ,
bank_id  int  NOT NULL ,
company_id  int  NOT NULL ,
branch_id  int NOT NULL,
deposit_amount bigint NOT NULL,
deposit_type VARCHAR(50) NOT NULL,
submit_date date default current_date,
cheque_number  VARCHAR(50),
deposited_by bigint not null,
nepali_date varchar(50) not null,
english_date date not null
);

CREATE TABLE  bank_withdraw(
withdraw_id SERIAL,
bank_id int ,
company_id  int  NOT NULL ,
branch_id  int NOT NULL,
withdraw_amount bigint NOT NULL,
withdraw_type VARCHAR(50) NOT NULL,
withdraw_date date default current_date,
cheque_number  VARCHAR(50),
nepali_date varchar(50) not null,
english_date date not null
);



CREATE TABLE type_of_payment(
id SERIAL,
name VARCHAR(50)
);


CREATE TABLE account_type(
id SERIAL,
name varchar(50)
);

CREATE TABLE bank_list(
id SERIAL,
name varchar(50),
location VARCHAR(100)
);

create table public.loan (
	id  serial not null,
	company_id int not null,
	branch_id int not null,
	bank_id int not null,
	lender_id int ,
	loan_type int ,
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


CREATE TABLE counter(
	id SERIAL,
	name VARCHAR(50) DEFAULT NULL,
	company_id INT NOT NULL,
	branch_id INT NOT NULL ,
	date DATE DEFAULT NULL,
	status boolean DEFAULT TRUE
);


CREATE TABLE feature_control(
id SERIAL,
	feature VARCHAR(50) NOT NULL,
	status BOOLEAN DEFAULT TRUE,
	feature_group INT DEFAULT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE user_feature(
	id SERIAL,
	feature_id INT DEFAULT NULL,
	user_id INT DEFAULT NULL,
	company_id INT DEFAULT NULL,
	status BOOLEAN DEFAULT TRUE,
	PRIMARY KEY(id)

);

create table sale_type(
	id serial not null,
	sale_type_index int not null,
	sale_type varchar(50) not null
);




create  table unit (
id serial not null ,
name VARCHAR(50) not null
);

CREATE TABLE split_product (
    id SERIAL PRIMARY KEY,
    product_id INT ,
    product_name VARCHAR(100),
    qty INT,
	split_qty INT,
    total_qty INT,
    unit VARCHAR(50),
    price REAL,
    updated_product_id INT,
    updated_product_name VARCHAR(100),
    company_id INT,
    branch_id INT
);
CREATE TABLE split_product_log(
    id SERIAL PRIMARY KEY,
    product_id INT ,
    product_name VARCHAR(100),
    qty INT,
	split_qty INT,
    total_qty INT,
    unit VARCHAR(50),
    price REAL,
    updated_product_id INT,
    updated_product_name VARCHAR(100),
    company_id INT,
    branch_id INT
);
CREATE TABLE merge_product_log(
    id SERIAL PRIMARY KEY,
    product_id INT ,
    product_name VARCHAR(100),
    qty INT,
	merge_qty INT,
    total_qty INT,
    unit VARCHAR(50),
    price REAL,
    updated_product_id INT,
    updated_product_name VARCHAR(100),
    company_id INT,
    branch_id INT
);

CREATE Table merge_product(
id serial PRIMARY KEY,
product_id INT,
product_name VARCHAR(250),
split_product_id INT,
split_product_name VARCHAR(200),
price INT,
qty INT,
merge_qty INT,
unit VARCHAR(250),
tax INT,
company_id INT,
branch_id INT
);

CREATE TABLE debit_note(
    id SERIAL PRIMARY KEY,
	pan_number BIGINT DEFAULT NULL,
	receiver_name VARCHAR(100) DEFAULT NULL,
	receiver_address VARCHAR(100) DEFAULT NULL,
	bill_number BIGINT DEFAULT NULL,
	date DATE DEFAULT NULL,
	nepali_date VARCHAR DEFAULT NULL,
	fiscal_year VARCHAR DEFAULT NULL,
	total_amount REAL DEFAULT NULL,
	total_tax REAL DEFAULT NULL,
	company_id INT NOT NULL,
	branch_id INT NOT NULL
);

CREATE TABLE debit_note_details(
    id SERIAL PRIMARY KEY,
    serial_number  BIGINT DEFAULT NULL,
	product_id INT DEFAULT NULL,
	product_name VARCHAR(100),
	product_unit VARCHAR DEFAULT NUll,
	debit_reason TEXT DEFAULT NULL,
	debit_amount REAL DEFAULT NULL,
	total_debit_amount REAL DEFAULT NUll,
	debit_tax_amount REAL DEFAULT NULL,
	company_id INT NOT NULL,
	branch_id INT NOT NULL,
	bill_number BIGINT NOT NULL
);

CREATE TABLE credit_note(
    id SERIAL PRIMARY KEY,
	pan_number BIGINT DEFAULT NULL,
	customer_name VARCHAR(100) DEFAULT NULL,
	customer_address VARCHAR(100) DEFAULT NULL,
	bill_number VARCHAR(100) DEFAULT NULL,
	date DATE DEFAULT NULL,
	nepali_date VARCHAR DEFAULT NULL,
	total_amount REAL DEFAULT NULL,
	total_tax REAL DEFAULT NULL,
	company_id INT NOT NULL,
	branch_id INT NOT NULL,
	fiscal_year VARCHAR DEFAULT NULL
);


CREATE TABLE credit_note_details(
    id SERIAL PRIMARY KEY,
    serial_number BIGINT DEFAULT NULL ,
	product_id INT DEFAULT NULL,
	product_name VARCHAR(100),
	product_qty INT DEFAULT NULL,
	product_unit VARCHAR DEFAULT NULL,
	credit_reason TEXT DEFAULT NULL,
	credit_amount REAL DEFAULT NULL,
	total_credit_amount REAL DEFAULT NUll,
	credit_tax_amount REAL DEFAULT NULL,
	company_id INT NOT NULL,
	branch_id INT NOT NULL,
	bill_number VARCHAR(100)   NOT NULL
);




CREATE TABLE company_logo(
id SERIAL,
company_id INT DEFAULT NULL,
image_name VARCHAR DEFAULT NULL,
PRIMARY KEY(id)
);

create table company_label_data(
id SERIAL,
	label_id INT,
	text TEXT,
	company_id INT
);


create table company_label(
     id SERIAL,
	name VARCHAR(100)
);

create table receipt_no_generator (
    id serial primary key,
    fiscal_year varchar(50) not null,
    receipt_no int not null,
    active boolean not null,
    company_id int not null,
    branch_id int not null
);

create table sales_receipt(
	id serial primary key,
	receipt_no int not null,
	receipt_date date not null,
	receipt_amount serial ,
	bill_no varchar(40) not null,
	has_abbr boolean not null,
	company_id int not null,
	branch_id int not null
);


CREATE TABLE purchase_additional_info (
	id SERIAL PRIMARY KEY,
	purchase_bill_id INT DEFAULT NULL,
    expense_id INT ,
    supplier_pan BIGINT,
    supplier_name VARCHAR(50),
    invoice_date VARCHAR(20),
    invoice_no VARCHAR(50),
    amount REAL,
    vat_bill BOOLEAN,
    company_id INT,
    branch_id INT,
    bill_no VARCHAR(50)
);


CREATE TABLE purchase_additional_attributes(
id SERIAL,
	attribute_name VARCHAR(50),
	company_id INT
);


create table deposit_withdraw_types (
	id int not null,
	name varchar not null
)


create table employee (
	sn serial,
	name varchar(50),
	designation int not null,
	panNo int unique,
	salary real not null,
	employee_type int not null,
	married boolean not null,
	company_id int not null,
	branch_id int not null,
	join_date Date not null,
	join_date_nepali varchar(50) not null,
	entry_date Date default current_date,
	deleted boolean default false
);

create table otherIncome (
	sn serial,
	source int not null,
	amount real not null,
	date Date not null,
	company_id int not null,
	deleted boolean default false
);


create table otherIncomeSource(
	id serial,
	name varchar(50) not null
);

create table designation (
	id serial,
	title varchar(50) not null,
	company_id int not null,
	branch_id int not null,
	deleted boolean default false not null
);


create table employee_type(
	id serial,
	name varchar(50)
);

CREATE TABLE loan_repay(
id serial,
	loan_no INT DEFAULT NULL,
	loan_name VARCHAR(50) DEFAULT NULL,
	amount REAL DEFAULT 0 ,
	interest BOOLEAN DEFAULT FALSE,
	penalty BOOLEAN DEFAULT FALSE,
	service BOOLEAN DEFAULT FALSE,
	principle BOOLEAN DEFAULT FALSE,
	date DATE DEFAULT CURRENT_DATE,
	nepali_date VARCHAR(20) DEFAULT NULL,
	company_id INT NOT NULL,
	branch_id INT

)

CREATE TABLE other_income (
  sn SERIAL PRIMARY KEY,
  source INTEGER,
  amount NUMERIC,
  date_english DATE,
  date_nepali VARCHAR(255),
  company_id INTEGER,
  branch_id INTEGER,
  deleted BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE other_income_source (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  company_id INTEGER,
  branch_id INTEGER
);


create TABLE expense_topics (
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    company_id INTEGER,
    branch_id INTEGER
);


create table fiscal_year (
    id serial NOT null,
    fiscal_year varchar(10) not null,
    first_quarter_start Date not null,
    first_quarter_end Date not null,
    second_quarter_start Date not null,
    second_quarter_end Date not null,
    third_quarter_start Date not null,
    third_quarter_end Date not null,
    active boolean not null;
);

create table forgot_password(
    id serial,
    useremail varchar not null,
    token varchar(400) not null,
    status boolean not null
)