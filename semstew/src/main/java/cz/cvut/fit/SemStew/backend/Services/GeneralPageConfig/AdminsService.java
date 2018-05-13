package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Admins;
import JOOQ.tables.records.AdminsRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class AdminsService {
    private DSLContext ctx;

    public AdminsService() {}

    //select
    public List<AdminsRecord> SelectAdminsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AdminsRecord> configs = new ArrayList<AdminsRecord>();
        Admins a = new Admins();
        for (AdminsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.update(tmp).set(tmp.NAME, a.getName()).set(tmp.PASSWORD, a.getPassword()).
                        where(tmp.ID_ADMIN.eq(a.getIdAdmin())).execute();
    }

    //insert
    public void InsertAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.insertInto(tmp).columns(tmp.ID_ADMIN, tmp.NAME, tmp.PASSWORD).
                values(a.getIdAdmin(), a.getName(), a.getPassword()).execute();
    }

    //delete
    public void DeleteAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.delete(tmp).where(tmp.ID_ADMIN.eq(a.getIdAdmin())).execute();
    }

    // get by name
    public AdminsRecord GetByName(String name){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins a = new Admins();
        for(AdminsRecord rec : ctx.selectFrom(a).where(a.NAME.eq(name)))
            return rec;
        return null;
    }

    public List<AdminsRecord> getConfigs() {
        return SelectAdminsService();
    }
}
