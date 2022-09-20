### **💫 시작하기 전에**

---

이 페이지는 대학 동료들과 함께 개발한 `“취업 스케줄러: 나비(Me-Be)” 의 책임별 서비스 서버 중 "스케줄러 관리 API (Scheduler Management Server)"`에 해당하는 레포지토리 입니다.

### **💡목차**

---

1. 팀 프로젝트 소개
2. 전체 시스템 소개 및 서버별 기능
3. `[스케줄러 관리 API] 설계 과정 및 주요 도전 과제 해결 과정 로그 문서`
4. 팀 소개 및 협업 방식

</br>
</br>

### 💡팀 프로젝트 소개

---

### **취업 스케줄러: 나비(Me-Be) `2022.09 런칭예정`**

`진행중` 2021.07 - 현재

`“무수히 많은 취업 공고나 스팩 관련 일정들 누가 '자동'으로 내 스케줄러에 입력해주면 안되나.."` 라는 아이디어에서 출발한  프로젝트 입니다.

`기존 스케줄러`에 `취업 공고 및 스팩 관련 일정 조회 및 추천 기능`과 `해당 일정 구독 시 자동 일정 추가 기능` 을 `추가한 스케줄러`로, 서비스에서 제공하는 취업 및 스팩 관련 일정 내용을 `한번의 터치만으로 내 스케줄러에 자동으로 기록` 할 수 있습니다.

드래그 앤 드롭 방식으로 더욱 `간편한 일정 CRUD 기능`과 `웹과 앱 연동 기능`을 지원합니다.

💯 교내 S/W 아이디어 대회에서 금상을 수상하여 대학에 비용 지원을 받아 제작되고 있으며, 2022.09 말 런칭을 앞두고 있습니다.
</br>
</br>

### **💡전체 시스템 소개 및 서버별 기능**

---

- `회원 및 인증 관리 , 스케줄러 관리 , 채용 공고 관리 등 책임별 REST API를 가진 MSA + SPA 구조` 입니다. MSA 아키텍쳐를 통해 FE ,BE ,ML 팀간 서로 다른 개발 주기로 인한 개발 지연 문제를 해결하였습니다.
- 웹 서비스는 전체적인 트래픽 감소와 렌더링 효율성을 위해 SPA 구조로 개발 되었습니다. (vue.js web server가 API GATEWAY의 역할을 포함하고 있습니다.)

![https://user-images.githubusercontent.com/68331041/190093225-b0a2a9e8-aa10-4771-8b5e-a3ff5ef2ca95.png](https://user-images.githubusercontent.com/68331041/190093225-b0a2a9e8-aa10-4771-8b5e-a3ff5ef2ca95.png)

**회원 관리 및 인증/인가 서비스**

- JWT 토큰을 이용한 인증/인가
- Oauth2.0 회원가입 기능

`전체 담당중` **스케줄러 관리 서비스**

- 채용 공고 및 스팩 관련 일정 구독 기능
- 구독시 회원 스케줄러에 자동 CRD 기능
- 개인 스케줄 CRUD기능
- 일정 태그 기능과 알림설정 기능 (예정)
- 로지스틱 회귀 분석 및 클러스터링을 이용한 자격증 취득 소요기간 예측 기능 (예정)

`일부 담당중` **채용 공고 제공 서비스**

- Open API 채용 공고 데이터 수집 및 제공 기능
- 사용자 검색 로그 분석을 통한 공고 추천 기능

**추천 일정 수집 서비스**

- 크롤링을 통한 대외활동 및 자격증 시험 일정 수집 분류(개발 중)
</br>
</br>

### **~~💡[스케줄러 관리 API] 클래스 다이어그램~~**

---

</br>
</br>

### **💡[스케줄러 관리 API] 설계 및 주요 도전 과제 해결 과정**

---

</br>

*링크를 통해 노션에 정리된 문서 전문을 조회하실 수 있습니다.*

</br>

`정확한 개발과 협업`**을 위해** `도메인 설계 과정과 API명세서`**를 작성했습니다.**

- [요구사항 추적 및 버전별 데이터베이스 설계 과정](https://www.notion.so/dbf1cc2b6ac7480bbcc6b5de2f2b32f0)
- [Scheduler Server API 명세서](https://www.notion.so/Scheduler-Server-API-0eff9253bc7d4362bbaeaa1dae9f3a7a)

![https://user-images.githubusercontent.com/68331041/190341529-d120df5d-56d8-435e-ab93-ca2ef3d56981.png](https://user-images.githubusercontent.com/68331041/190341529-d120df5d-56d8-435e-ab93-ca2ef3d56981.png)

</br>

`프로젝트 유지보수가 용이하게 개선`**하고** `주요 도전 과제 해결 과정을 문서화` **했습니다.**

- [전략 패턴과 래퍼 클래스 개념을 이용한 도메인과 검증 로직 최적 분리](https://www.notion.so/4e0fedd161944108848b5693c75450b5)
- [API 사용성 개선: 사용자 정의 예외를 이용한 API 응답 세분화 및 상세화](https://www.notion.so/API-API-0e34aa4024124318a43869f2ae3117f3)
- [AOP를 이용한 Access-Token 유효성 검증 로직(횡단관심사) 분리](https://www.notion.so/AOP-Access-Token-a5fd488650d44a94a4c553b74a663174)
- [민첩한 비즈니스 대응을 위한 GitHub Actions CI/CD 배포 파이프라인 구축](https://www.notion.so/GitHub-Actions-CI-CD-e5e41eaed8f04e2c829cfbed104531e4)
- [Github Action Workflow로 J**scrypt 암,복호화 KEY 숨기기**](https://www.notion.so/Github-Action-Workflow-Jscrypt-KEY-8a2e5b89c2134661b3a099baae7bda65)

![https://user-images.githubusercontent.com/68331041/190343818-7a773ce5-4097-4bd8-bcd6-08280062cebe.png](https://user-images.githubusercontent.com/68331041/190343818-7a773ce5-4097-4bd8-bcd6-08280062cebe.png)

</br>

`함께 자라기`**를 위해 팀에** **학습한 내용을** `문서화하여 지식공유` **했습니다**.

- [Spring Data JPA를 도입하자!](https://www.notion.so/Spring-Data-JPA-e75c6f691fa8468897d22c9aadb4ad7e)
- [REST API 정확하게 설계하기(feat.Self-descriptive messages 와 HATEOAS)](https://www.notion.so/REST-API-feat-Self-descriptive-messages-HATEOAS-070913d4c8664f9cbcd1d05d5b0d4b06)
- [CORS 이슈 발생! 정의와 동작 원리를 알아보자](https://www.notion.so/CORS-abace1a36cda420cbceebd997a5e752c)

![https://user-images.githubusercontent.com/68331041/190093516-dc97183e-95c5-4cff-a621-7497de1020c8.png](https://user-images.githubusercontent.com/68331041/190093516-dc97183e-95c5-4cff-a621-7497de1020c8.png)

</br>

**ML , 멀티스레딩 등 `다양한 요소 기술을 학습`하고 `문제 해결에 적용`해 보았습니다.**

- [멀티 스레딩을 이용한 ML 모듈 수행 속도 개선](https://www.notion.so/ML-ML-feat-80c53558d1b04d6e9828610a7d833b28)
- [로지스틱 회귀 분석 및 클러스터링을 이용한 자격증 취득 소요기간 예측 모듈 설계](https://www.notion.so/ML-a92a2c6fe1c24bb29250b6beb7bd0a30)

![https://user-images.githubusercontent.com/68331041/191207374-8c6ee10b-f408-48b3-af7a-197ccadbdd67.png](https://user-images.githubusercontent.com/68331041/191207374-8c6ee10b-f408-48b3-af7a-197ccadbdd67.png)

</br>

**본 프로젝트를 진행하며 다음을 `깨닫고 개선`할 수 있었습니다.**

- 개발자의 `문서화와 커뮤니케이션 역량을 개선`하고, 명확한 전달을 통한 `원활한 프로젝트 협업`을 이끌어 냈습니다.
- 다양한 `기업 기술 블로그 참고`하여 문제 해결 `인사이트를 얻고`, CS `기본기를 응용하여 명확한 최적화` 했습니다.
- 문서화를 통한 `과정 기록과 공유`를 습관화하여 `함께 자라기를 실천`하고, `리펙토링과 클린코드` 중심의 개발을 통한 `생산성 개선` 했습니다.

</br>
</br>

### **💡**팀 소개 및 협업 방식

---

</br>

***[팀 소개]***

`대학 동료들과` 요구사항 추적, 아키텍처 적절성 판단, 레거시 개선 등 체계적인 프로젝트 `개발 역량`과

협업 역량, 문제 해결 페어 리뷰 등 `개발 자세`를 기르며, `실제 개발을 통한 배움`을 지향하는 10인 `개발 스터디` 입니다.

</br>

***[팀 구성(총 10명)]***

- **[DESIGN]** 3인 - Figma, HTML, CSS, JS
- **[FE]** 2인 - Vue.js , Flutter
- `담당중` **[BE]** 3인 - Java, Spring boot , Spring Date JPA , MSSQL , Docker , AWS , Python , Django
- `담당중` **[ML]** 2인 - Python , sklean , Flask

</br>

***[협업 방식]***

주기적인 페어 리뷰를 통한 부족한 점을 보완 및 발생 가능한 위험을 초기 대응을 위한  `에자일 스크럼` 방식을 사용하고 있으며,

데일리 스크럼 , 스프린트 리뷰를 통해 정확한 작업 검토가 가능해져, 프로젝트를 성공적으로 운영하고 있습니다.

- 주 1회 스프린트 회의: `목표설정` , `도전 과제 해결 과정 및 지식 공유 문서 페어리뷰`
- 매일 20분씩 Daily Scrum: `작업검토 및 전략회의`, `도전과제 설계`

![https://user-images.githubusercontent.com/68331041/190093649-7f5a93bf-4ce7-4f9d-98b2-5c4248a1372f.png](https://user-images.githubusercontent.com/68331041/190093649-7f5a93bf-4ce7-4f9d-98b2-5c4248a1372f.png)
