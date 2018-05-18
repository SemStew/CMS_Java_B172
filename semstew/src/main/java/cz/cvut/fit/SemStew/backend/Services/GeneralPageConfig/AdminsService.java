package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Admins;
import JOOQ.tables.records.AdminsRecord;
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
public class AdminsService {
    /**
     * Database context
     */
    private DSLContext ctx;

    /**
     * AdminsService constructor
     */
    public AdminsService() {}

    /**
     * Get all Admins record
     *
     * Use {@link #SelectAdminsService()} to get all Admins record from database
     *
     * @return list of all Admins records
     */
    public List<AdminsRecord> SelectAdminsService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AdminsRecord> configs = new ArrayList<AdminsRecord>();
        Admins a = new Admins();
        for (AdminsRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Updates given Admins record
     *
     * Use {@link #UpdateAdminsService(AdminsRecord a)} to update given Admins record
     *
     * @param a Admins record to update
     */
    public void UpdateAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.update(tmp).set(tmp.NAME, a.getName()).set(tmp.PASSWORD, a.getPassword()).
                        where(tmp.ID_ADMIN.eq(a.getIdAdmin())).execute();
    }

    /**
     * Insert new Admins record
     *
     * Use {@link #InsertAdminsService(AdminsRecord a)} to insert given Admins record
     *
     * @param a Admins record to insert into database
     */
    public void InsertAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.insertInto(tmp).columns( tmp.NAME, tmp.PASSWORD).
                values(a.getName(), a.getPassword()).execute();
    }

    /**
     * Delete Admins record
     *
     * Use {@link #DeleteAdminsService(AdminsRecord a)} to delete given Admins record
     *
     * @param a Admins record to delete
     */
    public void DeleteAdminsService(AdminsRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins tmp = new Admins();
        ctx.delete(tmp).where(tmp.ID_ADMIN.eq(a.getIdAdmin())).execute();
    }

    /**
     * Gets Admins record by given name
     *
     * Use {@link #GetByName(String name)} to get Admins record by given name
     *
     * @param name name of searched Admins record
     * @return Admins record of given name if it exist, else null
     */
    public AdminsRecord GetByName(String name){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Admins a = new Admins();
        for(AdminsRecord rec : ctx.selectFrom(a).where(a.NAME.eq(name)))
            return rec;
        return null;
    }

    /**
     * Gets all Admins record
     *
     * Use {@link #getConfigs()} to get all Admins record from database
     *
     * @return list of all Admins records
     */
    public List<AdminsRecord> getConfigs() {
        return SelectAdminsService();
    }
}
