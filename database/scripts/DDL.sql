drop table if exists detalle_venta;
drop table if exists venta;
drop table if exists persona;
drop table if exists producto;

create table public.producto (
  id_producto serial primary key,
  nombre varchar(100) not null,
  marca varchar(100) not null,
  precio float not null
);

create table persona (
  id_persona serial primary key,
  dni varchar(8) not null unique,
  nombres varchar(100) not null,
  apellidos varchar(100) not null
);

create table venta (
  id_venta serial primary key,
  id_persona integer not null,
  importe float not null,
  fecha timestamp not null,

  constraint fk_venta_persona_idx foreign key (id_persona)
    references public.persona(id_persona) match simple
      on update no action
      on delete no action
);

create table if not exists detalle_venta (
  id_detalle_venta serial primary key,
  id_venta int not null,
  id_producto int not null,
  cantidad int null,
  constraint fk_venta_has_producto_producto1_idx foreign key (id_producto)
    references public.producto(id_producto) match simple
      on update no action
      on delete no action,
  constraint fk_venta_has_producto_venta1_idx foreign key (id_venta)
    references public.venta(id_venta) match simple
      on update no action
      on delete no action
);