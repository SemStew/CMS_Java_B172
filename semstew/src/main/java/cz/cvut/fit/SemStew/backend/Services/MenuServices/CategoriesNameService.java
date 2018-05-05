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

    // Record by ID
    public CategoriesNameRecord CategoriesNameById(Integer id)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        for(CategoriesNameRecord res : ctx.selectFrom(tmp).where(tmp.ID_CATEGORY.eq(id)))
            return res;
        return null;
    }

    // Record by name
    public  CategoriesNameRecord CategoriesByName(String name)
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        CategoriesName tmp = new CategoriesName();
        for(CategoriesNameRecord res: ctx.selectFrom(tmp).where(tmp.DESCRIPTION.eq(name)))
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
            ret.add(res.getDescription());
        }

        return ret;
    }

    public List<CategoriesNameRecord> getConfigs() {
        return configs;
    }
}
