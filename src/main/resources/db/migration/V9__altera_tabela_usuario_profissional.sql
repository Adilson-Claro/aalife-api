ALTER TABLE usuario_profissional
    ADD CONSTRAINT fk_endereco
        FOREIGN KEY (endereco_id)
            REFERENCES endereco(id);