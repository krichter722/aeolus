# Some advices to deal with coding issues
## Sharing of mocking code
In order to share mocking code in test classes it'd be necessary to include the mocking library (currently `mockito`) as `maven` runtime dependency. This is not accepted by the project.

# `TollDataStore` implementations
The enforced usage of Java 6 (see `CONTRIBUTING.md`) prevents the usage of Hibernate as JPA provider which depends on Java 7.
