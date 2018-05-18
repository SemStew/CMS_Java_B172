package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MainCategoriesName;
import JOOQ.tables.records.MainCategoriesNameRecord;
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
public class MainCategoriesNameService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MainCategoriesNameService constructor
     */
    public MainCategoriesNameService() {}

    /**
     * Get all MainCategoriesName records
     *
     * Use {@link #SelectMainCategoriesNameService()} to get all MainCategoriesName records from database
     *
     * @return list of all MainCategoriesName records
     */
    public List<MainCategoriesNameRecord> SelectMainCategoriesNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MainCategoriesNameRecord> configs = new ArrayList<MainCategoriesNameRecord>();
        MainCategoriesName a = new MainCategoriesName();
        for (MainCategoriesNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MainCategoriesName record
     *
     * Use {@link #UpdateMainCategoriesNameService(MainCategoriesNameRecord a)} to update given MainCategoriesName record
     *
     * @param a MainCategoriesName record to be updated
     */
    public void UpdateMainCategoriesNameService(MainCategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert MainCategoriesName record
     *
     * Use {@link #InsertMainCategoriesNameService(MainCategoriesNameRecord a)} to insert given MainCategoriesName record
     *
     * @param a MainCategoriesName record to be inserted
     */
    public void InsertMainCategoriesNameService(MainCategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        ctx.insertInto(tmp).columns(tmp.ID_MAIN_CATEGORY, tmp.ID_LANGUAGE, tmp.NAME).
                values(a.getIdMainCategory(), a.getIdLanguage(), a.getName()).execute();
    }

    /**
     * Delete MainCategoriesName record
     *
     * Use {@link #DeleteMainCategoriesNameService(MainCategoriesNameRecord a)} to delete given MainCategoriesName record
     *
     * @param a MainCategoriesName record to be deleted
     */
    public void DeleteMainCategoriesNameService(MainCategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        ctx.delete(tmp).where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get MainCategoriesName record by ID
     *
     * Use {@link #MainCategoriesNameById(Integer id)} to get MainCategoriesName record of given ID
     *
     * @param id ID of searched MainCategoriesName record
     * @return MainCategoriesName record of given id if it exists, else null
     */
    public MainCategoriesNameRecord MainCategoriesNameById(Integer id)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        for(MainCategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_MAIN_CATEGORY.eq(id)))
            return res;
        return null;
    }

    /**
     * Get MainCategoriesName record by name
     *
     * Use {@link #CategoriesByName(String name)} to get MainCategoriesName record of given name
     *
     * @param name Name of searched MainCategoriesName record
     * @return MainCategoriesName record of given name if it exists, else null
     */
    public  MainCategoriesNameRecord CategoriesByName(String name)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        for(MainCategoriesNameRecord res: ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
            return res;
        return null;
    }

    /**
     * Get MainCategoriesName records descriptions
     *
     * Use {@link #CategoriesDescriptions()} to get MainCategoriesName records decriptions
     *
     * @return list of MainCategoriesName records descriptions
     */
    public List<String> CategoriesDescriptions()
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        List<String> ret = new ArrayList<>();
        for(MainCategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(1)))
        {
            ret.add(res.getName());
        }

        return ret;
    }

    /**
     * Get all MainCategoriesName records
     *
     * Use {@link #getConfigs()} to get all MainCategoriesName records from database
     *
     * @return list of all MainCategoriesName records
     */
    public List<MainCategoriesNameRecord> getConfigs() {
        return SelectMainCategoriesNameService();
    }
}
