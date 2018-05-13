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
    private DSLContext ctx;

    public IntroConfigService() {}

    //select
    public List<IntroConfigRecord> SelectIntroConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<IntroConfigRecord> configs = new ArrayList<IntroConfigRecord>();
        IntroConfig a = new IntroConfig();
        for (IntroConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.NEWS_HEADER, a.getNewsHeader()).
                set(tmp.SHORT_DESCRIPTION, a.getShortDescription()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    //insert
    public void InsertIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER, tmp.NEWS_HEADER, tmp.SHORT_DESCRIPTION).
                values(a.getIdLanguage(), a.getHeader(), a.getNewsHeader(), a.getShortDescription()).execute();
    }

    //delete
    public void DeleteIntroConfigService(IntroConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    // get by language ID
    public IntroConfigRecord GetByLanquageID(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        IntroConfig tmp = new IntroConfig();
        for(IntroConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(id)))
            return rec;
        return null;
    }

    public List<IntroConfigRecord> getConfigs() {
        return SelectIntroConfigService();
    }
}
