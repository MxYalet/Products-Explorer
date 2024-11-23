# Product Explorer App

A modern Android application that showcases products from the Fake Store API with offline support and a clean Material Design interface.

## Screenshots

![Product List Screen](/screenshots/list_screen.png)
*Product List Screen - Displays all products in a clean, scrollable list*

![Product Details Screen](/screenshots/details_screen.png)
*Product Details Screen - Shows comprehensive product information*

## Features

- Browse products in an intuitive list interface
- View detailed product information including images, descriptions, and pricing
- Offline support with local data caching
- Material Design components for a modern Android feel
- Responsive design that adapts to different screen sizes

## Technical Stack

- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Database**: Room for local data persistence
- **Networking**: Retrofit with Moshi for API communication
- **Asynchronous Operations**: Kotlin Coroutines and Flow
- **UI Components**: Material Design 3
- **Testing**: JUnit for unit tests

## Project Structure

```
app/
├── data/
│   ├── local/
│   │   ├── ProductDao.kt
│   │   └── ProductDatabase.kt
│   ├── remote/
│   │   ├── ProductApi.kt
│   │   └── ProductService.kt
│   ├── model/
│   │   └── Product.kt
│   └── repository/
│       └── ProductRepository.kt
├── di/
│   └── AppModule.kt
├── ui/
│   ├── list/
│   │   ├── ProductListFragment.kt
│   │   ├── ProductListViewModel.kt
│   │   └── ProductAdapter.kt
│   └── details/
│       ├── ProductDetailsFragment.kt
│       └── ProductDetailsViewModel.kt
└── utils/
    └── NetworkUtil.kt
```

## Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/yourusername/product-explorer.git
```

2. Open the project in Android Studio Arctic Fox or later

3. Build the project:
   - Click on Build → Make Project
   - Or use the keyboard shortcut: Ctrl+F9 (Windows) or Cmd+F9 (Mac)

4. Run the app:
   - Connect an Android device or use an emulator
   - Click on Run → Run 'app'
   - Or use the keyboard shortcut: Shift+F10 (Windows) or Control+R (Mac)

## Architecture Decisions and Trade-offs

### What Went Well
1. **MVVM Architecture**
   - Clear separation of concerns
   - Easy to test components independently
   - Maintainable and scalable codebase

2. **Offline Support**
   - Implemented using Room database
   - Single source of truth pattern
   - Smooth user experience even without internet

3. **Dependency Injection**
   - Used Hilt for clean and efficient DI
   - Reduced boilerplate code
   - Better testability

### Trade-offs and Future Improvements

1. **UI/UX Enhancements**
   - With more time, would add:
     - Product search functionality
     - Filtering and sorting options
     - Pull-to-refresh feature
     - Skeleton loading screens

2. **Technical Improvements**
   - Implement pagination for better performance with large datasets
   - Add more comprehensive error handling
   - Implement retry mechanisms for failed network requests
   - Add integration tests
   - Implement WorkManager for background sync

3. **Features to Add**
   - User favorites functionality
   - Product categories
   - Share product feature
   - Dark theme support

4. **Testing Coverage**
   - Current focus was on critical business logic
   - Would add more UI tests using Espresso
   - Implement screenshot testing
   - Add integration tests for the database and network layers

## Testing

The project includes unit tests for:
- ViewModels
- Repository
- Data mapping logic

To run tests:
1. Right-click on the `app/src/test` folder
2. Select "Run Tests" or use the keyboard shortcut

## API Reference

The app uses the [Fake Store API](https://fakestoreapi.com/) with the following endpoints:

- GET `/products` - Fetch all products
- GET `/products/{id}` - Fetch specific product details

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
