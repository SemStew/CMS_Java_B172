package cz.cvut.fit.SemStew.ui.views.selectedadminmenu;

import JOOQ.tables.records.LanguagesRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import cz.cvut.fit.SemStew.backend.CorrectnessController;
import cz.cvut.fit.SemStew.backend.MenuItemController;
import cz.cvut.fit.SemStew.backend.MenuItemRepresentation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.AllergensNameService;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;
import cz.cvut.fit.SemStew.ui.views.menulist.MenuList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Route(value = "admin/dishes/selected",layout = MainLayout.class)
@PageTitle("Selected Dishes | Admin")
public class SelectedAdminMenu extends GeneralAdminList
    implements RouterLayout {
    private final H2 header = new H2();
    private final Grid<MenuItemRepresentation> gridMenu = new Grid<>();
    private MenuItemController menuItemController = new MenuItemController();
    private LanguagesService languagesService = new LanguagesService();
    private final AllergensNameService allergensNameService = new AllergensNameService();
    private List<MenuItemRepresentation> menuItems;
    private RouterLink back;
    private final Dialog editDialog = new Dialog();
    private final Button addButton = new Button();
    // Allergen checkboxes
    private final Checkbox allergen1 = new Checkbox();
    private final Checkbox allergen2 = new Checkbox();
    private final Checkbox allergen3 = new Checkbox();
    private final Checkbox allergen4 = new Checkbox();
    private final Checkbox allergen5 = new Checkbox();
    private final Checkbox allergen6 = new Checkbox();
    private final Checkbox allergen7 = new Checkbox();
    private final Checkbox allergen8 = new Checkbox();
    private final Checkbox allergen9 = new Checkbox();
    private final Checkbox allergen10 = new Checkbox();
    private final Checkbox allergen11 = new Checkbox();
    private final Checkbox allergen12 = new Checkbox();
    private final Checkbox allergen13 = new Checkbox();
    private final Checkbox allergen14 = new Checkbox();

    public SelectedAdminMenu()
    {
        init();
        addContent();
    }

    private void init() {
        addClassName("dishes-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Menu");

        menuItems = menuItemController.getItems();

        back = new RouterLink(null, MenuList.class);
        back.add(new Button("Back"));

        gridMenu.setHeightByRows(true);
        gridMenu.setItems(menuItems);
        gridMenu.addColumn(new ComponentRenderer<>(menuItemRepresentation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuItemRepresentation.getImageAddress());
            return tmp;
        }));
        gridMenu.addColumn(MenuItemRepresentation::getName).setComparator(Comparator.comparing(MenuItemRepresentation::getName)).setHeader("Name").setSortable(true);
        gridMenu.addColumn(MenuItemRepresentation::getDescription).setHeader("Description");
        gridMenu.addColumn(MenuItemRepresentation::getAmount).setHeader("Amount");
        gridMenu.addColumn(MenuItemRepresentation::getUnitDescription).setHeader("Units");
        gridMenu.addColumn(MenuItemRepresentation::getCategoryDescription).setComparator(Comparator.comparing(MenuItemRepresentation::getCategoryDescription)).setHeader("Category").setSortable(true);
        gridMenu.addColumn(MenuItemRepresentation::getPrice).setComparator(Comparator.comparing(MenuItemRepresentation::getPrice)).setHeader("Price").setSortable(true);
        gridMenu.addColumn(MenuItemRepresentation::getAlergens).setHeader("Alergens");
        gridMenu.addColumn(new ComponentRenderer<>(clickedItem -> {
            Button tmp = new Button(new Icon(VaadinIcons.EDIT));
            tmp.addClickListener(buttonClickEvent -> {
                setUpEditDialog(clickedItem);
                editDialog.open();
            });
            return tmp;
        }));
        gridMenu.addColumn(new ComponentRenderer<>(clickedItem -> {
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE));
            tmp.addClickListener(buttonClickEvent -> {
                menuItemController.Delete(clickedItem);
                RefreshValues();
            });
            return tmp;
        }));

        addButton.setText("Add");

        editDialog.setCloseOnEsc(true);
        editDialog.setCloseOnOutsideClick(false);

        addButton.addClickListener(buttonClickEvent -> {
            setUpAddDialog();
            editDialog.open();
        });

        buttons.add(back, addButton);

        content.add(header, gridMenu, buttons);

        add(content);
    }

    private void RefreshValues(){
        menuItems = menuItemController.getItems();
        gridMenu.setItems(menuItems);
    }

    private void setUpCheckboxes(int language){
        List<String> allergenNames = allergensNameService.GetAllDescriptionsInLanguage(language);

        allergen1.setLabel(allergenNames.get(0));
        allergen1.setValue(false);
        allergen2.setLabel(allergenNames.get(1));
        allergen2.setValue(false);
        allergen3.setLabel(allergenNames.get(2));
        allergen3.setValue(false);
        allergen4.setLabel(allergenNames.get(3));
        allergen4.setValue(false);
        allergen5.setLabel(allergenNames.get(4));
        allergen5.setValue(false);
        allergen6.setLabel(allergenNames.get(5));
        allergen6.setValue(false);
        allergen7.setLabel(allergenNames.get(6));
        allergen7.setValue(false);
        allergen8.setLabel(allergenNames.get(7));
        allergen8.setValue(false);
        allergen9.setLabel(allergenNames.get(8));
        allergen9.setValue(false);
        allergen10.setLabel(allergenNames.get(9));
        allergen10.setValue(false);
        allergen11.setLabel(allergenNames.get(10));
        allergen11.setValue(false);
        allergen12.setLabel(allergenNames.get(11));
        allergen12.setValue(false);
        allergen13.setLabel(allergenNames.get(12));
        allergen13.setValue(false);
        allergen14.setLabel(allergenNames.get(13));
        allergen14.setValue(false);
    }

    private List<String> SelectedCheckboxes() {
        List<String> values = new ArrayList<>();
        if(allergen1.getValue())
            values.add(allergen1.getLabel());
        if(allergen2.getValue())
            values.add(allergen2.getLabel());
        if(allergen2.getValue())
            values.add(allergen2.getLabel());
        if(allergen3.getValue())
            values.add(allergen3.getLabel());
        if(allergen4.getValue())
            values.add(allergen4.getLabel());
        if(allergen5.getValue())
            values.add(allergen5.getLabel());
        if(allergen6.getValue())
            values.add(allergen6.getLabel());
        if(allergen7.getValue())
            values.add(allergen7.getLabel());
        if(allergen8.getValue())
            values.add(allergen8.getLabel());
        if(allergen9.getValue())
            values.add(allergen9.getLabel());
        if(allergen10.getValue())
            values.add(allergen10.getLabel());
        if(allergen11.getValue())
            values.add(allergen11.getLabel());
        if(allergen12.getValue())
            values.add(allergen12.getLabel());
        if(allergen13.getValue())
            values.add(allergen13.getLabel());
        if(allergen14.getValue())
            values.add(allergen14.getLabel());
        return values;
    }

    private void SetCheckboxes(List<String> in){
        if(in.contains(allergen1.getLabel()))
            allergen1.setValue(true);
        if(in.contains(allergen2.getLabel()))
            allergen2.setValue(true);
        if(in.contains(allergen3.getLabel()))
            allergen3.setValue(true);
        if(in.contains(allergen4.getLabel()))
            allergen4.setValue(true);
        if(in.contains(allergen5.getLabel()))
            allergen5.setValue(true);
        if(in.contains(allergen6.getLabel()))
            allergen6.setValue(true);
        if(in.contains(allergen7.getLabel()))
            allergen7.setValue(true);
        if(in.contains(allergen8.getLabel()))
            allergen8.setValue(true);
        if(in.contains(allergen9.getLabel()))
            allergen9.setValue(true);
        if(in.contains(allergen10.getLabel()))
            allergen10.setValue(true);
        if(in.contains(allergen11.getLabel()))
            allergen11.setValue(true);
        if(in.contains(allergen12.getLabel()))
            allergen12.setValue(true);
        if(in.contains(allergen13.getLabel()))
            allergen13.setValue(true);
        if(in.contains(allergen14.getLabel()))
            allergen14.setValue(true);
    }

    private void setUpEditDialog( MenuItemRepresentation update)
    {
        editDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        HorizontalLayout firstLayer = new HorizontalLayout();
        HorizontalLayout secondLayer = new HorizontalLayout();
        HorizontalLayout allergens1 = new HorizontalLayout();
        HorizontalLayout allergens2 = new HorizontalLayout();


        setUpCheckboxes(update.getIdLanguage());

        allergens1.add(allergen1, allergen2, allergen3, allergen4, allergen5, allergen6, allergen7);
        allergens2.add(allergen8, allergen9, allergen10, allergen11, allergen12, allergen13 ,allergen14);


        List<String> category = menuItemController.getCategories();
        List<String> unit = menuItemController.getUnits();

        Label headLabel = new Label("Add");
        TextField name = new TextField("Name:");
        name.setValue(update.getName());
        TextField description = new TextField("Description:");
        description.setValue(update.getDescription());
        TextField amount = new TextField("Amount:");
        amount.setValue(update.getAmount().toString());
        TextField urlImage = new TextField("Image url:");
        urlImage.setValue(update.getImageAddress());
        ComboBox<String> units = new ComboBox<>("Units:");
        units.setItems(unit);
        units.setValue(update.getUnitDescription());
        ComboBox<String> categories = new ComboBox<>("Categories:");
        categories.setItems(category);
        categories.setValue(update.getCategoryDescription());
        TextField price = new TextField("Price:");
        price.setValue(update.getPrice().toString());
        Label infoLabel = new Label();

        SetCheckboxes(update.getAlergens());

        Button updateButton = new Button("Update");
        Button close = new Button("Close");

        updateButton.addClickListener(buttonClickEvent -> {
            if(description.isEmpty() || name.isEmpty() || amount.isEmpty() || price.isEmpty() || urlImage.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(amount.getValue()) || !CorrectnessController.OnlyNumbers(price.getValue())){
                infoLabel.setText("Amount and price must by numbers only");
                return;
            }

            update.setDescription(name.getValue());
            update.setImageAddress(urlImage.getValue());
            update.setAmount(new BigDecimal(amount.getValue()));
            update.setCategoryDescription(categories.getValue());
            update.setDescription(description.getValue());
            update.setName(name.getValue());
            update.setPrice(Integer.parseInt(price.getValue()));
            update.setUnitDescription(units.getValue());
            update.setAlergens(SelectedCheckboxes());
            menuItemController.Update(update);
            RefreshValues();
            infoLabel.setText("Updated");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(updateButton, close);

        firstLayer.add(name, description, urlImage);
        secondLayer.add(amount, units, categories, price);

        content.add(headLabel,firstLayer, secondLayer,allergens1,allergens2,buttons,infoLabel);

        editDialog.add(content);
    }

    private void setUpAddDialog()
    {
        editDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        HorizontalLayout firstLayer = new HorizontalLayout();
        HorizontalLayout secondLayer = new HorizontalLayout();
        HorizontalLayout allergens1 = new HorizontalLayout();
        HorizontalLayout allergens2 = new HorizontalLayout();

        List<MenuItemRepresentation> menuItemRepresentations = new ArrayList<>();

        List<LanguagesRecord> languagesRecords = languagesService.getConfigs();

        List<String> languageNames = new ArrayList<>();
        for(LanguagesRecord rec : languagesRecords){
            languageNames.add(rec.getName());
        }

        ComboBox<String> language = new ComboBox<>();
        language.setItems(languageNames);
        language.setValue(languageNames.get(0));

        int langId = languagesService.GetIdByName(language.getValue());
        setUpCheckboxes(langId);

        allergens1.add(allergen1, allergen2, allergen3, allergen4, allergen5, allergen6, allergen7);
        allergens2.add(allergen8, allergen9, allergen10, allergen11, allergen12, allergen13 ,allergen14);


        List<String> category = menuItemController.getCategories();
        List<String> unit = menuItemController.getUnits();

        Label headLabel = new Label("Add");
        TextField name = new TextField("Name:");
        TextField description = new TextField("Description:");
        TextField amount = new TextField("Amount:");
        TextField urlImage = new TextField("Image url:");
        ComboBox<String> units = new ComboBox<>("Units:");
        units.setItems(unit);
        ComboBox<String> categories = new ComboBox<>("Categories:");
        categories.setItems(category);
        TextField price = new TextField("Price:");
        Label infoLabel = new Label();

        Button add = new Button("Add");
        Button close = new Button("Close");

        language.addValueChangeListener(valueChangeEvent -> {
            if(languageNames.stream().filter(nameLang -> nameLang.equals(language.getValue())).count() == 0){
                language.setErrorMessage("Select existing language");
                language.setInvalid(true);
                return;
            }
            if(description.isEmpty() || name.isEmpty() || amount.isEmpty() || price.isEmpty() || urlImage.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(amount.getValue()) || !CorrectnessController.OnlyNumbers(price.getValue())){
                infoLabel.setText("Amount and price must by numbers only");
                return;
            }
            int languageId = languagesService.GetIdByName(valueChangeEvent.getOldValue());
            if(menuItemRepresentations.stream().filter(menu -> menu.getIdLanguage().equals(languageId)).count() == 0){
                MenuItemRepresentation tmp = new MenuItemRepresentation();
                tmp.setIdLanguage(languageId);
                tmp.setDescription(name.getValue());
                tmp.setImageAddress(urlImage.getValue());
                tmp.setAmount(new BigDecimal(amount.getValue()));
                tmp.setCategoryDescription(categories.getValue());
                tmp.setDescription(description.getValue());
                tmp.setName(name.getValue());
                tmp.setPrice(Integer.parseInt(price.getValue()));
                tmp.setUnitDescription(units.getValue());
                tmp.setAlergens(SelectedCheckboxes());
                tmp.setIdMenu(menuItemController.getMenuID());
                menuItemRepresentations.add(tmp);
                int languageIdea = languagesService.GetIdByName(valueChangeEvent.getValue());
                setUpCheckboxes(languageIdea);
                SetCheckboxes(menuItemController.getAlergensForItem(menuItemRepresentations.get(0).getAlergens(),languageIdea));
            }
        });

        add.addClickListener(buttonClickEvent -> {
            if(description.isEmpty() || name.isEmpty() || amount.isEmpty() || price.isEmpty() || urlImage.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(amount.getValue()) || !CorrectnessController.OnlyNumbers(price.getValue())){
                infoLabel.setText("Amount and price must by numbers only");
                return;
            }
            if(menuItemRepresentations.size() < languageNames.size() - 1){
                infoLabel.setText("Fill all languages Size");
                return;
            }
            int languageId = languagesService.GetIdByName(language.getValue());
            MenuItemRepresentation tmp = new MenuItemRepresentation();
            tmp.setIdLanguage(languageId);
            tmp.setDescription(name.getValue());
            tmp.setImageAddress(urlImage.getValue());
            tmp.setAmount(new BigDecimal(amount.getValue()));
            tmp.setCategoryDescription(categories.getValue());
            tmp.setDescription(description.getValue());
            tmp.setName(name.getValue());
            tmp.setPrice(Integer.parseInt(price.getValue()));
            tmp.setUnitDescription(units.getValue());
            tmp.setAlergens(SelectedCheckboxes());
            tmp.setIdMenu(menuItemController.getMenuID());
            if(menuItemRepresentations.stream().filter(menuItem -> menuItem.getIdLanguage().equals(languageId)).count() != 0){
                if(menuItemRepresentations.size() == languageNames.size()){
                    menuItemController.InsertMultiLingual(menuItemRepresentations);
                    RefreshValues();
                    infoLabel.setText("Added");
                    return;
                }
            } else {
                if(menuItemRepresentations.size() + 1 == languageNames.size()){
                    menuItemRepresentations.add(tmp);
                    menuItemController.InsertMultiLingual(menuItemRepresentations);
                    RefreshValues();
                    infoLabel.setText("Added");
                    return;
                }

            }
            infoLabel.setText("Fill all languages");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(add, close);

        firstLayer.add(language, name, description, urlImage);
        secondLayer.add(amount, units, categories, price);

        content.add(headLabel,firstLayer, secondLayer,allergens1,allergens2,buttons,infoLabel);

        editDialog.add(content);
    }
}
