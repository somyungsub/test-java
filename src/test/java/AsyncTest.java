import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AsyncTest {

  @Test
  public void test1() {
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

    System.out.println("Test2");
    System.out.println("Thread.currentThread().getName()2 = " + Thread.currentThread().getName());
  }

  @Test
  public void test2(){
    Function<String, String> function = (String s) -> s;

    CompletableFuture<String> test = CompletableFuture.supplyAsync(() -> "Test1")
        .thenApply(s -> s + ", ^^:")
        .thenCompose(future -> CompletableFuture.supplyAsync(
            () -> {
              for (int i = 0; i < 5000; i++) {
                System.out.println("function.apply(future) = " + function.apply(future));
                System.out.println("Thread 1 = " + Thread.currentThread().getName());
              }
              return "complete";
            }
        ));

    for (int i = 0; i < 5000; i++) {
      System.out.println("i : " + i);
      System.out.println("Thread 2 = " + Thread.currentThread().getName());
    }
  }

  @Test
  public void test3() throws ExecutionException, InterruptedException {
    Function<String, String> function = (String s) -> s;

    CompletableFuture<String> test = CompletableFuture.supplyAsync(() -> "Test1")
        .thenApply(s -> s + ", ^^:")
        .thenCompose(future -> CompletableFuture.supplyAsync(
            () -> {
              for (int i = 0; i < 5000; i++) {
                System.out.println("function.apply(future) = " + function.apply(future));
              }
              return "complete";
            }
        ));

    System.out.println("기다림 = " + test.get());  // 완료 대기
    System.out.println("Test2");
  }

  @Test
  public void test4() throws ExecutionException, InterruptedException {
    Function<String, String> function = (String s) -> s;

    CompletableFuture<String> test = CompletableFuture.supplyAsync(() -> "Test1")
        .thenApply(s -> s + ", ^^:")
        .thenCompose(future -> CompletableFuture.supplyAsync(
            () -> {
              for (int i = 0; i < 5000; i++) {
                System.out.println("function.apply(future) = " + function.apply(future));
              }
              return "complete";
            }
        ));

    System.out.println("Test2");
    System.out.println("기다림 = " + test.get());  // 완료 대기
  }

  @Test
  public void test5() throws ExecutionException, InterruptedException {
    Function<String, String> function = (String s) -> s;

    CompletableFuture<String> test = CompletableFuture.supplyAsync(() -> "Test1")
        .thenApply(s -> s + ", ^^:")
        .thenCompose(future -> CompletableFuture.supplyAsync(
            () -> {
              for (int i = 0; i < 10_000; i++) {
                System.out.println("function.apply(future) = " + function.apply(future));
              }
              return "complete";
            }
        ));

    IntStream.range(0, 5_000).forEach(System.out::println);   // 좀 걸리는 작업
    System.out.println("기다림 = " + test.get());              // 완료 대기
  }

  @Test
  public void test6() throws ExecutionException, InterruptedException {
    Function<String, String> function = (String s) -> s;

    CompletableFuture<String> test = CompletableFuture.supplyAsync(() -> "Test1")
        .thenApply(s -> s + ", ^^:")
        .thenCompose(future -> CompletableFuture.supplyAsync(
            () -> {
              for (int i = 0; i < 10_000; i++) {
                System.out.println("function.apply(future) = " + function.apply(future));
              }
              return "complete";
            }
        ));

    System.out.println("기다림 = " + test.get());              // 완료 대기
    IntStream.range(0, 5_000).forEach(System.out::println);   // 좀 걸리는 작업
  }

  private void timer(long millisecond) {
    try {
      Thread.sleep(millisecond);
      System.out.println("sleep 완료");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
