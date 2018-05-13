package cz.cvut.fit.SemStew.ui.customerviews.menuslist;

import JOOQ.tables.records.MenusConfigRecord;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.MenuController;
import cz.cvut.fit.SemStew.backend.MenuRepresantation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.MenusConfigService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.util.Comparator;
import java.util.List;

@Route(value = "menus", layout = CustomerLayout.class)
@PageTitle("Menu | Home")
public class MenusList extends VerticalLayout {
    private final H2 header = new H2();
    private final Grid<MenuRepresantation> gridMenu = new Grid<>();
    private final MenuController menusController = new MenuController();
    private final MenusConfigService menusConfigService = new MenusConfigService();
    private final LanguagesService languagesService = new LanguagesService();
    private List<MenuRepresantation> menus;

    public MenusList()
    {
        init();
        addContent();
    }

    private void init()
    {
        setClassName("menu");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

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

        menus = menusController.getItems();

        gridMenu.setHeightByRows(true);
        gridMenu.setItems(menus);
        gridMenu.addColumn(new ComponentRenderer<>(menuRepresantation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuRepresantation.getImageAddress());
            return tmp;
        }));
        gridMenu.addColumn(MenuRepresantation::getDescription).setComparator(Comparator.comparing(MenuRepresantation::getDescription)).setHeader("Menu name").setSortable(true);
        gridMenu.addColumn(new NativeButtonRenderer<MenuRepresantation>("Select", clickedItem ->{
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("menu_id",clickedItem.getIdMenu().toString());
            this.getUI().ifPresent(ui-> ui.navigate("menus/selected"));
        }));

        content.add(header,gridMenu);

        add(content);
    }
}
