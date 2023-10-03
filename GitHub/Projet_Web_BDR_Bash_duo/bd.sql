-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 31 mars 2023 à 14:39
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `sae`
--

-- --------------------------------------------------------

--
-- Structure de la table `assos_fam`
--

DROP TABLE IF EXISTS `assos_fam`;
CREATE TABLE IF NOT EXISTS `assos_fam` (
  `id_p` smallint NOT NULL,
  `nom_famille` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_p`,`nom_famille`),
  KEY `nom_famille` (`nom_famille`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `assos_fam`
--

INSERT INTO `assos_fam` (`id_p`, `nom_famille`) VALUES
(2, 'Animaux'),
(2, 'Chevaux'),
(3, 'Animaux'),
(4, 'Grand Huit'),
(5, 'Montagnes russe'),
(6, 'Grand Huit'),
(6, 'Montagnes russe'),
(19, 'Animaux'),
(19, 'Chevaux'),
(20, 'Grand Huit'),
(20, 'Montagnes russe'),
(21, 'Montagnes russe'),
(22, 'Grand Huit');

-- --------------------------------------------------------

--
-- Structure de la table `atelier`
--

DROP TABLE IF EXISTS `atelier`;
CREATE TABLE IF NOT EXISTS `atelier` (
  `id_atelier` smallint NOT NULL,
  `nom_z` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_atelier`),
  UNIQUE KEY `nom_z` (`nom_z`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `atelier`
--

INSERT INTO `atelier` (`id_atelier`, `nom_z`) VALUES
(1, 'Aquatique'),
(2, 'Enfants'),
(3, 'Sensation Fortes');

-- --------------------------------------------------------

--
-- Structure de la table `boutique`
--

DROP TABLE IF EXISTS `boutique`;
CREATE TABLE IF NOT EXISTS `boutique` (
  `id_b` smallint NOT NULL,
  `nom_b` varchar(50) CHARACTER SET latin1 NOT NULL,
  `nom_emplacement` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_b`),
  UNIQUE KEY `nom_emplacement` (`nom_emplacement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `boutique`
--

INSERT INTO `boutique` (`id_b`, `nom_b`, `nom_emplacement`) VALUES
(1, 'La Cabane du Chef Alfreddo', '1'),
(2, 'Chez Barbe Rousse', '4'),
(3, 'Les merveilles de Joe', '2'),
(4, 'Le Débarra Magique', '3');

-- --------------------------------------------------------

--
-- Structure de la table `b_souvenir`
--

DROP TABLE IF EXISTS `b_souvenir`;
CREATE TABLE IF NOT EXISTS `b_souvenir` (
  `id_b` smallint NOT NULL,
  PRIMARY KEY (`id_b`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `b_souvenir`
--

INSERT INTO `b_souvenir` (`id_b`) VALUES
(2),
(3),
(4);

-- --------------------------------------------------------

--
-- Structure de la table `chiffrea`
--

DROP TABLE IF EXISTS `chiffrea`;
CREATE TABLE IF NOT EXISTS `chiffrea` (
  `id_b` smallint NOT NULL,
  `Id_chiffreA` smallint NOT NULL,
  `date_ca` date DEFAULT NULL,
  `CA` decimal(10,2) DEFAULT NULL,
  `nbr_cli` int DEFAULT NULL,
  PRIMARY KEY (`id_b`,`Id_chiffreA`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `chiffrea`
--

INSERT INTO `chiffrea` (`id_b`, `Id_chiffreA`, `date_ca`, `CA`, `nbr_cli`) VALUES
(1, 1, '2023-01-12', '300.00', 300),
(1, 2, '2023-01-13', '250.00', 150),
(1, 3, '2023-01-14', '280.00', 200),
(2, 4, '2023-01-12', '320.00', 280),
(2, 5, '2023-01-13', '200.00', 120),
(2, 6, '2023-01-14', '260.00', 300),
(3, 7, '2023-01-12', '275.00', 310),
(3, 8, '2023-01-13', '190.00', 135),
(3, 9, '2023-01-14', '249.00', 284),
(4, 10, '2023-01-12', '400.00', 294),
(4, 11, '2023-01-13', '240.00', 197),
(4, 12, '2023-01-14', '238.00', 201);

-- --------------------------------------------------------

--
-- Structure de la table `cm`
--

DROP TABLE IF EXISTS `cm`;
CREATE TABLE IF NOT EXISTS `cm` (
  `id_p` smallint NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `cm`
--

INSERT INTO `cm` (`id_p`) VALUES
(2),
(3),
(4),
(5),
(6),
(19),
(20),
(21),
(22);

-- --------------------------------------------------------

--
-- Structure de la table `date_m`
--

DROP TABLE IF EXISTS `date_m`;
CREATE TABLE IF NOT EXISTS `date_m` (
  `id_p` smallint NOT NULL,
  `id_m` smallint NOT NULL,
  `date_ouv` date NOT NULL,
  PRIMARY KEY (`id_p`,`id_m`,`date_ouv`),
  KEY `id_m` (`id_m`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `date_m`
--

INSERT INTO `date_m` (`id_p`, `id_m`, `date_ouv`) VALUES
(2, 2, '2023-01-13'),
(2, 2, '2023-01-14'),
(3, 3, '2023-01-12'),
(3, 3, '2023-01-13'),
(4, 5, '2023-01-13'),
(5, 1, '2023-01-12'),
(5, 1, '2023-01-14'),
(6, 4, '2023-01-12'),
(6, 4, '2023-01-14'),
(19, 2, '2023-01-12'),
(19, 3, '2023-01-14'),
(20, 5, '2023-01-12'),
(21, 1, '2023-01-13'),
(21, 5, '2023-01-14'),
(22, 4, '2023-01-13');

-- --------------------------------------------------------

--
-- Structure de la table `directeur`
--

DROP TABLE IF EXISTS `directeur`;
CREATE TABLE IF NOT EXISTS `directeur` (
  `id_p` smallint NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `directeur`
--

INSERT INTO `directeur` (`id_p`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `emplacement`
--

DROP TABLE IF EXISTS `emplacement`;
CREATE TABLE IF NOT EXISTS `emplacement` (
  `nom_emplacement` varchar(50) CHARACTER SET latin1 NOT NULL,
  `nom_z` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`nom_emplacement`),
  KEY `nom_z` (`nom_z`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `emplacement`
--

INSERT INTO `emplacement` (`nom_emplacement`, `nom_z`) VALUES
('1', 'Enfants'),
('2', 'Sensation Fortes'),
('3', 'Aquatique'),
('4', 'Enfants');

-- --------------------------------------------------------

--
-- Structure de la table `famille`
--

DROP TABLE IF EXISTS `famille`;
CREATE TABLE IF NOT EXISTS `famille` (
  `nom_famille` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`nom_famille`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `famille`
--

INSERT INTO `famille` (`nom_famille`) VALUES
('Chaises Volante'),
('Chenille'),
('Chevaux'),
('Grand Huit'),
('Montagnes russe');

-- --------------------------------------------------------

--
-- Structure de la table `manege`
--

DROP TABLE IF EXISTS `manege`;
CREATE TABLE IF NOT EXISTS `manege` (
  `id_m` smallint NOT NULL,
  `nom_m` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `description_m` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `taille_min_m` int DEFAULT NULL,
  `fonctionne_m` tinyint(1) DEFAULT NULL,
  `nom_famille` varchar(50) CHARACTER SET latin1 NOT NULL,
  `id_p` smallint NOT NULL,
  `nom_z` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_m`),
  KEY `nom_famille` (`nom_famille`),
  KEY `id_p` (`id_p`),
  KEY `nom_z` (`nom_z`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `manege`
--

INSERT INTO `manege` (`id_m`, `nom_m`, `description_m`, `taille_min_m`, `fonctionne_m`, `nom_famille`, `id_p`, `nom_z`) VALUES
(1, 'Mega Splash', 'montagne russe qui éclabousse de l’eau', 140, 1, 'Montagnes russe', 5, 'Aquatique'),
(2, 'Petit Tonnerre', 'chevaux qui tourne en rond', 50, 1, 'Chevaux', 2, 'Enfants'),
(3, 'Rencontre des chèvres', 'enclos de chèvre', 0, 1, 'Animaux', 3, 'Enfants'),
(4, 'Le Briseur de Nuque', 'manege qui secoue fortement', 160, 1, 'Montagnes Russe', 6, 'Sensation Fortes'),
(5, 'Le Double 8', 'manège avec deux looping de suite', 140, 1, 'Grand huit', 4, 'Sensation Fortes');

-- --------------------------------------------------------

--
-- Structure de la table `nbr_client`
--

DROP TABLE IF EXISTS `nbr_client`;
CREATE TABLE IF NOT EXISTS `nbr_client` (
  `id_m` smallint NOT NULL,
  `matin` int DEFAULT NULL,
  `aprem` int DEFAULT NULL,
  PRIMARY KEY (`id_m`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `nbr_client`
--

INSERT INTO `nbr_client` (`id_m`, `matin`, `aprem`) VALUES
(1, 100, 180),
(2, 60, 88),
(3, 79, 140),
(4, 146, 220),
(5, 175, 258);

-- --------------------------------------------------------

--
-- Structure de la table `objet`
--

DROP TABLE IF EXISTS `objet`;
CREATE TABLE IF NOT EXISTS `objet` (
  `type_objet` varchar(50) CHARACTER SET latin1 NOT NULL,
  `quantiter_obj` int DEFAULT NULL,
  `prix_obj` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`type_objet`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `objet`
--

INSERT INTO `objet` (`type_objet`, `quantiter_obj`, `prix_obj`) VALUES
('Déguisement', 300, '39.99'),
('Tasse', 1000, '9.99'),
('Maillot', 600, '65.00'),
('Figurine', 560, '29.99');

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
CREATE TABLE IF NOT EXISTS `personnel` (
  `id_p` smallint NOT NULL,
  `nom_p` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `prenom_p` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `db_p` date DEFAULT NULL,
  `ss_p` varchar(40) CHARACTER SET latin1 DEFAULT NULL,
  `mdp_p` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`id_p`, `nom_p`, `prenom_p`, `db_p`, `ss_p`, `mdp_p`) VALUES
(16, 'Ramos', 'Louis', '1990-12-06', '288049935104876', '$2y$10$yI3lVk7DlUqLhPN42K2HWO9sV1LCQjX9mtyCJAflImy5GW4SmB3sK'),
(15, 'Gonzalez', 'Luis', '1996-01-19', '272103062244674', '$2y$10$XWbuZGmqZjQGNcclwdK0hOFm.PryJlRAJWf8Wb4gyrY9IzeHzfFeO'),
(14, 'Chen', 'Wei', '1989-05-10', '176046053900542', '$2y$10$/ZF/8v1mLDenb1C1I0jkJen.gXBYqMiS8w22IVX5S0jCoFBc5fDPq'),
(13, 'Sato', 'Yuki', '1994-02-14', '294022987656432', '$2y$10$7wC1kHVUNCg/Ascf.iVsfOR38fP2S/zSz8ut5OUrzDXJAKH6w3uii'),
(12, 'Kim', 'Hyeon', '1987-08-23', '287082468901237', '$2y$10$5Ygms5.fOae3KfLjt9bYp.X2jIXEkvUdBEmiBKLECi9ZOAubzqRwq'),
(11, 'Nguyen', 'Trung', '1991-11-01', '291112123695824', '$2y$10$FtGytvimiVaywYlngV0QDehmS1RZZr6VMaP7D0eWlEGelZzKxEYK6'),
(10, 'Lee', 'David', '1992-03-25', '292032987654321', '$2y$10$HWfqAzV9LXGXBbLhxp9moeNK3rrZy.Oj2sG0vx9i/ouNx/xqGuzh6'),
(9, 'Johnson', 'Emily', '1988-06-08', '288062369123456', '$2y$10$ARwcPZA1m8YV.CZOmYm9puXtM1YbO9xvVbZtWWFdiPihJkk7tU/CG'),
(8, 'Garcia', 'Manuel', '1993-09-22', '293092456890123', '$2y$10$5/CtoL.zuzmiQBtwZVaoJ.Udo9wi5Yl7ElWIiIV.deA9BTaJr2w5G'),
(2, 'Smith', 'Jane', '1985-02-15', '288022308906543', '$2y$10$A07ubOAajPEXBAFRci8e4ucnpnshI7PRvTy2ZjvOdaoSsD0Eb4tHe'),
(1, 'Doe', 'John', '1990-01-01', '189127308901234', '$2y$10$yI3lVk7DlUqLhPN42K2HWO9sV1LCQjX9mtyCJAflImy5GW4SmB3sK'),
(7, 'Girard', 'Thomas', '1995-02-28', '295022468913579', '$2y$10$aA3AItefVCKBlNtNO4XnieN91NTaFHBXvd8eeIw.S8pBDLjZ4kxP.'),
(6, 'Lefebvre', 'Julie', '1985-11-20', '285112308913575', '$2y$10$1Jhomhp14Oae67wpSsJiKeIl73b3sZ2jSthtcM.e5GC53t3Bv46qW'),
(5, 'Martin', 'Sophie', '1990-05-12', '290052308906543', '$2y$10$ZeIHw7OowNIRbj6fPUaM5urMQTkQ5haUNgXXphBTTiNT5EmxNDFCW'),
(4, 'Dupont', 'Pierre', '1980-01-01', '180012308901234', '$2y$10$JKbTWumKikw5OClsJeU97eP60NmSY8a0Je5mn4UHeGGykqdsdkE3O'),
(3, 'Garcia', 'Maria', '1995-07-03', '295072308913575', '$2y$10$Hvd3cRIxBd4.1Do53H6MsOd/fXY7I6aMtabke3IEjMHwVqM3rsmhq'),
(17, 'Baker', 'Oliver', '1998-09-15', '267052523646844', '$2y$10$xItb5VvCw8pt6sEYUbsrEOkq0UMd6DIUrQf7qLKxXuBkHsMoFn7L.'),
(18, 'Wong', 'Emily', '1993-04-30', '246099908696229', '$2y$10$AAD2M7nfDyco34CHYMmsEutZ4uh16dmAU1Ok/by/0WKxg6ssn.ogC'),
(19, 'Gomez', 'Ana', '1994-08-18', '277109040557585', '$2y$10$jelXrChLjd2y8o3A3Tzg2.8Ol1gxQZvaUU7GB9dTtS/tIgeG9vzyO'),
(20, 'Kumar', 'Raj', '1989-03-22', '247116052955515', '$2y$10$NzJ6c1LAse0ovKcO.6JzTOAHZNf1ItS8/vCpYZqzV8xpA490POlOK'),
(21, 'Yamamoto', 'Akira', '1996-05-09', '267105410832445', '$2y$10$C4e6r1UJ3.D8HRo.21BOhOaF3nb4N/9OczeuZIBx8l26mDZs6foxC'),
(22, 'Liu', 'Wei', '1992-11-17', '293031829991629', '$2y$10$vPmGTA5dfX.2j4UYEiNJQecmVgWEO2ftr0kDRiT3OOb6oXJYr7CBa');

-- --------------------------------------------------------

--
-- Structure de la table `piece_d`
--

DROP TABLE IF EXISTS `piece_d`;
CREATE TABLE IF NOT EXISTS `piece_d` (
  `nom_d` varchar(50) CHARACTER SET latin1 NOT NULL,
  `quantiter_d` int DEFAULT NULL,
  PRIMARY KEY (`nom_d`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `piece_d`
--

INSERT INTO `piece_d` (`nom_d`, `quantiter_d`) VALUES
('Ecrou', 100),
('Moteur', 30),
('Roue', 50),
('Poignée', 20),
('Plaque de fer', 80);

-- --------------------------------------------------------

--
-- Structure de la table `reparation`
--

DROP TABLE IF EXISTS `reparation`;
CREATE TABLE IF NOT EXISTS `reparation` (
  `id_repar` smallint NOT NULL,
  `date_repar` date DEFAULT NULL,
  `piece` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `id_m` smallint NOT NULL,
  `id_atelier` smallint NOT NULL,
  PRIMARY KEY (`id_repar`),
  KEY `id_m` (`id_m`),
  KEY `id_atelier` (`id_atelier`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `reparation`
--

INSERT INTO `reparation` (`id_repar`, `date_repar`, `piece`, `id_m`, `id_atelier`) VALUES
(1, '2023-01-12', 'Moteur', 1, 1),
(2, '2023-01-13', 'Ecrou', 2, 2),
(3, '2023-01-12', 'Plaque de fer', 2, 2),
(4, '2023-01-14', 'Ecrou', 3, 2),
(5, '2023-01-12', 'Ecrou', 5, 3),
(6, '2023-01-14', 'Roue', 5, 3),
(7, '2023-01-13', 'Poignée', 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `repare`
--

DROP TABLE IF EXISTS `repare`;
CREATE TABLE IF NOT EXISTS `repare` (
  `id_p` smallint NOT NULL,
  `id_atelier` smallint NOT NULL,
  PRIMARY KEY (`id_p`,`id_atelier`),
  KEY `Id_atelier` (`id_atelier`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `repare`
--

INSERT INTO `repare` (`id_p`, `id_atelier`) VALUES
(13, 1),
(14, 1),
(15, 2),
(16, 2),
(17, 3),
(18, 3);

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE IF NOT EXISTS `restaurant` (
  `id_b` smallint NOT NULL,
  `nombre_table` int NOT NULL,
  PRIMARY KEY (`id_b`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`id_b`, `nombre_table`) VALUES
(1, 25);

-- --------------------------------------------------------

--
-- Structure de la table `sers`
--

DROP TABLE IF EXISTS `sers`;
CREATE TABLE IF NOT EXISTS `sers` (
  `id_p` smallint NOT NULL,
  `id_b` smallint NOT NULL,
  PRIMARY KEY (`id_p`,`id_b`),
  KEY `id_b` (`id_b`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `sers`
--

INSERT INTO `sers` (`id_p`, `id_b`) VALUES
(7, 1),
(8, 1);

-- --------------------------------------------------------

--
-- Structure de la table `serveur`
--

DROP TABLE IF EXISTS `serveur`;
CREATE TABLE IF NOT EXISTS `serveur` (
  `id_p` smallint NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `serveur`
--

INSERT INTO `serveur` (`id_p`) VALUES
(7),
(8);

-- --------------------------------------------------------

--
-- Structure de la table `stocke`
--

DROP TABLE IF EXISTS `stocke`;
CREATE TABLE IF NOT EXISTS `stocke` (
  `id_atelier` smallint NOT NULL,
  `nom_d` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_atelier`,`nom_d`),
  KEY `nom_d` (`nom_d`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `stocke`
--

INSERT INTO `stocke` (`id_atelier`, `nom_d`) VALUES
(1, 'Ecrou'),
(1, 'Moteur'),
(2, 'Plaque de fer'),
(2, 'Roue'),
(3, 'Poignée');

-- --------------------------------------------------------

--
-- Structure de la table `technicien`
--

DROP TABLE IF EXISTS `technicien`;
CREATE TABLE IF NOT EXISTS `technicien` (
  `id_p` smallint NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `technicien`
--

INSERT INTO `technicien` (`id_p`) VALUES
(13),
(14),
(15),
(16),
(17),
(18);

-- --------------------------------------------------------

--
-- Structure de la table `vend`
--

DROP TABLE IF EXISTS `vend`;
CREATE TABLE IF NOT EXISTS `vend` (
  `id_p` smallint NOT NULL,
  `id_b` smallint NOT NULL,
  PRIMARY KEY (`id_p`,`id_b`),
  KEY `id_b` (`id_b`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `vend`
--

INSERT INTO `vend` (`id_p`, `id_b`) VALUES
(9, 2),
(10, 2),
(11, 3),
(12, 4);

-- --------------------------------------------------------

--
-- Structure de la table `vendeur`
--

DROP TABLE IF EXISTS `vendeur`;
CREATE TABLE IF NOT EXISTS `vendeur` (
  `id_p` smallint NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `vendeur`
--

INSERT INTO `vendeur` (`id_p`) VALUES
(9),
(10),
(11),
(12);

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

DROP TABLE IF EXISTS `vente`;
CREATE TABLE IF NOT EXISTS `vente` (
  `id_vente` varchar(50) CHARACTER SET latin1 NOT NULL,
  `date_vente` date DEFAULT NULL,
  `type_objet` varchar(50) CHARACTER SET latin1 NOT NULL,
  `id_b` smallint NOT NULL,
  `quantiter_vente` int NOT NULL,
  PRIMARY KEY (`id_vente`),
  KEY `type_objet` (`type_objet`),
  KEY `id_b` (`id_b`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id_vente`, `date_vente`, `type_objet`, `id_b`, `quantiter_vente`) VALUES
('21', '2023-01-13', 'Figurine', 2, 1),
('20', '2023-01-13', 'Figurine', 4, 2),
('19', '2023-01-13', 'Maillot', 2, 4),
('18', '2023-01-13', 'Maillot', 3, 3),
('17', '2023-01-13', 'Maillot', 4, 1),
('16', '2023-01-13', 'Tasse', 3, 1),
('15', '2023-01-13', 'Tasse', 4, 1),
('14', '2023-01-13', 'Tasse', 2, 4),
('13', '2023-01-13', 'Déguisement', 2, 2),
('12', '2023-01-13', 'Déguisement', 3, 3),
('11', '2023-01-13', 'Déguisement', 4, 4),
('10', '2023-01-12', 'Figurine', 4, 2),
('9', '2023-01-12', 'Figurine', 3, 3),
('8', '2023-01-12', 'Figurine', 2, 7),
('7', '2023-01-12', 'Maillot', 4, 2),
('6', '2023-01-12', 'Maillot', 2, 4),
('5', '2023-01-12', 'Tasse', 4, 5),
('4', '2023-01-12', 'Tasse', 3, 3),
('3', '2023-01-12', 'Tasse', 2, 8),
('2', '2023-01-12', 'Déguisement', 3, 4),
('1', '2023-01-12', 'Déguisement', 2, 5),
('22', '2023-01-13', 'Figurine', 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `zone`
--

DROP TABLE IF EXISTS `zone`;
CREATE TABLE IF NOT EXISTS `zone` (
  `nom_z` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`nom_z`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `zone`
--

INSERT INTO `zone` (`nom_z`) VALUES
('Aquatique'),
('Enfants'),
('Sensation Fortes');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
