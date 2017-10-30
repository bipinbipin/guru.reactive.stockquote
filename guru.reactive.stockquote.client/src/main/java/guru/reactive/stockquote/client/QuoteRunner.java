package guru.reactive.stockquote.client;

import guru.reactive.stockquote.client.client.StockQuoteClient;
import guru.reactive.stockquote.client.domain.Quote;
import guru.reactive.stockquote.client.repositories.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteRunner(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Quote> quoteFlux = quoteRepository.findWithTailableCursorBy();

        System.out.println("=============================== RUNNER =============================");
        Disposable disposable = quoteFlux.subscribe(quote -> {
            System.out.println("*#*#*#*#*#*#*#*#*#*#*#*#*#* Id: " + quote.getId());
        });

        disposable.dispose();
    }
}
