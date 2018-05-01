package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Units;
import JOOQ.tables.records.UnitsRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class UnitsService {
    private List<UnitsRecord> configs;
    private DSLContext ctx;

    public UnitsService() {
        SelectUnitsService();
    }

    //select
    public void SelectUnitsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<UnitsRecord>();
        Units a = new Units();
        for (UnitsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateUnitsService(UnitsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Units tmp = new Units();
        ctx.update(tmp).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_UNIT.eq(a.getIdUnit())).execute();
        SelectUnitsService();
    }

    //insert
    public void InsertUnitsService(UnitsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Units tmp = new Units();
        ctx.insertInto(tmp).columns(tmp.DESCRIPTION).values(a.getDescription()).execute();
        SelectUnitsService();
    }

    //delete
    public void DeleteUnitsService(UnitsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Units tmp = new Units();
        ctx.delete(tmp).where(tmp.ID_UNIT.eq(a.getIdUnit())).execute();
        SelectUnitsService();
    }

    public List<UnitsRecord> getConfigs() {
        return configs;
    }
}
