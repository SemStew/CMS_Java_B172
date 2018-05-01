package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Orders;
import JOOQ.tables.records.OrdersRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class OrdersService {
    private List<OrdersRecord> configs;
    private DSLContext ctx;

    public OrdersService() {
        SelectOrdersService();
    }

    //select
    public void SelectOrdersService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<OrdersRecord>();
        Orders a = new Orders();
        for (OrdersRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.update(tmp).set(tmp.ID_BRANCH, a.getIdBranch()).
                where(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
        SelectOrdersService();
    }

    //insert
    public void InsertOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.ID_ORDER).
                values(a.getIdBranch(), a.getIdOrder()).execute();
        SelectOrdersService();
    }

    //delete
    public void DeleteOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.delete(tmp).where(tmp.ID_BRANCH.eq(a.getIdBranch())).and(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
        SelectOrdersService();
    }

    public List<OrdersRecord> getConfigs() {
        return configs;
    }
}
