package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.ContactConfig;
import JOOQ.tables.records.ContactConfigRecord;
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
public class ContactConfigService {
    /**
     * Database context
     */
    private DSLContext ctx;

    /**
     * ContactsConfigService constructor
     */
    public ContactConfigService() {}

    /**
     * Gets all ContactConfig records
     *
     * Use {@link #SelectContactConfigService()} to get all ContactConfig records from database
     *
     * @return list of all ContactConfigs records
     */
    public List<ContactConfigRecord> SelectContactConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<ContactConfigRecord> configs = new ArrayList<ContactConfigRecord>();
        ContactConfig a = new ContactConfig();
        for (ContactConfigRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Updates given ContactConfig record
     *
     * Use {@link #UpdateContactConfigService(ContactConfigRecord a)} to update given ContactConfig record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a ContactConfig record to be updated
     */
    public void UpdateContactConfigService(ContactConfigRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Inserts new ContactConfig record
     *
     * Use {@link #InsertContactConfigService(ContactConfigRecord a)} to insert given ContactConfig record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a ContactConfig record to be inserted
     */
    public void InsertContactConfigService(ContactConfigRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Deletes given ContactConfig record
     *
     * Use {@link #DeleteContactConfigService(ContactConfigRecord a)} to delete given ContactConfig record
     *
     * @deprecated Do not use this method, it is not implemented
     *
     * @param a ContactsConfig record to delete
     */
    public void DeleteContactConfigService(ContactConfigRecord a){
        System.out.println("Method not implemented");
    }

    /**
     * Gets ContactConfig in selected language
     *
     * Use {@link #GetByLanguageId(int id)} to get ContactConfig by given language
     *
     * @param id requested language identification number
     * @return ContactConfig of given language id if it exists, else null
     */
    public ContactConfigRecord GetByLanguageId(int id){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        ContactConfig tmp = new ContactConfig();
        for(ContactConfigRecord rec : ctx.selectFrom(tmp).where(tmp.ID_LANGUAGE.eq(id)))
            return rec;
        return null;
    }

    /**
     * Gets all ContactConfigs record
     *
     * Use {@link #getConfigs()} to get all ContactsConfigs from database
     *
     * @return list of all ContactsConfigs
     */
    public List<ContactConfigRecord> getConfigs() {
        return SelectContactConfigService();
    }
}
