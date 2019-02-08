Use imdb;

# 1
SELECT title
FROM   titles
WHERE  production_year = 1942
ORDER BY title ASC;

#2
SELECT title, production_year
FROM titles, cast_info, name
WHERE name.name = "Connery, Sean" 
AND cast_info.person_id = name.id 
AND titles.id = cast_info.movie_id
AND titles.kind_id = 1
ORDER BY production_year DESC;


#3

SELECT title, production_year
FROM titles, cast_info, char_name
WHERE char_name.name = "James Bond" 
AND cast_info.person_role_id = char_name.id 
AND titles.id = cast_info.movie_id
And titles.kind_id = 1
ORDER BY production_year DESC;

#4
# referenced in = 6
SELECT *
FROM movie_link ml, 
	(SELECT id 
     FROM titles 
	 WHERE titles.title = "The Godfather"
	 AND production_year = 1972) tmp
WHERE ml.link_type_id = 6 
AND ml.movie_id = tmp.id 
AND exists ( SELECT t.*
			 FROM titles t
			 WHERE t.kind_id = 1
			 AND t.id = ml.linked_movie_id )                				
;

#5
SELECT * 
FROM info_type 

#VOTE ID = 100
;
SELECT AVG(info)
FROM movie_info_idx mi, titles t
WHERE t.kind_id = 1
AND mi.movie_id = t.id
AND mi.info_type_id = 100
;

#6

SELECT t.title
FROM movie_info_idx mi, titles t,
     (SELECT movie_id
      FROM movie_info_idx 
      WHERE info_type_id = 100
      AND info > 5000) temp
WHERE t.kind_id = 1
AND mi.movie_id = t.id
AND t.id = temp.movie_id
AND mi.info_type_id = 101
Order by mi.info asc
LIMIT 0,100;
;
#9

SELECT ak.title , mi.info, ak.production_year, m2.info as Budget
FROM movie_info_idx mi, movie_info m2, aka_titles ak

WHERE mi.info_type_id = 100 #Votes
 OR mi.info > 5000
 OR mi.info > 8
 OR mi.info_type_id = 101 # Rating
 OR m2.info_type_id = 105 #Budget
 AND ak.kind_id = 1
 AND mi.movie_id = ak.movie_id
 AND m2.movie_id = ak.movie_id
 Group by ak.title
 Order by mi.info desc
 Limit 0,100;



SELECT 
    ak.title, mi.info, ak.production_year,
FROM
    movie_info_idx mi,
    movie_info m2,
    aka_titles ak,
    (SELECT 
        movie_id
    FROM
        movie_info_idx
    WHERE
        info_type_id = 100 AND info > 5000) temp
WHERE
    ak.note = '(Germany)'
        AND mi.info_type_id = 101
        AND m2.info_type_id = 105
        AND mi.info > 8
        AND ak.kind_id = 1
        AND temp.movie_id = ak.movie_id
        AND mi.movie_id = ak.movie_id
        AND m2.movie_id = ak.movie_id
GROUP BY ak.title
ORDER BY mi.info DESC
LIMIT 0 , 100;

SELECT t1.title
FROM 
(SELECT id
FROM titles t, movie_link m 
WHERE m.link_type_id = 4 # Remade as
AND t.id = m.movie_id
AND t.kind_id = 1
) temp;
SELECT *
FROm link_type
;


SELECT t1.title, m2.info as Werting, tmp.Schnitt
FROM titles t1, movie_info_idx m2,
	(SELECT AVG(mi.info) as Schnitt, t.id
	FROM titles t, movie_link m, movie_info_idx mi
	WHERE m.link_type_id = 3 # Remade as
	AND t.id = m.movie_id
	AND t.kind_id = 1
	AND mi.info_type_id = 101
	AND mi.movie_id = t.id
    Group by t.id )tmp
WHERE m2.info_type_id = 101
AND m2.movie_id = t1.id
AND m2.movie_id = tmp.id
AND tmp.Schnitt > m2.info
;
select * 
From aka_titles;