package com.example;

public interface TaskBehavior {

    Status getStatus();

    String getMessage();

    String getName();

    void setMessage(String message);

    void setStatus(Status status);

}
