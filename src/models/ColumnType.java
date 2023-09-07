package models;

import constants.Const;

public enum ColumnType {
    BOOL,
    INT,
    BIGINT,
    FLOAT,
    DOUBLE,
    VARCHAR,
    CHAR,
    TEXT,
    DATE,
    DATETIME;

    public String toString() {
        return switch (this) {
            case BOOL -> Const.STRING_BOOL;
            case INT -> Const.STRING_INT;
            case BIGINT -> Const.STRING_BIGINT;
            case FLOAT -> Const.STRING_FLOAT;
            case DOUBLE -> Const.STRING_DOUBLE;
            case CHAR, VARCHAR -> Const.STRING_VARCHAR;
            case TEXT -> Const.STRING_TEXT;
            case DATE -> Const.STRING_DATE;
            case DATETIME -> Const.STRING_DATETIME;
        };

    }
}