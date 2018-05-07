package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Reservation;
import JOOQ.tables.records.ReservationRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<ReservationRecord> configs;
    private DSLContext ctx;

    public ReservationService() {
        SelectReservationService();
    }

    //select
    public void SelectReservationService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<ReservationRecord>();
        Reservation a = new Reservation();
        for (ReservationRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateReservationService(ReservationRecord a){
        SelectReservationService();
    }

    //insert
    public void InsertReservationService(ReservationRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Reservation tmp = new Reservation();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.N_TABLE, tmp.PERSON, tmp.R_DATE, tmp.TIME_FROM).
                values(a.getIdBranch(), a.getNTable(), a.getPerson(), a.getRDate(), a.getTimeFrom()).execute();
        SelectReservationService();
    }

    //delete
    public void DeleteReservationService(ReservationRecord a){
        SelectReservationService();
    }

    public List<ReservationRecord> getConfigs() {
        return configs;
    }
}
