package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Languages;
import JOOQ.tables.records.LanguagesRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class LanguagesService {
    private DSLContext ctx;

    public LanguagesService() {}

    //select
    public List<LanguagesRecord> SelectLanguagesService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<LanguagesRecord> configs = new ArrayList<LanguagesRecord>();
        Languages a = new Languages();
        for (LanguagesRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    //insert
    public void InsertLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
    }

    //delete
    public void DeleteLanguagesService(LanguagesRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Languages tmp = new Languages();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    public List<LanguagesRecord> getConfigs() {
        return SelectLanguagesService();
    }
}
