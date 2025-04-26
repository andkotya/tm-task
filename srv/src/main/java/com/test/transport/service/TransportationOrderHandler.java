package com.test.transport.service;

import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.test.transport.model.TransportationOrder;
import com.test.transport.model.TransportationOrderItem;
import com.sap.cds.services.handler.annotations.Before;

@ServiceName("TransportationService")
public class TransportationOrderHandler implements EventHandler {

    @Before(event = CdsService.EVENT_READ, entity = "TransportationOrder")
    public void beforeReadTransportationOrder(CdsService.ReadContext context) {
        context.setParameter("$autoexpand", "items");
    }

    @On(entity = "TransportationService.calculateTotal")
    public void calculateTotalWeightOnRead(CdsService.ReadContext context) {
        Object result = context.getResult();

        if (result instanceof List) {
            ((List<TransportationOrder>) result).forEach(this::calculateOrderTotal);
        } else if (result instanceof TransportationOrder) {
            calculateOrderTotal((TransportationOrder) result);
        }
    }

    @On(entity = "TransportationService.editTransportationOrderItem")
    public void onEditItem(CdsUpdateEventContext context) {
        String itemId = (String) context.getParameter("itemID");
        Integer quantity = (Integer) context.getParameter("quantity");
        BigDecimal weight = (BigDecimal) context.getParameter("weight");

        TransportationOrderItem item = TransportationOrderItem.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item not found"));

        if (quantity != null) item.setQuantity(quantity);
        if (weight != null) item.setWeight(weight);

        context.setResult(item);
        System.out.println("Updated item: " + itemId);
    }
}

