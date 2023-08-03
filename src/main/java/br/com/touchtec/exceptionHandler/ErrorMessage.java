package br.com.touchtec.exceptionHandler;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ErrorMessage {

    private final String message;
    private final String error;
    private final String stack;

    public ErrorMessage(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        this.message = e.getMessage();
        this.error = e.toString();
        this.stack = baos.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getStack() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !this.getClass().equals(o.getClass())) {
            return false;
        }

        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(this.message, that.message) &&
                Objects.equals(this.error, that.error) &&
                Objects.equals(this.stack, that.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.message, this.error, this.stack);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
