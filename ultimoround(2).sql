-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-07-2015 a las 23:05:10
-- Versión del servidor: 5.6.20
-- Versión de PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `ultimoround`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compraproducto`
--

CREATE TABLE IF NOT EXISTS `compraproducto` (
  `id_compra_producto` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `monto_unitario` int(11) DEFAULT NULL,
  `monto_total` int(11) DEFAULT NULL,
  `proveedor` varchar(45) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_orden_de_compra` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kit_productos`
--

CREATE TABLE IF NOT EXISTS `kit_productos` (
  `id_kit_productos` int(11) NOT NULL,
  `nombre_kit` varchar(45) DEFAULT NULL,
  `precio_compra_productos` int(11) DEFAULT NULL,
  `precio_venta_kit` int(11) DEFAULT NULL,
  `descripcion_kit` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordendecompra`
--

CREATE TABLE IF NOT EXISTS `ordendecompra` (
  `id_orden_compra` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `monto_total` int(11) DEFAULT NULL,
  `numero_factura_recibida` int(11) DEFAULT NULL,
  `proveedor` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordendeventa`
--

CREATE TABLE IF NOT EXISTS `ordendeventa` (
  `id_orden_venta` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `monto_total` varchar(45) DEFAULT NULL,
  `numero_boleta` int(11) DEFAULT NULL,
  `medio_pago` varchar(45) DEFAULT NULL,
  `estado_presupuesto` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `id_producto` int(11) NOT NULL,
  `codigo_barra` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `talla` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `precio_compra` int(20) DEFAULT NULL,
  `precio_venta` int(20) DEFAULT NULL,
  `proveedor` varchar(45) DEFAULT NULL,
  `cantidad_actual` int(11) DEFAULT NULL,
  `tipo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `codigo_barra`, `nombre`, `marca`, `talla`, `color`, `precio_compra`, `precio_venta`, `proveedor`, `cantidad_actual`, `tipo`) VALUES
(1, '111111', 'SHORT MAXIMUS VM', 'VENUM', '52', 'AGREGAR CAMPO COLOR', 15000, 42259, 'VENUM SA', 10, 'SHORT'),
(2, '111111', 'SHORT ESPECIAL VM', 'VENUM', '46', 'AGREGAR CAMPO COLOR', 20000, 50000, 'VENUM SA', 20, 'SHORT'),
(3, '111111', 'SHORT ESPECIAL VM', 'VENUM', '46', 'AGREGAR CAMPO COLOR', 20000, 50000, 'VENUM SA', 20, 'SHORT');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacion_kit_productos`
--

CREATE TABLE IF NOT EXISTS `relacion_kit_productos` (
  `kit_productos` bigint(100) NOT NULL,
  `id_producto` bigint(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventaproducto`
--

CREATE TABLE IF NOT EXISTS `ventaproducto` (
  `id_venta_producto` int(11) NOT NULL,
  `id_orden_de_venta` int(11) DEFAULT NULL,
  `id_kit_producto` int(11) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `precio_unitario_neto` int(11) DEFAULT NULL,
  `cantidad_producto` int(11) DEFAULT NULL,
  `precio_unitario_final` int(11) DEFAULT NULL,
  `precio_total_neto` int(11) DEFAULT NULL,
  `precio_total_final` int(11) DEFAULT NULL,
  `descuento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
 ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `compraproducto`
--
ALTER TABLE `compraproducto`
 ADD PRIMARY KEY (`id_compra_producto`), ADD KEY `fk_CompraProducto_1_idx` (`id_producto`), ADD KEY `fk_CompraProducto_1_idx1` (`id_orden_de_compra`);

--
-- Indices de la tabla `kit_productos`
--
ALTER TABLE `kit_productos`
 ADD PRIMARY KEY (`id_kit_productos`);

--
-- Indices de la tabla `ordendecompra`
--
ALTER TABLE `ordendecompra`
 ADD PRIMARY KEY (`id_orden_compra`);

--
-- Indices de la tabla `ordendeventa`
--
ALTER TABLE `ordendeventa`
 ADD PRIMARY KEY (`id_orden_venta`), ADD KEY `fk_OrdenDeVenta_1_idx` (`id_cliente`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
 ADD PRIMARY KEY (`id_producto`,`codigo_barra`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `ventaproducto`
--
ALTER TABLE `ventaproducto`
 ADD PRIMARY KEY (`id_venta_producto`), ADD KEY `fk_VentaProducto_1_idx` (`id_orden_de_venta`), ADD KEY `fk_VentaProducto_2_idx` (`id_kit_producto`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compraproducto`
--
ALTER TABLE `compraproducto`
ADD CONSTRAINT `id_orden_de_compra` FOREIGN KEY (`id_orden_de_compra`) REFERENCES `ordendecompra` (`id_orden_compra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ordendeventa`
--
ALTER TABLE `ordendeventa`
ADD CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ventaproducto`
--
ALTER TABLE `ventaproducto`
ADD CONSTRAINT `id_kit_producto` FOREIGN KEY (`id_kit_producto`) REFERENCES `kit_productos` (`id_kit_productos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `id_orden_de_venta` FOREIGN KEY (`id_orden_de_venta`) REFERENCES `ordendeventa` (`id_orden_venta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
