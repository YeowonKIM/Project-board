# Project-board
스프링 기초. 스프링 부트로 로그인 기능이 없는 게시판 백엔드 서버  만들기

#### | 목표 |
  1. Lombok과 JPA를 이용하여 원하는 데이터베이스를 만들고 활용
  2. Spring Boot를 기반으로 CRUD(Create, Read, Update, Delete) 기능이 포함된 REST API를 만들기

<br/> <br/> 

## STEP 1 : 서비스 요구사항 분석


 + 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
 

  #### 1) 게시글 작성 API 

      - 제목, 작성자명, 비밀번호, 작성 내용을 저장

      - 저장된 게시글을 Client 로 반환하기

 #### 2) 선택한 게시글 조회 API

     - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용 간단한 조회

 #### 3) 선택한 게시글 수정 API 

      - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후

      - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기

 #### 4) 선택한 게시글 삭제 API 

      - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후

      - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
<br/> <br/> 

## STEP 2  유스케이스 작성

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fk9H3H%2FbtrYf0ISrMp%2FdfoW6a64pCJsykULRIBpdK%2Fimg.jpg"  width="500" height="300">

<br/> <br/> 


## STEP 3   API 명세서 작성
Method|URL|Request|Response
|------|---|---|-----|
|GET|	/api/posts|-|{{"createdAt": "작성일자”,<br/> "modifiedAt": "수정일자”, <br/> "id": 1, <br/> "title": "title1", <br/> "content": "content1",<br/>  "author": "author1"}, <br/> { "createdAt": "작성일자",<br/> "modifiedAt": "수정일자”,<br/> "id": 2,<br/> "title": "title",<br/>  "content": "content",<br/> "author": "author" } ...}|
|GET|	/api/post/{id}|-|{{"createdAt": "작성일자”,<br/> "modifiedAt": "수정일자”, <br/> "id": 1, <br/> "title": "title2", <br/> "content": "content2",<br/>  "author": "author2"}, <br/> { "createdAt": "작성일자",<br/> "modifiedAt": "수정일자”,<br/> "id": 2,<br/> "title": "title",<br/>  "content": "content",<br/> "author": "author" } ...}|
|POST| /api/post|{ "title" : "title,"<br/> "content" : "content","<br/>  "author" : "author","<br/> "password" : "password"}|{"createdAt": "작성일자”,<br/> "modifiedAt": "수정일자”, <br/> "id": 1, <br/> "title": "title1", <br/> "content": "content1",<br/>  "author": "author1"}|
|PUT|	/api/post/{id}|{ "title2" : "new title","<br/> "content2" : "content2","<br/> "author2" : "new author","<br/> "password2" : "password"}|{"createdAt": "작성일자”,<br/> "modifiedAt": "수정일자”, <br/> "id": 1, <br/> "title2": "title2", <br/> "content2": "content2",<br/>  "author2": "author2"} |
|DELETE| /api/post/{id}|{"password" : "password"}|{"success":true},|

<br/><br/>

## STEP 4  코드작성

<br/><br/>

 
### ⛔️ 중간에 발생한 오류
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbcVqAA%2FbtrYhaE2k60%2FXYdJDkBr2fhISAWZzGsQxk%2Fimg.png"  width="700" height="100">
<br/>

### 💡 해결

1) org.springframework.transaction.annotation.Transactional   :  옵션을 허용(readonly=true? false) 가능
2) javax.transaction.Transactional :  옵션을 허용하지 않음. <br/>
    => 1번으로 다시 import하니 문제가 해결 되었다.
