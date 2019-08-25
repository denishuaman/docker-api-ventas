INSERT INTO persona(dni, nombres, apellidos) VALUES('01234567','Juan','Perez Lopez');
INSERT INTO persona(dni, nombres, apellidos) VALUES('09222322','Luis','Cortez');

INSERT INTO producto(nombre, marca, precio) VALUES('Teclado','Logitech',30.5);
INSERT INTO producto(nombre, marca, precio) VALUES('Mouse','Logitech',20);
INSERT INTO producto(nombre, marca, precio) VALUES('Memoria RAM','Kingston',230);
INSERT INTO producto(nombre, marca, precio) VALUES('Cargador','Toshiba',50);
INSERT INTO producto(nombre, marca, precio) VALUES('Laptop','SONY VAIO',3400);

INSERT INTO venta(id_persona, importe, fecha) VALUES(1,191.5,current_timestamp);
INSERT INTO venta(id_persona, importe, fecha) VALUES(2,510,current_timestamp);

INSERT INTO detalle_venta(id_venta, id_producto, cantidad) VALUES(1,1,3);
INSERT INTO detalle_venta(id_venta, id_producto, cantidad) VALUES(1,2,5);
INSERT INTO detalle_venta(id_venta, id_producto, cantidad) VALUES(2,3,2);
INSERT INTO detalle_venta(id_venta, id_producto, cantidad) VALUES(2,4,1);
