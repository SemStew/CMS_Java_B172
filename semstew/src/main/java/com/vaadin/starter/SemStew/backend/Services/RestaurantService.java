package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Restaurant;
import JOOQ.tables.records.RestaurantRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private List<RestaurantRecord> configs;
    private DSLContext ctx;

    public RestaurantService() {
        SelectRestaurantService();
    }

    //select
    public void SelectRestaurantService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<RestaurantRecord>();
        Restaurant a = new Restaurant();
        for (RestaurantRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.update(tmp).set(tmp.NAME, a.getName()).set(tmp.ICO, a.getIco()).set(tmp.IMAGE, a.getImage()).
                set(tmp.ID_ADMIN, a.getIdAdmin()).where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).execute();
        SelectRestaurantService();
    }

    //insert
    public void InsertRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.insertInto(tmp).columns(tmp.NAME, tmp.ICO, tmp.ID_ADMIN, tmp.IMAGE).
                values(a.getName(), a.getIco(), a.getIdAdmin(), a.getImage()).execute();
        SelectRestaurantService();
    }

    //delete
    public void DeleteRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.delete(tmp).where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).execute();
        SelectRestaurantService();
    }

    public List<RestaurantRecord> getConfigs() {
        return configs;
    }
}
