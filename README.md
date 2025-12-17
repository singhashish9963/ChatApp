# 📱ConnectX – Android (Firebase)

A simple **WhatsApp-like chat application** built using **Android (Java)** and **Firebase**, implementing **real-time messaging, group chats, and authentication** using the **MVVM architecture pattern**.

---

## 🚀 Features

### 🔐 Authentication
- Firebase Authentication integration  
- Uses **Anonymous Authentication** for quick testing  
- Easily extendable to **Email/Password or Phone Authentication**

### 💬 Real-Time Messaging
- Send and receive messages instantly
- Messages synced in real time using **Firebase Realtime Database**
- Smooth chat UI using `RecyclerView`

### 👥 Group Chats
- Create and view chat groups
- Join existing groups
- Real-time message updates inside groups

### 🧠 MVVM Architecture
- Clean separation of concerns using:
  - Model
  - View
  - ViewModel
  - Repository pattern

### ☁️ Firebase Backend
- Firebase Authentication
- Firebase Realtime Database
- Firebase Analytics (auto-enabled)

### 📱 Android UI
- XML-based layouts
- RecyclerView adapters for chats and groups
- Responsive design for different screen sizes

---

## 🛠️ Tech Stack

| Layer | Technology |
|------|-----------|
| Language | Java |
| IDE | Android Studio |
| Architecture | MVVM |
| Backend | Firebase |
| Auth | Firebase Authentication |
| Database | Firebase Realtime Database |
| UI | XML + RecyclerView |
| Build System | Gradle (Kotlin DSL) |

---

## 📁 Project Structure
```
WhatsappClone--master
│
├── app
│ ├── src/main/java/com/example/chatapp
│ │ ├── repository
│ │ │ └── Repository.java
│ │ ├── model
│ │ │ ├── ChatMessage.java
│ │ │ └── ChatGroup.java
│ │ ├── viewmodel
│ │ │ └── MyViewModel.java
│ │ ├── views
│ │ │ ├── LoginActivity.java
│ │ │ ├── ChatActivity.java
│ │ │ └── GroupsActivity.java
│ │ └── adapters
│ ├── ChatAdapter.java
│ └── GroupAdapter.java
│
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew
```


---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/sreedevigidugu/WhatsappClone-
cd WhatsappClone--master

```
2️⃣ Open in Android Studio

Open Android Studio

Click Open

Select the project folder

Wait for Gradle sync to complete

3️⃣ Firebase Setup

Go to Firebase Console

Create a new Firebase project

Add an Android app

Use this package name:com.example.chatapp
Download google-services.json

Place it inside:app/google-services.json

4️⃣ Enable Firebase Services
🔐 Authentication

Enable Anonymous Authentication

💾 Realtime Database

Create Realtime Database

Start in Test Mode

Set rules (for development only):{
  "rules": {
    ".read": true,
    ".write": true
  }
}

5️⃣ Run the App

Connect a physical device or start an emulator

Click ▶ Run in Android Studio

🧪 How to Use the App

Launch the app

User is authenticated anonymously

View or create chat groups

Open a group and start messaging

Messages sync in real time

🔮 Future Enhancements

Email / Phone authentication

One-to-one private chat

Image & media sharing

Push notifications (FCM)

Online/offline user presence

Typing indicators

Read receipts

⚠️ Notes

Firebase rules are open for development only

Do NOT use test rules in production

🤝 Contributing

Contributions are welcome.
Feel free to fork the repository and submit pull requests.
