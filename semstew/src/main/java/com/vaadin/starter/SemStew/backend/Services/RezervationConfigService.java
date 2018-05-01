package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Rezervation;
import JOOQ.tables.RezervationConfig;
import JOOQ.tables.records.RezervationConfigRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class RezervationConfigService {
    private List<RezervationConfigRecord> configs;
    private DSLContext ctx;

    public RezervationConfigService() {
        SelectRezervationConfigService();
    }

    //select
    public void SelectRezervationConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<RezervationConfigRecord>();
        RezervationConfig a = new RezervationConfig();
        for (RezervationConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateRezervationConfigService(RezervationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        RezervationConfig tmp = new RezervationConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.TABLE_NUMBER, a.getTableNumber()).
                set(tmp.TIME_FROM_DESC, a.getTimeFromDesc()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectRezervationConfigService();
    }

    //insert
    public void InsertRezervationConfigService(RezervationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        RezervationConfig tmp = new RezervationConfig();
        ctx.insertInto(tmp).columns(tmp.HEADER, tmp.ID_LANGUAGE, tmp.TABLE_NUMBER, tmp.TIME_FROM_DESC).
                values(a.getHeader(), a.getIdLanguage(), a.getTableNumber(), a.getTimeFromDesc()).execute();
        SelectRezervationConfigService();
    }

    //delete
    public void DeleteRezervationConfigService(RezervationConfigRecord a){
        SelectRezervationConfigService();
    }

    public List<RezervationConfigRecord> getConfigs() {
        return configs;
    }
}
