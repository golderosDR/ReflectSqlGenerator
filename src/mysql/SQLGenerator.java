package mysql;

import mappers.FieldMapper;
import models.Column;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static constants.Const.*;

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

        int primaryKeyCount = 0;
        String primaryKeyName = "";
        List<String> columnStrings = new ArrayList<>();
        List<String> uniqueNameList = new ArrayList<>();

        for (Column column : columns) {
            if (column.isPrimaryKey) {
                primaryKeyCount++;
                primaryKeyName = column.name;
            }
            if (column.isUnique) {
                uniqueNameList.add(column.name);
            }
            columnStrings.add(column.toString());
        }
        if (primaryKeyCount > 1) throw new RuntimeException("Не может быть более одного поля с аннотацией @PrimaryKey");

        StringBuilder footer = new StringBuilder();
        if (primaryKeyCount == 1) {
            footer.append(String.format(STRING_PRIMARY_KEY, primaryKeyName));
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
