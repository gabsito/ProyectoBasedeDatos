use proyecto_kywy;
drop view if exists vista1;
create view vista1 as
select a.nombre as nombre, a.apellido as apellido, sum(fp.valor*fp.cantidad) as total from administrador a inner join (factura f,factura_producto fp) on (f.usuario_admin=a.usuario and fp.numero_factura=f.numero_factura) group by (a.usuario) order by total desc;
select * from vista1;

drop view if exists vista2;
create view vista2 as
select c.nombre,sum(fp.valor*fp.cantidad) as total from cliente c inner join(factura f,factura_producto fp) on (f.ruc_cliente=c.ruc_cliente and fp.numero_factura=f.numero_factura) group by (c.ruc_cliente) order by total desc;
select * from vista2;

drop view if exists vista3;
create view vista3 as 
select p.codigo_producto, p.descripcion, sum(fp.cantidad)as total from factura_producto fp inner join (producto p) on p.codigo_producto=fp.codigo_producto group by p.codigo_producto order by total desc;
select * from vista3;

/*
drop view if exists vista4;
create view vista4 as
select  month(f.fecha) as mes,year(f.fecha) as año,sum(fp.valor*fp.cantidad) as total from factura f inner join(factura_producto fp) on (fp.numero_factura=f.numero_factura) group by (mes,año) order by total desc;
select * from vista4;