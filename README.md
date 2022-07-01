# Simple Stock Market

## Author
 Sangam Pandey

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system 

## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)


## Running the application locally

There are several ways to run `Super Simple Stock Market` on your local machine. One way is to execute the `main` method in the `com.sse.stock.market.StockMarketApplication` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### URLs

|  URL |  Method | Remarks |
|----------|--------------|--------------|
|`http://localhost:8080/sse/trade/{stock-symbol}`                | POST | Custom Response Headers|
|`http://localhost:8080/sse/{stock-symbol}/dividend-yield`       | GET | |
|`http://localhost:8080/sse/{stock-symbol}/pe-ratio`             | GET | |
|`http://localhost:8080/sse/{stock-symbol}/volume-weight-price`  | GET | |
|`http://localhost:8080/sse/all-share-index`                     | GET | |


### Current symbols

[1] ITC
[2] MRF
[3] CIPLA
[4] HDFC
[5] ONGC

* You can view and edit these symbols through application.properties**

