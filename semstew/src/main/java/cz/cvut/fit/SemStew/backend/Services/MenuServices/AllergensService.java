package cz.cvut.fit.SemStew.backend.Services.MenuServices;

import JOOQ.tables.Allergens;
import JOOQ.tables.records.AllergensRecord;
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
public class AllergensService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * AllergensService constructor
     */
    public AllergensService() {}

    /**
     * Get all Allergens records
     *
     * Use {@link #SelectAllergensService()} to get all Allergens records from database
     *
     * @return list of all Allergens records
     */
    public List<AllergensRecord> SelectAllergensService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<AllergensRecord> configs = new ArrayList<AllergensRecord>();
        Allergens a = new Allergens();
        for (AllergensRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Allergens record
     *
     * Use {@link #UpdateAllergensService(AllergensRecord a)} to update given Allergens record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a Allergens record to be updated
     */
    public void UpdateAllergensService(AllergensRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Insert new Allergens record
     *
     * Use {@link #InsertAllergensService(AllergensRecord a)} to insert given Allergens record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a Allergens record to be inserted
     */
    public void InsertAllergensService(AllergensRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Delete Allergens record
     *
     * Use {@link #DeleteAllergensService(AllergensRecord a)} to delete given Allergens record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a Allergens record to be deleted
     */
    public void DeleteAllergensService(AllergensRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Get all Allergens records
     *
     * Use {@link #getConfigs()} to get all Allergens records from database
     *
     * @return list of all Allergens records
     */
    public List<AllergensRecord> getConfigs() {
        return SelectAllergensService();
    }
}

