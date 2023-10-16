SELECT client.cname, COUNT(project.project_id) as project_count
FROM client
LEFT JOIN project ON client.client_id = project.client_id
GROUP BY client.cname
HAVING COUNT(project.project_id) = (
    SELECT MAX(project_count)
    FROM (SELECT client_id, COUNT(project_id) as project_count FROM project GROUP BY client_id) AS project_counts
);