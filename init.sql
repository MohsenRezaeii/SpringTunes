CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (username, authority),
    FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users (username, password, enabled)
VALUES ('user', '{bcrypt}$2a$12$OpudCYfhwGO5mFQeB6bzdO0V7PpW2ay2yZxVKeJBmFaogfraFZ/He', true);
INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER');

INSERT INTO users (username, password, enabled)
VALUES ('manager', '{bcrypt}$2a$12$OpudCYfhwGO5mFQeB6bzdO0V7PpW2ay2yZxVKeJBmFaogfraFZ/He', true);
INSERT INTO authorities (username, authority)
VALUES ('manager', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('manager', 'ROLE_MANAGER');

INSERT INTO users (username, password, enabled)
VALUES ('admin', '{bcrypt}$2a$12$OpudCYfhwGO5mFQeB6bzdO0V7PpW2ay2yZxVKeJBmFaogfraFZ/He', true);
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_MANAGER');
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');