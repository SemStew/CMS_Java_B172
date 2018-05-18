package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Languages;
import JOOQ.tables.records.LanguagesRecord;
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
public class LanguagesService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * LanguageServis constructor
     */
    public LanguagesService() {}

    /**
     * Get all Language records
     *
     * Use {@link #SelectLanguagesService()} to get all Language records from database
     *
     * @return list of all Language records
     */
    public List<LanguagesRecord> SelectLanguagesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<LanguagesRecord> configs = new ArrayList<LanguagesRecord>();
        Languages a = new Languages();
        for (LanguagesRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update given Language record
     *
     * Use {@link #UpdateLanguagesService(LanguagesRecord a)} to update given Language record
     *
     * @param a Language record to be updated
     */
    public void UpdateLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert new Language record
     *
     * Use {@link #InsertLanguagesService(LanguagesRecord a)} to insert given Language record
     *
     * @param a Language record to be inserted
     */
    public void InsertLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
    }

    /**
     * Delete Language record
     *
     * Use {@link #DeleteLanguagesService(LanguagesRecord a)} to delete given Language record
     *
     * @param a Language record to be deleted
     */
    public void DeleteLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get Language record id by name
     *
     * Use {@link #GetIdByName(String name)} to get Language record id of given name
     *
     * @param name name of searched Language record id
     * @return Language record id of given name if it exist, else -1
     */
    public int GetIdByName(String name){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        for(LanguagesRecord rec : ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
            return rec.getIdLanguage();
        return -1;
    }

    /**
     * Get all Language records
     *
     * Use {@link #getConfigs()} to get all Languages records
     *
     * @return list of all Languages records
     */
    public List<LanguagesRecord> getConfigs() {
        return SelectLanguagesService();
    }
}
