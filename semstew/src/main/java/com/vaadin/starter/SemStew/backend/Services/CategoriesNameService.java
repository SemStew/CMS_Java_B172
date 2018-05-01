package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.CategoriesName;
import JOOQ.tables.records.CategoriesNameRecord;
import JOOQ.tables.records.CategoriesNameRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class CategoriesNameService {
    private List<CategoriesNameRecord> configs;
    private DSLContext ctx;

    public CategoriesNameService() {
        SelectCategoriesNameService();
    }

    //select
    public void SelectCategoriesNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<CategoriesNameRecord>();
        CategoriesName a = new CategoriesName();
        for (CategoriesNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.update(tmp).set(tmp.DESCRIPTION, a.getDescription()).
                where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectCategoriesNameService();
    }

    //insert
    public void InsertCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.insertInto(tmp).columns(tmp.ID_CATEGORY, tmp.ID_LANGUAGE, tmp.DESCRIPTION).
                values(a.getIdCategory(), a.getIdLanguage(), a.getDescription()).execute();
        SelectCategoriesNameService();
    }

    //delete
    public void DeleteCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectCategoriesNameService();
    }

    public List<CategoriesNameRecord> getConfigs() {
        return configs;
    }
}
