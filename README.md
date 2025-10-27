# Refactoring Legacy Code Tutorial

Code examples for [Refactoring Legacy Code Tutorial](https://ibanfr.github.io/xp/tutorials/refactoring-legacy-code/)

## Refactoring List

1. Run with coverage.
2. Rename variable `url` to `urlToTOC` - enables better understanding of the variable's purpose.
3. Remove dead/commented code - improves code clarity and maintainability.
4. Remove the cast to Element - unnecessary and clutters the code.
5. Convert wrapper type `Boolean` to primitive `boolean` - avoids unnecessary object creation and potential null pointer exceptions.
6. [Decompose conditional] - extract conditional code to clearly named method.
7. Remove else branch with empty body - simplifies the code by removing unnecessary branches.
8. Remove unnecessary `continue` statement - enhances code readability.



[Decompose conditional]: https://refactoring.guru/decompose-conditional