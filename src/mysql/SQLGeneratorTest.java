package mysql;

import classes.test.*;
import exceptions.InvalidAnnotationsSetException;
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
            String expected = "CREATE TABLE Correct test (" + spr +
                    "nickname CHAR(30) NOT NULL," + spr +
                    "email VARCHAR(1024) NOT NULL," + spr +
                    "position INT AUTO_INCREMENT NOT NULL," + spr +
                    "letter CHAR(1) NOT NULL," + spr +
                    "PRIMARY KEY(email)," + spr +
                    "UNIQUE(nickname)" + spr +
                    ");";
            String actual = sqlGenerator.generateTable(TestClass_Correct.class);
            assertEquals(expected, actual);
        }

        @Test
        void throws_InvalidAnnotationsSetException_on_TestClass2_bcs_of_3_PrimaryKeys() {
            assertThrows(InvalidAnnotationsSetException.class, () -> sqlGenerator.generateTable(TestClass_3PrimaryKeys.class));
        }
        @Test
        void throws_IllegalArgumentException_on_class_without_empty_constructor() {
            assertThrows(IllegalArgumentException.class, () -> sqlGenerator.generateTable(Integer.class));
        }
        @Test
        void throws_InvalidAnnotationsSetException_on_class_with_AutoIncrement_without_int() {
            assertThrows(InvalidAnnotationsSetException.class, () -> sqlGenerator.generateTable(TestClass_AutoIncrement_without_INT.class));
        }
        @Test
        void throws_InvalidAnnotationsSetException_on_class_with_PrimaryKey_without_NotNull() {
            assertThrows(InvalidAnnotationsSetException.class, () -> sqlGenerator.generateTable(TestClass_PR_Key_Null.class));
        }
        @Test
        void throws_InvalidAnnotationsSetException_on_class_with_MaxLength_without_String() {
            assertThrows(InvalidAnnotationsSetException.class, () -> sqlGenerator.generateTable(TestClass_MaxLength_without_String.class));
        }
    }

}