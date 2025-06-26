# 🎒 Backpack Life Sim

A Kotlin Multiplatform demo that explores life through the lens of a virtual backpack.  
Each day, users are given a random set of items and must choose to **Keep**, **Use**, or **Toss** them — with each decision shaping their simulated life path.

Built with:
- ✅ Kotlin Multiplatform (KMM)
- 🌐 Ktor Server (JVM)
- 📱 Android + iOS Support
- 🧠 Shared business logic
- ⭮️ Clean Architecture principles

---

## 🧪 What is This?

This project is a demonstration of:
- How to structure a **KMM project with a backend (server)** component.
- Using **expect/actual** for shared UUID and platform-specific logic.
- Implementing **Ktor** as both the client and the server.
- Applying **Clean Code** and **Domain-Driven Design** in a KMP app.

---

## 🧱 Project Structure

```
Backpack-Life-Sim/
│
├── shared/                 # Shared KMM module (business logic, models, API)
│   ├── commonMain/
│   │   ├── model/          # Data classes (Item, Backpack, Choice, LifePath)
│   │   ├── network/        # Ktor client logic
│   │   └── utils/          # expect/actual UUID, date utils
│   └── ...
│
├── androidApp/             # Android app UI (Jetpack Compose)
├── iosApp/                 # iOS SwiftUI app (optional future target)
│
└── ktor-backend/           # Ktor server (serves backpack + tracks life events)
    ├── Application.kt
    └── routes/
```

---

## 📦 Features

- 🎲 Daily randomized backpack
- ✂️ Simple item decision mechanic (Keep, Use, Toss)
- 🧠 Life path evolves based on player choices
- 🧪 Shared testable business logic
- ⭮️ Fully functioning backend to simulate realistic KMM scenarios

---

## 🚀 Running the Project

### 🔧 Prerequisites

- [JDK 17+](https://adoptium.net)
- [Android Studio Giraffe+](https://developer.android.com/studio)
- Kotlin Multiplatform plugin
- [Ktor CLI](https://ktor.io/docs/cli-install.html) (optional)

---

### 🖥️ Backend (Ktor)

```bash
cd ktor-backend
./gradlew run
```

The server will start on `http://localhost:8080`

Endpoints:
- `GET /backpack/today` - Get randomized backpack items
- `POST /backpack/choose` - Submit choices for today's items
- `GET /life-path` - View life path built from choices

---

## 📊 Roadmap

- [ ] Add persistence (store events + history)
- [ ] Add user profiles
- [ ] Hook iOS frontend (SwiftUI)
- [ ] Add animation or effects in Android UI

---

## 🌐 License

MIT License — free to use, modify, and build upon.

---

## 📱 Screenshots
![Screenshot](https://github.com/user-attachments/assets/e7848013-ca6d-4122-a20a-aec0852acef1)

> Want to contribute? Feel free to open an issue or submit a PR!

Made with ❤️ by Mark
