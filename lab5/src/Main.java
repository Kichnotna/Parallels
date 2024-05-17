import com.example.MySemaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final int THREADS = 4;
    private static final int COUNT = 2;

    public static void main(String[] args) {
        System.out.println("Default");
        runTask(new Semaphore(COUNT));
        System.out.println("My");
        runTask(new MySemaphore(COUNT));
    }

    private static void runTask(Semaphore semaphore) {
        ExecutorService es = Executors.newFixedThreadPool(THREADS);

        List<Callable<String>> tasks = new ArrayList<>();
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            tasks.add(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " create");

                System.out.println(threadName + " acquire");
                semaphore.acquire();
                System.out.println(threadName + " working...");
                Thread.sleep(1000);

                System.out.println(threadName + " release");
                semaphore.release();

                return threadName + " success";

            });
        }

        try {
            results = es.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        es.shutdown();
    }
}