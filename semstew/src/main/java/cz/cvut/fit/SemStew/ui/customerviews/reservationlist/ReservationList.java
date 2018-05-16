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
import cz.cvut.fit.SemStew.backend.Controllers.CorrectnessController;
import cz.cvut.fit.SemStew.backend.Controllers.ReservationController;
import cz.cvut.fit.SemStew.backend.Controllers.ReservationRepresentation;
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
    private final Button updateButton = new Button();
    private final Label infoLabel = new Label();
    private final Dialog checkDialog = new Dialog();
    private final ReservationConfigService reservationConfigService = new ReservationConfigService();
    private final LanguagesService languagesService = new LanguagesService();
    private final ReservationController reservationController = new ReservationController();
    private final BranchService branchService = new BranchService();
    private ReservationRepresentation update;

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

        checkStatus.setText("Check status or delete");
        checkStatus.addClassName("btn_style");

        updateButton.setText("Update");
        updateButton.addClassName("btn_style");

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
            insert.LoadDate(branchService.GetByAddress(branches.getValue()).getIdBranch(), Integer.parseInt(table.getValue()), person.getValue(),
                    email.getValue(), "Open", time.getValue(), date.getValue());
            reservationController.Insert(insert);
            infoLabel.setText("Added");
        });

        checkStatus.addClickListener(buttonClickEvent -> {
           SetUpCheckDialog();
           checkDialog.open();
        });

        updateButton.addClickListener(buttonClickEvent -> {
            SetUpEditDialog();
            checkDialog.open();
        });

        buttons.add(confirm, checkStatus, updateButton);


        content.add(header, date, time, person, table, email, branches, buttons, infoLabel);

        add(content);
    }

    private void SetUpEditDialog(){
        checkDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Update reservation");
        TextField id = new TextField("Insert ID");
        Label updateLabel = new Label("Update");
        DatePicker datePick = new DatePicker();
        datePick.setLabel("Date");
        TextField timePick = new TextField("Time");
        TextField personSet = new TextField("Name and Surname");
        TextField email = new TextField("Email");
        TextField table = new TextField("Table number");
        ComboBox<String> branchSelect = new ComboBox<>("Branches");
        Label infoLabeling = new Label();

        List<String> branchAddresses = branchService.GetAllAdresses();

        branchSelect.setItems(branchAddresses);
        branchSelect.setValue(branchAddresses.get(0));

        branchSelect.addValueChangeListener(valueChangeEvent -> {
            if(branchAddresses.stream().filter(branchAddress -> branchAddress.equals(branchSelect.getValue())).count() == 0){
                branchSelect.setErrorMessage("Select existing branch");
                branchSelect.setInvalid(true);
            }
        });


        Button update = new Button("Update");
        Button get = new Button("Get");
        Button close = new Button("Close");

        get.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabeling.setText("Fill id");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabeling.setText("Id has to contain only numbers");
                return;
            }
            this.update = reservationController.GetById(Integer.parseInt(id.getValue()));
            if(this.update == null){
                infoLabeling.setText("Incorect reservation id");
                return;
            }
            datePick.setValue(this.update.getTimeDate().toLocalDate());
            timePick.setValue(Integer.toString(this.update.getTimeDate().toLocalTime().getHour()) + ":" +
                    Integer.toString(this.update.getTimeDate().toLocalTime().getMinute()));
            personSet.setValue(this.update.getPerson());
            email.setValue(this.update.getEmail());
            branchSelect.setValue(branchService.GetById(this.update.getBranchId()).getAddress());
            table.setValue(this.update.getTableNum().toString());
        });

        update.addClickListener(buttonClickEvent -> {
            if(datePick.isEmpty() || timePick.isEmpty() || personSet.isEmpty() || email.isEmpty()
                    || branchSelect.isEmpty() || table.isEmpty()){
                infoLabeling.setText("Fill all update fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(table.getValue())){
                infoLabeling.setText("Table must contain only numbers");
                return;
            }
            if(!CorrectnessController.ValidTime(timePick.getValue())){
                infoLabeling.setText("Time must have correct format HH:MM");
                return;
            }
            if(!CorrectnessController.ValidEmail(email.getValue())){
                infoLabeling.setText("Enter valid email address");
                return;
            }
            this.update.LoadDate(branchService.GetByAddress(branchSelect.getValue()).getIdBranch(),Integer.parseInt(table.getValue()),
                    personSet.getValue(),email.getValue(),"Updated",timePick.getValue(),datePick.getValue());
            reservationController.Update(this.update);
            infoLabel.setText("Updated");
        });

        close.addClickListener(buttonClickEvent -> {
            checkDialog.close();
        });

        buttons.add(update, close);
        content.add(headLabel,id,get,updateLabel, datePick, timePick, personSet, email, branchSelect,buttons,infoLabeling);
        checkDialog.add(content);
    }

    private void SetUpCheckDialog(){
        checkDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Check reservation");
        TextField id = new TextField("Insert ID");
        TextField status = new TextField("Status");
        status.setReadOnly(true);
        Button check = new Button("Check");
        Button delete = new Button("Delete");
        Button close = new Button("Close");
        Label infoLabeling = new Label();

        check.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabeling.setText("Fill id field");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabeling.setText("Id must be only numbers");
                return;
            }

            ReservationRepresentation reservationRecord = reservationController.GetById(Integer.parseInt(id.getValue()));
            if(reservationRecord == null){
                infoLabeling.setText("Incorrect reservation number");
                return;
            }
            status.setValue(reservationRecord.getStatus());
            infoLabeling.setText("Found");
        });

        delete.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabeling.setText("Fill id field");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabeling.setText("Id must be only numbers");
                return;
            }

            ReservationRepresentation reservationRecord = reservationController.GetById(Integer.parseInt(id.getValue()));
            if(reservationRecord == null){
                infoLabeling.setText("Incorrect reservation number");
                return;
            }
            reservationRecord.setStatus("Delete");
            reservationController.Update(reservationRecord);
            infoLabel.setText("Deleted");
        });

        close.addClickListener(buttonClickEvent -> {
            checkDialog.close();
        });

        buttons.add(check,delete,close);
        content.add(headLabel,id,status,buttons,infoLabeling);
        checkDialog.add(content);
    }
}
