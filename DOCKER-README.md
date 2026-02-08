# 🐳 Nettside CRM - Docker Infrastructure

Docker Compose configuration for Nettside CRM local development environment.

## 📋 Services

| Service | Port | Description | Management UI |
|---------|------|-------------|---------------|
| **PostgreSQL** | 5432 | Main transactional database | PgAdmin: http://localhost:5050 |
| **PostgreSQL Test** | 5433 | Test database for E2E tests | PgAdmin: http://localhost:5050 |
| **MongoDB** | 27017 | Chat and operational data | Mongo Express: http://localhost:8081 |
| **Redis** | 6379 | Cache and session storage | Redis Commander: http://localhost:8082 |

## 🚀 Quick Start

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

## 🔐 Default Credentials

### PostgreSQL (Development)
- Host: localhost:5432
- Database: nettside_crm
- Username: postgres
- Password: postgres

### PostgreSQL (Test)
- Host: localhost:5433
- Database: nettside_crm_test
- Username: test
- Password: test123

### MongoDB
- Host: localhost:27017
- Database: nettside_chat
- No authentication

### Redis
- Host: localhost:6379
- Password: redis123

### Management UIs
- **PgAdmin**: http://localhost:5050 (admin@nettside-crm.com / admin123)
- **Mongo Express**: http://localhost:8081 (admin / admin123)
- **Redis Commander**: http://localhost:8082

## ⚠️ Important

This configuration is for **LOCAL DEVELOPMENT ONLY**. Do not use in production.