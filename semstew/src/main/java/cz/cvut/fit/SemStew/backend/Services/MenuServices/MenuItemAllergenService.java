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
    private DSLContext ctx;

    public MenuItemAllergenService() {}

    //select
    public List<MenuItemAllergenRecord> SelectMenuItemAllergenService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenuItemAllergenRecord> configs = new ArrayList<MenuItemAllergenRecord>();
        MenuItemAllergen a = new MenuItemAllergen();
        for (MenuItemAllergenRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateMenuItemAllergenService(MenuItemAllergenRecord a){
        System.out.println("Method not implemented");
    }

    //insert
    public void InsertMenuItemAllergenService(MenuItemAllergenRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.insertInto(tmp).columns(tmp.ID_ALLERGEN, tmp.ID_MENU_ITEM).values(a.getIdAllergen(), a.getIdMenuItem()).execute();
    }

    //delete
    public void DeleteMenuItemAllergenService(MenuItemAllergenRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemAllergen tmp = new MenuItemAllergen();
        ctx.delete(tmp).where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
    }

    public List<MenuItemAllergenRecord> getConfigs() {
        return SelectMenuItemAllergenService();
    }
}
