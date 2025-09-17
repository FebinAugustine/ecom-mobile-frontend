# API Endpoints For Whole Project

### REST API's

- baseUrl = http://localhost:3000
- Extension - /api/v1

## 1. User Endpoints:

### **CREATE**

- base endpoint: /users

- Register:
    - Method: POST
    - URL: /register
    - Body: {
      "fullname": "John Doe",
      "email": "t0t6j@example.com",
      "password": "password123",
      "phone": "1234567890"
      }
- Login:
    - Method: POST
    - URL: /login
    - Body: {
      "email": "t0t6j@example.com",
      "password": "password123"
      }
- Google Login: /
    - Method: POST
    - URL: /users/google-login
    - Body: {
      "email": "t0t6j@example.com",
      "password": "password123"
      }

### **READ**

- Get all users:
    - Method: GET
    - URL: /all-users
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- Get user by id:
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- Get user by email:
    - Method: GET
    - URL: /email/:email
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- **Update user by id:**
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "fullname": "John Doe",
      "email": "t0t6j@example.com",
      "password": "password123",
      "phone": "1234567890"
      "address": "123 Main St",
      "delivery_address": "123 Main St"  
      }
- **Update User Avatar:**
    - Method: PUT
    - URL: /update-avatar
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "avatar": "https://example.com/avatar.jpg"
      }
- **Update User Password:**
    - Method: PUT
    - URL: /update-password
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "oldPassword": "oldPassword123",
      "newPassword": "newPassword123"
      }
- **Logout:**
    - Method: POST
    - URL: /users/logout
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Post Refresh Token:**
    - Method: POST
    - URL: /refresh-token
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Post Forgot Password:**
    - Method: POST
    - URL: /forgot-password
    - Body: {
      "email": "t0t6j@example.com"
      }
- **Post Forgot password verify code and set new password:**
    - Method: POST
    - URL: /users/forgot-password-verify-code
    - Body: {
      "email": "t0t6j@example.com",
      "code": "123456",
      "password": "newPassword123"
      }
- **Post Verify Email:**
    - Method: POST
    - URL: /verify-email
    - Body: {
      "email": "t0t6j@example.com"
      }
- **Post Resend Verification Email:**
    - Method: POST
    - URL: /users/resend-verification-email
    - Body: {
      "email": "t0t6j@example.com"
      }

### **DELETE**

- **Delete user by id:**
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

---

## **2. SELLER ENDPOINTS:**

- base endpoint: /sellers

### **CREATE**

- **Register:**
    - Method: POST
    - URL: /register
    - Body: {
      "fullname": "John Doe",
      "email": "t0t6j@example.com",
      "password": "password123",
      "phone": "1234567890",
      "address": "123 Main St",
      "company_name": "ABC Company",
      "location": "New York, USA"
      }
- **Login:**
    - Method: POST
    - URL: /login
    - Body: {
      "email": "t0t6j@example.com",
      "password": "password123"
      }

### **READ**

- **Get all sellers:**
    - Method: GET
    - URL: /all-sellers
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Get seller by id:**
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Get seller by email:**
    - Method: GET
    - URL: /sellers/email/:email
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- **Update seller by id:**
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "fullname": "John Doe",
      "email": "t0t6j@example.com",
      "password": "password123",
      "phone": "1234567890",
      "address": "123 Main St",
      "company_name": "ABC Company",
      "location": "New York, USA"
      "gst": "1234567890",
      "pan": "1234567890",
      }
- **Update seller Bank Details by id:**
    - Method: PUT
    - URL: /bank/:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "bank_name": "ABC Bank",
      "account_number": "1234567890",
      "ifsc": "1234567890"
      }
- **Logout:**
    - Method: POST
    - URL: /logout
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Post Refresh Token:**
    - Method: POST
    - URL: /refresh-token
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Forgot Password:**
    - Method: POST
    - URL: /forgot-password
    - Body: {
      "email": "t0t6j@example.com"
      }
- **Forgot password verify code and set new password:**
    - Method: POST
    - URL: /forgot-password-verify-code
    - Body: {
      "email": "t0t6j@example.com",
      "code": "123456",
      "password": "newPassword123"
      }
- **Verify Email:**
    - Method: POST
    - URL: /verify-email
    - Body: {
      "email": "t0t6j@example.com"
      }
- **Resend Verification Email:**
    - Method: POST
    - URL: /resend-verification-email
    - Body: {
      "email": "t0t6j@example.com"
      }

### **DELETE**

- Delete seller by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

---

## **3. ADMIN ENDPOINTS:**

- base endpoint: /admins

### **CREATE**

- **Register:**
    - Method: POST
    - URL: /register
    - Body: {
      "fullname": "John Doe",
      "email": "t0t6j@example.com",
      "password": "password123",
      "phone": "1234567890"
      }
- **Login:**
    - Method: POST
    - URL: /login
    - Body: {
      "email": "t0t6j@example.com",
      "password": "password123"
      }

### **READ**

- **Get admin by id:**
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- **Update admin by id:**
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "fullname": "John Doe",
      "email": "t0t6j@example.com",
      "password": "password123",
      "phone": "1234567890"
      }
- **Logout:**
    - Method: POST
    - URL: /logout
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Post Refresh Token:**
    - Method: POST
    - URL: /refresh-token
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Forgot Password:**
    - Method: POST
    - URL: /forgot-password
    - Body: {
      "email": "t0t6j@example.com"
      }
- **Forgot password verify code and set new password:**
    - Method: POST
    - URL: /admins/forgot-password-verify-code
    - Body: {
      "email": "t0t6j@example.com",
      "code": "123456",
      "password": "newPassword123"
      }

### **DELETE**

- **Delete admin by id:**
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

---

## **4. PRODUCT ENDPOINTS:**

- base endpoint: /products

### **CREATE**

- **Create product:**

    - Method: POST
    - URL: /add-products
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "name": "Product Name",
      "description": "Product Description",
      "price": 100,
      "discount": 10,
      "inStock": 100,
      "color": "color",
      "sizes": "sizes",
      "image": "image",
      "category": "category",
      }

### **READ**

- **Get all products:**
    - Method: GET
    - URL: /
- **Get product by id:**
    - Method: GET
    - URL: /products/:id
- **Get products by category:**

    - Method: GET
    - URL: /products/category/:category

- **Get product by seller:**
    - Method: GET
    - URL: /products/seller/:id

### **UPDATE**

- **Update product by id:**
    - Method: PUT
    - URL: /products/:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "name": "Product Name",
      "description": "Product Description",
      "price": 100,
      "discount": 10,
      "inStock": 100,
      "color": "color",
      "sizes": "sizes",
      "category": "category",
      }
- **Update Product Images:**
    - Method: PUT
    - URL: /update-images/:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
        - Body: {
          "image": "image"
          }

### **DELETE**

- **Delete product by id:**
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

---

## **5. Order Endpoints:**

- base endpoint: /orders

### **CREATE**

- **Create order:**
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "seller_id": "seller_id",
      "products": "products",
      "total": 100,
      "deliveryAddress": "deliveryAddress",
      "payment_method": "payment_method",
      "payment_status": "payment_status",
      "status": "status",
      }

### **READ**

- **Get all orders:**
    - Method: GET
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- **Get order by id:**
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- **Update order by id:**
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "seller_id": "seller_id",
      "products": "products",
      "total": 100,
      "deliveryAddress": "deliveryAddress",
      "payment_method": "payment_method",
      "payment_status": "payment_status",
      "status": "status",
      }

### **DELETE**

- **Delete order by id:**
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **6. Review Endpoints:**

- base endpoint: /reviews

### **CREATE**

- Create review:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      "rating": 5,
      "review": "review",
      }

### **READ**

- Get all reviews:
    - Method: GET
    - URL: /
- Get review by id:
    - Method: GET
    - URL: /reviews/:id

### **UPDATE**

- Update review by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      "rating": 5,
      "review": "review",
      }

### **DELETE**

- Delete review by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **7. Cart Endpoints:**

- base endpoint: /carts

### **CREATE**

- Add to cart:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      "quantity": 1,
      }

### **READ**

- Get all carts:
    - Method: GET
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- Get cart by id:
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization ": "Bearer <token>"
      }

### **UPDATE**

- Update cart by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      "quantity": 1,
      }

### **DELETE**

- Delete cart by id:
    - Method: DELETE
    - URL: /carts/:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **8.Wishlist Endpoints:**

- base endpoint: /wishlists

### **CREATE**

- Add to wishlist:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      }

### **READ**

- Get all wishlists:
    - Method: GET
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- Get wishlist by id:
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- Update wishlist by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      }

### **DELETE**

- Delete wishlist by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **9. Category Endpoints:**

- base endpoint: /categories

### **CREATE**

- Create category:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "name": "category",
      "image": "image",
      }

### **READ**

- Get all categories:

    - Method: GET
    - URL: /

- Get category by id:
    - Method: GET
    - URL: /:id

### **UPDATE**

- Update category by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "name": "category",
      "image": "image",
      }

### **DELETE**

- Delete category by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **10. Likes Endpoints:**

- base endpoint: /likes

### **CREATE**

- Create like:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      }

### **READ**

- Get all likes:
    - Method: GET
    - URL: /
- Get like by id:
    - Method: GET
    - URL: /:id

### **UPDATE**

- Update like by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "product_id": "product_id",
      }

### **DELETE**

- Delete like by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **11. Invoice Endpoints:**

- base endpoint: /invoices

### **CREATE**

- Create invoice:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "order_id": "order_id",
      "user_id": "user_id",
      "seller_id": "seller_id",
      "total": 100,
      "deliveryAddress": "deliveryAddress",
      "payment_method": "payment_method",
      "payment_status": "payment_status",
      "status": "status",
      }

### **READ**

- Get all invoices:
    - Method: GET
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- Get invoice by id:
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- Update invoice by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "order_id": "order_id",
      "user_id": "user_id",
      "seller_id": "seller_id",
      "total": 100,
      "deliveryAddress": "deliveryAddress",
      "payment_method": "payment_method",
      "payment_status": "payment_status",
      "status": "status",
      }

### **DELETE**

- Delete invoice by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

## **12. Reply Endpoints:**

- base endpoint: /replies

### **CREATE**

- Create reply:
    - Method: POST
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "comment_id": "comment_id",
      "reply": "reply",
      }

### **READ**

- Get all replies:
    - Method: GET
    - URL: /
    - Headers: {
      "Authorization": "Bearer <token>"
      }
- Get reply by id:
    - Method: GET
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }

### **UPDATE**

- Update reply by id:
    - Method: PUT
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
    - Body: {
      "user_id": "user_id",
      "comment_id": "comment_id",
      "reply": "reply",
      }

### **DELETE**

- Delete reply by id:
    - Method: DELETE
    - URL: /:id
    - Headers: {
      "Authorization": "Bearer <token>"
      }
