package com.example;

public class TaskThread implements Runnable {

    private final TaskBehavior tt;
    private final Long timeout;

    private final Long MAX_TIMEOUT = 10L;

    public TaskThread(TaskBehavior tt, Long timeout) {
        this.tt = tt;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        final String currentThread = Thread.currentThread().getName();
        try {
            System.out.println("Please wait " + timeout + " seconds, Thread " + currentThread + " executes...");
            if (timeout.equals(MAX_TIMEOUT)) {
                throw new RuntimeException("Waiting limit exceeded!");
            }
            Thread.sleep(10);
            tt.setStatus(Status.OK);
            tt.setMessage("Thread " + currentThread + " executed successfully!");
        } catch (RuntimeException | InterruptedException e) {
            tt.setStatus(Status.FAILED);
            tt.setMessage("An error occurred while executing the " + currentThread + " Thread:\n" + e.getMessage());
        }
    }

}
