The online store API allows frontend developers to interact with the backend services for managing products, categories, and shopping carts. This API follows RESTful principles and is built using Spring Boot.

Base URL

The base URL for the API is: http://localhost:8080/api/v1
Authentication

The API uses token-based authentication. Developers must include a valid token in the Authorization header for all requests.
Endpoints

Products

Get All Products

Endpoint: GET /products
Description: Retrieves a list of all products.
Response:
json

Verify

Open In Editor
Run
Copy code
[
{
"id": 1,
"name": "Product A",
"price": 29.99,
"quantity": 100
},
...
]
Get Product by ID

Endpoint: GET /products/{id}
Description: Retrieves a single product by its ID.
Response:
json

Verify

Open In Editor
Run
Copy code
{
"id": 1,
"name": "Product A",
"price": 29.99,
"quantity": 100
}
Create Product

Endpoint: POST /products
Description: Creates a new product.
Request Body:
json

Verify

Open In Editor
Run
Copy code
{
"name": "Product A",
"price": 29.99,
"quantity": 100
}
Response:
json

Verify

Open In Editor
Run
Copy code
{
"id": 1,
"name": "Product A",
"price": 29.99,
"quantity": 100
}
Update Product

Endpoint: PUT /products/{id}
Description: Updates an existing product.
Request Body:
json

Verify

Open In Editor
Run
Copy code
{
"name": "Updated Product A",
"price": 19.99,
"quantity": 50
}
Response:
json

Verify

Open In Editor
Run
Copy code
{
"id": 1,
"name": "Updated Product A",
"price": 19.99,
"quantity": 50
}
Delete Product

Endpoint: DELETE /products/{id}
Description: Deletes a product by its ID.
Response:
json

Verify

Open In Editor
Run
Copy code
{
"message": "Product with ID 1 has been deleted successfully"
}
Categories

Get All Categories

Endpoint: GET /categories
Description: Retrieves a list of all categories.
Create Category

Endpoint: POST /categories
Description: Creates a new category.
Shopping Cart

Get Cart Items

Endpoint: GET /cart
Description: Retrieves items in the shopping cart.
Add Item to Cart

Endpoint: POST /cart
Description: Adds a product to the shopping cart.
Error Handling

The API returns standard HTTP status codes to indicate the success or failure of requests:
200 OK: Successful request.
201 Created: Resource created successfully.
400 Bad Request: Invalid request format.
404 Not Found: Resource not found.
500 Internal Server Error: Unexpected server error.
Testing the API

Use tools like Postman or curl to test the API endpoints. Ensure to include the Authorization header with a valid token for protected routes.
Conclusion

This documentation provides a comprehensive overview of the online store API built with Spring Boot. Frontend developers can use this guide to understand how to interact with the API effectively.