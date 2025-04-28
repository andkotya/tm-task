package com.test.transport.service;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@ServiceName("com.test.transport.TransportationService")
public class TransportationOrderHandler implements EventHandler {

    @On(event = "READ", entity = "com.test.transport.TransportationOrder")
    public void onRead(EventContext context, Service service) {
        List<Map<String, Object>> orders = (List<Map<String, Object>>) context.get("result");

        if (orders == null) {
            return;
        }

        for (Map<String, Object> order : orders) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) order.get("items");

            if (items == null) {
                continue;
            }

            BigDecimal totalWeight = BigDecimal.ZERO;

            for (Map<String, Object> item : items) {
                Integer quantity = (Integer) item.get("quantity");
                BigDecimal weight = (BigDecimal) item.get("weight");

                if (quantity != null && weight != null) {
                    totalWeight = totalWeight.add(weight.multiply(BigDecimal.valueOf(quantity)));
                }
            }

            order.put("totalWeight", totalWeight);
        }
    }
}
