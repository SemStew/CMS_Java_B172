package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenuItemAllergen;
import JOOQ.tables.records.MenuItemAllergenRecord;
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
public class MenuItemAllergenService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MenuItemAllergenService constructor
     */
    public MenuItemAllergenService() {}

    /**
     * Get all MenuItemAllergen records
     *
     * Use {@link #SelectMenuItemAllergenService()} to get all MenuItemAllergen records from database
     *
     * @return list of all MenuItemAllergen records
     */
    public List<MenuItemAllergenRecord> SelectMenuItemAllergenService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenuItemAllergenRecord> configs = new ArrayList<MenuItemAllergenRecord>();
        MenuItemAllergen a = new MenuItemAllergen();
        for (MenuItemAllergenRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MenuItemAllergen record
     *
     * Use {@link #UpdateMenuItemAllergenService(MenuItemAllergenRecord a)} to update given MenuItemAllergen record
     *
     * @param a MenuItemAllergen record to be updated
     */
    public void UpdateMenuItemAllergenService(MenuItemAllergenRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Insert new MenuItemAllergen record
     *
     * Use {@link #InsertMenuItemAllergenService(MenuItemAllergenRecord a)} to insert given MenuItemAllergen record
     *
     * @param a MenuItemAllergen record to be inserted
     */
    public void InsertMenuItemAllergenService(MenuItemAllergenRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.insertInto(tmp).columns(tmp.ID_ALLERGEN, tmp.ID_MENU_ITEM).values(a.getIdAllergen(), a.getIdMenuItem()).execute();
    }

    /**
     * Delete MenuItemAllergen record
     *
     * Use {@link #DeleteMenuItemAllergenService(MenuItemAllergenRecord a)} to delete given MenuItemAllergen record
     *
     * @param a MenuItemAllergen record to be deleted
     */
    public void DeleteMenuItemAllergenService(MenuItemAllergenRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.delete(tmp).where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
    }

    /**
     * Delete MenuItemAllergen records by MenuItem ID
     *
     * Use {@link #DeleteByMenuItemId(Integer id)} to delete MenuItemAllergen records by given MenuItem ID
     *
     * @param id MenuItem ID of MenuItemAllergen records to delete
     */
    public void DeleteByMenuItemId(Integer id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.delete(tmp).where(tmp.ID_MENU_ITEM.eq(id)).execute();
    }

    /**
     * Get MenuItemAllergen records by MenuItem ID
     *
     * Use {@link #GetByMenuItemId(Integer)} to get MenuItemAllergen records by given MenuItem ID
     *
     * @param id MenuItem ID of searched MenuItemAllergen records
     * @return list of all MenuItemAllergen records of given MenuItem ID
     */
    public List<MenuItemAllergenRecord> GetByMenuItemId(Integer id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        List<MenuItemAllergenRecord> ret = new ArrayList<>();
        for(MenuItemAllergenRecord rec : ctx.selectFrom(tmp).where(tmp.ID_MENU_ITEM.eq(id)))
            ret.add(rec);
        return ret;
    }

    /**
     * Get all MenuItemAllergen records
     *
     * Use {@link #getConfigs()} to get all MenuItemAllergen records from database
     *
     * @return list of all MenuItemAllergen records
     */
    public List<MenuItemAllergenRecord> getConfigs() {
        return SelectMenuItemAllergenService();
    }
}
