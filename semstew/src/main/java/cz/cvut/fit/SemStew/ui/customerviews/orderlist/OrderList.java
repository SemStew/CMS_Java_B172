package cz.cvut.fit.SemStew.ui.customerviews.orderlist;

import JOOQ.tables.records.OrdersRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Controllers.*;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.BranchService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

@Route(value = "orders",layout = CustomerLayout.class)
@PageTitle("Orders | Home")
public class OrderList extends VerticalLayout {
    private final H2 header = new H2();
    private final Text orderDescription = new Text("");
    private final Grid<MenuRepresentation> gridMenu = new Grid<>();
    private final Button createButton = new Button();
    private final Button updateButton = new Button();
    private final Button checkButton = new Button();
    private final Button closeOrder = new Button();
    private final Label infoLabel = new Label();
    private final MenuController menuController = new MenuController();
    private final Dialog editDialog = new Dialog();
    private final OrdersController ordersController = new OrdersController();
    private final BranchService branchService = new BranchService();
    private List<MenuRepresentation> menuRepresentations;
    private OrdersRepresentation ordersRepresentation;


    public OrderList(){
        init();
        addContent();
    }

    private void init(){
        setClassName("order");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent(){
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        HorizontalLayout buttons = new HorizontalLayout();

        editDialog.setCloseOnEsc(true);
        editDialog.setCloseOnOutsideClick(false);

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active") == null ||
                VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString().equals("none")){
            orderDescription.setText("No active order");
            SetUpCreateDialog();
            editDialog.open();
        } else {
            orderDescription.setText("Working on order " + VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString());
        }

        header.setText("Orders");

        menuRepresentations = menuController.getItems();

        gridMenu.setItems(menuRepresentations);
        gridMenu.addColumn(new ComponentRenderer<>(menuRepresentation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuRepresentation.getImageAddress());
            return tmp;
        }));
        gridMenu.addColumn(MenuRepresentation::getDescription).
                setComparator(Comparator.comparing(MenuRepresentation::getDescription)).setHeader("Menu name").setSortable(true);
        gridMenu.addColumn(new ComponentRenderer<>(clickedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.AREA_SELECT));
            tmp.addClickListener(buttonClickEvent -> {
                if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active") == null ||
                        VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString().equals("none")){
                    infoLabel.setText("You must create/open order to be able to select items");
                    return;
                }
                VaadinService.getCurrentRequest().getWrappedSession().setAttribute("menu_id",clickedItem.getIdMenu().toString());
                this.getUI().ifPresent(ui-> ui.navigate("orders/selected"));
            });
            return tmp;
        }));

        createButton.setText("Create new Order");

        createButton.addClickListener(buttonClickEvent -> {
            SetUpCreateDialog();
            editDialog.open();
        });

        updateButton.setText("Update and change Order");

        updateButton.addClickListener(buttonClickEvent -> {
            SetUpEditDialog();
            editDialog.open();
        });

        checkButton.setText("Check or delete Order");

        checkButton.addClickListener(buttonClickEvent -> {
            SetUpCheckDialog();
            editDialog.open();
        });

        closeOrder.setText("Close and send Order");

        closeOrder.addClickListener(buttonClickEvent -> {
            if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active") == null ||
                    VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString().equals("none")){
                infoLabel.setText("No order opened");
            }
            int id = Integer.parseInt(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString());
            ordersRepresentation = ordersController.LoadById(id);
            ordersRepresentation.setStatus("Closed");
            ordersController.Update(ordersRepresentation);
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("order_active","none");
            orderDescription.setText("No active order");
            infoLabel.setText("Closed and send");
        });

        buttons.add(createButton, updateButton, checkButton, closeOrder);

        content.add(header, orderDescription, gridMenu, buttons, infoLabel);

        add(content);
    }

    private void SetUpCheckDialog(){
        editDialog.removeAll();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Check or delete Order");
        TextField id = new TextField("Id order");
        TextField status = new TextField("Status");
        status.setReadOnly(true);
        Label infoLabeling = new Label();
        Button check = new Button("Check");
        Button delete = new Button("Delete order");
        Button close = new Button("Close");

        check.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabeling.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabeling.setText("Id must be numbers only");
                return;
            }
            OrdersRepresentation ordersRepresentation = ordersController.LoadById(Integer.parseInt(id.getValue()));
            if(ordersRepresentation == null){
                infoLabeling.setText("Incorrect order number");
                return;
            }
            status.setValue(ordersRepresentation.getStatus());
            infoLabeling.setText("Found");
        });

        delete.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabeling.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabeling.setText("Id must be numbers only");
                return;
            }
            OrdersRepresentation ordersRepresentation = ordersController.LoadById(Integer.parseInt(id.getValue()));
            if(ordersRepresentation == null){
                infoLabeling.setText("Incorrect order number");
                return;
            }
            ordersRepresentation.setStatus("Delete");
            ordersController.Update(ordersRepresentation);
            infoLabeling.setText("Delete requested");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(close, check, delete);
        content.add(headLabel,id, status, buttons, infoLabeling);

        editDialog.add(content);
    }

    private void SetUpEditDialog(){
        editDialog.removeAll();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Update and reopen Order");
        TextField id = new TextField("Id order");
        Button loadOrder = new Button("Load Order");
        TextField name = new TextField("Name and Surname");
        TextField email = new TextField("Email");
        TextField address = new TextField("Address");
        DatePicker date = new DatePicker("Date");
        ComboBox<String> branchSelect = new ComboBox<>("Branches");
        Label infoLabeling = new Label();
        Button update = new Button("Update and reopen");
        update.setEnabled(false);
        Button close = new Button("Close");

        List<String> branchAddresses = branchService.GetAllAdresses();

        branchSelect.setItems(branchAddresses);
        branchSelect.setValue(branchAddresses.get(0));

        loadOrder.addClickListener(buttonClickEvent -> {
            if(id.isEmpty()){
                infoLabeling.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(id.getValue())){
                infoLabeling.setText("Id must be numbers only");
                return;
            }
            ordersRepresentation = ordersController.LoadById(Integer.parseInt(id.getValue()));
            if(ordersRepresentation == null){
                infoLabeling.setText("Wrong order id");
                return;
            }
            name.setValue(ordersRepresentation.getPerson());
            email.setValue(ordersRepresentation.getEmail());
            address.setValue(ordersRepresentation.getAddress());
            date.setValue(ordersRepresentation.getDate());
            branchSelect.setValue(branchService.GetById(ordersRepresentation.getIdBranch()).getAddress());
            id.setReadOnly(true);
            update.setEnabled(true);
        });

        branchSelect.addValueChangeListener(valueChangeEvent -> {
            if(branchAddresses.stream().filter(branchAddress -> branchAddress.equals(branchSelect.getValue())).count() == 0){
                branchSelect.setErrorMessage("Select existing branch");
                branchSelect.setInvalid(true);
            }
        });

        update.addClickListener(buttonClickEvent -> {
            if(name.isEmpty() || email.isEmpty() || address.isEmpty() || date.isEmpty() || branchSelect.isEmpty()){
                infoLabeling.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.ValidEmail(email.getValue())){
                infoLabeling.setText("Enter valid email address format");
                return;
            }
            ordersRepresentation.setPerson(name.getValue());
            ordersRepresentation.setEmail(email.getValue());
            ordersRepresentation.setAddress(address.getValue());
            ordersRepresentation.setDate(date.getValue());
            ordersRepresentation.setIdBranch(branchService.GetByAddress(branchSelect.getValue()).getIdBranch());
            ordersRepresentation.setStatus("Working on");
            ordersController.Update(ordersRepresentation);
            orderDescription.setText("Working on order " + ordersRepresentation.getIdOrder().toString());
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("order_active",ordersRepresentation.getIdOrder().toString());
            infoLabeling.setText("Updated");
            editDialog.close();
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(close, update);
        content.add(headLabel,id, loadOrder, name, email,address, date,branchSelect, buttons, infoLabeling);

        editDialog.add(content);
    }

    private void SetUpCreateDialog(){
        editDialog.removeAll();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Create Order");
        TextField name = new TextField("Name and Surname");
        TextField email = new TextField("Email");
        TextField address = new TextField("Address");
        DatePicker date = new DatePicker("Date");
        ComboBox<String> branchSelect = new ComboBox<>("Branches");
        Label infoLabeling = new Label();
        Button create = new Button("Create");
        Button close = new Button("Close");

        List<String> branchAddresses = branchService.GetAllAdresses();

        branchSelect.setItems(branchAddresses);
        branchSelect.setValue(branchAddresses.get(0));

        branchSelect.addValueChangeListener(valueChangeEvent -> {
            if(branchAddresses.stream().filter(branchAddress -> branchAddress.equals(branchSelect.getValue())).count() == 0){
                branchSelect.setErrorMessage("Select existing branch");
                branchSelect.setInvalid(true);
            }
        });

        create.addClickListener(buttonClickEvent -> {
           if(name.isEmpty() || email.isEmpty() || address.isEmpty() || date.isEmpty() || branchSelect.isEmpty()){
               infoLabeling.setText("Fill all fields");
               return;
           }
           if(!CorrectnessController.ValidEmail(email.getValue())){
               infoLabeling.setText("Enter valid email address format");
               return;
           }
           OrdersRepresentation tmp = new OrdersRepresentation();
           tmp.setPerson(name.getValue());
           tmp.setEmail(email.getValue());
           tmp.setAddress(address.getValue());
           tmp.setDate(date.getValue());
           tmp.setIdBranch(branchService.GetByAddress(branchSelect.getValue()).getIdBranch());
           tmp.setStatus("Working on");
           int id = ordersController.InsertOrder(tmp);
           orderDescription.setText("Working on order " + Integer.toString(id));
           VaadinService.getCurrentRequest().getWrappedSession().setAttribute("order_active",Integer.toString(id));
           infoLabeling.setText("Created");
           editDialog.close();
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(close, create);
        content.add(headLabel, name, email,address, date,branchSelect, buttons, infoLabeling);

        editDialog.add(content);
    }
}
