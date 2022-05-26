# Weather-Forecast
This android application fetches data from a weather api and presents the weather forecast, built using the clean architecture guideline.

With layers including;
 - The domain layer which consists of use cases that help handled business logic, its mapper and models and repository interface definition to allow for abstraction of information rather than concretion.
 - The data layer which consists of the repository implementation and helps manage data flow and transfer between the remote and local datasources layer.
 - The data sources layer consists of the remote source from the weather api and local source which uses Room for caching of information.
 - The presentation layer, this layer uses MVVM for data transfer to the views and also employs sealed classes for event and state handling.

1. The room database helped to integrate offline caching hence allowing for access to cached data on the app when there is no internet and the user can choose to refresh with a swipe to refresh or a reload button when internet is back. This ensures that the weather information is always available to the user anytime the app is launched after the initial fetching from the remote source.

2. Motion layout was employed in animating views and also the anim folder in android. This improved the aesthetics of the application.

3. For each weather conditon there is a beautiful display of animated icons, this also was done to improve the UI and UX of the app.

4. Employing two usecases in the domain layer, devised an algorithm to search through a given string, extract temperature values and convert them to its equivalent word expression before presenting the data to the user, TDD was important in getting an efficient solution and covering test cases. e.g 23°C became twenty three degrees.

Technologies used include: Flow, Motion Layout, Room, Coil, Navigation Component, MVVM, Retrofit, Kotlin, Clean Architecture

Screenshots
![Screenshot_20220526-095906_Weather Forecast]
Main Weather Screen             |  Main Weather Screen
:-------------------------:|:-------------------------:
![Main Screen 1](https://user-images.githubusercontent.com/40584796/170474625-b5ae3716-ae15-4e58-ac6d-6856ef4675ff.jpg)|![Main Screen 2](https://user-images.githubusercontent.com/40584796/170474776-7868692b-d237-47a8-abc7-20271f67cf16.jpg)

Forecast Days Gallery             |  Single Day Weather Screen
:-------------------------:|:-------------------------:
![Forecast Days Gallery](https://user-images.githubusercontent.com/40584796/170475000-12deaf90-a887-426d-8a43-eacad4b4f47b.jpg)|![Specific Date Weather Screen](https://user-images.githubusercontent.com/40584796/170475127-9632949f-6c13-4528-a7fb-80cf6df15f9c.jpg)

Place Screen               | Offline Cache Status
:-------------------------:|:-------------------------:
![Place Screen](https://user-images.githubusercontent.com/40584796/170475352-d7455ca3-4444-4489-aee7-99e9a8c65855.jpg)|![Offline Cache Status](https://user-images.githubusercontent.com/40584796/170475528-42d9b62c-777a-46ba-9563-fe7344dd8850.jpg)

Animated icons from: flaticon.com














