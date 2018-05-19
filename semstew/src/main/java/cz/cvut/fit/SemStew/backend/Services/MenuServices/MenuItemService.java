package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenuItem;
import JOOQ.tables.records.MenuItemRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class MenuItemService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MenuItemService constructor
     */
    public MenuItemService() {}

    /**
     * Get all MenuItem records
     *
     * Use {@link #SelectMenuItemService()} to get all MenuItem records from database
     *
     * @return list of all MenuItem records
     */
    public List<MenuItemRecord> SelectMenuItemService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenuItemRecord> configs = new ArrayList<MenuItemRecord>();
        MenuItem a = new MenuItem();
        for (MenuItemRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MenuItem record
     *
     * Use {@link #UpdateMenuItemService(MenuItemRecord a)} to update given MenuItem record
     *
     * @param a MenuItem record to be updated
     */
    public void UpdateMenuItemService(MenuItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        ctx.update(tmp).set(tmp.AMOUNT, a.getAmount()).set(tmp.ID_UNIT, a.getIdUnit()).
                        set(tmp.IMAGE_NAME, a.getImageName()).set(tmp.PRICE, a.getPrice()).
                        set(tmp.ID_MENU, a.getIdMenu()).
                        where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
    }

    /**
     * Insert new MenuItem record
     *
     * Use {@link #InsertMenuItemService(MenuItemRecord a)} to insert given MenuItem record
     *
     * @param a MenuItem record to be inserted
     */
    public void InsertMenuItemService(MenuItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        ctx.insertInto(tmp).columns(tmp.ID_CATEGORY, tmp.AMOUNT, tmp.ID_UNIT, tmp.IMAGE_NAME, tmp.PRICE, tmp.ID_MENU).
                values(a.getIdCategory(), a.getAmount(), a.getIdUnit(), a.getImageName(), a.getPrice(), a.getIdMenu()).execute();
    }

    /**
     * Delete MenuItem record
     *
     * Use {@link #DeleteMenuItemService(MenuItemRecord a)} to delete given MenuItem record
     *
     * @param a MenuItem record to be deleted
     */
    public void DeleteMenuItemService(MenuItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
    }

    /**
     * Get MenuItem records by Menu ID
     *
     * Use {@link #GetByMenuId(int id)} to get MenuItem records by Menu ID
     *
     * @param id Menu ID of searched MenuItem records
     * @return list of all MenuItem records with Menu ID
     */
    public List<MenuItemRecord> GetByMenuId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        List<MenuItemRecord> ret = new ArrayList<>();
        for(MenuItemRecord rec : ctx.selectFrom(tmp).where(tmp.ID_MENU.eq(id)))
            ret.add(rec);
        return ret;
    }

    /**
     * Get MenuItem record by MenuItem ID
     *
     * Use {@link #GetByMenuItemId(int id)} to get MenuItem record of MenuItem ID
     *
     * @param id MenuItem ID fo searched MenuItem record
     * @return MenuItem record with given MenuItem ID if it exists, else null
     */
    public MenuItemRecord GetByMenuItemId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        for(MenuItemRecord rec : ctx.selectFrom(tmp).where(tmp.ID_MENU_ITEM.eq(id)))
            return rec;
        return null;
    }

    /**
     * Insert MenuItem record and return generated MenuItem ID
     *
     * Use {@link #InsertAndReturn(MenuItemRecord insert)} to insert MenuItem record and get generated MenuItem ID
     *
     * @param insert MenuItem record to be inserted
     * @return generated MenuItem ID
     */
    public int InsertAndReturn(MenuItemRecord insert){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        MenuItemRecord menuItemRecord = ctx.newRecord(tmp);
        menuItemRecord.setIdMenu(insert.getIdMenu());
        menuItemRecord.setIdUnit(insert.getIdUnit());
        menuItemRecord.setIdCategory(insert.getIdCategory());
        menuItemRecord.setPrice(insert.getPrice());
        menuItemRecord.setAmount(insert.getAmount());
        menuItemRecord.setImageName(insert.getImageName());
        menuItemRecord.store();
        return menuItemRecord.getIdMenuItem();
    }

    /**
     * Get all MenuItem records
     *
     * Use {@link #getConfigs()} to get all MenuItem records from database
     *
     * @return list of all MenuItem records
     */
    public List<MenuItemRecord> getConfigs() {
        return SelectMenuItemService();
    }
}
