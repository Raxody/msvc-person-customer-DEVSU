CREATE DATABASE devsu;

-- Conectar a la base de datos devsu
\c devsu;


-- Eliminar las tablas si existen
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS person CASCADE;

-- Crear la base de datos devsu


-- Crear la tabla person con la identificación como clave primaria
CREATE TABLE person (
                        identification VARCHAR(255) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        gender VARCHAR(50) NOT NULL,
                        age INTEGER NOT NULL,
                        address VARCHAR(255),
                        phone BIGINT
);

-- Crear la tabla customer con la identificación como clave foránea y customer_id autoincremental
CREATE TABLE customer (
                          customer_id SERIAL PRIMARY KEY,
                          identification VARCHAR(255) REFERENCES person(identification) ON DELETE CASCADE,
                          password VARCHAR(255) NOT NULL,
                          status BOOLEAN NOT NULL
);
