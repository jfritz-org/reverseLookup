## Requirements
* maven 3.3.x (https://maven.apache.org/)

## Build
```mvn clean install```

## Tests
* ```mvn test``` to execute all tests

## Branches
This project uses the gitflow branching model (see [here](http://nvie.com/posts/a-successful-git-branching-model/) and  [here](https://jeffkreeftmeijer.com/git-flow/)).

It uses the [jgitflow maven plugin](https://bitbucket.org/atlassian/jgit-flow/wiki/Home)
 
To start a new branch to implement a new feature type ```mvn jgitflow:feature-start```
To merge a feature branch back into develop use ```mvn jgitflow:feature-finish```.

To create a new release from develop branch use ```mvn jgitflow:release-start``` and merge it back to develop and to master using ```mvn jgitflow:release-finish```.

To create a hotfix using the latest commit in master branch use ```mvn jgitflow:hotfix-start``` and use ```mvn jgitflow:hotfix-finish``` to merge it back to master and develop branches.
