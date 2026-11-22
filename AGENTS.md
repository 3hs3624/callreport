# callreport 작업 규칙

고객 지원 Call Report 관리 웹앱. 지원월·팀·담당자·고객사·고객 담당자·지원일·상태를 기록하고 목록·포털 화면으로 본다.

## 0. 공통 원칙 (모든 AI 에이전트·사람 공통)

이 절은 3hs3624 저장소 전체에 같은 문구로 들어간다. 바꿀 때는 모든 저장소에서 함께 바꾼다.

1. **이 파일(AGENTS.md)이 작업 규칙의 원본이다.** Codex 등 AGENTS.md를 읽는 에이전트는 이 파일을, Claude Code는 이 파일을 불러오는 `CLAUDE.md`를 읽는다. 규칙은 이 파일에만 쓰고 `CLAUDE.md`에는 불러오기 한 줄만 둔다.
2. **원본은 GitHub `3hs3624/callreport`의 `main`이다.** PC 로컬 폴더, 개인 메모리, 채팅 기록은 근거가 아니다. 작업 시작 시 `main` 최신본에서 출발하고, 끝나면 커밋·푸시해 저장소에 남긴다.
3. **소스·문서·설계 결정·작업 기록은 모두 이 저장소에 둔다.** 다음 작업자가 다른 모델이어도 저장소만 읽고 같은 결과를 이어갈 수 있어야 한다. 결정은 문서(ADR·결정 기록)로, 진행 상황은 작업 기록으로 남긴다.
4. **구글 드라이브(또는 노션)에 함께 올리라고 정한 문서는 계속 함께 올린다.** 저장소가 원본이고 드라이브·노션은 같은 내용의 사본이다.
5. **비밀값은 커밋하지 않는다.** 토큰·비밀번호·`.env`는 저장소 밖에 두고, 필요한 변수 이름만 `.env.example`에 적는다.
6. **완료 보고 전에 아래 "검증" 명령을 실행하고 결과를 남긴다.** 실행하지 못한 검증은 "미검증"으로 적는다.
7. **특정 모델 전용 기능에 결과가 의존하지 않게 한다.** Claude 서브에이전트·훅, Codex 전용 설정 등을 쓰더라도, 같은 결과를 내는 절차를 이 파일이나 `docs/`에 적어 둔다.
8. **도구·버전은 저장소에 고정된 것을 쓴다.** 잠금 파일(`package-lock.json`, `requirements.txt`, `pom.xml` 등)과 `Dockerfile`·`docker-compose.yml`을 기준으로 하며, 바꾸면 이유를 기록한다.

## 1. 기술 기준

- Spring Boot 3.5.5, Java 17, Thymeleaf, Spring Data JPA, Lombok (`app/pom.xml`)
- DB: PostgreSQL 15, 스키마 `app/src/main/resources/schema.sql`
- 컨테이너: 루트 `docker-compose.yml`(app + db), `app/Dockerfile`

## 2. 실행

`app/Dockerfile`은 미리 빌드한 jar를 복사하므로 jar를 먼저 만든다(테스트는 DB가 필요해 여기서는 건너뛴다).

```bash
cd app
./mvnw -B package -DskipTests    # Windows: mvnw.cmd -B package -DskipTests
cd ..
docker compose up -d --build     # http://localhost:8080 , 목록 http://localhost:8080/callreportlist
```

## 3. 검증

GitHub Actions(`.github/workflows/ci.yml`)가 모든 푸시와 PR에서 PostgreSQL 15 서비스를 띄우고 `mvnw -B verify`(빌드와 테스트)를 실행한다. 완료 보고 전에 CI 결과를 확인한다.

로컬에서는 PostgreSQL이 떠 있는 상태에서 실행한다(`docker compose up -d db` 후).

```bash
cd app
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/callreport ./mvnw -B verify
```

## 4. 알려진 사항 (2026-11-22 기준)

- 엔티티 `model/CallReport.java`는 `schema.sql`의 `call_report` 컬럼과 같다. 테이블은 `spring.jpa.hibernate.ddl-auto=update`로 만들어지며 `schema.sql`은 자동 실행되지 않는다.
- `docker-compose.yml`과 `application.properties`의 DB 계정은 로컬 개발용 값이다. 운영에서는 환경 변수로 바꾸고 실제 값을 커밋하지 않는다.
- 등록·수정 화면은 아직 없다. 목록(`/callreportlist`)은 조회만 한다.
