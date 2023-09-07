package models;

import annotations.*;

import java.time.LocalDate;
import java.util.Objects;

public class Staff {

        @PrimaryKey
        @AutoIncrement
        private long id;

        @MaxLength(maxLength = 256)
        @NotNull
        private String name;

        @NotNull
        @MaxLength(maxLength = 100)
        @Unique
        private String email;
        @NotNull
        private boolean isMarriage;
        @NotNull
        private LocalDate dateOfBirth;

        public Staff() {
        }

        public Staff(long id, String name, String email, boolean isMarriage, LocalDate dateOfBirth) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.isMarriage = isMarriage;
                this.dateOfBirth = dateOfBirth;
        }

        @Override
        public String toString() {
                return "Staff{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", email='" + email + '\'' +
                        ", isMarriage=" + isMarriage +
                        ", dateOfBirth=" + dateOfBirth +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Staff staff = (Staff) o;
                return id == staff.id && isMarriage == staff.isMarriage && Objects.equals(name, staff.name) && Objects.equals(email, staff.email) && Objects.equals(dateOfBirth, staff.dateOfBirth);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, name, email, isMarriage, dateOfBirth);
        }
}
