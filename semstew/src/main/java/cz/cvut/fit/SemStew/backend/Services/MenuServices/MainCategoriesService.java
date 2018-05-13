package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MainCategories;
import JOOQ.tables.MainCategoriesName;
import JOOQ.tables.records.MainCategoriesRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class MainCategoriesService {
    private DSLContext ctx;

    public MainCategoriesService() {}

    //select
    public List<MainCategoriesRecord> SelectMainCategoriesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MainCategoriesRecord> configs = new ArrayList<MainCategoriesRecord>();
        MainCategories a = new MainCategories();
        for (MainCategoriesRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateMainCategoriesService(MainCategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategories tmp = new MainCategories();
        ctx.update(tmp).set(tmp.ID_MAIN_CATEGORY, a.getIdMainCategory()).
                where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).execute();
    }

    //insert
    public void InsertMainCategoriesService(MainCategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategories tmp = new MainCategories();
        ctx.insertInto(tmp).columns(tmp.ID_MAIN_CATEGORY).
                values(a.getIdMainCategory()).execute();
    }

    //delete
    public void DeleteMainCategoriesService(MainCategoriesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MainCategories tmp = new MainCategories();
        ctx.delete(tmp).where(tmp.ID_MAIN_CATEGORY.eq(a.getIdMainCategory())).execute();
    }

    public List<MainCategoriesRecord> getConfigs() {
        return SelectMainCategoriesService();
    }
}
