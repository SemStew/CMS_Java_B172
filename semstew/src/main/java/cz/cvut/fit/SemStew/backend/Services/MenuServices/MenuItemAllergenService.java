package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenuItemAllergen;
import JOOQ.tables.records.MenuItemAllergenRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MenuItemAllergenService {
    private List<MenuItemAllergenRecord> configs;
    private DSLContext ctx;

    public MenuItemAllergenService() {
        SelectMenuItemAllergenService();
    }

    //select
    public void SelectMenuItemAllergenService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<MenuItemAllergenRecord>();
        MenuItemAllergen a = new MenuItemAllergen();
        for (MenuItemAllergenRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateMenuItemAllergenService(MenuItemAllergenRecord a){
        SelectMenuItemAllergenService();
    }

    //insert
    public void InsertMenuItemAllergenService(MenuItemAllergenRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.insertInto(tmp).columns(tmp.ID_ALLERGEN, tmp.ID_MENU_ITEM).values(a.getIdAllergen(), a.getIdMenuItem()).execute();
        SelectMenuItemAllergenService();
    }

    //delete
    public void DeleteMenuItemAllergenService(MenuItemAllergenRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.delete(tmp).where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
        SelectMenuItemAllergenService();
    }

    public List<MenuItemAllergenRecord> getConfigs() {
        return configs;
    }
}
