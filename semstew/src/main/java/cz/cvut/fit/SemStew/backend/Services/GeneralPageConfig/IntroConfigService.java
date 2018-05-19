package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.IntroConfig;
import JOOQ.tables.records.IntroConfigRecord;
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
public class IntroConfigService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * IntroConfigServis constructor
     */
    public IntroConfigService() {}


    /**
     * Get all IntroConfig records
     *
     * Use {@link #SelectIntroConfigService()} to get all IntroConfig  records from database
     *
     * @return list of all IntroConfig records
     */
    public List<IntroConfigRecord> SelectIntroConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<IntroConfigRecord> configs = new ArrayList<IntroConfigRecord>();
        IntroConfig a = new IntroConfig();
        for (IntroConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Updates IntroConfig record
     *
     * Use {@link #UpdateIntroConfigService(IntroConfigRecord a)} to update given IntroConfig
     *
     * @param a IntroConfig to be updated
     */
    public void UpdateIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.NEWS_HEADER, a.getNewsHeader()).
                set(tmp.SHORT_DESCRIPTION, a.getShortDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Inserts new IntroConfig record
     *
     * Use {@link #InsertIntroConfigService(IntroConfigRecord a)} to insert given IntroConfig record
     *
     * @param a IntroConfig record to be inserted
     */
    public void InsertIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER, tmp.NEWS_HEADER, tmp.SHORT_DESCRIPTION).
                values(a.getIdLanguage(), a.getHeader(), a.getNewsHeader(), a.getShortDescription()).execute();
    }

    /**
     * Deletes IntroConfig record
     *
     * Use {@link #DeleteIntroConfigService(IntroConfigRecord a)} to delete given IntroConfig record
     *
     * @param a IntroConfig record to be deleted
     */
    public void DeleteIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get IntroConfig record in given language
     *
     * Use {@link #GetByLanquageID(int id)} to get IntroConfig of given language
     *
     * @param id identification number of requested language
     * @return IntroConfig record of given lanquage if it exist, else null
     */
    public IntroConfigRecord GetByLanquageID(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        for(IntroConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(id)))
            return rec;
        return null;
    }

    /**
     * Get all IntroConfig records
     *
     * Use {@link #getConfigs()} to get all IntroConfig records from database
     *
     * @return list of all IntroConfig records
     */
    public List<IntroConfigRecord> getConfigs() {
        return SelectIntroConfigService();
    }
}
