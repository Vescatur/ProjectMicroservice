package soa.group5.headquarter;

public class Order {
    private Long id;
    private String start;
    private String end;

    public Order(String start, String end) {
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
}
