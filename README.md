# Diba

**Diba** is a practice project focused on implementing [EventStoreDB](https://www.eventstore.com/) within a Java application. It serves as a sandbox to explore event sourcing patterns, domain-driven design (DDD), and microservices architecture using modern Java tools.

---

## 🧰 Tech Stack

- **Java 17**
- **Spring Boot**
- **EventStoreDB**
- **MongoDB**
- **Gradle**
- **Docker & Docker Compose**

---

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 17
- Docker & Docker Compose
- Gradle (or use the provided Gradle wrapper)

### Clone the Repository

```bash
git clone https://github.com/Al1r3z4asadi/diba.git
cd diba
```

### Build the Project

```bash
./gradlew build
```

### Run with Docker Compose

```bash
docker-compose up
```

This command will start both the application and the EventStoreDB instance.

---

## 📁 Project Structure

```plaintext
diba/
├── src/
│   └── main/
│       ├── java/           # Application source code
│       └── resources/      # Configuration files
├── build.gradle            # Build configuration
├── docker-compose.yml      # Docker Compose setup
├── Dockerfile              # Docker image definition
├── gradlew                 # Gradle wrapper script
└── settings.gradle         # Gradle settings
```

---

## 🧪 Features

- Integration with EventStoreDB for event sourcing
- Implementation of domain-driven design principles
- Microservices architecture exploration
- Dockerized setup for easy deployment

---

## 📚 Learning Objectives

- Understand and implement event sourcing with EventStoreDB
- Apply domain-driven design concepts in a practical project
- Explore microservices architecture using Spring Boot
- Gain experience with Docker and containerized applications

---

## 🤝 Contributing

This project is intended for personal learning and experimentation. However, contributions are welcome! Feel free to fork the repository and submit pull requests.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
