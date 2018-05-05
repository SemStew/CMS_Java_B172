package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.NewsName;
import JOOQ.tables.records.NewsNameRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class NewsNameService {
    private List<NewsNameRecord> configs;
    private DSLContext ctx;

    public NewsNameService() {
        SelectNewsNameService();
    }

    //select
    public void SelectNewsNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<NewsNameRecord>();
        NewsName a = new NewsName();
        for (NewsNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_NEWS.eq(a.getIdNews())).execute();
        SelectNewsNameService();
    }

    //insert
    public void InsertNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.ID_NEWS, tmp.HEADER, tmp.DESCRIPTION).
                values(a.getIdLanguage(), a.getIdNews(), a.getHeader(), a.getDescription()).execute();
        SelectNewsNameService();
    }

    //delete
    public void DeleteNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_NEWS.eq(a.getIdNews())).execute();
        SelectNewsNameService();
    }

    public List<NewsNameRecord> getConfigs() {
        return configs;
    }
}
