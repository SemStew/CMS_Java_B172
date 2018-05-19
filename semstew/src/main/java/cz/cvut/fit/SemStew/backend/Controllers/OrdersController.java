package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.OrderItemRecord;
import JOOQ.tables.records.OrdersRecord;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.OrderItemService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.OrdersService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class OrdersController {
    /**
     * Orders management
     */
    private final OrdersService ordersService = new OrdersService();
    /**
     * OrdersItem management
     */
    private final OrderItemService orderItemService = new OrderItemService();
    /**
     * MenuIem management
     */
    private final MenuItemController menuItemController = new MenuItemController();

    /**
     * OrdersController constructor
     */
    public OrdersController(){}

    /**
     * Get all OrdersRepresentation
     *
     * Use {@link #LoadData()} to get all OrdersRepresentation from database
     *
     * @return list of OrdersRepresentation
     */
    private List<OrdersRepresentation> LoadData(){
        List<OrdersRepresentation> ordersRepresentations = new ArrayList<>();

        List<OrdersRecord> ordersRecords = ordersService.getConfigs();


        for(OrdersRecord rec : ordersRecords){
            List<OrderItemRecord> orderItemRecords = orderItemService.GetByOrderId(rec.getIdOrder());

            List<MenuItemRepresentation> menuItemRepresentations = new ArrayList<>();
            for(OrderItemRecord record : orderItemRecords){
                menuItemRepresentations.add(menuItemController.LoadById(record.getIdMenuItem()));
            }

            ordersRepresentations.add(new OrdersRepresentation());
            ordersRepresentations.get(ordersRepresentations.size() -1).LoadData(rec, menuItemRepresentations);
        }

        return ordersRepresentations;
    }

    /**
     * Get OrdersRepresentation by id
     *
     * Use {@link #LoadById(int id)} to get OrdersRepresentation by given id
     *
     * @param orderId ID of requested order
     * @return OrdersRepresentation with given id if it exists, else null
     */
    public OrdersRepresentation LoadById(int orderId){
        OrdersRecord ordersRecord = ordersService.GetByOrderId(orderId);
        if(ordersRecord == null)
            return null;
        List<OrderItemRecord> orderItemRecords = orderItemService.GetByOrderId(orderId);

        List<MenuItemRepresentation> menuItemRepresentations = new ArrayList<>();
        for(OrderItemRecord record : orderItemRecords){
            menuItemRepresentations.add(menuItemController.LoadById(record.getIdMenuItem()));
        }

        OrdersRepresentation ordersRepresentation = new OrdersRepresentation();
        ordersRepresentation.LoadData(ordersRecord,menuItemRepresentations);
        return ordersRepresentation;
    }

    /**
     * Get current OrdersRepresentation
     *
     * Use {@link #LoadByID()} to get currently worked on OrdersRepresentation
     *
     * @return currently worked on OrdersRepresentation
     */
    public OrdersRepresentation LoadByID(){
        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_detail") == null){
            return null;
        }
        return LoadById(Integer.parseInt(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_detail").toString()));
    }

    /**
     * Get ordered items in OrdersRepresentation
     *
     * Use {@link #GetItemInOrder(int)} to get ordered items in OrdersRepresentation of given id
     *
     * @param orderId Id of requested OrdersRepresentation
     * @return list of OrderedItem records if order exists, else null
     */
    public List<OrderItemRecord> GetItemInOrder(int orderId){
        return orderItemService.GetByOrderId(orderId);
    }

    /**
     * Checks if order contains certain item
     *
     * Use {@link #IsSelected(int, int)} to check if OrdersRepresentation of given orderId contains ordered item of given itemId
     *
     * @param orderId Id of requested OrdersRepresentation
     * @param itemId Id of requested item
     * @return true it it contains item, false if it does not,
     */
    public boolean IsSelected(int orderId, int itemId){
        if(orderItemService.GetSpecific(orderId,itemId) == null){
            return false;
        }

        return true;
    }

    /**
     * Inserts OrdersRepresentation and returns generated id
     *
     * Use {@link #InsertOrder(OrdersRepresentation)} inserts given order OrdersRepresentation and returns generated id
     *
     * @param insert OrdersRepresentation to be inserted
     * @return generated id
     */
    public int InsertOrder(OrdersRepresentation insert){
        OrdersRecord ordersRecord = insert.getOrderRecord();
        return ordersService.InsertAndGetId(ordersRecord);
    }

    /**
     * Inserts Items into OrdersRepresentation
     *
     * Use {@link #InsertItemIntoOrder(int, MenuItemRepresentation)} to insert given items into OrdersRepresentation
     *
     * @param orderId OrdersRepresentation to insert into
     * @param inItem item to insert
     */
    public void InsertItemIntoOrder(int orderId, MenuItemRepresentation inItem){
        OrderItemRecord orderItemRecord = new OrderItemRecord();
        orderItemRecord.setIdOrder(orderId);
        orderItemRecord.setIdMenuItem(inItem.getIdMenuItem());
        orderItemService.InsertOrderItemService(orderItemRecord);
    }

    /**
     * Updates OrderRecords
     *
     * @param update OrderRecord to be updated
     */
    public void Update(OrdersRecord update){
        ordersService.UpdateOrdersService(update);
    }

    /**
     * Updates OrdersRepresentation
     *
     * @param update OrdersRepresentation to be updated
     */
    public void Update(OrdersRepresentation update){
        OrdersRecord ordersRecord = update.getOrderRecord();

        ordersService.UpdateOrdersService(ordersRecord);
    }

    /**
     * Delete OrdersRepresentation
     *
     * Use {@link #Delete(OrdersRepresentation)} to delete given OrdersRepresentation
     *
     * @param delete OrdersRepresentation to be deleted
     */
    public void Delete(OrdersRepresentation delete){
        OrdersRecord ordersRecord = delete.getOrderRecord();

        orderItemService.DeleteByOrderId(ordersRecord.getIdOrder());
        ordersService.DeleteOrdersService(ordersRecord);
    }

    /**
     * Delete OrdersRepresentation by id
     *
     * Use {@link #Delete(int)} to delete OrdersRepresentation by given id
     *
     * @param orderId id of OrdersRepresentation to delete
     */
    public void Delete(int orderId){
        orderItemService.DeleteByOrderId(orderId);
        ordersService.DeleteById(orderId);
    }

    /**
     * Removes item from OrdersRepresentation
     *
     * Use {@link #RemoveItem(int, MenuItemRepresentation)} to remove given item from given OrdersRepresentation
     *
     * @param orderId id of selected OrdersRepresentation
     * @param removeItem item to be removed
     */
    public void RemoveItem(int orderId, MenuItemRepresentation removeItem){
        OrderItemRecord orderItemRecord = new OrderItemRecord();
        orderItemRecord.setIdOrder(orderId);
        orderItemRecord.setIdMenuItem(removeItem.getIdMenuItem());

        orderItemService.DeleteOrderItemService(orderItemRecord);
    }

    /**
     * Get all OrdersRepresentation
     *
     * Use {@link #getConfigs()} to get all OrdersRepresentation from database
     *
     * @return list of all OrdersRepresentation
     */
    public List<OrdersRepresentation> getConfigs(){
        return LoadData();
    }
}
