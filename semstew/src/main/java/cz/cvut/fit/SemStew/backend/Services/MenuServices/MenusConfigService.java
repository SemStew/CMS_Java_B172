package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.MenusConfig;
import JOOQ.tables.records.MenusConfigRecord;
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
public class MenusConfigService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * MenusConfigService constructor
     */
    public MenusConfigService() {}

    /**
     * Get all MenusConfig records
     *
     * Use {@link #SelectMenusConfigService()} to get all MenusConfig records from database
     *
     * @return list of all MenusConfig records
     */
    public List<MenusConfigRecord> SelectMenusConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<MenusConfigRecord> configs = new ArrayList<MenusConfigRecord>();
        MenusConfig a = new MenusConfig();
        for (MenusConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update MenusConfig record
     *
     * Use {@link #UpdateMenusConfigService(MenusConfigRecord a)} to update given MenusConfig record
     *
     * @param a MenusConfig record to be updated
     */
    public void UpdateMenusConfigService(MenusConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).
                where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert new MenusConfig record
     *
     * Use {@link #InsertMenusConfigService(MenusConfigRecord a)} to insert given MenusConfig record
     *
     * @param a MenusConfig record to be inserted
     */
    public void InsertMenusConfigService(MenusConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER).
                values(a.getIdLanguage(), a.getHeader()).execute();
    }

    /**
     * Delete MenusConfig record
     *
     * Use {@link #DeleteMenusConfigService(MenusConfigRecord a)} to delete given MenusConfig record
     *
     * @param a MenusConfig record to be deleted
     */
    public void DeleteMenusConfigService(MenusConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get MenusConfig record by language
     *
     * Use {@link #GetInstanceByLanguage(int id)} to get MenusConfig record of given language
     *
     * @param id Requested language ID
     * @return MenusConfig record of requested language if it exists, else null
     */
    public MenusConfigRecord GetInstanceByLanguage(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        MenusConfig tmp = new MenusConfig();
        for(MenusConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(id)))
            return rec;
        return null;
    }

    /**
     * Get all MenusConfig records
     *
     * Use {@link #getConfigs()} to get all MenusConfig records from database
     *
     * @return list of all MenusConfig records
     */
    public List<MenusConfigRecord> getConfigs() {
        return SelectMenusConfigService();
    }
}
