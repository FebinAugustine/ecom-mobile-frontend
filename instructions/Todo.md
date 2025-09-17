# Ecom App - Todo List

This list tracks the progress of the Ecom application development. 

## Phase 0: Project Setup & Core Foundation
- [X] **Project Initialization**
    - [X] Create new Android Studio project
    - [X] Setup `libs.versions.toml`
    - [X] Configure `buildSrc` or root `build.gradle.kts`
- [X] **Module Creation (Initial Structure)**
    - [X] Create `:core:ui`
    - [X] Create `:core:common`
    - [X] Create `:core:data`
    - [X] Create `:core:domain`
    - [X] Create `:app` module
    - [X] Setup `build.gradle.kts` for each module
- [X] **:core:ui Setup**
    - [X] Implement `Color.kt` (#007200 primary)
    - [X] Implement `Theme.kt`
    - [X] Implement `Type.kt`
    - [X] Implement `Shape.kt`
    - [X] Create placeholder common composables
- [X] **:core:common Setup**
    - [X] Create `Constants.kt` (BASE_URL)
    - [X] Create basic utility classes/files
    - [X] Add initial string resources
- [X] **:core:domain Setup**
    - [X] Define initial repository interfaces (User, Product)
    - [X] Define initial domain models (User, Product)
- [X] **:core:data Setup**
    - [X] Integrate Koin
    - [X] Integrate Ktor & setup `HttpClient`
    - [X] Define & Implement `AppPreferences`
    - [X] Implement basic repository implementations (User, Product)
    - [X] Define DTOs for User login/register
    - [X] Define API service interfaces (Auth, User)
- [X] **:app Module Setup**
    - [X] Initialize Koin in `Application` class
    - [X] Setup main `NavHost`
    - [X] Apply theme in `MainActivity`

## Phase 1: Authentication Feature
- [X] Create `:feature:authentication` module
- [X] Define `AuthContract.kt` (State, Intent, Effect) for Login, Signup, Forgot Password
- [X] Implement `LoginViewModel`
- [X] Implement `SignupViewModel`
- [X] Implement `ForgotPasswordViewModel`
- [X] Implement `ForgotPasswordOtpViewModel`
- [X] Implement Use Cases (Domain): `LoginUserUseCase`, `RegisterUserUseCase`, `RequestForgotPasswordUseCase`, `VerifyOtpAndResetPasswordUseCase`, `GoogleLoginUseCase` (placeholder)
- [X] Update/Implement Repositories (Data): `AuthRepository`/`UserRepository` for auth flows
- [X] Implement API Service (Data): `AuthApiService` for relevant Ktor calls
- [X] Implement UI Screen: `LoginScreen.kt`
- [X] Implement UI Screen: `SignupScreen.kt`
- [X] Implement UI Screen: `ForgotPasswordScreen.kt`
- [X] Implement UI Screen: `ForgotPasswordOtpScreen.kt`
- [X] Setup `AuthNavGraph.kt` and integrate into main NavHost
- [X] Create `authDIModule.kt` in `:feature:authentication`
- [X] Unit tests for ViewModels and Use Cases

## Phase 2: Common Screens
- [X] Create `:feature:common_screens` module
- [X] Implement `SplashScreen.kt`
- [X] Implement `WelcomeOnboardingScreen.kt`
- [X] Implement `PrivacyPolicyScreen.kt`
- [X] Implement `TermsAndConditionsScreen.kt`
- [X] Implement `ReturnPolicyScreen.kt`
- [X] ViewModel for Onboarding (if needed)
- [X] Integrate into main NavHost
- [X] Koin Module for ViewModels

## Phase 3: User Flow - Part 1 (Dashboard, Profile, Products)
- [ ] Create `:feature:user_dashboard` module
- [ ] Create `:feature:products` module
- [ ] **User Dashboard**
    - [ ] `UserDashboardScreen.kt` (Bottom Nav/Layout)
    - [ ] `UserProfileScreen.kt` (View details)
    - [ ] `UserChangePasswordScreen.kt`
    - [ ] ViewModels, UseCases, Repo methods for profile & password change
- [ ] **Product Listing & Details (`:feature:products`)**
    - [ ] `ProductApiService` methods (get all, by id, by category)
    - [ ] Domain: `Product` model, `ProductRepository` interface, UseCases
    - [ ] Data: Product DTOs, `ProductRepositoryImpl`
    - [ ] UI: `AllProductsScreen.kt`
    - [ ] UI: `SingleProductScreen.kt` (Coil for images)
    - [ ] ViewModels for products
    - [ ] Koin modules
- [ ] Integrate navigation
- [ ] Unit & UI Testing

## Phase 4: User Flow - Part 2 (Cart, Wishlist, Orders, Checkout)
- [ ] Create `:feature:cart` module
- [ ] Create `:feature:wishlist` module
- [ ] Create `:feature:orders` module (User part)
- [ ] **Cart (`:feature:cart`)**
    - [ ] `CartApiService` methods
    - [ ] Domain/Data: `CartItem` model, `CartRepository`, UseCases
    - [ ] UI: `UserCartScreen.kt`
    - [ ] ViewModel, Koin module
- [ ] **Wishlist (`:feature:wishlist`)**
    - [ ] `WishlistApiService` methods
    - [ ] Domain/Data: `WishlistItem` model, `WishlistRepository`, UseCases
    - [ ] UI: `UserWishlistScreen.kt`
    - [ ] ViewModel, Koin module
- [ ] **Order (`:feature:orders` - User part)**
    - [ ] `OrderApiService` methods (get user orders, create order)
    - [ ] Domain/Data: `Order` model, `OrderRepository`, UseCases
    - [ ] UI: `UserOrderHistoryScreen.kt`
    - [ ] UI: `CheckoutScreen.kt`
    - [ ] UI: `PaymentSuccessScreen.kt`
    - [ ] UI: `AllInvoicesScreen.kt` (User invoices)
    - [ ] ViewModels, Koin modules
- [ ] Integrate Navigation & Testing

## Phase 5: Seller Flow
- [ ] Module structure for Seller (e.g., `:feature:seller_auth`, `:feature:seller_dashboard`)
- [ ] Seller Authentication (Login/Signup)
- [ ] `SellerDashboardScreen.kt`
- [ ] `SellerProfileScreen.kt`, `SellerChangePasswordScreen.kt`
- [ ] Product Management (Seller view)
    - [ ] `AllProductScreen.kt` (Seller)
    - [ ] `CreateProductScreen.kt`
    - [ ] `EditProductScreen.kt`
- [ ] Order Management (Seller view)
    - [ ] `AllOrdersScreen.kt` (Seller)
- [ ] Invoices (Seller view)
- [ ] APIs, Domain/Data, ViewModels, UI, Koin, Nav, Tests for Seller features

## Phase 6: Admin Flow
- [ ] Module structure for Admin (e.g., `:feature:admin_auth`, `:feature:admin_dashboard`)
- [ ] Admin Authentication (Login/Signup)
- [ ] `AdminDashboardScreen.kt`
- [ ] `AdminProfileScreen.kt`, `AdminChangePasswordScreen.kt`
- [ ] Product Management (Admin view all)
- [ ] Order Management (Admin view all)
- [ ] `AllUsersScreen.kt` (Admin manage users)
- [ ] `AllSellersScreen.kt` (Admin manage sellers)
- [ ] APIs, Domain/Data, ViewModels, UI, Koin, Nav, Tests for Admin features

## Phase 7: Refinement, Testing, and Polish
- [ ] End-to-End Testing
- [ ] UI Polish & UX review
- [ ] Performance Optimization
- [ ] Error Handling Review
- [ ] Code Cleanup & Refactoring
- [ ] Final PRD requirements review

## Recurring
- [ ] Regular Git Commits & Branching
- [ ] Code Reviews
- [ ] Dependency Updates Check
- [ ] Documentation Updates (LLD, HLD, Code comments)
