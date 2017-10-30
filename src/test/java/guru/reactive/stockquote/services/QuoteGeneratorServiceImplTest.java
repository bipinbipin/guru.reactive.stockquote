package guru.reactive.stockquote.services;

import guru.reactive.stockquote.model.Quote;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class QuoteGeneratorServiceImplTest {

    QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void fetchQuoteStream() throws Exception {

        // get Flux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));

        quoteFlux.take(22000)
                .subscribe(System.out::println);

        // without these the test will end before the stream emits anything
        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);

    }

    @Test
    public void fetchQuoteStreamCountDown() throws Exception {
        // this method is much more desirable than the Thread.sleep()

        // get Flux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

        // subscriber lambda
        Consumer<Quote> println = System.out::println;

        // error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some Error Occurred");

        // set Countdown latch to 1
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // runnable called upon complete, count down latch
        Runnable allDone = () -> countDownLatch.countDown();

        quoteFlux.take(10)
                .subscribe(println, errorHandler, allDone);

        countDownLatch.await();
    }
}
