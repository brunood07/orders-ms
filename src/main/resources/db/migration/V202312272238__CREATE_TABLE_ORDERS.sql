create schema if not exists ecomm_orders;

create table ecomm_orders.tb_orders(
    order_id serial primary key,
    client_id bigint not null,
    status varchar(20) not null,
    created_at timestamp without time zone not null,
    updated_at timestamp without time zone not null
);

alter table ecomm_orders.tb_orders owner to admin;
grant all on table ecomm_orders.tb_orders to admin;
grant select,insert,update,delete on table ecomm_orders.tb_orders to admin;

create table ecomm_orders.tb_price_info(
    id_price_info serial primary key,
    order_id bigint not null,
    discount numeric(15, 2),
    sub_total numeric(15, 2) not null,
    total numeric(15, 2) not null,
    shipping numeric(15, 2) not null,
    tax numeric(15, 2)
);

alter table ecomm_orders.tb_price_info owner to admin;
grant all on table ecomm_orders.tb_price_info to admin;
grant select,insert,update,delete on table ecomm_orders.tb_price_info to admin;

create table ecomm_orders.tb_order_products(
    id_order_product serial primary key,
    order_id bigint not null,
    quantity integer not null,
    price numeric(15, 2) not null,
    id_product bigint not null
);

alter table ecomm_orders.tb_order_products owner to admin;
grant all on table ecomm_orders.tb_order_products to admin;
grant select,insert,update,delete on table ecomm_orders.tb_order_products to admin;

create table ecomm_orders.tb_order_address(
    id_order_address serial primary key,
    order_id bigint not null,
    street varchar(100) not null,
    number varchar(10) not null,
    city varchar(50) not null,
    state varchar(50) not null,
    neighborhood varchar(100) not null,
    complement varchar(200),
    postal_code varchar(8) not null,
    days_to_delivery integer
);

alter table ecomm_orders.tb_order_address owner to admin;
grant all on table ecomm_orders.tb_order_address to admin;
grant select,insert,update,delete on table ecomm_orders.tb_order_address to admin;