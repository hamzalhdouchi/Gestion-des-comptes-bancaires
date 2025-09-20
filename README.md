#  Gestion des Comptes Bancaires — Application Console (Java 8)

##  Introduction
La gestion des comptes bancaires est un processus complexe qui nécessite rigueur et sécurité.  
Ce projet a été conçu dans un cadre pédagogique afin de simuler le fonctionnement d’un système bancaire simplifié.  
Il met en œuvre les concepts fondamentaux de la **programmation orientée objet (POO)** en Java, tout en intégrant des notions avancées comme l’héritage, l’abstraction, la gestion des exceptions, et la manipulation des collections.

---

##  Objectif
L’objectif principal de ce projet est de développer une **application console en Java 8** permettant :  
- La **création et gestion de comptes bancaires** (courants et épargnes).  
- La **réalisation d’opérations financières** : versements, retraits et virements.  
- La **consultation des soldes et historiques d’opérations**.  
- Le **respect des règles bancaires** (plafond du découvert, calcul d’intérêts, etc.).  

---

##  Problématique
Comment concevoir une application Java simple, claire et évolutive qui permette de :  
1. Gérer différents types de comptes avec des règles spécifiques.  
2. Assurer la traçabilité et la sécurité des opérations.  
3. Respecter les bonnes pratiques de la POO et de l’architecture logicielle.  
4. Offrir une interface console intuitive pour l’utilisateur final.  

---

##  Technologies utilisées
- **Langage :** Java 8  
- **Paradigme :** Programmation Orientée Objet (POO)  
- **Collections Java :** `ArrayList`  
- **Gestion des dates :** `Java Time API (LocalDateTime)`  
- **Identifiants uniques :** `UUID`  
- **Gestion d’exceptions** pour la robustesse  
- **Streams & Optional** (bonus, amélioration du code)  
- **JDBC/MySQL** (optionnel, pour la persistance des données)  

---

##  Structure du projet

brif-1/
├─ .idea/
├─ out/
├─ src/
│ ├─ Dbconnection.java
│ ├─ entity/
│ │ ├─ Compte.java # Classe abstraite
│ │ ├─ Operation.java # Classe abstraite
│ │ ├─ Versement.java
│ │ ├─ Retrait.java
│ ├─ repository/
│ │ ├─ CompteCourantRepo.java
│ │ ├─ CompteEpargneRepo.java
│ │ ├─ RetraitRepo.java
│ │ ├─ VersementRepo.java
│ ├─ util/
│ │ ├─ Helper.java
│ ├─ Main.java
├─ .gitignore


---

##  demonstration

<img width="1337" height="858" alt="image" src="https://github.com/user-attachments/assets/75da0ccf-1c13-4c51-b4d9-12ce8dbd0e32" />
<img width="1460" height="875" alt="image" src="https://github.com/user-attachments/assets/c43dcffe-3b4b-457a-aae3-01fb4c65c984" />
<img width="1472" height="897" alt="image" src="https://github.com/user-attachments/assets/6ac4c1d6-0888-4f6b-8175-f0a2ff64720c" />




##  Conclusion
Ce projet illustre la manière dont la **programmation orientée objet** et les concepts de conception logicielle peuvent être appliqués à un **cas concret de gestion bancaire**.  
Il offre une base solide qui peut être enrichie par :  
- L’intégration d’une base de données pour la persistance.  
- Une interface graphique (JavaFX/Swing).  
- Des tests unitaires pour valider les règles métier.  

Ainsi, cette application constitue un excellent exercice pratique pour renforcer ses compétences en **Java**, en **POO**, et en **architecture logicielle**.

---
