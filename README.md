# Gemini Client Spring Boot Application

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

This is a Spring Boot 3.x application that exposes REST endpoints to interact with Google's Gemini Generative Language APIs for text and image generation.

---

## How to Use

1. **Clone the Repository:**
    ```bash
    git clone <your-repo-url>
    cd gemini-client
    ```

2. **Update API Key:**
    Open `src/main/resources/application.yml` and replace `YOUR_API_KEY_HERE` with your actual Gemini API Key.

    ```yaml
    gemini:
      api:
        key: YOUR_ACTUAL_API_KEY
    ```

3. **Build and Run the Application:**
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```
    or directly run `GeminiClientApplication.java` from your IDE.

4. **Access Swagger UI for API Testing:**
    Open your browser and navigate to:
    ```
    http://localhost:8080/swagger-ui.html
    ```

5. **Curl Commands to Test Endpoints:**

    - **Text Generation Endpoint**
      ```bash
      curl --request POST \
        --url 'http://localhost:8080/api/gemini/text' \
        --header 'Content-Type: application/json' \
        --data '{
          "contents": [
            {
              "parts": [
                {
                  "text": "Explain how AI works"
                }
              ]
            }
          ]
        }'
      ```

    - **Image + Text Generation Endpoint**
      ```bash
      curl --request POST \
        --url 'http://localhost:8080/api/gemini/image' \
        --header 'Content-Type: application/json' \
        --data '{
          "contents": [
            {
              "parts": [
                {
                  "text": "Create a 3D rendered image of a futuristic flying car in a city with lots of greenery"
                }
              ]
            }
          ],
          "generationConfig": {
            "responseModalities": ["TEXT", "IMAGE"]
          }
        }'
      ```

---

## Key Features

- Supports both Text and Image content generation using Gemini API.
- Automatically saves generated images inside `images/` folder with a unique timestamp-based filename.
- Cleanly structured DTOs and Service layer.
- Integrated Swagger/OpenAPI for easy API exploration.
- No hardcoded file paths, all saved relative to project root.

---

## Project Structure

```
gemini-client/
├── controller/
├── service/
├── model/
├── util/
├── config/
├── resources/
└── GeminiClientApplication.java
```

---

## Requirements

- Java 17+
- Maven 3.8+

---

## Contributing

Feel free to open issues or submit pull requests for improvements!

---

## License

This project is licensed under the MIT License.
