CREATE TABLE paciente (

                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          run VARCHAR(20),

                          nombre VARCHAR(100),

                          apellido VARCHAR(100),

                          correo VARCHAR(100),

                          fecha_nacimiento DATE
);