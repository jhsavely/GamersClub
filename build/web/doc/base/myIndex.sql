

CREATE INDEX name ON gamers(name,lastname);

CREATE INDEX game ON gamers(game);

CREATE UNIQUE INDEX email ON gamers(email)

CREATE INDEX result ON gamers(result);


EXPLAIN SELECT email FROM gamers WHERE email = 'golotyuk@gmail.com';/*will show index*/

show table status;/*will show status*/
