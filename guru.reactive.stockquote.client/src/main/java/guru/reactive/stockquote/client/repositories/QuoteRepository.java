package guru.reactive.stockquote.client.repositories;

import guru.reactive.stockquote.client.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
}
