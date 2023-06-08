INSERT INTO role (id, role, description) VALUES
	(1, 'ADMIN', ''),
	(2, 'USER', ''),
	(3, 'AUDITOR',''),
	(4, 'ACCOUNTANT',''),
	(5, 'STAFF',''),
	(6, 'SUPER_ADMIN','');

    	insert into bill_no_generator
        (
            fiscal_year,
            bill_no,
            active,
            company_id,
            branch_id,
            has_abbr
        ) values
        (
            '2079/80',
            1,
            true,
            1,
            1,
            false
        ),
        (
          '2079/80',
          1,
          true,
          1,
          1,
          true
        );

INSERT INTO public.vat_rate_type(
	id, vate_rate, vat_rate_num)
	VALUES (1, 'NO VAT', 0),
(2, 'Inclusive 13% VAT', 13),
(3, 'Exclusive 13% VAT', 13);

insert into loan_type (loan_type_index,loan_type) values (1, 'TERM'),
(2,'OD');
insert into loan_name(loan_name_index, loan_name) values(1, 'HOME_LOAN'),
(2, 'LAND_LOAN');


INSERT INTO districts (district_id, district_name, province_id, disabled) VALUES (1, 'Achham', 7, b'0'),
 (2, 'Arghakhanchi', 5, b'0'),
 (3, 'Baglung', 4, b'0'),
 (4, 'Baitadi', 7, b'0'),
 (5, 'Bajhang', 7, b'0'),
 (6, 'Bajura', 7, b'0'),
 (7, 'Banke', 5, b'0'),
 (8, 'Bara', 2, b'0'),
 (9, 'Bardiya', 5, b'0'),
 (10, 'Bhaktapur', 3, b'0'),
 (11, 'Bhojpur', 1, b'0'),
 (12, 'Chitwan', 3, b'0'),
 (13, 'Dadeldhura', 2, b'0'),
 (14, 'Dailekh', 6, b'0'),
 (15, 'Dang Deukhuri', 5, b'0'),
 (16, 'Darchula', 7, b'0'),
 (17, 'Dhading', 3, b'0'),
 (18, 'Dhankuta', 1, b'0'),
 (19, 'Dhanusa', 2, b'0'),
 (20, 'Dolakha', 3, b'0'),
 (21, 'Dolpa', 6, b'0'),
 (22, 'Doti', 7, b'0'),
 (23, 'Gorkha', 4, b'0'),
 (24, 'Gulmi', 5, b'0'),
 (25, 'Humla', 6, b'0'),
 (26, 'Ilam', 1, b'0'),
 (27, 'Jajarkot', 6, b'0'),
 (28, 'Jhapa', 1, b'0'),
 (29, 'Jumla', 6, b'0'),
 (30, 'Kailali', 7, b'0'),
 (31, 'Kalikot', 6, b'0'),
 (32, 'Kanchanpur', 7, b'0'),
 (33, 'Kapilvastu', 5, b'0'),
 (34, 'Kaski', 4, b'0'),
 (35, 'Kathmandu', 3, b'0'),
 (36, 'Kavrepalanchok', 3, b'0'),
 (37, 'Khotang', 1, b'0'),
 (38, 'Lalitpur', 3, b'0'),
 (39, 'Lamjung', 4, b'0'),
 (40, 'Mahottari', 2, b'0'),
 (41, 'Makwanpur', 3, b'0'),
 (42, 'Manang', 4, b'0'),
 (43, 'Morang', 1, b'0'),
 (44, 'Mugu', 6, b'0'),
 (45, 'Mustang', 4, b'0'),
 (46, 'Myagdi', 4, b'0'),
 (47, 'Nawalparasi East', 4, b'0'),
 (48, 'Nawalparasi West', 5, b'0'),
 (49, 'Nuwakot', 3, b'0'),
 (50, 'Okhaldhunga', 1, b'0'),
 (51, 'Palpa', 5, b'0'),
 (52, 'Panchthar', 1, b'0'),
 (53, 'Parbat', 4, b'0'),
 (54, 'Parsa', 2, b'0'),
 (55, 'Pyuthan', 5, b'0'),
 (56, 'Ramechhap', 3, b'0'),
 (57, 'Rasuwa', 3, b'0'),
 (58, 'Rautahat', 2, b'0'),
 (59, 'Rolpa', 5, b'0'),
 (60, 'Rukum East', 5, b'0'),
 (61, 'Rukum West', 6, b'0'),
 (62, 'Rupandehi', 5, b'0'),
 (63, 'Salyan', 6, b'0'),
 (64, 'Sankhuwasabha', 1, b'0'),
 (65, 'Saptari', 2, b'0'),
 (66, 'Sarlahi', 2, b'0'),
 (67, 'Sindhuli', 3, b'0'),
 (68, 'Sindhupalchok', 3, b'0'),
 (69, 'Siraha', 2, b'0'),
 (70, 'Solukhumbu', 1, b'0'),
 (71, 'Sunsari', 1, b'0'),
 (72, 'Surkhet', 6, b'0'),
 (73, 'Syangja', 4, b'0'),
 (74, 'Tanahu', 4, b'0'),
 (75, 'Taplejung', 1, b'0'),
 (76, 'Tehrathum', 1, b'0'),
 (77, 'Udayapur', 1, b'0');



 INSERT INTO province (province_id, province_name,disabled) VALUES
     	(1, 'Province No. 1' ,b'0'),
     	(2, 'Province No. 2', b'0'),
     	(3, 'Bagmati Province', b'0'),
     	(4, 'Gandaki Province', b'0'),
     	(5, 'Lumbini Province', b'0'),
     	(6, 'Karnali Province',b'0'),
     	(7, 'Sudurpashchim Province', b'0');


  INSERT INTO municipality (municipality_id, municipality_name,province_id, district_id, disabled) VALUES
     	(1, 'Kathmandu Metropolitan City', 3, 35, b'0'),
     	(2, 'Budanilkantha Municipality',3, 35, b'0'),
     	(3, 'Chandragiri Municipality', 3, 35, b'0'),
     	(4, 'Dakshinkali Municipality',3, 35, b'0'),
     	(5, 'Gokarneshwar Municipality', 3, 35, b'0'),
     	(6, 'Kageshwari Manohara Municipality', 3, 35, b'0'),
     	(7, 'Kirtipur Municipality', 3, 35, b'0'),
     	(8, 'Nagarjun Municipality', 3, 35, b'0'),
     	(9, 'Shankharapur Municipality', 3, 35, b'0'),
     	(10, 'Tarakeshwar Municipality',3, 35, b'0'),
     	(11, 'Tokha Municipality', 3, 35, b'0'),
     	(12, 'Lalitpur Metropolitan City', 3, 38, b'0'),
     	(13, 'Mahalaxmi Municipality', 3, 38, b'0'),
     	(14, 'Godawari Municipality',3, 38, b'0'),
     	(15, 'Konjyoson Rural Municipality', 3, 38, b'0'),
     	(16, 'Bagmati Rural Municipality',3, 38, b'0'),
     	(17, 'Mahankal Rural Municipality', 3, 38, b'0'),
     	(18, 'Bhaktapur Municipality', 3, 10, b'0'),
     	(19, 'Changunarayan Municipality',3, 10, b'0'),
     	(20, 'Madhyapur Thimi Municipality', 3, 10, b'0'),
     	(21, 'Suryabinayak Municipality', 3, 10, b'0');


     	 INSERT INTO payment_mode (id, mode_name) VALUES
             	(1, 'cash' ),
             	(2, 'cheque'),
             	(3, 'bank_redirect'),
             	(4, 'ebanking');


INSERT INTO feature_control(
	id, feature,feature_group)
	VALUES
	(1, 'Edit_Price', 1),
	(2, 'Search_By_Barcode', 2),
	(3, 'Search_By_Id', 2),
	(4, 'Search_By_Product_Name', 3),
	(5, 'edit_discount', 4),
       (6,'has_abb',5);


INSERT INTO public.account_type(
	id, name)
	VALUES (1, 'saving'),(2,'current'),(3,'other');

INSERT INTO public.type_of_payment(
	id, name)
	VALUES (1, 'cheque'),(2,'ebanking'),(3,'cash') ,(4,'other');

--	INSERT INTO public.bank(
--    	bank_id, company_id, branch_id, bank_name, account_number, create_date, account_type)
--    	VALUES (1, 1, 1, 'testing', 132465897, '2023/01/01', 'saving');
 INSERT INTO public.bank_list(
 	id, name, location)
 	VALUES (1,'Agriculture Development Bank','Ramshahpath, Kathmandu'),
 (2,'Citizens Bank International','Narayanhitipath, Kathmandu'),
 (3,'Everest Bank','Lazimpat, Kathmandu'),
 (4,'Global IME Bank','Kamaladi, Kathmandu'),
 (5,'Himalayan Bank','Kamaladi, Kathmandu'),
 (6,'Kumari Bank','Durbarmarg, Kathmandu'),
 (7,'Laxmi Bank','Hattisar, Kathmandu'),
 (8,'Machhapuchhre Bank','Lazimpat, Kathmandu'),
 (9,'Nabil Bank','Beena Marg, Kathmandu'),
 (10,'Nepal Bank','Dharmapath, Kathmandu'),
 (11,'Nepal Investment Mega Bank'	,'Durbarmarg, Kathmandu'),
 (12,'Nepal SBI Bank','Kesharmahal, Kathmandu'),
 (13,'NIC Asia Bank','Thapathali, Kathmandu'),
 (14,'NMB Bank','Babarmahal, Kathmandu'),
 (15,'Prabhu Bank','Babarmahal, Kathmandu'),
 (16,'Prime Commercial Bank','Kamalpokhari, Kathmandu'),
 (17,'Rastriya Banijya Bank','Singhadurbarplaza, Kathmandu'),
 (18,'Sanima Bank','Nagpokhari, Kathmandu'),
 (19,'Siddhartha Bank','Hattisar, Kathmandu'),
 (20,'Standard Chartered Bank','Nayabaneshwor, Kathmandu'),
 (21,'Sunrise Bank','Gairidhara, Kathmandu'),
 (22,'other','unknown');


insert into sale_type(
	id,
	sale_type_index,
	sale_type
) values (
	1,
	1,
	'Cash Sale'
),
(2,2,'Credit Sale');

INSERT INTO public.unit(
	name)
	VALUES ( 'Carton'),
	('pieces'),
	 ( 'Bora'),
	 ( 'package'),
	 ( 'sack'),
	 ( 'pkg'),
	 ( 'Box'),
	 ( 'Crate'),
	 ( 'Bundle'),
	 ( 'Bag'),
	 ( 'Container'),
	( 'Case'),
	 ( 'Pouch'),
	 ( 'Jar'),
	 ( 'Can'),
	( 'Barrel'),
	('other');

INSERT INTO public.users (id, firstname, lastname, email, phone, password, deleted, create_date, edit_date)
VALUES (DEFAULT, 'super', 'admin', 'super@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW()),
 (DEFAULT, 'admin', 'admin', 'admin@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW()),
 (DEFAULT, 'employee', 'employee', 'employee@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW()),
 (DEFAULT, 'staff', 'staff', 'staff@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW()),
 (DEFAULT, 'account', 'account', 'account@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW()),
 (DEFAULT, 'user', 'user', 'user@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW()),
 (DEFAULT, 'auditor', 'auditor', 'auditor@gmail.com', '0123456789', '$2a$10$98oIuT9bhOyEJK5GCmOwk.Mk0XrF6OPR9uEzJF4d7LnYgzYYEI.xa', false, NOW(), NOW());


INSERT INTO public.user_role (id, user_id, role_id, status, deleted)
VALUES (DEFAULT, 1, 6, true, false),
(DEFAULT, 1, 1, true, false),
(DEFAULT, 1, 2, true, false),
(DEFAULT, 1, 5, true, false),
(DEFAULT, 1, 4, true, false),
(DEFAULT, 1, 2, true, false),
(DEFAULT, 1, 3, true, false);