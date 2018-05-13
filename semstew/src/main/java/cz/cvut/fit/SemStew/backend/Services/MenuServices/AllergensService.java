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
    private DSLContext ctx;

    public AllergensService() {}

    //select
    public List<AllergensRecord> SelectAllergensService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AllergensRecord> configs = new ArrayList<AllergensRecord>();
        Allergens a = new Allergens();
        for (AllergensRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateAllergensService(AllergensRecord a){
        System.out.println("Method not implemented");
    }

    //insert
    public void InsertAllergensService(AllergensRecord a){
        System.out.println("Method not implemented");
    }

    //delete
    public void DeleteAllergensService(AllergensRecord a){
        System.out.println("Method not implemented");
    }

    public List<AllergensRecord> getConfigs() {
        return SelectAllergensService();
    }
}

