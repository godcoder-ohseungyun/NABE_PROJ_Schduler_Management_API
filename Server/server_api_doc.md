## SC

~~~java
//해당 기간 일정 데이터 요청=====================================================
[GET] /api/user_sc?s_date=2020-03-01 & e_date=2020-04-31  
/**
parameters: 

sdate , edate = 2020-03-01 
user_sc_list , user_anno_sc , user_lc_sc = true or false
*/

//채용공고 요청================================================================
[GET] /api/an?keywords=kakao
/**
기업명 , 근무지 , 학력조건 , 검색 결과 수 , 마감일 , 직무 코드 , 고용조건 등
*/
    
//특별관리일정 추가(목표)=======================================================
[POST] api/user_goals?name = Opic & deadline = 2023-06-02
/**
만약 어학,자격등 일정추천기능 카테고리의 경우 일정 반환
*/
    
    
//타겟 기업 추가===============================================================
[POST] api/user_target
{
    "target" : "kakao"
}

//특정 타겟기업 관련 뉴스 제공
[GET] api/user_target/news?target=kakao

//특정 타겟기업 스팩정보 제공
[GET] api/user_target?target=kakao

//해당 일정 추가================================================================
[POST] /api/user_sc/ps
{
    "date" : "2022-04-29",
    "s_time" : "08:30",
    "e_time" : "09:00"
}

[POST] /api/user_sc/an
{
    "date" : "2022-04-29",
    "anno_id" : "공고번호",
    "Announcement_url" : "공고 url",
    "Announcement_name" : "공고명",
    "detail": "그외 표시하고싶은 정보"
}    

[POST] /api/user_sc/lc
{
    "date" : "2022-04-29",
    "goal_name": "Opic",
    "type" : "v" //v는 신청일 r은 결과발표일
}

//알림센터 데이터 요청===========================================================
[GET] /api/observer
/**
1. 오늘 기준으로 타겟공고,특별일정 기준으로 파악후 일정 기간 이하 남았으면 알림 반환
2. 금일 기준으로 타겟기업 관련 공고가 새로 나왔으면 알림
*/
~~~

