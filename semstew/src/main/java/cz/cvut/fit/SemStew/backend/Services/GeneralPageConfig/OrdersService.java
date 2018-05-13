package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Orders;
import JOOQ.tables.records.OrdersRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class OrdersService {
    private DSLContext ctx;

    public OrdersService() {}

    //select
    public List<OrdersRecord> SelectOrdersService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<OrdersRecord> configs = new ArrayList<OrdersRecord>();
        Orders a = new Orders();
        for (OrdersRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.update(tmp).set(tmp.ID_BRANCH, a.getIdBranch()).
                where(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
    }

    //insert
    public void InsertOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.ID_ORDER).
                values(a.getIdBranch(), a.getIdOrder()).execute();
    }

    //delete
    public void DeleteOrdersService(OrdersRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Orders tmp = new Orders();
        ctx.delete(tmp).where(tmp.ID_BRANCH.eq(a.getIdBranch())).and(tmp.ID_ORDER.eq(a.getIdOrder())).execute();
    }

    public List<OrdersRecord> getConfigs() {
        return SelectOrdersService();
    }
}
