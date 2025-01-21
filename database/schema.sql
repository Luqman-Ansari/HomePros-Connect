create database homepros;

use homepros;

CREATE TABLE User (
    ID INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    bal FLOAT DEFAULT 0.0
);

CREATE TABLE ServiceProvider (
    ID INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    bal FLOAT DEFAULT 0.0,
    companyName VARCHAR(255),
    rating int DEFAULT 0
);

CREATE TABLE login (
    ID INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE Services (
    ID INT auto_increment PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE ServicesProvided (
    serviceID int,
    ServiceProviderID int,
    PRIMARY KEY (serviceID, ServiceProviderID),
	FOREIGN KEY (serviceID) REFERENCES Services(ID),
    FOREIGN KEY (ServiceProviderID) REFERENCES ServiceProvider(ID)
);

insert into ServicesProvided values (2, 111112);


select s2.name, s2.companyName, s2.rating
from ServicesProvided s
inner join services s1 on s.serviceID = s1.ID
inner join ServiceProvider s2 on s2.ID = s.ServiceProviderID
where s.serviceID = 1

CREATE TABLE Booking (
    bookingID INT auto_increment PRIMARY KEY,
    userID INT,
    providerID INT,
    serviceID INT,
    status BOOLEAN,
    accepted BOOLEAN,
    foreign key(userID) references user(ID),
    foreign key(serviceID) references services(ID),
    foreign key(providerID) references serviceProvider(ID),
    paymentID INT,
    foreign key (paymentID) references payment(ID),
    feedbackID int,
    foreign key (feedbackID) references feedback(ID)
);

create table feedback(
	ID Int auto_increment primary key,
    comment nvarchar(255)
);

create table payment(
	ID INT auto_increment primary key,
    amount float,
    status boolean
);

UPDATE User SET bal = 1000000 WHERE ID = 111111


	SELECT MAX(id) + 1 FROM login

select* from login;
select* from user;
select* from serviceProvider;

delete FROM user where 

delete FROM user where id = 111111

insert into services(name) value ("electrician")
