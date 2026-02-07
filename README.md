# Nettside CRM API

<div align="center">
  
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=for-the-badge&logo=spring-boot)
  ![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
  ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16+-blue?style=for-the-badge&logo=postgresql)
  ![MongoDB](https://img.shields.io/badge/MongoDB-7+-green?style=for-the-badge&logo=mongodb)
  ![Redis](https://img.shields.io/badge/Redis-7+-red?style=for-the-badge&logo=redis)
  ![License](https://img.shields.io/badge/License-Proprietary-yellow?style=for-the-badge)

  **A complete RESTful Customer Relationship Management (CRM) API developed with Spring Boot**
  
  Designed to optimize commercial management, enhance customer experience, and boost your organization's sales.

</div>

---

## 📋 Table of Contents

- [About the Project](#-about-the-project)
- [Key Features](#-key-features)
- [Architecture](#-architecture)
- [Technology Stack](#-technology-stack)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Project Structure](#-project-structure)
- [Development Guidelines](#-development-guidelines)
- [Roadmap](#-roadmap)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

---

## 🚀 About the Project

**Nettside CRM API** is an enterprise-grade Customer Relationship Management system built with modern software architecture principles. It implements **Clean Architecture** combined with **Domain-Driven Design (DDD)** to ensure maintainability, scalability, and testability.

### Why Nettside CRM?

- ✅ **Clean Architecture**: Complete separation of concerns with framework-independent business logic
- ✅ **Domain-Driven Design**: Rich domain models with tactical and strategic patterns
- ✅ **Polyglot Persistence**: PostgreSQL for transactional data, MongoDB for operational data, Redis for caching
- ✅ **Complete Audit Trail**: Every user action is tracked and logged
- ✅ **Multi-Tenancy Ready**: Built-in company isolation for SaaS deployments
- ✅ **Real-Time Chat**: WebSocket-based messaging system with MongoDB persistence
- ✅ **RESTful API**: Well-documented endpoints with OpenAPI/Swagger
- ✅ **Production Ready**: Comprehensive security, monitoring, and optimization

---

## 🎯 Key Features

### Lead Management
- Multi-source lead capture (website, referrals, social media)
- Lead qualification and scoring
- Intelligent assignment to sales representatives
- Lead-to-Contact conversion workflow

### Contact & Customer Management
- Comprehensive contact profiles
- Multiple emails, phones, and addresses per contact
- Interaction history tracking
- Customer credit management

### Opportunity Pipeline
- Configurable sales stages
- Probability-based forecasting
- Expected and actual close date tracking
- Won/Lost opportunity analysis

### Activity Management
- Tasks, calls, emails, and meetings
- Automated reminders
- Calendar integration
- Activity linking to leads, customers, and opportunities

### Real-Time Chat
- Instant messaging between team members
- Chat rooms linked to CRM entities (Leads, Opportunities)
- Message history with automatic retention policies
- Real-time notifications via WebSocket

### Comprehensive Audit System
- Complete user action tracking
- Before/after state capture
- IP and user-agent logging
- Configurable retention policies

---

## 🏗 Architecture

Nettside CRM follows **Clean Architecture** principles with four distinct layers:

```
┌─────────────────────────────────────────┐
│         PRESENTATION LAYER              │
│    (REST Controllers, WebSocket)        │
└──────────────────┬──────────────────────┘
                   │ Depends on ↓
┌──────────────────┴──────────────────────┐
│         APPLICATION LAYER               │
│      (Use Cases, DTOs, Mappers)         │
└──────────────────┬──────────────────────┘
                   │ Depends on ↓
┌──────────────────┴──────────────────────┐
│           DOMAIN LAYER                  │
│  (Business Logic - Framework Free)      │
└──────────────────┬──────────────────────┘
                   ↑ Implements
┌──────────────────┴──────────────────────┐
│       INFRASTRUCTURE LAYER              │
│  (JPA, MongoDB, Redis, External APIs)   │
└─────────────────────────────────────────┘
```

### Design Principles

- **SOLID Principles**: Applied throughout the codebase
- **Dependency Inversion**: Domain layer has no framework dependencies
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Bounded Contexts**: Independent modules with clear boundaries

---

## 💻 Technology Stack

### Backend
- **Framework**: Spring Boot 4.0.2
- **Language**: Java 25
- **Build Tool**: Gradle with Groovy DSL
- **ORM**: Hibernate/JPA

### Databases
- **Transactional**: PostgreSQL 16+ (Users, Leads, Customers, Opportunities)
- **Operational**: MongoDB 7+ (Chat system with TTL)
- **Cache**: Redis 7+ (Sessions, rate limiting)

### Key Libraries
- **Security**: Spring Security + JWT (JJWT 0.12.5)
- **Validation**: Jakarta Bean Validation
- **Mapping**: MapStruct 1.5.5
- **Migrations**: Flyway
- **Documentation**: SpringDoc OpenAPI 2.3.0
- **Messaging**: Spring WebSocket
- **Monitoring**: Spring Boot Actuator

### Testing
- **Unit Testing**: JUnit 5, Mockito
- **Integration Testing**: Testcontainers (PostgreSQL, MongoDB)
- **API Testing**: REST Assured

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 25
- **Gradle**: Version 8.5+ (or use Gradle Wrapper)
- **PostgreSQL**: Version 16+
- **MongoDB**: Version 7+
- **Redis**: Version 7+
- **Git**: For version control
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (recommended: IntelliJ IDEA)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/alexcoronell/nettside-crm.git
cd nettside-crm
```

### 2. Configure Databases

**PostgreSQL:**
```bash
# Create database
createdb nettside_crm

# Default connection (can be changed in application.yml):
# URL: jdbc:postgresql://localhost:5432/nettside_crm
# Username: postgres
# Password: postgres
```

**MongoDB:**
```bash
# MongoDB will auto-create the database on first connection
# Default connection: mongodb://localhost:27017/nettside_chat
```

**Redis:**
```bash
# Start Redis server
redis-server

# Default connection: localhost:6379
```

### 3. Configure Application Properties

Copy `application-example.yml` to `application.yml` and update with your settings:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/nettside_crm
    username: your_username
    password: your_password
  
  data:
    mongodb:
      uri: mongodb://localhost:27017/nettside_chat
    
    redis:
      host: localhost
      port: 6379

jwt:
  secret: your-super-secret-key-min-512-bits-change-in-production
```

### 4. Build the Project

```bash
# Using Gradle Wrapper
./gradlew clean build

# Or if you have Gradle installed globally
gradle clean build
```

### 5. Run Database Migrations

Flyway migrations run automatically on application startup. To run manually:

```bash
./gradlew flywayMigrate
```

### 6. Run the Application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

---

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **Health Check**: http://localhost:8080/actuator/health

### Sample API Endpoints

```
Authentication:
  POST   /api/v1/auth/login
  POST   /api/v1/auth/register
  POST   /api/v1/auth/refresh

Leads:
  GET    /api/v1/leads
  POST   /api/v1/leads
  GET    /api/v1/leads/{id}
  PUT    /api/v1/leads/{id}
  DELETE /api/v1/leads/{id}
  POST   /api/v1/leads/{id}/convert

Contacts:
  GET    /api/v1/contacts
  POST   /api/v1/contacts
  GET    /api/v1/contacts/{id}
  PUT    /api/v1/contacts/{id}

Customers:
  GET    /api/v1/customers
  POST   /api/v1/customers
  GET    /api/v1/customers/{id}
  PUT    /api/v1/customers/{id}

Opportunities:
  GET    /api/v1/opportunities
  POST   /api/v1/opportunities
  GET    /api/v1/opportunities/{id}
  PUT    /api/v1/opportunities/{id}
  PATCH  /api/v1/opportunities/{id}/stage

Activities:
  GET    /api/v1/activities
  POST   /api/v1/activities
  GET    /api/v1/activities/{id}
  PUT    /api/v1/activities/{id}
  PATCH  /api/v1/activities/{id}/complete

Chat:
  GET    /api/v1/chat/rooms
  POST   /api/v1/chat/rooms
  GET    /api/v1/chat/rooms/{roomId}/messages
  WS     /ws/chat (WebSocket endpoint)

Audit:
  GET    /api/v1/audit/logs
  GET    /api/v1/audit/entity/{type}/{id}
  GET    /api/v1/audit/user/{userId}
```

---

## 🗄 Database Schema

### PostgreSQL Schema

The system uses PostgreSQL for all transactional data:

- **Core Module**: Companies, Users, Roles, Permissions
- **Lead Module**: Leads, Lead Sources, Lead Activities
- **Contact Module**: Contacts, Contact Emails, Contact Phones
- **Customer Module**: Customers, Customer Addresses
- **Opportunity Module**: Opportunities, Opportunity Stages
- **Activity Module**: Activities, Activity Types
- **Audit Module**: Audit Logs (comprehensive tracking)

Full schema documentation available in [ARCHITECTURE.md](docs/ARCHITECTURE.md)

### MongoDB Collections

MongoDB is used exclusively for the chat system:

- **chat_messages**: Message storage with 90-day TTL
- **chat_rooms**: Room metadata and participants
- **chat_audit**: Chat action logs with 90-day TTL

---

## 📂 Project Structure

```
com.nettside.crm/
├── domain/                     # Business logic (framework-free)
│   ├── common/                 # Shared domain elements
│   ├── core/                   # User, Company, Role management
│   ├── lead/                   # Lead management
│   ├── contact/                # Contact management
│   ├── customer/               # Customer management
│   ├── activity/               # Activity management
│   ├── opportunity/            # Opportunity management
│   └── chat/                   # Chat system
│
├── application/                # Use cases and orchestration
│   ├── common/                 # Shared DTOs, mappers
│   └── [module]/               # Module-specific use cases
│
├── infrastructure/             # Technical implementation
│   ├── common/                 # Config, security, AOP
│   └── [module]/               # JPA/MongoDB repositories
│
└── presentation/               # API layer
    ├── rest/v1/                # REST controllers
    └── websocket/              # WebSocket handlers
```

---

## 👨‍💻 Development Guidelines

### Code Style

- **Language**: Java 25 with modern features
- **Comments**: English (professional level)
- **Communication**: Spanish/English (team discussions)
- **Naming**: camelCase for Java, snake_case for database

### Commit Messages

We follow conventional commits with professional style (in English):

```
feat: add lead qualification scoring system
fix: resolve null pointer in customer credit calculation
docs: update API documentation for opportunity endpoints
refactor: improve performance of audit log queries
test: add integration tests for chat message persistence
chore: upgrade Spring Boot to version 4.0.2
```

### Git Workflow

1. Create feature branch from `main`
2. Make changes with conventional commits
3. Write/update tests
4. Submit pull request
5. Code review required before merge

### Testing Requirements

- **Unit Tests**: Minimum 80% coverage for domain layer
- **Integration Tests**: All use cases must have integration tests
- **API Tests**: All endpoints must have REST Assured tests

---

## 🗺 Roadmap

### Phase 1: Foundation (Current)
- [x] Project setup with Spring Initializr
- [ ] Clean Architecture implementation
- [ ] PostgreSQL schema design
- [ ] MongoDB chat integration
- [ ] Core module implementation
- [ ] Authentication & authorization

### Phase 2: Core Modules (Q1 2026)
- [ ] Lead management module
- [ ] Contact management module
- [ ] Customer management module
- [ ] Complete audit system

### Phase 3: Advanced Features (Q2 2026)
- [ ] Opportunity pipeline
- [ ] Activity management
- [ ] Real-time chat system
- [ ] Email integration

### Phase 4: Analytics & Reporting (Q3 2026)
- [ ] Dashboard and metrics
- [ ] Custom reports
- [ ] Export capabilities
- [ ] Analytics API

### Phase 5: Integrations (Q4 2026)
- [ ] Third-party CRM imports
- [ ] Email service providers
- [ ] Calendar integrations
- [ ] Webhook system

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feat/amazing-feature`)
3. Commit your changes using conventional commits
4. Push to the branch (`git push origin feat/amazing-feature`)
5. Open a Pull Request

### Development Setup

```bash
# Clone your fork
git clone https://github.com/your-username/nettside-crm.git

# Add upstream remote
git remote add upstream https://github.com/original-repo/nettside-crm.git

# Create feature branch
git checkout -b feat/your-feature

# Make changes and commit
git add .
git commit -m "feat: add your feature description"

# Push to your fork
git push origin feat/your-feature
```

---

## 📄 License

This project is proprietary software. All rights reserved.

For licensing inquiries, please contact: [contactl@alexcoronell.dev]

---

## 📞 Contact

**Project Lead**: Alex Coronell

- Email: [contact@alexcoronell.dev]
- LinkedIn: [linkedin.com/in/alex-coronell]
- GitHub: [@alexcoronell]

**Project Repository**: [https://github.com/alexcoronell/nettside-crm](https://github.com/alexcoronell/nettside-crm)

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Clean Architecture community
- Domain-Driven Design practitioners
- All contributors to this project

---

<div align="center">
  
  **Made with ❤️ by Alex Coronell**
  
  ⭐ Star this repository if you find it helpful!

</div>
