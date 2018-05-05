package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Rezervation;
import JOOQ.tables.records.RezervationRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class RezervationService {
    private List<RezervationRecord> configs;
    private DSLContext ctx;

    public RezervationService() {
        SelectRezervationService();
    }

    //select
    public void SelectRezervationService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<RezervationRecord>();
        Rezervation a = new Rezervation();
        for (RezervationRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateRezervationService(RezervationRecord a){
        SelectRezervationService();
    }

    //insert
    public void InsertRezervationService(RezervationRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Rezervation tmp = new Rezervation();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.N_TABLE, tmp.PERSON, tmp.R_DATE, tmp.TIME_FROM).
                values(a.getIdBranch(), a.getNTable(), a.getPerson(), a.getRDate(), a.getTimeFrom()).execute();
        SelectRezervationService();
    }

    //delete
    public void DeleteRezervationService(RezervationRecord a){
        SelectRezervationService();
    }

    public List<RezervationRecord> getConfigs() {
        return configs;
    }
}
