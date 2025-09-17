# High-Level Design (HLD) - Ecom App

## 1. Introduction
This document outlines the high-level architecture for the Ecom Android application. The app will provide a frontend for an existing Node.js/Express backend, focusing on a clean, maintainable, and scalable MVI (Model-View-Intent) architecture with feature-based multi-module organization.

## 2. Goals
- Production-ready, industry-standard code.
- Adherence to Clean Architecture and SOLID principles.
- Feature-based modularization for scalability and independent development.
- Efficient API interaction using Ktor.
- Modern UI with Jetpack Compose and Material 3.
- Robust dependency injection using Koin.

## 3. Architecture Overview
- **Presentation Layer:** Jetpack Compose (UI), ViewModels (MVI), Navigation Components.
- **Domain Layer:** Use Cases, Domain Models, Repository Interfaces.
- **Data Layer:** Repository Implementations, API Service (Ktor), Local Data Source (SharedPreferences for session/preferences).
- **Dependency Injection:** Koin for managing dependencies across layers and modules.

## 4. Module Structure (Feature-based)
```
Ecom/
├── app/ # Main application module, DI setup, navigation host
├── :feature:authentication/
│   ├── src/main/java/...
│   └── build.gradle.kts
├── :feature:user_dashboard/
├── :feature:seller_dashboard/
├── :feature:admin_dashboard/
├── :feature:products/
├── :feature:orders/
├── :feature:cart/
├── :feature:wishlist/
├── :feature:common_screens/ # Splash, Onboarding, Policy, ToS, etc.
├── :core:ui/ # Shared Jetpack Compose components, Theme, Typography, Colors
├── :core:common/ # Utility classes, Constants, String resources, Extension functions
├── :core:data/ # Repository implementations, Ktor client setup, API service interfaces & DTOs, Local data sources (Preferences)
├── :core:domain/ # Domain models, Repository interfaces, Use cases (business logic)
└── buildSrc/ # Optional: for build logic and dependencies management (or use libs.versions.toml)
```

## 5. Key Technology Stack
- **UI:** Jetpack Compose, Material 3.
- **Architecture:** MVI (Model-View-Intent).
- **Asynchronous Operations:** Kotlin Coroutines & Flow.
- **Networking:** Ktor client (with Kotlinx Serialization for JSON).
- **Dependency Injection:** Koin.
- **Image Loading:** Coil.
- **Navigation:** Jetpack Navigation for Compose.
- **Build System:** Gradle with Kotlin DSL.

## 6. Data Flow (MVI)
1.  **View (Composable Screen):** User interacts, triggers an Intent.
2.  **ViewModel:** Receives Intent, processes it (may involve Use Cases).
3.  **Use Case (Domain Layer):** Executes business logic, interacts with Repository.
4.  **Repository (Data Layer):** Fetches data from API (Ktor) or local source.
5.  **ViewModel:** Receives data from Use Case, updates its State (immutable).
6.  **View:** Observes State changes (e.g., using `collectAsStateWithLifecycle`) and re-renders the UI.
7.  **ViewModel:** Can also send one-time Effects/Events (e.g., navigation, showing a Toast) to the View via a separate Flow/Channel.

## 7. API Interaction (Ktor)
- Centralized Ktor `HttpClient` configuration in `:core:data`.
- API endpoint definitions as interfaces (e.g., `UserApiService`) in `:core:data`.
- Ktor client will handle JSON serialization/deserialization using `kotlinx.serialization`.
- Bearer token authentication will be handled via Ktor Auth feature or interceptors.
- Base URL: `http://localhost:3000/api/v1` (as per `ApiEndPoints.md`).

## 8. Authentication & Authorization
- REST API for authentication (Login, Register, Forgot Password for User, Seller, Admin).
- JWT (assumed based on "Bearer <token>") will be stored securely (e.g., SharedPreferences, potentially EncryptedSharedPreferences).
- Ktor client will automatically attach the token to authorized requests.
- Role-based access will be primarily enforced by the backend, but the frontend will navigate to appropriate dashboards/features based on user role after login.

## 9. UI Design - Jetpack Compose & Material 3
- **Theme:** Base/primary color `#007200` will be used to generate Material 3 `ColorScheme`.
- Reusable UI components (buttons, text fields, cards) will be defined in `:core:ui`.
- Screens will be built entirely with Composables.

## 10. Navigation
- Jetpack Navigation for Compose will manage screen transitions.
- Nested navigation graphs for features (e.g., auth flow, user dashboard flow).
- Main navigation host in the `app` module.

## 11. Error Handling
- Repositories and Use Cases will use `Result` wrappers or similar mechanisms to propagate success/failure.
- ViewModels will catch errors and expose them in the State for the UI to display (e.g., Snackbars, dialogs, or inline error messages).

## 12. State Management
- ViewModel's `StateFlow` will hold the screen state.
- UI will observe the `StateFlow` and react to changes.
- For one-time events (navigation, toasts), `SharedFlow` or `Channel` will be used (Effects).

## 13. Modules Dependencies
- `app` depends on all `feature` modules and relevant `core` modules.
- `feature` modules depend on relevant `core` modules (e.g., `:core:domain`, `:core:ui`, `:core:common`).
- `feature` modules generally do not depend on each other directly; inter-feature navigation is handled via the `app` module's navigation graph or through defined navigation contracts.
- `:core:data` depends on `:core:domain` (for repository interfaces).
- `:core:domain` has no Android framework dependencies (pure Kotlin).

## 14. Testing Strategy (Brief)
- **Unit Tests:** For ViewModels, Use Cases, Repositories (mocking dependencies).
- **Integration Tests:** For testing interactions between layers (e.g., ViewModel -> Use Case -> Repository).
- **UI Tests (Espresso/Compose Test Rule):** For Composable screens and user flows.

## 15. Future Considerations
- Offline support (caching strategies).
- Real-time updates (WebSockets if needed).
- Analytics integration.
- CI/CD pipeline.
