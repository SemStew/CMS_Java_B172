package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.AllergensName;
import JOOQ.tables.records.AllergensNameRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class AllergensNameService {
    private List<AllergensNameRecord> configs;
    private DSLContext ctx;

    public AllergensNameService() {
        SelectAllergensNameService();
    }

    //select
    public void SelectAllergensNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<AllergensNameRecord>();
        AllergensName a = new AllergensName();
        for (AllergensNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.update(tmp).set(tmp.ALLERGEN, a.getAllergen()).
                where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectAllergensNameService();
    }

    //insert
    public void InsertAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.insertInto(tmp).columns(tmp.ID_ALLERGEN, tmp.ID_LANGUAGE, tmp.ALLERGEN).
                values(a.getIdAllergen(), a.getIdLanguage(), a.getAllergen()).execute();
        SelectAllergensNameService();
    }

    //delete
    public void DeleteAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.delete(tmp).where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectAllergensNameService();
    }

    public List<AllergensNameRecord> getConfigs() {
        return configs;
    }
}
