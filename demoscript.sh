
curl -X GET "http://localhost:8080/api/1.0/screenings?max=2020-03-24T18%3A30%3A00.703Z&min=2020-03-24T12%3A30%3A00.703Z" -H "accept: application/json"

echo -e "\n"

curl -X GET "http://localhost:8080/api/1.0/screenings/3?row=3" -H "accept: application/json"

echo -e "\n"

curl -X POST "http://localhost:8080/api/1.0/reservation" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\": \"Andrew\", \"lastName\": \"Korzhov\", \"screeningId\": 7, \"tickets\": [ { \"row\": 6, \"seat\": 2, \"ticketType\": \"adult\" }, { \"row\": 6, \"seat\": 3, \"ticketType\": \"adult\" }, { \"row\": 6, \"seat\": 4, \"ticketType\": \"adult\" } ]}"

echo -e "\n"

curl -X POST "http://localhost:8080/api/1.0/reservation" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\": \"Test\", \"lastName\": \"Test-Surname\", \"screeningId\": 7, \"tickets\": [ { \"row\": 6, \"seat\": 6, \"ticketType\": \"child\" } ]}"

echo -e "\n"