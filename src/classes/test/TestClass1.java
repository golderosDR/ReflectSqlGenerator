package classes.test;

import annotations.*;

import java.util.Objects;

public class TestClass1 {
    public TestClass1() {
    }
    @NotNull
    @MaxLength(maxLength = 30)
    @Unique
    private String nickname;
    @NotNull
    @PrimaryKey
    private String email;

    @AutoIncrement
    @NotNull
    private int position;
    @NotNull
    @ColumnName(name = "letter")
    private char someLetter;

    public TestClass1(String nickname, String email, int position, char someLetter) {
        this.nickname = nickname;
        this.email = email;
        this.position = position;
        this.someLetter = someLetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass1 that = (TestClass1) o;
        return position == that.position && someLetter == that.someLetter && Objects.equals(nickname, that.nickname) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, email, position, someLetter);
    }

    @Override
    public String toString() {
        return "TestClass1{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", position=" + position +
                ", someLetter=" + someLetter +
                '}';
    }
}
