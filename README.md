### **💫 시작하기 전에**

---

이 페이지는 “취업 스케줄러: 나비(Me-Be)” 의 서비스 서버 중 `본인이 개발을 담당`하고 있는 `“스케줄러 관리 API 서버(Scheduler Management Server)”`에 `해당`하는 페이지 입니다.

이 프로젝트는 대학 동료들과 `완성도 있는 개발 경험을 위해` 모인, 실제 사용자 유치를 목표로 `자체 프로젝트`를 개발하기를 지향하는 `8인 스터디` 에서 `함께 개발`하였습니다.

- 협업 및 커뮤니케이션 경험
- 다양한 문제 경험을 통한 인사이트 확장과 대처 자세 및 역량
- 스택간 유기적인 상호작용에 대한 이해


</br></br>

### **💡목차**

---

1. 팀 프로젝트 소개
2. 전체 시스템 소개 및 서버별 기능
3. `스케줄러 관리 API 시스템 클래스 다이어그램`
4. `스케줄러 관리 API 설계 및 주요 도전 과제 해결 과정 문서화`
5. 협업 방식

</br></br>

### **💡팀 프로젝트 소개**

---

</br></br>

### **취업 스케줄러: 나비(Me-Be) `런칭예정` 2022.09**

`진행중` 2021.07 - 현재

`“무수히 많은 취업 공고나 스팩 관련 일정들 누가 자동으로 내 스케줄러에 입력해주면 안되나.."` 라는 아이디어에서 출발한  `“취업 스케줄러: 나비(me-be)”`입니다.

`기존 스케줄러`에 `취업 공고 및 스팩 관련 일정 조회 및 추천 기능`과 `해당 일정 구독 시 자동 일정 추가 기능`을 더한 스케줄러 입니다.

또한, 드래그 앤 드롭 방식으로 더욱 `간편한 일정 CRUD 기능`과 `웹과 앱 연동 기능`을 지원합니다.

</br>

💯**교내 S/W 아이디어 대회에서 금상을 수상**하여 **대학에 비용 지원**을 받아 제작되고 있으며, **2022.09 말 런칭**을 앞두고 있습니다.

💯**지속적인 유지보수**를 목표로 하고있는 `저희 팀 핵심 프로젝트` 입니다.

</br>

⭐**담당 업무**

- **[BE] Scheduler Management Server API 개발 및 배포(개인 일정 CRUD , 구독 일정 자동 CRD)**

**사용 기술:** `Spring boot` , `Spring Data JPA` , `Docker` ,  `AWS ec2` , `AWS RDS` , `Git Actions CI/CD`

- **[BE] 데일리 스크럼 및 스프린트 리뷰 리드**

</br></br>

### **💡전체 시스템 소개 및 서버별 기능**

---

- 장애 격리를 통한 대응력 , 독립 배포 및 개발을 위해 REST 제약 조건을 준수를 기반한 `MSA 구조`로 개발 되었습니다.
- 웹 서비스는 전체적인 트래픽 감소와 렌더링 효율성을 위해 `SPA 구조`로 개발 되었습니다. (vue.js web server가 API GATEWAY의 역할을 포함하고 있습니다.)

![https://user-images.githubusercontent.com/68331041/190093225-b0a2a9e8-aa10-4771-8b5e-a3ff5ef2ca95.png](https://user-images.githubusercontent.com/68331041/190093225-b0a2a9e8-aa10-4771-8b5e-a3ff5ef2ca95.png)
</br>

**회원 관리 서비스**

- JWT 토큰을 이용한 인증/인가
- Oauth2.0 회원가입 기능

</br>
`담당중` **스케줄러 관리 서비스**

- 채용 공고 및 스팩 관련 일정 구독 기능
- 구독시 회원 스케줄러에 자동 CRD 기능
- 개인 스케줄 CRUD기능
- 일정 태그 기능과 알림설정 기능 (예정)
- 로지스틱 회귀 분석 및 클러스터링을 이용한 자격증 취득 소요기간 예측 기능 (예정)
</br>

`일부 담당중`**채용 공고 제공 서비스**

- Open API 채용 공고 데이터 수집 및 제공 기능
- 사용자 검색 로그 분석을 통한 공고 추천 기능
</br>

**추천 일정 수집 서비스**

- 크롤링을 통한 대외활동 및 자격증 시험 일정 수집 분류(개발 중)

</br></br>

### **💡스케줄러 관리 API 클래스 다이어그램**

---

</br></br>

### **💡설계 및 주요 도전 과제 해결 과정**

---

`링크를 통해 노션에 정리된 문서를 조회하실 수 있습니다.`
</br>

⭐`정확한 개발과 협업`**을 위해** `도메인 설계 과정과 API명세서`**를 작성했습니다.**

[요구분석 및 ERD 설계 과정(feat. 릴레이션 설계)](https://www.notion.so/ERD-feat-dbf1cc2b6ac7480bbcc6b5de2f2b32f0)

[Scheduler Server API 명세서](https://www.notion.so/Scheduler-Server-API-0eff9253bc7d4362bbaeaa1dae9f3a7a)

![https://user-images.githubusercontent.com/68331041/190093368-34002372-ffe5-4aa6-9f1d-9cc039cad900.png](https://user-images.githubusercontent.com/68331041/190093368-34002372-ffe5-4aa6-9f1d-9cc039cad900.png)

</br>

⭐`레거시 코드를 유지보수가 용이하게 개선`**하고** `주요 문제 해결 과정을 문서화` **했습니다.**

[OOP를 이용해 도메인과 검증 로직을 분리하기(feat. 래퍼클래스&디자인 패턴 등)](https://www.notion.so/OOP-feat-4e0fedd161944108848b5693c75450b5)

[사용자 정의 예외를 이용한 API 응답 세분화(feat. Spring ExceptionResolver)](https://www.notion.so/API-feat-Spring-ExceptionResolver-0e34aa4024124318a43869f2ae3117f3)

[AOP를 이용한 토큰 유효성 확인 로직 분리(feat. MSA 서비스에서 JWT를 이용한 인증/인가 아키텍쳐 구현)](https://www.notion.so/AOP-feat-MSA-JWT-a5fd488650d44a94a4c553b74a663174)

[[INFRA] GitHub Actions 을 이용한 CI/CD 배포 파이프라인 구축 (feat. DOCKER , AWS RDS)](https://www.notion.so/INFRA-GitHub-Actions-CI-CD-feat-DOCKER-AWS-RDS-e5e41eaed8f04e2c829cfbed104531e4)

![https://user-images.githubusercontent.com/68331041/190093439-40a938d2-dc2d-4fb9-a834-8c3c05d4634c.png](https://user-images.githubusercontent.com/68331041/190093439-40a938d2-dc2d-4fb9-a834-8c3c05d4634c.png)

</br>

⭐`함께 자라기`**를 위해 팀에** `문서화를 통해 지식공유` **했습니다**.

[Spring Data JPA를 도입하자!](https://www.notion.so/Spring-Data-JPA-e75c6f691fa8468897d22c9aadb4ad7e)

[REST API 정확하게 설계하기(feat.Self-descriptive messages 와 HATEOAS)](https://www.notion.so/REST-API-feat-Self-descriptive-messages-HATEOAS-070913d4c8664f9cbcd1d05d5b0d4b06)

[CORS 이슈 발생! 정의와 동작 원리를 알아보자](https://www.notion.so/CORS-abace1a36cda420cbceebd997a5e752c)

![https://user-images.githubusercontent.com/68331041/190093516-dc97183e-95c5-4cff-a621-7497de1020c8.png](https://user-images.githubusercontent.com/68331041/190093516-dc97183e-95c5-4cff-a621-7497de1020c8.png)

</br>

⭐`ML , 멀티스레딩 등 다양한 요소 기술을 학습`**하고 문제 해결에 적용해 보았습니다.**

[[ML] 로지스틱 회귀 분석 및 클러스터링을 이용한 자격증 취득 소요기간 예측 모듈 설계](https://www.notion.so/ML-a92a2c6fe1c24bb29250b6beb7bd0a30)

[[ML] 멀티 스레드를 이용해 Open API 수집 속도 향상을 통한 ML 수행 속도 개선(feat. 동기 → 비동기)](https://www.notion.so/ML-Open-API-ML-feat-80c53558d1b04d6e9828610a7d833b28)

![https://user-images.githubusercontent.com/68331041/190093590-b829aae1-51c4-47f8-ac89-1756af29f714.png](https://user-images.githubusercontent.com/68331041/190093590-b829aae1-51c4-47f8-ac89-1756af29f714.png)

</br>

⭐ **본 프로젝트를 진행하며** `깨닫고 개선한 점` **입니다.**

- 효율적인 협업을 위한 개발자의 글쓰기와 말하기 역량 개선
- 다양한 기업 기술 블로그 참고하여 문제해결 인사이트 확장
- 리펙토링과 클린코드 중심의 개발을 통한 효율적인 개선
- 추상 최적화 보단 기본기를 회고하여 올바른 성장
- 문서화를 통한 기록과 공유를 습관화하여 효과적인 발전

</br></br>

### **💡협업 방식**

---

부족한 개발 경험으로 인한 `문제를 초기대응` 하기위해 작은 주기로 개발 및 검토가 가능하도록 `스크럼 개발 방법론`을 도입했습니다.

- 주 1회 스프린트 회의
- 매일 15분씩 **Daily Scrum**를 가지고 있습니다 → `지식 공유, 도전과제 해결과정 공유 , 스프린트 진행 상황 점검`

![https://user-images.githubusercontent.com/68331041/190093649-7f5a93bf-4ce7-4f9d-98b2-5c4248a1372f.png](https://user-images.githubusercontent.com/68331041/190093649-7f5a93bf-4ce7-4f9d-98b2-5c4248a1372f.png)
