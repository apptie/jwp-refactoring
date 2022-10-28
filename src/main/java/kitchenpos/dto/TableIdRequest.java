package kitchenpos.dto;

public class TableIdRequest {

    private Long id;

    private TableIdRequest() {
    }

    public TableIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
