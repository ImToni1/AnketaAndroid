# AnketaAndroid

## Description

AnketaAndroid is a simple Android application developed in Android Studio that demonstrates the creation of a multi-screen survey. Users navigate through various screens (Activities) where they input different types of data. At the end, a summary of all entered information is displayed. The application also showcases the use of `SharedPreferences` for temporarily saving data between sessions within individual survey parts and passing data between Activities using `Intent` objects.

## Features

The application consists of the following data input sections:

* **Start Screen (`MainActivity`):** Displays a button to start the survey.
* **Text Input (`ActivityText`):** Collects basic textual user data:
    * Name
    * Last Name
    * Email
    * Password
* **Numeric Input (`ActivityNumeric`):** Collects numerical data:
    * Year of birth
    * Height
* **Option Selection (`ActivityCheckRadio`):** Allows user selection via:
    * CheckBox elements (e.g., interests like Travel, Sport, Music)
    * RadioButton elements (e.g., age group)
* **Date and Time Input (`ActivityDateTime`):** Uses `DatePickerDialog` and `TimePickerDialog` for date and time input.
* **Spinner Selection (`ActivitySpinner`):** Allows selection of one option (e.g., country) from a dropdown menu.
* **Finish Screen (`ActivityFinish`):** Displays all the data entered by the user throughout the survey.
* **State Saving:** Uses `SharedPreferences` to save entered data within each activity, so data is not lost if the user temporarily leaves an activity.
* **Localization:** The application supports Croatian and English languages.

## Technologies Used

* Android Studio
* Java
* Android SDK
* Gradle
* AppCompat, Material Design components, ConstraintLayout
* SharedPreferences for data persistence
* Intent for navigation and data passing between activities

## Setup and Running

1.  Open the project in Android Studio.
2.  Sync Gradle files if necessary.
3.  Run the application on an emulator or a physical Android device (minimum API level 24).

## Project Structure

The main application logic is located in `app/src/main/java/com/example/anketa/`:

* `MainActivity.java`: The starting activity.
* `ActivityText.java`: Activity for text data input.
* `ActivityNumeric.java`: Activity for numerical data input.
* `ActivityCheckRadio.java`: Activity for input using CheckBox and RadioButton elements.
* `ActivityDateTime.java`: Activity for date and time input.
* `ActivitySpinner.java`: Activity for selection from a dropdown menu.
* `ActivityFinish.java`: Activity that displays the survey summary.

Layout files (.xml) for each activity are located in `app/src/main/res/layout/`.
