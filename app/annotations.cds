using com.test.transport as transport from '../srv/service';

annotate transport.TransportationOrder with @(
Capabilities: {
      Insertable: true,
      Updatable: true,
      Deletable: true
    },
  UI: {
  SelectionFields: [displayId, description],
    LineItem: [
      {
        $Type: 'UI.DataField',
        Value: displayId,
        Label: 'Order ID'
      },
      {
        $Type: 'UI.DataField',
        Value: description,
        Label: 'Description'
      },
      {
        $Type: 'UI.DataField',
        Value: totalWeight,
        Label: 'Total weight',
        @Common.Text: {
          $Path: 'totalWeight',
          format: '{0} kg'
        }
      }
    ],
    HeaderInfo: {
      Title: {
        $Type: 'UI.DataField',
        Value: displayId,
        Label: 'Order ID'
      },
      Description: {
        $Type: 'UI.DataField',
        Value: description
      }
    },

    Facets: [
      {
        $Type: 'UI.ReferenceFacet',
        Label: 'General Information',
        Target: '@UI.FieldGroup#GeneralInfo'
      },
      {
        $Type: 'UI.ReferenceFacet',
        Label: 'Items',
        Target: 'items/@UI.LineItem',
        Actions: [
        {
          $Type: 'UI.DataFieldForAction',
          Action: 'com.test.transport.TransportationOrderItem/create',
          Label: 'Add Item'
        },
        {
          $Type: 'UI.DataFieldForAction',
          Action: 'com.test.transport.TransportationOrderItem/edit',
          Label: 'Edit Item'
        }
        ]
      }
    ],

    FieldGroup#GeneralInfo: {
      $Type: 'UI.FieldGroupType',
      Data: [
        {
          $Type: 'UI.DataField',
          Value: displayId,
          Label: 'Order ID'
        },
        {
          $Type: 'UI.DataField',
          Value: description,
          Label: 'Description'
        },
        {
          $Type: 'UI.DataField',
          Value: totalWeight,
          Label: 'Total Weight',
          @Common.Text: {$Path: 'totalWeight', format: '{0} kg'}
        }
      ]
    },
    Identification: [
      {Value: displayId},
      {Value: description}
    ]
  },
  Common: {
    DraftRoot: true,
    DraftActivationAction: true
  }
);

annotate transport.TransportationOrderItem with @(
Capabilities: {
      Updatable: true,
      Deletable: true
    },
  UI: {
    LineItem: [
      {
        $Type: 'UI.DataField',
        Value: displayId,
        Label: 'ID Item'
      },
      {
        $Type: 'UI.DataField',
        Value: quantity,
        Label: 'Quantity'
      },
      {
        $Type: 'UI.DataField',
        Value: weight,
        Label: 'Weight'
      }
    ]
  },
  Common: {
    DraftNode: true,
    DraftActivationAction: true
  }
);
