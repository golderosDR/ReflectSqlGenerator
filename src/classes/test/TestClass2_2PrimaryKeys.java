package classes.test;

import annotations.AutoIncrement;
import annotations.MaxLength;
import annotations.NotNull;
import annotations.PrimaryKey;

import java.util.Objects;

public class TestClass2_2PrimaryKeys {
    @PrimaryKey
    @AutoIncrement
    @NotNull
    private Long id;
    @PrimaryKey
    @NotNull
    private String email;
    @NotNull
    @MaxLength(maxLength = 256)
    private String name;

    public TestClass2_2PrimaryKeys() {
    }

    public TestClass2_2PrimaryKeys(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass2_2PrimaryKeys that = (TestClass2_2PrimaryKeys) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name);
    }

    @Override
    public String toString() {
        return "TestClass2_2PrimaryKeys{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
