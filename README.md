# Weather API

## 📖 Overview

The Weather API is a robust Spring Boot application that provides real-time weather information for cities worldwide. It integrates seamlessly with the OpenWeatherMap API to fetch current weather data and utilizes Swagger UI for interactive API documentation. The application features advanced caching mechanisms using Caffeine to optimize performance and reduce API calls.

## ✨ Features

- 🌤️ Retrieve current weather data by city name
- 🌡️ Comprehensive weather information including temperature, humidity, wind speed, and weather conditions
- 📚 Interactive Swagger UI documentation for easy API testing
- ⚡ High-performance caching with Caffeine (12-hour cache expiration)
- 🏗️ Clean architecture with separation of concerns
- 🔧 Configurable API settings through application properties
- 🚀 RESTful API design following best practices

## 🛠️ Tech Stack

- **Java**: 17+
- **Spring Boot**: 3.x
- **Spring Web**: For RESTful services
- **Spring Cache**: For caching abstraction
- **Caffeine Cache**: High-performance caching library
- **Springdoc OpenAPI**: For Swagger documentation
- **OpenWeatherMap API**: External weather data provider
- **Maven**: Dependency management and build tool

## 📋 Prerequisites

Before setting up the project, ensure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 17 or later
    - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
    - Verify installation: `java -version`
- **Apache Maven**: Version 3.8 or later
    - Download from [Maven Official Site](https://maven.apache.org/download.cgi)
    - Verify installation: `mvn -version`
- **Git**: For version control
    - Download from [Git Official Site](https://git-scm.com/downloads)
    - Verify installation: `git --version`
- **IDE** (Optional but recommended): IntelliJ IDEA, Eclipse, or VS Code

## 🚀 Setup Instructions

### Step 1: Clone the Repository

```bash
git clone https://github.com/Senibo-Don-Pedro/weather_api.git
cd weather_api
```

### Step 2: Obtain OpenWeatherMap API Key

1. **Visit OpenWeatherMap**: Navigate to [https://openweathermap.org/](https://openweathermap.org/)
2. **Create Account**:
    - Click on "Sign Up" if you don't have an account
    - Fill in your details (username, email, password)
    - Verify your email address
3. **Generate API Key**:
    - Log in to your account
    - Go to "My API Keys" section in your profile
    - Click "Generate" or use the default API key provided
    - Copy your API key (it looks like: `a6d0d5d2c7b2b6f97b7cee36405158b1`)

**Important Notes about API Key:**
- Free tier allows 1,000 API calls per day
- New API keys may take up to 2 hours to activate
- Keep your API key secure and don't commit it to version control

### Step 3: Configure Application Properties

Navigate to `src/main/resources/` and ensure your `application.properties` file contains:

```properties
# Application Configuration
spring.application.name=weatherapi

# OpenWeatherMap API Configuration
openweather.api.key=YOUR_API_KEY_HERE
openweather.api.base-url=http://api.openweathermap.org/data/2.5/weather
openweather.api.default-units=metric

# Caching Configuration
spring.cache.type=caffeine
spring.cache.caffeine.spec=maximumSize=1000,expireAfterWrite=12h
```

**Replace `YOUR_API_KEY_HERE` with your actual OpenWeatherMap API key.**

### Step 4: Verify Dependencies

The project uses the following key dependencies in `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    
    <!-- Caffeine Cache -->
    <dependency>
        <groupId>com.github.ben-manes.caffeine</groupId>
        <artifactId>caffeine</artifactId>
    </dependency>
    
    <!-- Swagger Documentation -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>
</dependencies>
```

### Step 5: Build the Application

```bash
# Clean and compile the project
./mvnw clean compile

# Run tests (if available)
./mvnw test

# Package the application
./mvnw clean package
```

For Windows users, use `mvnw.cmd` instead of `./mvnw`.

### Step 6: Run the Application

**Option 1: Using Maven Spring Boot Plugin**
```bash
./mvnw spring-boot:run
```

**Option 2: Using Java JAR**
```bash
java -jar target/weatherapi-0.0.1-SNAPSHOT.jar
```

**Option 3: Using IDE**
- Import the project into your IDE
- Run the main class `WeatherApiApplication.java`

### Step 7: Verify Installation

Once the application starts successfully, you should see output similar to:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.x.x)

...
Tomcat started on port(s): 8080 (http)
Started WeatherApiApplication in X.XXX seconds
```

## 🌐 API Usage
## 📊 Swagger Documentation

### Accessing Swagger UI

Once the application is running, access the interactive API documentation at:

**Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger.html)

### Features of Swagger UI:
- 📋 Complete API documentation with request/response schemas
- 🧪 Interactive testing capabilities - test APIs directly from the browser
- 📝 Detailed parameter descriptions and examples
- 🔍 Model definitions for request and response objects
- 📤 Export functionality for API specifications

## 🏗️ Project Structure

```
weather_api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/weatherapi/
│   │   │       ├── WeatherApiApplication.java      # Main application class
│   │   │       ├── controller/
│   │   │       │   └── WeatherController.java      # REST API endpoints
│   │   │       ├── service/
│   │   │       │   └── WeatherService.java         # Business logic
│   │   │       ├── dto/
│   │   │       │   └── WeatherDto.java             # Data transfer objects
│   │   │       └── config/
│   │   │           └── CacheConfig.java            # Cache configuration
│   │   └── resources/
│   │       └── application.properties              # Configuration file
│   └── test/
│       └── java/                                   # Test classes
├── target/                                         # Compiled classes and JAR
├── pom.xml                                         # Maven dependencies
├── README.md                                       # Project documentation
└── .gitignore                                      # Git ignore rules
```

## 🔧 Code Explanation

### WeatherController.java
The main REST controller that handles HTTP requests:

```java
@RestController
@RequestMapping("/weather")
@Tag(name = "Weather API", description = "Operations related to weather information")
public class WeatherController {
    
    private final WeatherService weatherService;
    
    @Operation(summary = "Get weather by city", description = "Retrieves current weather data for a specified city")
    @GetMapping
    public ResponseEntity<WeatherDto> getWeather(@RequestParam String city) {
        WeatherDto weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }
}
```

### WeatherService.java
Contains business logic for fetching weather data:

```java
@Service
public class WeatherService {
    
    @Value("${openweather.api.key}")
    private String apiKey;
    
    @Value("${openweather.api.base-url}")
    private String baseUrl;
    
    @Cacheable("weather")
    public WeatherDto getWeather(String city) {
        // Makes API call to OpenWeatherMap
        // Transforms response to WeatherDto
        // Returns cached result for subsequent requests
    }
}
```

### WeatherDto.java
Data transfer object representing weather information:

```java
@Schema(description = "Weather information for a city")
public class WeatherDto {
    
    @Schema(description = "City name", example = "London")
    private String city;
    
    @Schema(description = "Temperature in Celsius", example = "15.5")
    private double temperature;
    
    @Schema(description = "Humidity percentage", example = "80")
    private int humidity;
    
    // Additional weather fields...
}
```

## ⚡ Caching Configuration

The application implements intelligent caching to optimize performance:

### Cache Settings:
- **Cache Type**: Caffeine (high-performance caching library)
- **Maximum Size**: 1,000 entries
- **Expiration**: 12 hours after write
- **Cache Key**: City name (case-insensitive)

### Benefits:
- 🚀 Faster response times for frequently requested cities
- 💰 Reduced API calls to OpenWeatherMap (saves on rate limits)
- 🌐 Better user experience with instant responses for cached data
- 📊 Automatic cache eviction prevents stale data

### Cache Behavior:
- First request for a city: Fetches from OpenWeatherMap API
- Subsequent requests (within 12 hours): Returns cached data
- After 12 hours: Cache expires, next request fetches fresh data

## 🔧 Configuration Options

### Application Properties Explained:

```properties
# Sets the application name (appears in logs and actuator endpoints)
spring.application.name=weatherapi

# Your OpenWeatherMap API key - NEVER commit this to version control
openweather.api.key=your_actual_api_key_here

# OpenWeatherMap API endpoint for current weather data
openweather.api.base-url=http://api.openweathermap.org/data/2.5/weather

# Default units for temperature (metric = Celsius, imperial = Fahrenheit)
openweather.api.default-units=metric

# Enables Caffeine as the caching provider
spring.cache.type=caffeine

# Cache specifications:
# - maximumSize=1000: Cache up to 1000 different cities
# - expireAfterWrite=12h: Data expires 12 hours after being cached
spring.cache.caffeine.spec=maximumSize=1000,expireAfterWrite=12h
```

### Customization Options:

1. **Change Cache Duration**:
   ```properties
   spring.cache.caffeine.spec=maximumSize=1000,expireAfterWrite=6h
   ```

2. **Change Temperature Units**:
   ```properties
   openweather.api.default-units=imperial  # For Fahrenheit
   ```

3. **Increase Cache Size**:
   ```properties
   spring.cache.caffeine.spec=maximumSize=5000,expireAfterWrite=12h
   ```

## 🔗 Useful Links

- [OpenWeatherMap API Documentation](https://openweathermap.org/api)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Swagger/OpenAPI Documentation](https://swagger.io/docs/)
- [Caffeine Cache Documentation](https://github.com/ben-manes/caffeine)

---

**Happy Coding! 🌤️**
