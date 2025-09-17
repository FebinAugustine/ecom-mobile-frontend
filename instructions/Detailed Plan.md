# Detailed Implementation Plan - Ecom App

This document outlines the step-by-step plan to implement the Ecom application based on the HLD, LLD, PRD, and API endpoints.

## Phase 0: Project Setup & Core Foundation (Est: 2-3 days)

1.  **Project Initialization:**
    *   Create a new Android Studio project.
    *   Setup `libs.versions.toml` for dependency management.
    *   Configure `buildSrc` or root `build.gradle.kts` for common build logic if needed.
2.  **Module Creation (Initial Structure):**
    *   Create core modules: `:core:ui`, `:core:common`, `:core:data`, `:core:domain`.
    *   Create the main `:app` module.
    *   Setup `build.gradle.kts` for each module with initial dependencies and inter-module dependencies.
3.  **:core:ui Setup:**
    *   Implement `Color.kt` with the primary color `#007200` and generate Material 3 Light/Dark ColorSchemes.
    *   Implement `Theme.kt` applying the color schemes.
    *   Implement `Type.kt` with basic typography.
    *   Implement `Shape.kt` with basic shapes.
    *   Create placeholder common composables (e.g., `AppButton`, `AppTextField`, `LoadingSpinner`).
4.  **:core:common Setup:**
    *   Create `Constants.kt` (e.g., `BASE_URL`).
    *   Create basic utility classes/files (e.g., `StringExtensions.kt`, `Validator.kt`).
    *   Add initial string resources in `strings.xml`.
5.  **:core:domain Setup:**
    *   Define initial (empty) repository interfaces for key entities (User, Product).
    *   Define initial (empty) domain models for User, Product.
6.  **:core:data Setup:**
    *   Integrate Koin: Add dependencies, create basic Koin modules structure.
    *   Integrate Ktor: Add dependencies.
    *   Setup Ktor `HttpClient` (basic configuration, JSON serialization with `kotlinx.serialization`).
    *   Define `AppPreferences` interface and implementation (SharedPreferences) for storing auth token and onboarding status.
    *   Implement basic (empty) repository implementations for User, Product, injecting API service and preferences.
    *   Define DTOs for User login/register based on `ApiEndPoints.md`.
    *   Define API service interfaces (e.g., `AuthApiService`, `UserApiService`) with initial login/register methods.
7.  **:app Module Setup:**
    *   Initialize Koin in the `Application` class.
    *   Setup main `NavHost` with a placeholder start destination.
    *   Apply the theme from `:core:ui` in `MainActivity`.

## Phase 1: Authentication Feature (Est: 4-5 days)

1.  **Module Creation:** Create `:feature:authentication`.
2.  **Define MVI Contract (`AuthContract.kt`):**
    *   States (e.g., `LoginState`, `SignupState`, `ForgotPasswordState`).
    *   Intents (e.g., `EmailChanged`, `PasswordChanged`, `SubmitLogin`, `SubmitSignup`).
    *   Effects (e.g., `NavigateToDashboard`, `ShowErrorToast`).
3.  **Implement ViewModels:**
    *   `LoginViewModel`
    *   `SignupViewModel`
    *   `ForgotPasswordViewModel`
    *   `ForgotPasswordOtpViewModel`
4.  **Implement Use Cases (in `:core:domain`, used by ViewModels):
    *   `LoginUserUseCase`
    *   `RegisterUserUseCase`
    *   `RequestForgotPasswordUseCase`
    *   `VerifyOtpAndResetPasswordUseCase`
    *   `GoogleLoginUseCase` (initial placeholder, actual implementation might be later)
5.  **Update/Implement Repositories (in `:core:data`):
    *   `AuthRepository` / `UserRepository` with methods for login, register, forgot password flows, linking to Ktor API services.
    *   Implement token storage/retrieval upon successful login.
6.  **Implement API Service (in `:core:data`):
    *   `AuthApiService` with Ktor calls for endpoints: `/users/register`, `/users/login`, `/users/google-login`, `/users/forgot-password`, `/users/forgot-password-verify-code`.
7.  **Implement UI Screens (Jetpack Compose in `:feature:authentication`):
    *   `LoginScreen.kt`
    *   `SignupScreen.kt`
    *   `ForgotPasswordScreen.kt`
    *   `ForgotPasswordOtpScreen.kt`
    *   Use common composables from `:core:ui`.
8.  **Navigation (`AuthNavGraph.kt`):
    *   Setup nested navigation graph for the authentication flow.
    *   Integrate into the main `NavHost` in `:app`.
9.  **Koin Module (`authDIModule.kt` in `:feature:authentication`):
    *   Provide ViewModels, UseCases, and other dependencies for this feature.
10. **Unit Testing:** Write unit tests for ViewModels and Use Cases.

## Phase 2: Common Screens (Est: 2-3 days)

1.  **Module Creation:** Create `:feature:common_screens`.
2.  **Implement Screens (Static or with minimal logic):
    *   `SplashScreen.kt` (with delay and navigation to Onboarding or Main).
    *   `WelcomeOnboardingScreen.kt` (potentially with a ViewPager-like Composable).
    *   `PrivacyPolicyScreen.kt` (display static text, potentially loaded from resources or a simple API).
    *   `TermsAndConditionsScreen.kt`.
    *   `ReturnPolicyScreen.kt`.
3.  **ViewModel (if needed, e.g., for Onboarding completion tracking).
4.  **Navigation:** Integrate into main `NavHost`.
5.  **Koin Module:** For any ViewModels specific to these screens.

## Phase 3: User Flow - Part 1 (Dashboard, Profile, Products) (Est: 7-10 days)

1.  **Module Creation:**
    *   `:feature:user_dashboard`
    *   `:feature:products` (can be shared with Seller/Admin later, focus on user view first)
2.  **User Dashboard (`:feature:user_dashboard`):
    *   `UserDashboardScreen.kt` (Bottom Navigation or main layout for user sections).
    *   `UserProfileScreen.kt` (View user details, option to edit - implement edit later).
    *   `UserChangePasswordScreen.kt` (API: `/users/update-password`).
    *   ViewModels, UseCases, Repository methods for profile view & password change.
3.  **Product Listing & Details (`:feature:products`):
    *   **API Service:** `ProductApiService` methods for `/products`, `/products/:id`, `/products/category/:category`.
    *   **Domain:** `Product` domain model, `ProductRepository` interface, `GetProductsUseCase`, `GetProductDetailsUseCase`.
    *   **Data:** DTOs for Product, `ProductRepositoryImpl`.
    *   **UI:**
        *   `AllProductsScreen.kt` (List products, category sorting, search/filter).
        *   `SingleProductScreen.kt` (Detailed product view, image gallery - Coil, description, price, add to cart/wishlist buttons - functionality later).
    *   ViewModels for product listing and details.
    *   Koin modules.
4.  **Navigation:** Integrate user dashboard and product screens into main navigation.
5.  **Coil Integration:** Implement image loading for product images.
6.  **Unit & UI Testing.**

## Phase 4: User Flow - Part 2 (Cart, Wishlist, Orders, Checkout) (Est: 8-12 days)

1.  **Module Creation:**
    *   `:feature:cart`
    *   `:feature:wishlist`
    *   `:feature:orders` (User specific part)
2.  **Cart (`:feature:cart`):
    *   **API Service:** `CartApiService` for `/carts`, `/carts/:id`.
    *   **Domain/Data:** `CartItem` model, `CartRepository`, UseCases (`GetCartUseCase`, `AddToCartUseCase`, `UpdateCartItemUseCase`, `RemoveFromCartUseCase`).
    *   **UI:** `UserCartScreen.kt` (List items, update quantity, remove, proceed to checkout).
    *   ViewModel, Koin module.
3.  **Wishlist (`:feature:wishlist`):
    *   **API Service:** `WishlistApiService` for `/wishlists` (assuming similar CRUD as cart).
    *   **Domain/Data:** `WishlistItem` model, `WishlistRepository`, UseCases.
    *   **UI:** `UserWishlistScreen.kt`.
    *   ViewModel, Koin module.
4.  **Order (`:feature:orders` - User part):
    *   **API Service:** `OrderApiService` for `/orders`, `/orders/:id` (user's perspective), `/orders` (POST for creating order).
    *   **Domain/Data:** `Order` model, `OrderRepository`, UseCases (`GetUserOrdersUseCase`, `GetOrderDetailsUseCase`, `CreateOrderUseCase`).
    *   **UI:**
        *   `UserOrderHistoryScreen.kt`.
        *   `CheckoutScreen.kt` (Review cart, select address, payment method - UI only initially).
        *   `PaymentSuccessScreen.kt`.
        *   `AllInvoicesScreen.kt` (User's invoices - if different from orders).
    *   ViewModels, Koin modules.
5.  **Navigation & Testing.**

## Phase 5: Seller Flow (Est: 10-14 days)

*   Follow a similar pattern as User Flow: Module creation, API services, Domain/Data layers, ViewModels, UI Screens, Koin, Navigation, Testing.
1.  **Module Creation:** `:feature:seller_auth`, `:feature:seller_dashboard`, etc. (or reuse `:feature:authentication` with role handling).
2.  **Authentication:** Adapt existing auth or create seller-specific screens for Login/Signup (API: `/sellers/register`, `/sellers/login`).
3.  **Dashboard (`SellerDashboardScreen.kt`).**
4.  **Profile (`SellerProfileScreen.kt`, `SellerChangePasswordScreen.kt`).**
5.  **Product Management (reuse/extend `:feature:products`):
    *   `AllProductScreen.kt` (Seller's view).
    *   `CreateProductScreen.kt` (API: `/products/add-products`).
    *   `EditProductScreen.kt` (API: `/products/:id` (PUT), `/products/update-images/:id`).
6.  **Order Management (`:feature:orders` - Seller part):
    *   `AllOrdersScreen.kt` (Seller's view, update status).
7.  **Invoices (`AllInvoicesScreen.kt` - Seller's view).**

## Phase 6: Admin Flow (Est: 8-12 days)

*   Similar pattern as User/Seller flows.
1.  **Module Creation:** `:feature:admin_auth`, `:feature:admin_dashboard`, etc.
2.  **Authentication:** Login/Signup (API: `/admins/register`, `/admins/login`).
3.  **Dashboard (`AdminDashboardScreen.kt`).**
4.  **Profile (`AdminProfileScreen.kt`, `AdminChangePasswordScreen.kt`).**
5.  **Product Management (view all).**
6.  **Order Management (view all).**
7.  **User Management (`AllUsersScreen.kt` - API: `/users/all-users`, `/users/:id` (DELETE)).**
8.  **Seller Management (`AllSellersScreen.kt` - API: `/sellers/all-sellers`, `/sellers/:id` (DELETE)).**

## Phase 7: Refinement, Testing, and Polish (Est: 5-7 days)

1.  **End-to-End Testing:** Thoroughly test all user flows.
2.  **UI Polish:** Ensure consistency, address any UI/UX issues.
3.  **Performance Optimization:** Profile app, identify and fix bottlenecks.
4.  **Error Handling Review:** Ensure all error cases are handled gracefully.
5.  **Code Cleanup & Refactoring:** Address TODOs, improve code quality.
6.  **Final Review of PRD requirements.**

## Continuous Tasks:

*   **Version Control:** Regular commits, feature branches.
*   **Code Reviews:** For all significant changes.
*   **Dependency Updates:** Periodically check and update libraries.
*   **Documentation:** Update LLD, HLD, and inline code comments as needed.
*   **Populate `Todo.md`** as tasks are identified and progressed.

This plan is ambitious and timelines are estimates. Flexibility will be key. Each phase will involve iterative development and testing.
