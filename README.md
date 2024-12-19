# Mental Health Tracker

<img src="https://github.com/Richnell/Mental_Health-_Tracker_App/blob/main/Mental%20Health%20Tracker.png?raw=true" width="200"/>

A console-based application that helps users monitor their mental health by logging moods, viewing history, and generating detailed mood reports.

## 📖 Table of Contents
1. [Project Overview](#-project-overview)
2. [Objectives](#-objectives)
3. [Features](#-features)
4. [How OOP Principles Were Applied](#-how-oop-principles-were-applied)
5. [Mental Health Benefits](#-mental-health-benefits)
6. [SDGs Connection](#-sdgs-connection) 
7. [Contributors](#-contributors)

## 🌍 Project Overview
The Mental Health Tracker is a command-line application designed to empower users in maintaining their mental well-being. By logging daily moods, reviewing mood histories, and generating reports, the application helps users identify patterns and gain insights into their emotional health.

## 🎯 Objectives
- **Promote Self-awareness:** Help users understand their emotional patterns through consistent tracking.
- **Encourage Positive Habits:** Provide tips for improving mental well-being based on logged moods.
- **Simplified Interaction:** Offer a user-friendly interface for effortless logging and review.

## 🚀 Features
- User Registration and Login System. 🔐
- Mood Logging with predefined categories like Happy, Sad, Stressed, etc. 📝
- Personalized mood tips based on the logged mood. 💡
- Mood History View to track daily entries. 📅
- Generate detailed mood reports with percentage breakdowns of logged emotions.

## 💻 How OOP Principles Were Applied

This project makes use of Object-Oriented Programming (OOP) principles, such as:

**Encapsulation:** The app encapsulates mood-related logic within the MoodTracker and user-related logic within the User class. Data like users and moods are hidden behind methods and are not directly accessible from outside the class.

**Inheritance:** The MoodTracker class extends the abstract Tracker class, inheriting common functionality such as saving entries to a file and loading user data. This demonstrates code reuse and promotes a cleaner design.

**Abstraction:** The Tracker class provides an abstract representation for mood tracking, which can be extended for other types of tracking (e.g., physical health, exercise). Users interact with high-level methods without worrying about the underlying implementation.

**Polymorphism:** Methods like logEntry, viewHistory, and generateReport are abstract in the Tracker class and are implemented differently in the MoodTracker subclass, showing how the app can be expanded with different types of trackers in the future.


## 🧠 Mental Health Benefits
- **Increased Self-Awareness:** By reviewing mood history, users can better understand the causes and effects of their emotions.
- **Actionable Insights:** Tailored suggestions provide users with strategies to manage or enhance their mental health.
- **Emotional Tracking:** Consistent monitoring enables early detection of recurring negative patterns.

## 🌏 SDGs Connection
This project supports several **United Nations Sustainable Development Goals (SDGs):**
- **SDG 3: Good Health and Well-being**  
  Promotes mental health and well-being by encouraging self-awareness, emotional tracking, and positive mental habits.
  
- **SDG 4: Quality Education**  
  Enhances understanding of mental health by offering actionable insights and tips to users for self-improvement.
  
- **SDG 10: Reduced Inequalities**  
  Provides an inclusive and accessible mental health platform for individuals from diverse backgrounds.

## 🗂 Instructions for Running the Program

1. Clone the repository:
You can clone this repository to your local machine using:

- git clone https://github.com/yourusername/mental-health-tracker.git

3. Compile the code:
Ensure you have Java installed on your machine. Compile the Java files using the following command:

- javac *.java 

5. Run the application:
After compilation, run the MentalHealthTracker class to start the app:

- java MentalHealthTracker

6. Interacting with the App:
   
- **Register:** Create a new account by providing a username and password.

- **Login:** After registration, log in with your username and password.

- **Log Mood:** Record your mood for the day, and receive helpful tips.

- **View Mood History:** View your previously logged moods.

- **Generate Mood Report:** View a summary of your moods over time.
  
7. ## 👷‍ Contributors
| Name                    | Role       | Email                          |
|-------------------------|------------|--------------------------------|
| John Richnell Catibog   | Developer  | 23-04985@g.batstate-u.edu.ph   |

## 📘 Course: 
- IT 211 - Database Management System

## 🧑‍🏫 Course Facilitator: 
- Owen Patrick Falculan
