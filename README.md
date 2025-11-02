# Refactoring Legacy Code Tutorial

Code examples for [Refactoring Legacy Code Tutorial](https://ibanfr.github.io/xp/tutorials/refactoring-legacy-code/)

## Step 1: Removing the clutter

1. Run with coverage.
2. Rename variable `url` to `urlToTOC` - enables better understanding of the variable's purpose.
3. Remove dead/commented code - improves code clarity and maintainability.
4. Remove the cast to Element - unnecessary and clutters the code.
5. Convert wrapper type `Boolean` to primitive `boolean` - avoids unnecessary object creation and potential null pointer exceptions.
6. [Decompose conditional] - extract conditional code to clearly named method.
7. Remove else branch with empty body - simplifies the code by removing unnecessary branches.
8. Remove unnecessary `continue` statement - enhances code readability.
9. Use `isEmpty()` to check whether the collection is empty or not.
10. Remove unnecessary `main` method - cleans up the code by removing unused entry points.
11. Replace `equals("")` with `isEmpty()` - improves readability and performance.

## Step 2: Reducing Complexity and Composing Methods

"Good design is moving things that are related closer together and things that are unrelated further apart." - Kent Beck on Tidy First

1. Simplify `hasChildren` variable assignment by removing if else statements - reduces nesting and improves readability.
2. Inline `hasChildren` variable - eliminates unnecessary variable and simplifies the code.
3. Extract method `getNode` to reduce complexity before start iterating - breaks down large methods into smaller, more manageable pieces.
4. Extract method `processElement` to handle element processing - Use `option`+`Up Arrow` to expand your selection.
5. Extract method `closeJson` to handle JSON closing logic
6. Extract method `getJsonForDocument` to inject a Document instead of obtaining it inside the method

## Step 3: Refactoring to Testability

1. [Replace Function with Command] - encapsulates behavior in command objects for better testability.
    - Extract method `toJsonString(String, Element)` to convert a Document element to JSON string.
    - Create class `DocumentElement` to represent document elements - encapsulates related data and behavior.
    - Add `DocumentElement` as parameter to `toJsonString` method.
    - Inline method `hasChildren` - make the code accessible from `toJsonString` method.
    - Convert `toJsonString` method to an instance method of `DocumentElement` - improve encapsulation and support polymorphism.
    - Extract method `hasChildren` in `DocumentElement` class.
    - Create constructor `DocumentElement(String)` - pass arguments to the command on the constructor.
    - Use `xPathString` field in `toJsonString` method - utilize instance fields for better encapsulation.
    - Safe delete `xPathString` parameter from `toJsonString` method - remove unused parameters to clean up the method signature.



[Decompose conditional]: https://refactoring.guru/decompose-conditional
[Replace Function with Command]: https://refactoring.guru/replace-function-with-command