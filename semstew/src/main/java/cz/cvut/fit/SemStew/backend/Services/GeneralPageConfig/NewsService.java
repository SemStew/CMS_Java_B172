package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.News;
import JOOQ.tables.records.NewsRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class NewsService {
    private DSLContext ctx;

    public NewsService() {}

    //select
    public List<NewsRecord> SelectNewsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<NewsRecord> configs = new ArrayList<NewsRecord>();
        News a = new News();
        for (NewsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.update(tmp).set(tmp.ID_RESTAURANT, a.getIdRestaurant()).set(tmp.N_DATE, a.getNDate()).
                where(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    //insert
    public void InsertNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.N_DATE).
                values(a.getIdRestaurant(), a.getNDate()).execute();
    }

    //delete
    public void DeleteNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.delete(tmp).where(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    // insert and return id
    public int InsertAndReturn(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        NewsRecord newsRecord = ctx.newRecord(tmp);
        newsRecord.setIdRestaurant(a.getIdRestaurant());
        newsRecord.setNDate(a.getNDate());
        newsRecord.store();
        return newsRecord.getIdNews();
    }

    public List<NewsRecord> getConfigs() {
        return SelectNewsService();
    }
}
