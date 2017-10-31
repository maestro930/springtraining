package priv.rdo.sdaspring.task07;

public class ErrorDetail {
    private String field;
    private String message;
    private Object rejectedValue;

    public ErrorDetail(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}
