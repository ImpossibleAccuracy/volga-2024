insert into "Role" (title) values
    ('ADMIN'), ('MANAGER'), ('DOCTOR');

INSERT INTO "Account" (username,"password",first_name,last_name,deleted) VALUES
	 ('admin','$2a$10$7jEBnhRIOZrySAlsRyDWteAFsVlckDCHFEL1W9J0kUIyW/Nbr9MJi','FIRST_NAME 1','LAST NAME 1',false),
	 ('manager','$2a$10$QakMAoTmpxbgjoKg1Coit..lUJGr3LdUERNoN7rgQLyi1sJZQvgly','FIRST_NAME 2','LAST NAME 2',false),
	 ('doctor','$2a$10$QNoSV1.4qcdtfrIrRHP98.hjnT.miJkC/5AXJpdEtttnUFQZtmEKG','FIRST_NAME 3','LAST NAME 3',false),
	 ('user','$2a$10$FYQkvr2MQnWB0i/rtKN0U.mHlAsU0.DyVT2P7UmdSoevpsWOvHAB.','FIRST_NAME 4','LAST NAME 4',false);

INSERT INTO "Role_Account" (role_id,account_id) VALUES
	 (1,1),
	 (2,2),
	 (3,3);
