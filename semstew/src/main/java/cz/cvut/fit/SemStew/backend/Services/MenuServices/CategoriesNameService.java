package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.CategoriesName;
import JOOQ.tables.records.CategoriesNameRecord;
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
public class CategoriesNameService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * CategoriesNameService constructor
     */
    public CategoriesNameService() {}

    /**
     * Get all CategoriesName records
     *
     * Use {@link #SelectCategoriesNameService()} to get all CategoriesName records from database
     *
     * @return list of all CategoriesName records
     */
    public List<CategoriesNameRecord> SelectCategoriesNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<CategoriesNameRecord> configs = new ArrayList<CategoriesNameRecord>();
        CategoriesName a = new CategoriesName();
        for (CategoriesNameRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update CategoriesName record
     *
     * Use {@link #UpdateCategoriesNameService(CategoriesNameRecord a)} to update given CategoriesName record
     *
     * @param a CategoriesName record to be updated
     */
    public void UpdateCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert new CategoriesName record
     *
     * Use {@link #InsertCategoriesNameService(CategoriesNameRecord a)} to insert given CategoriesName record
     *
     * @param a CategoriesName to be inserted
     */
    public void InsertCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.insertInto(tmp).columns(tmp.ID_CATEGORY, tmp.ID_LANGUAGE, tmp.NAME).
                values(a.getIdCategory(), a.getIdLanguage(), a.getName()).execute();
    }

    /**
     * Delete CategoriesName record
     *
     * Use {@link #DeleteCategoriesNameService(CategoriesNameRecord a)} to delete given CategoriesName record
     *
     * @param a CategoriesName to be deleted
     */
    public void DeleteCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get CategoriesName record by ID and language
     *
     * Use {@link #CategoriesNameById(Integer id, int language)} to get CategoriesName record by given ID and language
     *
     * @param id Category ID of searched CategoriesName record
     * @param language Language ID of requested language
     * @return CategoriesName record of given specifications if exists, else null
     */
    public CategoriesNameRecord CategoriesNameById(Integer id,int language)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        for(CategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_CATEGORY.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return res;
        return null;
    }

    /**
     * Get CategoriesName record by name
     *
     * Use {@link #CategoriesByName(String name)} to get CategoriesName record of given name
     *
     * @param name Name of seached CategoriesName record
     * @return CategoriesName record with given name if it exist, else null
     */
    public  CategoriesNameRecord CategoriesByName(String name)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        for(CategoriesNameRecord res: ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
            return res;
        return null;
    }

    /**
     * Get all CategoriesName records descriptions by language
     *
     * Use {@link #CategoriesDescriptions(int language)} to get all CategoriesName records descriptions in given language
     *
     * @param language Language ID of requested language
     * @return list of all CategoriesName descriptions in given language
     */
    public List<String> CategoriesDescriptions(int language)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        List<String> ret = new ArrayList<>();
        for(CategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(language)))
        {
            ret.add(res.getName());
        }

        return ret;
    }

    /**
     * Get all CategoriesName records
     *
     * Use {@link #getConfigs()} to get all CategoriesName records from database
     *
     * @return list of all CategoriesName records
     */
    public List<CategoriesNameRecord> getConfigs() {
        return SelectCategoriesNameService();
    }
}
