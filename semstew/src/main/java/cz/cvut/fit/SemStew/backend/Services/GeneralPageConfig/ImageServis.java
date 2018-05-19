package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Images;
import JOOQ.tables.records.ImagesRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 1.0
 */
public class ImageServis {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * ImageService constructor
     */
    public ImageServis(){}

    /**
     * Get all Image records
     *
     * Use {@link #Select()} to get all Image records from database
     *
     * @return list of all Image records
     */
    private List<ImagesRecord> Select(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        List<ImagesRecord> ret = new ArrayList<>();
        for(ImagesRecord rec : ctx.selectFrom(tmp))
            ret.add(rec);
        return ret;
    }

    /**
     * Updates Image record
     *
     * Use {@link #Update(ImagesRecord in)} to update given Image record
     *
     * @param in Image record to be updated
     */
    public void Update(ImagesRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        ctx.update(tmp).set(tmp.ID_RESTAURANT, in.getIdRestaurant()).set(tmp.IMAGE, in.getImage()).
                where(tmp.ID_IMAGE.eq(in.getIdImage())).execute();
    }

    /**
     * Inserts Image record
     *
     * Use {@link #Insert(ImagesRecord in)} to insert given Image record
     *
     * @param in Image record to insert
     */
    public void Insert(ImagesRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.IMAGE).values(in.getIdRestaurant(), in.getImage()).execute();
    }

    /**
     * Delete Image record
     *
     * Use {@link #Delete(ImagesRecord in)} to delete given Image record
     *
     * @param in Image record to delete
     */
    public void Delete(ImagesRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        ctx.delete(tmp).where(tmp.ID_IMAGE.eq(in.getIdImage())).execute();
    }

    /**
     * Get all Image records of given restaurant id
     *
     * Use {@link #SelectByRestaurantId(int id)} to get all Image records of given restaurant id from database
     *
     * @param id id of restaurant
     * @return list of all Image records of given restaurant id
     */
    public List<ImagesRecord> SelectByRestaurantId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        List<ImagesRecord> ret = new ArrayList<>();
        for(ImagesRecord rec : ctx.selectFrom(tmp).where(tmp.ID_RESTAURANT.eq(id)))
            ret.add(rec);
        return ret;
    }

    /**
     * Get all Image records
     *
     * Use {@link #getConfigs()} to get all Image records from database
     *
     * @return list of all Image records
     */
    public List<ImagesRecord> getConfigs(){
        return Select();
    }
}
