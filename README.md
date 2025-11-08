# Refactoring Legacy Code Tutorial

Code examples for [Refactoring Legacy Code Tutorial](https://ibanfr.github.io/xp/tutorials/refactoring-legacy-code/).

See the checklist below for the complete list of refactorings applied in the tutorial.

## Step 1: Removing the clutter

- [ ] Run `XMLToJsonTest` with coverage.
- [ ] Rename variable `url` to `urlToTOC` - enables better understanding of the variable's purpose.
- [ ] Remove dead/commented code - improves code clarity and maintainability.
- [ ] Remove the cast to Element - unnecessary and clutters the code.
- [ ] Convert wrapper type `Boolean` to primitive `boolean` - avoids unnecessary object creation and potential null pointer exceptions.
- [ ] [Decompose conditional] - extract conditional code to clearly named method.
- [ ] Remove else branch with empty body - simplifies the code by removing unnecessary branches.
- [ ] Remove unnecessary `continue` statement - enhances code readability.
- [ ] Use `isEmpty()` to check whether the collection is empty or not.
- [ ] Remove unnecessary `main` method - cleans up the code by removing unused entry points.
- [ ] Replace `equals("")` with `isEmpty()` - improves readability and performance.

## Step 2: Reducing Complexity and Composing Methods

- [ ] Simplify `hasChildren` variable assignment by removing if else statements - reduces nesting and improves readability.
- [ ] Inline `hasChildren` variable - eliminates unnecessary variable and simplifies the code.
- [ ] Extract method `getNode` to reduce complexity before start iterating - breaks down large methods into smaller, more manageable pieces.
- [ ] Extract method `processElement` to handle element processing - Use `option`+`Up Arrow` to expand your selection.
- [ ] Extract method `closeJson` to handle JSON closing logic
- [ ] Extract method `getJsonForDocument` to inject a Document instead of obtaining it inside the method.

## Step 3: Refactoring to Testability

Introduce further refactorings to improve testability and maintainability.

### Replace Function with Command

[Replace Function with Command] aka [Replace Method with Method Object] encapsulates behavior in command objects for better testability.

- [ ] Extract method `toJsonString(String, Element)` to convert a Document element to JSON string.
- [ ] Create class `DocumentElement` to represent document elements - encapsulates related data and behavior.
- [ ] Add `DocumentElement` as parameter to `toJsonString` method.
- [ ] Inline method `hasChildren` - make the code accessible from `toJsonString` method.
- [ ] Convert `toJsonString` method to an instance method of `DocumentElement` - improve encapsulation and support polymorphism.
- [ ] Extract method `hasChildren` in `DocumentElement` class.
- [ ] Create constructor `DocumentElement(String)` - pass arguments to the command on the constructor.
- [ ] Use `xPathString` field in `toJsonString` method - utilize instance fields for better encapsulation.
- [ ] Safe delete `xPathString` parameter from `toJsonString` method - remove unused parameters to clean up the method signature.
- [ ] Add `Element` parameter to `DocumentElement` constructor - ensure all necessary data is provided at instantiation.
- [ ] Use `element` field in `toJsonString` method - utilize instance fields for better encapsulation.
- [ ] Safe delete `element` parameter from `toJsonString` method - further clean up the method signature.
- [ ] Introduce field `jsonString` to store the result of `toJsonString` method - maintain state within the command object.
- [ ] Introduce field `elementName` to store the element name - move the function state into the command object.
- [ ] Introduce field `attributes` to store the element attributes - move the function state into the command object.
- [ ] Introduce field `title` to store the element title - move the function state into the command object.
- [ ] Introduce field `file` to store the element filename - move the function state into the command object.
- [ ] Extract method `processDocAttributes` in `DocumentElement` class - break down `toJsonString` methods into smaller pieces.
- [ ] Extract method `addStateClosed` in `DocumentElement` class - compose method to handle state closing logic.
- [ ] Extract method `closeElement` in `DocumentElement` class - compose method to handle element closing logic.
- [ ] Extract method `processFolderAttributes` in `DocumentElement` class - break down `toJsonString` methods into smaller pieces.

### Replace Conditional with Polymorphism

[Replace Conditional with Polymorphism] uses polymorphism to handle conditional logic.

- [ ] Extract method `processElement()` in `DocumentElement` class - extract conditional statement into its own method.
- [ ] [Encapsulate variable] `elementName` - self-encapsulate the type code.
- [ ] [Replace Constructor with Factory Function] aka Replace Constructor with Factory Method - use factory methods to create an instance of `DocumentElement`.
- [ ] Create `DocElement` subclass and add selector logic for `doc` type code value in the factory method.
- [ ] Override the type code getter in `DocElement` class to return the literal type code value.
- [ ] Create `FolderElement` subclass and add selector logic for `folder` type code value in the factory method.
- [ ] Override the type code getter in `FolderElement` class to return the literal type code.
- [ ] Remove type code field `elementName` from `DocumentElement` class.
- [ ] Add default branch in the factory method to handle unexpected type code values.
- [ ] Make `getElementName` an abstract method and `DocumentElement` an abstract class.
- [ ] Override `processElement` method in `DocElement` class. Copy the relevant logic from the conditional statement.
- [ ] [Push Down Method] `processDocAttributes` to `DocElement` class - move method to the subclass where it's relevant.
- [ ] Rename `processDocAttributes` method in `DocElement` class to `processAttributes` - generalize method name for better readability.
- [ ] [Push Down Method] `addStateClosed` to `DocElement` class - move method to the subclass where it's relevant.
- [ ] [Push Down Method] `hasChildren` to `DocElement` class - move method to the subclass where it's relevant.
- [ ] Override `processElement` method in `FolderElement` class. Copy the relevant logic from the conditional statement.
- [ ] [Push Down Method] `processFolderAttributes` to `FolderElement` class - move method to the subclass where it's relevant.
- [ ] Rename `processFolderAttributes` method in `FolderElement` class to `processAttributes` - generalize method name for better readability.
- [ ] Declare `processElement` method in `DocumentElement` class as abstract - enforce implementation in subclasses.
- [ ] Safe delete `getElementName` method from `DocumentElement` class and its subclasses - clean up unused methods.


[Decompose conditional]: https://refactoring.guru/decompose-conditional
[Replace Function with Command]: https://refactoring.guru/replace-function-with-command
[Replace Method with Method Object]: https://refactoring.guru/replace-method-with-method-object
[Replace Conditional with Polymorphism]: https://refactoring.com/catalog/replaceConditionalWithPolymorphism.html
[Replace Type Code with Subclasses]: https://refactoring.com/catalog/replaceTypeCodeWithSubclasses.html
[Encapsulate Variable]: https://refactoring.com/catalog/encapsulateVariable.html
[Replace Constructor with Factory Function]: https://refactoring.com/catalog/replaceConstructorWithFactoryFunction.html
[Push Down Method]: https://refactoring.com/catalog/pushDownMethod.html
