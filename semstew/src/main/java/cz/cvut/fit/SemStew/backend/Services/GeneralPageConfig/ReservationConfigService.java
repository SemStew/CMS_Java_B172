package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.ReservationConfig;
import JOOQ.tables.records.ReservationConfigRecord;
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
public class ReservationConfigService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * ReservationConfigService constructor
     */
    public ReservationConfigService() {}

    /**
     * Get all ReservationConfig records
     *
     * Use {@link #SelectReservationConfigService()} to get all ReservationConfig records from database
     *
     * @return list of all ReservationConfig records
     */
    public List<ReservationConfigRecord> SelectReservationConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<ReservationConfigRecord> configs = new ArrayList<ReservationConfigRecord>();
        ReservationConfig a = new ReservationConfig();
        for (ReservationConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update ReservationConfig record
     *
     * Use {@link #UpdateReservationConfigService(ReservationConfigRecord a)} to update given ReservationConfig record
     *
     * @param a ReservationConfig to be updated
     */
    public void UpdateReservationConfigService(ReservationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.TABLE_NUMBER, a.getTableNumber()).
                set(tmp.TIME_FROM_DESC, a.getTimeFromDesc()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert new ReservationConfig record
     *
     * Use {@link #InsertReservationConfigService(ReservationConfigRecord a)} to insert given ReservationConfig record
     *
     * @param a ReservationConfig record to be inserted
     */
    public void InsertReservationConfigService(ReservationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        ctx.insertInto(tmp).columns(tmp.HEADER, tmp.ID_LANGUAGE, tmp.TABLE_NUMBER, tmp.TIME_FROM_DESC).
                values(a.getHeader(), a.getIdLanguage(), a.getTableNumber(), a.getTimeFromDesc()).execute();
    }

    /**
     * Delete ReservationConfig record
     *
     * Use {@link #DeleteReservationConfigService(ReservationConfigRecord a)} to delete given ReservationConfig record
     *
     * @param a ReservationConfig record to be deleted
     */
    public void DeleteReservationConfigService(ReservationConfigRecord a){
        System.out.println("Method not implemented yet");
    }

    /**
     * Get ReservationConfig record by language
     *
     * Use {@link #GetByLanguageId(int language)} to get ReservationConfig record of given language ID
     *
     * @param language language ID of search ReservationConfig record
     * @return ReservationConfig record of given language if it exists, else null
     */
    public ReservationConfigRecord GetByLanguageId(int language){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        for(ReservationConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(language)))
            return rec;
        return null;
    }

    /**
     * Get all ReservationConfig records
     *
     * Use {@link #getConfigs()} to get all ReservationConfig records from database
     *
     * @return list of all ReservationConfig records
     */
    public List<ReservationConfigRecord> getConfigs() {
        return SelectReservationConfigService();
    }
}
