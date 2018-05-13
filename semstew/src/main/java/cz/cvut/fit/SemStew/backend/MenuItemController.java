package cz.cvut.fit.SemStew.backend;

import JOOQ.tables.MenuItemAllergen;
import JOOQ.tables.records.*;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.AboutUsConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.MenuServices.*;

import java.util.ArrayList;
import java.util.List;

public class MenuItemController {
    private final MenuItemService menuItemService = new MenuItemService();
    private final MenuItemNameService menuItemNameService = new MenuItemNameService();
    private final CategoriesNameService categoriesNameService = new CategoriesNameService();
    private final AboutUsConfigService.UnitsService unitsService = new AboutUsConfigService.UnitsService();
    private final MenuItemAllergenService menuItemAllergenService = new MenuItemAllergenService();
    private final AllergensNameService allergensNameService = new AllergensNameService();
    private final LanguagesService languagesService = new LanguagesService();
    private  int menuID = 0;

    public MenuItemController() {}

    private List<MenuItemRepresantation> LoadData()
    {
        List<MenuItemRepresantation> items = new ArrayList<>();
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

        int id = languagesService.GetIdByName(name);

        List<MenuItemRecord> menuItemRecords = menuItemService.GetByMenuId(menuID);

        for(MenuItemRecord menuItemRec : menuItemRecords)
        {
            menuItemNameRecord = menuItemNameService.ItemNameById(menuItemRec.getIdMenuItem(),id);

            categoriesNameRecord = categoriesNameService.CategoriesNameById(menuItemRec.getIdCategory(),id);

            unitsRecord = unitsService.UnitById(menuItemRec.getIdUnit());

            List<MenuItemAllergenRecord> menuItemAllergenRecords = menuItemAllergenService.GetByMenuItemId(menuItemRec.getIdMenuItem());

            List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();
            if(menuItemAllergenRecords.size()!=0) {
                for (MenuItemAllergenRecord rec : menuItemAllergenRecords) {
                    allergensNameRecords.add(allergensNameService.GetById(rec.getIdAllergen(), id));
                }
            }

            if(menuItemNameRecord != null && categoriesNameRecord != null && unitsRecord != null) {
                items.add(new MenuItemRepresantation());
                items.get(items.size()-1).Load(menuItemNameRecord, menuItemRec, categoriesNameRecord, unitsRecord,allergensNameRecords);
            }
        }

        return items;
    }

    public void Insert(MenuItemRepresantation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        List<String> allergens = in.getAlergens();
        List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();

        for(String rec : allergens){
            allergensNameRecords.add(allergensNameService.GetByDescription(rec));
        }

        menuItemService.InsertMenuItemService(item);
        MenuItemRecord inserted = menuItemService.GetByCombination(item.getAmount(),item.getPrice(), item.getIdUnit(),item.getIdMenu(),item.getImageName());
        itemName.setIdMenuItem(inserted.getIdMenuItem());
        menuItemNameService.InsertMenuItemNameService(itemName);

        for(AllergensNameRecord rec : allergensNameRecords){
            MenuItemAllergenRecord tmp = new MenuItemAllergenRecord();
            tmp.setIdAllergen(rec.getIdAllergen());
            tmp.setIdMenuItem(inserted.getIdMenuItem());
            menuItemAllergenService.InsertMenuItemAllergenService(tmp);
        }
    }

    public void InsertMultiLingual(List<MenuItemRepresantation> insert){
        MenuItemRecord item = insert.get(0).GetItemRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(insert.get(0).getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(insert.get(0).getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        List<String> allergens = insert.get(0).getAlergens();
        List<AllergensNameRecord> allergensNameRecords = new ArrayList<>();

        for(String rec : allergens){
            allergensNameRecords.add(allergensNameService.GetByDescription(rec));
        }

        menuItemService.InsertMenuItemService(item);
        MenuItemRecord inserted = menuItemService.GetByCombination(item.getAmount(),item.getPrice(), item.getIdUnit(), item.getIdMenu(), item.getImageName());

        for(AllergensNameRecord rec : allergensNameRecords){
            MenuItemAllergenRecord tmp = new MenuItemAllergenRecord();
            tmp.setIdAllergen(rec.getIdAllergen());
            tmp.setIdMenuItem(inserted.getIdMenuItem());
            menuItemAllergenService.InsertMenuItemAllergenService(tmp);
        }

        for(MenuItemRepresantation rep : insert){
            MenuItemNameRecord tmp = rep.GetItemNameRecord();

            tmp.setIdMenuItem(inserted.getIdMenuItem());
            menuItemNameService.InsertMenuItemNameService(tmp);
        }
    }

    public void Update(MenuItemRepresantation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        List<String> allergens = in.getAlergens();
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

    public void Delete(MenuItemRepresantation in)
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

    private void Delete(MenuItemRecord in){

        menuItemAllergenService.DeleteByMenuItemId(in.getIdMenuItem());

        menuItemNameService.DeleteByMenuItemId(in.getIdMenuItem());
        menuItemService.DeleteMenuItemService(in);
    }

    public void DeleteByMenuId(int menuID){
        List<MenuItemRecord> menuItemRecords = menuItemService.GetByMenuId(menuID);
        for(MenuItemRecord rec : menuItemRecords){
            Delete(rec);
        }
    }

    public List<MenuItemRepresantation> getItems() {
        return LoadData();
    }

    public List<String> getCategories()
    {
        return categoriesNameService.CategoriesDescriptions();
    }

    public List<String> getUnits()
    {
        return unitsService.UnitsDescription();
    }

    public int getMenuID() {
        return menuID;
    }
}
