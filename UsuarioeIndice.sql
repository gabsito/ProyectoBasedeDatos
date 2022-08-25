drop user if exists dsanti;
create user dsanti identified by "contra";
grant select on proyecto_kywy.* to dsanti;
grant insert on proyecto_kywy.* to dsanti;
grant update on proyecto_kywy.* to dsanti;
grant execute on procedure proyecto_kywy.login to dsanti;
flush privileges;
use proyecto_kywy;
create index nombre_producto on producto(descripcion);
