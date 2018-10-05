package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.AllergensName;
import JOOQ.tables.records.AllergensNameRecord;
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
public class AllergensNameService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * AllergensNameService constructor
     */
    public AllergensNameService() {}

    /**
     * Get all AllergensName records
     *
     * Use {@link #SelectAllergensNameService()} to get all AllergensName records from database
     *
     * @return list of all AllergensName records
     */
    public List<AllergensNameRecord> SelectAllergensNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AllergensNameRecord> configs = new ArrayList<AllergensNameRecord>();
        AllergensName a = new AllergensName();
        for (AllergensNameRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update AllergensName record
     *
     * Use {@link #UpdateAllergensNameService(AllergensNameRecord a)} to update given AllergensName record
     *
     * @param a AllergensName record to be updated
     */
    public void UpdateAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.update(tmp).set(tmp.ALLERGEN, a.getAllergen()).
                where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert new AllergensName record
     *
     * Use {@link #InsertAllergensNameService(AllergensNameRecord a)} to insert given AllergensName record
     *
     * @param a AllergensRecord to be inserted
     */
    public void InsertAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.insertInto(tmp).columns(tmp.ID_ALLERGEN, tmp.ID_LANGUAGE, tmp.ALLERGEN).
                values(a.getIdAllergen(), a.getIdLanguage(), a.getAllergen()).execute();
    }

    /**
     * Delete AllergensName record
     *
     * Use {@link #DeleteAllergensNameService(AllergensNameRecord a)} to delete given AllergensName record
     *
     * @param a Allergens record to be deleted
     */
    public void DeleteAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.delete(tmp).where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get AllergensName record by ID and language
     *
     * Use {@link #GetById(Integer id, int language)} to get AllergensName record of given ID and language
     *
     * @param id Allergen ID of searched AllergensName record
     * @param language Language ID of searched AllergensName record
     * @return AllergensName record of given ID and language if it exists, else null
     */
    public AllergensNameRecord GetById(Integer id, int language){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        for(AllergensNameRecord rec : ctx.selectFrom(tmp).where(tmp.ID_ALLERGEN.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return rec;
        return null;
    }

    /**
     * Get AllergensName record by description
     *
     * Use {@link #GetByDescription(String description)} to get AllergensName record of given description
     *
     * @param description Description of searched AllergensName record
     * @return AllergensName record of given specifications if it exist, else null
     */
    public AllergensNameRecord GetByDescription(String description){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        for(AllergensNameRecord rec : ctx.selectFrom(tmp).where(tmp.ALLERGEN.eq(description)))
            return rec;
        return null;
    }

    /**
     * Get all AllergensName records descriptions by language
     *
     * Use {@link #GetAllDescriptionsInLanguage(int language)} to get all AllergensName records descriptions of given language
     *
     * @param language language id of requested language
     * @return list of all AllergensName descriptions
     */
    public List<String> GetAllDescriptionsInLanguage(int language) {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        List<String> ret = new ArrayList<>();
        for(AllergensNameRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(language)))
            ret.add(rec.getAllergen());
        return ret;
    }

    /**
     * Get all AllergensName records
     *
     * Use {@link #getConfigs()} to get all AllergensName records from database
     *
     * @return list of all AllergensName records
     */
    public List<AllergensNameRecord> getConfigs() {
        return SelectAllergensNameService();
    }
}
