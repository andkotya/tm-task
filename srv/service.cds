namespace com.test.transport;

using com.test.transport from '../db/schema';
using { cuid } from '@sap/cds/common';

service TransportationService {
    @odata.draft.enabled
    entity TransportationOrder as projection on transport.TransportationOrder;
}