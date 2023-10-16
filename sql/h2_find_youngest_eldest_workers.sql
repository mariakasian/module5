SELECT 'ELDEST' AS TYPE, wname AS NAME, birthday AS BIRTHDAY
FROM worker
WHERE birthday = (SELECT MIN(birthday) FROM worker)

UNION ALL

SELECT 'YOUNGEST' AS TYPE, wname AS NAME, birthday AS BIRTHDAY
FROM worker
WHERE birthday = (SELECT MAX(birthday) FROM worker);

