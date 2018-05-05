package cz.cvut.fit.SemStew.ui.views.menulist;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import cz.cvut.fit.SemStew.backend.MenuItemControler;
import cz.cvut.fit.SemStew.backend.MenuItemRepresantation;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.math.BigDecimal;
import java.util.List;

@Route(value = "admin/dishes", layout = MainLayout.class)
@PageTitle("Dishes List | Admin")
public class MenuList extends GeneralAdminList {
    private final H2 header = new H2();
    private final Grid<MenuItemRepresantation> menuItemGrid = new Grid<>();
    private MenuItemControler menuItemControler = new MenuItemControler();
    private List<MenuItemRepresantation> menuItemRecords;
    private final Button editButton = new Button();
    private final Dialog editDialog = new Dialog();

    public MenuList() {
        init();
        addContent();
    }

    private void init() {
        addClassName("dishes-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Dishes & Beverages");

        menuItemRecords = menuItemControler.getItems();

        menuItemGrid.setItems(menuItemRecords);

        menuItemGrid.addColumn(MenuItemRepresantation::getIdMenuItem).setHeader("ID item");
        menuItemGrid.addColumn(MenuItemRepresantation::getName).setHeader("Name");
        menuItemGrid.addColumn(MenuItemRepresantation::getDescription).setHeader("Description");
        menuItemGrid.addColumn(MenuItemRepresantation::getAmount).setHeader("Amount");
        menuItemGrid.addColumn(MenuItemRepresantation::getUnitDescription).setHeader("Units");
        menuItemGrid.addColumn(MenuItemRepresantation::getCategoryDescription).setHeader("Category");
        menuItemGrid.addColumn(MenuItemRepresantation::getPrice).setHeader("Price");

        setUpDialog();

        editButton.setText("Edit");

        editButton.addClickListener(buttonClickEvent -> {
            editDialog.open();
        });

        content.add(header, menuItemGrid, editButton);

        add(content);
    }

    private void setUpDialog()
    {
        editDialog.setCloseOnEsc(true);
        editDialog.setCloseOnOutsideClick(false);

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        List<String> category = menuItemControler.getCategories();
        List<String> unit = menuItemControler.getUnits();

        Label headLabel = new Label("Edit");
        TextField idItem = new TextField("ID item:");
        TextField name = new TextField("Name:");
        TextField description = new TextField("Description:");
        TextField amount = new TextField("Amount:");
        ComboBox<String> units = new ComboBox<>("Units:");
        units.setItems(unit);
        ComboBox<String> categories = new ComboBox<>("Categories:");
        categories.setItems(category);
        TextField price = new TextField("Price:");
        Label infoLabel = new Label();

        Button insert = new Button("Insert");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button close = new Button("Close");

        insert.addClickListener(buttonClickEvent -> {
           MenuItemRepresantation tmp = new MenuItemRepresantation();
           tmp.setIdMenuItem(Integer.parseInt(idItem.getValue()));
           tmp.setAmount(new BigDecimal(amount.getValue()));
           tmp.setCategoryDescription(categories.getValue());
           tmp.setDescription(description.getValue());
           tmp.setName(name.getValue());
           tmp.setPrice(Integer.parseInt(price.getValue()));
           tmp.setUnitDescription(units.getValue());
           tmp.setIdLanguage(1);

           menuItemControler.Insert(tmp);
           menuItemRecords = menuItemControler.getItems();
           menuItemGrid.setItems(menuItemRecords);
           infoLabel.setText("Inserted");
        });

        update.addClickListener(buttonClickEvent -> {
            MenuItemRepresantation tmp = new MenuItemRepresantation();
            tmp.setIdMenuItem(Integer.parseInt(idItem.getValue()));
            tmp.setAmount(new BigDecimal(amount.getValue()));
            tmp.setCategoryDescription(categories.getValue());
            tmp.setDescription(description.getValue());
            tmp.setName(name.getValue());
            tmp.setPrice(Integer.parseInt(price.getValue()));
            tmp.setUnitDescription(units.getValue());
            tmp.setIdLanguage(1);

            menuItemControler.Update(tmp);
            menuItemRecords = menuItemControler.getItems();
            menuItemGrid.setItems(menuItemRecords);
            infoLabel.setText("Updated");
        });

        delete.addClickListener(buttonClickEvent -> {
            MenuItemRepresantation tmp = new MenuItemRepresantation();
            tmp.setIdMenuItem(Integer.parseInt(idItem.getValue()));
            tmp.setAmount(new BigDecimal(amount.getValue()));
            tmp.setCategoryDescription(categories.getValue());
            tmp.setDescription(description.getValue());
            tmp.setName(name.getValue());
            tmp.setPrice(Integer.parseInt(price.getValue()));
            tmp.setUnitDescription(units.getValue());
            tmp.setIdLanguage(1);

            menuItemControler.Delete(tmp);
            menuItemRecords = menuItemControler.getItems();
            menuItemGrid.setItems(menuItemRecords);
            infoLabel.setText("Deleted");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(insert,update,delete,close);

        content.add(headLabel,name,description,amount,units,categories,price,buttons,infoLabel);

        editDialog.add(content);
    }
}