SELECT count(*) FROM cd.facilities; 


SELECT count(*) FROM cd.facilities WHERE guestcost >= 10;


SELECT recommendedby, count(*)
FROM cd.members
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;


SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;


SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings 
WHERE starttime >= '2012-09-01' 
AND starttime <'2012-10-01'
GROUP BY facid
ORDER BY "Total Slots";


SELECT facid, EXTRACT(MONTH FROM starttime) AS month ,sum(slots) AS "Total Slots"
FROM cd.bookings 
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;


SELECT count(distinct memid) from cd.bookings;


SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings 
GROUP BY facid
HAVING sum(slots) > 1000
ORDER BY facid;


SELECT fac.name, 
sum(slots * 
	CASE 
	WHEN memid = 0 
	THEN fac.guestcost
	ELSE fac.membercost
	END) as revenue
FROM cd.bookings bk
INNER JOIN cd.facilities fac
ON bk.facid = fac.facid
GROUP BY fac.name
ORDER BY revenue;


SELECT fac.name, 
sum(slots * 
	CASE 
	WHEN memid = 0 
	THEN fac.guestcost
	ELSE fac.membercost
	END) as revenue
FROM cd.bookings bk
INNER JOIN cd.facilities fac
ON bk.facid = fac.facid
GROUP BY fac.name
HAVING sum(
CASE
WHEN memid = 0 then slots * fac.guestcost
ELSE slots * membercost
END) < 1000
ORDER BY revenue;


SELECT facid, sum(slots) as "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY sum(slots) desc
LIMIT 1;     


SELECT facid, extract(month from starttime) as month, sum(slots) as slots
FROM cd.bookings
WHERE extract(year from starttime) = 2012
GROUP BY rollup(facid, month)
ORDER BY facid, month;  


select facs.facid, facs.name, trim(to_char(sum(bks.slots)/2.0, '9999999999999999D99')) as "Total Hours"
from cd.bookings bks
inner join cd.facilities facs
on facs.facid = bks.facid
group by facs.facid, facs.name
order by facs.facid;         


SELECT mem.surname, mem.firstname, mem.memid, min(bk.starttime) as starttime
FROM cd.bookings bk
INNER JOIN cd.members mem on
mem.memid = bk.memid
WHERE starttime >= '2012-09-01'
GROUP BY mem.surname, mem.firstname, mem.memid
ORDER BY mem.memid;   


SELECT (select count(*) from cd.members) as count, firstname, surname
FROM cd.members
ORDER BY joindate;

SELECT row_number() over(ORDER BY joindate), firstname, surname
FROM cd.members
ORDER BY joindate;


SELECT facid, sum(slots) as totalslots
FROM cd.bookings
GROUP BY facid
HAVING sum(slots) = (select max(sum2.totalslots)
FROM (select sum(slots) as totalslots
FROM cd.bookings
GROUP BY facid) as sum2);



SELECT firstname, surname,
((sum(bk.slots)+10)/20)*10 as hours,
rank() over (order by ((sum(bk.slots)+10)/20)*10 desc) as rank
FROM cd.bookings bk
INNER JOIN cd.members mem
ON bk.memid = mem.memid
GROUP BY mem.memid
ORDER BY rank, surname, firstname; 


SELECT name, rank from (
SELECT facs.name as name, rank() over (order by sum(case
WHEN memid = 0 
THEN slots * facs.guestcost
ELSE slots * membercost
END) desc) as rank
FROM cd.bookings bks
INNER JOIN cd.facilities facs
ON bks.facid = facs.facid
GROUP BY facs.name) as subq
WHERE rank <= 3
ORDER BY rank;         


SELECT name, case when class=1 then 'high'
WHRN class=2 
THEN 'average'
ELSE 'low'
END revenue
FROM (
SELECT facs.name as name, ntile(3) over (order by sum(case
WHEN memid = 0 then slots * facs.guestcost
ELSE slots * membercost
END) desc) as class
FROM cd.bookings bks
INNER JOIN cd.facilities facs
ON bks.facid = facs.facid
GROUP BY facs.name) as subq
ORDER BY class, name;     


SELECT 	facs.name as name, facs.initialoutlay/((sum(case
WHEN memid = 0 
THEN slots * facs.guestcost
ELSE slots * membercost
END)/3) - facs.monthlymaintenance) as months
FROM cd.bookings bks
INNER JOIN cd.facilities facs
ON bks.facid = facs.facid
GROUP BY facs.facid
ORDER BY name;   


SELECT 	dategen.date,
(
SELECT sum(case
WHEN memid = 0
THEN slots * facs.guestcost
ELSE slots * membercost
END) as rev
FROM cd.bookings bks
INNER JOIN cd.facilities facs
ON bks.facid = facs.facid
WHERE bks.starttime > dategen.date - interval '14 days'
AND bks.starttime < dategen.date + interval '1 day')/15 as revenue
FROM(
SELECT 	cast(generate_series(timestamp '2012-08-01', '2012-08-31','1 day') as date) as date)  as dategen
ORDER BY dategen.date;  




