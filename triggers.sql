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