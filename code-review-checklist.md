# Code Review Checklist

**Reviewer Name:** [Your Name]
**Date:** [Date]
**Branch:** Review

## Instructions
Review ALL source files (in main not test) in the project and identify defects using the categories below. Log at least 5 defects total:
- At least 1 from CS (Coding Standards)
- At least 1 from CG (Code Quality/General)
- At least 1 from FD (Functional Defects)
- Remaining can be from any category

## Review Categories

- **CS**: Coding Standards (naming conventions, formatting, style violations)
- **CG**: Code Quality/General (design issues, code smells, maintainability)
- **FD**: Functional Defects (logic errors, incorrect behavior, bugs)
- **MD**: Miscellaneous (documentation, comments, other issues)

## Defect Log

| Defect ID | File | Line(s) | Category | Description | Severity |
|-----------|------|---------|----------|-------------|----------|
| 1 | Checkout.java | 246 | FD | Compares two strings with == | Medium |
| 2 | Patron.java | 130-132 | CG | chkSuspend does the same thing as isAccountSuspended | Low |
| 3 | Book.java | 112-115 | FD | resetAvailability sets available to be true always | High |
| 4 | Book.java | 1-2 | CS | Unused imports | Low |
| 5 | Book.java | 87-90 | FD | setAvailableCopies doesn't make sure input is valid number | High |
| 6 | Checkout.java | 44-50 | FD | No check for a null input in the functions | Medium |
| 7 | Patron.java | 118-123 | CS | The Statements don't have whitespaces between them | Low |
| 8 | Checkout.java | 17 | MD | None/unfished comment for history | Low |
| 9 | Patron.java | 151 | CG | Empty else statement | Low |

**Severity Levels:**
- **Critical**: Causes system failure, data corruption, or security issues
- **High**: Major functional defect or significant quality issue
- **Medium**: Moderate issue affecting maintainability or minor functional problem
- **Low**: Minor style issue or cosmetic problem

## Example Entry

| Defect ID | File          | Line(s) | Category | Description                                | Severity |
|-----------|---------------|---------|----------|--------------------------------------------|----------|
| 1 | Checkout.java | 17      | CS       | Variable bookList misleading - Map not List | Medium |
| 2 | Book.java     | 107     | FD       | Magic number 100 should be totalCopies      | High |

## Notes
- Be specific with line numbers
- Provide clear, actionable descriptions
- Consider: readability, maintainability, correctness, performance, security
- Focus on issues that impact code quality or functionality
