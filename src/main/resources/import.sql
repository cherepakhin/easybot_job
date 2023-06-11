insert into group_product(id, name, parent_id, is_last) values
(1, 'IT products', -1, false),
(2, 'Computers', 1, false),
(3, 'Desktop Computers', 2, true),
(4, 'Notebooks', 2, true),
(5, 'Monitors', 1, true),
(6, 'Hard drives', 1, true);

insert into product(id, name, group_product_id) values (31, 'Desktop1', 3);
insert into product(id, name, group_product_id) values (32, 'Desktop2', 3);
insert into product(id, name, group_product_id) values (33, 'Desktop3', 3);
insert into product(id, name, group_product_id) values (41, 'Notebook1', 4);
insert into product(id, name, group_product_id) values (42, 'Notebook2', 4);
insert into product(id, name, group_product_id) values (51, 'Monitor1', 5);
insert into product(id, name, group_product_id) values (52, 'Monitor2', 5);
insert into product(id, name, group_product_id) values (61, 'HDD1', 6);
insert into product(id, name, group_product_id) values (62, 'HDD2', 6);
COMMIT;

