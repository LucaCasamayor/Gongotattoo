-- =============================
-- Admin
-- =============================
-- Contraseña hasheada: "admin123"
INSERT INTO adminuser (username, password)
VALUES ('admin', '$2a$10$Dow1qkIhO4u1pJxkm4sI/O4y/2B3fWb0T9w0sM7xq6L5ZcFQx3PjS');

-- =============================
-- Media
-- =============================
INSERT INTO media (name, url, uploaded_at, type)
VALUES
    ('Tatuaje Estrella', 'https://miurl.com/estrella.jpg', CURRENT_TIMESTAMP, 'imagen'),
    ('Tatuaje Dragón', 'https://miurl.com/dragon.jpg', CURRENT_TIMESTAMP, 'imagen'),
    ('Tatuaje Tribal', 'https://miurl.com/tribal.jpg', CURRENT_TIMESTAMP, 'imagen'),
    ('Flyer Promocional 1', 'https://miurl.com/flyer1.jpg', CURRENT_TIMESTAMP, 'flyer'),
    ('Flyer Promocional 2', 'https://miurl.com/flyer2.jpg', CURRENT_TIMESTAMP, 'flyer');
