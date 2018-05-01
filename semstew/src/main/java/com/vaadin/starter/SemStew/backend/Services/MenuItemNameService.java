package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.MenuItemName;
import JOOQ.tables.records.MenuItemNameRecord;
import JOOQ.tables.records.MenuItemRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MenuItemNameService {
    private List<MenuItemNameRecord> configs;
    private DSLContext ctx;

    public MenuItemNameService() {
        SelectMenuItemNameService();
    }

    //select
    public void SelectMenuItemNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<MenuItemNameRecord>();
        MenuItemName a = new MenuItemName();
        for (MenuItemNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateMenuItemNameService(MenuItemNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.update(tmp).set(tmp.DESCRIPTION, a.getDescription()).set(tmp.NAME, a.getName()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
        SelectMenuItemNameService();
    }

    //insert
    public void InsertMenuItemNameService(MenuItemNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.insertInto(tmp).columns(tmp.ID_MENU_ITEM, tmp.ID_LANGUAGE, tmp.NAME, tmp.DESCRIPTION).
                            values(a.getIdMenuItem(), a.getIdLanguage(), a.getName(), a.getDescription()).execute();
        SelectMenuItemNameService();
    }

    //delete
    public void DeleteMenuItemNameService(MenuItemNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).and(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).execute();
        SelectMenuItemNameService();
    }

    // Item by id
    public MenuItemNameRecord ItemNameById(Integer id)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenuItemName tmp = new MenuItemName();
        for(MenuItemNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_MENU_ITEM.eq(id)))
            return res;
        return null;
    }

    public List<MenuItemNameRecord> getConfigs() {
        return configs;
    }
}
