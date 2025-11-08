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

Introduce further refactorings to improve testability and maintainability.

### Replace Function with Command

[Replace Function with Command] aka [Replace Method with Method Object] encapsulates behavior in command objects for better testability.

1. Extract method `toJsonString(String, Element)` to convert a Document element to JSON string.
2. Create class `DocumentElement` to represent document elements - encapsulates related data and behavior.
3. Add `DocumentElement` as parameter to `toJsonString` method.
4. Inline method `hasChildren` - make the code accessible from `toJsonString` method.
5. Convert `toJsonString` method to an instance method of `DocumentElement` - improve encapsulation and support polymorphism.
6. Extract method `hasChildren` in `DocumentElement` class.
7. Create constructor `DocumentElement(String)` - pass arguments to the command on the constructor.
8. Use `xPathString` field in `toJsonString` method - utilize instance fields for better encapsulation.
9. Safe delete `xPathString` parameter from `toJsonString` method - remove unused parameters to clean up the method signature.
10. Add `Element` parameter to `DocumentElement` constructor - ensure all necessary data is provided at instantiation.
11. Use `element` field in `toJsonString` method - utilize instance fields for better encapsulation.
12. Safe delete `element` parameter from `toJsonString` method - further clean up the method signature.
13. Introduce field `jsonString` to store the result of `toJsonString` method - maintain state within the command object.
14. Introduce field `elementName` to store the element name - move the function state into the command object.
15. Introduce field `attributes` to store the element attributes - move the function state into the command object.
16. Introduce field `title` to store the element title - move the function state into the command object.
17. Introduce field `file` to store the element filename - move the function state into the command object.
18. Extract method `processDocAttributes` in `DocumentElement` class - break down `toJsonString` methods into smaller pieces.
19. Extract method `addStateClosed` in `DocumentElement` class - compose method to handle state closing logic.
20. Extract method `closeElement` in `DocumentElement` class - compose method to handle element closing logic.
21. Extract method `processFolderAttributes` in `DocumentElement` class - break down `toJsonString` methods into smaller pieces.

### Replace Conditional with Polymorphism

[Replace Conditional with Polymorphism] uses polymorphism to handle conditional logic.

1. Extract method `processElement()` in `DocumentElement` class - extract conditional statement into its own method.
2. [Encapsulate variable] `elementName` - self-encapsulate the type code.
3. [Replace Constructor with Factory Function] aka Replace Constructor with Factory Method - use factory methods to create an instance of `DocumentElement`.
4. Create `DocElement` subclass and add selector logic for `doc` type code value in the factory method.
5. Override the type code getter in `DocElement` class to return the literal type code value.
6. Create `FolderElement` subclass and add selector logic for `folder` type code value in the factory method.
7. Override the type code getter in `FolderElement` class to return the literal type code.
8. Remove type code field `elementName` from `DocumentElement` class.
9. Add default branch in the factory method to handle unexpected type code values.
10. Make `getElementName` an abstract method and `DocumentElement` an abstract class.
11. Override `processElement` method in `DocElement` class. Copy the relevant logic from the conditional statement.
12. [Push Down Method] `processDocAttributes` to `DocElement` class - move method to the subclass where it's relevant.
13. Rename `processDocAttributes` method in `DocElement` class to `processAttributes` - generalize method name for better readability.
14. [Push Down Method] `addStateClosed` to `DocElement` class - move method to the subclass where it's relevant.
15. [Push Down Method] `hasChildren` to `DocElement` class - move method to the subclass where it's relevant.
16. Override `processElement` method in `FolderElement` class. Copy the relevant logic from the conditional statement.
17. [Push Down Method] `processFolderAttributes` to `FolderElement` class - move method to the subclass where it's relevant.
18. Rename `processFolderAttributes` method in `FolderElement` class to `processAttributes` - generalize method name for better readability.
19. Declare `processElement` method in `DocumentElement` class as abstract - enforce implementation in subclasses.
20. Safe delete `getElementName` method from `DocumentElement` class and its subclasses - clean up unused methods.
 

[Decompose conditional]: https://refactoring.guru/decompose-conditional
[Replace Function with Command]: https://refactoring.guru/replace-function-with-command
[Replace Method with Method Object]: https://refactoring.guru/replace-method-with-method-object
[Replace Conditional with Polymorphism]: https://refactoring.com/catalog/replaceConditionalWithPolymorphism.html
[Replace Type Code with Subclasses]: https://refactoring.com/catalog/replaceTypeCodeWithSubclasses.html
[Encapsulate Variable]: https://refactoring.com/catalog/encapsulateVariable.html
[Replace Constructor with Factory Function]: https://refactoring.com/catalog/replaceConstructorWithFactoryFunction.html
[Push Down Method]: https://refactoring.com/catalog/pushDownMethod.html
