package mappers;

import annotations.*;
import exceptions.InvalidAnnotationsSetException;
import models.Column;
import models.ColumnType;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static constants.Const.*;
import static exceptions.InvalidAnnotationsSetException.*;


public class FieldMapper {

    private FieldMapper() {
    }

    public static Column fromField(Field field) {
        ColumnType columnType = getColumnType(field);
        if (columnType == null) return null;

        Column column = new Column();

        column.columnType = columnType;
        column.maxLength = CHAR_DEFAULT_MIN_BYTES;

        column.isAutoIncrement = field.isAnnotationPresent(AutoIncrement.class);
        column.isPrimaryKey = field.isAnnotationPresent(PrimaryKey.class);
        column.isNotNull = field.isAnnotationPresent(NotNull.class);
        column.isUnique = field.isAnnotationPresent(Unique.class);
        column.hasMaxLength = field.isAnnotationPresent(MaxLength.class);
        column.hasColumnName = field.isAnnotationPresent(ColumnName.class);

        if (column.hasColumnName) {
            column.name = field.getDeclaredAnnotation(ColumnName.class).name();
        } else {
            column.name = field.getName();
        }


        if (column.hasMaxLength) {
            if (columnType != ColumnType.VARCHAR) {
                throw new InvalidAnnotationsSetException(WRONG_MAX_LENGTH_ANNOTATION_USE);
            }
            column.maxLength = field.getDeclaredAnnotation(MaxLength.class).bytes();
        }
        if (column.isPrimaryKey && !column.isNotNull) {
            throw new InvalidAnnotationsSetException(PRIMARY_KEY_WITHOUT_NOT_NULL);
        }

        boolean isNotInteger = column.columnType != ColumnType.BIGINT && column.columnType != ColumnType.INT;

        if (column.isAutoIncrement && isNotInteger) {
            throw new InvalidAnnotationsSetException(AUTOINCREMENT_ANNOTATION_INVALID_USE);
        }

        if (columnType == ColumnType.VARCHAR) {
            if (column.hasMaxLength) {
                if (column.maxLength > VARCHAR_DEFAULT_MAX_BYTES) {
                    column.columnType = ColumnType.TEXT;
                }
                if (column.maxLength > 1 && column.maxLength <= CHAR_DEFAULT_MAX_BYTES) {
                    column.columnType = ColumnType.CHAR;
                }

            } else {
                column.maxLength = CHAR_DEFAULT_MAX_BYTES;
                column.columnType = ColumnType.CHAR;
            }

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
