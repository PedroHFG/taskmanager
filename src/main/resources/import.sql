-- Inserir Roles
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');

-- Inserir Usuários
INSERT INTO tb_user (name, email, password) VALUES ('John Doe', 'john@example.com', '$2a$10$IzmtdcyaMHR5QpJ035RIZOaO.9YFCHJburwIxLd0tR.uzRUTt2rXK');
INSERT INTO tb_user (name, email, password) VALUES ('Jane Smith', 'jane@example.com', '$2a$10$IzmtdcyaMHR5QpJ035RIZOaO.9YFCHJburwIxLd0tR.uzRUTt2rXK');
INSERT INTO tb_user (name, email, password) VALUES ('Alice Brown', 'alice@example.com', '$2a$10$IzmtdcyaMHR5QpJ035RIZOaO.9YFCHJburwIxLd0tR.uzRUTt2rXK');

-- Inserir Associação Usuário-Role
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);

-- Inserir Tasks
INSERT INTO tb_task (title, description, status, due_date, user_id) VALUES ('Finalizar relatório de vendas', 'Elaborar relatório financeiro com dados de vendas do último trimestre.', 0, '2024-12-20', 3);
INSERT INTO tb_task (title, description, status, due_date, user_id) VALUES ('Revisar código do projeto X', 'Revisar o código da API para resolver problemas de performance.', 0, '2024-12-15', 1);
INSERT INTO tb_task (title, description, status, due_date, user_id) VALUES ('Reunião com a equipe', 'Organizar a reunião para discutir o progresso do projeto e definir próximos passos.', 1, '2024-12-01', 2);
INSERT INTO tb_task (title, description, status, due_date, user_id) VALUES ('Planejar a apresentação para o cliente', 'Criar slides de apresentação e ensaiar a fala para o cliente.', 1, '2024-11-30', 2);
INSERT INTO tb_task (title, description, status, due_date, user_id) VALUES ('Atualizar o sistema de gerenciamento', 'Verificar e aplicar as atualizações de segurança no sistema.', 0, '2024-12-30', 1);
