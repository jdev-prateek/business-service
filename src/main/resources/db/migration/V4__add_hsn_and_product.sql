create table if not exists main.hsn (
	id uuid not null primary key,
	code varchar(10) not null,
	description varchar(255) not null,
	gst decimal(10, 2) not null,
	created_at timestamp,
	updated_at timestamp
);

create table if not exists main.products (
	id uuid not null primary key,
	name varchar(255) not null,
	description text not null,
	unit_price decimal(10, 2) not null,
	stock_quantity int not null,
	created_at timestamp,
	updated_at timestamp,
	hsn_id uuid not null,
	business_id uuid not null,
	foreign key (hsn_id) references main.hsn (id),
	foreign key (business_id) references main.businesses (id)
)