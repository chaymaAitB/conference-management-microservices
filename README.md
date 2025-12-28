# Rapport du Projet Conference App

## 1. Architecture Technique

Le projet est basé sur une architecture micro-services utilisant l'écosystème Spring Cloud et Angular pour le frontend.

**Composants du système :**

*   **Discovery Service (Eureka)** : Permet l'enregistrement et la découverte dynamique des micro-services.
*   **Config Service** : Centralise la configuration de tous les micro-services (stockée ici localement dans le classpath, mais configurable via Git).
*   **Gateway Service (Spring Cloud Gateway)** : Point d'entrée unique de l'application (Port 8080). Gère le routing et la sécurité.
*   **Keynote Service** : Micro-service métier gérant les conférenciers (Keynotes). Utilise H2 comme base de données.
*   **Conference Service** : Micro-service métier gérant les conférences et les reviews. Utilise H2 et communique avec Keynote Service via OpenFeign.
*   **Frontend App (Angular)** : Interface utilisateur pour gérer les conférences et keynotes.
*   **Sécurité (Keycloak)** : Assure l'authentification et l'autorisation via OIDC/OAuth2.

## 2. Structure du Projet

Le projet est structuré via un `pom.xml` parent Maven et des sous-modules :
*   `discovery-service`
*   `config-service`
*   `gateway-service`
*   `keynote-service`
*   `conference-service`
*   `frontend-app` (Projet Angular)

## 3. Détails d'Implémentation

### Keynote Service
*   **Entités** : `Keynote` (id, nom, prenom, email, fonction).
*   **API** : RESTful pour CRUD.
*   **Base de données** : H2 en mémoire.

### Conference Service
*   **Entités** : `Conference` (avec relation Keynote via ID) et `Review`.
*   **API** : RESTful pour CRUD Conférences et ajout Reviews.
*   **OpenFeign** : Client `KeynoteRestClient` pour récupérer les infos du Keynote associé.
*   **Resilience4J** : Circuit Breaker implémenté sur le client Feign pour gérer la tolérance aux pannes du service Keynote.

### Gateway & Sécurité
*   Routing configuré via le Config Server.
*   Sécurité OAuth2 Resource Server validant les JWT émis par Keycloak.

### Frontend
*   Application Angular 17+ (Standalone Components).
*   Utilise `angular-oauth2-oidc` pour l'authentification Keycloak.
*   Interface responsive avec TailwindCSS (utilisé via classes utilitaires simples dans l'exemple ou CSS standard). Note: Tailwind n'a pas été configuré explicitement dans le build, les classes sont indicatives pour le style.

## 4. Déploiement (Docker)

Un fichier `docker-compose.yml` est fourni pour orchestrer le démarrage de tous les services ainsi que Keycloak.

**Ordre de démarrage recommandé :**
1.  Discovery Service
2.  Config Service
3.  Keycloak (Prendre le temps de configurer le Realm `conference-realm` et le client `conference-gateway` / `conference-frontend`)
4.  Micros-services métier (Keynote, Conference)
5.  Gateway

## 5. Instructions de Lancement

1.  **Compiler le backend** : `mvn clean package -DskipTests` à la racine.
2.  **Lancer Docker Compose** : `docker-compose up --build -d`.
3.  **Lancer le Frontend** : `cd frontend-app && npm start`.
4.  **Accéder à l'application** : `http://localhost:4200`.
