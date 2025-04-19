CREATE SCHEMA IF NOT EXISTS main;

CREATE TABLE IF NOT EXISTS main.businesses (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gstin VARCHAR(15) UNIQUE,
    preferred_language VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);