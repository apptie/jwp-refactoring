package kitchenpos.ui;

import kitchenpos.application.TableGroupService;
import kitchenpos.domain.TableGroup;
import kitchenpos.ui.dto.request.CreateTableGroupRequest;
import kitchenpos.ui.dto.response.CreateTableGroupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TableGroupRestController {

    private final TableGroupService tableGroupService;

    public TableGroupRestController(final TableGroupService tableGroupService) {
        this.tableGroupService = tableGroupService;
    }

    @PostMapping("/api/table-groups")
    public ResponseEntity<CreateTableGroupResponse> create(@RequestBody final CreateTableGroupRequest request) {
        final TableGroup created = tableGroupService.create(request);
        final URI uri = URI.create("/api/table-groups/" + created.getId());
        final CreateTableGroupResponse response = new CreateTableGroupResponse(created);

        return ResponseEntity.created(uri)
                             .body(response);
    }

    @DeleteMapping("/api/table-groups/{tableGroupId}")
    public ResponseEntity<Void> ungroup(@PathVariable final Long tableGroupId) {
        tableGroupService.ungroup(tableGroupId);

        return ResponseEntity.noContent()
                             .build();
    }
}
