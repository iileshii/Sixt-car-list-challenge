# Sixt-car-list-challenge

# SOLUTION DESCRIPTION

The app contains 3 main layer:
- presentation
- repository
- domain

They are close to the Clean architecture layers: 
- domain layer here is just domain data classes
- repository layer handles everything about data sources and parsing them
- presentation layer makes datas visible

Also every layer is separated to single module.

## Domain layer 
Domain layer contains only domain models, so it's better to use koltin data classes here. Data classes bring a possibility to change model structure faster.

## Repository layer
- OkHttp + Retrofit as "de facto" standard libraries to make server calls
- Kotlin serialization for serialization/deserealization, and Jake Wharton json convertor factory to connect it to retrofit
- Kotlin coroutines for making asynchronous calls
- RepositoryFactory class is a custom Simple Dependecy Injector to hide repository realizations under interfaces. We don't have to change anything in presentation or domain layer, if we want to change caching method, combine models on server or something else.
- LiveData for asynchronous data source for presentation layer

## Presentation layer
- Splash is implemented to make "cold launch" more responsible by using splash theme for activity. Also it's a good place to make initial server calls, initialize database and so on (for extending)
- MainActivity has starter design pattern to simplify its launching from Splash screen
- ViewModel architecture component is implemented to handle activity and fragment lifecycles stable
- Constraint layout is implemented to have a posibility to design maintainable complex layouts
- Recycler view is implemented to show list
- Injector is for providing view model factories

## Things to improve
- Handle Loading states - EMPTY, LOADING, LOADED, ERROR for each layout
- Testing / there is nothing now ;-( but the architecture is ready to implement tests
