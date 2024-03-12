/*SELECT starttime
FROM bookings
JOIN members ON bookings.memid = members.memid
WHERE members.firstname = 'David' AND members.surname = 'Farrell';*/



SELECT book.starttime as start, fc.name 
FROM bookings book,
      faсilities fc
WHERE fc.name in ('Tennis Court 1', 'Tennis Court 2') 
AND book.starttime >= '2012-09-21'
AND book.starttime < '2012-09-22'
AND book.facid = fc.facid
ORDER BY book.starttime;

/*
select distinct recs.firstname as firstname, recs.surname as surname
 from 
  cd.members mems
  inner join cd.members recs
   on recs.memid = mems.recommendedby
order by surname, firstname;


SELECT mem.firstname as memfname, mem.surname as memsname, 
rec.firstname as recfname, rec.surname as recsname
from cd.members mem
left outer join cd.members rec on rec.memid = mem.recommendedby
order by memsname, memfname;


SELECT DISTINCT mem.firstname || ' ' || mem.surname as member, fac.name as facility
FROM cd.members mem, cd.facilities fac, cd.bookings bk
WHERE fac.name in ('Tennis Court 1', 'Tennis Court 2')
AND mem.memid = bk.memid
AND bk.facid = fac.facid
ORDER BY member, facility;


SELECT mem.firstname || ' ' || mem.surname as member, 
fac.name AS facility, 
CASE 
WHEN mem.memid = 0 then
bk.slots*fac.guestcost
ELSE
bk.slots*fac.membercost
END AS cost
FROM cd.members mem                
INNER JOIN cd.bookings bk
ON mem.memid = bk.memid
INNER JOIN cd.facilities fac
ON bk.facid = fac.facid
WHERE bk.starttime >= '2012-09-14' AND 
bk.starttime < '2012-09-15' AND (
(mem.memid = 0 AND bk.slots*fac.guestcost > 30) OR
(mem.memid != 0 AND bk.slots*fac.membercost > 30))
ORDER BY cost DESC;


SELECT DISTINCT mem.firstname || ' ' || mem.surname AS member,
(
 SELECT  rec.firstname || ' ' || rec.surname AS recommender
 FROM cd.members rec
 WHERE rec.memid = mem.recommendedby
)
FROM cd.members mem
ORDER BY member;


SELECT member, facility, cost 
FROM
(
 SELECT
  mem.firstname || ' ' || mem.surname AS member,
  fac.name AS facility,
  CASE
  WHEN mem.memid = 0 THEN
  bk.slots*fac.guestcost
  ELSE
  bk.slots*fac.membercost
  END AS cost
  FROM cd.members mem
  INNER JOIN cd.bookings bk
  ON mem.memid = bk.memid
  INNER JOIN cd.facilities fac
  ON bk.facid = fac.facid
  WHERE bk.starttime >= '2012-09-14' 
  AND bk.starttime < '2012-09-15'
 ) 
AS bookings
WHERE cost > 30
ORDER BY cost DESC;*/



