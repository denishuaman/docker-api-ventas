USE ventas;

INSERT INTO `persona` VALUES(3,'08123232','Juan','Pérez Lopez');
INSERT INTO `persona` VALUES(4,'09222322','María','Cortez');

INSERT INTO `producto` VALUES(1,'Teclado','Logitech',30.5);
INSERT INTO `producto` VALUES(2,'Mouse','Logitech',20);
INSERT INTO `producto` VALUES(3,'Memoria RAM','Kingston',230);
INSERT INTO `producto` VALUES(4,'Cargador','Toshiba',50);
INSERT INTO `producto` VALUES(6,'Laptop','SONY VAIO',3400);

INSERT INTO `venta` VALUES(1,3,191.5,'2019-08-04 04:39:27');
INSERT INTO `venta` VALUES(2,4,510,'2019-08-04 04:59:26');

INSERT INTO `detalle_venta` VALUES(1,1,1,3);
INSERT INTO `detalle_venta` VALUES(2,1,2,5);
INSERT INTO `detalle_venta` VALUES(3,2,3,2);
INSERT INTO `detalle_venta` VALUES(4,2,4,1);

COMMIT;
