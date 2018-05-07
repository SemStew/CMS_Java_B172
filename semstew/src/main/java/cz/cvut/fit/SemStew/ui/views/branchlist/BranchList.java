package cz.cvut.fit.SemStew.ui.views.branchlist;

import JOOQ.tables.records.BranchRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.BranchService;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.util.List;

@Route(value = "admin", layout = MainLayout.class)
@PageTitle("Branch List | Admin")
public class BranchList extends GeneralAdminList {

    private final H2 header = new H2();
    private BranchService branchServis = new BranchService();
    private final Grid<BranchRecord> gridBranch = new Grid<>();
    private List<BranchRecord> branchRecords;
    private final Button editButton = new Button();
    private final Dialog editDialog = new Dialog();

    public BranchList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("branch-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);


        header.setText("Branches");

        //header.setText(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("myvalue").toString());

        branchRecords = branchServis.getConfigs();

        gridBranch.setItems(branchRecords);
        gridBranch.addColumn(BranchRecord::getIdBranch).setHeader("ID branch");
        gridBranch.addColumn(BranchRecord::getAddress).setHeader("Address");
        gridBranch.addColumn(BranchRecord::getPhone).setHeader("Phone");
        gridBranch.addColumn(BranchRecord::getOpeningHours).setHeader("Openning hours");
        gridBranch.addColumn(BranchRecord::getDescription).setHeader("Description");
        gridBranch.addColumn(BranchRecord::getIdRestaurant).setHeader("ID restaurant");

        setUpDialog();

        editButton.setText("Edit");

        editButton.addClickListener(buttonClickEvent -> {
            editDialog.open();
        });

        content.add(header, gridBranch, editButton);

        super.add(content);
    }

    private void setUpDialog()
    {
        editDialog.setCloseOnOutsideClick(true);
        editDialog.setCloseOnEsc(true);

        VerticalLayout content_layout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label descriptionLabel = new Label("Edit");

        TextField idBranch = new TextField("ID branch");
        TextField address = new TextField("Address");
        TextField phone = new TextField("Phone");
        TextField openHours = new TextField("Opening Hours");
        TextField description = new TextField("Description");
        TextField idRestaurant = new TextField("ID restaurant");

        Button insert = new Button("Insert");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button close = new Button("Close");

        Label infoLabel = new Label();

        insert.addClickListener(buttonClickEvent -> {
           BranchRecord tmp = new BranchRecord();
           tmp.setIdBranch(Integer.parseInt(idBranch.getValue()));
           tmp.setAddress(address.getValue());
           tmp.setPhone(phone.getValue());
           tmp.setOpeningHours(openHours.getValue());
           tmp.setDescription(description.getValue());
           tmp.setIdRestaurant(Integer.parseInt(idRestaurant.getValue()));

           branchServis.InsertBranchService(tmp);
           infoLabel.setText("Inserted");
           branchRecords = branchServis.getConfigs();
           gridBranch.setItems(branchRecords);
        });

        update.addClickListener(buttonClickEvent ->{
            BranchRecord tmp = new BranchRecord();
            tmp.setIdBranch(Integer.parseInt(idBranch.getValue()));
            tmp.setAddress(address.getValue());
            tmp.setPhone(phone.getValue());
            tmp.setOpeningHours(openHours.getValue());
            tmp.setDescription(description.getValue());
            tmp.setIdRestaurant(Integer.parseInt(idRestaurant.getValue()));

            branchServis.UpdateBranchService(tmp);
            infoLabel.setText("Updated");
            branchRecords = branchServis.getConfigs();
            gridBranch.setItems(branchRecords);
        });

        delete.addClickListener(buttonClickEvent -> {
            BranchRecord tmp = new BranchRecord();
            tmp.setIdBranch(Integer.parseInt(idBranch.getValue()));
            tmp.setAddress(address.getValue());
            tmp.setPhone(phone.getValue());
            tmp.setOpeningHours(openHours.getValue());
            tmp.setDescription(description.getValue());
            tmp.setIdRestaurant(Integer.parseInt(idRestaurant.getValue()));

            branchServis.DeleteBranchService(tmp);
            infoLabel.setText("Deleted");
            branchRecords = branchServis.getConfigs();
            gridBranch.setItems(branchRecords);
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(insert,update,delete,close);
        content_layout.add(descriptionLabel,idBranch,address,phone,openHours,description,idRestaurant,buttons,infoLabel);
        editDialog.add(content_layout);
    }
}