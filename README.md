# Advent of Code 2022 - Kotlin

A simple setup to test solutions for the Advent of Code challenge in Kotlin.

I am trying to keep the setup basic to reduce the overhead when running the [Repl](https://github.com/nathanjent/Advent-of-Code-2022---Kotlin).

The following commands are used to compile and run all the tests.

    kotlinc -cp target/dependency/* -d target/classes $(find . -type f -name '*.kt') $(find . -type f -name '*.kts') $(find . -type f -name '*.java')
    
    kotlin -cp target/dependency/* -cp target/classes org.junit.platform.console.ConsoleLauncher --fail-if-no-tests --scan-classpath