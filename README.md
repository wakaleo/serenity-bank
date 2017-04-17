# BDD In Action Online Banking Sample Project

This sample project is used as part of the [BDD/TDD Masterclass](https://johnfergusonsmart.com/programs-courses/bdd-tdd-clean-coding/) run by [John Ferguson Smart](https://johnfergusonsmart.com/programs-courses/bdd-tdd-clean-coding/). It is a simple application that simulates the core of an online banking system. 


The project uses [Maven 3](https://maven.apache.org), Java 8, [Cucumber JVM](https://cucumber.io/) and [Spock](http://spockframework.org/). You will only need to install Java 8 and Maven 3 - the other dependencies will be downloaded as part of the build process.

We recommend [IntelliJ Idea](https://www.jetbrains.com/idea/) because of it's excellent built-in Groovy and Cucumber support.

To get started, clone this project and run `mvn install` in the root directory of the project, e.g.
```
git clone git@github.com:wakaleo/serenity-bank.git
cd serenity-bank
mvn install
```

This will run all of the tests as part of the build. To run the tests individually, just run `mvn verify`. This will generate a Serenity BDD report in the `target/site/serenity` directory.

Each branch of the project represents an exercise that is done as part of the workshop. 
