# 🛒 AeroCart – AI Powered E-Commerce Platform

A modern, scalable, and cloud-native E-Commerce platform built using **Spring Boot Microservices**, **Spring Security (JWT + OAuth2)**, **Spring AI**, **React.js**, **Docker**, and **AWS Cloud Services**.

---

## 🚀 Features

### ✅ Authentication & Security
- JWT Authentication
- OAuth2 Login (Google/GitHub)
- Role-Based Authorization (Admin/User)
- Secure REST APIs with Spring Security

### ✅ AI Features
- AI-powered Product Recommendations
- Semantic Search using Vector Database
- RAG (Retrieval-Augmented Generation)
- Spring AI + Ollama/OpenAI Integration
- Chat Assistant for customer support

### ✅ E-Commerce Features
- Product Catalog
- Category Management
- Shopping Cart
- Wishlist
- Order Management
- Payment Integration
- Inventory Management
- User Profile Management

### ✅ Cloud & DevOps
- Dockerized Services
- AWS ECS / ECR Deployment
- AWS RDS PostgreSQL
- CI/CD Ready
- API Gateway Support
- Config Server Support
- Service Discovery

### ✅ Microservices Architecture
- Independent Services
- Scalable Deployment
- Centralized Configuration
- Fault Tolerance Ready

---

# 🏗️ Tech Stack

## Backend
- Java 21+
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Cloud
- Spring AI
- Hibernate
- PostgreSQL
- Redis
- PGVector / Vector Store

## Frontend
- React.js
- Redux Toolkit
- Axios
- Tailwind CSS / Material UI

## AI & LLM
- Spring AI
- Ollama
- TinyLlama / Llama3
- OpenAI Compatible APIs

## DevOps & Cloud
- Docker
- Docker Compose
- AWS ECS
- AWS ECR
- AWS RDS
- GitHub Actions

---

# 📁 Professional Project Structure

```bash
AeroCart/
│
├── backend/
│   │
│   ├── api-gateway/
│   ├── config-server/
│   ├── discovery-server/
│   │
│   ├── auth-service/
│   ├── user-service/
│   ├── product-service/
│   ├── cart-service/
│   ├── order-service/
│   ├── payment-service/
│   ├── inventory-service/
│   ├── ai-service/
│   │
│   ├── common-library/
│   └── docker-compose.yml
│
├── frontend/
│   │
│   ├── public/
│   ├── src/
│   │   ├── api/
│   │   ├── components/
│   │   ├── features/
│   │   ├── hooks/
│   │   ├── layouts/
│   │   ├── pages/
│   │   ├── redux/
│   │   ├── routes/
│   │   ├── services/
│   │   ├── utils/
│   │   └── App.jsx
│   │
│   └── package.json
│
├── docs/
├── screenshots/
├── scripts/
├── .github/
│   └── workflows/
│
├── README.md
├── .gitignore
├── .gitattributes
└── LICENSE