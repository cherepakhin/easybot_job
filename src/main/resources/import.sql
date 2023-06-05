insert into group_product(id, name, parent_id) values (1, 'Desktop Computers', -1);
insert into group_product(id, name, parent_id) values (2, 'Notebooks', -1);
insert into group_product(id, name, parent_id) values (3, 'Monitors', -1);
insert into group_product(id, name, parent_id) values (4, 'Hard drives', -1);

insert into product(id, name, group_product_id) values (1, 'Desktop1', 1);
insert into product(id, name, group_product_id) values (2, 'Desktop2', 1);
insert into product(id, name, group_product_id) values (3, 'Notebook1', 2);
insert into product(id, name, group_product_id) values (4, 'Notebook2', 2);
insert into product(id, name, group_product_id) values (5, 'Monitor1', 3);
insert into product(id, name, group_product_id) values (6, 'Monitor2', 3);
insert into product(id, name, group_product_id) values (7, 'HDD1', 4);
insert into product(id, name, group_product_id) values (8, 'HDD2', 4);
COMMIT;

