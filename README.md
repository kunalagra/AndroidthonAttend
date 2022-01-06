# Attendance Manager app

This readme file is designed for FYJC students of Thakur College to
help them navigate easily thorugh our app

## Problem Statement :-

Due to COVID pandemic all colleges have started using digital
medium (zoom and goggle meet) for teaching students. Yet a
major problem of attendance still persists and we like to solve
it using technology. Mobile Application can be used effectively
for this particular job as they are widely used and are known
for their easy access.

## Proposed Solution :-

Our project 'Attendance Manager app' is used to monitor daily
attendane of FYJC students for the subjects Physics, Chemistry
& Mathematics. Students can view their daily attendance for all
subjects at one go. It is for local use of Thakur College now but
we intend to launch it globally.

## How it works?

1. Signup or login

   ![Signup](https://user-images.githubusercontent.com/96176706/148374004-b2a023f8-a371-40fb-a256-c822d2c9eef1.jpeg)
   ![login](https://user-images.githubusercontent.com/96176706/148374251-09b73903-19b7-48f9-94f4-11c5761f28e8.jpeg)

2. Home Screen & Choose Subject

   ![Homescreen](https://user-images.githubusercontent.com/96176706/148374365-743acb53-4a2f-4f6e-880e-e987cf0592dc.jpeg)
   
3. Select Date
   
   ![Selectdate](https://user-images.githubusercontent.com/96176706/148374406-8b1eb15c-315a-4aa3-884b-d09deb97b252.jpeg)

4. Data
 
   ![Data](https://user-images.githubusercontent.com/96176706/148374450-992037b6-407c-4d68-99a7-34cfcb22dd9d.jpeg)



## Concepts used :-

We have made an user friendly app which helps students check their attendance. Below is the list 
of concepts used in our app. 

* Jetpack libraries -

  -ViewModel: Facilitates the management of data related to UI in a lifecycle-aware manner. Further,
  it also re-constructs the Activity or Fragment during any configuration change like device rotation.

  -Navigation: Framework for structuring the in-app UI and to see and manage the navigation
  properties visually.

  -Layout: Contains information regarding the declaration of different kinds of layouts like
  LinearLayout, RelativeLayout, ContraintLayout.

  -LinearLayout: It is a ViewGroup that is responsible for holding views in it. It is a layout that
  arranges its children i.e the various views and layouts linearly (one after another) in a single
  column(vertically) or a single row(horizontally).

  -Constraint Layout - ConstraintLayout provides you the ability to completely design your UI with
  the drag and drop feature provided by the Android Studio design editor. It helps to improve the
  UI performance over other layouts. With the help of ConstraintLayout, we can control the group of
  widgets through a single line of code. With the help of ConstraintLayout, we can easily add
  animations to the UI components which we used in our app.

  -RecyclerView :  A RecyclerView is an advanced version of ListView with improved performance. When
  you have a long list of items to show you can use RecyclerView. It has the ability to reuse its
  views. In RecyclerView when the View goes out of the screen or not visible to the user it won’t
  destroy it, it will reuse these views. This feature helps in reducing power consumption and
  providing more responsiveness to the application.
  
* Firebase Authorization - You can sign in users to yourFirebase app either by usingFirebaseUI as a 
    complete drop-in auth solution or by using theFirebase Authentication SDK to manually integrate 
    one or several sign-in methods into your app.
  
* Firebase realtime database - The Firebase Realtime Database is a cloud-hosted database. Data is
  stored as JSON and synchronized in realtime to every connected client. When you build 
  cross-platform apps with our Apple platforms, Android, and JavaScript SDKs, all of your clients 
  share one Realtime Database instance and automatically receive updates with the newest data.
  
* LiveData -  Live data only updates the app components like Activity or Fragments which are
  in active life cycle state. LiveData notifies the observer(Activity or Fragment) which are in 
  Started or Resumed life cycle state. Inactive observers registered to watch LiveData objects 
  aren’t notified about changes. Here inactive observers mean which are not in the state of 
  Started or Resumed. One can register an observer paired with an object that implements the 
  LifecycleOwner interface
  
* Room database - Room is one of the Jetpack Architecture Components in Android. This provides an
  abstract layer over the SQLite Database to save and perform the operations on persistent data 
  locally. This is recommended by Google over SQLite Database although the SQLite APIs are more 
  powerful they are fairly low-level, which requires a lot of time and effort to use. But Room 
  makes everything easy and clear to create a Database and perform the operations on it.
  
## Features :-

- Easy to use
- Simple and Intutive UI
- LightWeight App and no Ads
- Login/Signup/Forgot Password using Firebase
- Full Supoort for Dark Theme
- Online Hosted database (Realtime Databae) for Attendance Details
- Material Colors
- Auto Switch to Day/Night Mode based on System Theme
- Material Bottom Navigation

## Installation

You can access our app through : ( apk link)

## Future Scope

We plan to implement support for teachers to mark/change students being absent or present.
We also plan to add issue forms for students to raise request to change status.
We also plan to add feature to support for Multiple Colleges so that we can launch our app
globally app globally and integrate all the students from various colloges from 
their start in career till the very end. We intend to be one stop solution for all the universities,
colleges and schools when it comes of maintaining ateendance. We would also like integrate 
learning process and expand oursevlves to an edtech revolution by utilising data of students.
    
