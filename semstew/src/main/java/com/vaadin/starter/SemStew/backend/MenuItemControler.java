package com.vaadin.starter.SemStew.backend;

import JOOQ.tables.records.CategoriesNameRecord;
import JOOQ.tables.records.MenuItemNameRecord;
import JOOQ.tables.records.MenuItemRecord;
import JOOQ.tables.records.UnitsRecord;
import com.vaadin.starter.SemStew.backend.Services.CategoriesNameService;
import com.vaadin.starter.SemStew.backend.Services.MenuItemNameService;
import com.vaadin.starter.SemStew.backend.Services.MenuItemService;
import com.vaadin.starter.SemStew.backend.Services.UnitsService;

import java.util.ArrayList;
import java.util.List;

public class MenuItemControler {
    private MenuItemService menuItemService = new MenuItemService();
    private MenuItemNameService menuItemNameService = new MenuItemNameService();
    private CategoriesNameService categoriesNameService = new CategoriesNameService();
    private UnitsService unitsService = new UnitsService();

    private List<MenuItemRepresantation> items;

    public MenuItemControler()
    {
        items = new ArrayList<>();
        LoadData();
    }

    private void LoadData()
    {
        List<MenuItemRecord> menuItemRecords = menuItemService.getConfigs();

        MenuItemNameRecord menuItemNameRecord;
        CategoriesNameRecord categoriesNameRecord;
        UnitsRecord unitsRecord;

        for(MenuItemRecord menuItemRec : menuItemRecords)
        {
            menuItemNameRecord = menuItemNameService.ItemNameById(menuItemRec.getIdMenuItem());

            categoriesNameRecord = categoriesNameService.CategoriesNameById(menuItemRec.getIdCategory());

            unitsRecord = unitsService.UnitById(menuItemRec.getIdUnit());

            if(menuItemNameRecord != null && categoriesNameRecord != null && unitsRecord != null ) {
                items.add(new MenuItemRepresantation());
                items.get(items.size()-1).Load(menuItemNameRecord, menuItemRec, categoriesNameRecord, unitsRecord);
            }
        }
    }

    public void Insert(MenuItemRepresantation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());


        menuItemService.InsertMenuItemService(item);
        menuItemNameService.InsertMenuItemNameService(itemName);

        LoadData();
    }

    public void Update(MenuItemRepresantation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        menuItemService.UpdateMenuItemService(item);
        menuItemNameService.UpdateMenuItemNameService(itemName);

        LoadData();
    }

    public void Delete(MenuItemRepresantation in)
    {
        MenuItemRecord item = in.GetItemRecord();
        MenuItemNameRecord itemName = in.GetItemNameRecord();

        CategoriesNameRecord category = categoriesNameService.CategoriesByName(in.getCategoryDescription());
        UnitsRecord unit = unitsService.UnitByName(in.getUnitDescription());

        item.setIdCategory(category.getIdCategory());
        item.setIdUnit(unit.getIdUnit());

        menuItemService.DeleteMenuItemService(item);
        menuItemNameService.DeleteMenuItemNameService(itemName);

        LoadData();
    }

    public List<MenuItemRepresantation> getItems() {
        return items;
    }

    public List<String> getCategories()
    {
        return categoriesNameService.CategoriesDescriptions();
    }

    public List<String> getUnits()
    {
        return unitsService.UnitsDescription();
    }
}
