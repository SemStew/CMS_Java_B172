package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.ContactConfig;
import JOOQ.tables.records.ContactConfigRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class ContactConfigService {
    private DSLContext ctx;

    public ContactConfigService() {}

    //select
    public List<ContactConfigRecord> SelectContactConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<ContactConfigRecord> configs = new ArrayList<ContactConfigRecord>();
        ContactConfig a = new ContactConfig();
        for (ContactConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateContactConfigService(ContactConfigRecord a){
        System.out.println("Method not implemented");
    }

    //insert
    public void InsertContactConfigService(ContactConfigRecord a){
        System.out.println("Method not implemented");
    }

    //delete
    public void DeleteContactConfigService(ContactConfigRecord a){
        System.out.println("Method not implemented");
    }

    public List<ContactConfigRecord> getConfigs() {
        return SelectContactConfigService();
    }
}
