namespace com.test.transport;

using com.test.transport from '../db/schema';
using { cuid } from '@sap/cds/common';

service TransportationService {
    @odata.draft.enabled
    entity TransportationOrder as projection on transport.TransportationOrder;

    entity TransportationOrderItem as projection on transport.TransportationOrderItem;

    action createTransportationOrderItem(
        transportOrder_ID : String(20),
        displayId : String(20),
        quantity : Integer,
        weight : Decimal(10,2)
    ) returns TransportationOrderItem;

    action editTransportationOrderItem(
        itemID : String(20),
        quantity : Integer,
        weight : Decimal(10,2)
    ) returns TransportationOrderItem;
}

