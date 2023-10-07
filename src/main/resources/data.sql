INSERT INTO customer (name,surname) VALUES ('Richard', 'Moore');
INSERT INTO customer (name, surname) VALUES ('Ibrahim', 'Khalil');
INSERT INTO customer (name, surname) VALUES ('Musa', 'Abdullah');
INSERT INTO customer (name, surname) VALUES ('John', 'Nijbor');
INSERT INTO customer (name, surname) VALUES ('Gerry', 'Hoover');

INSERT INTO account (account_type) VALUES ('CURRENT_ACCOUNT');
INSERT INTO account (account_type) VALUES ('SAVING_ACCOUNT');

INSERT INTO customer_account(customer_id, account_id) values (1, 1);
INSERT INTO customer_account(customer_id, account_id) values (1, 2);

INSERT INTO transaction(account_id, transaction_type) values (1, 'DEBIT');
INSERT INTO transaction(account_id, transaction_type) values (2, 'DEBIT');