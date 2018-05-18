package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MainCategories;
import JOOQ.tables.MainCategoriesName;
import JOOQ.tables.records.MainCategoriesRecord;
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
public class MainCategoriesService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MainCategoriesService constructor
     */
    public MainCategoriesService() {}

    /**
     * Get all MainCategories records
     *
     * Use {@link #SelectMainCategoriesService()} to get all MainCategories records from database
     *
     * @return list of all MainCategories records
     */
    public List<MainCategoriesRecord> SelectMainCategoriesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MainCategoriesRecord> configs = new ArrayList<MainCategoriesRecord>();
        MainCategories a = new MainCategories();
        for (MainCategoriesRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MainCategories record
     *
     * Use {@link #UpdateMainCategoriesService(MainCategoriesRecord a)} to update given MainCategories record
     *
     * @param a MainCategories record to be updated
     */
    public void UpdateMainCategoriesService(MainCategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategories tmp = new MainCategories();
        ctx.update(tmp).set(tmp.ID_MAIN_CATEGORY, a.getIdMainCategory()).
                where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).execute();
    }

    /**
     * Insert MainCategories record
     *
     * Use {@link #InsertMainCategoriesService(MainCategoriesRecord a)} to insert given MainCategories record
     *
     * @param a MainCategories record to be inserted
     */
    public void InsertMainCategoriesService(MainCategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategories tmp = new MainCategories();
        ctx.insertInto(tmp).columns(tmp.ID_MAIN_CATEGORY).
                values(a.getIdMainCategory()).execute();
    }

    /**
     * Delete MainCategories record
     *
     * Use {@link #DeleteMainCategoriesService(MainCategoriesRecord a)} to delete given MainCategories record
     *
     * @param a MainCategories record to be deleted
     */
    public void DeleteMainCategoriesService(MainCategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategories tmp = new MainCategories();
        ctx.delete(tmp).where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).execute();
    }

    /**
     * Get all MainCategories records
     *
     * Use {@link #getConfigs()} to get all MainCategories records from database
     *
     * @return list of all MainCategories records
     */
    public List<MainCategoriesRecord> getConfigs() {
        return SelectMainCategoriesService();
    }
}
