SELECT project.pname,
    DATEDIFF(MONTH, project.start_date, project.finish_date) as duration_months
FROM project
WHERE DATEDIFF(MONTH, project.start_date, project.finish_date) = (
    SELECT MAX(duration_months)
    FROM (
        SELECT
            project_id,
            DATEDIFF(MONTH, start_date, finish_date) as duration_months
        FROM project
    ) AS project_durations
);

