INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (1, 'furniture');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (2, 'clothes');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (3, 'sports');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (4, 'electronics');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (5, 'hobbies');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (6, 'travel');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (7, 'books');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (8, 'accesoirs');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (9, 'toys and games');
INSERT INTO `auktionera`.`categories` (`id`, `category_title`) VALUES (10, 'shoes');

INSERT INTO `auktionera`.`roles` (`id`, `role`) VALUES (1, 'ADMIN');
INSERT INTO `auktionera`.`roles` (`id`, `role`) VALUES (2, 'USER');
INSERT INTO accounts (id, anonymous_buyer, city, email, password, post_nr, street_name, user_name) VALUES (1,false, 'Göteborg', 'admin@admin.se', 'admin', 41010, 'Skånegatan 3', 'admin');
INSERT INTO accounts_roles (account_entities_id, roles_id) VALUES (1,1);