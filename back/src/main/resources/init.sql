CREATE SCHEMA IF NOT EXISTS `mdd`;

CREATE TABLE IF NOT EXISTS `mdd`.`_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email` (`email`));


CREATE TABLE `mdd`.`topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50),
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_name` (`title`)
);


CREATE TABLE `mdd`.`post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `description` text NOT NULL,
  `topic_id` int NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_user_fk` (`author_id`),
  KEY `post_topic_fk` (`topic_id`),
  CONSTRAINT `post_topic_fk` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  CONSTRAINT `post_user_fk` FOREIGN KEY (`author_id`) REFERENCES `_user` (`id`)
);

CREATE TABLE IF NOT EXISTS `mdd`.`comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `message` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_user_fk` (`user_id`),
  KEY `comment_post_fk` (`post_id`),
  CONSTRAINT `comment_post_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `comment_user_fk` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
);


CREATE TABLE `mdd`.`subscription` (
  `user_id` int NOT NULL,
  `topic_id` int NOT NULL,
  UNIQUE KEY `subscription_unique` (`user_id`,`topic_id`),
  KEY `subscription_topic_fk` (`topic_id`),
  CONSTRAINT `subscription_topic_fk` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  CONSTRAINT `subscription_user_FK` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
);



INSERT INTO `mdd`.`_user` (`email`, `username`, `password`, `created_at`)
VALUE('John@doe.fr', 'JohnDoe', '$2a$10$uwuPzbIOo910f9fkJwKmiekevUGuP0CpDQcpptMhs6LxKG/yMVfn.', '2024-06-28 12:00:00');


INSERT INTO `mdd`.`topic` (`title`, `description`)
VALUES('Java', 'Java est un langage de programmation de haut niveau orienté objet créé par James Gosling et Patrick Naughton, employés de Sun Microsystems, avec le soutien de Bill Joy (cofondateur de Sun Microsystems en 1982), présenté officiellement le 23 mai 1995 au SunWorld. La société Sun est rachetée en 2009 par la société Oracle qui détient et maintient désormais Java. Un logiciel écrit en langage Java a pour particularité d\'être compilé vers un code intermédiaire formé de bytecodes qui peut être exécuté dans une machine virtuelle Java (JVM) en faisant abstraction du système d\'exploitation.'),
('Angular', 'Angular (communément appelé « Angular 2+ » ou « Angular v2 et plus »)2,3 est un framework pour clients, open source, basé sur TypeScript et codirigé par l\'équipe du projet « Angular » chez Google ainsi que par une communauté de particuliers et de sociétés. Angular est une réécriture complète d\'AngularJS, cadriciel construit par la même équipe. Il permet la création d’applications Web et plus particulièrement d\'applications Web monopages : des applications Web accessibles via une page Web unique qui permet de fluidifier l’expérience utilisateur et d’éviter les chargements de pages à chaque nouvelle action. Le framework est basé sur une architecture du type MVC et permet donc de séparer les données, le visuel et les actions pour une meilleure gestion des responsabilités. Un type d’architecture qui a largement fait ses preuves et qui permet une forte maintenabilité et une amélioration du travail collaboratif.'),
('Microservice', 'En informatique, les microservices sont des services logiciels. L\'architecture en microservices est une variante du style architectural de l\'architecture orientée services (SOA) qui structure une application comme un ensemble de services faiblement couplés. Les microservices indépendants communiquent les uns avec les autres en utilisant des API indépendantes du langage de programmation. Des API REST sont souvent employées pour relier chaque microservice aux autres. Un avantage avancé est que lors d\'un besoin critique de mise à jour d\'une ressource, seul le microservice contenant cette ressource sera mis à jour, l\'ensemble de l\'application restant compatible avec la modification, contrairement à la totalité de l\'application dans une architecture classique, par exemple une architecture trois tiers. Cependant, le coût de mise en place, en raison des compétences requises, est parfois plus élevé.');
