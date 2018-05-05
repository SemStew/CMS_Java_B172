package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.IntroConfig;
import JOOQ.tables.records.IntroConfigRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class IntroConfigService {
    private List<IntroConfigRecord> configs;
    private DSLContext ctx;

    public IntroConfigService() {
        SelectIntroConfigService();
    }

    //select
    public void SelectIntroConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<IntroConfigRecord>();
        IntroConfig a = new IntroConfig();
        for (IntroConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.NEWS_HEADER, a.getNewsHeader()).
                set(tmp.SHORT_DESCRIPTION, a.getShortDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectIntroConfigService();
    }

    //insert
    public void InsertIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER, tmp.NEWS_HEADER, tmp.SHORT_DESCRIPTION).
                values(a.getIdLanguage(), a.getHeader(), a.getNewsHeader(), a.getShortDescription()).execute();
        SelectIntroConfigService();
    }

    //delete
    public void DeleteIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectIntroConfigService();
    }

    public List<IntroConfigRecord> getConfigs() {
        return configs;
    }
}
