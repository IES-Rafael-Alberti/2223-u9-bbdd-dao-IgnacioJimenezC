ALTER TABLE GRUPOS
    ADD FOREIGN KEY (mejorposCTFid, grupoid)
        REFERENCES CTFS(CTFid,grupoid);