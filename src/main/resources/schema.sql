INSERT INTO tb_usuario (username, password, role)
VALUES
    ('admin', '$2a$10$ngf9CWHymGECeDPQXsj05.jJK7T3co1BqeI.EC4elIjP8VuFHwLsm', 'ADMIN'),
    ('joao', '$2a$10$uKkoztiaXwUFhSgwng9v1e7i5SuK6qRYNIsRWYqfaPWz19y1R4sgm', 'USER'),
    ('maria', '$2a$10$B8sjftZQ5MxI8XckfHgZfedPcKZbnjxzwvmdK0.WqAvoR1TzFWZoy', 'USER');
INSERT INTO tb_motorista (nome, cnh)
VALUES
    ('Carlos Silva', '12345678901'),
    ('João Oliveira', '23456789012'),
    ('Marcos Santos', '34567890123'),
    ('Ana Souza', '45678901234');
INSERT INTO tb_entrega (descricao, status, motorista_id)
VALUES
    ('Entrega de peças industriais', 'PENDENTE', 1),
    ('Entrega de componentes eletrônicos', 'EM_ANDAMENTO', 2),
    ('Entrega de materiais de escritório', 'CONCLUIDA', 1),
    ('Entrega de equipamentos', 'PENDENTE', 3),
    ('Entrega de documentos', 'CONCLUIDA', 4);