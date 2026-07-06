# GitHub Explorer

GitHub Explorer is a Java CLI application that retrieves public GitHub user data using the GitHub REST API and displays the results in a readable format.

## Project Goal

The goal of this project is to practice working with:

- Maven project structure
- HTTP requests in Java
- JSON parsing
- Object-oriented design
- Interfaces and service layers
- Clean separation of responsibilities
- CLI input validation

## Planned Features for Version 1.0

- Search for a GitHub user by username
- Fetch live user data from the GitHub API
- Parse JSON response into a Java object
- Display formatted user profile information
- Keep the application running until the user exits
- Handle invalid input and missing users gracefully

## Planned Architecture

```text
Main
 ↓
ConsoleUI
 ↓
GitHubService
 ↓
GitHubApiService
 ↓
GitHubHttpClient
 ↓
GitHub API
 ↓
JsonMapper
 ↓
GitHubUser
```

## Package Responsibilities

- app      - Application entry point
- ui       - Console input and output
- service  - Application logic and use cases
- client   - HTTP communication with GitHub API
- parser   - JSON parsing and mapping
- model    - Data objects

```text
Version:
Current status: Architecture and design phase.
```