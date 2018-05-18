package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Restaurant;
import JOOQ.tables.records.RestaurantRecord;
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
public class RestaurantService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * RestaurantService constructor
     */
    public RestaurantService() {}

    /**
     * Get all Restaurant records
     *
     * Use {@link #SelectRestaurantService()} to get all Restaurant records from database
     *
     * @return list of all Restaurant records
     */
    public List<RestaurantRecord> SelectRestaurantService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<RestaurantRecord> configs = new ArrayList<RestaurantRecord>();
        Restaurant a = new Restaurant();
        for (RestaurantRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Restaurant record
     *
     * Use {@link #UpdateRestaurantService(RestaurantRecord a)} to update given Restaurant record
     *
     * @param a Restaurant record to be updated
     */
    public void UpdateRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.update(tmp).set(tmp.NAME, a.getName()).set(tmp.ICO, a.getIco()).set(tmp.IMAGE, a.getImage()).
                set(tmp.ID_ADMIN, a.getIdAdmin()).set(tmp.EMAIL, a.getEmail()).set(tmp.EMAIL_PASSWORD, a.getEmailPassword()).
                where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).execute();
    }

    /**
     * Insert Restaurant record
     *
     * Use {@link #InsertRestaurantService(RestaurantRecord a)} to insert given Restaurant record
     *
     * @param a Restaurant record to be inserted
     */
    public void InsertRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.insertInto(tmp).columns(tmp.NAME, tmp.ICO, tmp.ID_ADMIN, tmp.IMAGE, tmp.EMAIL, tmp.EMAIL_PASSWORD).
                values(a.getName(), a.getIco(), a.getIdAdmin(), a.getImage(), a.getEmail(), a.getEmailPassword()).execute();
    }

    /**
     * Delete Restaurant record
     *
     * Use {@link #DeleteRestaurantService(RestaurantRecord a)} to delete given Restaurant record
     *
     * @param a Restaurant record to be deleted
     */
    public void DeleteRestaurantService(RestaurantRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        ctx.delete(tmp).where(tmp.ID_RESTAURANT.eq(a.getIdRestaurant())).execute();
    }

    /**
     * Get single instance of Restaurant record
     *
     * Use {@link #GetInstance()} to get single instance of Restaurant record from database
     *
     * @return single instance Restaurant record if some exists, if empty than null
     */
    public  RestaurantRecord GetInstance()
    {
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Restaurant tmp = new Restaurant();
        for(RestaurantRecord rec : ctx.selectFrom(tmp))
            return rec;
        return null;
    }

    /**
     * Get all Restaurant records
     *
     * Use {@link #getConfigs()} to get all Restaurant records from database
     *
     * @return list of all Restaurant records
     */
    public List<RestaurantRecord> getConfigs() {
        return SelectRestaurantService();
    }
}
