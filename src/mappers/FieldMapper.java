package mappers;

import annotations.*;
import models.Column;
import models.ColumnType;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class FieldMapper {
    private FieldMapper() {
    }
    public static Column fromField(Field field) {
        ColumnType columnType = getColumnType(field);
        if (columnType == null) return null;
        Column column = new Column();
        column.columnType = columnType;
        column.name = field.getName();
        column.isAutoIncrement = field.isAnnotationPresent(AutoIncrement.class);
        column.isPrimaryKey = field.isAnnotationPresent(PrimaryKey.class);
        column.isNotNull = field.isAnnotationPresent(NotNull.class);
        column.isUnique = field.isAnnotationPresent(Unique.class);
        column.hasMaxLength = field.isAnnotationPresent(MaxLength.class);
        if (column.hasMaxLength) {
            column.maxLength = field.getDeclaredAnnotation(MaxLength.class).maxLength();
        }
        if ((columnType == ColumnType.VARCHAR) && (column.maxLength == Integer.MAX_VALUE)) {
            column.columnType = ColumnType.TEXT;
        }

        return column;
    }
    private static ColumnType getColumnType(Field field) {
        Class<?> fieldType = field.getType();
        ColumnType columnType;
        if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
            columnType = ColumnType.BOOL;
        } else if (fieldType == Integer.TYPE || fieldType == Integer.class) {
            columnType = ColumnType.INT;
        } else if (fieldType == Long.TYPE || fieldType == Long.class) {
            columnType = ColumnType.BIGINT;
        } else if (fieldType == Float.TYPE || fieldType == Float.class) {
            columnType = ColumnType.FLOAT;
        } else if (fieldType == Double.TYPE || fieldType == Double.class) {
            columnType = ColumnType.DOUBLE;
        } else if (fieldType == Character.TYPE || fieldType == Character.class) {
            columnType = ColumnType.CHAR;
        } else if (fieldType == String.class) {
            columnType = ColumnType.VARCHAR;
        } else if (fieldType == LocalDate.class) {
            columnType = ColumnType.DATE;
        } else if (fieldType == LocalDateTime.class) {
            columnType = ColumnType.DATETIME;
        } else {
            return null;
        }
        return columnType;
    }
}
