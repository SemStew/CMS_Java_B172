package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.CategoriesName;
import JOOQ.tables.records.CategoriesNameRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class CategoriesNameService {
    private DSLContext ctx;

    public CategoriesNameService() {}

    //select
    public List<CategoriesNameRecord> SelectCategoriesNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<CategoriesNameRecord> configs = new ArrayList<CategoriesNameRecord>();
        CategoriesName a = new CategoriesName();
        for (CategoriesNameRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    //insert
    public void InsertCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.insertInto(tmp).columns(tmp.ID_CATEGORY, tmp.ID_LANGUAGE, tmp.NAME).
                values(a.getIdCategory(), a.getIdLanguage(), a.getName()).execute();
    }

    //delete
    public void DeleteCategoriesNameService(CategoriesNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    // Record by ID and language ID
    public CategoriesNameRecord CategoriesNameById(Integer id,int language)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        for(CategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_CATEGORY.eq(id)).and(tmp.ID_LANGUAGE.eq(language)))
            return res;
        return null;
    }

    // Record by name
    public  CategoriesNameRecord CategoriesByName(String name)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        for(CategoriesNameRecord res: ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
            return res;
        return null;
    }

    // Only descriptions
    public List<String> CategoriesDescriptions()
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        List<String> ret = new ArrayList<>();
        for(CategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(1)))
        {
            ret.add(res.getName());
        }

        return ret;
    }

    public List<CategoriesNameRecord> getConfigs() {
        return SelectCategoriesNameService();
    }
}
