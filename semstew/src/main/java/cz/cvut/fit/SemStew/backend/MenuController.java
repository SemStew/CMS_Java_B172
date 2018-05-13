package cz.cvut.fit.SemStew.backend;

import JOOQ.tables.Menus;
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

    private List<MenuRepresantation> LoadData()
    {
        List<MenuRepresantation> items = new ArrayList<>();

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
                items.add(new MenuRepresantation());
                items.get(items.size()-1).Load(rec,menusNameRecord);
            }
        }

        return items;
    }

    public void Insert(MenuRepresantation insert)
    {
        MenusRecord menusRecord = insert.GetMenus();
        MenusNameRecord menusNameRecord = insert.GetMenusName();

        menusService.InsertMenusService(menusRecord);
        menusRecord = menusService.GetByCombination(menusRecord.getIdBranch(),menusRecord.getUrlImage());
        menusNameRecord.setIdMenu(menusRecord.getIdMenu());
        menusNameService.InsertMenusNameService(menusNameRecord);
    }

    public void InsertMultiLingual(List<MenuRepresantation> insert)
    {
        MenusRecord menusRecord = insert.get(0).GetMenus();
        menusService.InsertMenusService(menusRecord);
        menusRecord = menusService.GetByCombination(menusRecord.getIdBranch(),menusRecord.getUrlImage());

        for(MenuRepresantation rep : insert){
            MenusNameRecord tmp = rep.GetMenusName();
            tmp.setIdMenu(menusRecord.getIdMenu());
            menusNameService.InsertMenusNameService(tmp);
        }
    }

    public void Update(MenuRepresantation update)
    {
        MenusRecord menusRecord = update.GetMenus();
        MenusNameRecord menusNameRecord = update.GetMenusName();

        menusService.UpdateMenusService(menusRecord);
        menusNameService.UpdateMenusNameService(menusNameRecord);
    }

    public void Delete(MenuRepresantation delete)
    {
        MenusRecord menusRecord = delete.GetMenus();

        menuItemController.DeleteByMenuId(menusRecord.getIdMenu());
        menusNameService.DeleteByMenuId(menusRecord.getIdMenu());
        menusService.DeleteMenusService(menusRecord);

    }

    public List<MenuRepresantation> getItems() {
        return LoadData();
    }
}
