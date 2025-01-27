
# User Management App

This project is a simple user management system consisting of a **Spring Boot backend** and an **Android app**. The backend is built using **Spring Boot** and connects to a **PostgreSQL database**, while the Android app communicates with the backend via **Retrofit** for API calls.

---

## Backend Setup (Spring Boot + PostgreSQL)

### Prerequisites:
- **Java 17** or later
- **Maven** (for dependency management)
- **PostgreSQL** installed and running

### Steps to Set Up:

1. Clone the repository.
	```bash 
	git clone https://github.com/Ghanshyam32/android-springboot-app.git

2. Set up **PostgreSQL**:
   - Create a new database (e.g., `user_management`).
   - Make sure PostgreSQL is running and accessible.

3. Configure the **application.properties** file:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/user_management
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
4. Run the Backend Application:
- To start the Spring Boot backend:
	```bash
	 cd backend
	./mvnw spring-boot:run

## Android App Setup
### 1. **Configure Retrofit Client**

In the `RetrofitClient.java` file of your Android app (located in the `android/` folder), replace `BASE_URL` with the IP address of the machine running the Spring Boot backend:

If you're running both the Spring Boot application and the Android app on the same machine, `localhost` or `127.0.0.1` will work. If you're testing the Android app on a physical device, use your machine's IP address (e.g., `http://192.168.1.100:8080`).

### 2. **Build and Run the Android App**

Open the Android project in **Android Studio**, and build the project. Run it either on a physical device or an emulator. The app will now communicate with the backend API.

----------

## API Endpoints

### 1. **POST /api/users**: Add a new user
- Request Body:
`{
  "name": "John Doe",
  "email": "johndoe@example.com"
}` 

- Response:
`{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}` 

### 2. **GET /api/users**: Retrieve all users

- Response:
`[
  {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@example.com"
  },
  {
    "id": 2,
    "name": "Jane Doe",
    "email": "janedoe@example.com"
  }
]` 

----------

## Features

### Backend (Spring Boot):

-   Handles API requests for adding and retrieving users.
-   Uses PostgreSQL for storing user data.
-   Supports CORS to allow connections from different origins (like your Android app).

### Android App:

-   Allows users to add new users by providing a name and email.
-   Displays a list of all users retrieved from the Spring Boot backend in a `RecyclerView`.
-   Displays error messages for failed network requests or missing fields.

----------

## Error Handling

Both the backend and Android app include error handling. If something goes wrong, like failed network requests or missing fields, appropriate error messages will be displayed.

----------

## Contributing
Feel free to fork the repository, open issues, and submit pull requests.

## Contact
For questions, suggestions, or feedback, feel free to contact us:
- Email: [Ghanshyam Mishra](mailto:ghanshyammishra3205615@gmail.com)
- LinkedIn: [Ghanshyam Mishra](https://www.linkedin.com/in/ghanshyam-mishra-83949a124/)
