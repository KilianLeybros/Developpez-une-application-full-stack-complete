# P6-Full-Stack-reseau-dev

# Developpez-une-application-full-stack-complete_Kilian-Leybros

# Cloner le projet

Git clone : https://github.com/KilianLeybros/Developpez-une-application-full-stack-complete.git

## Back

Le projet est réalisé sous java 17 et utilise maven, le jdk 17 et maven sont nécessaires pour lancer le projet.

Se rendre à la racine du projet backend (/back) et installer les dépendances à l'aide de maven :

> `mvn clean install`

### Test

Des tests ont été réalisés dans la couche Authentification

Pour lancer les tests :

> `mvn clean verify`

Une fois les tests terminés, le rapport de couverture Jacoco sera disponible ici :

> `back/target/site/jacoco/index.html`

### Démarrer le projet back

Si vous avez besoin de démarrer le back, il vous faudra installer MySQL server version 8 ainsi qu'un client MySQL (MySQL Workbench, phpMyAdmin),
lien de téléchargement : https://dev.mysql.com/downloads/installer/

Une fois MySQL installé :

- Assurez-vous que MySQL est bien lancé sur le port 3306.

- Créez le schéma grace au script se trouvant dans le dossier à la racine du projet

> `ressources/init.sql`

- L'application utilise l'utilisateur root, password: root pour se connecter à la base de donnée, si besoin vous pouvez changer cet utilisateur dans le fichier application.properties.
- Assurez-vous que l'utilisateur a les priviléges suivants: DELETE,EXECUTE,INSERT,SELECT,SHOW VIEW, UPDATE

Le script SQL ajoute un utilisateur par défaut dans l'application :

- login: John@doe.fr
- password: azertyAZERTY123$

Vous pouvez désormais lancer le projet en exécutant la commande suivante :

> `mvn spring-boot:run`

### Documentation swagger 

Une documentation swagger est disponible à cette url: http://localhost:8080/swagger-ui/index.html#/
L'authentification est gérée grâce à un Cookie, veuillez utiliser le endpoint auth/login ou auth/register pour vous connecter et auth/logout pour vous déconnecter.

## Front

Ce project a été générer avec [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3 et mis à jour en version 17, il est conseillé d'avoir cet version ou une version supérieur.

Se rendre à la racine du projet frontend (/front) et installer les dépendances grâce à npm :

> `npm install`

### Démarrer le projet front

> `npm run start` => http://localhost:4200/




