# PLAN

## 1. Objective

Build a small but production-ready Quiz Management System with:
- An **Admin Panel** to create quizzes with multiple question types.
- A **Public Page** where anyone can take a quiz and see their results.

Focus is on:
- A realistic, clean architecture.
- Reliable core flow (create quiz → take quiz → see results).
- Clear trade-offs and scoped features to fit in 2 hours.

---

## 2. Assumptions

- Only a basic, implicit "admin" exists. There is **no authentication**; any user who knows the `/admin` URL can create quizzes.
- The system is single-tenant; we don't differentiate between organizations or multiple admins.
- Quizzes are public via a simple URL: `/quiz/{id}`.
- Auto-grading covers:
  - **MCQ** (single correct option)
  - **TRUE_FALSE**
- **TEXT** questions are stored but **not auto-graded**. They show up in the results as "Not auto-graded".
- Database will be a relational DB (MySQL or similar). For local development, I may use H2 with schema compatible with MySQL.
- There is no requirement to edit or delete quizzes within this assignment.

---

## 3. Scope

### In Scope

- Admin:
  - Create a new quiz with:
    - Title
    - A few questions (MCQ, TRUE_FALSE, TEXT)
  - Persist quiz and questions in the database.
- Public:
  - Public page to take a quiz using a URL.
  - Capture user answers.
  - Auto-grade MCQ and TRUE_FALSE questions.
  - Display a summary:
    - Score and max score (based on auto-gradable questions).
    - Per-question feedback (correct/incorrect/Not auto-graded).
- Architecture:
  - Layered Spring Boot application:
    - Controller → Service → Repository layers.
  - Basic error handling (e.g., quiz not found).
- AI usage:
  - Use an AI coding agent to help with boilerplate code (DTOs, mappings, repository interfaces, basic HTML forms), but review and adapt everything.

### Out of Scope (for 2-hour timebox)

- Authentication/authorization (real admin login).
- Editing or deleting existing quizzes.
- Advanced UI/UX, SPA frontend, or a separate React/Next.js app.
- Client-side validation beyond basic HTML/browser validation.
- Advanced analytics or dashboard for quiz results.
- Exporting data or large-scale performance tuning.

---

## 4. High-Level Architecture

### Tech Stack

- **Backend**: Spring Boot (Spring Web, Spring Data JPA)
- **Frontend**: Server-side rendered HTML via Thymeleaf (counts as "Java frontend")
- **Database**: MySQL
- **Build Tool**: Maven
- **IDE**: IntelliJ / VS Code / Eclipse (locally)
- **AI Agent**: CLI-based coding assistant to generate boilerplate and speed up development

### Components

1. **Domain Model**
   - `Quiz`
   - `Question`
   - `QuizSubmission`
   - `SubmissionAnswer`
   - `QuestionType` enum (`MCQ`, `TRUE_FALSE`, `TEXT`)

2. **Controllers**
   - `AdminQuizController`
     - `GET /admin/quizzes/new`
     - `POST /admin/quizzes`
   - `PublicQuizController`
     - `GET /quiz/{id}`
     - `POST /quiz/{id}/submit`

3. **Services**
   - `QuizService` (create quiz, load quiz)
   - `SubmissionService` (process submission, grade, store result)

4. **Repositories**
   - `QuizRepository`
   - `QuestionRepository`
   - `QuizSubmissionRepository`
   - `SubmissionAnswerRepository`

5. **Views (Thymeleaf templates)**
   - `admin/new-quiz.html` (quiz creation form)
   - `public/take-quiz.html` (quiz taking page)
   - `public/quiz-result.html` (results page)

### Data Model (Simplified)

- `Quiz(id, title, createdAt)`
- `Question(id, quizId, text, type, optionsJson, correctAnswer)`
- `QuizSubmission(id, quizId, submittedAt, score, maxScore)`
- `SubmissionAnswer(id, submissionId, questionId, answer, isCorrect)`

---

## 5. Implementation Plan (Timeline)

### Phase 1 (0–20 min): Setup & Initial Plan

- Initialize Spring Boot project with dependencies (Web, Thymeleaf, JPA, DB driver).
- Define domain entities and `QuestionType` enum.
- Commit: `chore: initial project setup and domain model`.

### Phase 2 (20–50 min): Admin Quiz Creation

- Implement `QuizService` and repositories.
- Implement `AdminQuizController` with:
  - `GET /admin/quizzes/new`
  - `POST /admin/quizzes`
- Create simple Thymeleaf form to create a quiz with a few questions.
- Commit: `feat: add quiz creation flow for admin`.

### Phase 3 (50–90 min): Public Quiz + Submission

- Implement `PublicQuizController`:
  - Load quiz by ID.
  - Render quiz page with appropriate inputs per question type.
  - Handle submission, grade answers, persist submission and answers.
- Implement `SubmissionService` with grading logic for MCQ and TRUE_FALSE.
- Commit: `feat: implement public quiz taking and grading`.

### Phase 4 (90–110 min): Results View, Error Handling, Polish

- Build results page showing:
  - Score and max score.
  - Per-question breakdown (user answer vs correct answer).
  - Indicate "Not auto-graded" for TEXT questions.
- Add basic error handling (quiz not found).
- Clean up structure and add comments where it clarifies intent.
- Commit: `refactor: polish views and add basic error handling`.

### Phase 5 (110–120 min): Reflection, Demo, Final Touches

- Update PLAN.md with any scope changes and final architecture notes.
- Add reflection section (what to do next with more time).
- Record final demo walkthrough (admin → create quiz → public → take quiz → see result).
- Commit: `docs: update PLAN and final cleanup`.

---

## 6. Scope Changes During Implementation

> (To be updated during the session.)

Examples you might add later:

- "Planned to support editing quizzes, but dropped due to time."
- "Originally planned to use MySQL directly but switched to H2 due to setup time."
- "Reduced validation complexity to keep focus on core flows."

---

## 7. Reflection / Next Steps (To Fill In At The End)

If I had more time, I would:
- Add authentication and proper admin-only routes.
- Allow editing and deleting quizzes.
- Introduce a React or Next.js-based frontend calling the Spring Boot backend.
- Improve validation and error handling (e.g., field-level messages).
- Add more tests (unit + integration) and CI (GitHub Actions).
- Harden the app for production: better logging, configuration management, and DB migrations.
