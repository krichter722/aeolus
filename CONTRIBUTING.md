# Contributing guidelines
Contributions are welcome. Send a pull request on [github.com] or a patch per mail if you have or insist to.

## Code formatting
Code formatting is done automatically with the `maven-java-formatter-plugin`.

## Code quality
Code quality is checked using `checkstyle` which has been integrated in the `maven` livecycle using the `maven-checkstyle-plugins`. You can't build the application if the validation fails.

## Tests
Some unit tests are based on random values. If you encounter a test failure, please run the test at least 3 times in order to evaluate it's severeness (if it fails only one of the 3 times, you can probably ignore it). Please use `mvn clean verify` as `maven` target. Is the failre persists, please [open an issue](https://github.com/mjsax/aeolus/issues).

## Java
Due to fact that `storm` is an irreplacable dependency and that it's based on Java 6, we have to stick with it and can only upgrade as soon as `storm` builds with Java 7.

This prevents you from using Hibernate > 4.3 which might be interesting for implementation of storage backends for historical quieries (see documentation of the `TollDataStore` interface for details).

[1]:https://github.com/mjsax/aeolus
