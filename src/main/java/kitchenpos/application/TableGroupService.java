package kitchenpos.application;

import java.util.ArrayList;
import java.util.List;
import kitchenpos.application.exception.OrderTableNotFoundException;
import kitchenpos.application.exception.TableGroupNotFoundException;
import kitchenpos.domain.ordertable.OrderTable;
import kitchenpos.domain.TableGroup;
import kitchenpos.domain.ordertable.repository.OrderTableRepository;
import kitchenpos.repository.TableGroupRepository;
import kitchenpos.ui.dto.request.CreateOrderGroupOrderTableRequest;
import kitchenpos.ui.dto.request.CreateTableGroupRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableGroupService {

    private final OrderTableRepository orderTableRepository;
    private final TableGroupRepository tableGroupRepository;

    public TableGroupService(
            final OrderTableRepository orderTableRepository,
            final TableGroupRepository tableGroupRepository
    ) {
        this.orderTableRepository = orderTableRepository;
        this.tableGroupRepository = tableGroupRepository;
    }

    @Transactional
    public TableGroup create(final CreateTableGroupRequest request) {
        final List<OrderTable> orderTables = new ArrayList<>();

        for (final CreateOrderGroupOrderTableRequest orderTableRequest : request.getOrderTables()) {
            final OrderTable findOrderTable = orderTableRepository.findById(orderTableRequest.getId())
                                                                  .orElseThrow(OrderTableNotFoundException::new);

            orderTables.add(findOrderTable);
        }

        final TableGroup tableGroup = new TableGroup(orderTables);

        return tableGroupRepository.save(tableGroup);
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        final TableGroup tableGroup = tableGroupRepository.findById(tableGroupId)
                                                          .orElseThrow(TableGroupNotFoundException::new);

        tableGroup.ungroupOrderTables();
    }
}
