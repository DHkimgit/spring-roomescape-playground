# Spring MVC
## 1단계 - 홈 화면
### 기능 요구 사항
- **홈 화면 구현**
- [x] 메인 페이지가 응답할 수 있다
  - [x] 웹 관련 의존성 추가
  - [x] 화면 응답 설정
## 2단계 - 예약 조회
### 기능 요구 사항
- **예약 페이지 구현**
- [x] 예약 페이지가 응답할 수 있다
  - [x] 예약 페이지 요청 및 응답
- [x] 예약 목록을 조회할 수 있다
  - [x] 예약 목록 요청 및 조회 API 구현
## 3단계 - 예약 추가 / 취소
### 기능 요구 사항
- **예약 추가 / 취소 기능 구현**
- [x] 예약을 추가할 수 있다
  - [x] 예약 추가 요청 및 응답 API 구현
- [x] 예약을 취소할 수 있다
  - [x] 예약 취소 요청 및 응답 API 구현
## 4단계 - 예외 처리
### 기능 요구 사항
- **예외 처리 기능 구현**
- [x] 요청에 문제가 있는 경우 Status Code 400으로 응답할 수 있다
  - [x] 예약 추가 시 인자가 비어있는 경우
  - [x] 예약 취소 시 인자에 해당하는 예약을 찾을 수 없는 경우
## 5단계 - 데이터베이스 적용하기
### 기능 요구 사항
- **h2 데이터베이스를 활용하여 데이터를 저장하도록 수정**
- [x] 데이터베이스 의존성 추가
  - spring-boot-stater-jdbc
  - h2
- [x] 테이블 스키마 정의
  - schema.sql 파일 생성
  - 테이블 생성 쿼리 작성
- [x] 데이터베이스 설정
  - url -> jdbc:h2:mem:database
## 6단계 - 데이터 조회하기
### 기능 요구 사항
- **예약 조회 API 처리 로직에서 저장된 예약을 조회할 때 데이터베이스를 활용하도록 수정**
- [x] JdbcTemplate 과 RowMapper 를 활용하여 데이터베이스 조회 로직을 구현할 수 있다
## 7단계 - 데이터 추가/삭제하기
### 기능 요구 사항
- **예약 추가/취소 API 처리 로직에서 데이터베이스를 활용하도록 수정**
- [x] 기존에 사용하던 List 및 AtomicLong 을 제거한다
- [x] JdbcTemplate 을 활용하여 데이터베이스에 데이터를 추가/삭제할 수 있다
