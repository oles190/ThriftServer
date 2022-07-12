package com.example.demo23.exception.handler;

public class ErrorBook {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ErrorBook(String error, long time) {
        this.error = error;
        this.time = time;
    }

    private long time;

}
