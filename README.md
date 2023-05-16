# vrushal-sdk
# Lord of the Rings SDK

The Lord of the Rings SDK is a Kotlin library that provides easy access to the Lord of the Rings API, allowing developers to retrieve information about movies and quotes from the Lord of the Rings series.

## Installation

To use the Lord of the Rings SDK in your Kotlin project, you can add the following dependency to your Gradle build file:

```kotlin
groovy
dependencies {
implementation 'com.liblab:lordoftheringssdk:1.0.0'
}
```


Replace `com.liblab:lordoftheringssdk:1.0.0` with the actual dependency coordinates.



## Usage

To get started with the Lord of the Rings SDK, follow these steps:

1. Obtain an API key from the Lord of the Rings API. Visit https://the-one-api.dev/ to sign up and get your API key.

2. Create an instance of the `LordOfTheRingsSDK` class, passing your API key as the constructor parameter:

```kotlin
   val apiKey = "your-api-key"
   val lordOfTheRingsSDK = LordOfTheRingsSDK(apiKey)
```

3. Use the available methods to retrieve movies and quotes:

   ```kotlin
   // Get a list of movies
   val movies = lordOfTheRingsSDK.getMovies()

   // Get a specific movie by ID
   val movie = lordOfTheRingsSDK.getMovieById("movie-id")

   // Get quotes for a specific movie
   val quotes = lordOfTheRingsSDK.getMovieQuotes("movie-id")

   // Get all quotes
   val allQuotes = lordOfTheRingsSDK.getQuotes()

You can try out examples in `Runner.kt`. The file uses the sdk methods and prints out the output of the above methods

4. Handle any exceptions that may occur during API requests. The SDK throws `LordOfTheRingsSDKException` for errors.

## Testing

The Lord of the Rings SDK includes unit tests to ensure the correctness of its functionality. To run the tests, follow these steps:

1. Clone the repository from GitHub:

```kotlin
git clone https://github.com/your-username/lord-of-the-rings-sdk.git
```

2. Navigate to the project directory:

```kotlin
cd lord-of-the-rings-sdk
```

3. Build and run the tests using Gradle:

```kotlin
./gradlew test
```

The test results will be displayed in the console.

## License

This SDK is released under the [MIT License](LICENSE).


Make sure to replace `"your-api-key"` with instructions on how the users can obtain their own API key. Additionally, provide information on how to replace `"com.liblab:lordoftheringssdk:1.0.0"` with the actual dependency coordinates for your SDK.
