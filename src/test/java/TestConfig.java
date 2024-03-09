import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.diba.beneficiary") // Adjust the package to scan for your components
public class TestConfig {
    @Bean
    @Scope("singleton")
    EventStoreDBClient eventStoreDBClient(@Value("${test.esdb.connectionstring}") String connectionString) {
        try {
            EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(connectionString);
            return EventStoreDBClient.create(settings);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}