-- User details table.
DROP TABLE IF EXISTS USER;

CREATE TABLE `user` (
	`id` VARCHAR(50) NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	`password` VARCHAR(50) NOT NULL
);

INSERT USER (ID, NAME, PASSWORD) 
VALUES ("1", "username1", "password1");
INSERT USER (ID, NAME, PASSWORD) 
VALUES ("2", "username2", "password2");

-- AWB Details table.
DROP TABLE IF EXISTS T001_AWB_DTL;

CREATE TABLE T001_AWB_DTL(
	`AWBID` int AUTO_INCREMENT NOT NULL,
	`DATALOAD_TIME` TIMESTAMP NOT NULL,
	`AWB` varchar(20) NOT NULL,
	`FLIGHT_NUMBER` varchar(10) NOT NULL,
	`ARRIVAL_TIMESTAMP` TIMESTAMP NOT NULL,
	`DEPARTURE_TIMESTAMP` TIMESTAMP NOT NULL,
	`CTO_NAME` varchar(10) NULL,
	`CUSTOMER_NAME` varchar(200) NULL,
	`ORIGIN` varchar(5) NULL,
	`DESTINATION` varchar(5) NULL,
	`MANIFESTED_PIECES` int NULL,
	`MANIFESTED_WEIGHT` decimal(16, 2) NULL,
	`UNITS_FOR_PICKUP` int NULL,
	`CHARGE_WEIGHT` decimal(16, 2) NULL,
	`STATUS_CODE` varchar(10) NULL,
	`COMMODITY` varchar(200) NULL,
	`SPECIAL_HANDLING_CODES` varchar(200) NULL,
	`LOOSE` varchar(20) NULL,
	`RECEIVED_DATE` TIMESTAMP NULL,
	`FLIGHT_CLOSED` varchar(1) NULL DEFAULT 'N',
 CONSTRAINT `PK_AWB_DTL` PRIMARY KEY 
(
	`AWBID` ASC,
	`AWB` ASC,
	`FLIGHT_NUMBER` ASC,
	`ARRIVAL_TIMESTAMP` ASC,
	`DEPARTURE_TIMESTAMP` ASC,
	`DATALOAD_TIME` ASC
) 
);

INSERT INTO T001_AWB_DTL
           (`AWB`,
			`FLIGHT_NUMBER`,
			`ARRIVAL_TIMESTAMP`,
			`DEPARTURE_TIMESTAMP`,
			`ORIGIN`,
			`DESTINATION`)
     VALUES
           ('12548050951'
           ,'BA 015'
           ,'2018-05-29 07:34:25'
           ,'2018-05-30 08:34:25'
           ,'SYD'
           ,'GER');

INSERT INTO T001_AWB_DTL
           (`AWB`,
			`FLIGHT_NUMBER`,
			`ARRIVAL_TIMESTAMP`,
			`DEPARTURE_TIMESTAMP`,
			`ORIGIN`,
			`DESTINATION`)
     VALUES
           ('78948050951'
           ,'DC 015'
           ,'2018-06-01 01:54:25'
           ,'2018-06-02 02:04:25'
           ,'GER'
           ,'SYD');
           