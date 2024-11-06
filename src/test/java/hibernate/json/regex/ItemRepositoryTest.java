package hibernate.json.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemRepositoryTest {


    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("Native query")
    @Nested
    class SQL {

        @DisplayName("#getAll")
        @Nested
        class getAll {

            @Test
            void test() {
                // when
                List<Object[]> people = itemRepository.getAll();

                // then
                assertThat(people).hasSize(2);
            }
        }

        @DisplayName("#findBy")
        @Nested
        class findBy {

            @Test
            void test() {
                // given
                String value = "a";

                // when
                List<Object[]> people = itemRepository.findByReference(value);

                // then
                assertThat(people).hasSize(1);
            }
        }

    }
}