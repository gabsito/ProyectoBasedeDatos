use proyecto_kywy;

drop trigger if exists precioFactura;
delimiter |
create trigger precioFactura after insert on factura_producto
for each row
begin 
update factura set valor=0 where valor is null;
	update factura set factura.valor=factura.valor+new.cantidad*new.valor*1.12 where factura.numero_factura=new.numero_factura;
end;
| delimiter ;

drop trigger if exists precioProforma;

delimiter |
create trigger precioProforma after insert on proforma_producto
for each row
begin 
	update proforma set valor=0 where valor is null;
	update proforma set 
		proforma.valor=proforma.valor+new.cantidad*(select valor from producto where codigo_producto=new.codigo_producto)*1.12 
        where cod_proforma=new.cod_proforma;
end;
| delimiter ;








use proyecto_kywy;
drop procedure if exists login;
select * from cliente;
Delimiter || 
create procedure login(in usuar varchar(10),in contra varchar(32),out bln boolean)
begin
declare exit handler for sqlexception 
begin
rollback;
end;
start transaction;
	if (select count(contrasena) from administrador where usuario=usuar and contrasena=contra group by (usuario))>0 then 
		set bln = true;
	else
		set bln = false;
	end if;
    commit;
end;
|| Delimiter ;

call login ("dsanti","true",@r);
select @r;





drop view if exists vistaInfoFactura;
create view vistaInfoFactura as (
select a.usuario as UsuarioAdmin, a.nombre as NombreVendedor, a.apellido as ApellidoVendedor, a.cedula as Cedula,
c.ruc_cliente as RUC,c.nombre as Cliente, c.direccion as Direccion, c.telefono as Telefono,
f.numero_factura as NumeroFactura,f.num_ord_compra as OrdenDeCompra ,f.fecha as Fecha,
sum(fp.cantidad*fp.valor) as subtotal, f.valor as Total from factura f
inner join (cliente c, administrador a)
on(f.ruc_cliente=c.ruc_cliente and f.usuario_admin=a.usuario)
left join factura_producto fp
on f.numero_factura=fp.numero_factura
group by f.numero_factura);

drop view if exists vistaProdFactura;
create view vistaProdFactura as (
select f.numero_factura as NumeroFactura,p.codigo_producto as CodigoProducto, p.descripcion as Producto,
fp.valor as PrecioUnitario,fp.cantidad as Cantidad, (fp.valor*fp.cantidad) as TotalUS$ from factura f
inner join (factura_producto fp,producto p)
on(fp.numero_factura=f.numero_factura and p.codigo_producto=fp.codigo_producto)
);

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