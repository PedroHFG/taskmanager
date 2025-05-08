--Insert tb_user
INSERT INTO tb_user (name, email, password, created_At) VALUES ('Alice Santos', 'alice@example.com', '$2a$10$CSoBmOaDrtnoL4jqTlsAYuKxuVnZErumgZPAes5xuL8zob9FK3fuu', NOW()); --123456
INSERT INTO tb_user (name, email, password, created_At) VALUES ('Bruno Oliveira', 'bruno@example.com', '$2a$10$U/kOxMEzPD2QHvKicm.t6ucMRuoGAo7JgQeQ8CtvGHQqF6mpktN0S', NOW()); -- senha123
INSERT INTO tb_user (name, email, password, created_At) VALUES ('Carla Mendes', 'carla@example.com', '$2a$10$UzvcB9v/GLKxzWCKvEiXOOcf7rI0Xn3JCUZtNB9u6P3zVeUdup176', NOW()); --abc123
INSERT INTO tb_user (name, email, password, created_At) VALUES ('Daniel Souza', 'daniel@example.com', '$2a$10$HIlrI64vqSCD/iixQKZRsuEcgPc038.YwENiubMbcdnJMxNCmy./2', NOW()); --pass2024
INSERT INTO tb_user (name, email, password, created_At) VALUES ('Fernanda Lima', 'fernanda@example.com', '$2a$10$nhNMTpqmLQdtb6V5OaHNs.twDZbF0JGeN2h1l2y5XMXAN1mqqZsE2', NOW()); --minhasenha

-- Insert tb_role
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');

-- Insert tb_user_role
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 2);


-- Tarefas do usuário 1 (Alice Santos)
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Comprar mantimentos', 'Ir ao mercado e comprar itens da lista.', '2025-05-10', 1, 0, 1, NOW());
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Estudar Spring Boot', 'Revisar autenticação com JWT.', '2025-05-08', 2, 1, 1, NOW());

-- Tarefas do usuário 2 (Bruno Oliveira)
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Enviar relatório', 'Enviar relatório mensal ao gerente.', '2025-05-05', 2, 4, 2, NOW());
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Agendar reunião', 'Marcar reunião com a equipe de vendas.', '2025-05-09', 0, 0, 2, NOW());

-- Tarefas do usuário 3 (Carla Mendes)
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Atualizar site', 'Publicar nova seção no site institucional.', '2025-05-12', 1, 1, 3, NOW());

-- Tarefas do usuário 4 (Daniel Souza)
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Backup de dados', 'Realizar backup completo do sistema.', '2025-05-07', 3, 2, 4, NOW());

-- Tarefas do usuário 5 (Fernanda Lima)
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Renovar domínio', 'Renovar o domínio do site por mais 1 ano.', '2025-05-11', 2, 3, 5, NOW());
INSERT INTO tb_task (title, description, deadline, priority, status, user_id, created_At) VALUES ('Planejar campanha', 'Elaborar estratégia para campanha de marketing.', '2025-05-15', 3, 0, 5, NOW());
