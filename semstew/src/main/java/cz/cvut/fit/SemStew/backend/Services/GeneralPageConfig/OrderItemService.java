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
    private List<OrderItemRecord> configs;
    private DSLContext ctx;

    public OrderItemService() {
        SelectOrderItemService();
    }

    //select
    public void SelectOrderItemService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<OrderItemRecord>();
        OrderItem a = new OrderItem();
        for (OrderItemRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateOrderItemService(OrderItemRecord a){
        SelectOrderItemService();
    }

    //insert
    public void InsertOrderItemService(OrderItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.insertInto(tmp).columns(tmp.ID_MENU_ITEM, tmp.ID_ORDER).
                values(a.getIdMenuItem(), a.getIdOrder()).execute();
        SelectOrderItemService();
    }

    //delete
    public void DeleteOrderItemService(OrderItemRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        OrderItem tmp = new OrderItem();
        ctx.delete(tmp).where(tmp.ID_MENU_ITEM.eq(a.getIdMenuItem())).and(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
        SelectOrderItemService();
    }

    public List<OrderItemRecord> getConfigs() {
        return configs;
    }
}
