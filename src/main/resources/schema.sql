CREATE TABLE IF NOT EXISTS WALLET
(
    id    INT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    document VARCHAR(100) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);
CREATE INDEX phone_document_index ON WALLET (phone, document);

INSERT INTO WALLET (id, name, phone, document, balance) VALUES (1, 'John Doe', '123456789', '123456789', 1000.00);
INSERT INTO WALLET (id, name, phone, document, balance) VALUES (2, 'Jane Doe', '987654321', '123456789', 2000.00);
INSERT INTO WALLET (id, name, phone, document, balance) VALUES (3, 'Alice', '123456789', '987654321', 3000.00);
INSERT INTO WALLET (id, name, phone, document, balance) VALUES (4, 'Bob', '987654321', '987654321', 4000.00);
