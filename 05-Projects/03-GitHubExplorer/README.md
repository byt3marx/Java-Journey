# GitHub Explorer

GitHub Explorer is a Java CLI application that retrieves public GitHub user data using the GitHub REST API and displays the results in a readable format.

## Project Goal

The goal of this project is to practice building a clean Java application that communicates with an external API.

This project focuses on:

* Maven project structure
* HTTP requests in Java
* JSON parsing with Gson
* Object-oriented design
* Interfaces and service layers
* Clean separation of responsibilities
* CLI input validation
* Graceful error handling

## Current Features

* Search for a GitHub user by username
* Search for a GitHub user's public repositories
* Fetch live user data from the GitHub REST API
* Parse JSON response into a Java object
* Display formatted GitHub profile details
* Display repository name, description, language, stars, forks and URL
* Limit repository output to the first 10 repositories for readability
* Sort public repositories by star count, highest first
* Keep the application running until the user exits
* Validate blank username input
* Handle missing GitHub users gracefully
* Handle API or network errors with a user-friendly message

## Displayed User Data

The application currently displays:

* Username
* Name
* Bio
* Public repository count
* Followers
* Following
* GitHub profile URL

If optional profile fields such as name or bio are missing, the application displays:

```text
Not provided
```

## Displayed Repository Data

The application currently displays up to 10 public repositories for a searched GitHub user, sorted by star count from highest to lowest.

For each repository, it displays:

* Repository name
* Description
* Main language
* Star count
* Fork count
* Repository URL

If optional repository fields such as description or language are missing, the application displays:

```text
Not provided
```


## Architecture

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
JSON
 ↓
JsonMapper
 ↓
GitHubUser
 ↓
ConsoleUI
```

## Package Responsibilities

```text
app      - Application entry point
ui       - Console input, output, menu flow, and user-facing messages
service  - Application logic and use-case coordination
client   - HTTP communication with the GitHub API
parser   - JSON parsing and mapping
model    - Data objects
```

## Main Classes

### `Main`

Starts the application and wires dependencies together.

### `ConsoleUI`

Handles the command-line menu, user input, validation, and displaying results.

### `GitHubService`

Defines the service contract for searching GitHub users.

### `GitHubApiService`

Implements the GitHub user search workflow.

Responsibilities:

* Validate service input defensively
* Request raw JSON from the HTTP client
* Return `Optional.empty()` when a user is not found
* Convert valid JSON into a `GitHubUser`

### `GitHubHttpClient`

Handles communication with the GitHub API.

Responsibilities:

* Build the GitHub API request URL
* Send HTTP requests
* Return JSON when the request succeeds
* Return `Optional.empty()` when GitHub returns `404`
* Throw an exception for API or network failures

### `JsonMapper`

Uses Gson to convert raw JSON into a `GitHubUser` object.

### `GitHubUser`

Represents a GitHub user profile.

The model uses private final fields, constructor-based initialization, and getters.

## Validation Approach

The application uses layered validation:

```text
ConsoleUI
- Handles normal user input validation
- Prevents blank usernames from reaching the service
- Shows friendly messages and reprompts

GitHubApiService
- Defensively validates method arguments
- Throws IllegalArgumentException for invalid internal calls

GitHubHttpClient
- Handles HTTP status codes and network-level failures

GitHubUser
- Represents a read-only data snapshot from GitHub
```

## Error Handling

Expected cases:

```text
User not found → Optional.empty()
Blank input    → UI reprompts
```

Unexpected or technical cases:

```text
Network failure
Interrupted request
Unexpected GitHub API status code
```

These are handled with user-friendly messages in the UI.

## Example CLI Flow

```text
Welcome to GitHub Explorer

1. Search GitHub user
2. Exit
Choose option: 1

Enter GitHub username: torvalds

GitHub User Profile
-------------------
Username: torvalds
Name: Linus Torvalds
Bio: Not provided
Public repos: 8
Followers: 245000
Following: 0
Profile: https://github.com/torvalds
```

## Technologies Used

* Java
* Maven
* Gson
* GitHub REST API
* IntelliJ IDEA

## Version Status

Current status: Version 1.1 core functionality working.

Completed:

* Maven project setup
* Package structure
* CLI menu loop
* User input validation
* GitHub API request
* JSON mapping
* Profile display
* Missing user handling
* API error handling
* Basic unit testing
* Public repository search
* Repository JSON mapping
* Repository display formatting
* Repository output limiting

## Tests

The project currently has 11 passing JUnit tests.

Test coverage includes:

* Mapping valid GitHub user JSON into a `GitHubUser`
* Preserving `null` optional fields from GitHub user JSON
* Mapping GitHub repository JSON arrays into `List<GitHubRepository>`
* Returning a user when the client provides valid user JSON
* Returning `Optional.empty()` when a user is not found
* Throwing `IllegalArgumentException` for blank usernames
* Throwing `IllegalArgumentException` for null usernames
* Returning repositories when the client provides valid repository JSON
* Sorting repositories by star count in descending order
* Returning `Optional.empty()` when repository search finds no user
* Throwing `IllegalArgumentException` for blank repository search usernames

Current test classes:

```text
JsonMapperTest
GitHubApiServiceTest
```

## Possible Future Improvements

* Search and display public repositories
* Sort repositories by stars, forks, or update date
* Show repository languages
* Add GitHub API rate limit handling
* Add more unit tests
* Add custom exceptions
* Improve CLI formatting
* Add support for command-line arguments
* Add caching for repeated searches
