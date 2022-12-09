#!/bin/sh

# Run the following to download dependencies into target/dependency directory:
# mvn dependency:copy-dependencies

# compile
kotlinc -cp target/dependency/* -d target/classes $(find . -type f -name '*.kt') $(find . -type f -name '*.kts') $(find . -type f -name '*.java')

# run
kotlin -cp target/dependency/* -cp target/classes org.junit.platform.console.ConsoleLauncher --fail-if-no-tests --scan-classpath
