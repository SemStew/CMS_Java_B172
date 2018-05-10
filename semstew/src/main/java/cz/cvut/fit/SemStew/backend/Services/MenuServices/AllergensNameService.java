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
    private DSLContext ctx;

    public AllergensNameService() {}

    //select
    public List<AllergensNameRecord> SelectAllergensNameService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AllergensNameRecord> configs = new ArrayList<AllergensNameRecord>();
        AllergensName a = new AllergensName();
        for (AllergensNameRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.update(tmp).set(tmp.ALLERGEN, a.getAllergen()).
                where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    //insert
    public void InsertAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.insertInto(tmp).columns(tmp.ID_ALLERGEN, tmp.ID_LANGUAGE, tmp.ALLERGEN).
                values(a.getIdAllergen(), a.getIdLanguage(), a.getAllergen()).execute();
    }

    //delete
    public void DeleteAllergensNameService(AllergensNameRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AllergensName tmp = new AllergensName();
        ctx.delete(tmp).where(tmp.ID_ALLERGEN.eq(a.getIdAllergen())).and(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    public List<AllergensNameRecord> getConfigs() {
        return SelectAllergensNameService();
    }
}
