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

```bash
docker compose up -d --build     # http://localhost:8080
```

DB 없이 앱만 빌드할 때:

```bash
cd app
./mvnw -B package                # Windows: mvnw.cmd -B package
```

## 3. 검증

```bash
cd app
./mvnw -B test
```

## 4. 알려진 문제 (2026-11-22 기준)

- `app/src/main/java/com/example/callreport/model/CallReport.java`가 빈 파일이라 현재 빌드가 실패한다. `schema.sql`의 `call_report` 컬럼에 맞춰 엔티티를 작성하는 것이 첫 작업이다.
- `docker-compose.yml`과 `application.properties`의 DB 계정은 로컬 개발용 값이다. 운영에서는 환경 변수로 바꾸고 실제 값을 커밋하지 않는다.
