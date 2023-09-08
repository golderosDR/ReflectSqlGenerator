package models;


import constants.Const;

public class Column {
    public String name;
    public ColumnType columnType;
    public boolean isAutoIncrement;
    public boolean isPrimaryKey;
    public boolean isNotNull;
    public boolean isUnique;
    public boolean hasMaxLength;
    public boolean hasColumnName;
    public int maxLength;

    public Column() {
    }



    @Override
    public  String toString() {
        StringBuilder result = new StringBuilder();

        result.append(name)
            .append(columnType.toString());
            if (columnType == ColumnType.VARCHAR || columnType == ColumnType.CHAR) {
            result.append("(");
            result.append(maxLength);
            result.append(")");
            }
        if (isAutoIncrement) {
            result.append(Const.STRING_AUTO_INCREMENT);
        }
        if (isNotNull) {
            result.append(Const.STRING_NOT_NULL);
        }

        return result.toString();


    }



}
