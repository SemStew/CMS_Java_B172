package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.OrderItem;
import JOOQ.tables.records.OrderItemRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService {
    private DSLContext ctx;

    public OrderItemService() {}

    //select
    public List<OrderItemRecord> SelectOrderItemService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<OrderItemRecord> configs = new ArrayList<OrderItemRecord>();
        OrderItem a = new OrderItem();
        for (OrderItemRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateOrderItemService(OrderItemRecord a){
        System.out.println("Method not implemented");
    }

    //insert
    public void InsertOrderItemService(OrderItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.insertInto(tmp).columns(tmp.ID_MENU_ITEM, tmp.ID_ORDER).
                values(a.getIdMenuItem(), a.getIdOrder()).execute();
    }

    //delete
    public void DeleteOrderItemService(OrderItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.delete(tmp).where(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).and(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
    }

    // delete by order id
    public void DeleteByOrderId(int orderId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.delete(tmp).where(tmp.ID_ORDER.eq(orderId)).execute();
    }

    // get by order id
    public List<OrderItemRecord> GetByOrderId(int orderId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        List<OrderItemRecord> ret = new ArrayList<>();
        for(OrderItemRecord rec : ctx.selectFrom(tmp).where(tmp.ID_ORDER.eq(orderId)))
            ret.add(rec);
        return ret;
    }

    public OrderItemRecord GetSpecific(int orderId, int itemId){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        for(OrderItemRecord rec : ctx.selectFrom(tmp).where(tmp.ID_ORDER.eq(orderId)).and(tmp.ID_MENU_ITEM.eq(itemId)))
            return rec;
        return null;
    }


    public List<OrderItemRecord> getConfigs() {
        return SelectOrderItemService();
    }
}
