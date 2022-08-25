create database proyecto_kywy;
use proyecto_kywy;

create table if not exists cliente(
	ruc_cliente char(13) primary key,
    nombre varchar(80) not null,
    direccion varchar(120) not null,
    telefono varchar(14) not null
);

create table if not exists administrador(
	usuario varchar(10) primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null,
    cedula char(10) not null,
    contrasena varchar(32) not null	/*Para almacenar el hash de la contraseña*/
);

create table if not exists producto(
	codigo_producto char(10) primary key,
    descripcion varchar(40) not null,
    valor double not null
);

create table if not exists orden_compra(
	num_ord_compra int primary key auto_increment,
    descripcion varchar(150),
    fecha datetime not null default current_timestamp,
    ruc_cliente char(13) not null,
    constraint refcliente foreign key (ruc_cliente) references cliente(ruc_cliente)
);

create table if not exists orden_producto(
	num_ord_compra int,
    codigo_producto char(10),
    cantidad int not null,
    valor double not null,
    constraint primaria primary key (num_ord_compra,codigo_producto),
    constraint refprod foreign key (codigo_producto) references producto(codigo_producto),
    constraint refordcom foreign key (num_ord_compra) references orden_compra(num_ord_compra)
);

create table if not exists proforma(
	cod_proforma int primary key auto_increment,
    valor double,
    usuario_admin varchar(10) not null,
    constraint refadmin foreign key (usuario_admin) references administrador(usuario)
);

create table if not exists proforma_producto(
	cod_proforma int,
    codigo_producto char(10),
    cantidad int not null,
    constraint primaria primary key (cod_proforma,codigo_producto),
    constraint refprodu foreign key (codigo_producto) references producto(codigo_producto),
    constraint refprof foreign key (cod_proforma) references proforma(cod_proforma)
);

create table if not exists factura(
	numero_factura int primary key auto_increment,
    fecha datetime not null default current_timestamp,
    valor double,
    ruc_cliente char(13) not null,
    usuario_admin varchar(10) not null,
    num_ord_compra int not null,
    constraint ref_clie foreign key (ruc_cliente) references cliente(ruc_cliente),
    constraint ref_admin foreign key (usuario_admin) references administrador(usuario),
    constraint ref_ord_com foreign key (num_ord_compra) references orden_compra(num_ord_compra)
);

create table if not exists factura_producto(
	numero_factura int,
    codigo_producto char(10),
    cantidad int not null,
    valor double not null,
    constraint primaria primary key (numero_factura,codigo_producto),
    constraint refproduc foreign key (codigo_producto) references producto(codigo_producto),
    constraint reffactura foreign key (numero_factura) references factura(numero_factura)
);

create table if not exists cobro(
	codigo_cobro int primary key auto_increment,
    valor double,
    metodo_pago varchar(20),
    fecha datetime not null default current_timestamp,
    cod_factura int not null,
    usuario_admin varchar(10) not null,
    constraint ref_factura foreign key (cod_factura) references factura(numero_factura),
    constraint refadministrador foreign key (usuario_admin) references administrador(usuario)
    
);
