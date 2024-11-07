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
                assertThat(people).hasSize(7);
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

        @DisplayName("#findByPattern")
        @Nested
        class findByPattern {

            @DisplayName("works for pattern without brackets")
            @Nested
            class Unbracketed {

                @DisplayName("literal (without dot)")
                @Test
                void literal() {
                    // given
                    String pattern = "^a$";

                    // when
                    List<Object[]> people = itemRepository.findByReferenceRegex(pattern);

                    // then
                    assertThat(people).hasSize(1);
                }

                @DisplayName("with class, without escaping dot")
                @Test
                void withClass() {
                    // given
                    String pattern = "^.*$";

                    // when
                    List<Object[]> people = itemRepository.findByReferenceRegex(pattern);

                    // then
                    assertThat(people).hasSize(7);
                }
            }


            @DisplayName("pattern with dot")
            @Nested
            class Dotted {

                @DisplayName("with escaping - works")
                @Test
                void test2() {
                    // given
                    String pattern = "^a\\.a$";

                    // when
                    List<Object[]> people = itemRepository.findByReferenceRegex(pattern);

                    // then
                    assertThat(people).hasSize(1);
                }

            }

            @DisplayName("pattern with brackets")
            @Nested
            class Bracketed {

                @DisplayName("No escape - fails")
                @Test
                void unescaped() {
                    // given
                    String pattern = "^a[.*]$";

                    // when
                    List<Object[]> people = itemRepository.findByReferenceRegex(pattern);

                    // then
                    assertThat(people).hasSize(0);
                }

                @DisplayName("Escaping (with double backslash) - works")
                @Test
                void escaping() {
                    // given
                    String pattern = "^a\\[.*\\]$";

                    // when
                    List<Object[]> people = itemRepository.findByReferenceRegex(pattern);

                    // then
                    assertThat(people).hasSize(2);
                }

            }

            @DisplayName("pattern with brackets and dots")
            @Nested
            class BracketAndDot {

                @DisplayName("Escaping - works")
                @Test
                void escaping() {
                    // given
                    String pattern = "^a\\.a\\[.*\\]$";

                    // when
                    List<Object[]> people = itemRepository.findByReferenceRegex(pattern);

                    // then
                    assertThat(people).hasSize(2);
                }

            }
        }


    }
}