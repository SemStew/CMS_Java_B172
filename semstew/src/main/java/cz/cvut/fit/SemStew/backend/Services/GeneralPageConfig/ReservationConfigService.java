package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.ReservationConfig;
import JOOQ.tables.records.ReservationConfigRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class ReservationConfigService {
    private List<ReservationConfigRecord> configs;
    private DSLContext ctx;

    public ReservationConfigService() {
        SelectReservationConfigService();
    }

    //select
    public void SelectReservationConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<ReservationConfigRecord>();
        ReservationConfig a = new ReservationConfig();
        for (ReservationConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateReservationConfigService(ReservationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.TABLE_NUMBER, a.getTableNumber()).
                set(tmp.TIME_FROM_DESC, a.getTimeFromDesc()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
        SelectReservationConfigService();
    }

    //insert
    public void InsertReservationConfigService(ReservationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        ctx.insertInto(tmp).columns(tmp.HEADER, tmp.ID_LANGUAGE, tmp.TABLE_NUMBER, tmp.TIME_FROM_DESC).
                values(a.getHeader(), a.getIdLanguage(), a.getTableNumber(), a.getTimeFromDesc()).execute();
        SelectReservationConfigService();
    }

    //delete
    public void DeleteReservationConfigService(ReservationConfigRecord a){
        SelectReservationConfigService();
    }

    public List<ReservationConfigRecord> getConfigs() {
        return configs;
    }
}
