# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Kotlin-based lesson generator that creates HTML presentation slides using reveal.js. The application generates educational content for Kotlin programming and backend development courses.

## Build Commands

- **Build the project**: `./gradlew build`
- **Run tests**: `./gradlew test`
- **Clean build artifacts**: `./gradlew clean`

## Course Generation Commands

- **Generate Programming in Kotlin course**: `./gradlew generateProgrammingCourse`
- **Generate Backend Development course**: `./gradlew generateBackendCourse`
- **Generate all courses**: `./gradlew generateAllCourses` or `./gradlew run`

## Architecture

### Core Components

- **Main.kt**: Entry point that generates all courses
- **ProgrammingInKotlinMain.kt**: Entry point for Programming in Kotlin course only
- **BackendDevelopmentInKotlinMain.kt**: Entry point for Backend Development course only
- **CourseBuilder**: Generates HTML files from course definitions using kotlinx-html DSL
- **Model Classes**: 
  - `Course`: Contains lessons and course metadata
  - `Lesson`: Contains topics and lesson metadata, auto-generates filename from title
  - `Topic`: Contains slides and topic metadata
  - `Slide`: Individual slide content with header, summary, and content functions

### Content Structure

- **Lessons**: Located in `src/main/kotlin/com/motycka/edu/content/lessons/`
- **Topics**: Organized by category in `src/main/kotlin/com/motycka/edu/content/topics/`
  - `basics/`: Fundamental programming concepts
  - `introductory/`: Course introduction topics
  - `oop/`: Object-oriented programming topics

### Output Generation

- HTML files are generated in the project root with naming pattern: `lesson-XX-{lesson-filename}.html`
- Uses reveal.js framework for presentation functionality
- Custom Kotlin theme located at `revealjs/dist/theme/kotlin.css`

### Key Technical Details

- Uses kotlinx-html DSL for HTML generation
- Reveal.js integration with plugins: markdown, highlight, notes
- FontAwesome icons integration
- Kotlin-specific syntax highlighting with VS theme

## Development Workflow

1. Define new lessons in the lessons directory
2. Create topics in appropriate subdirectories under topics/
3. Add lessons to the course definition in Main.kt
4. Run the application to generate HTML files
5. Generated files can be opened directly in browsers for viewing

## Dependencies

- Kotlin JVM 2.1.20 with JVM toolchain 23
- kotlinx-html-jvm 0.12.0 for HTML DSL
- JUnit for testing (uses JUnit Platform)