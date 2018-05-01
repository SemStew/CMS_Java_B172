package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.News;
import JOOQ.tables.records.NewsRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class NewsService {
    private List<NewsRecord> configs;
    private DSLContext ctx;

    public NewsService() {
        SelectNewsService();
    }

    //select
    public void SelectNewsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<NewsRecord>();
        News a = new News();
        for (NewsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.update(tmp).set(tmp.ID_RESTAURANT, a.getIdRestaurant()).set(tmp.N_DATE, a.getNDate()).
                where(tmp.ID_NEWS.eq(a.getIdNews())).execute();
        SelectNewsService();
    }

    //insert
    public void InsertNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.N_DATE).
                values(a.getIdRestaurant(), a.getNDate()).execute();
        SelectNewsService();
    }

    //delete
    public void DeleteNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.delete(tmp).where(tmp.ID_NEWS.eq(a.getIdNews())).execute();
        SelectNewsService();
    }

    public List<NewsRecord> getConfigs() {
        return configs;
    }
}
