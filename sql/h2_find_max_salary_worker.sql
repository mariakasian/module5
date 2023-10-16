SELECT wname, salary FROM worker
WHERE salary = (SELECT MAX(salary) FROM worker);