package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.GeneralConfig;
import JOOQ.tables.records.GeneralConfigRecord;
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
public class GeneralConfigService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * GeneralConfigService controler
     */
    public GeneralConfigService() {}

    /**
     * Get all GeneralConfig records
     *
     * Use {@link #SelectGeneralConfigService()} to get all GeneralConfigs records
     *
     * @return list of all GeneralConfigs records
     */
    public List<GeneralConfigRecord> SelectGeneralConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<GeneralConfigRecord> configs = new ArrayList<GeneralConfigRecord>();
        GeneralConfig a = new GeneralConfig();
        for (GeneralConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Updates GeneralConfig record
     *
     * Use {@link #UpdateGeneralConfigService(GeneralConfigRecord a)} to update given GeneralConfigs record
     *
     * @param a GeneralConfig to update
     */
    public void UpdateGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.update(tmp).set(tmp.URL_MAIN_IMAGE, a.getUrlMainImage()).execute();
    }

    /**
     * Insert new GeneralConfig record
     *
     * Use {@link #InsertGeneralConfigService(GeneralConfigRecord a)} to insert given GeneralConfigs record
     *
     * @param a GeneralConfig record to insert
     */
    public void InsertGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.insertInto(tmp).columns(tmp.URL_MAIN_IMAGE).values(a.getUrlMainImage()).execute();
    }

    /**
     * Delete GeneralConfig record
     *
     * Use {@link #DeleteGeneralConfigService(GeneralConfigRecord a)} to delete given GeneralConfig record
     *
     * @param a GeneralCongfig record to delete
     */
    public void DeleteGeneralConfigService(GeneralConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        ctx.delete(tmp).execute();
    }

    /**
     * Gets single instance of GeneralConfig record
     *
     * Use {@link #GetInstance()} to get single GeneralConfig record from database
     *
     * @return instance of GeneralConfig record
     */
    public GeneralConfigRecord GetInstance(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(),SQLDialect.POSTGRES);
        GeneralConfig tmp = new GeneralConfig();
        for(GeneralConfigRecord rec : ctx.selectFrom(tmp))
            return rec;
        return null;
    }

    /**
     * Gets all GeneralConfigs records
     *
     * Use {@link #getConfigs()} to get all GeneralConfigs from database
     *
     * @return list of all GeneralConfigs records
     */
    public List<GeneralConfigRecord> getConfigs() {
        return SelectGeneralConfigService();
    }
}
