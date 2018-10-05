package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Orders;
import JOOQ.tables.records.OrdersRecord;
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
public class OrdersService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * OrdersService constructor
     */
    public OrdersService() {}

    /**
     * Get all Order records
     *
     * Use {@link #SelectOrdersService()} to get all Order records from database
     *
     * @return list of all Order records
     */
    public List<OrdersRecord> SelectOrdersService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<OrdersRecord> configs = new ArrayList<OrdersRecord>();
        Orders a = new Orders();
        for (OrdersRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }


    /**
     * Update Order record
     *
     * Use {@link #UpdateOrdersService(OrdersRecord a)} to update given Order record
     *
     * @param a Order record to be updated
     */
    public void UpdateOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.update(tmp).set(tmp.ID_BRANCH, a.getIdBranch()).set(tmp.ADDRESS, a.getAddress()).set(tmp.O_DATE, a.getODate()).
                set(tmp.PERSON, a.getPerson()).set(tmp.EMAIL, a.getEmail()).set(tmp.STATUS, a.getStatus()).
                where(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
    }

    /**
     * Insert new Order record
     *
     * Use {@link #InsertOrdersService(OrdersRecord a)} to insert given Order record
     *
     * @param a Order record to be inserted
     */
    public void InsertOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.ADDRESS, tmp.O_DATE, tmp.PERSON, tmp.EMAIL, tmp.STATUS).
                values(a.getIdBranch(), a.getAddress(), a.getODate(), a.getPerson(), a.getEmail(), a.getStatus()).execute();
    }

    /**
     * Insert new Order record and get generated Order ID
     *
     * Use {@link #InsertAndGetId(OrdersRecord in)} insert given Order record and get generated Order ID
     *
     * @param in Order record to be inserted
     * @return generated Order identification number
     */
    public int InsertAndGetId(OrdersRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        OrdersRecord inserted = ctx.newRecord(tmp);
        inserted.setIdBranch(in.getIdBranch());
        inserted.setPerson(in.getPerson());
        inserted.setODate(in.getODate());
        inserted.setAddress(in.getAddress());
        inserted.setEmail(in.getEmail());
        inserted.setStatus(in.getStatus());
        inserted.store();
        return inserted.getIdOrder();
    }


    /**
     * Delete Order record
     *
     * Use {@link #DeleteOrdersService(OrdersRecord a)} to delete given Order record
     *
     * @param a Order record to be deleted
     */
    public void DeleteOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.delete(tmp).where(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
    }

    /**
     * Delete Order record by ID
     *
     * Use {@link #DeleteById(int orderId)} to delete Order record of given ID
     *
     * @param orderId Order ID of Order record to be deleted
     */
    public void DeleteById(int orderId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.delete(tmp).where(tmp.ID_ORDER.eq(orderId)).execute();
    }

    /**
     * Get Order record by ID
     *
     * Use {@link #GetByOrderId(int orderID)} to get Order record of given ID
     *
     * @param orderId Order ID of searched Order record
     * @return Order record with given Order ID if it exists, else null
     */
    public OrdersRecord GetByOrderId(int orderId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        for(OrdersRecord rec : ctx.selectFrom(tmp).where(tmp.ID_ORDER.eq(orderId)))
            return rec;
        return null;
    }

    /**
     * Get all Order records
     *
     * Use {@link #getConfigs()} to get all Order records from database
     *
     * @return list of all Order records
     */
    public List<OrdersRecord> getConfigs() {
        return SelectOrdersService();
    }
}
