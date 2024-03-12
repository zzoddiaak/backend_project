
CREATE TABLE faсilities(
    facid integer PRIMARY KEY,
    name varchar(100),
    membercost numeric,
    guestcost numeric,
    initialoutlay numeric,
    monthlymaintenance numeric
);

CREATE TABLE members(
    memid integer PRIMARY KEY,
    surname varchar(200),
    firstname varchar(200),
    address varchar(200),
    zipcode integer,
    telephone varchar(20),
    recommendedby integer REFERENCES members(memid),
    joindate timestamp
);

CREATE TABLE bookings(
    bookid integer PRIMARY KEY,
    facid integer,
    memid integer,
    starttime timestamp,
    slots integer
);
