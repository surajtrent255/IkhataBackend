INSERT INTO role (id, role, description) VALUES
	(1, 'ADMIN', ''),
	(2, 'USER', ''),
	(3, 'ACCOUNTANT',''),
	(4, 'MANAGER',''),
	(5, 'SUPER_ADMIN','');

	INSERT INTO public.company(
    	 name, description, pan_no, state, zone, district, mun_vdc, ward_no, phone)
    	VALUES ('nabin', 'hello There', 23456783245678, 02, 'bagmati', 'kathmandu', 'alocknagar', 01, 9848859416);


    	insert into bill_no_generator
        (
            fiscal_year,
            bill_no,
            active
        ) values
        (
            '2079/80',
            1,
            true
        )