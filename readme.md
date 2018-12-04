# JuleKalenderLoader 2018
Simple loader that will load all solutions located in a given package X that imlements the interface `Solution`.  

## Prerequisites

### External libraries
The following is a list of dependencies required by different parts of the loader.
#### Logging Dependencies
- logback-core-1.2.3.jar
- logback-classic-1.2.3.jar
- slf4j-api-1.7.25.jar

## Usage
The loader will run all solutions in parallell.
### Setup
The loader utilises 2 arrays to decide the constructor to use.  
- `constructorParameters` - The constructor parameter array
- `constructorInputs` - The inputs for the constructor that is defined by constructorParameters

note: there should only ever be one constructor pr solution.

### Logging
The logging system is configured using the file `logback.xml`.
The current setup will create one file for each implemented solution, so you can check the logfile for the solution you want.

## Running
- Load the project into IntelliJ or your IDE of choice
- Add the libraries described above to the classpath.
- Create a run configuration and see the solutions being run.