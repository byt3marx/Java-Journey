# 🎨 JSON to HTML Parser

A structured **Java-based JSON → HTML generation engine** built as part of my Java learning journey.

This project focuses on applying **recursive parsing, tree traversal, rendering pipelines, separation of concerns, and file generation** using real-world style architecture.

> Status: Complete
>
> Version: 1.0
>
> Features:
> - JSON → HtmlNode conversion
> - HTML rendering engine
> - Command-line argument support
> - JUnit 5 test suite (18 tests passing) 

---

## 🚀 Overview

The JSON to HTML Parser converts structured JSON documents into fully generated HTML pages.

The application supports:

* recursive nested HTML structures
* HtmlNode Tree generation and traversal
* attributes and inline styling
* arrays/lists for repeated elements
* document-level HTML generation
* void HTML elements
* formatted multiline rendering
* HTML file generation and persistence
* type-safe HTML document modeling

---

## 🧠 Key Concepts Applied

This project combines multiple core Java concepts into one cohesive system:

* Object-Oriented Programming (OOP)
* Recursive tree traversal
* Collections (`Map`, `List`, `Set`)
* Java Generics
* JSON deserialization with Gson
* TypeToken and generic type handling
* File I/O
* Separation of concerns
* Rendering pipelines
* Dynamic structure conversion
* Recursive HTML generation
* Defensive validation
* Exception wrapping and error handling
* Utility class design
* Rendering abstraction
* Domain modeling
* Tree-based data structures
* Unmodifiable collections
* Encapsulation and state protection
* Refactoring and code cleanup
* Type-safe architecture design

---

## 🏗 Architecture

The project follows a structured layered architecture:

```text
app      → application entry point
io       → file loading and file writing
parser   → JSON parsing and HtmlNode conversion
model    → HtmlNode document representation
builder  → HTML rendering
html     → shared HTML rules/constants

```

The application transforms JSON into a recursive HtmlNode tree before rendering the final HTML output.

```text
JSON
  ↓
Map<String, Object>
  ↓
HtmlNode Tree
  ↓
HTML Renderer
  ↓
HTML File
```

This intermediate tree representation improves type safety, reduces casting, and creates a cleaner separation
between parsing and rendering responsibilities. 


---

## 📦 Package Breakdown

### 🔹 app

```text
Main.java
```

* Entry point of the application
* Coordinates the full parsing pipeline
* Loads JSON input
* Converts data into HTML
* Generates final HTML files

---

### 🔹 io

```text
JsonLoader.java
HtmlFileWriter.java
```

#### JsonLoader

* Reads JSON files from disk
* Handles file-loading logic separately from parsing
* Validates file paths before reading
* Provides clearer file-loading exception messages

#### HtmlFileWriter

* Writes generated HTML to `.html` files
* Handles output persistence
* Keeps file-writing logic isolated from rendering

---

### 🔹 parser

```text
JsonParserService.java
JsonToHtmlNodeConverter.java
```

#### JsonParserService

* Uses Gson to deserialize JSON into Java structures
* Converts JSON into `Map<String, Object>`
* Performs defensive validation before parsing
* Wraps parsing failures with meaningful exceptions
* Isolates Gson-specific implementation details

#### JsonToHtmlNodeConverter

* Converts parsed JSON into a recursive HtmlNode tree representation
* Handles:

  * recursive conversion
  * arrays/lists
  * nested children
  * smart attribute detection
  * inline style conversion
  * void element handling
  * document-level HTML conversion

---

### 🔹 builder

```text
HtmlBuilder.java
```

* Recursively renders HTML nodes into formatted HTML strings
* Handles:

  * indentation
  * inline vs block rendering
  * multiline formatting
  * attributes
  * void elements

* Uses extracted rendering helpers for cleaner orchestration
* Separates inline and multiline rendering behavior
* Centralizes reusable tag rendering logic

---

### 🔹 model

```text
HtmlNode.java
```

#### HtmlNode

* Represents a single HTML element in the document tree
* Stores:

    * tag name
    * text content
    * attributes
    * child elements

* Uses defensive validation
* Exposes unmodifiable collections
* Provides helper methods for querying node state
* Acts as the domain model used by the rendering pipeline

---
### 🔹 html

```text
HtmlRules.java
```

#### HtmlRules

* Centralizes shared HTML semantics
* Stores reusable HTML rule sets such as:

    * void elements
    * inline elements

* Prevents duplication across parser and renderer layers
* Demonstrates utility-class architecture

---
## ⚙ Features

## 🌳 Recursive HTML Generation

Supports deeply nested HTML structures:

```json
{
  "div": {
    "h1": "Title",
    "p": "Paragraph"
  }
}
```

---

## 🔁 Array Support

Supports repeated HTML elements using JSON arrays:

```json
"link": [
  { "href": "style.css" },
  { "href": "theme.css" }
]
```

---

## 🎨 Nested Style Conversion

Supports nested style objects converted into inline CSS:

```json
"style": {
  "width": "80%",
  "text-align": "center"
}
```

Generated output:

```html
style="width: 80%; text-align: center;"
```

---

## 🧠 Smart Attribute Detection

Void elements automatically interpret primitive key-value pairs as attributes:

```json
"link": {
  "href": "style.css",
  "rel": "stylesheet"
}
```

Generated output:

```html
<link href="style.css" rel="stylesheet">
```

---

## 🛡 Defensive Validation & Error Handling

The parser includes validation and exception handling for:

* empty JSON input
* invalid JSON content 
* invalid file paths 
* failed parsing operations 
* failed file-loading operations

This improves debugging and creates cleaner abstraction boundaries between application layers.

---

## 🧹 Refactored Rendering Architecture

The rendering engine was refactored into smaller reusable helpers:

* opening tag rendering
* closing tag rendering
* inline child rendering
* multiline child rendering
* style attribute building
* viewport content generation

This improved readability, maintainability, and separation of concerns.

---
## 📄 Document-Level HTML Generation

Supports full HTML document creation:

* `<!DOCTYPE html>`
* `<html>`
* `<head>`
* `<body>`
* `<meta>`
* `<link>`
* nested content

---

## 💾 HTML File Generation

Generated HTML can be saved directly into `.html` files.

---

## 📁 Project Structure

```text
02-JsonToHtmlParser/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── app/
│       │   │   └── Main.java
│       │   │
│       │   ├── builder/
│       │   │   └── HtmlBuilder.java
│       │   │
│       │   ├── html/
│       │   │   └── HtmlRules.java
│       │   │
│       │   ├── io/
│       │   │   ├── JsonLoader.java
│       │   │   └── HtmlFileWriter.java
│       │   │
│       │   ├── model/
│       │   │   └── HtmlNode.java
│       │   │
│       │   └── parser/
│       │       ├── JsonParserService.java
│       │       └── JsonToHtmlNodeConverter.java
│       │
│       └── resources/
│           ├── input/
│           └── output/
│
└── pom.xml
```

---

## ▶ How to Run

1. Navigate to the project directory:

```bash
  cd 02-JsonToHtmlParser
```

2. Run the application:

```bash
  mvn compile
  mvn exec:java
```
3. The application supports command-line arguments for custom input and output files.

   Example:
```bash
java -jar JsonToHtmlParser.jar input.json output.html
```

---

## 🧪 Testing

The project includes JUnit 5 unit tests covering the core components:

### HtmlNodeTest

* Node Creation
* Attribute management
* Child management
* Text content
* Validation

### JsonParserServiceTest

* Valid JSON parsing
* Blank JSON rejection
* Invalid JSON rejection

### HtmlBuilderTest

* Text rendering
* Attribute rendering
* Attribute + text rendering
* Parent/child rendering
* Multiple children
* Nested elements
* Inline elements
* Void elements

### Current Results:

* Tests: 18
* Failures: 0
* Errors: 0
* Skipped: 0

---

## 🧪 Example Pipeline

```text
helloWorld.json
        ↓
JsonLoader
        ↓
JsonParserService
        ↓
JsonToHtmlNodeConverter
        ↓
HtmlNode Tree
        ↓
HtmlBuilder
        ↓
HtmlFileWriter
        ↓
helloWorld.html
```

---

## 📈 What This Project Demonstrates

This project demonstrates the ability to:

* build recursive parsers
* design transformation pipelines
* separate responsibilities across layers
* dynamically generate structured documents
* work with nested collections and maps
* build maintainable Java applications
* apply real-world architecture patterns
* defensive programming techniques
* layered exception handling
* utility class architecture
* incremental refactoring practices
* reusable rendering abstractions
* domain modeling with HtmlNode
* recursive tree-based document representation
* type-safe architecture design
* encapsulation through dedicated domain objects
* use of unmodifiable collections
* migration from generic Map structures to object-oriented models
* safe large-scale refactoring of an existing codebase
* recursive rendering of object hierarchies
* practical use of Java generics and TypeToken
* unit testing with JUnit 5
* test-driven validation of rendering behavior
* verification of recursive tree rendering

---

## 🚀 Future Improvements

Possible extensions:

* HTML validation
* XHTML/self-closing mode
* CSS file generation
* template engine support
* HTML escaping & sanitization
* parser schema validation
* configurable formatting rules
* visitor pattern rendering architecture
* support for additional HTML document features
* configurable indentation and output styles

---

## 👨‍💻 Author

Part of my ongoing journey to become a confident Java developer by building structured, real-world style applications and learning how parsing, rendering, and transformation systems work internally.
