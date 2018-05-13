package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Images;
import JOOQ.tables.records.ImagesRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class ImageServis {
    private DSLContext ctx;

    public ImageServis(){}

    // select
    private List<ImagesRecord> Select(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        List<ImagesRecord> ret = new ArrayList<>();
        for(ImagesRecord rec : ctx.selectFrom(tmp))
            ret.add(rec);
        return ret;
    }

    // update
    public void Update(ImagesRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        ctx.update(tmp).set(tmp.ID_RESTAURANT, in.getIdRestaurant()).set(tmp.IMAGE, in.getImage()).
                where(tmp.ID_IMAGE.eq(in.getIdImage())).execute();
    }

    //insert
    public void Insert(ImagesRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        ctx.insertInto(tmp).columns(tmp.ID_RESTAURANT, tmp.IMAGE).values(in.getIdRestaurant(), in.getImage()).execute();
    }

    // delete
    public void Delete(ImagesRecord in){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        ctx.delete(tmp).where(tmp.ID_IMAGE.eq(in.getIdImage())).execute();
    }

    // select by restaurant id
    public List<ImagesRecord> SelectByRestaurantId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Images tmp = new Images();
        List<ImagesRecord> ret = new ArrayList<>();
        for(ImagesRecord rec : ctx.selectFrom(tmp).where(tmp.ID_RESTAURANT.eq(id)))
            ret.add(rec);
        return ret;
    }

    public List<ImagesRecord> getConfigs(){
        return Select();
    }
}
