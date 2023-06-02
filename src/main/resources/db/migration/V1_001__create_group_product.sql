CREATE table if not exists group_product
(
    "id"        int      not null UNIQUE,
    "name"      varchar(20) not null default '',
    "is_last"   boolean      not null default false,
    "parent_id" int      not null default -1
);

COMMIT;