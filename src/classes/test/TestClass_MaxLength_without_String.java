package classes.test;

import annotations.MaxLength;
import annotations.NotNull;

public class TestClass_MaxLength_without_String {
    @MaxLength(bytes = 255)
    @NotNull
    int id;

    public TestClass_MaxLength_without_String() {
    }
}
