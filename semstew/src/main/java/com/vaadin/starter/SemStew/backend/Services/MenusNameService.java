package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.MenusName;
import JOOQ.tables.records.MenusNameRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MenusNameService {
    private List<MenusNameRecord> configs;
    private DSLContext ctx;

    public MenusNameService() {
        SelectMenusNameService();
    }

    //select
    public void SelectMenusNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<MenusNameRecord>();
        MenusName a = new MenusName();
        for (MenusNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateMenusNameService(MenusNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.update(tmp).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
        SelectMenusNameService();
    }

    //insert
    public void InsertMenusNameService(MenusNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.ID_MENU, tmp.DESCRIPTION).
                values(a.getIdLanguage(), a.getIdMenu(), a.getDescription()).execute();
        SelectMenusNameService();
    }

    //delete
    public void DeleteMenusNameService(MenusNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusName tmp = new MenusName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
        SelectMenusNameService();
    }

    public List<MenusNameRecord> getConfigs() {
        return configs;
    }
}
