package com.seatrade.entity;

public class ShipHarbourAssociation {
    private int fkShipId;
    private int fkHarbourId;
    private int shipHarbourAssociationId;

    public ShipHarbourAssociation(int fkShipId, int fkHarbourId, int shipHarbourAssociationId) {
        this.fkShipId = fkShipId;
        this.fkHarbourId = fkHarbourId;
        this.shipHarbourAssociationId = shipHarbourAssociationId;
    }

    public ShipHarbourAssociation(int fkShipId, int fkHarbourId) {
        this.fkShipId = fkShipId;
        this.fkHarbourId = fkHarbourId;
    }

    public int getFkShipId() {
        return fkShipId;
    }

    public int getFkHarbourId() {
        return fkHarbourId;
    }

    public int getShipHarbourAssociationId() {
        return shipHarbourAssociationId;
    }
}
