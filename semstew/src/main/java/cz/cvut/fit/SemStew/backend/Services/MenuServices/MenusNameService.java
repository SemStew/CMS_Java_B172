package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenusName;
import JOOQ.tables.records.MenusNameRecord;
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
public class MenusNameService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MenusNameService constructor
     */
    public MenusNameService() {}

    /**
     * Get all MenusName records
     *
     * Use {@link #SelectMenusNameService()} to get all MenusName records from database
     *
     * @return list of all MenusName records
     */
    public List<MenusNameRecord> SelectMenusNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenusNameRecord> configs = new ArrayList<MenusNameRecord>();
        MenusName a = new MenusName();
        for (MenusNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MenusName record
     *
     * Use {@link #UpdateMenusNameService(MenusNameRecord a)} to update given MenusName record
     *
     * @param a MenusName record to be updated
     */
    public void UpdateMenusNameService(MenusNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.update(tmp).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
    }

    /**
     * Insert new MenusName record
     *
     * Use {@link #InsertMenusNameService(MenusNameRecord a)} to insert given MenusName record
     *
     * @param a MenusName record to be inserted
     */
    public void InsertMenusNameService(MenusNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.ID_MENU, tmp.DESCRIPTION).
                values(a.getIdLanguage(), a.getIdMenu(), a.getDescription()).execute();
    }

    /**
     * Delete MenusName record
     *
     * Use {@link #DeleteMenusNameService(MenusNameRecord a)} to delete given MenusName record
     *
     * @param a MenusName record to be deleted
     */
    public void DeleteMenusNameService(MenusNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
    }

    /**
     *  Get MenusName record by ID and language
     *
     * Use {@link #GetById(Integer id, int language)} to get MenusName record of ID and language
     *
     * @param id id of searched MenusName record
     * @param language requested language of MenusName record
     * @return MenusName record of given parameters if it exists, else null
     */
    public MenusNameRecord GetById(Integer id, int language){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        for(MenusNameRecord rec : ctx.selectFrom(tmp).where(tmp.ID_MENU.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return rec;
        return null;
    }

    /**
     * Delete MenusName records of given Menu ID
     *
     * Use {@link #DeleteByMenuId(int menuID)} to delete MenusName records of given Menu ID
     *
     * @param menuId Menu ID of MenusName records to be deleted
     */
    public void DeleteByMenuId(int menuId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.delete(tmp).where(tmp.ID_MENU.eq(menuId)).execute();
    }

    /**
     * Get all MenusName records
     *
     * Use {@link #getConfigs()} to get all MenusName records from database
     *
     * @return list of all MenusName records
     */
    public List<MenusNameRecord> getConfigs() {
        return SelectMenusNameService();
    }
}
