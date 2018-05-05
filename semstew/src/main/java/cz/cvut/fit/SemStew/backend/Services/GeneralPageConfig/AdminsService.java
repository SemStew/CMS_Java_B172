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
    private List<AdminsRecord> configs;
    private DSLContext ctx;

    public AdminsService() {
        SelectAdminsService();
    }

    //select
    public void SelectAdminsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<AdminsRecord>();
        Admins a = new Admins();
        for (AdminsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.update(tmp).set(tmp.NAME, a.getName()).set(tmp.PASSWORD, a.getPassword()).
                        where(tmp.ID_ADMIN.eq(a.getIdAdmin())).execute();
        SelectAdminsService();
    }

    //insert
    public void InsertAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.insertInto(tmp).columns(tmp.ID_ADMIN, tmp.NAME, tmp.PASSWORD).
                values(a.getIdAdmin(), a.getName(), a.getPassword()).execute();
        SelectAdminsService();
    }

    //delete
    public void DeleteAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.delete(tmp).where(tmp.ID_ADMIN.eq(a.getIdAdmin())).execute();
        SelectAdminsService();
    }

    public List<AdminsRecord> getConfigs() {
        return configs;
    }
}
