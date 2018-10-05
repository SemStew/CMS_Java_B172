package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Reservation;
import JOOQ.tables.records.ReservationRecord;
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
public class ReservationService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * ReservationService constructor
     */
    public ReservationService() {}

    /**
     * Get all Reservation records
     *
     * Use {@link #SelectReservationService()} to get all Reservation records from database
     *
     * @return list of all Reservation records
     */
    public List<ReservationRecord> SelectReservationService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<ReservationRecord> configs = new ArrayList<ReservationRecord>();
        Reservation a = new Reservation();
        for (ReservationRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Reservation record
     *
     * Use {@link #UpdateReservationService(ReservationRecord a)} to update given Reservation record
     *
     * @param a Reservation record to be updated
     */
    public void UpdateReservationService(ReservationRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Reservation tmp = new Reservation();
        ctx.update(tmp).set(tmp.STATUS, a.getStatus()).set(tmp.PERSON, a.getPerson()).set(tmp.EMAIL, a.getEmail()).
                where(tmp.ID_RESERVATION.eq(a.getIdReservation())).execute();
    }

    /**
     * Insert new Reservation record
     *
     * Use {@link #InsertReservationService(ReservationRecord a)} to insert given Reservation record
     *
     * @param a Reservation record to be inserted
     */
    public void InsertReservationService(ReservationRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Reservation tmp = new Reservation();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.N_TABLE, tmp.PERSON, tmp.R_DATE, tmp.TIME_FROM, tmp.EMAIL, tmp.STATUS).
                values(a.getIdBranch(), a.getNTable(), a.getPerson(), a.getRDate(), a.getTimeFrom(), a.getEmail(), a.getStatus()).execute();
    }

    /**
     * Delete Reservation record
     *
     * Use {@link #DeleteReservationService(ReservationRecord a)} to delete given Reservation record
     *
     * @param a Reservation record to be deleted
     */
    public void DeleteReservationService(ReservationRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Reservation tmp = new Reservation();
        ctx.delete(tmp).where(tmp.ID_RESERVATION.eq(a.getIdReservation())).execute();
    }

    /**
     * Get Reservation Record by ID
     *
     * Use {@link #GetById(int id)} to get Reservation record of given ID
     *
     * @param id ID of searched Reservation record
     * @return Reservation record of given ID if it exists, else null
     */
    public ReservationRecord GetById(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Reservation tmp = new Reservation();
        return ctx.fetchOne(tmp, tmp.ID_RESERVATION.eq(id));
    }

    /**
     * Get all Reservation records
     *
     * Use {@link #getConfigs()} to get all Reservation records from database
     *
     * @return list of all Reservation records
     */
    public List<ReservationRecord> getConfigs() {
        return SelectReservationService();
    }
}
