# Task Tracker CLI
The project page URL: https://roadmap.sh/projects/task-tracker

A command-line task tracker built in Java that lets you add, update, delete, and track the status of your tasks. All tasks are saved to a local `tasks.json` file and persist between sessions.

---

## Requirements

- Java 11 or higher

---

## Setup

**1. Clone or download the project**

**2. Navigate to the `src` folder**
```
cd src
```

**3. Compile all Java files**
```
javac *.java
```

**4. Set the classpath so you don't have to type `-cp .` every time (optional)**
```
[System.Environment]::SetEnvironmentVariable("CLASSPATH", ".", "User")
```
Restart your terminal after running this.

---

## Usage

```
java Main <command> [arguments]
```

---

## Commands

### Add a task
```
java Main add "Task description"
```
Example:
```
java Main add "Buy groceries"
```

### Update a task
```
java Main update <id> "New description"
```
Example:
```
java Main update 1 "Buy groceries and cook dinner"
```

### Delete a task
```
java Main delete <id>
```
Example:
```
java Main delete 1
```

### Mark a task as in progress
```
java Main mark-in-progress <id>
```
Example:
```
java Main mark-in-progress 1
```

### Mark a task as done
```
java Main mark-done <id>
```
Example:
```
java Main mark-done 1
```

### List all tasks
```
java Main list
```

### List tasks by status
```
java Main list <status>
```
Available statuses: `todo`, `in-progress`, `done`

Examples:
```
java Main list todo
java Main list in-progress
java Main list done
```

---

## Task Statuses

| Status | Description |
|---|---|
| `todo` | Newly added task, not yet started |
| `in-progress` | Task currently being worked on |
| `done` | Completed task |

---

## Data Storage

Tasks are saved in a `tasks.json` file in the directory where you run the program. Each line represents one task in the following format:

```
id|description|status|createdAt|updatedAt
```
