# 🎨 JSON to HTML Parser

A structured **Java-based JSON → HTML generation engine** built as part of my Java learning journey.

This project focuses on applying **recursive parsing, tree traversal, rendering pipelines, separation of concerns, and file generation** using real-world style architecture.

---

# 🚀 Overview

The JSON to HTML Parser converts structured JSON documents into fully generated HTML pages.

The application supports:

* recursive nested HTML structures
* attributes and inline styling
* arrays/lists for repeated elements
* document-level HTML generation
* void HTML elements
* formatted multiline rendering
* HTML file generation and persistence

The project is designed using a layered transformation pipeline:

```text
JSON → Parsed Data → HTML Nodes → HTML Rendering → File Output
```

---

# 🧠 Key Concepts Applied

This project combines multiple core Java concepts into one cohesive system:

* Object-Oriented Programming (OOP)
* Recursive tree traversal
* Collections (`Map`, `List`, `Set`)
* JSON deserialization with Gson
* File I/O
* Separation of concerns
* Rendering pipelines
* Dynamic structure conversion
* Recursive HTML generation

---

# 🏗 Architecture

The project follows a structured layered architecture:

```text
io       → file loading and file writing
parser   → JSON parsing and structure conversion
builder  → HTML rendering
app      → application entry point
```

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

#### JsonToHtmlNodeConverter

* Converts parsed JSON into normalized HTML node structures
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

---

# ⚙ Features

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

# 📁 Project Structure

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
│       │   ├── io/
│       │   │   ├── JsonLoader.java
│       │   │   └── HtmlFileWriter.java
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

# ▶ How to Run

1. Navigate to the project directory:

```bash
cd 02-JsonToHtmlParser
```

2. Run the application:

```bash
mvn compile
mvn exec:java
```

---

# 🧪 Example Pipeline

```text
helloWorld.json
        ↓
JsonLoader
        ↓
JsonParserService
        ↓
JsonToHtmlNodeConverter
        ↓
HtmlBuilder
        ↓
HtmlFileWriter
        ↓
helloWorld.html
```

---

# 📈 What This Project Demonstrates

This project demonstrates the ability to:

* build recursive parsers
* design transformation pipelines
* separate responsibilities across layers
* dynamically generate structured documents
* work with nested collections and maps
* build maintainable Java applications
* apply real-world architecture patterns

---

# 🚀 Future Improvements

Possible extensions:

* Typed `HtmlNode` classes instead of raw `Map<String, Object>`
* HTML validation
* configurable formatting options
* XHTML/self-closing mode
* CSS file generation
* template engine support
* HTML escaping & sanitization
* parser schema validation

---

# 👨‍💻 Author

Part of my ongoing journey to become a confident Java developer by building structured, real-world style applications and learning how parsing, rendering, and transformation systems work internally.
