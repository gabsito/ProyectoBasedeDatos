use proyecto_kywy;
drop procedure if exists login;
select * from cliente;
Delimiter || 
create procedure login(in usuar varchar(10),in contra varchar(32),out bln boolean)
begin
	if (select count(contrasena) from administrador where usuario=usuar and contrasena=sha1(contra) group by (usuario))>0 then 
		set bln = true;
	else
		set bln = false;
	end if;
end;
|| Delimiter ;

call login ("dsanti","contra",@r);
select @r;
alter table orden_compra drop foreign key refcliente;
alter table orden_compra modify ruc_cliente char(13) ;
alter table orden_compra ADD CONSTRAINT refcliente FOREIGN KEY (ruc_cliente) REFERENCES cliente(ruc_cliente);