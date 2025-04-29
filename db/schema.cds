namespace com.test.transport;

using { managed } from '@sap/cds/common';

entity TransportationOrder : managed {
    key displayId : String(20) not null;
    description : localized String;
    totalWeight : Decimal(15, 2) @(Core.Computed);
    items : Composition of many TransportationOrderItem on items.order = $self;
}

entity TransportationOrderItem : managed {
    key displayId : String(20) not null;
    quantity : Integer;
    weight : Decimal(10,2);
    order : Association to TransportationOrder;
}