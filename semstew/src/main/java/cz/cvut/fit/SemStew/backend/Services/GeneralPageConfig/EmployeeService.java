package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Employee;
import JOOQ.tables.records.EmployeeRecord;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private DSLContext ctx;

    public EmployeeService() {}

    //select
    public List<EmployeeRecord> SelectEmployeeService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<EmployeeRecord> configs = new ArrayList<EmployeeRecord>();
        Employee a = new Employee();
        for (EmployeeRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    //update
    public void UpdateEmployeeService(EmployeeRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Employee tmp = new Employee();
        ctx.update(tmp).set(tmp.ID_BRANCH, a.getIdBranch()).set(tmp.MAIL, a.getMail()).set(tmp.ROLE, a.getRole()).
                        set(tmp.NAME, a.getName()).set(tmp.PHONE, a.getPhone()).set(tmp.SURNAME, a.getSurname()).
                where(tmp.ID_EMPLOYEE.eq(a.getIdEmployee())).execute();
    }

    //insert
    public void InsertEmployeeService(EmployeeRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Employee tmp = new Employee();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.MAIL, tmp.NAME, tmp.ROLE, tmp.PHONE, tmp.SURNAME).
                values(a.getIdBranch(), a.getMail(), a.getName(), a.getRole(), a.getPhone(), a.getSurname()).execute();
    }

    //delete
    public void DeleteEmployeeService(EmployeeRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Employee tmp = new Employee();
        ctx.delete(tmp).where(tmp.ID_EMPLOYEE.eq(a.getIdEmployee())).execute();
    }

    public List<EmployeeRecord> getConfigs() {
        return SelectEmployeeService();
    }
}
