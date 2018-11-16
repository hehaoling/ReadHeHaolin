package com.example.administrator.readhehaolin;
import java.util.List;

public class Person {
    private String reason;
    private Results result;
    private int error_code;

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Results getResult() {
        return result;
    }

    public void setResult(Results result) {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "text[reason=" + reason +",results="+result+",error_code="
                +error_code+"]";
    }
}