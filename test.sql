sELECT a, b, count(*), sum(c) FROM testtable where a>0 AND b>0 AND d>0 GROUP BY a,b;

