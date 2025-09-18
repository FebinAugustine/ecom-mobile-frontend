# E-commerce App - API Endpoint Reference

This document provides a comprehensive reference for all the API endpoints used in the E-commerce application.

### **Base URL:** `http://localhost:3000/api/v1`

---

## 1. User Endpoints

*   **Base Path:** `/users`

| Action                      | Method | URL                          | Headers                      | Body (JSON)                                                                 | Expected Response (JSON)                                  |
| --------------------------- | ------ | ---------------------------- | ---------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------- |
| **Register**                | `POST` | `/register`                  | `Content-Type: application/json` | `{"fullname", "email", "password", "phone"}`                             | `{"token", "user": {"id", "fullname", "email"}}`      |
| **Login**                   | `POST` | `/login`                     | `Content-Type: application/json` | `{"email", "password"}`                                                      | `{"token", "user": {"id", "fullname", "email"}}`      |
| **Google Login**            | `POST` | `/google-login`              | `Content-Type: application/json` | `{"email", "password"}`                                                      | `{"token", "user": {"id", "fullname", "email"}}`      |
| **Get All Users**           | `GET`  | `/all-users`                 | `Authorization: Bearer <token>`  | -                                                                           | `[{"id", "fullname", "email", ...}]`                   |
| **Get User by ID**          | `GET`  | `/:id`                       | `Authorization: Bearer <token>`  | -                                                                           | `{"id", "fullname", "email", ...}`                      |
| **Get User by Email**       | `GET`  | `/email/:email`              | `Authorization: Bearer <token>`  | -                                                                           | `{"id", "fullname", "email", ...}`                      |
| **Update User by ID**       | `PUT`  | `/:id`                       | `Authorization: Bearer <token>`  | `{"fullname", "email", "password", "phone", "address", "delivery_address"}` | `{"id", "fullname", "email", ...}`                      |
| **Update User Avatar**      | `PUT`  | `/update-avatar`             | `Authorization: Bearer <token>`  | `{"avatar": "url"}`                                                          | `{"id", "fullname", "email", ...}`                      |
| **Update User Password**    | `PUT`  | `/update-password`           | `Authorization: Bearer <token>`  | `{"oldPassword", "newPassword"}`                                             | `{"message": "Password updated successfully"}`          |
| **Logout**                  | `POST` | `/logout`                    | `Authorization: Bearer <token>`  | -                                                                           | `{"message": "Logged out successfully"}`              |
| **Refresh Token**           | `POST` | `/refresh-token`             | `Authorization: Bearer <token>`  | -                                                                           | `{"token": "new_token"}`                                |
| **Forgot Password**         | `POST` | `/forgot-password`           | `Content-Type: application/json` | `{"email"}`                                                                | `{"message": "Password reset code sent"}`             |
| **Verify & Reset Password** | `POST` | `/forgot-password-verify-code` | `Content-Type: application/json` | `{"email", "code", "password"}`                                            | `{"message": "Password reset successful"}`            |
| **Verify Email**            | `POST` | `/verify-email`              | `Content-Type: application/json` | `{"email"}`                                                                | `{"message": "Email verification sent"}`              |
| **Resend Verification Email** | `POST` | `/resend-verification-email` | `Content-Type: application/json` | `{"email"}`                                                                | `{"message": "Verification email resent"}`            |
| **Delete User by ID**       | `DELETE` | `/:id`                       | `Authorization: Bearer <token>`  | -                                                                           | `{"message": "User deleted successfully"}`            |

---

## 2. Seller Endpoints

*   **Base Path:** `/sellers`

| Action                      | Method | URL                          | Headers                      | Body (JSON)                                                                                             | Expected Response (JSON)                                      |
| --------------------------- | ------ | ---------------------------- | ---------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------- |
| **Register**                | `POST` | `/register`                  | `Content-Type: application/json` | `{"fullname", "email", "password", "phone", "address", "company_name", "location"}`             | `{"token", "seller": {"id", "fullname", "email", ...}}` |
| **Login**                   | `POST` | `/login`                     | `Content-Type: application/json` | `{"email", "password"}`                                                                  | `{"token", "seller": {"id", "fullname", "email", ...}}` |
| **Get All Sellers**         | `GET`  | `/all-sellers`               | `Authorization: Bearer <token>`  | -                                                                                       | `[{"id", "fullname", "email", ...}]`                       |
| **Get Seller by ID**        | `GET`  | `/:id`                       | `Authorization: Bearer <token>`  | -                                                                                       | `{"id", "fullname", "email", ...}`                          |
| **Get Seller by Email**     | `GET`  | `/email/:email`              | `Authorization: Bearer <token>`  | -                                                                                       | `{"id", "fullname", "email", ...}`                          |
| **Update Seller by ID**     | `PUT`  | `/:id`                       | `Authorization: Bearer <token>`  | `{"fullname", "email", "password", "phone", "address", "company_name", "location", "gst", "pan"}` | `{"id", "fullname", "email", ...}`                          |
| **Update Seller Bank Details** | `PUT`  | `/bank/:id`                  | `Authorization: Bearer <token>`  | `{"bank_name", "account_number", "ifsc"}`                                                | `{"id", "fullname", "email", ...}`                          |
| **Logout**                  | `POST` | `/logout`                    | `Authorization: Bearer <token>`  | -                                                                                       | `{"message": "Logged out successfully"}`                  |
| **Refresh Token**           | `POST` | `/refresh-token`             | `Authorization: Bearer <token>`  | -                                                                                       | `{"token": "new_token"}`                                    |
| **Forgot Password**         | `POST` | `/forgot-password`           | `Content-Type: application/json` | `{"email"}`                                                                            | `{"message": "Password reset code sent"}`                 |
| **Verify & Reset Password** | `POST` | `/forgot-password-verify-code` | `Content-Type: application/json` | `{"email", "code", "password"}`                                                        | `{"message": "Password reset successful"}`                |
| **Verify Email**            | `POST` | `/verify-email`              | `Content-Type: application/json` | `{"email"}`                                                                            | `{"message": "Email verification sent"}`                  |
| **Resend Verification Email** | `POST` | `/resend-verification-email` | `Content-Type: application/json` | `{"email"}`                                                                            | `{"message": "Verification email resent"}`                |
| **Delete Seller by ID**     | `DELETE` | `/:id`                       | `Authorization: Bearer <token>`  | -                                                                                       | `{"message": "Seller deleted successfully"}`                |

---

## 3. Admin Endpoints

*   **Base Path:** `/admins`

| Action                      | Method | URL                          | Headers                      | Body (JSON)                               | Expected Response (JSON)                                  |
| --------------------------- | ------ | ---------------------------- | ---------------------------- | ----------------------------------------- | --------------------------------------------------------- |
| **Register**                | `POST` | `/register`                  | `Content-Type: application/json` | `{"fullname", "email", "password", "phone"}` | `{"token", "admin": {"id", "fullname", "email", ...}}` |
| **Login**                   | `POST` | `/login`                     | `Content-Type: application/json` | `{"email", "password"}`                    | `{"token", "admin": {"id", "fullname", "email", ...}}` |
| **Get Admin by ID**         | `GET`  | `/:id`                       | `Authorization: Bearer <token>`  | -                                         | `{"id", "fullname", "email", ...}`                      |
| **Update Admin by ID**      | `PUT`  | `/:id`                       | `Authorization: Bearer <token>`  | `{"fullname", "email", "password", "phone"}` | `{"id", "fullname", "email", ...}`                      |
| **Logout**                  | `POST` | `/logout`                    | `Authorization: Bearer <token>`  | -                                         | `{"message": "Logged out successfully"}`              |
| **Refresh Token**           | `POST` | `/refresh-token`             | `Authorization: Bearer <token>`  | -                                         | `{"token": "new_token"}`                                |
| **Forgot Password**         | `POST` | `/forgot-password`           | `Content-Type: application/json` | `{"email"}`                              | `{"message": "Password reset code sent"}`             |
| **Verify & Reset Password** | `POST` | `/forgot-password-verify-code` | `Content-Type: application/json` | `{"email", "code", "password"}`          | `{"message": "Password reset successful"}`            |
| **Delete Admin by ID**      | `DELETE` | `/:id`                       | `Authorization: Bearer <token>`  | -                                         | `{"message": "Admin deleted successfully"}`             |

---

## 4. Product Endpoints

*   **Base Path:** `/products`

| Action                      | Method | URL                          | Headers                      | Body (JSON)                                                                                  | Expected Response (JSON)                         |
| --------------------------- | ------ | ---------------------------- | ---------------------------- | -------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| **Create Product**          | `POST` | `/add-products`              | `Authorization: Bearer <token>`  | `{"name", "description", "price", "discount", "inStock", "color", "sizes", "image", "category"}` | `{"id", "name", "description", ...}`           |
| **Get All Products**        | `GET`  | `/`                          | -                            | -                                                                                            | `[{"id", "name", "description", ...}]`         |
| **Get Product by ID**       | `GET`  | `/:id`                       | -                            | -                                                                                            | `{"id", "name", "description", ...}`           |
| **Get Products by Category**  | `GET`  | `/category/:category`        | -                            | -                                                                                            | `[{"id", "name", "description", ...}]`         |
| **Get Product by Seller**   | `GET`  | `/seller/:id`                | -                            | -                                                                                            | `[{"id", "name", "description", ...}]`         |
| **Update Product by ID**    | `PUT`  | `/:id`                       | `Authorization: Bearer <token>`  | `{"name", "description", "price", "discount", "inStock", "color", "sizes", "category"}`      | `{"id", "name", "description", ...}`           |
| **Update Product Images**   | `PUT`  | `/update-images/:id`         | `Authorization: Bearer <token>`  | `{"image": "url"}`                                                                        | `{"id", "name", "description", ...}`           |
| **Delete Product by ID**    | `DELETE` | `/:id`                       | `Authorization: Bearer <token>`  | -                                                                                            | `{"message": "Product deleted successfully"}`  |

---

## 5. Order Endpoints

*   **Base Path:** `/orders`

| Action                 | Method | URL      | Headers                      | Body (JSON)                                                                                                     | Expected Response (JSON)                     |
| ---------------------- | ------ | -------- | ---------------------------- | --------------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| **Create Order**       | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"user_id", "seller_id", "products", "total", "deliveryAddress", "payment_method", "payment_status", "status"}` | `{"id", "user_id", "total", ...}`          |
| **Get All Orders**     | `GET`  | `/`      | `Authorization: Bearer <token>`  | -                                                                                                               | `[{"id", "user_id", "total", ...}]`        |
| **Get Order by ID**    | `GET`  | `/:id`   | `Authorization: Bearer <token>`  | -                                                                                                               | `{"id", "user_id", "total", ...}`          |
| **Update Order by ID** | `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"user_id", "seller_id", "products", "total", "deliveryAddress", "payment_method", "payment_status", "status"}` | `{"id", "user_id", "total", ...}`          |
| **Delete Order by ID** | `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                                                                                                               | `{"message": "Order deleted successfully"}` |

---

## 6. Review Endpoints

*   **Base Path:** `/reviews`

| Action                 | Method | URL      | Headers                      | Body (JSON)                                               | Expected Response (JSON)                         |
| ---------------------- | ------ | -------- | ---------------------------- | --------------------------------------------------------- | ------------------------------------------------ |
| **Create Review**      | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"user_id", "product_id", "rating", "review"}`       | `{"id", "user_id", "rating", ...}`           |
| **Get All Reviews**    | `GET`  | `/`      | -                            | -                                                         | `[{"id", "user_id", "rating", ...}]`         |
| **Get Review by ID**   | `GET`  | `/:id`   | -                            | -                                                         | `{"id", "user_id", "rating", ...}`           |
| **Update Review by ID**| `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"user_id", "product_id", "rating", "review"}`       | `{"id", "user_id", "rating", ...}`           |
| **Delete Review by ID**| `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                                                         | `{"message": "Review deleted successfully"}`   |

---

## 7. Cart Endpoints

*   **Base Path:** `/carts`

| Action                 | Method | URL      | Headers                      | Body (JSON)                                       | Expected Response (JSON)                     |
| ---------------------- | ------ | -------- | ---------------------------- | ------------------------------------------------- | -------------------------------------------- |
| **Add to Cart**        | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"user_id", "product_id", "quantity"}`       | `{"id", "user_id", "quantity", ...}`       |
| **Get All Carts**      | `GET`  | `/`      | `Authorization: Bearer <token>`  | -                                                 | `[{"id", "user_id", "quantity", ...}]`     |
| **Get Cart by ID**     | `GET`  | `/:id`   | `Authorization: Bearer <token>`  | -                                                 | `{"id", "user_id", "quantity", ...}`       |
| **Update Cart by ID**  | `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"user_id", "product_id", "quantity"}`       | `{"id", "user_id", "quantity", ...}`       |
| **Delete Cart by ID**  | `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                                                 | `{"message": "Cart item deleted successfully"}` |

---

## 8. Wishlist Endpoints

*   **Base Path:** `/wishlists`

| Action                 | Method | URL      | Headers                      | Body (JSON)                             | Expected Response (JSON)                         |
| ---------------------- | ------ | -------- | ---------------------------- | --------------------------------------- | ------------------------------------------------ |
| **Add to Wishlist**    | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"user_id", "product_id"}`           | `{"id", "user_id", "product_id"}`            |
| **Get All Wishlists**  | `GET`  | `/`      | `Authorization: Bearer <token>`  | -                                       | `[{"id", "user_id", "product_id"}]`          |
| **Get Wishlist by ID** | `GET`  | `/:id`   | `Authorization: Bearer <token>`  | -                                       | `{"id", "user_id", "product_id"}`            |
| **Update Wishlist by ID**| `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"user_id", "product_id"}`           | `{"id", "user_id", "product_id"}`            |
| **Delete Wishlist by ID**| `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                                       | `{"message": "Wishlist item deleted successfully"}` |

---

## 9. Category Endpoints

*   **Base Path:** `/categories`

| Action                 | Method | URL      | Headers                      | Body (JSON)               | Expected Response (JSON)                     |
| ---------------------- | ------ | -------- | ---------------------------- | ------------------------- | -------------------------------------------- |
| **Create Category**    | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"name", "image"}`      | `{"id", "name", "image"}`                |
| **Get All Categories** | `GET`  | `/`      | -                            | -                         | `[{"id", "name", "image"}]`              |
| **Get Category by ID** | `GET`  | `/:id`   | -                            | -                         | `{"id", "name", "image"}`                |
| **Update Category by ID**| `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"name", "image"}`      | `{"id", "name", "image"}`                |
| **Delete Category by ID**| `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                         | `{"message": "Category deleted successfully"}` |

---

## 10. Likes Endpoints

*   **Base Path:** `/likes`

| Action              | Method | URL      | Headers                      | Body (JSON)                   | Expected Response (JSON)                 |
| ------------------- | ------ | -------- | ---------------------------- | ----------------------------- | ---------------------------------------- |
| **Create Like**     | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"user_id", "product_id"}`   | `{"id", "user_id", "product_id"}`    |
| **Get All Likes**   | `GET`  | `/`      | -                            | -                             | `[{"id", "user_id", "product_id"}]`  |
| **Get Like by ID**  | `GET`  | `/:id`   | -                            | -                             | `{"id", "user_id", "product_id"}`    |
| **Update Like by ID** | `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"user_id", "product_id"}`   | `{"id", "user_id", "product_id"}`    |
| **Delete Like by ID** | `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                             | `{"message": "Like removed successfully"}` |

---

## 11. Invoice Endpoints

*   **Base Path:** `/invoices`

| Action                 | Method | URL      | Headers                      | Body (JSON)                                                                                                     | Expected Response (JSON)                     |
| ---------------------- | ------ | -------- | ---------------------------- | --------------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| **Create Invoice**     | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"order_id", "user_id", "seller_id", "total", "deliveryAddress", "payment_method", "payment_status", "status"}` | `{"id", "order_id", "total", ...}`         |
| **Get All Invoices**   | `GET`  | `/`      | `Authorization: Bearer <token>`  | -                                                                                                               | `[{"id", "order_id", "total", ...}]`       |
| **Get Invoice by ID**  | `GET`  | `/:id`   | `Authorization: Bearer <token>`  | -                                                                                                               | `{"id", "order_id", "total", ...}`         |
| **Update Invoice by ID** | `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"order_id", "user_id", "seller_id", "total", "deliveryAddress", "payment_method", "payment_status", "status"}` | `{"id", "order_id", "total", ...}`         |
| **Delete Invoice by ID** | `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                                                                                                               | `{"message": "Invoice deleted successfully"}` |

---

## 12. Reply Endpoints

*   **Base Path:** `/replies`

| Action              | Method | URL      | Headers                      | Body (JSON)                           | Expected Response (JSON)                 |
| ------------------- | ------ | -------- | ---------------------------- | ------------------------------------- | ---------------------------------------- |
| **Create Reply**    | `POST` | `/`      | `Authorization: Bearer <token>`  | `{"user_id", "comment_id", "reply"}` | `{"id", "user_id", "reply", ...}`    |
| **Get All Replies** | `GET`  | `/`      | `Authorization: Bearer <token>`  | -                                     | `[{"id", "user_id", "reply", ...}]`  |
| **Get Reply by ID** | `GET`  | `/:id`   | `Authorization: Bearer <token>`  | -                                     | `{"id", "user_id", "reply", ...}`    |
| **Update Reply by ID**| `PUT`  | `/:id`   | `Authorization: Bearer <token>`  | `{"user_id", "comment_id", "reply"}` | `{"id", "user_id", "reply", ...}`    |
| **Delete Reply by ID**| `DELETE` | `/:id`   | `Authorization: Bearer <token>`  | -                                     | `{"message": "Reply deleted successfully"}` |

