package soa.group5.emergency.integrations.response;

import soa.group5.emergency.EmergencyType;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "shipId",
        "type",
        "dispatched"
})
@XmlRootElement(name = "Dispatch")
public class DispatchResponse {

    protected long shipId;
    protected EmergencyType type;
    protected boolean dispatched;

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public EmergencyType getType() {
        return type;
    }

    public void setType(EmergencyType type) {
        this.type = type;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }
}
