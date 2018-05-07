package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.Categories;
import JOOQ.tables.records.CategoriesRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class CategoriesService {
    private List<CategoriesRecord> configs;
    private DSLContext ctx;

    public CategoriesService() {
        SelectCategoriesService();
    }

    //select
    public void SelectCategoriesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<CategoriesRecord>();
        Categories a = new Categories();
        for (CategoriesRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateCategoriesService(CategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Categories tmp = new Categories();
        ctx.update(tmp).set(tmp.ID_MAIN_CATEGORY, a.getIdMainCategory()).
                where(tmp.ID_CATEGORY.eq(a.getIdCategory())).execute();
        SelectCategoriesService();
    }

    //insert
    public void InsertCategoriesService(CategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Categories tmp = new Categories();
        ctx.insertInto(tmp).columns(tmp.ID_MAIN_CATEGORY).
                values(a.getIdMainCategory()).execute();
        SelectCategoriesService();
    }

    //delete
    public void DeleteCategoriesService(CategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Categories tmp = new Categories();
        ctx.delete(tmp).where(tmp.ID_CATEGORY.eq(a.getIdCategory())).execute();
        SelectCategoriesService();
    }

    public List<CategoriesRecord> getConfigs() {
        return configs;
    }
}
