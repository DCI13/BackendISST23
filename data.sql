CREATE TABLE barriocovid.comprador (
    idComprador int NOT NULL,
    Nombre varchar(255) NOT NULL,
    Apellidos varchar(255) NOT NULL,
    Telefono varchar(9) NOT NULL,
    Email varchar(255) NOT NULL,
    Contraseña varchar(255) NOT NULL,
    Ubicacion varchar(255) NOT NULL,
    Vulnerable tinyint(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (idComprador),
    UNIQUE KEY idComprador_UNIQUE (idComprador),
    UNIQUE KEY Telefono_UNIQUE (Telefono),
    UNIQUE KEY Email_UNIQUE (Email),
    CHECK (Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
    CHECK (Contraseña REGEXP '^(?=.[a-z])(?=.[A-Z])(?=.[0-9])(?=.[!@#$%^&*]).{8,}$'),
    CHECK (Telefono REGEXP '^(?:(?:\\+|00)34)?[6-7]\\d{8}$')
);

CREATE TABLE barriocovid.vendedores (
    idVendedor int NOT NULL,
    Nombre varchar(255) NOT NULL,
    Apellidos varchar(255) NOT NULL,
    Telefono varchar(9) NOT NULL,
    Email varchar(255) NOT NULL,
    Contraseña varchar(255) NOT NULL,
    Ubicacion varchar(255) NOT NULL,
    idComercio int NOT NULL,
    PRIMARY KEY (idVendedor),
    FOREIGN KEY (idComercio) REFERENCES barriocovid.comercios (idComercio),
    UNIQUE KEY idVendedor_UNIQUE (idVendedor),
    UNIQUE KEY Telefono_UNIQUE (Telefono),
    UNIQUE KEY Email_UNIQUE (Email),
    UNIQUE KEY idComercio_UNIQUE (idComercio),
    CHECK (Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
    CHECK (Contraseña REGEXP '^(?=.[a-z])(?=.[A-Z])(?=.[0-9])(?=.[!@#$%^&*]).{8,}$'),
    CHECK (Telefono REGEXP '^(?:(?:\\+|00)34)?[6-7]\\d{8}$')
);

CREATE TABLE barriocovid.voluntarios (
    idVoluntario int NOT NULL,
    Nombre varchar(255) NOT NULL,
    Apellidos varchar(255) NOT NULL,
    Telefono varchar(9) NOT NULL,
    Email varchar(255) NOT NULL,
    Contraseña varchar(255) NOT NULL,
    Ubicacion varchar(255) NOT NULL,
    PRIMARY KEY (idVoluntario),
    UNIQUE KEY idVoluntario_UNIQUE (idVoluntario),
    UNIQUE KEY Telefono_UNIQUE (Telefono),
    UNIQUE KEY Email_UNIQUE (Email),
    CHECK (Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]));
