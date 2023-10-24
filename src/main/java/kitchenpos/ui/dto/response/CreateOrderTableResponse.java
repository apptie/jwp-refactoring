package kitchenpos.ui.dto.response;

import kitchenpos.domain.ordertable.OrderTable;
import kitchenpos.domain.tablegroup.TableGroup;

public class CreateOrderTableResponse {

    private final Long id;
    private final Long tableGroupId;
    private final int numberOfGuests;
    private final boolean empty;

    public CreateOrderTableResponse(final OrderTable orderTable) {
        this.id = orderTable.getId();
        this.tableGroupId = convertTableGroupId(orderTable);
        this.numberOfGuests = orderTable.getNumberOfGuests();
        this.empty = orderTable.isEmpty();
    }

    private Long convertTableGroupId(final OrderTable orderTable) {
        final TableGroup tableGroup = orderTable.getTableGroup();

        if (tableGroup == null) {
            return null;
        }

        return tableGroup.getId();
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }
}
