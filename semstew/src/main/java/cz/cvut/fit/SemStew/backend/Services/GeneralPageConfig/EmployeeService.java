package cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig;

import JOOQ.tables.Employee;
import JOOQ.tables.records.EmployeeRecord;
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
public class EmployeeService {
    /**
     * database context
     */
    private DSLContext ctx;

    /**
     * EmployeesService constructor
     */
    public EmployeeService() {}

    /**
     * Gets all Employee records
     *
     * Use {@link #SelectEmployeeService()} to get all Employee records from database
     *
     * @return list of all Employee records
     */
    public List<EmployeeRecord> SelectEmployeeService(){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        List<EmployeeRecord> configs = new ArrayList<EmployeeRecord>();
        Employee a = new Employee();
        for (EmployeeRecord rec : ctx.selectFrom(a)) {
            configs.add(rec);
        }

        return configs;
    }

    /**
     * Update Employee record
     *
     * Use {@link #UpdateEmployeeService(EmployeeRecord a)} to update given Employee record
     *
     * @param a Employee record to update
     */
    public void UpdateEmployeeService(EmployeeRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Employee tmp = new Employee();
        ctx.update(tmp).set(tmp.ID_BRANCH, a.getIdBranch()).set(tmp.MAIL, a.getMail()).set(tmp.ROLE, a.getRole()).
                        set(tmp.NAME, a.getName()).set(tmp.PHONE, a.getPhone()).set(tmp.SURNAME, a.getSurname()).
                where(tmp.ID_EMPLOYEE.eq(a.getIdEmployee())).execute();
    }

    /**
     * Insert Employee record
     *
     * Use {@link #InsertEmployeeService(EmployeeRecord a)} to insert given Employee record
     *
     * @param a Employee record to insert
     */
    public void InsertEmployeeService(EmployeeRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Employee tmp = new Employee();
        ctx.insertInto(tmp).columns(tmp.ID_BRANCH, tmp.MAIL, tmp.NAME, tmp.ROLE, tmp.PHONE, tmp.SURNAME).
                values(a.getIdBranch(), a.getMail(), a.getName(), a.getRole(), a.getPhone(), a.getSurname()).execute();
    }

    /**
     * Delete Employee record
     *
     * Use {@link #DeleteEmployeeService(EmployeeRecord a)} to delete given Employee record
     *
     * @param a Employee record to delete
     */
    public void DeleteEmployeeService(EmployeeRecord a){
        ctx = DSL.using(PostgreSQLConnection.getConnection(), SQLDialect.POSTGRES);
        Employee tmp = new Employee();
        ctx.delete(tmp).where(tmp.ID_EMPLOYEE.eq(a.getIdEmployee())).execute();
    }

    /**
     * Get all Employee records
     *
     * Use {@link #getConfigs()} to get all Employee records
     *
     * @return list of all Employee record
     */
    public List<EmployeeRecord> getConfigs() {
        return SelectEmployeeService();
    }
}
