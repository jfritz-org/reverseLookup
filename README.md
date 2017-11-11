## Requirements
* maven 3.3.x (https://maven.apache.org/)

## Build
```mvn clean install```

## Tests
* ```mvn test``` to execute all tests

## Create a new release
* Commit and push all pending changes to GIT
* ```mvn release:prepare```
* ```mvn release:perform```
* now you should have a release copy in your local maven repository as well as in target subdirectory of your project
