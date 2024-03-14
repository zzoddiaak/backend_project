SELECT * FROM faсilities;



--SELECT name, membercost FROM faсilities;

--SELECT * FROM faсilities WHERE membercost > 0;

--SELECT facid, name, membercost,monthlymaintenance FROM faсilities WHERE membercost < (monthlymaintenance)/50 and membercost != 0;

--SELECT * FROM faсilities WHERE name LIKE '%Tennis%';

--SELECT * FROM faсilities WHERE facid IN (1,5);

--SELECT name, CASE WHEN monthlymaintenance > 100 THEN 'expensive' ELSE 'cheap' END AS cost FROM faсilities;

--SELECT memid, surname, firstname, joindate FROM members WHERE joindate > '2012-09-01%';

--SELECT DISTINCT surname FROM members ORDER BY surname LIMIT 10;

--SELECT surname  FROM members UNION SELECT name FROM faсilities;

--SELECT joindate FROM members ORDER BY joindate DESC LIMIT 1;

--SELECT firstname, surname, joindate FROM members ORDER BY joindate DESC LIMIT 1;

