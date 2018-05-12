package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenuItem;
import JOOQ.tables.records.MenuItemRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MenuItemService {
    private List<MenuItemRecord> configs;
    private DSLContext ctx;

    public MenuItemService() {
        SelectMenuItemService();
    }

    //select
    public void SelectMenuItemService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<MenuItemRecord>();
        MenuItem a = new MenuItem();
        for (MenuItemRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateMenuItemService(MenuItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        ctx.update(tmp).set(tmp.AMOUNT, a.getAmount()).set(tmp.ID_UNIT, a.getIdUnit()).
                        set(tmp.IMAGE_NAME, a.getImageName()).set(tmp.PRICE, a.getPrice()).
                        set(tmp.ID_MENU, a.getIdMenu()).
                        where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
        SelectMenuItemService();
    }

    //insert
    public void InsertMenuItemService(MenuItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        ctx.insertInto(tmp).columns(tmp.ID_MENU_ITEM, tmp.ID_CATEGORY, tmp.AMOUNT, tmp.ID_UNIT, tmp.IMAGE_NAME,
                                    tmp.PRICE, tmp.ID_MENU).
                values(a.getIdMenuItem(), a.getIdCategory(), a.getAmount(), a.getIdUnit(), a.getImageName(),
                        a.getPrice(), a.getIdMenu()).execute();
        SelectMenuItemService();
    }

    //delete
    public void DeleteMenuItemService(MenuItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItem tmp = new MenuItem();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
        SelectMenuItemService();
    }

    public List<MenuItemRecord> getConfigs() {
        return configs;
    }
}
