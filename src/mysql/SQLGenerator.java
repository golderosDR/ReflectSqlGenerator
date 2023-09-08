package mysql;

import exceptions.InvalidAnnotationsSetException;
import mappers.FieldMapper;
import models.Column;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static constants.Const.*;
import static exceptions.InvalidAnnotationsSetException.PRIMARY_KEY_COUNT_MORE_THAN_2;

public class SQLGenerator {
    public SQLGenerator() {
    }

    public <T> String generateTable(Class<T> tableClass) {
        String result;
        try {
            Constructor<T> constructor = tableClass.getConstructor();
            T tObj = constructor.newInstance();
            result = createTable(tObj);
            return result;
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T> String createTable(T tObj) {
        Class<T> tClass = (Class<T>) tObj.getClass();
        Field[] fields = tClass.getDeclaredFields();
        StringBuilder result = new StringBuilder();

        List<Column> columns = new ArrayList<>();
        for (Field field : fields) {
            Column column = FieldMapper.fromField(field);
            if (column != null) {
                columns.add(column);
            }
        }
        String header = STRING_CREATE_TABLE + tClass.getSimpleName() + " (" + System.lineSeparator();

        List<String> columnStrings = new ArrayList<>();
        List<String> uniqueNameList = new ArrayList<>();
        List<String> primaryKeyList = new ArrayList<>();

        for (Column column : columns) {
            if (column.isPrimaryKey) {
                primaryKeyList.add(column.name);
            }
            if (column.isUnique) {
                uniqueNameList.add(column.name);
            }
            columnStrings.add(column.toString());
        }
        if (primaryKeyList.size() > 2) throw new InvalidAnnotationsSetException(PRIMARY_KEY_COUNT_MORE_THAN_2);

        StringBuilder footer = new StringBuilder();

        if (!primaryKeyList.isEmpty()) {
            footer.append(String.format(STRING_PRIMARY_KEY, String.join(", ", primaryKeyList)));
        }

        if (!uniqueNameList.isEmpty()) {
            footer.append(String.format(STRING_UNIQUE, String.join(", ", uniqueNameList)));
        }
        footer.append(System.lineSeparator()).append(");");


        result.append(header)
                .append(String.join("," + System.lineSeparator(), columnStrings))
                .append(footer);

        return result.toString();
    }
}
