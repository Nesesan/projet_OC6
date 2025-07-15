# MDD – Monde de Dév

**Monde de Dév (MDD)** est un réseau social en cours de développement, destiné aux développeurs. Son objectif : **favoriser la mise en relation, la collaboration, et l'entraide entre pairs**, en mettant en avant les sujets tech partagés (JavaScript, Web3, etc...

---

## 🧱 Tech Stack

- **Frontend :** Angular 14
- **Backend :** Java 11 (Spring Boot)
- **Base de données :** PostgreSQL

---

## 🚀 Fonctionnalités 

- Abonnement à des sujets de programmation (JavaScript, Python, Web3…)
- Fil d’actualité chronologique affichant les articles liés aux sujets suivis
- Création d’articles
- Publication de commentaires
- Responsive design

---

## ⚙️ Installation et lancement

### 1. Cloner le dépôt

```bash
git clone git@github.com:Nesesan/projet_OC6.git
cd MDD
```

## Frontend (Angular)

```bash
cd frontend
npm install
npm run start
```
L’application sera accessible à : http://localhost:4200

## Backend (Java + Spring Boot)

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

## Base de données PostgreSQL

- Utilisateur : postgres

- Mot de passe : postgres (à adapter selon votre configuration)

- Vérifiez que votre serveur PostgreSQL est démarré et que la configuration de connexion dans application.properties est correcte.