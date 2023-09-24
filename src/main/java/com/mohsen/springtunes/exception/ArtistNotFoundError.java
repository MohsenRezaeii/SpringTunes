package com.mohsen.springtunes.exception;

import java.time.LocalTime;

public class ArtistNotFoundError {
    private int status;
    private String message;
    private LocalTime timeStamp;

    public ArtistNotFoundError() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
