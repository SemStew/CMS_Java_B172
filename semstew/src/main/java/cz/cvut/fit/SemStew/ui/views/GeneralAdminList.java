package cz.cvut.fit.SemStew.ui.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.ui.Login;
import cz.cvut.fit.SemStew.ui.views.appearancelist.AppearanceList;
import cz.cvut.fit.SemStew.ui.views.articlelist.ArticleList;
import cz.cvut.fit.SemStew.ui.views.branchlist.BranchList;
import cz.cvut.fit.SemStew.ui.views.gallerylist.GalleryList;
import cz.cvut.fit.SemStew.ui.views.menulist.MenuList;
import cz.cvut.fit.SemStew.ui.views.orderslist.OrdersList;
import cz.cvut.fit.SemStew.ui.views.previewlist.PreviewList;
import cz.cvut.fit.SemStew.ui.views.reservationslist.ReservationsList;
import cz.cvut.fit.SemStew.ui.views.settingslist.SettingsList;
import cz.cvut.fit.SemStew.ui.views.statisticslist.StatisticsList;

public class GeneralAdminList extends VerticalLayout
        implements AfterNavigationObserver, BeforeEnterObserver {
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
    private final Button logout = new Button(new Icon(VaadinIcons.EXIT));

    private HorizontalLayout bottom;

    public GeneralAdminList(){
        addMenu();
        addFoot();
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

        logout.addClassName("nav-item");
        logout.addClickListener(buttonClickEvent -> {
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("logged_in","logout");
            logout.getUI().ifPresent(ui-> ui.navigate("login"));
        });


        navigation.add(branch, preview, dishes, articles, gallery, orders, reservations, statistics, appearance, settings, logout);

        add(navigation);
    }

    private void addFoot () {
        bottom = new HorizontalLayout();
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

        remove(bottom);

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

        add(bottom);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event)
    {
        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("logged_in") == null) {
            event.rerouteTo(Login.class);
        } else {
            String res = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("logged_in").toString();
            if (!res.equals("logged")) {
                event.rerouteTo(Login.class);
            }
        }
    }
}
