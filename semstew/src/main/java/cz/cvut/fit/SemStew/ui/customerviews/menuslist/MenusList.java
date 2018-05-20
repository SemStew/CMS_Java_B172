package cz.cvut.fit.SemStew.ui.customerviews.menuslist;

import JOOQ.tables.records.MenusConfigRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Controllers.MenuController;
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemController;
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemRepresentation;
import cz.cvut.fit.SemStew.backend.Controllers.MenuRepresentation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.MenusConfigService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.util.Comparator;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "menus", layout = CustomerLayout.class)
@PageTitle("Menu | Home")
public class MenusList extends VerticalLayout {
    /**
     * Page header
     */
    private final H2 header = new H2();
    /**
     * Page grid to display menus
     */
    private final Grid<MenuRepresentation> gridMenu = new Grid<>();
    /**
     * Menu management
     */
    private final MenuController menusController = new MenuController();
    /**
     * Page management
     */
    private final MenusConfigService menusConfigService = new MenusConfigService();
    /**
     * Language management
     */
    private final LanguagesService languagesService = new LanguagesService();
    /**
     * List of all available menus
     */
    private List<MenuRepresentation> menus;

    /**
     * MenusList constructor
     *
     * Use {@link #MenusList()} to create and initialize page
     *
     */
    public MenusList()
    {
        init();
        addContent();
    }

    /**
     * Initialize page
     *
     * Use {@link #init()} to initialize page
     *
     */
    private void init()
    {
        setClassName("menu");
        getElement().setAttribute("itemtype", "http://schema.org/Menu");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Load content
     *
     * Use {@link #addContent()} to load and set up content of the page
     *
     */
    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        MenusConfigRecord menusConfigRecord = menusConfigService.GetInstanceByLanguage(id);

        header.setText(menusConfigRecord.getHeader());
        content.getElement().setAttribute("name", menusConfigRecord.getHeader());
        menus = menusController.getItems();
        gridMenu.setItems(menus);
        gridMenu.addColumn(new ComponentRenderer<>(menuRepresentation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuRepresentation.getImageAddress());
            tmp.getElement().setAttribute("image", menuRepresentation.getImageAddress());
            tmp.getElement().setAttribute("description", menuRepresentation.getDescription());
            return tmp;
        }));
        gridMenu.addColumn(MenuRepresentation::getDescription).setComparator(Comparator.comparing(MenuRepresentation::getDescription)).setHeader("Menu name").setSortable(true);
        gridMenu.addColumn(new ComponentRenderer<>(clickedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.AREA_SELECT));
            tmp.addClickListener(buttonClickEvent -> {
                VaadinService.getCurrentRequest().getWrappedSession().setAttribute("menu_id",clickedItem.getIdMenu().toString());
                this.getUI().ifPresent(ui-> ui.navigate("menus/selected"));
            });
            return tmp;
        }));

        content.add(header,gridMenu);

        add(content);
    }
}
