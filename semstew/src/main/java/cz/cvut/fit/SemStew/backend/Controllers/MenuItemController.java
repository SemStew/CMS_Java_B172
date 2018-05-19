package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.*;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.AboutUsConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class MenuItemController {
    /**
     *  MenuItem management
     */
    private final MenuItemService menuItemService = new MenuItemService();
    /**
     * MenuItemName management
     */
    private final MenuItemNameService menuItemNameService = new MenuItemNameService();
    /**
     * CategoriesName management
     */
    private final CategoriesNameService categoriesNameService = new CategoriesNameService();
    /**
     * Units management
     */
    private final AboutUsConfigService.UnitsService unitsService = new AboutUsConfigService.UnitsService();
    /**
     * MenuItemAllergen management
     */
    private final MenuItemAllergenService menuItemAllergenService = new MenuItemAllergenService();
    /**
     * Allergens management
     */
    private final AllergensNameService allergensNameService = new AllergensNameService();
    /**
     * Languages management
     */
    private final LanguagesService languagesService = new LanguagesService();
    /**
     * Menu ID number
     */
    private int menuID = 0;
    /**
     * Selected Language number
     */
    private int languageId;

    /**
     * MenuItemController constructor
     */
    public MenuItemController() {}

    /**
     * Get MenuItemRepresentations in selected language
     *
     * Use {@link #LoadData()} to get MenuItemRepresentations in selected language from database
     *
     * @return list of all MenuItemRepresentations in given language
     */
    private List<MenuItemRepresentation> LoadData()
    {
        List<MenuItemRepresentation> items = new ArrayList<>();
        MenuItemNameRecord menuItemNameRecord;
        CategoriesNameRecord categoriesNameRecord;
        UnitsRecord unitsRecord;

        String name = "English";
        String menuIdString = "0";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("menu_id") != null){
            menuIdString = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("menu_id").toString();
        }

        menuID = Integer.parseInt(menuIdString);

        languageId = languagesService.GetIdByName(name);

        List<MenuItemRecord> menuItemRecords = menuItemService.GetByMenuId(menuID);

        for(MenuItemRecord menuItemRec : menuItemRecords)
        {
            menuItemNameRecord = menuItemNameService.ItemNameById(menuItemRec.getIdMenuItem(),languageId);

            categoriesNameRecord = categoriesNameService.CategoriesNameById(menuItemRec.getIdCategory(),languageId);

            unitsRecord = unitsService.UnitById(menuItemRec.getIdUnit());

            List<MenuItemAllergenRecord> menuItemAllergenRecords = menuItemAllergenService.GetByMenuItemId(menuItemRec.getIdMenuItem());

            List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();
            if(menuItemAllergenRecords.size()!=0) {
                for (MenuItemAllergenRecord rec : menuItemAllergenRecords) {
                    allergensNameRecords.add(allergensNameService.GetById(rec.getIdAllergen(), languageId));
                }
            }

            if(menuItemNameRecord != null && categoriesNameRecord != null && unitsRecord != null) {
                items.add(new MenuItemRepresentation());
                items.get(items.size()-1).Load(menuItemNameRecord, menuItemRec, categoriesNameRecord, unitsRecord,allergensNameRecords);
            }
        }

        return items;
    }

    /**
     * Get all MenuItemRepresentationsin selected language and id
     *
     * Use {@link #LoadById(int id)} to get all MenuItemRepresentation in selected language and given id from database
     *
     * @param id ID of searched MenuItemRepresentation
     * @return MenuItemRepresentation with given id
     */
    public MenuItemRepresentation LoadById(int id){
        MenuItemNameRecord menuItemNameRecord;
        CategoriesNameRecord categoriesNameRecord;
        UnitsRecord unitsRecord;

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        languageId = languagesService.GetIdByName(name);

        MenuItemRecord menuItemRecord = menuItemService.GetByMenuItemId(id);

        menuItemNameRecord = menuItemNameService.ItemNameById(menuItemRecord.getIdMenuItem(),languageId);

        categoriesNameRecord = categoriesNameService.CategoriesNameById(menuItemRecord.getIdCategory(),languageId);

        unitsRecord = unitsService.UnitById(menuItemRecord.getIdUnit());

        List<MenuItemAllergenRecord> menuItemAllergenRecords = menuItemAllergenService.GetByMenuItemId(menuItemRecord.getIdMenuItem());

        List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();
        if(menuItemAllergenRecords.size()!=0) {
            for (MenuItemAllergenRecord rec : menuItemAllergenRecords) {
                allergensNameRecords.add(allergensNameService.GetById(rec.getIdAllergen(), languageId));
            }
        }

        MenuItemRepresentation representation = new MenuItemRepresentation();
        representation.Load(menuItemNameRecord,menuItemRecord,categoriesNameRecord,unitsRecord,allergensNameRecords);

        return representation;
    }

    /**
     * Insert MenuItemRepresentation in single language
     *
     * Use {@link #Insert(MenuItemRepresentation in)} to insert given MenuItemRepresentation
     *
     * @param in MenuItemRepresentation to be inserted
     */
    public void Insert(MenuItemRepresentation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        List<String> allergens = in.getAllergens();
        List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();

        for(String rec : allergens){
            allergensNameRecords.add(allergensNameService.GetByDescription(rec));
        }

        int menuItemId = menuItemService.InsertAndReturn(item);
        itemName.setIdMenuItem(menuItemId);
        menuItemNameService.InsertMenuItemNameService(itemName);

        for(AllergensNameRecord rec : allergensNameRecords){
            MenuItemAllergenRecord tmp = new MenuItemAllergenRecord();
            tmp.setIdAllergen(rec.getIdAllergen());
            tmp.setIdMenuItem(menuItemId);
            menuItemAllergenService.InsertMenuItemAllergenService(tmp);
        }
    }

    /**
     * Insert MenuItemRepresentation in multiple languages
     *
     * Use {@link #InsertMultiLingual(List insert)} to insert given MenuItemRepresentation in multiple languages
     *
     * @param insert list of MenuItemRepresentations to be inserted
     */
    public void InsertMultiLingual(List<MenuItemRepresentation> insert){
        MenuItemRecord item = insert.get(0).GetItemRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(insert.get(0).getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(insert.get(0).getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        List<String> allergens = insert.get(0).getAllergens();
        List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();

        for(String rec : allergens){
            allergensNameRecords.add(allergensNameService.GetByDescription(rec));
        }

        int menuItemId = menuItemService.InsertAndReturn(item);

        for(AllergensNameRecord rec : allergensNameRecords){
            MenuItemAllergenRecord tmp = new MenuItemAllergenRecord();
            tmp.setIdAllergen(rec.getIdAllergen());
            tmp.setIdMenuItem(menuItemId);
            menuItemAllergenService.InsertMenuItemAllergenService(tmp);
        }

        for(MenuItemRepresentation rep : insert){
            MenuItemNameRecord tmp = rep.GetItemNameRecord();

            tmp.setIdMenuItem(menuItemId);
            menuItemNameService.InsertMenuItemNameService(tmp);
        }
    }

    /**
     * Update MenuItemRepresentation
     *
     * Use {@link #Update(MenuItemRepresentation in)} to update given MenuItemRepresentation
     *
     * @param in MenuItemRepresentation to be updated
     */
    public void Update(MenuItemRepresentation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        List<String> allergens = in.getAllergens();
        List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();

        for(String rec : allergens){
            allergensNameRecords.add(allergensNameService.GetByDescription(rec));
        }

        menuItemAllergenService.DeleteByMenuItemId(in.getIdMenuItem());

        for(AllergensNameRecord rec : allergensNameRecords){
            MenuItemAllergenRecord tmp = new MenuItemAllergenRecord();
            tmp.setIdAllergen(rec.getIdAllergen());
            tmp.setIdMenuItem(in.getIdMenuItem());
            menuItemAllergenService.InsertMenuItemAllergenService(tmp);
        }


        menuItemService.UpdateMenuItemService(item);
        menuItemNameService.UpdateMenuItemNameService(itemName);
    }

    /**
     * Delete MenuItemRepresentation
     *
     * Use {@link #Delete(MenuItemRepresentation in)} to delete given MenuItemRepresentation
     *
     * @param in MenuItemRepresentation to be deleted
     */
    public void Delete(MenuItemRepresentation in)
    {
        MenuItemRecord item = in.GetItemRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        menuItemAllergenService.DeleteByMenuItemId(in.getIdMenuItem());

        menuItemNameService.DeleteByMenuItemId(item.getIdMenuItem());
        menuItemService.DeleteMenuItemService(item);
    }

    /**
     * Delete MenuItemRecord
     *
     * Use {@link #Delete(MenuItemRecord in)} to delete MenuItemRecord
     *
     * @param in MenuItemRecord to be deleted
     */
    private void Delete(MenuItemRecord in){

        menuItemAllergenService.DeleteByMenuItemId(in.getIdMenuItem());

        menuItemNameService.DeleteByMenuItemId(in.getIdMenuItem());
        menuItemService.DeleteMenuItemService(in);
    }

    /**
     * Delete MenuItemRepresentations by Menu ID
     *
     * Use {@link #DeleteByMenuId(int menuID)} to delete MenuItemRepresentations of given Menu ID
     *
     * @param menuID Menu ID for MenuItemRepresentations to be deleted
     */
    public void DeleteByMenuId(int menuID){
        List<MenuItemRecord> menuItemRecords = menuItemService.GetByMenuId(menuID);
        for(MenuItemRecord rec : menuItemRecords){
            Delete(rec);
        }
    }

    /**
     * Swaps languges for allergens on MenuItemRepresentation
     *
     * Use {@link #getAlergensForItem(List input, int languages)} to swap allergens name language
     *
     * @param input list of items allergens
     * @param language requested language
     * @return list of allergens names in requested language
     */
    public List<String> getAlergensForItem(List<String> input, int language){
        List<Integer> idList = new ArrayList<>();
        for(String rec : input)
            idList.add(allergensNameService.GetByDescription(rec).getIdAllergen());
        List<String> alergensByName = new ArrayList<>();
        for(Integer rec : idList)
            alergensByName.add(allergensNameService.GetById(rec,language).getAllergen());
        return alergensByName;
    }

    /**
     * Gets all MenuItemRepresentations in selected language
     *
     * Use {@link #getItems()} to get all MenuItemRepresentation in selected language from database
     *
     * @return list of MenuItemRepresentation in selected language
     */
    public List<MenuItemRepresentation> getItems() {
        return LoadData();
    }

    /**
     * Get all categories descriptions in selected language
     *
     * Use {@link #getCategories()} to get all Categories descriptions in selected language from database
     *
     * @return list of Categories descriptions
     */
    public List<String> getCategories()
    {
        return categoriesNameService.CategoriesDescriptions(languageId);
    }

    /**
     * Get all Units descriptions
     *
     * Use {@link #getUnits()} to get all Units descriptions from database
     *
     * @return list of all Units descriptions
     */
    public List<String> getUnits()
    {
        return unitsService.UnitsDescription();
    }

    /**
     * Get Menu ID
     *
     * @return Menu ID
     */
    public int getMenuID() {
        return menuID;
    }
}
