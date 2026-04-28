# todo-app-spring
A simple todo app built with Spring Boot

## Migrate from Spring Framework `@Nullable` to JSpecify's `@Nullable`

```shell
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run --define rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-migrate-java:RELEASE --define rewrite.activeRecipes=org.openrewrite.java.jspecify.MigrateFromSpringFrameworkAnnotations --define rewrite.exportDatatables=true
```

See https://docs.openrewrite.org/recipes/java/jspecify/migratefromspringframeworkannotations

## Nullability plugin

https://ik.am/entries/900/en

## Nullability plugin configuration

https://github.com/making/nullability-maven-plugin#configuration
