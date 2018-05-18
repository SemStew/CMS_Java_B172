package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Role;
import JOOQ.tables.records.RoleRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class RoleService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * RoleService constructor
     */
    public RoleService() {}

    /**
     * Get all Role records
     *
     * Use {@link #SelectRoleService()} to get all Role records from database
     *
     * @return list of all Role records
     */
    public List<RoleRecord> SelectRoleService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<RoleRecord> configs = new ArrayList<RoleRecord>();
        Role a = new Role();
        for (RoleRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Role record
     *
     * Use {@link #UpdateRoleService(RoleRecord a)} to update given Role record
     *
     * @param a Role record to be updated
     */
    public void UpdateRoleService(RoleRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Role tmp = new Role();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_ROLE.eq(a.getIdRole())).execute();
    }

    /**
     * Insert new Role record
     *
     * Use {@link #InsertRoleService(RoleRecord a)} to insert given Role record
     *
     * @param a Role record to be inserted
     */
    public void InsertRoleService(RoleRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Role tmp = new Role();
        ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
    }

    /**
     * Delete Role record
     *
     * Use {@link #DeleteRoleService(RoleRecord a)} to delete given Role record
     *
     * @param a Role record to be deleted
     */
    public void DeleteRoleService(RoleRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Role tmp = new Role();
        ctx.delete(tmp).where(tmp.ID_ROLE.eq(a.getIdRole())).execute();
    }

    /**
     * Get all Role records
     *
     * Use {@link #getConfigs()} to get all Role records from database
     *
     * @return list of all Role records
     */
    public List<RoleRecord> getConfigs() {
        return SelectRoleService();
    }
}
