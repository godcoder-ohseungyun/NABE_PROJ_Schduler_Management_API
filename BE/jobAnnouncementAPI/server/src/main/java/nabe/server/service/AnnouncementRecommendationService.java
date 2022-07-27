package nabe.server.service;

import lombok.RequiredArgsConstructor;
import nabe.server.domain.SearchHistory;
import nabe.server.repository.SearchHistoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementRecommendationService {
    private final SearchHistoryRepository searchHistoryRepository;

    public void saveSearchHistory(SearchHistory searchHistory) {
        searchHistoryRepository.saveSearchHistory(searchHistory);
    }
}
