package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Branch;
import JOOQ.tables.records.BranchRecord;
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
public class BranchService {
    /**
     * Database context
     */
    private DSLContext ctx;

    /**
     * BranchService constructor
     */
    public BranchService() {}

    /**
     * Gets all Branch records
     *
     * Use {@link #SelectBranchService()} to get all Branch records from database
     *
     * @return list of all Branch records
     */
    public List<BranchRecord> SelectBranchService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<BranchRecord> configs = new ArrayList<BranchRecord>();
        Branch a = new Branch();
        for (BranchRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Updates given Branch record
     *
     * Use {@link #UpdateBranchService(BranchRecord a)} to update given Branch record
     *
     * @param a Branch record to be updated
     */
    public void UpdateBranchService(BranchRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        ctx.update(tmp).set(tmp.ADDRESS, a.getAddress()).set(tmp.DESCRIPTION, a.getDescription()).
                        set(tmp.PHONE, a.getPhone()).set(tmp.OPENING_HOURS, a.getOpeningHours()).
                where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).and(tmp.ID_BRANCH.eq(a.getIdBranch())).execute();
    }

    /**
     * Inserts Branch record
     *
     * Use {@link #InsertBranchService(BranchRecord a)} to insert given Branch record into database
     *
     * @param a Branch record to be inserted
     */
    public void InsertBranchService(BranchRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.ADDRESS, tmp.DESCRIPTION, tmp.PHONE, tmp.OPENING_HOURS).
                values(a.getIdRestaurant(), a.getAddress(), a.getDescription(), a.getPhone(), a.getOpeningHours()).execute();
    }

    /**
     * Deletes Branch record
     *
     * Use {@link #DeleteBranchService(BranchRecord a)} to delete given Branch record
     *
     * @param a Branch record to be deleted
     */
    public void DeleteBranchService(BranchRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        ctx.delete(tmp).where(tmp.ID_BRANCH.eq(a.getIdBranch())).execute();
    }

    /**
     * Gets Branch record by address
     *
     * Use {@link #GetByAddress(String address)} to get Branch record by given address
     *
     * @param address address of searched Branch record
     * @return Branch record of given address if it exists, else null
     */
    public BranchRecord GetByAddress(String address){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        for(BranchRecord rec : ctx.selectFrom(tmp).where(tmp.ADDRESS.eq(address)))
            return rec;
        return null;
    }

    /**
     * Gets all Branch records addresses
     *
     * Use {@link #GetAllAdresses()} to get all Branch records addresses
     *
     * @return list of all Branch records addresses
     */
    public List<String> GetAllAdresses(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        List<String> ret = new ArrayList<>();
        for(BranchRecord res : ctx.selectFrom(tmp))
            ret.add(res.getAddress());
        return ret;
    }

    /**
     * Gets BranchRecord by id
     *
     * Use {@link #GetById(int id)} to get Branch record of given id
     *
     * @param id id of searched Branch record
     * @return Branch record of given id if it exists, else null
     */
    public BranchRecord GetById(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Branch tmp = new Branch();
        for(BranchRecord rec : ctx.selectFrom(tmp).where(tmp.ID_BRANCH.eq(id)))
            return rec;
        return null;
    }

    /**
     * Gets all Branch records
     *
     * Use {@link #getConfigs()} to get all Branch record from database
     *
     * @return list of all Branch records
     */
    public List<BranchRecord> getConfigs() {
        return SelectBranchService();
    }
}
