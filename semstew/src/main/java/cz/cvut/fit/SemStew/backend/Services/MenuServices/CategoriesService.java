package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.Categories;
import JOOQ.tables.records.CategoriesRecord;
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
public class CategoriesService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * CategoriesService constructor
     */
    public CategoriesService() {}

    /**
     * Get all Categories records
     *
     * Use {@link #SelectCategoriesService()} to get all Categories records from database
     *
     * @return list of all Categories records
     */
    public List<CategoriesRecord> SelectCategoriesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<CategoriesRecord> configs = new ArrayList<CategoriesRecord>();
        Categories a = new Categories();
        for (CategoriesRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Categories record
     *
     * Use {@link #UpdateCategoriesService(CategoriesRecord a)} to update given Categories record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a Categories record to be updated
     */
    public void UpdateCategoriesService(CategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Categories tmp = new Categories();
        ctx.update(tmp).set(tmp.ID_MAIN_CATEGORY, a.getIdMainCategory()).
                where(tmp.ID_CATEGORY.eq(a.getIdCategory())).execute();
    }

    /**
     * Insert new Categories record
     *
     * Use {@link #InsertCategoriesService(CategoriesRecord a)} to insert given Categories record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a Categories record to be inserted
     */
    public void InsertCategoriesService(CategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Categories tmp = new Categories();
        ctx.insertInto(tmp).columns(tmp.ID_MAIN_CATEGORY).
                values(a.getIdMainCategory()).execute();
    }

    /**
     * Delete Categories record
     *
     * Use {@link #DeleteCategoriesService(CategoriesRecord a)} to delete given Categories record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a Categories record to be deleted
     */
    public void DeleteCategoriesService(CategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Categories tmp = new Categories();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).execute();
    }

    /**
     * Get all Categories records
     *
     * Use {@link #getConfigs()} to get all Categories records from database
     *
     * @return list of all Categories records
     */
    public List<CategoriesRecord> getConfigs() {
        return SelectCategoriesService();
    }
}
