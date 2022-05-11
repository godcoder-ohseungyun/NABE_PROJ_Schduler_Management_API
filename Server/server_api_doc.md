### 사람인 url 분석

~~~
https://oapi.saramin.co.kr/job-search?access-key=jSzmOzrqgCNGWwUGJbBVBxqIPLxwXRz2ZfUGUtAlQOyUmmr5NTka&keywords=%EC%9B%B9+%EA%B0%9C%EB%B0%9C%EC%9E%90&bbs_gb=0
 고용형태       전체라는 의미   지역코드   업종코드
&job_type=1 &edu_lv= &loc_cd=101010 &job_mid_cd=2
~~~

지역코드: https://oapi.saramin.co.kr/guide/code-table2  loc_cd

직무코드 : https://oapi.saramin.co.kr/guide/code-table5  job_mid_cd

근무형태 코드: https://oapi.saramin.co.kr/guide/code-table1   job_type

학력코드: https://oapi.saramin.co.kr/guide/code-table1  edu_lv



### Url 명세

~~~
[GET]  /api/announcements
-----------------------------------------------------------------------------------
[GET,POST] /api/announcements/user-targets

[DELETE] /api/announcements/user-targets/{targetId}
-----------------------------------------------------------------------------------
[GET,POST,PUT,DELETE] /api/user-schedules
-----------------------------------------------------------------------------------
[GET,POST] /api/special-schedules

[DELETE] /api/special-schedules/{targetId}
~~~

https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods



## DFD




