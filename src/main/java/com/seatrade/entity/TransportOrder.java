package com.seatrade.entity;

public class TransportOrder {
    private int transportOrderId;
    private int foreignKeyCompanyId;

    private int foreignKeyShipId;


    public TransportOrder(int transportOrderId, int foreignKeyCompanyId, int foreignKeyShipId) {
        this.transportOrderId = transportOrderId;
        this.foreignKeyCompanyId = foreignKeyCompanyId;
        this.foreignKeyShipId = foreignKeyShipId;
    }


    public TransportOrder(int foreignKeyCompanyId, int foreignKeyShipId) {
        this.foreignKeyCompanyId = foreignKeyCompanyId;
        this.foreignKeyShipId = foreignKeyShipId;
    }

    public int getTransportOrderId() {
        return transportOrderId;
    }

    public int getForeignKeyCompanyId() {
        return foreignKeyCompanyId;
    }

    public int getForeignKeyShipId() {
        return foreignKeyShipId;
    }
}
