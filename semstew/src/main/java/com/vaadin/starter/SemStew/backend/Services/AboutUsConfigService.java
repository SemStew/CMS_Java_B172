package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.AboutUsConfig;
import JOOQ.tables.records.AboutUsConfigRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class AboutUsConfigService {
    private List<AboutUsConfigRecord> configs;
    private DSLContext ctx;

    public AboutUsConfigService() {
        SelectAllAboutUsConfigService();
    }

    //select
    public void SelectAllAboutUsConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<AboutUsConfigRecord>();
        AboutUsConfig a = new AboutUsConfig();
        for (AboutUsConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.FOTOGALLERY_HEADER, a.getFotogalleryHeader()).
                        set(tmp.DESCRIPTION, a.getDescription()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectAllAboutUsConfigService();
    }

    //insert
    public void InsertAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER, tmp.DESCRIPTION, tmp.FOTOGALLERY_HEADER).
                            values(a.getIdLanguage(), a.getHeader(), a.getDescription(), a.getFotogalleryHeader()).execute();
        SelectAllAboutUsConfigService();
    }

    //delete
    public void DeleteAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectAllAboutUsConfigService();
    }

    public List<AboutUsConfigRecord> getConfigs() {
        return configs;
    }
}
