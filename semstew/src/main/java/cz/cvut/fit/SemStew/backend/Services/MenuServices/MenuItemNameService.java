package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenuItemName;
import JOOQ.tables.records.MenuItemNameRecord;
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
public class MenuItemNameService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MenuItemNameService constructor
     */
    public MenuItemNameService() {}

    /**
     * Get all MenuItemName records
     *
     * Use {@link #SelectMenuItemNameService()} to get all MenuItemName records from database
     *
     * @return list of all MenuItemName records
     */
    public List<MenuItemNameRecord> SelectMenuItemNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenuItemNameRecord> configs = new ArrayList<MenuItemNameRecord>();
        MenuItemName a = new MenuItemName();
        for (MenuItemNameRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MenuItemName record
     *
     * Use {@link #UpdateMenuItemNameService(MenuItemNameRecord a)} to update given MenuItemName record
     *
     * @param a MenuItemName record to be updated
     */
    public void UpdateMenuItemNameService(MenuItemNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.update(tmp).set(tmp.DESCRIPTION, a.getDescription()).set(tmp.NAME, a.getName()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
    }

    /**
     * Insert new MenuItemName record
     *
     * Use {@link #InsertMenuItemNameService(MenuItemNameRecord a)} to insert given MenuItemName record
     *
     * @param a MenuItemName record to be inserted
     */
    public void InsertMenuItemNameService(MenuItemNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.insertInto(tmp).columns(tmp.ID_MENU_ITEM, tmp.ID_LANGUAGE, tmp.NAME, tmp.DESCRIPTION).
                            values(a.getIdMenuItem(), a.getIdLanguage(), a.getName(), a.getDescription()).execute();
    }

    /**
     * Delete MenuItemName record
     *
     * Use {@link #DeleteMenuItemNameService(MenuItemNameRecord a)} to delete given MenuItemName record
     *
     * @param a MenuItemName record to be deleted
     */
    public void DeleteMenuItemNameService(MenuItemNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
    }

    /**
     * Get MenuItemName record by ID and language
     *
     * Use {@link #ItemNameById(Integer id, int language)} to get MenuItemName record by given ID and language
     *
     * @param id ID of searched item
     * @param language requested language id
     * @return MenuItemName record with given parameters if exists, else null
     */
    public MenuItemNameRecord ItemNameById(Integer id, int language)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        for(MenuItemNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_MENU_ITEM.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return res;
        return null;
    }

    /**
     * Delete MenuItemName record by MenuItem ID
     *
     * Use {@link #DeleteByMenuItemId(int menuItemId)} to delete MenuItemName record of given MenuItem ID
     *
     * @param menuItemId MenuItem ID of MenuItemName record to delete
     */
    public void DeleteByMenuItemId(int menuItemId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.delete(tmp).where(tmp.ID_MENU_ITEM.eq(menuItemId)).execute();
    }

    /**
     * Get all MenuItemName records
     *
     * Use {@link #getConfigs()} to get all MenuItemName records from database
     *
     * @return list of all MenuItemName records
     */
    public List<MenuItemNameRecord> getConfigs() {
        return SelectMenuItemNameService();
    }
}
