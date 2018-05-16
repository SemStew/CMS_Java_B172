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
    private DSLContext ctx;

    public NewsNameService() {}

    //select
    public List<NewsNameRecord> SelectNewsNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<NewsNameRecord> configs = new ArrayList<NewsNameRecord>();
        NewsName a = new NewsName();
        for (NewsNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    //insert
    public void InsertNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.ID_NEWS, tmp.HEADER, tmp.DESCRIPTION).
                values(a.getIdLanguage(), a.getIdNews(), a.getHeader(), a.getDescription()).execute();
    }

    //delete
    public void DeleteNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    // get by id and language
    public NewsNameRecord GetByIdAndLanguage(int id, int language){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        for(NewsNameRecord rec : ctx.selectFrom(tmp).where(tmp.ID_NEWS.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return rec;
        return null;
    }

    // delete by news id
    public void DeleteByNewsId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.delete(tmp).where(tmp.ID_NEWS.eq(id)).execute();
    }

    public List<NewsNameRecord> getConfigs() {
        return SelectNewsNameService();
    }
}
