create table main.customers(
	id uuid NOT NULL,
	first_name varchar(255) NOT NULL,
	middle_name varchar(255) NULL,
	last_name varchar(255) NOT NULL,
	email varchar(50) NOT NULL,
	phone varchar(50) NOT NULL,
	gstin varchar(15) NULL,
	type varchar(50) NULL,
	created_at timestamp,
	updated_at timestamp,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

create table main.business_customers_rel(
	business_id uuid not null,
	customer_id uuid not null,
	CONSTRAINT business_customers_rel_pkey primary key (business_id, customer_id),
	foreign key (business_id) references main.businesses(id),
	foreign key (customer_id) references main.customers(id)
);