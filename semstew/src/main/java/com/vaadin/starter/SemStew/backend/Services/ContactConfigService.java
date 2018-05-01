package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.ContactConfig;
import JOOQ.tables.records.ContactConfigRecord;
import JOOQ.tables.records.ContactConfigRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class ContactConfigService {
    private List<ContactConfigRecord> configs;
    private DSLContext ctx;

    public ContactConfigService() {
        SelectContactConfigService();
    }

    //select
    public void SelectContactConfigService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<ContactConfigRecord>();
        ContactConfig a = new ContactConfig();
        for (ContactConfigRecord rec : ctx.selectFrom(a).where(a.ID_LANGUAGE.eq(1))) {
            configs.add(rec);
        }
    }

    //update
    public void UpdateContactConfigService(ContactConfigRecord a){
        SelectContactConfigService();
    }

    //insert
    public void InsertContactConfigService(ContactConfigRecord a){
        SelectContactConfigService();
    }

    //delete
    public void DeleteContactConfigService(ContactConfigRecord a){
        SelectContactConfigService();
    }

    public List<ContactConfigRecord> getConfigs() {
        return configs;
    }
}
