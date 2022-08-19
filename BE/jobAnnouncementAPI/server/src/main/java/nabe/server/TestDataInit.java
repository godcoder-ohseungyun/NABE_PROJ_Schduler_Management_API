package nabe.server;

import lombok.RequiredArgsConstructor;
import nabe.server.domain.SearchHistory;
import nabe.server.repository.SearchHistoryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final SearchHistoryRepository searchHistoryRepository;

    @PostConstruct
    public void init() {
        SearchHistory history1 = SearchHistory.createCompactSearchHistory(1L, "101000", "84", "1", "8");
        SearchHistory history2 = SearchHistory.createCompactSearchHistory(1L, "101000", "84", "2", "8");
        SearchHistory history3 = SearchHistory.createCompactSearchHistory(1L, "102000", "84", "3", "8");
        SearchHistory history4 = SearchHistory.createCompactSearchHistory(1L, "102000", "72", "1", "7");
        SearchHistory history5 = SearchHistory.createCompactSearchHistory(1L, "102190", "72", "1", "7");
        SearchHistory history6 = SearchHistory.createCompactSearchHistory(1L, "102190", "60", "2", "7");
        SearchHistory history7 = SearchHistory.createCompactSearchHistory(1L, "101010", "60", "3", "0");

        searchHistoryRepository.saveSearchHistory(history1);
        searchHistoryRepository.saveSearchHistory(history2);
        searchHistoryRepository.saveSearchHistory(history3);
        searchHistoryRepository.saveSearchHistory(history4);
        searchHistoryRepository.saveSearchHistory(history5);
        searchHistoryRepository.saveSearchHistory(history6);
        searchHistoryRepository.saveSearchHistory(history7);
    }
}
