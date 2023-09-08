package exceptions;

public class InvalidAnnotationsSetException extends RuntimeException{
    public static final String WRONG_MAX_LENGTH_ANNOTATION_USE = "Аннотация @MaxLength не может быть использована с нестроковыми типами.";
    public static final String PRIMARY_KEY_WITHOUT_NOT_NULL = "Аннотация @PrimaryKey не может быть использована без аннотации @NotNull.";
   public static final String PRIMARY_KEY_COUNT_MORE_THAN_2 =  "Не может быть более одного поля с аннотацией @PrimaryKey";
    public static final String AUTOINCREMENT_ANNOTATION_INVALID_USE =  "Аннотация @AUtoIncrement может быть использована только с целочисленными типами.";
    public InvalidAnnotationsSetException(String message) {
        super(message);
    }
}
