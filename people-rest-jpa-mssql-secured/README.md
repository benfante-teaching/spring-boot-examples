# People REST API example

A simple REST API example using Spring Boot.

## Running the application

To run the application, execute the following command:

```shell
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.

## Testing the application

The application can be tested using the following command:

```shell
./mvnw test
```

## Security

This project implements security using JSON Web Tokens (JWT). The following endpoints are secured:

- `/api/v1/people` (GET, POST, PUT, DELETE), allowed for users with role `USER`
- `/admin` (GET), allowed for users with role `ADMIN`

The following users are defined in the `UserDetailsService`:

- `user` with password `secret` and role `USER`
- `admin` with password `super-secret` and rols `ADMIN` and `USER`

For authentication, use the `/api/v1/auth/login` endpoint, with `POST` HTTP method, with the following JSON payload:

```json
{
  "username": "<username>",
  "password": "<password>"
}
```

For example, using `curl`:

```shell
curl -X POST -H "Content-Type: application/json" \
  -d '{"username": "user", "password": "secret"}' \
  http://localhost:8080/api/v1/auth/login
```

The response will contain the JWT token, for example:

```text
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzI5ODY0NzE5LCJleHAiOjE3Mjk4NjgzMTl9.nGIls-2s49XookCKE1ouyWj2uU_XBGxXpm4d6Of-34w
```

It should be used in the `Authorization` header for the secured endpoints. For example, using `curl`:

```shell
curl -X 'GET' \
  'http://localhost:8080/api/v1/people' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzI5ODY0NzE5LCJleHAiOjE3Mjk4NjgzMTl9.nGIls-2s49XookCKE1ouyWj2uU_XBGxXpm4d6Of-34w'
```

The JWT token can be used in **Swagger UI** as well. To do that, click on the green `Authorize` button (at the top-right of the page) and enter the token in the `Value` field, then click on the `Authorize` button, close the dialog, and use the endpoints.

The JWT token is valid for 60 minutes. After that, the user needs to authenticate again.

### Testing the `PeopleController`

After adding of the security layer, the `PeopleController` tests need to be updated to include the needed security configuration and context. The `PeopleControllerTest` class contains the necessary changes.

In particular:

- Included the security configuration:
    ```java
    @Import({PeopleTestConfig.class, SecurityConfig.class})
    ```
- Added the `@WithMockUser` annotation to the test methods that require authentication, using a specific user and roles:
    ```java
    @WithMockUser(username = "user", roles = {"USER"})
    ```

    For using the `@WithMockUser` annotation, the `spring-security-test` dependency needs to be included in the `pom.xml` file:

    ```xml
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```
- Added the `JwtService` bean to the test context (in the `PeopleTestConfig` class):
    ```java
    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
    ```
