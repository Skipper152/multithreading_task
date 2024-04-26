package com.example;

import java.util.List;
import java.util.concurrent.*;

public class TasksRunner {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private static final BlockingQueue<TaskBehavior> tasksQueue = new ArrayBlockingQueue<>(3, true,
            List.of(new Task("first task"),
                    new Task("second task"),
                    new Task("third task")));

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        for (TaskBehavior task : tasksQueue) {
            threadPool.submit(new TaskThread(task, (long) (Math.random() * 15L)));
        }

        threadPool.shutdown();
        threadPool.awaitTermination(3L, TimeUnit.SECONDS);

        for (TaskBehavior task : tasksQueue) {
            System.out.println(task.getName());
            System.out.println(task.getStatus());
            System.out.println(task.getMessage());
            System.out.println();
        }

        System.out.println("main end");
    }

}
