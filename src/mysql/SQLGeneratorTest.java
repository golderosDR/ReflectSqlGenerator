package mysql;

import classes.test.TestClass1;
import classes.test.TestClass2_2PrimaryKeys;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SQLGenerator is works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class SQLGeneratorTest {
    private SQLGenerator sqlGenerator;
    private static final String spr = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        this.sqlGenerator = new SQLGenerator();
    }

    @DisplayName("generate table tests")
    @Nested
    public class Generate {
        @Test
        void return_correct_table_on_TestClass1() {
            String expected = "CREATE TABLE TestClass1 (" + spr +
                    "nickname VARCHAR(30) NOT NULL," + spr +
                    "email VARCHAR(256) NOT NULL," + spr +
                    "position INT NOT NULL AUTO_INCREMENT," + spr +
                    "letter VARCHAR(1) NOT NULL," + spr +
                    "PRIMARY KEY(email)," + spr +
                    "UNIQUE(nickname)" + spr +
                    ");";
            String actual = sqlGenerator.generateTable(TestClass1.class);
            assertEquals(expected, actual);
        }

        @Test
        void throws_RuntimeException_on_TestClass2_bcs_of_2_PrimaryKeys() {
            assertThrows(RuntimeException.class, () -> sqlGenerator.generateTable(TestClass2_2PrimaryKeys.class));
        }
        @Test
        void throws_IllegalArgumentException_on_class_without_empty_constructor() {
            assertThrows(RuntimeException.class, () -> sqlGenerator.generateTable(Integer.class));
        }
    }

}