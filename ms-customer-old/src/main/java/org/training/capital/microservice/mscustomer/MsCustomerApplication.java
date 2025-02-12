package org.training.capital.microservice.mscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

@SpringBootApplication
@EnableAsync
// @EnableMongoRepositories
public class MsCustomerApplication {

    @Bean
    public ExecutorService myThreadPool(){
        return Executors.newFixedThreadPool(10);
    }


    @Async("myThreadPool")
    public Future<String> myAsyncMethod() {

        CompletableFuture<String> objectCompletableFutureLoc = new CompletableFuture<>();
        objectCompletableFutureLoc.complete("osman");
        System.out.println("I am running : " + Thread.currentThread().getName());
        try {
         Thread.sleep(5_000);
        } catch (Exception exp) {
        }
        System.out.println("Finished : " + Thread.currentThread().getName());

        return objectCompletableFutureLoc;
    }

    public static void main(String[] args) {
        SpringApplication.run(MsCustomerApplication.class,
                              args);
//        Runnable runnableLoc;
//        Callable callableLoc;
//
//        ExecutorService executorServiceLoc = Executors.newFixedThreadPool(10);
//        executorServiceLoc.execute(()-> System.out.println("deneme"));
//        Future<String> submitLoc = executorServiceLoc.submit(() -> "osman");
//        try {
//            System.out.println("I am cont running");
//            submitLoc.
//            String sLoc = submitLoc.get(100,TimeUnit.MILLISECONDS);
//
//        } catch (InterruptedException eParam) {
//            throw new RuntimeException(eParam);
//        } catch (ExecutionException eParam) {
//            throw new RuntimeException(eParam);
//        } catch (TimeoutException eParam) {
//            throw new RuntimeException(eParam);
//        }
    }

}
