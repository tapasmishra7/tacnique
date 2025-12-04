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

(To be updated during development.)

## 7. Reflection / Next Steps

(To be written at the end — what I’d do with more time.)
