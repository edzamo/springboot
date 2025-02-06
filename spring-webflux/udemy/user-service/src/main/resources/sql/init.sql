create table users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(100) NULL,
    balance INT NULL,
       PRIMARY KEY (id)
) ENGINE=INNODB ;

create table user_transaction(
    id BIGINT  NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL ,
    amount INT,
    transaction_date timestamp,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
) ENGINE=INNODB;