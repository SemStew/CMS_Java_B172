package cz.cvut.fit.SemStew.ui.views.branchlist;

import JOOQ.tables.records.BranchRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.BranchService;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.util.Comparator;
import java.util.List;

@Route(value = "admin", layout = MainLayout.class)
@PageTitle("Branch List | Admin")
public class BranchList extends GeneralAdminList {

    private final H2 header = new H2();
    private BranchService branchServis = new BranchService();
    private final Grid<BranchRecord> gridBranch = new Grid<>();
    private List<BranchRecord> branchRecords;
    private final Button addButton = new Button();
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

        branchRecords = branchServis.getConfigs();

        gridBranch.setItems(branchRecords);
        gridBranch.addColumn(BranchRecord::getAddress).setComparator(Comparator.comparing(BranchRecord::getAddress)).setHeader("Address").setSortable(true);
        gridBranch.addColumn(BranchRecord::getPhone).setComparator(Comparator.comparing(BranchRecord::getPhone)).setHeader("Phone").setSortable(true);
        gridBranch.addColumn(BranchRecord::getOpeningHours).setHeader("Openning hours");
        gridBranch.addColumn(BranchRecord::getDescription).setHeader("Description");
        gridBranch.addColumn(new ComponentRenderer<>(clickedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.EDIT));
            tmp.addClickListener(buttonClickEvent -> {
                setUpDialogEdit(clickedItem);
                editDialog.open();
            });
            return tmp;
        }));
        gridBranch.addColumn(new ComponentRenderer<>(clickedItem ->{
           Button tmp = new Button(new Icon(VaadinIcons.CLOSE));
           tmp.addClickListener(buttonClickEvent -> {
               deleteEntry(clickedItem);
           });
           return tmp;
        }));

        editDialog.setCloseOnOutsideClick(true);
        editDialog.setCloseOnEsc(true);

        addButton.setText("Add");

        addButton.addClickListener(buttonClickEvent -> {
            setUpDialogAdd();
            editDialog.open();
        });

        content.add(header, gridBranch, addButton);

        super.add(content);
    }

    private void deleteEntry(BranchRecord branchRecord){
        branchServis.DeleteBranchService(branchRecord);
        branchRecords = branchServis.getConfigs();
        gridBranch.setItems(branchRecords);
    }

    private void setUpDialogEdit(BranchRecord rec)
    {
        editDialog.removeAll();
        VerticalLayout content_layout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label descriptionLabel = new Label("Edit");

        TextField address = new TextField("Address");
        address.setValue(rec.getAddress());
        TextField phone = new TextField("Phone");
        phone.setValue(rec.getPhone());
        TextField openHours = new TextField("Opening Hours");
        openHours.setValue(rec.getOpeningHours());
        TextField description = new TextField("Description");
        description.setValue(rec.getDescription());

        Button update = new Button("Update");
        Button close = new Button("Close");

        Label infoLabel = new Label();

        update.addClickListener(buttonClickEvent -> {
            if(address.isEmpty() || phone.isEmpty() || openHours.isEmpty() || description.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            rec.setAddress(address.getValue());
            rec.setPhone(phone.getValue());
            rec.setOpeningHours(openHours.getValue());
            rec.setDescription(description.getValue());

            branchServis.UpdateBranchService(rec);
            infoLabel.setText("Updated");
            branchRecords = branchServis.getConfigs();
            gridBranch.setItems(branchRecords);
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(update, close);
        content_layout.add(descriptionLabel, address, phone, openHours, description, buttons, infoLabel);
        editDialog.add(content_layout);
    }

    private void setUpDialogAdd()
    {
        editDialog.removeAll();
        VerticalLayout content_layout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label descriptionLabel = new Label("Add");

        TextField address = new TextField("Address");
        TextField phone = new TextField("Phone");
        TextField openHours = new TextField("Opening Hours");
        TextField description = new TextField("Description");

        Button insert = new Button("Add");
        Button close = new Button("Close");

        Label infoLabel = new Label();

        insert.addClickListener(buttonClickEvent -> {
            if(address.isEmpty() || phone.isEmpty() || openHours.isEmpty() || description.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            BranchRecord tmp = new BranchRecord();
            tmp.setIdBranch(1);
            tmp.setAddress(address.getValue());
            tmp.setPhone(phone.getValue());
            tmp.setOpeningHours(openHours.getValue());
            tmp.setDescription(description.getValue());
            tmp.setIdRestaurant(1);

            branchServis.InsertBranchService(tmp);
            infoLabel.setText("Added");
            branchRecords = branchServis.getConfigs();
            gridBranch.setItems(branchRecords);
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(insert, close);
        content_layout.add(descriptionLabel, address, phone, openHours, description, buttons, infoLabel);
        editDialog.add(content_layout);
    }
}