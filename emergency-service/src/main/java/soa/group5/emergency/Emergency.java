package soa.group5.emergency;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Emergency {

    private @Id @GeneratedValue Long id;
    private Long shipId;
    private EmergencyType emergencyType;

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, shipId, emergencyType);
    }

    public Emergency() {}

    public Emergency(Long shipId, EmergencyType emergencyType) {
        this.shipId = shipId;
        this.emergencyType = emergencyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public EmergencyType getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(EmergencyType emergencyType) {
        this.emergencyType = emergencyType;
    }


    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Emergency emergency = (Emergency) object;
        return Objects.equals(id, emergency.id) && Objects.equals(shipId, emergency.shipId) && Objects.equals(emergencyType, emergency.emergencyType);
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "id=" + id +
                ", shipId=" + shipId +
                ", emergencyType=" + emergencyType +
                '}';
    }
}