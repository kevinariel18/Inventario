
DROP TABLE IF EXISTS categorias, categorias_unidad_medida, productos, proveedores, tipo_documentos, cabecera_pedidos, estados_pedido, detalle_pedido, historial_stock, cabecera_ventas, detalle_ventas;


CREATE TABLE categorias (
   codigo_cat SERIAL NOT NULL PRIMARY KEY, 
   nombre VARCHAR(100) NOT NULL,
   categoria_padre INT,
   CONSTRAINT categorias_fk FOREIGN KEY (categoria_padre) REFERENCES categorias(codigo_cat)  -- Relación con categoría padre
);


INSERT INTO categorias (nombre, categoria_padre)
VALUES 
    ('materia prima', NULL),
    ('proteina', 1),
    ('salsas', 1),
    ('punto de venta', NULL),
    ('bebidas', 4),
    ('con alcohol', 5),
    ('sin alcohol', 5);


CREATE TABLE unidades_medida (
    id SERIAL PRIMARY KEY,  
    codigo_udm CHAR(1) NOT NULL,  
    descripcion VARCHAR(100) NOT NULL
);


INSERT INTO unidades_medida (codigo_udm, descripcion) VALUES 
    ('V', 'MILILITROS'),
    ('V', 'LITROS'),
    ('U', 'UNIDAD'),
    ('U', 'DOCENA'),
    ('P', 'GRAMOS'),
    ('P', 'KILOGRAMOS'),
    ('P', 'LIBRAS');


CREATE TABLE categorias_unidad_medida (
    id SERIAL PRIMARY KEY,
    codigo_udm CHAR(1) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    CONSTRAINT fk_codigo_udm FOREIGN KEY (codigo_udm) REFERENCES unidades_medida(codigo_udm)  -- Referencia a unidades_medida
);


INSERT INTO categorias_unidad_medida (codigo_udm, nombre) VALUES 
    ('U', 'UNIDADES'),
    ('V', 'VOLUMEN'),
    ('P', 'PESO');


CREATE TABLE productos (
    codigo_prod CHAR(1) NOT NULL PRIMARY KEY,   
    nombre VARCHAR(100) NOT NULL,                
    categoria_udm CHAR(1) NOT NULL,              
    precio_venta MONEY,                         
    tiene_iva BOOLEAN NOT NULL,                 
    coste MONEY NOT NULL,                       
    categoria INT NOT NULL,                    
    stock INT NOT NULL,                         
    CONSTRAINT fk_categoria_udm FOREIGN KEY (categoria_udm) REFERENCES unidades_medida(codigo_udm),   -- Relación con unidades_medida
    CONSTRAINT fk_categoria FOREIGN KEY (categoria) REFERENCES categorias(codigo_cat)  -- Relación con categorias
);


INSERT INTO productos (codigo_prod, nombre, categoria_udm, precio_venta, tiene_iva, coste, categoria, stock)
VALUES 
    ('1', 'COCA COLA PEQUEÑA', 'U', 0.5804, TRUE, 0.3729, 7, 105),
    ('2', 'SALSA DE TOMATE', 'P', 0.95, TRUE, 0.8736, 7, 0),
    ('3', 'MOSTAZA', 'P', 0.95, TRUE, 0.89, 7, 0),
    ('4', 'FUZE TEA', 'U', 0.8, TRUE, 0.7, 3, 49);


CREATE TABLE proveedores (
    identificador VARCHAR(15) NOT NULL PRIMARY KEY,  
    tipo_documento CHAR(1) NOT NULL,                 
    nombre VARCHAR(100) NOT NULL,                    
    telefono VARCHAR(10),                           
    correo VARCHAR(100),                            
    direccion VARCHAR(225)                          
);


INSERT INTO proveedores (identificador, tipo_documento, nombre, telefono, correo, direccion)
VALUES 
    ('1792285747', 'C', 'SANTIAGO MOSQUERA', '0923292006', 'SANTY@proveedora.com', 'CUMBAYORK'),
    ('1792285747001', 'R', 'SNACKS SA', '0992920303', 'SANTY@proveedorb.com', 'LA TOLA');


CREATE TABLE tipo_documentos (
    codigo_documento CHAR(1) NOT NULL PRIMARY KEY,  -- Clave primaria
    descripcion VARCHAR(100)                         
);


INSERT INTO tipo_documentos (codigo_documento, descripcion)
VALUES 
    ('C', 'Cédula'),
    ('R', 'RUC');


CREATE TABLE cabecera_pedidos (
    numero VARCHAR(5) NOT NULL PRIMARY KEY,    
    proveedor VARCHAR(15),                      
    fecha DATE NOT NULL,                       
    estado CHAR(1) NOT NULL,                   
    CONSTRAINT fk_proveedor FOREIGN KEY (proveedor) REFERENCES proveedores(identificador)  -- Relación con proveedores
);


INSERT INTO cabecera_pedidos (numero, proveedor, fecha, estado) 
VALUES 
    ('1', '1792285747', '2023-11-20', 'R'),
    ('2', '1792285747', '2023-11-20', 'R');


CREATE TABLE estados_pedido (
    codigo CHAR(1) NOT NULL PRIMARY KEY,   -- Clave primaria
    descripcion VARCHAR(100)                
);


INSERT INTO estados_pedido (codigo, descripcion)
VALUES 
    ('S', 'SOLICITADO'),
    ('R', 'RECIBIDO');


CREATE TABLE detalle_pedido (
    codigo SERIAL PRIMARY KEY,               
    cabecera_pedido VARCHAR(5) NOT NULL,    
    producto CHAR(1) NOT NULL,              
    cantidad INTEGER NOT NULL,              
    subtotal DECIMAL(10,2) NOT NULL,        
    cantidad_recibida INTEGER NOT NULL,     
    CONSTRAINT fk_cabecera_pedido FOREIGN KEY (cabecera_pedido) REFERENCES cabecera_pedidos(numero),  -- Relación con cabecera_pedido
    CONSTRAINT fk_producto FOREIGN KEY (producto) REFERENCES productos(codigo_prod)  -- Relación con productos
);


INSERT INTO detalle_pedido (cabecera_pedido, producto, cantidad, subtotal, cantidad_recibida)
VALUES
    ('1', '1', 100, 37.29, 100),
    ('2', '4', 50, 11.8, 50),
    ('2', '1', 10, 3.73, 10);


CREATE TABLE historial_stock (
    codigo CHAR(1) NOT NULL PRIMARY KEY,             
    fecha TIMESTAMP NOT NULL,                       
    referencia VARCHAR(100) NOT NULL,              
    producto CHAR(1) NOT NULL,                    
    cantidad INTEGER,                             
    CONSTRAINT fk_historial_producto FOREIGN KEY (producto) REFERENCES productos(codigo_prod)  -- Relación con productos
);


INSERT INTO historial_stock (codigo, fecha, referencia, producto, cantidad)
VALUES
    ('1', '2023-11-20 08:00:00', 'PEDIDO 1', '1', 100),
    ('2', '2023-11-20 09:30:00', 'PEDIDO 1', '4', 50),
    ('3', '2023-11-20 11:00:00', 'PEDIDO 2', '1', 10),
    ('4', '2023-11-20 11:00:00', 'VENTA 1', '1', -5),
    ('5', '2023-11-20 11:00:00', 'VENTA 1', '4', 1);


CREATE TABLE cabecera_ventas (
    codigo CHAR(1) NOT NULL PRIMARY KEY,           
    fecha TIMESTAMP NOT NULL                     
);


INSERT INTO cabecera_ventas (codigo, fecha)
VALUES 
    ('1', '2023-11-20 12:00:00'),
    ('2', '2023-11-20 12:30:00');

CREATE TABLE detalle_ventas (
    codigo SERIAL PRIMARY KEY,                
    cabecera_ventas CHAR(1) NOT NULL,        
    producto CHAR(1) NOT NULL,              
    cantidad INT NOT NULL,                 
    precio_venta DECIMAL(10,2) NOT NULL,    
    sub_total DECIMAL(10,2) NOT NULL,       
    sub_total_iva DECIMAL(10,2) NOT NULL,   
    CONSTRAINT fk_cabecera_ventas FOREIGN KEY (cabecera_ventas) REFERENCES cabecera_ventas(codigo),
    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto) REFERENCES productos(codigo_prod)
);


INSERT INTO detalle_ventas (cabecera_ventas, producto, cantidad, precio_venta, sub_total, sub_total_iva)
VALUES
    ('1', '1', 5, 0.58, 2.91, 3.3);



SELECT * FROM categorias;


SELECT * FROM unidades_medida;


SELECT * FROM categorias_unidad_medida;


SELECT * FROM productos;


SELECT * FROM proveedores;


SELECT * FROM tipo_documentos;


SELECT * FROM cabecera_pedidos;


SELECT * FROM estados_pedido;


SELECT * FROM detalle_pedido;


SELECT * FROM historial_stock;


SELECT * FROM cabecera_ventas;


SELECT * FROM detalle_ventas;
	
