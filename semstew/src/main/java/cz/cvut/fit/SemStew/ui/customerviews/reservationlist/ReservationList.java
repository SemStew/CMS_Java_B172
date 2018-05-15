package cz.cvut.fit.SemStew.ui.customerviews.reservationlist;

import JOOQ.tables.records.ReservationConfigRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.CorrectnessController;
import cz.cvut.fit.SemStew.backend.ReservationController;
import cz.cvut.fit.SemStew.backend.ReservationRepresentation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.BranchService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ReservationConfigService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.util.List;

@Route(value = "reservations", layout = CustomerLayout.class)
@PageTitle("Reservations | Home")
public class ReservationList extends VerticalLayout {
    private final H2 header = new H2();
    private final DatePicker date = new DatePicker();
    private final TextField time = new TextField();
    private final TextField person = new TextField();
    private final TextField table = new TextField();
    private final TextField email = new TextField();
    private final ComboBox<String> branches = new ComboBox<>();
    private final Button confirm = new Button();
    private final Button checkStatus = new Button();
    private final Label infoLabel = new Label();
    private final Dialog checkDialog = new Dialog();
    private final ReservationConfigService reservationConfigService = new ReservationConfigService();
    private final LanguagesService languagesService = new LanguagesService();
    private final ReservationController reservationController = new ReservationController();
    private final BranchService branchService = new BranchService();

    public ReservationList()
    {
        init();
        addContent();
    }

    private void init()
    {
        setClassName("reservations");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        content.addClassName("form");
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        ReservationConfigRecord reservationConfigRecord = reservationConfigService.GetByLanguageId(id);

        List<String> branchAddresses = branchService.GetAllAdresses();

        header.setText(reservationConfigRecord.getHeader());

        date.setLabel("Date:");
        time.setLabel(reservationConfigRecord.getTimeFromDesc());
        person.setLabel("Name and Surname:");
        table.setLabel(reservationConfigRecord.getTableNumber());
        email.setLabel("Email");

        branches.setItems(branchAddresses);
        branches.setValue(branchAddresses.get(0));
        branches.setLabel("Branch");

        confirm.setText("Confirm");
        confirm.addClassName("btn_style");

        checkStatus.setText("Check status");
        checkStatus.addClassName("btn_style");

        checkDialog.setCloseOnOutsideClick(false);
        checkDialog.setCloseOnEsc(true);

        branches.addValueChangeListener(valueChangeEvent -> {
            if(branchAddresses.stream().filter(branchAddress -> branchAddress.equals(branches.getValue())).count() == 0){
                branches.setErrorMessage("Select existing branch");
                branches.setInvalid(true);
            }
        });

        confirm.addClickListener(buttonClickEvent -> {
            if(date.isEmpty() || time.isEmpty() || person.isEmpty() || table.isEmpty() || email.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.ValidEmail(email.getValue())){
                infoLabel.setText("Enter valid email address");
                return;
            }
            if(!CorrectnessController.ValidTime(time.getValue())){
                infoLabel.setText("Enter valid time. Format HH:MM");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(table.getValue())){
                infoLabel.setText("Table must be numbers only");
                return;
            }
            ReservationRepresentation insert = new ReservationRepresentation();
            insert.LoadDate(branchService.GetByAddress(branches.getValue()).getIdBranch(), Integer.parseInt(table.getValue()), email.getValue(),
                    person.getValue(), "Open", time.getValue(), date.getValue());
            reservationController.Insert(insert);
            infoLabel.setText("Added");
        });

        checkStatus.addClickListener(buttonClickEvent -> {
           SetUpDialog();
           checkDialog.open();
        });

        buttons.add(confirm, checkStatus);


        content.add(header, date, time, person, table, email, branches, buttons, infoLabel);

        add(content);
    }

    private void SetUpDialog(){
        checkDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Check reservation");
        TextField id = new TextField("Insert ID");
        TextField status = new TextField("Status");
        status.setReadOnly(true);
        Button check = new Button("Check");
        Button close = new Button("Close");
        Label infoLabel = new Label();

        check.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabel.setText("Fill id field");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabel.setText("Id must be only numbers");
                return;
            }

            ReservationRepresentation reservationRecord = reservationController.GetById(Integer.parseInt(id.getValue()));
            if(reservationRecord == null){
                infoLabel.setText("Incorrect reservation number");
                return;
            }
            status.setValue(reservationRecord.getStatus());
            infoLabel.setText("Found");
        });

        close.addClickListener(buttonClickEvent -> {
            checkDialog.close();
        });

        buttons.add(check,close);
        content.add(headLabel,id,status,buttons,infoLabel);
        checkDialog.add(content);
    }
}
