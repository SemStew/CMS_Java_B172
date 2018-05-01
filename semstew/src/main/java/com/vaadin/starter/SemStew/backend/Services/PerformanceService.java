package com.vaadin.starter.SemStew.backend.Services;

import JOOQ.tables.Performance;
import JOOQ.tables.records.PerformanceRecord;
import com.vaadin.starter.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class PerformanceService {
    private List<PerformanceRecord> configs;
    private DSLContext ctx;

    public PerformanceService() {
        SelectPerformanceService();
    }

    //select
    public void SelectPerformanceService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        configs = new ArrayList<PerformanceRecord>();
        Performance a = new Performance();
        for (PerformanceRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }
    }

    //update
    public void UpdatePerformanceService(PerformanceRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Performance tmp = new Performance();
        ctx.update(tmp).set(tmp.NAME, a.getName()).
                where(tmp.ID_PERFORMANCE.eq(a.getIdPerformance())).execute();
        SelectPerformanceService();
    }

    //insert
    public void InsertPerformanceService(PerformanceRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Performance tmp = new Performance();
        ctx.insertInto(tmp).columns(tmp.NAME).values(a.getName()).execute();
        SelectPerformanceService();
    }

    //delete
    public void DeletePerformanceService(PerformanceRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Performance tmp = new Performance();
        ctx.delete(tmp).where(tmp.ID_PERFORMANCE.eq(a.getIdPerformance())).execute();
        SelectPerformanceService();
    }

    public List<PerformanceRecord> getConfigs() {
        return configs;
    }
}
