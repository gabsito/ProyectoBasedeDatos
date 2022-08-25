use proyecto_kywy;

insert into cliente(nombre,ruc_cliente,direccion,telefono) values 
("TECNOVA S.A.","0990032815001","Urdesa Central, Km. 2.5 Av. Las Monjas 10 y Av. Carlos Julio Arosemena, Edif. Hamburgo 4to Piso.","220-4000"),
("INDUSTRIAL PESQUERA SANTA PRISCILA S.A.","0991257721001","KM. 5.5 VIA A DAULE","600-5238"),
("FOREVER MUSIC S.A. FOREMUSIC","0991428534001","La Puntilla (Satélite/C.C. Los Arcos 335 piso 3 oficina 307","372-6620"),
("SOCIEDAD NACIONAL DE GALAPAGOS C.A.","0990033110001","Av. Las Exclusas Km. 3 1/2 vía hacienda La Josefina","259-1250"),
("FARMAYALA PHARMACEUTICAL COMPANY S.A. FPC","0990013314001","Vía Durán-Tambo Km 3K Industrial  Vehicular 1A07 y Vehicular","281-5910"),
("INDUSTRIAL Y COMERCIAL TRILEX C.A.","0990013160001","Km. 10 1/2 de la Vía Daule","211-0705"),
("COMERCIALIZADORA LEDESMA&LEDESMA AGROGRULED S.A.","0992900202001","Km. 8.5 via Babahoyo Jujan","263-6040"),
("INDEUREC S.A.","0991254374001","Durán  Via Durán Boliche Km. 2,5 Av. Principal S/N","281-5910"),
("ROBERT BOSCH SOCIEDAD ANONIMA ECUABOSCH","0992862467001","Av.Las Monjas #10 y Carlos Julio Arosemena","220-4000"),
("MAQUINARIAS HENRIQUES C.A.","0990010870001","Km. 6,5 Vía a Daule","501-0070");

insert into administrador(usuario,nombre,apellido,cedula,contrasena) values
("dsanti","David","Santistevan","0908893771","contra"),
("camat","Claudia","Amat","0911456606","contra"); /*Solo hay dos administradores*/

insert into producto(codigo_producto,descripcion,valor) values
("0000000001","CAMISETA TIPO POLO",6.70),
("0000000002","CAMISETA CUELLO REDONDO",5.50),
("0000000003","CHAQUETA",16.70),
("0000000004","CAMISA",9.99),
("0000000005","GORRA",1.20),
("0000000006","MANDIL",8.70),
("0000000007","TOALLA",3.50),
("0000000008","PANTALON",16.80),
("0000000009","OVEROL",10.00),
("0000000010","CHOMPA",12.50);

insert into orden_compra(fecha,ruc_cliente) values
('20170618',"0990032815001"),
('20180725',"0991257721001"),
('20181122',"0990013314001"),
('20190112',"0992900202001"),
('20190522',"0990010870001"),
('20190112',"0992862467001"),
('20180923',"0990033110001"),
('20190420',"0991428534001"),
('20181022',"0990032815001"),
('20190911',"0990033110001");

insert into orden_producto values
(6,"0000000001",12,6.7),
(7,"0000000002",12,5.5),
(8,"0000000010",6,13),
(2,"0000000001",45,6.5),
(2,"0000000004",120,9.99),
(3,"0000000007",100,3.5),
(4,"0000000006",5,9),
(4,"0000000008",10,16.5),
(5,"0000000008",2,16.8),
(5,"0000000009",7,10.3);

insert into factura(fecha,ruc_cliente,usuario_admin,num_ord_compra) values
('20171016',"0990032815001","camat",2),
("20191016","0990033110001","dsanti",1),
("20181002","0990033110001","camat",8),
("20190501","0991428534001","dsanti",9),
("20180731","0991257721001","dsanti",3),
("20190125","0992862467001","camat",7),
("20190528","0990010870001","camat",6),
("20181201","0990032815001","dsanti",4),
("20190129","0992900202001","dsanti",5),
('20181031',"0990032815001","dsanti",10);

insert into factura_producto values
(1,"0000000001",12,6.7),
(1,"0000000002",12,5.5),
(1,"0000000010",6,13),
(2,"0000000001",45,6.5),
(2,"0000000004",120,9.99),
(3,"0000000007",100,3.5),
(4,"0000000006",5,9),
(4,"0000000008",10,16.5),
(5,"0000000008",2,16.8),
(5,"0000000009",7,10.3);

insert into cobro(cod_factura,usuario_admin) values
(1,"dsanti"),
(2,"camat"),
(3,"dsanti"),
(4,"camat"),
(5,"dsanti"),
(6,"camat"),
(7,"dsanti"),
(8,"camat"),
(9,"dsanti"),
(10,"camat");

insert into proforma(usuario_admin) values
("dsanti"),
("dsanti"),
("camat"),
("dsanti"),
("camat"),
("camat"),
("camat"),
("dsanti"),
("camat"),
("dsanti");


insert into proforma_producto values
(1,"0000000001",12),
(7,"0000000002",1),
(6,"0000000010",61),
(2,"0000000001",54),
(2,"0000000004",120),
(3,"0000000007",111),
(4,"0000000006",53),
(8,"0000000008",15),
(5,"0000000008",21),
(9,"0000000009",70);

/*Se debe actualizar los valores de las facturas, los cobros y las ordenes de compra, calculando en funcion de las tablas con las que estan relacionadas*/