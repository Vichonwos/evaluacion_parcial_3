CREATE TABLE cita (

                      id BIGINT AUTO_INCREMENT PRIMARY KEY,

                      paciente_id BIGINT,

                      fecha VARCHAR(50),

                      motivo VARCHAR(100)
);