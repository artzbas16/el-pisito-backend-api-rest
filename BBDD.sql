-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: elpisito2026
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banners`
--

DROP TABLE IF EXISTS `banners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banners` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `claim` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `titular` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners`
--

LOCK TABLES `banners` WRITE;
/*!40000 ALTER TABLE `banners` DISABLE KEYS */;
INSERT INTO `banners` VALUES (1,1,'Ahora nuestras hipotecas te permiten un periodo de esclavitud menor','consultor-hipotecas','Compra tu casa ya!'),(2,1,'Nuestro servicio de asesoramiento abarca también la fiscalidad. No dudes en consultarnos','nuestros-servicios','¿Preocupado?.¿Necesitas asesoramiento fiscal?'),(3,1,'Visita nuestras oficinas si necisitas asesesoramiento fiscal, contable o jurídico','nuestros-servicios','Ofrecemos mucho más de lo que imaginas'),(4,1,'Para cualquier duda que tengas...','contacto','Visitanos en nuestras oficinas o utiliza nuestro teéfono');
/*!40000 ALTER TABLE `banners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banners_carousel`
--

DROP TABLE IF EXISTS `banners_carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banners_carousel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `claim` varchar(255) DEFAULT NULL,
  `titular` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners_carousel`
--

LOCK TABLES `banners_carousel` WRITE;
/*!40000 ALTER TABLE `banners_carousel` DISABLE KEYS */;
INSERT INTO `banners_carousel` VALUES (1,1,'Ahora más accesibles que nunca','Pisos en la costa'),(2,0,'32 apartementos en Pirineos de nueva construcción te están esperando','¿Te gusta la montaña?');
/*!40000 ALTER TABLE `banners_carousel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alt_imagen` varchar(255) DEFAULT NULL,
  `entidad_id` bigint DEFAULT NULL,
  `entidad_imagen` enum('BANNER','BANNER_CAROUSEL','INMOBILIARIA','INMUEBLE') DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (13,'cocina muy soleada',3,'INMUEBLE','17bc2f54-7406-42eb-8c4c-05342b1197b2.jpg'),(14,'salón muy amplio',2,'INMUEBLE','d9442207-aa02-48d3-a3c6-9ccd5c9a7a82.jpg'),(15,'salón super coqueto',2,'INMUEBLE','dba103b1-062b-4467-9318-e1383c76d8d3.jpg'),(16,'Chica pensando',1,'BANNER','fe47cb51-83fe-4717-ab56-fb5cdb38f3fd.jpg'),(17,'Chalet blanco con mar azul',1,'BANNER_CAROUSEL','2da2ae7d-8e1c-497a-aa78-c3ccf727d4c9.jpg'),(18,'Logo de la inmobiliaria Arbeteta',1,'INMOBILIARIA','d6bab1d5-3f6c-4e55-95ba-460a290e6f45.jpg'),(19,'Casa de Montaña',2,'BANNER_CAROUSEL','a27e4a16-db9b-4e82-9d88-bf2e45ce6652.jpg'),(20,'Sala con mesita de Ikea',1,'INMUEBLE','7f1d4b59-ba9b-4dce-8340-43439acdb884.jpg'),(21,'Sala con cuadritos',1,'INMUEBLE','7089f1a5-9682-4873-b2f7-926df550fdd6.jpg'),(22,'Logo inmobiliaria Artea',2,'INMOBILIARIA','a0978f76-8db7-4f51-a855-d98af4d1d756.jpg'),(23,'Logo inmobiliaria Chamón',3,'INMOBILIARIA','e19edee6-991a-49fa-944f-e864af87cff5.jpg'),(25,'Logo inmobiliaria Chalets Bizkaia',4,'INMOBILIARIA','bb1555ea-06fc-4e1a-bf61-7b56f15ed0e3.jpg'),(27,'Logo de la inmobiliaria EL Pisito',7,'INMOBILIARIA','6e787cf4-a20e-407a-9a3e-ade86f2be03d.jpg'),(31,'Logo de la inmobiliaria Irizar',8,'INMOBILIARIA','5bddef69-6c9c-4d13-94ed-23c1d93984a4.jpg'),(32,'Logo de la inmobiliaria Ordunte',6,'INMOBILIARIA','6b9d6e10-82cd-4134-8ec5-908023559ada.jpg'),(37,'Logo de la inmobiliaria Don Piso',5,'INMOBILIARIA','88993dd1-95c6-4fc1-afe0-778eee0ddf0e.jpg');
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inmobiliarias`
--

DROP TABLE IF EXISTS `inmobiliarias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inmobiliarias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `representante` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK1q30i1fhly6jnigc4htduh05d` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inmobiliarias`
--

LOCK TABLES `inmobiliarias` WRITE;
/*!40000 ALTER TABLE `inmobiliarias` DISABLE KEYS */;
INSERT INTO `inmobiliarias` VALUES (1,1,'Arbeteta','José Luis Arbeteta','56434323'),(2,1,'Artea','Maria Jesús Puente','78760000'),(3,0,'Chamón','Felipe Suarez','34598543'),(4,0,'Chalets Bizkaia','María Teresa Del Campo','76543099'),(5,1,'Don Piso','Jose Luis Piso','67838924'),(6,1,'Ordunte','Iraia Ordunte','76543289'),(7,1,'EL Pisito','Arotz Basauri','99993333'),(8,1,'Irizar','Txomin Joseba Irizar','63124856');
/*!40000 ALTER TABLE `inmobiliarias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inmuebles`
--

DROP TABLE IF EXISTS `inmuebles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inmuebles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `amueblado` int DEFAULT NULL,
  `apertura` varchar(255) DEFAULT NULL,
  `ascensor` int DEFAULT NULL,
  `balcones` int DEFAULT NULL,
  `banhos` int DEFAULT NULL,
  `calefaccion` varchar(255) DEFAULT NULL,
  `claim` varchar(255) DEFAULT NULL,
  `descripcion` varchar(3500) DEFAULT NULL,
  `garajes` int DEFAULT NULL,
  `habitaciones` int DEFAULT NULL,
  `jardin` int DEFAULT NULL,
  `nombre_via` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `oportunidad` int DEFAULT NULL,
  `orientacion` varchar(255) DEFAULT NULL,
  `piscina` int DEFAULT NULL,
  `planta` varchar(255) DEFAULT NULL,
  `portada` int DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `puerta` varchar(255) DEFAULT NULL,
  `superficie_construida` double DEFAULT NULL,
  `superficie_util` double DEFAULT NULL,
  `tendedero` int DEFAULT NULL,
  `trastero` int DEFAULT NULL,
  `via` varchar(255) DEFAULT NULL,
  `tipo` bigint DEFAULT NULL,
  `operacion` bigint DEFAULT NULL,
  `poblacion` bigint DEFAULT NULL,
  `inmobiliaria` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcs1i8omrwkr2pq3155pxn6qdp` (`tipo`),
  KEY `FK2bn1118dujtjmuwfxsgxu6prn` (`operacion`),
  KEY `FKbsltt65sdkybknkr8nb7qn43s` (`poblacion`),
  KEY `FKdv7e1kvol5bmdx2u783107cbn` (`inmobiliaria`),
  CONSTRAINT `FK2bn1118dujtjmuwfxsgxu6prn` FOREIGN KEY (`operacion`) REFERENCES `operaciones` (`id`),
  CONSTRAINT `FKbsltt65sdkybknkr8nb7qn43s` FOREIGN KEY (`poblacion`) REFERENCES `poblaciones` (`id`),
  CONSTRAINT `FKcs1i8omrwkr2pq3155pxn6qdp` FOREIGN KEY (`tipo`) REFERENCES `tipos` (`id`),
  CONSTRAINT `FKdv7e1kvol5bmdx2u783107cbn` FOREIGN KEY (`inmobiliaria`) REFERENCES `inmobiliarias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inmuebles`
--

LOCK TABLES `inmuebles` WRITE;
/*!40000 ALTER TABLE `inmuebles` DISABLE KEYS */;
INSERT INTO `inmuebles` VALUES (1,1,0,'EXTERIOR',1,2,1,'CENTRAL','Maravilloso Piso en Algorta!!!','Descubre el lujo costero en Algorta con esta espectacular residencia de 224 m², que ofrece una experiencia de vida inigualable con vistas panorámicas al majestuoso mar Cantábrico y al pintoresco paisaje de Getxo. Sus dos amplias terrazas te permitirán disfrutar de este entorno privilegiado desde la comodidad de tu hogar.',0,2,0,'Salsidu','26',0,'NORTE',0,'SEGUNDA',1,250000,'B',75,72,1,1,'CALLE',1,1,1,3),(2,1,0,'INTERIOR',1,0,2,'ELÉCTRICA','Cerca de la playa!!!','Maravillosamente ubicada en el mejor enclave y en la posiblemente mejor finca de todo Algorta, con unas espectaculares vistas a la playa de Ereaga y al Abra desde toda la casa y especialmente desde la fabulosa terraza-jardín de 350 m² que rodea la vivienda, presentamos esta magnífica propiedad que no puede dejar indiferente a nadie.',1,3,0,'Euskalherria','32',1,'SUR',0,'BAJO',1,310000,'A',100,98,1,1,'CALLE',1,1,1,1),(3,1,0,'EXTERIOR',1,1,2,'GAS NATURAL','Piso en Dos Hermanas con mucha luz','Una vivienda de 100m2 construidos que se distribuye en un hall de entrada, un amplio salón, cocina equipada, tres dormitorios, un baño y un aseo. En un magnífico edificio en el centro de Algorta, en la Plaza San Nicolás.',1,3,0,'ElCordobés','45',0,'SUR',0,'TERCERA',1,235000,'C',90,98,1,1,'AVENIDA',1,2,3,3),(4,1,0,'EXTERIOR',0,4,3,'PROPANO','Chalet con piscina','Te presentamos esta magnífica vivienda, completamente exterior y llena de luz natural. Al entrar, hall que da paso a un amplio y luminoso salón, ideal para disfrutar de momentos en familia o con amigos.',4,5,1,'Central','2',0,'SUR',1,'SIN PLANTA',1,175000,'D',210,300,1,1,'PLAZA',2,1,4,3);
/*!40000 ALTER TABLE `inmuebles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
INSERT INTO `operaciones` VALUES (1,1,'VENTA'),(2,1,'ALQUILER'),(3,1,'TRASPASO'),(4,1,'NUDA PROPIEDAD');
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagina-banner`
--

DROP TABLE IF EXISTS `pagina-banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagina-banner` (
  `pagina_id` bigint NOT NULL,
  `banner_id` bigint NOT NULL,
  PRIMARY KEY (`pagina_id`,`banner_id`),
  KEY `FKmvlrehwbhlc4srpmcpp8w276j` (`banner_id`),
  CONSTRAINT `FKmvlrehwbhlc4srpmcpp8w276j` FOREIGN KEY (`banner_id`) REFERENCES `banners` (`id`),
  CONSTRAINT `FKsdglnxtan20e5osjqkf5so9mq` FOREIGN KEY (`pagina_id`) REFERENCES `paginas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagina-banner`
--

LOCK TABLES `pagina-banner` WRITE;
/*!40000 ALTER TABLE `pagina-banner` DISABLE KEYS */;
INSERT INTO `pagina-banner` VALUES (1,1),(2,1),(3,1),(4,2),(5,2),(11,2);
/*!40000 ALTER TABLE `pagina-banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paginas`
--

DROP TABLE IF EXISTS `paginas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paginas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpnhdfhof5hexmcvam0rwwbo4k` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paginas`
--

LOCK TABLES `paginas` WRITE;
/*!40000 ALTER TABLE `paginas` DISABLE KEYS */;
INSERT INTO `paginas` VALUES (1,1,'home'),(2,1,'consultor-hipotecas'),(3,1,'nuestros-servicios'),(4,1,'finder'),(5,1,'contacto'),(6,1,'inmobiliaria'),(7,1,'mapa-web'),(8,1,'publica-anuncio'),(9,1,'sobre-nosotros'),(10,1,'favoritos'),(11,1,'detail-inmueble');
/*!40000 ALTER TABLE `paginas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poblaciones`
--

DROP TABLE IF EXISTS `poblaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poblaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `provincia` bigint DEFAULT NULL,
  `cp` varchar(255) DEFAULT NULL,
  `provincias` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKed2mfrtnivr1wcnmq335c4ldf` (`provincia`),
  KEY `FK24pq4pq8s1vx5gy59w06c29ds` (`provincias`),
  CONSTRAINT `FK24pq4pq8s1vx5gy59w06c29ds` FOREIGN KEY (`provincias`) REFERENCES `provincias` (`id`),
  CONSTRAINT `FKed2mfrtnivr1wcnmq335c4ldf` FOREIGN KEY (`provincia`) REFERENCES `provincias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poblaciones`
--

LOCK TABLES `poblaciones` WRITE;
/*!40000 ALTER TABLE `poblaciones` DISABLE KEYS */;
INSERT INTO `poblaciones` VALUES (1,1,'BILBAO',1,'48990',NULL),(3,1,'DOS HERMANAS',3,'54678',NULL),(4,1,'VILLARCAYO',4,'09876',NULL),(5,1,'MÓSTOLES',2,'56123',NULL),(6,1,'BARAKALDO',1,'48223',NULL),(7,1,'SOPELANA',1,'48331',NULL),(8,1,'SEVILLA',3,NULL,NULL),(9,1,'URDULIZ',1,'48259',NULL);
/*!40000 ALTER TABLE `poblaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,1,'BIZKAIA'),(2,1,'MADRID'),(3,1,'SEVILLA'),(4,1,'BURGOS'),(5,1,'VALENCIA');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tematica-bannercarousel`
--

DROP TABLE IF EXISTS `tematica-bannercarousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tematica-bannercarousel` (
  `tematica_id` bigint NOT NULL,
  `banner_carousel_id` bigint NOT NULL,
  PRIMARY KEY (`tematica_id`,`banner_carousel_id`),
  KEY `FKh0977w9ak0pc3xmwq90q0l9i9` (`banner_carousel_id`),
  CONSTRAINT `FK1mtkdjfbkrxs7do2vjg5lvtjr` FOREIGN KEY (`tematica_id`) REFERENCES `tematicas` (`id`),
  CONSTRAINT `FKh0977w9ak0pc3xmwq90q0l9i9` FOREIGN KEY (`banner_carousel_id`) REFERENCES `banners_carousel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tematica-bannercarousel`
--

LOCK TABLES `tematica-bannercarousel` WRITE;
/*!40000 ALTER TABLE `tematica-bannercarousel` DISABLE KEYS */;
INSERT INTO `tematica-bannercarousel` VALUES (1,1);
/*!40000 ALTER TABLE `tematica-bannercarousel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tematicas`
--

DROP TABLE IF EXISTS `tematicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tematicas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `actual` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2kklv2limn5qo03yt2q1fl8ys` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tematicas`
--

LOCK TABLES `tematicas` WRITE;
/*!40000 ALTER TABLE `tematicas` DISABLE KEYS */;
INSERT INTO `tematicas` VALUES (1,1,'CAMPAÑA DE VERANO',1),(2,1,'FELIZ NAVIDAD',0),(3,1,'OTOÑO EN EL CAMPO',0),(4,1,'INVIERNO EN LA NIEVE',0),(5,0,'MAR AZUL EN LA COSTA',0),(6,1,'MONTAÑAS MILAGROSAS',0);
/*!40000 ALTER TABLE `tematicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,1,'PISO'),(2,1,'CHALET INDIVIDUAL'),(3,1,'LONJA'),(4,1,'TERRENO'),(5,1,'CHALET ADOSADO'),(6,1,'PARCELA DE GARAJE');
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario-inmueble`
--

DROP TABLE IF EXISTS `usuario-inmueble`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario-inmueble` (
  `usuario_id` bigint NOT NULL,
  `inmueble_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`inmueble_id`),
  KEY `FKtgakh7e643s5tpkja75dlj07v` (`inmueble_id`),
  CONSTRAINT `FKbq72nvxw904jt5sbyx6nsyao4` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKtgakh7e643s5tpkja75dlj07v` FOREIGN KEY (`inmueble_id`) REFERENCES `inmuebles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario-inmueble`
--

LOCK TABLES `usuario-inmueble` WRITE;
/*!40000 ALTER TABLE `usuario-inmueble` DISABLE KEYS */;
INSERT INTO `usuario-inmueble` VALUES (11,1),(8,2),(8,3),(9,3),(11,3),(11,4);
/*!40000 ALTER TABLE `usuario-inmueble` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_open` varchar(255) DEFAULT NULL,
  `rol` enum('ROLE_ADMIN','ROLE_SUPERADMIN','ROLE_USUARIO') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UKio49vjba68pmbgpy9vtw8vm81` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (8,1,'pesao@gmail.com','Flanders','$2a$10$L57gTOjMwxgKSpFo6XOreOqAyxstFSHj4T6DMfUE5ZOoCU8hMOa42','pesao','ROLE_USUARIO'),(9,1,'money@gmail.com','Burns','$2a$10$i5uCseXSDkCR.AP12DngtelfD1bmm76rM.aJuVGZjIVJpHyFFNJui','money','ROLE_SUPERADMIN'),(10,1,'pelota@gmail.com','Smithers','$2a$10$8iM2xe/CBjcjzFyRyR1ZnezVYcMUVClKkjgShjj7IxtfuhmE285je','pelota','ROLE_ADMIN'),(11,1,'empollona@gmail.com','Lisa','$2a$10$EsjinoxqpyFmqcKdovd.L.jLVUZHdXGGYD756PjGKCynu1fy0S8E6','empollona','ROLE_USUARIO');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-23 12:15:53
