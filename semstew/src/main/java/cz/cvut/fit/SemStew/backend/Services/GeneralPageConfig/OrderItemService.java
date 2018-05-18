package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.OrderItem;
import JOOQ.tables.records.OrderItemRecord;
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
public class OrderItemService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * OrderItemService constructor
     */
    public OrderItemService() {}


    /**
     * Get all OrderItem records
     *
     * Use {@link #SelectOrderItemService()} to get all OrderItem records from database
     *
     * @return list of all OrderItem records
     */
    public List<OrderItemRecord> SelectOrderItemService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<OrderItemRecord> configs = new ArrayList<OrderItemRecord>();
        OrderItem a = new OrderItem();
        for (OrderItemRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update OrderItem record
     *
     * Use {@link #UpdateOrderItemService(OrderItemRecord a)} to update given OrderItem record
     *
     * @deprecated  Do not use this method, it is not implemented
     *
     * @param a OrderItem record to be updated
     */
    public void UpdateOrderItemService(OrderItemRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Insert new OrderItem record
     *
     * Use {@link #InsertOrderItemService(OrderItemRecord a)} to insert given OrderItem record
     *
     * @param a OrderItem to be inserted
     */
    public void InsertOrderItemService(OrderItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.insertInto(tmp).columns(tmp.ID_MENU_ITEM, tmp.ID_ORDER).
                values(a.getIdMenuItem(), a.getIdOrder()).execute();
    }

    /**
     * Delete OrderItem record
     *
     * Use {@link #DeleteOrderItemService(OrderItemRecord a)} to delete given OrderItem record
     *
     * @param a OrderItem record to be deleted
     */
    public void DeleteOrderItemService(OrderItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.delete(tmp).where(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).and(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
    }

    /**
     * Delete OrderItem records by Order ID
     *
     * Use {@link #DeleteByOrderId(int orderId)} to delete all OrderItem record of given Order ID
     *
     * @param orderId Order identification number of OrderItems to be deleted
     */
    public void DeleteByOrderId(int orderId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.delete(tmp).where(tmp.ID_ORDER.eq(orderId)).execute();
    }

    /**
     * Get all OrderItem records by Order ID
     *
     * Use {@link #GetByOrderId(int orderId)} to get all OrderItem records of given Order ID
     *
     * @param orderId Order identification number of OrderItems to get
     * @return list of OrderItems with given Order ID
     */
    public List<OrderItemRecord> GetByOrderId(int orderId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        List<OrderItemRecord> ret = new ArrayList<>();
        for(OrderItemRecord rec : ctx.selectFrom(tmp).where(tmp.ID_ORDER.eq(orderId)))
            ret.add(rec);
        return ret;
    }

    /**
     * Gets exact OrderItem record
     *
     * Use {@link #GetSpecific(int orderId, int item Id)} to get OrderItem record of given Order ID and Item ID
     *
     * @param orderId Order identification number of searched OrderItem record
     * @param itemId Item identification number of searched OrderItem record
     * @return OrderItem record of matching parametrs if it exist, else null
     */
    public OrderItemRecord GetSpecific(int orderId, int itemId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        for(OrderItemRecord rec : ctx.selectFrom(tmp).where(tmp.ID_ORDER.eq(orderId)).and(tmp.ID_MENU_ITEM.eq(itemId)))
            return rec;
        return null;
    }

    /**
     * Get all OrderItem records
     *
     * Use {@link #getConfigs()} to get all OrderItem records from database
     *
     * @return list of all OrderItem records
     */
    public List<OrderItemRecord> getConfigs() {
        return SelectOrderItemService();
    }
}
