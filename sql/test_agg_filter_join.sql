sELECT a, b, count(*), sum(c) FROM testtableA  natural join testtableB where a>1 and b<5 GROUP BY a,b;

