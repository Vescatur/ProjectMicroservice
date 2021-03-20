package soa.group5.headquarter;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Schedule {

    private Long id;
    private Long shipId;
    private String start;
    private String end;

    public Schedule() {}

    public Schedule(String start, String end) {
        this.start = start;
        this.end = end;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(start, schedule.start) && Objects.equals(end, schedule.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end);
    }
}