package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.MenusNameRecord;
import JOOQ.tables.records.MenusRecord;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.MenusNameService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.MenusService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class MenuController {
    /**
     * Menu management
     */
    private final MenusService menusService = new MenusService();
    /**
     *  MenuName management
     */
    private final MenusNameService menusNameService = new MenusNameService();
    /**
     *  Language management
     */
    private final LanguagesService languagesService = new LanguagesService();
    /**
     *  MenuItem management
     */
    private final MenuItemController menuItemController = new MenuItemController();

    /**
     *  MenuController constructor
     */
    public MenuController(){}

    /**
     * Loads MenuRepresentations in selected language
     *
     * Use {@link #LoadData()} to load MenuRepresentations in selected language from database
     *
     * @return list of MenuRepresentations
     */
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

    /**
     * Inserts MenuRepresentation in single language
     *
     * Use {@link #Insert(MenuRepresentation insert)} to insert given MenuRepresentations in single language
     *
     * @param insert MenuRepresentations to be inserted
     */
    public void Insert(MenuRepresentation insert)
    {
        MenusRecord menusRecord = insert.GetMenus();
        MenusNameRecord menusNameRecord = insert.GetMenusName();

        int menuId = menusService.InsertAndReturn(menusRecord);
        menusNameRecord.setIdMenu(menuId);
        menusNameService.InsertMenusNameService(menusNameRecord);
    }

    /**
     * Insert MenuRepresentation in multiple languages
     *
     * Use {@link #InsertMultiLingual(List insert)} to insert given MenuRepresentation in multiple languages
     *
     * @param insert list of MenuRepresentation in various languages
     */
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

    /**
     * Update MenuRepresentation
     *
     * Use {@link #Update(MenuRepresentation update)} to update given MenuRepresentation
     *
     * @param update MenuRepresentation to be updated
     */
    public void Update(MenuRepresentation update)
    {
        MenusRecord menusRecord = update.GetMenus();
        MenusNameRecord menusNameRecord = update.GetMenusName();

        menusService.UpdateMenusService(menusRecord);
        menusNameService.UpdateMenusNameService(menusNameRecord);
    }

    /**
     * Delete MenuRepresentation
     *
     * Use {@link #Delete(MenuRepresentation delete)} to delete given MenuRepresentation
     *
     * @param delete MenuRepresentation to be deleted
     */
    public void Delete(MenuRepresentation delete)
    {
        MenusRecord menusRecord = delete.GetMenus();

        menuItemController.DeleteByMenuId(menusRecord.getIdMenu());
        menusNameService.DeleteByMenuId(menusRecord.getIdMenu());
        menusService.DeleteMenusService(menusRecord);

    }

    /**
     * Get MenuRepresentations in selected language
     *
     * Use {@link #getItems()} to get MenuRepresentations in selected language from database
     *
     * @return list of MenuRepresentations
     */
    public List<MenuRepresentation> getItems() {
        return LoadData();
    }
}
