package kitchenpos.application;

import static kitchenpos.fixture.ProductFixture.상품_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.List;
import kitchenpos.config.ServiceTest;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
@SuppressWarnings("NonAsciiCharacters")
class ProductServiceTest {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductService productService;

    @Test
    void create_메서드는_product를_전달하면_product를_저장하고_반환한다() {
        // given
        final Product product = 상품_생성();

        // when
        final Product actual = productService.create(product);

        // then
        assertThat(actual.getId()).isPositive();
    }
    
    @Test
    void create_메서드는_price가_null인_product를_전달하면_예외가_발생한다() {
        // given
        final Product invalidProduct = 상품_생성(null);

        // when & then
        assertThatThrownBy(() -> productService.create(invalidProduct))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "price가 {0}원이면 예외가 발생한다.")
    @ValueSource(longs = {-1L, -2L, -3L})
    void create_메서드는_price가_음수인_product를_전달하면_예외가_발생한다(final long invalidPrice) {
        // given
        final Product invalidProduct = 상품_생성(BigDecimal.valueOf(invalidPrice));

        // when & then
        assertThatThrownBy(() -> productService.create(invalidProduct))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void list_메서드는_등록한_모든_product를_반환한다() {
        // given
        final Product product = 상품_생성();
        final Product expected = productDao.save(product);

        // when
        final List<Product> actual = productService.list();

        // then
        assertAll(
                () -> assertThat(actual).hasSize(1),
                () -> assertThat(actual.get(0).getId()).isEqualTo(expected.getId())
        );
    }
}
