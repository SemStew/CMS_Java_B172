package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Restaurant;
import JOOQ.tables.records.RestaurantRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private DSLContext ctx;

    public RestaurantService() {}

    //select
    public List<RestaurantRecord> SelectRestaurantService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<RestaurantRecord> configs = new ArrayList<RestaurantRecord>();
        Restaurant a = new Restaurant();
        for (RestaurantRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.update(tmp).set(tmp.NAME, a.getName()).set(tmp.ICO, a.getIco()).set(tmp.IMAGE, a.getImage()).
                set(tmp.ID_ADMIN, a.getIdAdmin()).set(tmp.EMAIL, a.getEmail()).set(tmp.EMAIL_PASSWORD, a.getEmailPassword()).
                where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).execute();
    }

    //insert
    public void InsertRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.insertInto(tmp).columns(tmp.NAME, tmp.ICO, tmp.ID_ADMIN, tmp.IMAGE, tmp.EMAIL, tmp.EMAIL_PASSWORD).
                values(a.getName(), a.getIco(), a.getIdAdmin(), a.getImage(), a.getEmail(), a.getEmailPassword()).execute();
    }

    //delete
    public void DeleteRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.delete(tmp).where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).execute();
    }

    // getSingleInstance
    public  RestaurantRecord GetInstance()
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        for(RestaurantRecord rec : ctx.selectFrom(tmp))
            return rec;
        return null;
    }

    public List<RestaurantRecord> getConfigs() {
        return SelectRestaurantService();
    }
}
