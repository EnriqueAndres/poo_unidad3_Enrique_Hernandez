CREATE DATABASE IF NOT EXISTS speedfast_db;
USE speedfast_db;

DROP TABLE IF EXISTS entrega;
DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS repartidor;

CREATE TABLE repartidor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(100) NOT NULL,
    tipo ENUM('COMIDA', 'ENCOMIENDA', 'EXPRESS') NOT NULL,
    estado ENUM('PENDIENTE', 'EN_REPARTO', 'ENTREGADO') NOT NULL
);

CREATE TABLE entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_repartidor INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id) ON DELETE CASCADE,
    FOREIGN KEY (id_repartidor) REFERENCES repartidor(id) ON DELETE CASCADE
);


INSERT INTO repartidor (nombre) VALUES 
('Enrique Hernandez'),
('Pilar Godoy'),
('Pepito Barria');

INSERT INTO pedido (direccion, tipo, estado) VALUES 
('Av. Providencia 1234, Depto 502', 'COMIDA', 'ENTREGADO'),
('Calle Los Leones 456', 'ENCOMIENDA', 'EN_REPARTO'),
('Av. Vitacura 8900', 'EXPRESS', 'PENDIENTE');

INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES 
(1, 1, '2026-10-01', '14:30:00'),
(2, 2, '2026-10-03', '11:15:00');