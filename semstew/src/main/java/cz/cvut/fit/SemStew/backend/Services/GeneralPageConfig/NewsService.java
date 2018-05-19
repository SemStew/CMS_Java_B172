package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.News;
import JOOQ.tables.records.NewsRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class NewsService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * NewsService constructor
     */
    public NewsService() {}

    /**
     * Get all News records
     *
     * Use {@link #SelectNewsService()} to get all News records from database
     *
     * @return list of all News records
     */
    public List<NewsRecord> SelectNewsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<NewsRecord> configs = new ArrayList<NewsRecord>();
        News a = new News();
        for (NewsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update News record
     *
     * Use {@link #UpdateNewsService(NewsRecord a)} to update given News record
     *
     * @param a News record to be updated
     */
    public void UpdateNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.update(tmp).set(tmp.ID_RESTAURANT, a.getIdRestaurant()).set(tmp.N_DATE, a.getNDate()).
                where(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    /**
     * Insert new News record
     *
     * Use {@link #InsertNewsService(NewsRecord a)} to insert given News record
     *
     * @param a News record to be inserted
     */
    public void InsertNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.N_DATE).
                values(a.getIdRestaurant(), a.getNDate()).execute();
    }


    /**
     * Delete News record
     *
     * Use {@link #DeleteNewsService(NewsRecord a)} to delete given News record
     *
     * @param a News record to be deleted
     */
    public void DeleteNewsService(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        ctx.delete(tmp).where(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    /**
     * Insert and return generated News record ID
     *
     * Use {@link #InsertAndReturn(NewsRecord a)} to insert given News record and return generated News record ID
     *
     * @param a News record to be inserted
     * @return generated News record ID
     */
    public int InsertAndReturn(NewsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        News tmp = new News();
        NewsRecord newsRecord = ctx.newRecord(tmp);
        newsRecord.setIdRestaurant(a.getIdRestaurant());
        newsRecord.setNDate(a.getNDate());
        newsRecord.store();
        return newsRecord.getIdNews();
    }

    /**
     * Get all News records
     *
     * Use {@link #getConfigs()} to get all News records from database
     *
     * @return list of all News records
     */
    public List<NewsRecord> getConfigs() {
        return SelectNewsService();
    }
}
