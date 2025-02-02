INSERT INTO usuario (nome, email, password) VALUES ('admin', 'admin@email.com', '$2a$12$dOsq70AzXkxJrIdFN5.33OjUAMYvx6Ljt0TT3yOy8V0JUAq9LyP1u');
INSERT INTO role (nome) VALUES ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 2);