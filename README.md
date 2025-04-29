# 💬 MobileChat - Java Chat App with Sockets

This repository contains a simple real-time chat application built using Java sockets. It includes both client and server implementations, allowing multiple users to exchange messages over a local network (LAN) or localhost.

The project was developed as part of a university assignment for the subject "Mobile Development" to demonstrate the use of socket programming and multithreading in Java.

---

## 🚀 Features

- ✅ Console-based server and client applications
- ✅ Multiple clients can connect simultaneously
- ✅ Real-time message broadcasting from server to all connected clients
- ✅ Use of threads to handle multiple client connections concurrently
- ✅ Clean shutdown of clients and server

---

## 📂 Project Structure

```
java-chat-app/
├── server/
│   └── ChatServer.java
├── client/
│   └── ChatClient.java
└── README.md
```

---

## 🧠 Topics Covered

- 🔹 Java socket programming (ServerSocket and Socket)
- 🔹 Input/Output streams (`BufferedReader`, `PrintWriter`)
- 🔹 Multithreading with `Thread` class
- 🔹 Handling multiple client connections
- 🔹 Broadcasting messages
- 🔹 Graceful shutdown and error handling

---

## 🛠️ Requirements

- Java 11 or higher
- Any IDE (e.g., IntelliJ, Eclipse) or command-line tools

---

## ▶️ How to Run

### Start the Server
```bash
cd server
javac ChatServer.java
java ChatServer
```

### Start a Client (in a new terminal)
```bash
cd client
javac ChatClient.java
java ChatClient
```

📌 You can open multiple client terminals to simulate multiple users chatting.

---

## 📸 Example Output

**Server:**
```
Server started on port 1234
New client connected: /127.0.0.1
Client said: Hello everyone!
```

**Client:**
```
Enter your name: Alice
[Server]: Welcome, Alice!
Alice: Hello everyone!
Bob: Hi Alice!
```