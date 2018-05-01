package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Menus;
import JOOQ.tables.records.MenusRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MenusService {
    private List<MenusRecord> configs;
    private DSLContext ctx;

    public MenusService() {
        SelectMenusService();
    }

    //select
    public void SelectMenusService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<MenusRecord>();
        Menus a = new Menus();
        for (MenusRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateMenusService(MenusRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        ctx.update(tmp).set(tmp.URL_IMAGE, a.getUrlImage()).
                where(tmp.ID_BRANCH.eq(a.getIdBranch())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
        SelectMenusService();
    }

    //insert
    public void InsertMenusService(MenusRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.ID_MENU, tmp.URL_IMAGE).
                values(a.getIdBranch(), a.getIdMenu(), a.getUrlImage()).execute();
        SelectMenusService();
    }

    //delete
    public void DeleteMenusService(MenusRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Menus tmp = new Menus();
        ctx.delete(tmp).where(tmp.ID_BRANCH.eq(a.getIdBranch())).and(tmp.ID_MENU.eq(a.getIdMenu())).execute();
        SelectMenusService();
    }

    public List<MenusRecord> getConfigs() {
        return configs;
    }
}
