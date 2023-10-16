SELECT
    p.pname AS NAME,
    SUM(w.salary * DATEDIFF(MONTH, p.start_date, p.finish_date)) AS PRICE
FROM project AS p
JOIN project_worker AS pw ON p.project_id = pw.project_id
JOIN worker AS w ON pw.worker_id = w.worker_id
GROUP BY p.pname
ORDER BY PRICE DESC;
