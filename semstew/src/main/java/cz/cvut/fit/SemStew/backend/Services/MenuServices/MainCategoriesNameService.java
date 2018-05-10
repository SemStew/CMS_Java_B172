package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MainCategoriesName;
import JOOQ.tables.records.MainCategoriesNameRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MainCategoriesNameService {
    private DSLContext ctx;

    public MainCategoriesNameService() {}

    //select
    public List<MainCategoriesNameRecord> SelectMainCategoriesNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MainCategoriesNameRecord> configs = new ArrayList<MainCategoriesNameRecord>();
        MainCategoriesName a = new MainCategoriesName();
        for (MainCategoriesNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateMainCategoriesNameService(MainCategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    //insert
    public void InsertMainCategoriesNameService(MainCategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        ctx.insertInto(tmp).columns(tmp.ID_MAIN_CATEGORY, tmp.ID_LANGUAGE, tmp.NAME).
                values(a.getIdMainCategory(), a.getIdLanguage(), a.getName()).execute();
    }

    //delete
    public void DeleteMainCategoriesNameService(MainCategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        ctx.delete(tmp).where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    // Record by ID
    public MainCategoriesNameRecord MainCategoriesNameById(Integer id)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        for(MainCategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_MAIN_CATEGORY.eq(id)))
            return res;
        return null;
    }

    // Record by name
    public  MainCategoriesNameRecord CategoriesByName(String name)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        for(MainCategoriesNameRecord res: ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
            return res;
        return null;
    }

    // Only descriptions
    public List<String> CategoriesDescriptions()
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategoriesName tmp = new MainCategoriesName();
        List<String> ret = new ArrayList<>();
        for(MainCategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(1)))
        {
            ret.add(res.getName());
        }

        return ret;
    }

    public List<MainCategoriesNameRecord> getConfigs() {
        return SelectMainCategoriesNameService();
    }
}
