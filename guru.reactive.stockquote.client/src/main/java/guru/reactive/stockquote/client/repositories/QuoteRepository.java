package guru.reactive.stockquote.client.repositories;

import guru.reactive.stockquote.client.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {

    @Tailable
    Flux<Quote> findWithTailableCursorBy();
}
