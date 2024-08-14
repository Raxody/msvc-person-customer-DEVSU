DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS movement CASCADE;
DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE person (
                        identification VARCHAR(255) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        gender VARCHAR(50) NOT NULL,
                        age INTEGER NOT NULL,
                        address VARCHAR(255),
                        phone BIGINT
);

CREATE TABLE customer (
                          customer_id VARCHAR(255) PRIMARY KEY,
                          identification VARCHAR(255) REFERENCES person(identification) ON DELETE CASCADE,
                          password VARCHAR(255) NOT NULL,
                          status BOOLEAN NOT NULL
);

CREATE TABLE account (
                         account_number BIGINT PRIMARY KEY AUTO_INCREMENT,
                         account_type VARCHAR(50) NOT NULL,
                         initial_balance DOUBLE PRECISION NOT NULL,
                         status BOOLEAN NOT NULL,
                         customer_id VARCHAR(255) NOT NULL,
                         CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);


CREATE TABLE movement (
                          movement_id UUID PRIMARY KEY,
                          date TIMESTAMP NOT NULL,
                          movement_type VARCHAR(50) NOT NULL,
                          "value" DOUBLE PRECISION NOT NULL,
                          balance DOUBLE PRECISION NOT NULL,
                          status BOOLEAN NOT NULL DEFAULT TRUE,
                          account_number BIGINT NOT NULL,
                          CONSTRAINT fk_account FOREIGN KEY (account_number) REFERENCES account(account_number) ON DELETE CASCADE
);


SELECT * FROM person;
SELECT * FROM customer;
SELECT * FROM account;
SELECT * FROM movement;
