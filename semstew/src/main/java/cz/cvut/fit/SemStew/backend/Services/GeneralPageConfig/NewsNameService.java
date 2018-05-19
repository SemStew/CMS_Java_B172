package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.NewsName;
import JOOQ.tables.records.NewsNameRecord;
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
public class NewsNameService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * NewsNameService constructor
     */
    public NewsNameService() {}

    /**
     * Get all NewsName records
     *
     * Use {@link #SelectNewsNameService()} to get all NewsName records from database
     *
     * @return list of all NewsName records
     */
    public List<NewsNameRecord> SelectNewsNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<NewsNameRecord> configs = new ArrayList<NewsNameRecord>();
        NewsName a = new NewsName();
        for (NewsNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update NewsName record
     *
     * Use {@link #UpdateNewsNameService(NewsNameRecord a)} to update given NewsName record
     *
     * @param a NewsName record to be updated
     */
    public void UpdateNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    /**
     * Insert new NewsName record
     *
     * Use {@link #InsertNewsNameService(NewsNameRecord a)} to insert given NewsName record
     *
     * @param a NewsName record to be inserted
     */
    public void InsertNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.ID_NEWS, tmp.HEADER, tmp.DESCRIPTION).
                values(a.getIdLanguage(), a.getIdNews(), a.getHeader(), a.getDescription()).execute();
    }

    /**
     * Delete NewsName record
     *
     * Use {@link #DeleteNewsNameService(NewsNameRecord a)} to delete given NewsName record
     *
     * @param a NewsName record to be deleted
     */
    public void DeleteNewsNameService(NewsNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_NEWS.eq(a.getIdNews())).execute();
    }

    /**
     * Get NewsName record by record ID and language
     *
     * Use {@link #GetByIdAndLanguage(int id, int language)} to get NewsName record of given record ID and language
     *
     * @param id record ID of search NewsName record
     * @param language language ID of requested Language
     * @return NewsName record of given record ID and language if it exist, else null
     */
    public NewsNameRecord GetByIdAndLanguage(int id, int language){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        for(NewsNameRecord rec : ctx.selectFrom(tmp).where(tmp.ID_NEWS.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return rec;
        return null;
    }

    /**
     * Delete NewsName records by News ID
     *
     * Use {@link #DeleteByNewsId(int id)} to delete NewsName record of given News ID
     *
     * @param id News ID of NewsName records to be deleted
     */
    public void DeleteByNewsId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        NewsName tmp = new NewsName();
        ctx.delete(tmp).where(tmp.ID_NEWS.eq(id)).execute();
    }

    /**
     * Get all NewsName records
     *
     * Use {@link #getConfigs()} to get all NewsName records from database
     *
     * @return list of all NewsName records
     */
    public List<NewsNameRecord> getConfigs() {
        return SelectNewsNameService();
    }
}
