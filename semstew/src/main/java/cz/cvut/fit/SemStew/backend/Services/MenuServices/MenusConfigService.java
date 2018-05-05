package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenusConfig;
import JOOQ.tables.records.MenusConfigRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MenusConfigService {
    private List<MenusConfigRecord> configs;
    private DSLContext ctx;

    public MenusConfigService() {
        SelectMenusConfigService();
    }

    //select
    public void SelectMenusConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<MenusConfigRecord>();
        MenusConfig a = new MenusConfig();
        for (MenusConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateMenusConfigService(MenusConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectMenusConfigService();
    }

    //insert
    public void InsertMenusConfigService(MenusConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER).
                values(a.getIdLanguage(), a.getHeader()).execute();
        SelectMenusConfigService();
    }

    //delete
    public void DeleteMenusConfigService(MenusConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectMenusConfigService();
    }

    public List<MenusConfigRecord> getConfigs() {
        return configs;
    }
}
