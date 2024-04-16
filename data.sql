
CREATE TABLE comprador (
idComprador int NOT NULL,
Nombre varchar(255) NOT NULL,
Apellidos varchar(255) NOT NULL,
Teléfono varchar(9) NOT NULL,
Email varchar(255) NOT NULL,
Contraseña varchar(255) NOT NULL,
Ubicación varchar(255) NOT NULL,
Vulnerable tinyint(1) NOT NULL DEFAULT 0,
PRIMARY KEY (idComprador),
UNIQUE KEY idComprador_UNIQUE (idComprador),
UNIQUE KEY Teléfono_UNIQUE (Teléfono),
UNIQUE KEY Email_UNIQUE (Email),
CHECK (Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
CHECK (Contraseña REGEXP '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$'),
CHECK (Teléfono REGEXP '^(?:(?:\+|00)34)?[6-7]\d{8}$')
);

CREATE TABLE vendedores (
idVendedor int NOT NULL,
Nombre varchar(255) NOT NULL,
Apellidos varchar(255) NOT NULL,
Teléfono varchar(9) NOT NULL,
Email varchar(255) NOT NULL,
Contraseña varchar(255) NOT NULL,
Ubicación varchar(255) NOT NULL,
idComercio int NOT NULL,
PRIMARY KEY (idVendedor),
FOREIGN KEY (idComercio) REFERENCES comercios (idComercio),
UNIQUE KEY idVendedor_UNIQUE (idVendedor),
UNIQUE KEY Teléfono_UNIQUE (Teléfono),
UNIQUE KEY Email_UNIQUE (Email),
UNIQUE KEY idComercio_UNIQUE (idComercio),
CHECK (Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
CHECK (Contraseña REGEXP '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$'),
CHECK (Teléfono REGEXP '^(?:(?:\+|00)34)?[6-7]\d{8}$')
);

CREATE TABLE voluntarios (
idVoluntario int NOT NULL,
Nombre varchar(255) NOT NULL,
Apellidos varchar(255) NOT NULL,
Teléfono varchar(9) NOT NULL,
Email varchar(255) NOT NULL,
Contraseña varchar(255) NOT NULL,
Ubicación varchar(255) NOT NULL,
PRIMARY KEY (idVoluntario),
UNIQUE KEY idVoluntario_UNIQUE (idVoluntario),
UNIQUE KEY Teléfono_UNIQUE (Teléfono),
UNIQUE KEY Email_UNIQUE (Email),
CHECK (Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
CHECK (Contraseña REGEXP '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$'),
CHECK (Teléfono REGEXP '^(?:(?:\+|00)34)?[6-7]\d{8}$')
);

CREATE TABLE comercios (
idComercio int NOT NULL,
Nombre varchar(255) NOT NULL,
Ubicación varchar(255) NOT NULL,
Horario time NOT NULL,
Fotografía blob NOT NULL,
PRIMARY KEY (idComercio),
UNIQUE KEY idComercio_UNIQUE (idComercio),
UNIQUE KEY Nombre_UNIQUE (Nombre)
);

CREATE TABLE productos (
idProducto int NOT NULL,
Nombre varchar(255) NOT NULL,
Descripción varchar(255) NOT NULL,
Precio decimal(10, 2) NOT NULL,
Fotografía blob NOT NULL,
idComercio int NOT NULL,
PRIMARY KEY (idProducto),
FOREIGN KEY (idComercio) REFERENCES comercios (idComercio),
UNIQUE KEY idProducto_UNIQUE (idProducto),
UNIQUE KEY idComercio_UNIQUE (idComercio)
);

CREATE TABLE pedidos (
idPedido int NOT NULL,
Fecha_Realización datetime NOT NULL,
Fecha_Recogida datetime NOT NULL,
idComprador int NOT NULL,
idVoluntario int NOT NULL,
PRIMARY KEY (idPedido),
FOREIGN KEY (idComprador) REFERENCES comprador (idComprador),
FOREIGN KEY (idVoluntario) REFERENCES voluntarios (idVoluntario),
UNIQUE KEY idPedido_UNIQUE (idPedido),
UNIQUE KEY idComprador_UNIQUE (idComprador),
UNIQUE KEY idVoluntario_UNIQUE (idVoluntario)
);
