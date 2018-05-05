package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Branch;
import JOOQ.tables.records.BranchRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class BranchService {
    private List<BranchRecord> configs;
    private DSLContext ctx;

    public BranchService() {
        SelectBranchService();
    }

    //select
    public void SelectBranchService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<BranchRecord>();
        Branch a = new Branch();
        for (BranchRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateBranchService(BranchRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        ctx.update(tmp).set(tmp.ADDRESS, a.getAddress()).set(tmp.DESCRIPTION, a.getDescription()).
                        set(tmp.PHONE, a.getPhone()).set(tmp.OPENING_HOURS, a.getOpeningHours()).
                where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).and(tmp.ID_BRANCH.eq(a.getIdBranch())).execute();
        SelectBranchService();
    }

    //insert
    public void InsertBranchService(BranchRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.ADDRESS, tmp.DESCRIPTION, tmp.PHONE, tmp.OPENING_HOURS).
                values(a.getIdRestaurant(), a.getAddress(), a.getDescription(), a.getPhone(), a.getOpeningHours()).execute();
        SelectBranchService();
    }

    //delete
    public void DeleteBranchService(BranchRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        ctx.delete(tmp).where(tmp.ID_BRANCH.eq(a.getIdBranch())).execute();
        SelectBranchService();
    }

    public List<BranchRecord> getConfigs() {
        return configs;
    }
}
