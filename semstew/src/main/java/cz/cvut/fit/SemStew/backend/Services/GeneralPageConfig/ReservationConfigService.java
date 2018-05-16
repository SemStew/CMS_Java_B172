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
    private DSLContext ctx;

    public ReservationConfigService() {}

    //select
    public List<ReservationConfigRecord> SelectReservationConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<ReservationConfigRecord> configs = new ArrayList<ReservationConfigRecord>();
        ReservationConfig a = new ReservationConfig();
        for (ReservationConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateReservationConfigService(ReservationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.TABLE_NUMBER, a.getTableNumber()).
                set(tmp.TIME_FROM_DESC, a.getTimeFromDesc()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    //insert
    public void InsertReservationConfigService(ReservationConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        ctx.insertInto(tmp).columns(tmp.HEADER, tmp.ID_LANGUAGE, tmp.TABLE_NUMBER, tmp.TIME_FROM_DESC).
                values(a.getHeader(), a.getIdLanguage(), a.getTableNumber(), a.getTimeFromDesc()).execute();
    }

    //delete
    public void DeleteReservationConfigService(ReservationConfigRecord a){
        System.out.println("Method not implemented yet");
    }

    //get by language id
    public ReservationConfigRecord GetByLanguageId(int language){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ReservationConfig tmp = new ReservationConfig();
        for(ReservationConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(language)))
            return rec;
        return null;
    }

    public List<ReservationConfigRecord> getConfigs() {
        return SelectReservationConfigService();
    }
}
