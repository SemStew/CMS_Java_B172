package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.MenusNameRecord;
import JOOQ.tables.records.MenusRecord;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.MenusNameService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.MenusService;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private final MenusService menusService = new MenusService();
    private final MenusNameService menusNameService = new MenusNameService();
    private final LanguagesService languagesService = new LanguagesService();
    private final MenuItemController menuItemController = new MenuItemController();

    public MenuController(){}

    private List<MenuRepresentation> LoadData()
    {
        List<MenuRepresentation> items = new ArrayList<>();

        MenusNameRecord menusNameRecord;

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        List<MenusRecord> menusRecords = menusService.getConfigs();

        for(MenusRecord rec : menusRecords)
        {
            menusNameRecord = menusNameService.GetById(rec.getIdMenu(),id);

            if(menusNameRecord != null){
                items.add(new MenuRepresentation());
                items.get(items.size()-1).Load(rec,menusNameRecord);
            }
        }

        return items;
    }

    public void Insert(MenuRepresentation insert)
    {
        MenusRecord menusRecord = insert.GetMenus();
        MenusNameRecord menusNameRecord = insert.GetMenusName();

        int menuId = menusService.InsertAndReturn(menusRecord);
        menusNameRecord.setIdMenu(menuId);
        menusNameService.InsertMenusNameService(menusNameRecord);
    }

    public void InsertMultiLingual(List<MenuRepresentation> insert)
    {
        MenusRecord menusRecord = insert.get(0).GetMenus();
        int menuId = menusService.InsertAndReturn(menusRecord);

        for(MenuRepresentation rep : insert){
            MenusNameRecord tmp = rep.GetMenusName();
            tmp.setIdMenu(menuId);
            menusNameService.InsertMenusNameService(tmp);
        }
    }

    public void Update(MenuRepresentation update)
    {
        MenusRecord menusRecord = update.GetMenus();
        MenusNameRecord menusNameRecord = update.GetMenusName();

        menusService.UpdateMenusService(menusRecord);
        menusNameService.UpdateMenusNameService(menusNameRecord);
    }

    public void Delete(MenuRepresentation delete)
    {
        MenusRecord menusRecord = delete.GetMenus();

        menuItemController.DeleteByMenuId(menusRecord.getIdMenu());
        menusNameService.DeleteByMenuId(menusRecord.getIdMenu());
        menusService.DeleteMenusService(menusRecord);

    }

    public List<MenuRepresentation> getItems() {
        return LoadData();
    }
}
