import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class AsyncTest {
  public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

    Function<String, String> function = (String s) -> s;

    CompletableFuture<String> test = CompletableFuture.supplyAsync(() -> "Test1")
        .thenApply(s -> s + ", ^^:")
        .thenCompose(future -> CompletableFuture.supplyAsync(
            () -> {
              for (int i = 0; i < 5000; i++) {
                System.out.println("function.apply(future) = " + function.apply(future));
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
              }
              return "complete";
            }
        ));

//    System.out.println("test = " + test.get());
//    for (int i = 0; i < 10000; i++) {
//      System.out.println("i : " + i);
//    }

    System.out.println("test = " + test.get());
    System.out.println("Test2");
    System.out.println("Thread.currentThread().getName()2 = " + Thread.currentThread().getName());

//    System.out.println("async.complete(function) = " + async.complete(function));

  }
}
