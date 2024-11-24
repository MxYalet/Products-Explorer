# Product Explorer App

A modern Android application that showcases products from the Fake Store API with offline support and a clean Material Design interface.

## Screenshots

![Screenshot_20241124_033729](https://github.com/user-attachments/assets/759d9bed-9572-4d22-aad4-34d5c48fe9e1)
![Screenshot_20241124_033815](https://github.com/user-attachments/assets/9ef551af-1794-4775-b08b-5264888b7dea)
![Screenshot_20241124_033824](https://github.com/user-attachments/assets/0636648c-6892-47fc-8d94-f6f178409f1b)
![Screenshot_20241124_033833](https://github.com/user-attachments/assets/b8cae6d7-bbda-4340-a242-5ba7ffdea1f5)


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
- **Networking**: Retrofit for API communication
- **Asynchronous Operations**: Kotlin Coroutines and Flow
- **UI Components**: Material Design 3
- **Testing**: JUnit for unit tests

## Project Structure

```
app/
├── adapter
│   ├── ProductAdapter
├── data/
│   ├── local/
│   │   ├── ProductDao.kt
│   │   └── ProductDatabase.kt
├── model/
│   │   └── Product.kt
|
│   ├── remote/
│   │   ├── ApiService.kt
│   │   
│   └── repository/
│       └── ProductRepository.kt
├── di/
│   └── AppModule.kt
├── ui/
│   ├── viewModel/
│   |   |──ProductListViewModel
│   └──  ProductDetailsFragment.kt
│   └──  ProductsScreenFragment.kt
│   └──  SplashScreenFragment.kt
└── utils/
    └── NetworResult.kt
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
     - Skeleton loading screens

2. **Technical Improvements**
   - Implement pagination for better performance with large datasets
   - Add more comprehensive error handling
   - Improve retry mechanisms for failed network requests

3. **Features to Add**
   - User favorites functionality
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
