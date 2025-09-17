# Low-Level Design (LLD) - Ecom App

## 1. Introduction
This document provides a detailed low-level design for the Ecom application, elaborating on the HLD. It covers specific implementations for core and feature modules, data models, and component interactions.

## 2. Core Modules Implementation
### 2.1. `:core:ui`
- **Theme (`Theme.kt`):** Material 3 `ColorScheme` setup using `#007200` as primary. Defines `DarkColorScheme` and `LightColorScheme`.
- **Typography (`Type.kt`):** Defines `Typography` with custom font families (if any) and text styles.
- **Shapes (`Shape.kt`):** Defines corner radius values for components.
- **Common Composables:**
    - `AppButton(onClick: () -> Unit, text: String)`
    - `AppTextField(value: String, onValueChange: (String) -> Unit, label: String, leadingIcon: ImageVector?)`
    - `LoadingIndicator()`
    - `ErrorDialog(message: String, onDismiss: () -> Unit)`

### 2.2. `:core:common`
- **Constants (`Constants.kt`):**
    - `BASE_URL = "http://localhost:3000/api/v1"`
    - `PREF_AUTH_TOKEN`, `PREF_USER_ONBOARDED`
- **Utils (`Validator.kt`, `DateFormatter.kt`):** Helper functions for input validation, date conversions, etc.
- **Resource Files:** `strings.xml`, `dimens.xml`.

### 2.3. `:core:data`
- **Ktor Client (`NetworkModule.kt` or `KtorClient.kt`):**
    ```kotlin
    // Koin module providing HttpClient
    val networkModule = module {
        single {
            HttpClient(Android) { // CIO engine can also be used
                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(Auth) {
                    // Bearer token auth setup, to be configured
                }
                defaultRequest {
                    contentType(ContentType.Application.Json)
                    url(Constants.BASE_URL) // Prepend base URL
                }
            }
        }
    }
    ```
- **API Service Interfaces & Implementations:**
    - For each entity in `ApiEndPoints.md` (User, Product, etc.):
        - `UserApiService.kt`: `suspend fun register(request: UserRegisterRequest): UserRegisterResponse`
        - `UserApiServiceImpl(private val httpClient: HttpClient): UserApiService`
- **Data Transfer Objects (DTOs):** Located per feature or in a shared `:core:data/dtos` package.
    - Example: `UserRegisterRequest.kt`, `ProductDto.kt`. All DTOs are Kotlin `data class` with `@Serializable`.
- **Repository Implementations:**
    - `UserRepositoryImpl(private val userApiService: UserApiService, private val appPreferences: AppPreferences): UserRepository`
    - Handles mapping DTOs to Domain Models, manages token storage via `AppPreferences`.
- **Local Data Source (`AppPreferences.kt`):** For SharedPreferences access (e.g., auth token, onboarding status).

### 2.4. `:core:domain`
- **Domain Models (`User.kt`, `Product.kt`, `Order.kt`):**
    - `data class User(val id: String, val fullname: String, val email: String, ...)`
- **Repository Interfaces (`UserRepository.kt`, `ProductRepository.kt`):**
    - `interface UserRepository { suspend fun login(credentials: LoginCredentials): Result<User> }`
- **Use Cases (Example: `LoginUserUseCase.kt`):**
    ```kotlin
    class LoginUserUseCase(private val userRepository: UserRepository) {
        suspend operator fun invoke(credentials: LoginCredentials): Result<User> {
            // Business logic, e.g., validation before calling repository
            return userRepository.login(credentials)
        }
    }
    ```
    (Using a `Result` wrapper like `kotlin.Result` or a custom sealed class for success/failure).

## 3. Feature Module Design (Example: `:feature:authentication`)
### 3.1. Screens (Jetpack Compose)
- `LoginScreen(navController: NavController, viewModel: LoginViewModel)`
- `SignupScreen(navController: NavController, viewModel: SignupViewModel)`
- `ForgotPasswordScreen(navController: NavController, viewModel: ForgotPasswordViewModel)`
- Each screen observes ViewModel state and sends intents.

### 3.2. ViewModels (MVI)
- **`LoginViewModel(private val loginUserUseCase: LoginUserUseCase, private val sessionManager: SessionManager)`:**
    - **State (`LoginContract.State`):**
        ```kotlin
        data class State(
            val email: String = "",
            val password: String = "",
            val isLoading: Boolean = false,
            val error: String? = null,
            val selectedUserType: UserType = UserType.USER // For unified login
        )
        ```
    - **Intent (`LoginContract.Intent`):**
        ```kotlin
        sealed class Intent {
            data class EmailChanged(val email: String) : Intent()
            data class PasswordChanged(val password: String) : Intent()
            data class UserTypeSelected(val userType: UserType): Intent()
            object LoginClicked : Intent()
        }
        ```
    - **Effect (`LoginContract.Effect`):**
        ```kotlin
        sealed class Effect {
            object NavigateToDashboard : Effect()
            data class ShowToast(val message: String) : Effect()
        }
        ```
    - Handles intents, calls use cases, updates state, and emits effects.

### 3.3. Koin Module (`authDIModule.kt`)
```kotlin
val authModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignupViewModel(get(), get()) }
    // Add other ViewModels
    factory { LoginUserUseCase(get()) } // Assuming UserRepository is provided elsewhere or in a data module
    factory { RegisterUserUseCase(get()) }
    // Provide AuthRepository implementation from :core:data
}
```

### 3.4. Navigation (`AuthNavGraph.kt`)
Defines Composable routes for `login`, `signup`, `forgotPassword` within the authentication flow, managed by a nested NavController.

## 4. Data Models & DTOs
- DTOs in `:core:data` will strictly match the JSON structures in `ApiEndPoints.md`.
- Domain models in `:core:domain` will represent the app's understanding of entities, potentially cleaner or with added client-side logic/fields.
- Mappers will be used in Repository implementations to convert DTOs to Domain Models.

## 5. Error Handling in ViewModels & UI
- Use Cases and Repositories return `Result<T>` (e.g., `Result.success(data)` or `Result.failure(exception)`).
- ViewModels catch these results:
    - On success, update state with data.
    - On failure, update state with an error message (extracted from exception).
- UI observes the `error` field in the State and displays an `ErrorDialog` or inline error message.
- `isLoading` state is used to show/hide `LoadingIndicator`.

## 6. Image Loading with Coil
- In Composables:
  ```kotlin
  AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
          .data("product_image_url")
          .crossfade(true)
          .build(),
      placeholder = painterResource(R.drawable.placeholder_image),
      error = painterResource(R.drawable.error_image),
      contentDescription = "Product Image",
      contentScale = ContentScale.Crop
  )
  ```
- Configure Coil globally in Application class if needed (e.g., custom disk cache).
