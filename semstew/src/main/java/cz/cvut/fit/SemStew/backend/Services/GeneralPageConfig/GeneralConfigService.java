package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.GeneralConfig;
import JOOQ.tables.records.GeneralConfigRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class GeneralConfigService {
    private DSLContext ctx;

    public GeneralConfigService() {}

    //select
    public List<GeneralConfigRecord> SelectGeneralConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<GeneralConfigRecord> configs = new ArrayList<GeneralConfigRecord>();
        GeneralConfig a = new GeneralConfig();
        for (GeneralConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.update(tmp).set(tmp.URL_MAIN_IMAGE, a.getUrlMainImage()).execute();
    }

    //insert
    public void InsertGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.insertInto(tmp).columns(tmp.URL_MAIN_IMAGE).values(a.getUrlMainImage()).execute();
    }

    //delete
    public void DeleteGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.delete(tmp).execute();
    }

    public List<GeneralConfigRecord> getConfigs() {
        return SelectGeneralConfigService();
    }
}
