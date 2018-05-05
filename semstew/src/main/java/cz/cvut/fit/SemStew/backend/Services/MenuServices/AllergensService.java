package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.Allergens;
import JOOQ.tables.records.AllergensRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class AllergensService {
    private List<AllergensRecord> configs;
    private DSLContext ctx;

    public AllergensService() {
        SelectAllergensService();
    }

    //select
    public void SelectAllergensService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<AllergensRecord>();
        Allergens a = new Allergens();
        for (AllergensRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateAllergensService(AllergensRecord a){
        SelectAllergensService();
    }

    //insert
    public void InsertAllergensService(AllergensRecord a){
        SelectAllergensService();
    }

    //delete
    public void DeleteAllergensService(AllergensRecord a){
        SelectAllergensService();
    }

    public List<AllergensRecord> getConfigs() {
        return configs;
    }
}

