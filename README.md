# Delivery Android App <h1> 
## A simple demo project for Deliveries based on MVVM clean architecture. <h2> 
## App Features  <h2> 
* Users can view list of the deliveries items . 
* Users can go to details of delivery items by click on list .
* User can view the address on the marker of Map .
## App Architecture  <h2> 
<a target="_blank" rel="noopener noreferrer" href="https://image.slidesharecdn.com/droidcontlv2017-guybaron-androidarchitecturecomponents-180101085727/95/android-architecture-components-guy-bar-on-vonage-7-638.jpg?cb=1514797085"><img src="https://image.slidesharecdn.com/droidcontlv2017-guybaron-androidarchitecturecomponents-180101085727/95/android-architecture-components-guy-bar-on-vonage-7-638.jpg?cb=1514797085" width="500" style="max-width:100%;"></a>
## The app includes the following main components:<h2>
* A local database that servers as a single source of truth for data presented to the user.
* A web api service.
* A repository that works with the database and the api service, providing a unified data interface.
* A ViewModel that provides data specific for the UI.
* The UI, which shows a visual representation of the data in the ViewModel.
## App Specs <h2>
* Minimum SDK 15
* Java8 (in master branch) & Kotlin (in kotlin_support branch)
* MVVM Architecture
* Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
* RxJava2 for implementing Observable pattern.
* Dagger 2 for dependency injection.
* Retrofit 2 for API integration.
* Picasso for image loading.
* Unit Test (Db , WebAPI , UI , ViewModel) .
* Espresso UI testing.
