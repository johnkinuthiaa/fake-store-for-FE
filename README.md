# Fake store API
An online store API allows frontend developers to interact with the backend services for managing products, categories, and shopping carts. This API follows RESTful principles and is built using Spring Boot.

Base URL
The base URL for the API is: https://fake-store-for-fe.onrender.com
Authentication
The API uses token-based authentication.
Developers must include a valid token in the Authorization header for all requests except for login and registration requests
Authentication
{
"username": "johndoe",
"email": "john.doe@example.com",
"password": "securePassword123"
}
{
"statusCode": 200,
"message": "User logged in successfully",
"jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNzM2MjM4NjA4LCJleHAiOjE3MzYyNDcyNDh9.xqcJ6GGMVinng3L1yhAml5e99I9fnDZ6tCE2hueei_970hS7WmQUzNFNuvOVvyZjcRvzekAwYrgMP77N_xTvHg"
}
Endpoints

Products

Get All Products

* Endpoint: GET /api/v1/products/all/products 
* Description: Retrieves a list of all products.
* Response:
```json
[
  {
  "title": "Wireless Headphones",
  "description": "High-quality wireless headphones with noise cancellation.",
  "category": "Electronics",
  "image": "https://i.pinimg.com/236x/d4/13/f3/d413f351550c68c4297b3e39032eac83.jpg",
  "rate": 89.99,
  "ratingCount": 150,
  "createdOn": "2023-10-01T12:30:00",
  "quantityRemaining": 25
  }
]
```
[

...
]
Get Product by ID

Endpoint: GET /api/v1/products/get/products/id
Description: Retrieves a single product by its ID.
Response:
```json
{
"title": "Wireless Headphones",
"description": "High-quality wireless headphones with noise cancellation.",
"category": "Electronics",
"image": "https://i.pinimg.com/236x/d4/13/f3/d413f351550c68c4297b3e39032eac83.jpg",
"rate": 89.99,
"ratingCount": 150,
"createdOn": "2023-10-01T12:30:00",
"quantityRemaining": 25
}
```

Create Product

Endpoint: POST /api/v1/products/create/new
Description: Creates a new product.
Request Body:
```json
{
"title": "Wireless Headphones",
"description": "High-quality wireless headphones with noise cancellation.",
"category": "Electronics",
"image": "https://i.pinimg.com/236x/d4/13/f3/d413f351550c68c4297b3e39032eac83.jpg",
"rate": 89.99,
"ratingCount": 150,
"createdOn": "2023-10-01T12:30:00",
"quantityRemaining": 25
}
```
Response:
```json
{
  "message": "new product created successfully",
  "statusCode": 200,
  "product": [
    {
      "title": "Wireless Headphones",
      "description": "High-quality wireless headphones with noise cancellation.",
      "category": "Electronics",
      "image": "https://i.pinimg.com/236x/d4/13/f3/d413f351550c68c4297b3e39032eac83.jpg",
      "rate": 89.99,
      "ratingCount": 150,
      "createdOn": "2023-10-01T12:30:00",
      "quantityRemaining": 25
    }
  ]
}
```

Update Product

Endpoint: PUT 
Description: Updates an existing product.
Request Body:

Response:

Delete Product

Endpoint: DELETE /api/v1/products/delete/products
Request param : id
Description: Deletes a product by its ID.
Response:
```json
{
  "message": "Product with ID 1 has been deleted successfully",
  "statusCode": 200
}
```

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

 Ensure to include the Authorization header with a valid token for protected routes.
