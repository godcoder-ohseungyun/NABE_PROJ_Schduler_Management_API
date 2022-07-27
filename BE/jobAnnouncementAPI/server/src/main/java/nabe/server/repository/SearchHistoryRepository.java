package nabe.server.repository;

import lombok.RequiredArgsConstructor;
import nabe.server.domain.SearchHistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class SearchHistoryRepository {

    private final EntityManager em;

    public void saveSearchHistory(SearchHistory searchHistory) {
        em.persist(searchHistory);
    }
}
