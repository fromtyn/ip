# Elden User Guide

## What is Elden?

**Elden** is a command-line task chatbot. It is meant for users who want a simple way to manage tasks by typing commands.

This chatbot can help you:

- add todo tasks
- add deadline tasks
- add event tasks
- list all tasks
- mark tasks as done
- unmark tasks
- delete tasks
- find tasks by keyword
- save tasks automatically

## Quick Start

1. Run the program with the project instructions.
2. When you see the welcome message below, Elden is ready:

```text
Hello! I'm Elden
What can I do for you?
```

3. Type one command and press Enter.
4. If you need a task number for `mark`, `unmark`, or `delete`, use `list` first.
5. Your tasks are saved automatically in `data/elden.txt`.

## Before You Start

There are a few small things that are good to know first:

- Command words must be written in **lowercase**.
- Task numbers come from the main task list.
- For `deadline` and `event`, Elden stores the time text exactly as you type it.
- `find` checks the **description** of each task.
- `find` is **case-insensitive**.

## Task Display

Elden uses a short format when showing tasks:

- `[T]` means a todo task
- `[D]` means a deadline task
- `[E]` means an event task
- `[X]` means the task is done
- `[ ]` means the task is not done yet

Example:

```text
[D][X] submit report (by: Friday)
```

This means it is a deadline task, and it has already been marked as done.

## Command Summary

| Action | Format | Example |
| --- | --- | --- |
| Add a todo | `todo DESCRIPTION` | `todo read notes` |
| Add a deadline | `deadline DESCRIPTION /by TIME` | `deadline return book /by Sunday` |
| Add an event | `event DESCRIPTION /from START /to END` | `event project meeting /from 2pm /to 4pm` |
| List all tasks | `list` | `list` |
| Mark a task | `mark TASK_NUMBER` | `mark 2` |
| Unmark a task | `unmark TASK_NUMBER` | `unmark 2` |
| Delete a task | `delete TASK_NUMBER` | `delete 3` |
| Find tasks | `find KEYWORD` | `find book` |
| Exit the program | `bye` | `bye` |

## Features

### Add a todo task

Use this command for a basic task with no date or time.

**Format:** `todo DESCRIPTION`

Example:

```text
todo read notes
```

What Elden does:

- adds the task into the list
- shows the task on screen
- saves the change automatically

### Add a deadline task

Use this command when the task has a deadline.

**Format:** `deadline DESCRIPTION /by TIME`

Example:

```text
deadline submit draft /by Friday 6pm
```

Notes:

- `/by` must be included
- the time can be written in your own style
- Elden saves the time text as it is

### Add an event task

Use this command when the task has a start time and an end time.

**Format:** `event DESCRIPTION /from START /to END`

Example:

```text
event CS2103 meeting /from 2pm /to 4pm
```

Notes:

- both `/from` and `/to` must be included
- Elden keeps both time values exactly as entered

### List all tasks

Use this command to see every task currently stored.

**Format:** `list`

Example:

```text
list
```

A possible output looks like this:

```text
1.[T][ ] read notes
2.[D][X] submit report (by: Friday)
3.[E][ ] team meeting (from: 2pm to: 4pm)
```

This command is useful when:

- you want to check all current tasks
- you want to see the task numbers
- you want to confirm the correct task before using `mark`, `unmark`, or `delete`

### Mark a task as done

Use this command to mark one task as completed.

**Format:** `mark TASK_NUMBER`

Example:

```text
mark 2
```

After that, the task will show `[X]`.

### Unmark a task

Use this command to change a completed task back to not done.

**Format:** `unmark TASK_NUMBER`

Example:

```text
unmark 2
```

After that, the task will show `[ ]` again.

### Delete a task

Use this command to remove one task from the list.

**Format:** `delete TASK_NUMBER`

Example:

```text
delete 3
```

It is better to use `list` first before deleting, so you do not remove the wrong task.

### Find tasks

Use this command to search for tasks whose descriptions contain a keyword.

**Format:** `find KEYWORD`

Example:

```text
find report
```

Notes:

- `find report` can match `submit report`
- `find Report` can also match the same task
- matching is based on the task description only

Important:

The result list from `find` is only for checking matches. If you want to use `mark`, `unmark`, or `delete` after that, use `list` again to confirm the task number in the main list.

### Exit the program

Use this command to close Elden.

**Format:** `bye`

Example:

```text
bye
```

## Common Errors

### Empty description

These inputs are invalid:

```text
todo
deadline /by Friday
event /from 2pm /to 4pm
```

Reason: the task description cannot be empty.

### Missing `/by`, `/from`, or `/to`

These inputs are also invalid:

```text
deadline submit draft
event project meeting /from 2pm
```

Make sure the command follows the exact format shown above.

### Invalid task number

These examples will fail:

```text
mark 0
delete 99
unmark abc
```

The task number must:

- be an integer
- exist in the current task list

### Command word written in uppercase

Inputs like `List` or `Todo read book` are invalid.

Use lowercase command words such as:

```text
list
todo read book
```
