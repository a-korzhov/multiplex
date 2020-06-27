# Test project. Multiplex system to book tickets in cinema.

## Functionality:
- The system lists movies available in the given time interval - title and screening times
- The system gives information regarding screening room and available seats
- The system gives back the total amount to pay and reservation expiration time
- There cannot be a single place left over in a row between two already reserved places

## Technologies with versions:
- Java 11
- Postgres 12.2
- Spring Boot 2.2.5 
- Swagger 2.0

## Start application:
- ``` ./runscript.sh DB_NAME DB_USER DB_PASSWORD ```

## Demo requests:
- ``` ./demoscript.sh ```

## Assumes
 Assume, that we have: 
 - 9 screenings
 - 3 rooms
 - room has 6*9 seats (9 rows, 6 seats per row)

## ENDPOINTS
 
- **The system lists movies available in the given time interval - title and screening times:**
  
  ``` GET http://localhost:8080/api/1.0/screenings?max=2020-03-24T18%3A30%3A00.703Z&min=2020-03-24T12%3A30%3A00.703Z ```

- **The system gives information regarding screening room and available seats:**

  ``` GET http://localhost:8080/api/1.0/screenings/6?row=5 ```

- **The system gives back the total amount to pay and reservation expiration time:**
  
  ``` POST http://localhost:8080/api/1.0/reservation ``` 
  ```json
  {
  "firstName": "string",
  "lastName": "string",
  "row": 0,
  "tickets": [
    {
      "seat": 0,
      "ticketType": "string"
    }
  ],
   "screeningId": 0
  } 
  ```

  **To see, if you can book seat #6 ("There cannot be a single place left over in a row between two already reserved places."):**
  
  ***Set invalid data(e.g. combination of invalid seats, non-existent screening)***
  
  ``` POST "http://localhost:8080/api/1.0/reservation" ```
  ```json
   {
  "firstName": "string",
  "lastName": "string",
  "row": 0,
  "tickets": [
    {
      "seat": 0,
      "ticketType": "string"
    }
  ],
  "screeningId": 0
  }
  ```

## Or you can use SWAGGER UI

- ``` http://localhost:8080/swagger-ui.html#/ ```
