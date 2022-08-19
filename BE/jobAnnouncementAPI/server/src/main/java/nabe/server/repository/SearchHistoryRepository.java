package nabe.server.repository;

import lombok.RequiredArgsConstructor;
import nabe.server.domain.SearchHistory;
import nabe.server.repository.mapping.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Transactional
public class SearchHistoryRepository {

    private final EntityManager em;
    private Map<String, List> searchConditions = new HashMap<>();

    public void saveSearchHistory(SearchHistory searchHistory) {
        em.persist(searchHistory);
    }

    // 가장 많이 사용된 검색 조건 조회
    public Map<String, List> findMostUsedSearchConditions() {

        // 기업명, 공고명, 업직종 키워드, 직무내용에서 검색하고자 하는 검색어
        List<KeyWordsMapping> keywords = em.createNativeQuery("SELECT keywords FROM search_history GROUP BY(keywords) HAVING COUNT(*) = (SELECT TOP(1) COUNT(*) FROM search_history GROUP BY(keywords) ORDER BY COUNT(*) DESC)")
                .getResultList();

        searchConditions.put("keywords", keywords);

        // 근무지/지역 조건
        List<LocCodeMapping> loc_cd = em.createNativeQuery("SELECT loc_cd FROM search_history GROUP BY(loc_cd) HAVING COUNT(*) = (SELECT TOP(1) COUNT(*) FROM search_history GROUP BY(loc_cd) ORDER BY COUNT(*) DESC)")
                .getResultList();

        searchConditions.put("loc_cd", loc_cd);

        // 직무코드
        List<JobCodeMapping> job_cd = em.createNativeQuery("SELECT job_cd FROM search_history GROUP BY(job_cd) HAVING COUNT(*) = (SELECT TOP(1) COUNT(*) FROM search_history GROUP BY(job_cd) ORDER BY COUNT(*) DESC)")
                .getResultList();

        searchConditions.put("job_cd", job_cd);

        // 근무형태/고용형태 조건
        List<JobTypeMapping> job_type = em.createNativeQuery("SELECT job_type FROM search_history GROUP BY(job_type) HAVING COUNT(*) = (SELECT TOP(1) COUNT(*) FROM search_history GROUP BY(job_type) ORDER BY COUNT(*) DESC)")
                .getResultList();

        searchConditions.put("job_type", job_type);

        // 학력조건
        List<EduLvMapping> edu_lv = em.createNativeQuery("SELECT edu_lv FROM search_history GROUP BY(edu_lv) HAVING COUNT(*) = (SELECT TOP(1) COUNT(*) FROM search_history GROUP BY(edu_lv) ORDER BY COUNT(*) DESC)")
                .getResultList();

        searchConditions.put("edu_lv", edu_lv);

        return searchConditions;
    }
}
