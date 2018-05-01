package com.vaadin.starter.SemStew.ui.views.menulist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.IntroConfig;
import com.vaadin.starter.SemStew.backend.MenuItemControler;
import com.vaadin.starter.SemStew.backend.MenuItemRepresantation;
import com.vaadin.starter.SemStew.backend.Services.MenuItemService;
import com.vaadin.starter.SemStew.ui.Login;
import com.vaadin.starter.SemStew.ui.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.starter.SemStew.ui.views.appearancelist.AppearanceList;
import com.vaadin.starter.SemStew.ui.views.articlelist.ArticleList;
import com.vaadin.starter.SemStew.ui.views.gallerylist.GalleryList;
import com.vaadin.starter.SemStew.ui.views.branchlist.BranchList;
import com.vaadin.starter.SemStew.ui.views.menulist.MenuList;
import com.vaadin.starter.SemStew.ui.views.previewlist.PreviewList;
import com.vaadin.starter.SemStew.ui.views.reservationslist.ReservationsList;
import com.vaadin.starter.SemStew.ui.views.settingslist.SettingsList;
import com.vaadin.starter.SemStew.ui.views.statisticslist.StatisticsList;
import com.vaadin.starter.SemStew.ui.views.orderslist.OrdersList;
import com.vaadin.starter.SemStew.ui.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Route(value = "admin/dishes", layout = MainLayout.class)
@PageTitle("Dishes List | Admin")
public class MenuList extends VerticalLayout
        implements AfterNavigationObserver {

    private static final String ACTIVE_ITEM_STYLE = "main-layout__nav-item--selected";
    private RouterLink branch;
    private RouterLink preview;
    private RouterLink dishes;
    private RouterLink articles;
    private RouterLink gallery;
    private RouterLink reservations;
    private RouterLink orders;
    private RouterLink statistics;
    private RouterLink appearance;
    private RouterLink settings;
    private RouterLink logout;

    private final H2 header = new H2();
    private final Grid<MenuItemRepresantation> menuItemGrid = new Grid<>();
    private MenuItemControler menuItemControler = new MenuItemControler();
    private List<MenuItemRepresantation> menuItemRecords;
    private final Button editButton = new Button();
    private final Dialog editDialog = new Dialog();

    public MenuList() {
        init();
        addContent();
        addMenu();
        addFoot();
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

    private void addMenu() {
        H2 title = new H2("SemStew CMS");
        title.addClassName("title");

        Div navigation = new Div(title);
        navigation.addClassName("nav");

        branch = new RouterLink(null,BranchList.class);
        branch.add(new Icon(VaadinIcons.HOME), new Text("Branch"));
        branch.addClassName("nav-item");

        preview = new RouterLink(null, PreviewList.class);
        preview.add(new Icon(VaadinIcons.BROWSER), new Text("Preview"));
        preview.addClassName("nav-item");

        dishes = new RouterLink(null, MenuList.class);
        dishes.add(new Icon(VaadinIcons.CROSS_CUTLERY), new Text("Dishes"));
        dishes.addClassName("nav-item");

        articles = new RouterLink(null, ArticleList.class);
        articles.add(new Icon(VaadinIcons.PAPERCLIP), new Text("Articles"));
        articles.addClassName("nav-item");

        gallery = new RouterLink(null, GalleryList.class);
        gallery.add(new Icon(VaadinIcons.PICTURE), new Text("Gallery"));
        gallery.addClassName("nav-item");

        reservations = new RouterLink(null, ReservationsList.class);
        reservations.add(new Icon(VaadinIcons.NOTEBOOK), new Text("Reservations"));
        reservations.addClassName("nav-item");

        orders = new RouterLink(null, OrdersList.class);
        orders.add(new Icon(VaadinIcons.ALARM), new Text("Orders"));
        orders.addClassName("nav-item");

        statistics = new RouterLink(null, StatisticsList.class);
        statistics.add(new Icon(VaadinIcons.SPLINE_AREA_CHART), new Text("Statistics"));
        statistics.addClassName("nav-item");

        appearance = new RouterLink(null, AppearanceList.class);
        appearance.add(new Icon(VaadinIcons.VIEWPORT), new Text("Appearance"));
        appearance.addClassName("nav-item");

        settings = new RouterLink(null, SettingsList.class);
        settings.add(new Icon(VaadinIcons.TOOLS), new Text("Settings"));
        settings.addClassName("nav-item");

        logout = new RouterLink(null, Login.class);
        logout.add(new Icon(VaadinIcons.CIRCLE), new Text("Log out"));
        logout.addClassName("nav-item");

        navigation.add(branch, preview, dishes, articles, gallery, orders,reservations, statistics, appearance, settings, logout);

        add(navigation);
    }

    private void addFoot () {
        HorizontalLayout bottom = new HorizontalLayout();
        bottom.addClassName("main-layout__bottom");
        Div foot = new Div ();
        foot.addClassName("foot");

        Text foot__text = new Text("Design and developed by DreamTeam © SemStew CMS");
        foot.add(foot__text);
        bottom.add(foot);

        add(bottom);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        // updating the active menu item based on if either of views is active
        // (note that this is triggered even for the error view)

        String segment = event.getLocation().getFirstSegment();
        boolean branchActive = segment.equals(branch.getHref());
        boolean previewActive = segment.equals(preview.getHref());
        boolean dishesActive = segment.equals(dishes.getHref());
        boolean articlesActive = segment.equals(articles.getHref());
        boolean galleryActive = segment.equals(gallery.getHref());
        boolean reservationsActive = segment.equals(reservations.getHref());
        boolean ordersActive = segment.equals(orders.getHref());
        boolean statisticsActive = segment.equals(statistics.getHref());
        boolean appearanceActive = segment.equals(appearance.getHref());
        boolean settingsActive = segment.equals(settings.getHref());
        boolean logoutActive = segment.equals(logout.getHref());

        branch.setClassName(ACTIVE_ITEM_STYLE, branchActive);
        preview.setClassName(ACTIVE_ITEM_STYLE, previewActive);
        dishes.setClassName(ACTIVE_ITEM_STYLE, dishesActive);
        articles.setClassName(ACTIVE_ITEM_STYLE, articlesActive);
        gallery.setClassName(ACTIVE_ITEM_STYLE, galleryActive);
        reservations.setClassName(ACTIVE_ITEM_STYLE, reservationsActive);
        orders.setClassName(ACTIVE_ITEM_STYLE, ordersActive);
        statistics.setClassName(ACTIVE_ITEM_STYLE, statisticsActive);
        appearance.setClassName(ACTIVE_ITEM_STYLE, appearanceActive);
        settings.setClassName(ACTIVE_ITEM_STYLE, settingsActive);
        settings.setClassName(ACTIVE_ITEM_STYLE, logoutActive);
    }
}