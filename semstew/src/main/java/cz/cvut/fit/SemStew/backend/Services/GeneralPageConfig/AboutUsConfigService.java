package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.AboutUsConfig;
import JOOQ.tables.Units;
import JOOQ.tables.records.AboutUsConfigRecord;
import JOOQ.tables.records.UnitsRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class AboutUsConfigService {
    private List<AboutUsConfigRecord> configs;
    private DSLContext ctx;

    public AboutUsConfigService() {
        SelectAllAboutUsConfigService();
    }

    //select
    public void SelectAllAboutUsConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<AboutUsConfigRecord>();
        AboutUsConfig a = new AboutUsConfig();
        for (AboutUsConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.FOTOGALLERY_HEADER, a.getFotogalleryHeader()).
                        set(tmp.DESCRIPTION, a.getDescription()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectAllAboutUsConfigService();
    }

    //insert
    public void InsertAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER, tmp.DESCRIPTION, tmp.FOTOGALLERY_HEADER).
                            values(a.getIdLanguage(), a.getHeader(), a.getDescription(), a.getFotogalleryHeader()).execute();
        SelectAllAboutUsConfigService();
    }

    //delete
    public void DeleteAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectAllAboutUsConfigService();
    }

    public List<AboutUsConfigRecord> getConfigs() {
        return configs;
    }

    public static class UnitsService {
        private List<UnitsRecord> configs;
        private DSLContext ctx;

        public UnitsService() {
            SelectUnitsService();
        }

        //select
        public void SelectUnitsService(){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            configs = new ArrayList<UnitsRecord>();
            Units a = new Units();
            for (UnitsRecord rec : ctx.selectFrom(a)) {
                configs.add(rec);
            }
        }

        //update
        public void UpdateUnitsService(UnitsRecord a){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            ctx.update(tmp).set(tmp.NAME, a.getName()).
                    where(tmp.ID_UNIT.eq(a.getIdUnit())).execute();
            SelectUnitsService();
        }

        //insert
        public void InsertUnitsService(UnitsRecord a){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
            SelectUnitsService();
        }

        //delete
        public void DeleteUnitsService(UnitsRecord a){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            ctx.delete(tmp).where(tmp.ID_UNIT.eq(a.getIdUnit())).execute();
            SelectUnitsService();
        }


        // records by id
        public UnitsRecord UnitById(Integer id)
        {
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            for(UnitsRecord res : ctx.selectFrom(tmp).where(tmp.ID_UNIT.eq(id)))
            {
                return res;
            }

            return null;
        }

        // records by name
        public UnitsRecord UnitByName(String name)
        {
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            for(UnitsRecord res : ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
                return res;
            return null;
        }

        // only description
        public List<String> UnitsDescription()
        {
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            List<String> ret = new ArrayList<>();
            for(UnitsRecord res : ctx.selectFrom(tmp))
            {
                ret.add(res.getName());
            }

            return ret;
        }

        public List<UnitsRecord> getConfigs() {
            return configs;
        }
    }
}
