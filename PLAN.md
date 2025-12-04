# PLAN

## 1. Objective

Build a small but production-ready Quiz Management System with:
- An **Admin Panel** to create quizzes with multiple question types.
- A **Public Page** where anyone can take a quiz and see results.

Focus:
- Reliable core flow (create quiz → take quiz → see results)
- Clean, layered architecture
- Realistic trade-offs for a 2-hour timebox

## 2. Assumptions

- No real authentication: any user with `/admin` URLs acts as "admin".
- Quizzes are public via URL `/quiz/{id}`.
- Auto-grading covers:
    - MCQ (single correct option)
    - TRUE_FALSE
- TEXT questions are stored but not auto-graded.
- Database: MySQL in this project.
- Editing/deleting quizzes is out of scope for now.

## 3. Scope

### In Scope

- Admin:
    - Create quiz with title and a few questions (MCQ, TRUE_FALSE, TEXT).
    - Persist quiz and questions to DB.

- Public:
    - View quiz by ID.
    - Submit answers.
    - Auto-grade MCQ and TRUE_FALSE.
    - Show score + basic per-question feedback.

### Out of Scope (due to time)

- Authentication/authorization.
- Quiz editing/deletion.
- Fancy frontend (plain Thymeleaf is fine).
- Advanced validation, analytics, and CI pipeline.

## 4. High-Level Architecture

- **Backend**: Spring Boot (Web, JPA)
- **View**: Thymeleaf templates
- **DB**: MySQL

### Packages

- `com.example.tacnique.quiz.entity` — JPA entities (`Quiz`, `Question`, `QuizSubmission`, `SubmissionAnswer`, `QuestionType`)
- `com.example.tacnique.quiz.repository` — Spring Data JPA repositories
- `com.example.tacnique.quiz.service` — Business logic (quiz creation, submission, grading)
- `com.example.tacnique.quiz.controller` — Web controllers (admin + public)

## 5. Implementation Phases

1. Initial setup (Spring Boot + entities + PLAN).
2. Repositories + service layer skeleton.
3. Admin quiz creation flow (form + save).
4. Public quiz taking + submission + grading.
5. Result page + polish + reflection.

## 6. Scope Changes During Implementation

- Initially considered supporting dynamic add/remove of questions in the admin UI.
  - For the timebox, I fixed the number of questions on the form (3) and focused on making the end-to-end flow reliable.
- I planned to use JSON for MCQ options; in the implementation I simplified this to a string joined with `||`.
  - This keeps the schema simple while still allowing multiple options per question.
- I briefly considered adding authentication for the admin area, but explicitly decided to keep `/admin` open and document this as an assumption to stay within the 2-hour limit.
- I added a small home page listing existing quizzes (`/`) to make the system easier to navigate and demo.


## 7. Reflection / Next Steps

If I had more time, I would focus on:

1. **Admin Experience**
  - Allow dynamic add/remove of questions on the create quiz screen (using a small amount of JavaScript).
  - Add the ability to edit and delete existing quizzes.
  - Introduce basic authentication/authorization so that only admins can access `/admin` routes.

2. **Frontend & UX**
  - Improve the layout and styling (e.g., with a component library or a simple CSS framework).
  - Add client-side validation and clearer error messages on the forms.

3. **Grading & Analytics**
  - Provide a richer results page with per-question statistics (number of correct submissions, difficulty, etc.).
  - Implement manual grading support for TEXT questions and include their scores in the final result.

4. **Testing & Quality**
  - Add unit tests around the grading logic and service layer.
  - Add integration tests for the main flows (create quiz → take quiz → view result).
  - Configure a CI pipeline (GitHub Actions) to run tests on every push.

5. **Production Readiness**
  - Externalize configuration (profiles for dev/prod, secrets out of source control).
  - Use database migrations (Flyway or Liquibase) instead of `ddl-auto=update`.
  - Add logging, basic metrics, and health checks to make the service more observable in production.
