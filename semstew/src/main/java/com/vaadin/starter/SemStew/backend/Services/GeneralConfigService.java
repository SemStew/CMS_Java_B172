package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.GeneralConfig;
import JOOQ.tables.records.GeneralConfigRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class GeneralConfigService {
    private List<GeneralConfigRecord> configs;
    private DSLContext ctx;

    public GeneralConfigService() {
        SelectGeneralConfigService();
    }

    //select
    public void SelectGeneralConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<GeneralConfigRecord>();
        GeneralConfig a = new GeneralConfig();
        for (GeneralConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.update(tmp).set(tmp.URL_MAIN_IMAGE, a.getUrlMainImage()).execute();
        SelectGeneralConfigService();
    }

    //insert
    public void InsertGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.insertInto(tmp).columns(tmp.URL_MAIN_IMAGE).values(a.getUrlMainImage()).execute();
        SelectGeneralConfigService();
    }

    //delete
    public void DeleteGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.delete(tmp).execute();
        SelectGeneralConfigService();
    }

    public List<GeneralConfigRecord> getConfigs() {
        return configs;
    }
}
