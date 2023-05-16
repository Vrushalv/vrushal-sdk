## SDK Design

The design of the LordOfTheRingsSDK follows a simple and modular structure to provide an easy-to-use interface for accessing the Lord of the Rings API. Here are the key design aspects:

1. Class Structure:
    - `LordOfTheRingsSDK`: This is the main class of the SDK that encapsulates the functionality to interact with the Lord of the Rings API.
    - `LordOfTheRingsSDKException`: This custom exception class is used to handle and propagate any exceptions that occur during API interactions.

2. API Interaction:
    - The SDK uses the `OkHttpClient` library to handle HTTP requests to the API endpoints.
    - The `Jackson` library is utilized for JSON serialization and deserialization of API responses.
    - The SDK provides methods for retrieving movies, movie details by ID, movie quotes, and quotes from the API.

3. Error Handling:
    - The SDK throws custom `LordOfTheRingsSDKException` to handle and propagate any errors that occur during API interactions.
    - The exception messages provide relevant information about the error, including the HTTP status code and error message received from the API.

4. Authentication:
    - The SDK supports API key-based authentication by accepting the API key as a constructor parameter. It can be provided explicitly or read from the environment using the `Constants.TOKEN` constant.

5. Testability:
    - The SDK is designed to be easily testable by using dependency injection. The `OkHttpClient` and `ObjectMapper` instances are created within the SDK class, allowing them to be easily mocked in unit tests.
    - Unit tests cover the major functionality of the SDK, ensuring its correctness and handling of different scenarios.

6. Documentation:
    - The SDK includes inline documentation for classes, methods, and parameters to provide clear explanations of their purpose and usage.
    - A README file is provided to guide users on how to install, use, and test the SDK.

Overall, the LordOfTheRingsSDK is designed to provide a convenient and reliable way to access the Lord of the Rings API, abstracting away the complexities of API communication and allowing developers to focus on integrating the data into their applications with ease.