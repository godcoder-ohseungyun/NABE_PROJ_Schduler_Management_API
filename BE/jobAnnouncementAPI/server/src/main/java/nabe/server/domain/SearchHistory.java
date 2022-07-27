package nabe.server.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHistory {

    @Id @GeneratedValue
    private Long id;

    private Long memberId;

    // 기업명, 공고명, 업직종 키워드, 직무내용에서 검색하고자 하는 검색어
    private String keywords;

    // 근무지/지역 조건
    private String loc_cd;

    // 직무코드
    private String job_cd;

    // 근무형태/고용형태 조건(1-15)
    private String job_type;

    // 학력 조건(0-9)
    private String edu_lv;

    // 등록일
    private String published;

    // 마감일
    // today : 오늘 마감 공고
    // tomorrow : 내일 마감 공고
    // - : 마감된 공고
    private String deadline;

    // Setter 대신 생성 메소드 사용
    public static SearchHistory createSearchHistory(Long memberId, String keywords, String loc_cd, String job_cd, String job_type, String edu_lv, String published, String deadline) {
        SearchHistory searchHistory = new SearchHistory();

        searchHistory.memberId = memberId;
        searchHistory.keywords = keywords;
        searchHistory.loc_cd = loc_cd;
        searchHistory.job_cd = job_cd;
        searchHistory.job_type = job_type;
        searchHistory.edu_lv = edu_lv;
        searchHistory.published = published;
        searchHistory.deadline = deadline;

        return searchHistory;
    }
}
