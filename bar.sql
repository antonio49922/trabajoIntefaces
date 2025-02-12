-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-02-2025 a las 18:50:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebidas`
--

CREATE TABLE `bebidas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `vaso` varchar(255) DEFAULT NULL,
  `cocktelera` varchar(255) DEFAULT NULL,
  `licor_principal_id` int(11) DEFAULT NULL,
  `multimedia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bebidas`
--

INSERT INTO `bebidas` (`id`, `nombre`, `vaso`, `cocktelera`, `licor_principal_id`, `multimedia`) VALUES
(1, 'B-52', 'Vaso Carajillo', 'Directamente en Vaso', 1, NULL),
(2, 'Between the Sheets', 'Copa Pirámide', 'Sí', 1, NULL),
(3, 'Black Russian', 'Vaso Roca', 'Sí', 2, NULL),
(4, 'Bloody Mary', 'Copa Balón', 'Sí', 4, NULL),
(5, 'Caipirinha', 'Vaso Sidra', 'Directamente en Vaso', 6, NULL),
(6, 'Caipiroska', 'Vaso Sidra', 'Directamente en Vaso', 4, NULL),
(7, 'Calipada', 'Garrafa', 'Directamente en Vaso', 4, NULL),
(8, 'Cointreaupolitan', 'Copa Pirámide', 'Sí', 1, NULL),
(9, 'Cosmopolitan', 'Copa Pirámide', 'Sí', 4, NULL),
(10, 'Daikiri', 'Copa Pirámide', 'Sí', 3, NULL),
(11, 'Daikiri Bosque', 'Copa Pirámide', 'Sí', 3, NULL),
(12, 'Espresso Martini', 'Copa Pirámide', 'Sí', 4, NULL),
(13, 'Ficha Perdida', 'Copa Balón', 'Sí', 1, NULL),
(14, 'French Connection', 'Copa Pirámide', 'Sí', 5, NULL),
(15, 'French Connection v2', 'Vaso Roca', 'Directamente en Vaso', 5, NULL),
(16, 'Gin Fizz', 'Copa Balón', 'Sí', 7, NULL),
(17, 'Guarapita', 'Copa Balón', 'Sí', 3, NULL),
(18, 'Inforromántico', 'Copa Pirámide', 'Sí', 1, NULL),
(19, 'Leche de Bantha', 'Vaso Roca', 'Sí', 7, NULL),
(20, 'Leche de Juako', 'Vaso Roca', 'Sí', 7, NULL),
(21, 'Leche de Pantera', 'Vaso Roca', 'Sí', 7, NULL),
(22, 'Long Island Ice Tea', 'Copa Balón', 'Sí', 4, NULL),
(23, 'Margarita', 'Copa Margarita', 'Sí', 8, NULL),
(24, 'Margarita de Mango', 'Copa Margarita', 'Batidora', 8, NULL),
(25, 'Mojito', 'Vaso Sidra', 'Directamente en Vaso', 3, NULL),
(26, 'Piña Colada', 'Copa Balón', 'Sí', 3, NULL),
(27, 'Ruso Blanco', 'Vaso Roca', 'Sí', 2, NULL),
(28, 'Sex On The Beach', 'Copa Balón', 'Directamente en Vaso', 4, NULL),
(29, 'Tequila Sunrise', 'Copa Balón', 'Directamente en Vaso', 8, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingredientes`
--

CREATE TABLE `ingredientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `plato_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingredientes`
--

INSERT INTO `ingredientes` (`id`, `nombre`, `plato_id`) VALUES
(1, 'Pollo', 1),
(2, 'Crutones', 1),
(3, 'Aderezo César', 1),
(4, 'Pan de hamburguesa', 2),
(5, 'Carne de res', 2),
(6, 'Queso', 2),
(7, 'Lechuga', 2),
(8, 'Tomate', 2),
(9, 'Cebolla', 2),
(10, 'Tortilla de trigo', 3),
(11, 'Pollo', 3),
(12, 'Lechuga', 3),
(13, 'Tomate', 3),
(14, 'Aderezo César', 3),
(15, 'Pan de molde', 4),
(16, 'Pollo', 4),
(17, 'Queso', 4),
(18, 'Lechuga', 4),
(19, 'Tomate', 4),
(20, 'Aderezo César', 4),
(21, 'Lechuga', 5),
(22, 'Tomate', 5),
(23, 'Cebolla', 5),
(24, 'Crutones', 5),
(25, 'Aderezo César', 5),
(26, 'Pan de hamburguesa', 6),
(27, 'Carne de res', 6),
(28, 'Carne de res', 6),
(29, 'Queso', 6),
(30, 'Lechuga', 6),
(31, 'Tomate', 6),
(32, 'Cebolla', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licores_principales`
--

CREATE TABLE `licores_principales` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `licores_principales`
--

INSERT INTO `licores_principales` (`id`, `nombre`) VALUES
(1, 'Cointreau'),
(2, 'Licor Café'),
(3, 'Ron'),
(4, 'Vodka'),
(5, 'Coñac'),
(6, 'Cachaça'),
(7, 'Ginebra'),
(8, 'Tequila'),
(9, 'Amaretto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platos`
--

CREATE TABLE `platos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `platos`
--

INSERT INTO `platos` (`id`, `nombre`, `descripcion`, `precio`) VALUES
(1, 'Ensalada César', 'Ensalada con pollo, crutones y aderezo César', 8.50),
(2, 'Hamburguesa Clásica', 'Hamburguesa con queso, lechuga, tomate y cebolla', 10.00),
(3, 'Wrap de Pollo', 'Tortilla de trigo rellena de pollo, lechuga, tomate y aderezo César', 9.00),
(4, 'Sándwich Club', 'Sándwich con pollo, queso, lechuga, tomate y aderezo César', 9.50),
(5, 'Ensalada Mixta', 'Mezcla de lechuga, tomate, cebolla y crutones con aderezo César', 7.50),
(6, 'Cheeseburger Doble', 'Hamburguesa doble con queso, lechuga, tomate y cebolla', 12.00),
(7, 'Tarta de Queso', '', 3.00),
(8, 'Helado de Vainilla', '', 2.50),
(9, 'Flan de Huevo', '', 2.00),
(10, 'Brownie de Chocolate', '', 3.50),
(11, 'Filite de Ternera', '', 8.00),
(12, 'Pescado a la Plancha', '', 7.00),
(13, 'Pollo Asado', '', 6.00),
(14, 'Hamburguesa Completa', '', 5.00),
(15, 'Patatas con Alioli', '', 4.00),
(16, 'Croqueta de Jamon y Queso', '', 4.00),
(17, 'Montaditos de Tortilla con Cebolla', '', 4.00),
(18, 'Mini Buñuelos de Queso', '', 4.00),
(19, 'Champiñones al Ajillo', '', 6.00),
(19, 'Berenjenas fritas con Miel', '', 7.00),
(19, 'Boquerones en Vinagre', '', 7.50),
(19, 'Rabas de Calamar', '', 8.50);



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `rol` enum('admin','usuario') DEFAULT 'usuario'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido` ,  `email`, `contraseña`, `rol`) VALUES
(1, 'Admin', 'admin'  ,  'admin@bar.com', 'admin123', 'admin'),
(2, 'Usuario', 'usuario', 'usuario@bar.com', 'usuario123', 'usuario');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bebidas`
--
ALTER TABLE `bebidas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `licor_principal_id` (`licor_principal_id`);

--
-- Indices de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `plato_id` (`plato_id`);

--
-- Indices de la tabla `licores_principales`
--
ALTER TABLE `licores_principales`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `platos`
--
ALTER TABLE `platos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bebidas`
--
ALTER TABLE `bebidas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `licores_principales`
--
ALTER TABLE `licores_principales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `platos`
--
ALTER TABLE `platos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bebidas`
--
ALTER TABLE `bebidas`
  ADD CONSTRAINT `bebidas_ibfk_1` FOREIGN KEY (`licor_principal_id`) REFERENCES `licores_principales` (`id`);

--
-- Filtros para la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD CONSTRAINT `ingredientes_ibfk_1` FOREIGN KEY (`plato_id`) REFERENCES `platos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
