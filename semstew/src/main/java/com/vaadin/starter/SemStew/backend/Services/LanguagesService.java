package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Languages;
import JOOQ.tables.records.LanguagesRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class LanguagesService {
    private List<LanguagesRecord> configs;
    private DSLContext ctx;

    public LanguagesService() {
        SelectLanguagesService();
    }

    //select
    public void SelectLanguagesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<LanguagesRecord>();
        Languages a = new Languages();
        for (LanguagesRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectLanguagesService();
    }

    //insert
    public void InsertLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
        SelectLanguagesService();
    }

    //delete
    public void DeleteLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectLanguagesService();
    }

    public List<LanguagesRecord> getConfigs() {
        return configs;
    }
}
