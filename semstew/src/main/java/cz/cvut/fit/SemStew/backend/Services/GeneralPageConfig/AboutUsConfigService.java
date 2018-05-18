package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.AboutUsConfig;
import JOOQ.tables.Units;
import JOOQ.tables.records.AboutUsConfigRecord;
import JOOQ.tables.records.UnitsRecord;
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
public class AboutUsConfigService {
    /**
     * Database context
     */
    private DSLContext ctx;

    /**
     * AboutUsConfigService constructor
     */
    public AboutUsConfigService() {}

    /**
     * Get all AboutUs records
     *
     * Use {@link #SelectAllAboutUsConfigService()} to get all AboutUs records
     *
     * @return List of all AboutUs records from the database
     */
    public List<AboutUsConfigRecord> SelectAllAboutUsConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AboutUsConfigRecord> configs = new ArrayList<AboutUsConfigRecord>();
        AboutUsConfig a = new AboutUsConfig();
        for (AboutUsConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update given AboutUs record
     *
     * Use {@link #UpdateAboutUsConfigService(AboutUsConfigRecord a)} to update given record
     *
     * @param a record that will be updated
     */
    public void UpdateAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.update(tmp).set(tmp.HEADER, a.getHeader()).set(tmp.FOTOGALLERY_HEADER, a.getFotogalleryHeader()).
                        set(tmp.DESCRIPTION, a.getDescription()).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Insert new AboutUs record
     *
     * Use {@link #InsertAboutUsConfigService(AboutUsConfigRecord a)} to insert given record
     *
     * @param a record that will be inserted
     */
    public void InsertAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.insertInto(tmp).columns(tmp.ID_LANGUAGE, tmp.HEADER, tmp.DESCRIPTION, tmp.FOTOGALLERY_HEADER).
                            values(a.getIdLanguage(), a.getHeader(), a.getDescription(), a.getFotogalleryHeader()).execute();
    }

    /**
     * Delete AboutUs record
     *
     * Use {@link #DeleteAboutUsConfigService(AboutUsConfigRecord a)} to delete given record
     *
     * @param a record that will be deleted
     */
    public void DeleteAboutUsConfigService(AboutUsConfigRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        ctx.delete(tmp).where(tmp.ID_LANGUAGE.eq(a.getIdLanguage())).execute();
    }

    /**
     * Get AboutUs record by language
     *
     * Use {@link #GetByLanguageId(int id)} to get AboutUs record by language
     *
     * @param id identification number of requested language
     * @return AboutUs record if it exists, else null
     */
    public AboutUsConfigRecord GetByLanguageId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        AboutUsConfig tmp = new AboutUsConfig();
        for(AboutUsConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(id)))
            return rec;
        return null;
    }

    /**
     * Gets all AboutUs records from database
     *
     * Use {@link #getConfigs()} to get all AboutUs records from database
     *
     * @return list of all AboutUs records
     */
    public List<AboutUsConfigRecord> getConfigs() {
        return SelectAllAboutUsConfigService();
    }

    /**
     * Units service class
     */
    public static class UnitsService {
        /**
         * Database context
         */
        private DSLContext ctx;

        /**
         *  UnitsService constructor
         */
        public UnitsService() {}

        /**
         * Select all Units records from database
         *
         * Use {@link #SelectUnitsService()} to get all Units records from database
         *
         * @return list of all available Units records
         */
        public List<UnitsRecord> SelectUnitsService(){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            List<UnitsRecord> configs = new ArrayList<UnitsRecord>();
            Units a = new Units();
            for (UnitsRecord rec : ctx.selectFrom(a)) {
                configs.add(rec);
            }

            return configs;
        }

        /**
         * Updates given Unit record
         *
         * Use {@link #UpdateUnitsService(UnitsRecord a)} to update given Unit record
         *
         * @param a record to be updated
         */
        public void UpdateUnitsService(UnitsRecord a){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            ctx.update(tmp).set(tmp.NAME, a.getName()).
                    where(tmp.ID_UNIT.eq(a.getIdUnit())).execute();
        }

        /**
         * Inserts new Unit record
         *
         * Use {@link #InsertUnitsService(UnitsRecord a)} to insert Unit record into database
         *
         * @param a record to be inserted
         */
        public void InsertUnitsService(UnitsRecord a){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
        }

        /**
         * Deletes given Unit record
         *
         * Use {@link #DeleteUnitsService(UnitsRecord a)} to delete given Unit record
         *
         * @param a record to be deleted
         */
        public void DeleteUnitsService(UnitsRecord a){
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            ctx.delete(tmp).where(tmp.ID_UNIT.eq(a.getIdUnit())).execute();
        }


        /**
         * Gets Unit record by given id
         *
         * Use {@link #UnitById(Integer id)} to get Unit record by given id
         *
         * @param id id of searched Unit record
         * @return Unit of given id if found, else null
         */
        public UnitsRecord UnitById(Integer id)
        {
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            for(UnitsRecord res : ctx.selectFrom(tmp).where(tmp.ID_UNIT.eq(id)))
            {
                return res;
            }

            return null;
        }

        /**
         * Get Unit record by given name
         *
         * Use {@link #UnitByName(String name)} to get Unit record by name
         *
         * @param name name of searched Unit record
         * @return Unit of given name if found, else null
         */
        public UnitsRecord UnitByName(String name)
        {
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            for(UnitsRecord res : ctx.selectFrom(tmp).where(tmp.NAME.eq(name)))
                return res;
            return null;
        }

        /**
         * Gets all Units descriptions
         *
         * Use {@link #UnitsDescription()} to get all Units descriptions
         *
         * @return list of all Units descriptions
         */
        public List<String> UnitsDescription()
        {
            ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
            Units tmp = new Units();
            List<String> ret = new ArrayList<>();
            for(UnitsRecord res : ctx.selectFrom(tmp))
            {
                ret.add(res.getName());
            }

            return ret;
        }

        /**
         * Gets all Units record
         *
         * Use {@link #getConfigs()} to get all Unit records from database
         *
         * @return list of all Unit records
         */
        public List<UnitsRecord> getConfigs() {
            return SelectUnitsService();
        }
    }
}
