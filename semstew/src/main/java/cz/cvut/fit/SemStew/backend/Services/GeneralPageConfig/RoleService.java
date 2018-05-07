package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Role;
import JOOQ.tables.records.RoleRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class RoleService {
    private List<RoleRecord> configs;
    private DSLContext ctx;

    public RoleService() {
        SelectRoleService();
    }

    //select
    public void SelectRoleService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<RoleRecord>();
        Role a = new Role();
        for (RoleRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateRoleService(RoleRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Role tmp = new Role();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_ROLE.eq(a.getIdRole())).execute();
        SelectRoleService();
    }

    //insert
    public void InsertRoleService(RoleRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Role tmp = new Role();
        ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
        SelectRoleService();
    }

    //delete
    public void DeleteRoleService(RoleRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Role tmp = new Role();
        ctx.delete(tmp).where(tmp.ID_ROLE.eq(a.getIdRole())).execute();
        SelectRoleService();
    }

    public List<RoleRecord> getConfigs() {
        return configs;
    }
}
