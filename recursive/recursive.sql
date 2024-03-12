with recursive recommenders(recommender) as (
select recommendedby from cd.members where memid = 27
union all
select mems.recommendedby
from recommenders recs
inner join cd.members mems
on mems.memid = recs.recommender
)
select recs.recommender, mems.firstname, mems.surname
from recommenders recs
inner join cd.members mems
on recs.recommender = mems.memid
order by memid desc;       


with recursive recommendeds(memid) as (
select memid from cd.members where recommendedby = 1
union all
select mems.memid
from recommendeds recs
inner join cd.members mems
on mems.recommendedby = recs.memid
)
select recs.memid, mems.firstname, mems.surname
from recommendeds recs
inner join cd.members mems
on recs.memid = mems.memid
order by memid;   


with recursive recommendeds(memid) as (
select memid from cd.members where recommendedby = 1
union all
select mems.memid
from recommendeds recs
inner join cd.members mems
on mems.recommendedby = recs.memid
)
select recs.memid, mems.firstname, mems.surname
from recommendeds recs
inner join cd.members mems
on recs.memid = mems.memid
order by memid ;   
