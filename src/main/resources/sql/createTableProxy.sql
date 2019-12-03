 CREATE TABLE PROXYFULL(
        ID int(19) not null auto_increment,
        COUNTRY nvarchar(255),
        ANONYMITY_LEVEL nvarchar(255),
        IP nvarchar(255),
        LAST_UPDATE Timestamp,
        PORT1 nvarchar(255),
        RESPONSE_TIME int(19),
        UPTIME_D int(19),
        UPTIME_L int(19),
        VERIFY boolean,
        primary key (ID)
        );

