package kitchenpos.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {

    private final BigDecimal value;

    private Price(final BigDecimal value) {
        this.value = value;
    }

    public static Price valueOf(final BigDecimal value) {
        if (Objects.isNull(value) || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        return new Price(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
