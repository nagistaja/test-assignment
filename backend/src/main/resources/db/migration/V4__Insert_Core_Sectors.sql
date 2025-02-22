-- Top layer sectors
INSERT INTO sector (id, name, parent_id) VALUES (1, 'Manufacturing', NULL);
INSERT INTO sector (id, name, parent_id) VALUES (2, 'Service', NULL);
INSERT INTO sector (id, name, parent_id) VALUES (3, 'Other', NULL);

-- Manufacturing
INSERT INTO sector (id, name, parent_id) VALUES
                                             (4, 'Construction materials', 1),
                                             (5, 'Electronics & Optics', 1),
                                             (6, 'Food & Beverage', 1),
                                             (7, 'Furniture', 1),
                                             (8, 'Machinery', 1),
                                             (9, 'Metalworking', 1),
                                             (10, 'Plastic & Rubber', 1),
                                             (11, 'Printing', 1),
                                             (12, 'Textile & Clothing', 1),
                                             (13, 'Wood', 1);

-- Manufacturing -> Food & Beverage
INSERT INTO sector (id, name, parent_id) VALUES
                                             (14, 'Bakery & Confectionery Products', 6),
                                             (15, 'Beverages', 6),
                                             (16, 'Fish & Fish Products', 6),
                                             (17, 'Meat & Meat Products', 6),
                                             (18, 'Milk & Dairy Products', 6),
                                             (19, 'Sweets & Snack Food', 6),
                                             (20, 'Other (Bakery & Confectionery Products)', 6);

-- Manufacturing -> Furniture
INSERT INTO sector (id, name, parent_id) VALUES
                                             (21, 'Bathroom/Sauna', 7),
                                             (22, 'Bedroom', 7),
                                             (23, 'Children’s Room', 7),
                                             (24, 'Kitchen', 7),
                                             (25, 'Living Room', 7),
                                             (26, 'Office', 7),
                                             (27, 'Project Furniture', 7),
                                             (28, 'Outdoor', 7),
                                             (29, 'Other (Furniture)', 7);

-- SManufacturing -> Machinery
INSERT INTO sector (id, name, parent_id) VALUES
                                             (30, 'Machinery Components', 8),
                                             (31, 'Machinery Equipment/Tools', 8),
                                             (32, 'Manufacture of Machinery', 8),
                                             (33, 'Maritime', 8),
                                             (34, 'Metal Structures', 8),
                                             (35, 'Repair & Maintenance Service', 8),
                                             (36, 'Other (Machinery)', 8);

-- Manufacturing -> Maritime
INSERT INTO sector (id, name, parent_id) VALUES
                                             (37, 'Aluminium & Steel Workboats', 33),
                                             (38, 'Boat/Yacht Building', 33),
                                             (39, 'Ship Repair & Conversion', 33);

-- Manufacturing -> Metalworking
INSERT INTO sector (id, name, parent_id) VALUES
                                             (40, 'Construction of Metal Structures', 9),
                                             (41, 'Houses & Buildings', 9),
                                             (42, 'Metal Products', 9),
                                             (43, 'Metal Works', 9);

-- Manufacturing -> Metal Works
INSERT INTO sector (id, name, parent_id) VALUES
                                             (44, 'CNC-machining', 43),
                                             (45, 'Forgings, Fasteners', 43),
                                             (46, 'Gas, Plasma, Laser Cutting', 43),
                                             (47, 'MIG, TIG, Aluminum Welding', 43);

-- Manufacturing -> Plastic & Rubber
INSERT INTO sector (id, name, parent_id) VALUES
                                             (48, 'Packaging', 10),
                                             (49, 'Plastic Goods', 10),
                                             (50, 'Plastic Processing Technology', 10),
                                             (51, 'Blowing', 10),
                                             (52, 'Moulding', 10),
                                             (53, 'Plastics Welding & Processing', 10),
                                             (54, 'Plastic Profiles', 10);

-- Manufacturing -> Printing
INSERT INTO sector (id, name, parent_id) VALUES
                                             (55, 'Advertising', 11),
                                             (56, 'Book/Periodicals Printing', 11),
                                             (57, 'Labelling & Packaging Printing', 11);

-- Manufacturing -> Textile & Clothing
INSERT INTO sector (id, name, parent_id) VALUES
                                             (58, 'Clothing', 12),
                                             (59, 'Textile', 12);

-- Manufacturing -> Wood
INSERT INTO sector (id, name, parent_id) VALUES
                                             (60, 'Wooden Building Materials', 13),
                                             (61, 'Wooden Houses', 13),
                                             (62, 'Other (Wood)', 13);

-- Other
INSERT INTO sector (id, name, parent_id) VALUES
                                             (63, 'Creative Industries', 3),
                                             (64, 'Energy Technology', 3),
                                             (65, 'Environment', 3);

-- Service
INSERT INTO sector (id, name, parent_id) VALUES
                                             (66, 'Business services', 2),
                                             (67, 'Engineering', 2),
                                             (68, 'Information Technology & Telecommunications', 2),
                                             (69, 'Tourism', 2),
                                             (70, 'Translation services', 2),
                                             (71, 'Transport & Logistics', 2);

-- Service -> Information Technology & Telecommunications
INSERT INTO sector (id, name, parent_id) VALUES
                                             (72, 'Data processing, Web portals, E-marketing', 68),
                                             (73, 'Programming, Consultancy', 68),
                                             (74, 'Software, Hardware', 68),
                                             (75, 'Telecommunications', 68);

-- Service -> Transport & Logistics
INSERT INTO sector (id, name, parent_id) VALUES
                                             (76, 'Air', 71),
                                             (77, 'Rail', 71),
                                             (78, 'Road', 71),
                                             (79, 'Water', 71);

SELECT setval(pg_get_serial_sequence('sector','id'), (SELECT MAX(id) FROM sector));
