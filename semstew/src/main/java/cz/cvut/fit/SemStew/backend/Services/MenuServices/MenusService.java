package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.Menus;
import JOOQ.tables.records.MenusRecord;
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
public class MenusService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     *  MenusService constructor
     */
    public MenusService() {}

    /**
     * Get all Menus records
     *
     * Use {@link #SelectMenusService()} to get all Menus records from database
     *
     * @return list of all Menus records
     */
    public List<MenusRecord> SelectMenusService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenusRecord> configs = new ArrayList<MenusRecord>();
        Menus a = new Menus();
        for (MenusRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Menus record
     *
     * Use {@link #UpdateMenusService(MenusRecord a)} to update given Menus record
     *
     * @param a Menus record to be updated
     */
    public void UpdateMenusService(MenusRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        ctx.update(tmp).set(tmp.URL_IMAGE, a.getUrlImage()).
                where(tmp.ID_BRANCH.eq(a.getIdBranch())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
    }

    /**
     * Insert new Menus record
     *
     * Use {@link #InsertMenusService(MenusRecord a)} to insert given Menus record
     *
     * @param a Menus record to be inserted
     */
    public void InsertMenusService(MenusRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.URL_IMAGE).
                values(a.getIdBranch(), a.getUrlImage()).execute();
    }

    /**
     * Delete Menus record
     *
     * Use {@link #DeleteMenusService(MenusRecord a)} to delete given Menus record
     *
     * @param a Menus record to be deleted
     */
    public void DeleteMenusService(MenusRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        ctx.delete(tmp).where(tmp.ID_BRANCH.eq(a.getIdBranch())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
    }

    /**
     * Insert Menus record and return generated Menus ID
     *
     * Use {@link #InsertAndReturn(MenusRecord insert)} to insert given Menus record and return generated Menus ID
     *
     * @param insert Menus record to be inserted
     * @return generated Menus ID
     */
    public int InsertAndReturn(MenusRecord insert){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        MenusRecord menusRecord = ctx.newRecord(tmp);
        menusRecord.setUrlImage(insert.getUrlImage());
        menusRecord.setIdBranch(insert.getIdBranch());
        menusRecord.store();
        return menusRecord.getIdMenu();
    }

    /**
     * Get all Menus records
     *
     * Use {@link #getConfigs()} to get all Menus records from database
     *
     * @return list of all Menus records
     */
    public List<MenusRecord> getConfigs() {
        return SelectMenusService();
    }
}
