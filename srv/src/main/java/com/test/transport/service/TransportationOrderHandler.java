//package com.test.transport.service;
//
//import com.sap.cds.services.cds.CqnService;
//import com.sap.cds.services.handler.EventHandler;
//import com.sap.cds.services.handler.annotations.Before;
//import com.sap.cds.services.handler.annotations.ServiceName;
//import com.sap.cds.services.cds.CdsReadEventContext;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@ServiceName("TransportationService")
//public class TransportationOrderHandler implements EventHandler {
//
//    @Before(event = CqnService.EVENT_READ, entity = "TransportationOrder")
//    public void beforeReadTransportationOrders(CdsReadEventContext context) {
//        context.getResult().stream()
//                .map(row -> {
//                    @SuppressWarnings("unchecked")
//                    Map<String, Object> order = (Map<String, Object>) row;
//                    return calculateTotalWeight(order);
//                })
//                .toList();
//    }
//
//    private Map<String, Object> calculateTotalWeight(Map<String, Object> order) {
//        @SuppressWarnings("unchecked")
//        List<Map<String, Object>> items = (List<Map<String, Object>>) order.get("items");
//        BigDecimal totalWeight = BigDecimal.ZERO;
//
//        if (items != null) {
//            for (Map<String, Object> item : items) {
//                Integer quantity = item.get("quantity") instanceof Integer ? (Integer) item.get("quantity") : 0;
//                BigDecimal weight = item.get("weight") instanceof BigDecimal ? (BigDecimal) item.get("weight") : BigDecimal.ZERO;
//                totalWeight = totalWeight.add(weight.multiply(BigDecimal.valueOf(quantity)));
//            }
//        }
//        order.put("totalWeight", totalWeight);
//        return order;
//    }
//}