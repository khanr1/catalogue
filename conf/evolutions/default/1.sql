-- Category schema

-- !Ups
create table "CATEGORY" ("NAME" VARCHAR NOT NULL,"ID" BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,"PARENT_CATEGORY" BIGINT);

insert into "CATEGORY" ("NAME","PARENT_CATEGORY")
values
    ('Thermometry',NULL),
    ('Sensors',1),
    ('Thermometry harnesses',1),
    ('Hearters',SELECT ID FROM CATEGORY WHERE NAME='Thermometry'),
    ('Readers/Controller',SELECT ID FROM CATEGORY WHERE NAME='Thermometry'),
    ('Lakeshore',Select ID FROM CATEGORY WHERE Name='Readers/Controller'),
    ('Bluefors',Select ID FROM CATEGORY WHERE Name='Readers/Controller'),
    ('Pulse_Tube',NULL),
    ('PT+Compressor_Pacakge',Select ID FROM CATEGORY WHERE Name='Pulse_Tube'),
    ('Coldhead_with_remote_motor',Select ID FROM CATEGORY WHERE Name='Pulse_Tube'),
    ('Compressor',Select ID FROM CATEGORY WHERE Name='Pulse_Tube'),
    ('Flexline',Select ID FROM CATEGORY WHERE Name='Pulse_Tube'),
    ('Flexline_extension',Select ID FROM CATEGORY WHERE Name='Flexline'),
    ('Accessories',Select ID FROM CATEGORY WHERE Name='Pulse_Tube'),
    ('Maintenance',Select ID FROM CATEGORY WHERE Name='Pulse_Tube'),
    ('Services',NULL),
    ('Sample_Holder',NULL),
    ('Vertical_mounting_plate',Select ID FROM CATEGORY WHERE Name='Sample_Holder'),
    ('Mezzanine_plate',Select ID FROM CATEGORY WHERE Name='Sample_Holder'),
    ('Cold_Finger',Select ID FROM CATEGORY WHERE Name='Sample_Holder'),
    ('Extra_4K_plate',Select ID FROM CATEGORY WHERE Name='Sample_Holder');




















-- !Downs
drop table "Category" if exists;