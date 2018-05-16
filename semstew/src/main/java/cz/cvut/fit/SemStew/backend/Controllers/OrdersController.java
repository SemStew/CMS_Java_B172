package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.OrderItemRecord;
import JOOQ.tables.records.OrdersRecord;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.OrderItemService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.OrdersService;

import java.util.ArrayList;
import java.util.List;

public class OrdersController {

    private final OrdersService ordersService = new OrdersService();
    private final OrderItemService orderItemService = new OrderItemService();
    private final MenuItemController menuItemController = new MenuItemController();

    public OrdersController(){}

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

    public OrdersRepresentation LoadById(int orderId){
        OrdersRecord ordersRecord = ordersService.GetByOrderId(orderId);
        List<OrderItemRecord> orderItemRecords = orderItemService.GetByOrderId(orderId);

        List<MenuItemRepresentation> menuItemRepresentations = new ArrayList<>();
        for(OrderItemRecord record : orderItemRecords){
            menuItemRepresentations.add(menuItemController.LoadById(record.getIdMenuItem()));
        }

        OrdersRepresentation ordersRepresentation = new OrdersRepresentation();
        ordersRepresentation.LoadData(ordersRecord,menuItemRepresentations);
        return ordersRepresentation;
    }

    public OrdersRepresentation LoadByID(){
        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_detail") == null){
            return null;
        }
        return LoadById(Integer.parseInt(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_detail").toString()));
    }

    public void InsertOrder(OrdersRepresentation insert){
        OrdersRecord ordersRecord = insert.getOrderRecord();
        ordersService.InsertOrdersService(ordersRecord);
    }

    public void InsertItemIntoOrder(int orderId, MenuItemRepresentation inItem){
        OrderItemRecord orderItemRecord = new OrderItemRecord();
        orderItemRecord.setIdOrder(orderId);
        orderItemRecord.setIdMenuItem(inItem.getIdMenuItem());
        orderItemService.InsertOrderItemService(orderItemRecord);
    }

    public void Update(OrdersRecord update){
        ordersService.UpdateOrdersService(update);
    }

    public void Update(OrdersRepresentation update){
        OrdersRecord ordersRecord = update.getOrderRecord();

        ordersService.UpdateOrdersService(ordersRecord);
    }

    public void Delete(OrdersRepresentation delete){
        OrdersRecord ordersRecord = delete.getOrderRecord();

        orderItemService.DeleteByOrderId(ordersRecord.getIdOrder());
        ordersService.DeleteOrdersService(ordersRecord);
    }

    public void RemoveItem(int orderId, MenuItemRepresentation removeItem){
        OrderItemRecord orderItemRecord = new OrderItemRecord();
        orderItemRecord.setIdOrder(orderId);
        orderItemRecord.setIdMenuItem(removeItem.getIdMenuItem());

        orderItemService.DeleteOrderItemService(orderItemRecord);
    }

    public List<OrdersRepresentation> getConfigs(){
        return LoadData();
    }
}
