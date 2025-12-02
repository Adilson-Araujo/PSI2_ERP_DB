    --FINANCEIRO
CREATE TABLE despesa (
    id BIGSERIAL PRIMARY KEY,
    associado_id BIGINT,
    categoria_gasto VARCHAR(100),
    destinatario VARCHAR(100),
    valor_gasto DECIMAL(15,2),
    data_transacao TIMESTAMP,
    descricao_despesa VARCHAR(255)
);

    --NOTA FISCAL--
CREATE TABLE endereco (
  id BIGSERIAL PRIMARY KEY,
  estado VARCHAR(2),
  cidade VARCHAR(255),
  bairro VARCHAR(255),
  rua VARCHAR(255),
  numero INTEGER,
  cep VARCHAR(8)
);

CREATE TABLE cliente (
  id BIGSERIAL PRIMARY KEY,
  endereco_id BIGINT,
  nome_fantasia VARCHAR(255),
  razao_social VARCHAR(255),
  telefone VARCHAR(20),
  email VARCHAR(255),
  data_cadastro TIMESTAMP,
  ativo BOOL,
  cpf_cnpj VARCHAR(20)
);

CREATE TABLE associado (
  id BIGSERIAL PRIMARY KEY,
  endereco_id BIGINT,
  cnpj VARCHAR(18),
  razao_social VARCHAR(255),
  nome_fantasia VARCHAR(255),
  inscricao_estadual VARCHAR(20),
  inscricao_municipal VARCHAR(20),
  telefone VARCHAR(20),
  email VARCHAR(255),
  data_cadastrado TIMESTAMP,
  ativo BOOL
);

CREATE TABLE nota_fiscal_eletronica (
  id BIGSERIAL PRIMARY KEY,
  associado_id BIGINT,
  cliente_id BIGINT,
  chave_acesso VARCHAR(44),
  razao_social VARCHAR(255),
  data_emissao TIMESTAMP,
  valor_total DECIMAL(15,2),
  tipo_ambiente SMALLINT,
  tipo_operacao SMALLINT,
  tipo_forma_emissao SMALLINT,
  tipo_status_envio_sefaz SMALLINT,
  numero_protocolo SMALLINT,
  data_inclusao TIMESTAMP,
  ativo BOOL,
  numero_nota_fiscal VARCHAR(6),
  numero_serie VARCHAR(3),
  dados_adicionais TEXT,
  valor_frete DECIMAL(15,2)
);

CREATE TABLE nota_fiscal_item (
  id BIGSERIAL PRIMARY KEY,
  nota_fiscal_eletronica_id BIGINT,
  produto_id BIGINT,
  cfop VARCHAR(4),
  ncm VARCHAR(8),
  quantidade INTEGER,
  valor_unitario DECIMAL(15,2),
  valor_total DECIMAL(15,2)
);

CREATE TABLE nota_fiscal_xml (
  id BIGSERIAL PRIMARY KEY,
  hash TEXT,
  conteudo TEXT
);


    ---USUÁRIO--
CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    associado_id BIGINT,
    nome_usuario VARCHAR(100),
    senha VARCHAR(255),
    tipo_usuario SMALLINT,
    ativo BOOL
);

    --VENDAS--
CREATE TABLE projeto (
    id BIGSERIAL PRIMARY KEY,
    nome_projeto VARCHAR(200),
    data_criacao TIMESTAMP,
    data_final TIMESTAMP,
    orcamento DECIMAL
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    projeto_id BIGINT,
    associado_id BIGINT,
    data_criacao TIMESTAMP,
    status_pedido_id BIGINT,
    valor_total DECIMAL
);

CREATE TABLE status_pedido (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(100)
);

CREATE TABLE venda (
    id BIGSERIAL PRIMARY KEY,
    projeto_id BIGINT,
    associado_id BIGINT,
    pedido_id BIGINT,
    data_compra TIMESTAMP,
    valor_total DECIMAL,
    data_entrega TIMESTAMP,
    forma_pagamento_id BIGINT
);

CREATE TABLE forma_pagamento (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(100)
);

CREATE TABLE item_pedido (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT,
    produto_id BIGINT,
    quantidade_total DECIMAL,
    valor_unitario DECIMAL,
    valor_total DECIMAL
);

CREATE TABLE associado_item_pedido (
    id BIGSERIAL PRIMARY KEY,
    associado_id BIGINT,
    item_pedido_id BIGINT,
    quantidade_atribuida DECIMAL
);

    --PRODUÇÃO--
CREATE TABLE ordem_producao (
  id BIGSERIAL PRIMARY KEY,
  plano_id INTEGER,
  Especie_id BIGINT,
  Talhao_id BIGINT,
  nome_plano VARCHAR(255),
  descricao TEXT,
  data_inicio TIMESTAMP,
  data_fim TIMESTAMP,
  observacoes TEXT,
  area_cultivo DECIMAL,
  data_execucao DATE,
  quantidade_Kg FLOAT,
  status VARCHAR(50) DEFAULT 'em_execucao'
);

CREATE TABLE registrar_problema (
  id BIGSERIAL PRIMARY KEY,
  ordem_producao_id BIGINT, 
  tipo_problema_id BIGINT,
  quantidade_afetada INT,
  data_problema TIMESTAMP,
  observacoes VARCHAR(255)
);

CREATE TABLE tipo_problema (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(100)
);
    
    --PLANEJAMENTO--
CREATE TABLE area (
    id BIGSERIAL PRIMARY KEY,
    ativo BOOLEAN DEFAULT TRUE,
    associado_id BIGINT,
    nome VARCHAR(255),
    area_total DECIMAL,
    area_utilizada DECIMAL,
    ph DECIMAL
);

CREATE TABLE talhao (
    id BIGSERIAL PRIMARY KEY,
    ativo BOOLEAN DEFAULT TRUE,
    Area_id BIGINT,
    nome VARCHAR(255),
    area_talhao DECIMAL,
    observacoes TEXT,
    status VARCHAR(50)
);

CREATE TABLE plano (
    id BIGSERIAL PRIMARY KEY,
    ativo BOOLEAN DEFAULT TRUE,
    Especie_id BIGINT,
    Talhao_id BIGINT,
    nome_plano VARCHAR(255),
    descricao TEXT,
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    observacoes TEXT,
    area_cultivo DECIMAL
);

CREATE TABLE canteiro (
    id BIGSERIAL PRIMARY KEY,
    ativo BOOLEAN DEFAULT TRUE,
    ordem_producao_id BIGINT,
    nome VARCHAR(255),
    area_canteiro_m2 DECIMAL,
    observacoes TEXT,
    kg_gerados DECIMAL,
    status VARCHAR(50) DEFAULT 'crescendo'
);

CREATE TABLE atividade (
    id BIGSERIAL PRIMARY KEY,
    ativo BOOLEAN DEFAULT TRUE,
    nome_atividade VARCHAR(255),
    descricao TEXT,
    observacoes TEXT,
    status VARCHAR(50)
);

CREATE TABLE atividade_canteiro (
    id BIGSERIAL PRIMARY KEY,
    Canteiro_id BIGINT,
    ativo BOOLEAN DEFAULT TRUE,
    Atividade_id BIGINT,
    tempo_gasto_horas DECIMAL,
    data_atividade TIMESTAMP
);

CREATE TABLE material (
    id BIGSERIAL PRIMARY KEY,
    ativo BOOLEAN DEFAULT TRUE,
    associado_id BIGINT,
    nome VARCHAR(255),
    quantidade DECIMAL,
    unidade_medida VARCHAR(50)
);

CREATE TABLE atividade_has_Material (
    Material_id BIGINT,
    ativo BOOLEAN DEFAULT TRUE,
    Atividade_id BIGINT,
    quantidade_utilizada DECIMAL
);
    --ESTOQUE--

CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    deletado BOOL
);

CREATE TABLE especie (
    id BIGSERIAL PRIMARY KEY,
    categoria_id BIGINT,
    nome VARCHAR(100),
    descricao VARCHAR(255),
    tempo_colheita INTEGER,
    rendimento_kg_m2 DECIMAL,
    deletado BOOL
);

CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    especie_id BIGINT,
    nome VARCHAR(100),
    descricao VARCHAR(255),
    deletado BOOL
);

CREATE TABLE armazem (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    endereco_id BIGINT,
    deletado BOOL
);

CREATE TABLE estoque_atual (
    id BIGSERIAL PRIMARY KEY,
    associado_id BIGINT,
    produto_id BIGINT,
    armazem_id BIGINT,
    quantidade DECIMAL(10,2)
);

CREATE TABLE preco_ppa (
    id BIGSERIAL PRIMARY KEY,
    data_inicio TIMESTAMP,
    especie_id BIGINT,
    data_final  TIMESTAMP,
    valor DECIMAL(10,2)
);

CREATE TABLE tipo (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE origem (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE movimentacao (
    id BIGSERIAL PRIMARY KEY,
    tipo_id BIGINT,
    origem_id BIGINT,
    produto_id BIGINT,
    armazem_id BIGINT,
    quantidade DECIMAL(10,2),
    data_movimento TIMESTAMP,
    deletado BOOL
);

-- FINANCEIRO
ALTER TABLE despesa ADD CONSTRAINT fk_associado_despesa
	FOREIGN KEY (associado_id) REFERENCES associado(id);
	
ALTER TABLE despesa
	ALTER COLUMN destinatario SET NOT NULL;

ALTER TABLE despesa
	ALTER COLUMN valor_gasto SET NOT NULL;

ALTER TABLE despesa
	ALTER COLUMN data_transacao SET NOT NULL;

ALTER TABLE despesa
	ALTER COLUMN categoria_gasto SET NOT NULL;

-- VENDAS
ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_projeto
    FOREIGN KEY (projeto_id) REFERENCES projeto (id);

ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_associado
    FOREIGN KEY (associado_id) REFERENCES associado (id);

ALTER TABLE venda
    ADD CONSTRAINT fk_venda_pedido
    FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE venda
    ADD CONSTRAINT fk_venda_projeto
    FOREIGN KEY (projeto_id) REFERENCES projeto (id);

ALTER TABLE venda
    ADD CONSTRAINT fk_venda_associado
    FOREIGN KEY (associado_id) REFERENCES associado (id);

ALTER TABLE item_pedido
    ADD CONSTRAINT fk_item_pedido_pedido
    FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE item_pedido
    ADD CONSTRAINT fk_item_pedido_produto
    FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE associado_item_pedido
    ADD CONSTRAINT fk_associado_item_pedido_item_pedido
    FOREIGN KEY (item_pedido_id) REFERENCES item_pedido (id);

ALTER TABLE associado_item_pedido
    ADD CONSTRAINT fk_associado_item_pedido_associado
    FOREIGN KEY (associado_id) REFERENCES associado (id);

-- PLANEJAMENTO
-- Não precisa mais das chaves primárias compostas, pois as tabelas têm ID próprio
-- As foreign keys garantem a integridade referencial

-- area constraints
ALTER TABLE area ADD CONSTRAINT fk_area_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- talhao constraints
ALTER TABLE talhao ADD CONSTRAINT fk_talhao_area
    FOREIGN KEY (area_id) REFERENCES area(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- plano constraints
ALTER TABLE plano ADD CONSTRAINT fk_plano_talhao
    FOREIGN KEY (talhao_id) REFERENCES talhao(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE plano ADD CONSTRAINT fk_plano_especie
    FOREIGN KEY (Especie_id) REFERENCES especie(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- canteiro constraints
ALTER TABLE canteiro ADD CONSTRAINT fk_canteiro_ordem_producao
    FOREIGN KEY (ordem_producao_id) REFERENCES ordem_producao(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- canteiro_has_atividade constraints
ALTER TABLE atividade_canteiro ADD CONSTRAINT fk_canteiro_atividade_canteiro
    FOREIGN KEY (canteiro_id) REFERENCES canteiro(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE atividade_canteiro ADD CONSTRAINT fk_canteiro_atividade_atividade
    FOREIGN KEY (atividade_id) REFERENCES atividade(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- material constraints
ALTER TABLE material ADD CONSTRAINT fk_material_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- atividade_has_material constraints
ALTER TABLE atividade_has_material ADD CONSTRAINT fk_atividade_material_material
    FOREIGN KEY (material_id) REFERENCES material(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE atividade_has_material ADD CONSTRAINT fk_atividade_material_atividade
    FOREIGN KEY (atividade_id) REFERENCES atividade(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- ESTOQUE
ALTER TABLE especie
ADD CONSTRAINT fk_especie_categoria
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
ALTER COLUMN deletado 
    SET DEFAULT FALSE,
ALTER COLUMN deletado
    SET NOT NULL;

ALTER TABLE produto
ADD CONSTRAINT fk_produto_especie
    FOREIGN KEY (especie_id) REFERENCES especie(id),
ALTER COLUMN deletado 
    SET DEFAULT FALSE,
ALTER COLUMN deletado
    SET NOT NULL;

ALTER TABLE armazem
ALTER COLUMN deletado 
    SET DEFAULT FALSE,
ALTER COLUMN deletado
    SET NOT NULL;

ALTER TABLE categoria
ALTER COLUMN deletado 
    SET DEFAULT FALSE,
ALTER COLUMN deletado
    SET NOT NULL;

ALTER TABLE estoque_atual
ADD CONSTRAINT fk_estoque_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id),
ADD CONSTRAINT fk_estoque_produto
    FOREIGN KEY (produto_id) REFERENCES produto(id),
ADD CONSTRAINT fk_estoque_armazem
    FOREIGN KEY (armazem_id) REFERENCES armazem(id);

ALTER TABLE preco_ppa
ADD CONSTRAINT fk_preco_ppa_especie
    FOREIGN KEY (especie_id) REFERENCES especie(id);

-- Adicionar coluna associado_id na tabela movimentacao
ALTER TABLE movimentacao
ADD COLUMN associado_id BIGINT;

ALTER TABLE movimentacao
ADD CONSTRAINT fk_movimentacao_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id);

ALTER TABLE movimentacao
ADD CONSTRAINT fk_movimentacao_tipo
    FOREIGN KEY (tipo_id) REFERENCES tipo(id),
ADD CONSTRAINT fk_movimentacao_origem
    FOREIGN KEY (origem_id) REFERENCES origem(id),
ADD CONSTRAINT fk_movimentacao_produto
    FOREIGN KEY (produto_id) REFERENCES produto(id),
ADD CONSTRAINT fk_movimentacao_armazem
    FOREIGN KEY (armazem_id) REFERENCES armazem(id),
ALTER COLUMN deletado 
    SET DEFAULT FALSE,
ALTER COLUMN deletado
    SET NOT NULL;

-- PRODUCAO

-- Tabela ordem_producao -> referencia Plano
ALTER TABLE ordem_producao
ADD CONSTRAINT fk_ordem_producao_plano
FOREIGN KEY (Plano_id)
REFERENCES Plano(id);

ALTER TABLE ordem_producao
ADD CONSTRAINT fk_ordem_especie
FOREIGN KEY (Especie_id)
REFERENCES especie(id);

ALTER TABLE ordem_producao
ADD CONSTRAINT fk_ordem_talhao
FOREIGN KEY (Talhao_id)
REFERENCES talhao(id);

-- Tabela registrar_problema -> referencia ordem_producao
ALTER TABLE registrar_problema
ADD CONSTRAINT fk_registrar_problema_ordem
FOREIGN KEY (ordem_producao_id)
REFERENCES ordem_producao(id);

-- registrar_problema -> tipo_problema(id)
ALTER TABLE registrar_problema
ADD CONSTRAINT fk_registrar_problema_tipo
FOREIGN KEY (tipo_problema_id)
REFERENCES tipo_problema(id);

-- NOTA FISCAL
ALTER TABLE cliente
  ADD CONSTRAINT fk_endereco_cliente
  FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE associado
  ADD CONSTRAINT fk_endereco_associado
  FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE nota_fiscal_eletronica
  ADD CONSTRAINT fk_associado_nota_fiscal_eletronica
  FOREIGN KEY (associado_id) REFERENCES associado(id);

ALTER TABLE nota_fiscal_eletronica
  ADD CONSTRAINT fk_cliente_nota_fiscal_eletronica
  FOREIGN KEY (cliente_id) REFERENCES cliente(id);

ALTER TABLE nota_fiscal_item
  ADD CONSTRAINT fk_nota_fiscal_eletronica_nota_fiscal_item
  FOREIGN KEY (nota_fiscal_eletronica_id) REFERENCES nota_fiscal_eletronica(id);

ALTER TABLE nota_fiscal_item
  ADD CONSTRAINT fk_produto_nota_fiscal_item
  FOREIGN KEY (produto_id) REFERENCES produto(id);

ALTER TABLE nota_fiscal_xml
  ADD CONSTRAINT fk_nota_fiscal_eletronica_nota_fiscal_xml
  FOREIGN KEY (id) REFERENCES nota_fiscal_eletronica(id);

ALTER TABLE usuario
  ADD CONSTRAINT fk_usuario_associado
  FOREIGN KEY (associado_id)
  REFERENCES associado(id);

-- Restrictions NOT NULL==

ALTER TABLE endereco
  ALTER COLUMN estado SET NOT NULL,
  ALTER COLUMN cidade SET NOT NULL,
  ALTER COLUMN cep SET NOT NULL;

ALTER TABLE cliente
  ALTER COLUMN nome_fantasia SET NOT NULL,
  ALTER COLUMN razao_social SET NOT NULL,
  ALTER COLUMN telefone SET NOT NULL,
  ALTER COLUMN email SET NOT NULL,
  ALTER COLUMN data_cadastro SET NOT NULL,
  ALTER COLUMN ativo SET NOT NULL,
  ALTER COLUMN cpf_cnpj SET NOT NULL;

ALTER TABLE associado
  ALTER COLUMN cnpj SET NOT NULL,
  ALTER COLUMN razao_social SET NOT NULL,
  ALTER COLUMN nome_fantasia SET NOT NULL,
  ALTER COLUMN data_cadastrado SET NOT NULL,
  ALTER COLUMN ativo SET NOT NULL;

ALTER TABLE nota_fiscal_eletronica
  ALTER COLUMN associado_id SET NOT NULL,
  ALTER COLUMN chave_acesso SET NOT NULL,
  ALTER COLUMN razao_social SET NOT NULL,
  ALTER COLUMN data_emissao SET NOT NULL,
  ALTER COLUMN valor_total SET NOT NULL,
  ALTER COLUMN tipo_ambiente SET NOT NULL,
  ALTER COLUMN tipo_operacao SET NOT NULL,
  ALTER COLUMN tipo_forma_emissao SET NOT NULL,
  ALTER COLUMN tipo_status_envio_sefaz SET NOT NULL,
  ALTER COLUMN data_inclusao SET NOT NULL,
  ALTER COLUMN ativo SET NOT NULL,
  ALTER COLUMN numero_nota_fiscal SET NOT NULL,
  ALTER COLUMN numero_serie SET NOT NULL,
  ALTER COLUMN valor_frete SET NOT NULL;

ALTER TABLE nota_fiscal_item
  ALTER COLUMN nota_fiscal_eletronica_id SET NOT NULL,
  ALTER COLUMN produto_id SET NOT NULL,
  ALTER COLUMN cfop SET NOT NULL,
  ALTER COLUMN ncm SET NOT NULL,
  ALTER COLUMN quantidade SET NOT NULL,
  ALTER COLUMN valor_unitario SET NOT NULL,
  ALTER COLUMN valor_total SET NOT NULL;

ALTER TABLE nota_fiscal_xml
  ALTER COLUMN hash SET NOT NULL,
  ALTER COLUMN conteudo SET NOT NULL;

ALTER TABLE usuario
  ALTER COLUMN nome_usuario SET NOT NULL,
  ALTER COLUMN senha SET NOT NULL,
  ALTER COLUMN tipo_usuario SET NOT NULL;

    -- Restrictions UNIQUE--
ALTER TABLE usuario
  ADD CONSTRAINT uk_usuario_nome UNIQUE (nome_usuario);

    -- Restrictions CHECK--
ALTER TABLE usuario
  ADD CONSTRAINT ck_usuario_senha CHECK (length(senha) >= 6);

INSERT INTO categoria (id, nome) VALUES
(1, 'Verdura'),
(2, 'Legume'),
(3, 'Fruta'),
(4, 'Tempero'),
(5, 'Raiz');

INSERT INTO tipo (id, nome) VALUES
(1, 'Entrada (Colheita)'),
(2, 'Saída (Venda)'),
(3, 'Perda (Descarte)'),
(4, 'Ajuste (Balanço)'),
(5, 'Transferência Interna');

INSERT INTO origem (id, nome) VALUES
(1, 'Módulo de Produção (Colheita)'),
(2, 'Módulo de Vendas'),
(3, 'Tela de Inventário (Ajustes/Perdas)'),
(4, 'Módulo de Compras (Entrada NF)'),
(5, 'Sistema (Processo Automático)');

INSERT INTO status_pedido (id, descricao) VALUES
(1, 'Aberto'),
(2, 'Concluído'),
(3, 'Cancelado');

INSERT INTO forma_pagamento (id, descricao) VALUES
(1, 'Débito'),
(2, 'Crédito'),
(3, 'Pix'),
(4, 'Boleto'),
(5, 'Outro');

INSERT INTO tipo_problema (id, descricao) VALUES
(1, 'Praga'),
(2, 'Doença'),
(3, 'Falha de irrigação'),
(4, 'Clima adverso'),
(5, 'Outro');

INSERT INTO atividade (nome_atividade, descricao, observacoes, status) VALUES
('Preparação do Solo', 'Revolvimento e adubação do solo', 'Usar trator para aração', 'Concluída'),
('Plantio', 'Plantio das mudas no canteiro', 'Espaçamento de 30cm', 'Concluída'),
('Irrigação', 'Sistema de irrigação por gotejamento', 'Verificar vazamentos', 'Em Andamento'),
('Adubação', 'Aplicação de adubo orgânico', 'Usar esterco curtido', 'Pendente'),
('Colheita', 'Colheita manual dos produtos', 'Realizar pela manhã', 'Pendente');

INSERT INTO endereco (id, estado, cidade, bairro, rua, numero, cep) VALUES
(1, 'SP', 'Campinas', 'Centro', 'Rua das Palmeiras', 100, '13010000'),
(2, 'SP', 'Sumaré', 'Zona Rural', 'Estrada Terra Livre', 0, '13170000'),
(3, 'SP', 'Paulínia', 'Assentamento Esperança', 'Rua do Campo', 12, '13140000'),
(4, 'SP', 'Hortolândia', 'Jardim Verde', 'Rua das Flores', 56, '13184000'),
(5, 'SP', 'Americana', 'Zona Rural', 'Sítio Boa Vista', 0, '13470000');

INSERT INTO associado (id, endereco_id, cnpj, razao_social, nome_fantasia, inscricao_estadual, inscricao_municipal, telefone, email, data_cadastrado, ativo) VALUES
(1, 2, '12.345.678/0001-01', 'João da Silva', 'Sítio Terra Livre', '123456789', '321654', '(19)98888-0001', 'joao.terra@mst.org', NOW(), TRUE),
(2, 3, '23.456.789/0001-02', 'Maria Oliveira', 'Chácara Raízes do Campo', '987654321', '654321', '(19)98888-0002', 'maria.raizes@mst.org', NOW(), TRUE),
(3, 5, '34.567.890/0001-03', 'Carlos Souza', 'Sítio Boa Colheita', '456789123', '852963', '(19)98888-0003', 'carlos.colheita@mst.org', NOW(), TRUE),
(4, 2, '45.678.901/0001-04', 'Ana Santos', 'Fazendinha Nova Vida', '741852963', '147258', '(19)98888-0004', 'ana.novavida@mst.org', NOW(), TRUE),
(5, 1, '56.789.012/0001-05', 'Paulo Lima', 'Sítio Esperança Viva', '369258147', '369147', '(19)98888-0005', 'paulo.esperanca@mst.org', NOW(), TRUE);

INSERT INTO cliente (id, endereco_id, nome_fantasia, razao_social, telefone, email, data_cadastro, ativo, cpf_cnpj) VALUES
(1, 1, 'Mercado Central', 'Mercado Central de Campinas LTDA', '(19)3232-1111', 'mercado@central.com', NOW(), TRUE, '12.345.678/0001-00'),
(2, 4, 'Restaurante Sabor da Terra', 'Sabor da Terra Alimentos LTDA', '(19)3333-2222', 'contato@saborterra.com', NOW(), TRUE, '98.765.432/0001-11'),
(3, 2, 'Empório Rural', 'Empório Rural Paulínia LTDA', '(19)3444-3333', 'emporio@rural.com', NOW(), TRUE, '87.654.321/0001-22'),
(4, 1, 'Feira Verde', 'Cooperativa Feira Verde', '(19)3555-4444', 'contato@feiraverde.org', NOW(), TRUE, '76.543.210/0001-33'),
(5, 3, 'Casa Natural', 'Casa Natural Alimentos', '(19)3666-5555', 'casa@natural.com', NOW(), TRUE, '65.432.109/0001-44');

INSERT INTO armazem (id, nome, endereco_id) VALUES
(1, 'Armazém Central', 1),
(2, 'Galpão Refrigerado', 2),
(3, 'Estoque Sítio Boa Esperança', 3),
(4, 'Ponto de Coleta Sumaré', 4),
(5, 'Caixa de Campo 1', 5);

INSERT INTO especie (id, categoria_id, nome, descricao, tempo_colheita, rendimento_kg_m2) VALUES
(10, 1, 'Alface Crespa', 'Alface verde tradicional', 45, 1.5),
(11, 2, 'Tomate Italiano', 'Ideal para molhos', 90, 4.0),
(12, 3, 'Limão Tahiti', 'Limão comum sem semente', 120, 5.0),
(13, 5, 'Mandioca', 'Mandioca amarela (Aipim)', 240, 3.0),
(14, 4, 'Manjericão', 'Tempero fresco', 60, 0.8);

INSERT INTO produto (id, especie_id, nome, descricao) VALUES
(100, 10, 'Alface Crespa (Maço)', 'Maço de Alface com aprox. 200g'),
(101, 11, 'Tomate Italiano (Kg)', 'Tomate vendido a granel por peso'),
(102, 12, 'Limão Tahiti (Saco 1kg)', 'Pacote de 1kg de limão'),
(103, 13, 'Mandioca Descascada (500g)', 'Pacote embalado a vácuo'),
(104, 14, 'Manjericão (Molho)', 'Molho de manjericão fresco');

INSERT INTO preco_ppa (data_inicio, especie_id, data_final, valor) VALUES
('2025-01-01 00:00:00', 10, '2025-06-30 23:59:59', 3.50),
('2025-01-01 00:00:00', 11, '2025-06-30 23:59:59', 8.00),
('2025-01-01 00:00:00', 12, '2025-06-30 23:59:59', 4.00),
('2025-01-01 00:00:00', 13, '2025-06-30 23:59:59', 6.50),
('2025-01-01 00:00:00', 14, '2025-06-30 23:59:59', 2.50);

INSERT INTO area (associado_id, nome, area_total, area_utilizada, ph) VALUES
(1, 'Fazenda São João', 50.5, 35.2, 6.2),
(1, 'Sítio Esperança', 25.0, 18.7, 5.8),
(2, 'Chácara Boa Vista', 30.0, 22.3, 6.5),
(2, 'Fazenda Verde Vida', 45.2, 30.1, 6.0),
(3, 'Sítio Recanto Feliz', 15.8, 12.5, 5.9);

INSERT INTO talhao (area_id, nome, area_talhao, observacoes, status) VALUES
(1, 'Talhão A1', 5.2, 'Solo argiloso, boa drenagem', 'Ativo'),
(1, 'Talhão A2', 4.8, 'Próximo ao rio, umidade alta', 'Ativo'),
(2, 'Talhão B1', 3.5, 'Inclinação moderada', 'Inativo'),
(3, 'Talhão C1', 6.1, 'Solo arenoso, precisa adubação', 'Ativo'),
(4, 'Talhão D1', 5.7, 'Área plana, irrigação por gotejamento', 'Ativo');

INSERT INTO plano (Especie_id, talhao_id, nome_plano, descricao, data_inicio, data_fim, observacoes, area_cultivo) VALUES
(10, 1, 'Plano Alface Crespa', 'Cultivo de alface para primavera', '2024-09-01', '2024-11-15', 'Usar adubo orgânico', 5.0),
(11, 2, 'Plano Tomate Italiano', 'Cultivo de tomate para verão', '2024-10-01', '2024-12-10', 'Controlar irrigação', 4.5),
(12, 4, 'Plano Limão Tahiti', 'Cultivo de limão outonal', '2024-08-15', '2024-10-30', 'Solo precisa correção', 6.0),
(13, 5, 'Plano Mandioca', 'Cultivo de mandioca de inverno', '2024-07-01', '2024-09-20', 'Proteger de geadas', 5.5),
(14, 3, 'Plano Manjericão', 'Cultivo contínuo', '2024-09-10', '2025-03-10', 'Colheita escalonada', 3.2);

INSERT INTO ordem_producao 
(id, plano_id, Especie_id, Talhao_id, nome_plano, descricao, data_inicio, data_fim, observacoes, area_cultivo, data_execucao, quantidade_Kg, status) VALUES
(1, 1, 10, 1, 'Plano Alface Crespa', 'Cultivo de alface para primavera', '2024-09-01', '2024-11-15', 'Usar adubo orgânico', 5.0, CURRENT_DATE, 25.5, 'em_execucao'),

(2, 2, 11, 2, 'lano Tomate Italiano', 'Cultivo de tomate para verão', '2024-10-01', '2024-12-10', 'Controlar irrigação', 4.5, CURRENT_DATE, 48.2, 'em_execucao'),

(3, 3, 12, 3, 'Plano Limão Tahiti', 'Cultivo de limão outonal', '2024-08-15', '2024-10-30', 'Solo precisa correção', 6.0, CURRENT_DATE, 33.4, 'em_execucao'),

(4, 4, 13, 4, 'Plano Mandioca', 'Cultivo de mandioca de inverno', '2024-07-01', '2024-09-20', 'Proteger de geadas', 5.5, CURRENT_DATE, 65.0, 'em_execucao'),

(5, 5, 14, 5, 'Plano Manjericão', 'Cultivo contínuo', '2024-09-10', '2025-03-10', 'Colheita escalonada', 3.2, CURRENT_DATE, 27.8, 'em_execucao');


INSERT INTO Canteiro (ordem_producao_id, nome, area_canteiro_m2, observacoes, kg_gerados, status) VALUES
(1, 'Canteiro Alface Crespa', 120, 'Canteiro sombreado', 280.5, 'crescendo'),
(2, 'Canteiro Tomate Italiano', 100, 'Canteiro ensolarado', 240.2, 'crescendo'),
(3, 'Canteiro Limão Tahiti', 80, 'Irrigação automática', 132.8, 'crescendo'),
(4, 'Canteiro Mandioca', 150, 'Solo profundo', 450.0, 'crescendo'),
(5, 'Canteiro Manjericão', 130, 'Protegido do vento', 345.6, 'crescendo');

INSERT INTO registrar_problema (id, ordem_producao_id, tipo_problema_id, quantidade_afetada, data_problema, observacoes) VALUES
(1, 1, 1, 10, '2025-01-10 00:00:00', 'Insetos na plantação'),
(2, 2, 2, 15, '2025-01-12 00:00:00', 'Folhas amareladas'),
(3, 3, 3, 8, '2025-01-14 00:00:00', 'Quebra da bomba hidráulica'),
(4, 4, 1, 20, '2025-01-15 00:00:00', 'Ataque de lagartas'),
(5, 5, 5, 5, '2025-01-18 00:00:00', 'Problema não identificado');

INSERT INTO material (associado_id, nome, quantidade, unidade_medida) VALUES
(1, 'Adubo Orgânico', 500.0, 'kg'),
(1, 'Sementes de Alface', 2.5, 'kg'),
(1, 'Mangueira Irrigação', 150.0, 'metros'),
(2, 'Fertilizante NPK', 100.0, 'kg'),
(3, 'Calcário Dolomítico', 300.0, 'kg');

INSERT INTO atividade_has_material (material_id, atividade_id, quantidade_utilizada) VALUES
(1, 1, 50.0),
(2, 2, 0.5),
(3, 3, 25.0),
(4, 4, 15.0),
(1, 4, 30.0);

INSERT INTO atividade_canteiro (canteiro_id, atividade_id, tempo_gasto_horas, data_atividade) VALUES
(1, 1, 8.5, '2024-09-01'),
(1, 2, 6.0, '2024-09-02'),
(2, 1, 7.0, '2024-09-01'),
(3, 3, 12.5, '2024-09-05'),
(4, 2, 5.5, '2024-08-20');

INSERT INTO despesa (
    id,
    associado_id,
    categoria_gasto,
    destinatario,
    valor_gasto,
    data_transacao,
    descricao_despesa
) VALUES
(1, 1, 'outro', 'Posto DieselMax ', 1250.60, '2025-10-01 08:30:00', 'Combustível para trator'),
(2, 2, 'produtos', 'AgroInsumos S.A.', 15200.00, '2025-10-01 14:15:00', 'Compra de sementes de milho'),
(3, 3, 'outros', 'Contabilidade Rural Ltda', 950.00, '2025-10-02 19:00:00', 'Serviços contábeis ref. Setembro/25'),
(4, 1, 'transporte', 'Transportadora GrãoForte', 3800.00, '2025-10-03 12:00:00', 'Frete para escoamento da colheita'),
(5, 2, 'produtos', 'Fertilizantes Brasil', 22000.00, '2025-10-04 09:10:00', 'Aquisição de NPK 20-05-20 para cobertura'),
(6, 3, 'outros', 'Desktop Internet', 450.00, '2025-10-05 18:00:00', 'Mensalidade internet'),
(7, 1, 'transporte', 'Oficina TratorPeças', 1800.00, '2025-10-06 17:45:00', 'Manutenção corretiva Trator'),
(8, 2, 'produtos', 'Defensivos Agro', 7300.00, '2025-10-07 11:20:00', 'Compra de herbicida pré-emergente'),
(9, 3, 'outros', 'Sindicato Rural', 320.00, '2025-10-08 13:00:00', 'Anuidade associado 2025'),
(10, 1, 'transporte', 'SemParar', 245.50, '2025-10-10 07:15:00', 'Pedágios'),
(11, 2, 'produtos', 'Casa do Agricultor', 890.00, '2025-10-12 16:30:00', 'Aquisição de ferramentas (pás, enxadas)'),
(12, 3, 'outros', 'CPFL Energia', 1400.80, '2025-10-14 10:00:00', 'Fatura de energia'),
(13, 1, 'transporte', 'Posto DieselMax', 980.00, '2025-10-15 08:00:00', 'Diesel para caminhão'),
(14, 2, 'produtos', 'IrrigaTech Soluções', 4500.00, '2025-10-18 09:00:00', 'Compra de gotejadores e conexões'),
(15, 3, 'produtos', 'AgroInsumos S.A.', 8000.00, '2025-10-20 00:00:00', 'Compra de sementes de tomate');

INSERT INTO projeto (id, nome_projeto, data_criacao, data_final, orcamento) VALUES
(1, 'projeto1',        '2025-01-10 09:00:00', '2025-12-31', 150000.00),
(2, 'projeto2',         '2025-02-05 10:30:00', '2025-11-30',  80000.00),
(3, 'projeto3',               '2025-03-15 14:20:00', '2025-09-30',  35000.00),
(4, 'projeto4',       '2025-03-20 11:00:00', '2025-08-31',  20000.00),
(5, 'projeto5',                '2025-04-01 08:45:00', '2025-12-01', 120000.00),
(6, 'projeto6',      '2025-04-10 16:00:00', '2025-10-15',  50000.00),
(7, 'projeto7',          '2025-05-02 09:15:00', '2025-10-01',  65000.00),
(8, 'projeto8',                     '2025-05-22 13:40:00', '2025-11-15',  42000.00),
(9, 'projeto9',            '2025-06-05 10:10:00', '2025-12-20',  70000.00),
(10,'projeto10',          '2025-06-18 15:30:00', '2025-10-31',  30000.00);

INSERT INTO pedido (id, projeto_id, associado_id, data_criacao, status_pedido_id, valor_total) VALUES
(1,  1, 1, '2025-07-01 09:00:00', 1, 120.00),
(2,  1, 2, '2025-07-02 10:10:00', 1,  75.50),
(3,  2, 1, '2025-07-05 14:25:00', 2, 210.99),
(4,  2, 3, '2025-07-06 11:05:00', 3,  49.90),
(5,  3, 4, '2025-07-07 16:40:00', 1,  60.00),
(6,  4, 1, '2025-07-08 12:00:00', 2, 330.00),
(7,  5, 5, '2025-07-09 13:15:00', 1,  85.00),
(8,  6, 2, '2025-07-10 08:50:00', 1, 150.00),
(9,  7, 1, '2025-07-11 09:20:00', 2,  99.90),
(10, 8, 1, '2025-07-12 10:30:00', 1, 199.90);

INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade_total, valor_unitario, valor_total) VALUES
(1,   1, 100, 2,  60.00, 120.00),
(2,   2, 101, 1,  75.50,  75.50),
(3,   3, 102, 3,  70.33, 210.99),
(4,   4, 102, 1,  49.90,  49.90),
(5,   5, 101, 1,  60.00,  60.00),
(6,   6, 100, 5,  66.00, 330.00),
(7,   7, 103, 1,  85.00,  85.00),
(8,   8, 104, 3,  50.00, 150.00),
(9,   9, 100, 2,  49.95,  99.90),
(10, 10, 101, 2,  99.95, 199.90);

INSERT INTO associado_item_pedido (id, associado_id, item_pedido_id, quantidade_atribuida) VALUES
(1,  1,  1, 2),
(2,  2,  2, 1),
(3,  1,  3, 1),
(4,  3,  4, 1),
(5,  4,  5, 1),
(6,  1,  6, 3),
(7,  5,  7, 1),
(8,  2,  8, 2),
(9,  1,  9, 1),
(10, 1, 10, 2);

INSERT INTO venda (id, projeto_id, associado_id, pedido_id, data_compra, valor_total, data_entrega, forma_pagamento_id) VALUES
(1,  1, 1, 1,  '2025-07-01', 120.00, '2025-07-03', 2),
(2,  1, 2, 2,  '2025-07-02',  75.50, '2025-07-04', 3),
(3,  2, 1, 3,  '2025-07-05', 210.99, '2025-07-06', 5),
(4,  2, 3, 4,  '2025-07-06',  49.90, '2025-07-08', 3),
(5,  3, 4, 5,  '2025-07-07',  60.00, '2025-07-10', 3),
(6,  4, 1, 6,  '2025-07-08', 330.00, '2025-07-09', 2),
(7,  5, 5, 7,  '2025-07-09',  85.00, '2025-07-11', 3),
(8,  6, 2, 8,  '2025-07-10', 150.00, '2025-07-12', 2),
(9,  7, 1, 9,  '2025-07-11',  99.90, '2025-07-13', 4),
(10, 8, 1, 10, '2025-07-12', 199.90, '2025-07-14', 3);

INSERT INTO nota_fiscal_eletronica (id, associado_id, cliente_id, chave_acesso, razao_social, data_emissao, valor_total, tipo_ambiente, tipo_operacao, tipo_forma_emissao, tipo_status_envio_sefaz, numero_protocolo, data_inclusao, ativo, numero_nota_fiscal, numero_serie, dados_adicionais, valor_frete) VALUES
(1, 1, 1, '35190812345678000101550010000000111111111111', 'João da Silva', NOW(), 150.00, 1, 1, 1, 1, 1001, NOW(), TRUE, '000001', '001', 'Venda local para restaurante', 0),
(2, 2, 2, '35190823456789000102550010000000222222222222', 'Maria Oliveira', NOW(), 250.00, 1, 1, 1, 1, 1002, NOW(), TRUE, '000002', '001', 'Venda orgânica p/ feira', 0),
(3, 3, 3, '35190834567890000103550010000000333333333333', 'Carlos Souza', NOW(), 180.00, 1, 1, 1, 1, 1003, NOW(), TRUE, '000003', '001', 'Venda de feira', 0),
(4, 4, 4, '35190845678901000104550010000000444444444444', 'Ana Santos', NOW(), 220.00, 1, 1, 1, 1, 1004, NOW(), TRUE, '000004', '001', 'Venda direta em feira', 0),
(5, 5, 5, '35190856789012000105550010000000555555555555', 'Paulo Lima', NOW(), 310.00, 1, 1, 1, 1, 1005, NOW(), TRUE, '000005', '001', 'Venda com entrega', 15.00);

INSERT INTO nota_fiscal_item (id, nota_fiscal_eletronica_id, produto_id, cfop, ncm, quantidade, valor_unitario, valor_total)VALUES
(1, 3, 101, '5101', '07149000', 15, 4.00, 60.00),
(2, 4, 102, '5101', '04079000', 10, 15.00, 150.00),
(3, 4, 103, '5101', '07133319', 12, 8.00, 96.00),
(4, 5, 104, '5101', '10059010', 20, 2.00, 40.00),
(5, 5, 100, '5101', '07149090', 10, 5.00, 50.00);

INSERT INTO nota_fiscal_xml (id, hash, conteudo) VALUES
(1, 'hash_001', '<xml>Nota 1</xml>'),
(2, 'hash_002', '<xml>Nota 2</xml>'),
(3, 'hash_003', '<xml>Nota 3</xml>'),
(4, 'hash_004', '<xml>Nota 4</xml>'),
(5, 'hash_005', '<xml>Nota 5</xml>');

INSERT INTO estoque_atual (associado_id, produto_id, armazem_id, quantidade) VALUES
(1, 100, 1, 120.00), -- Fam. Silva, Alface, Armazém Central (150 - 30)
(2, 101, 2, 70.00),  -- Fam. Oliveira, Tomate, Refri (80 - 10)
(1, 102, 1, 50.00),  -- Fam. Silva, Limão, Armazém Central (Saldo inicial, ex.)
(3, 103, 2, 200.00), -- Sítio Verde, Mandioca, Refri
(4, 104, 5, 40.00);  -- Coop, Manjericão, Caixa de Campo

INSERT INTO movimentacao (id, tipo_id, origem_id, produto_id, armazem_id, associado_id, quantidade, data_movimento) VALUES
(1, 1, 1, 100, 1, 1, 150.00, '2025-05-10 08:00:00'), -- tipo_id=1 (Entrada/Colheita), origem_id=1 (Módulo de Produção)
(2, 1, 1, 101, 2, 2, 80.00, '2025-05-10 09:00:00'),  -- tipo_id=1 (Entrada/Colheita), origem_id=1 (Módulo de Produção)
(3, 2, 2, 100, 3, 1, 30.00, '2025-05-11 10:00:00'),  -- tipo_id=2 (Saída/Venda), origem_id=2 (Módulo de Vendas)
(4, 5, 3, 101, 4, 2, 10.00, '2025-05-11 11:00:00'),  -- tipo_id=5 (Transferência), origem_id=3 (Tela de Inventário, pois foi um ajuste manual)
(5, 3, 3, 102, 5, 1, 5.00, '2025-05-12 14:00:00');   -- tipo_id=3 (Perda), origem_id=3 (Tela de Inventário)

INSERT INTO usuario (id, associado_id, nome_usuario, senha, tipo_usuario, ativo) VALUES
(1, 1, 'admin', 'admin123', 1, TRUE);

-- Corrigir sequences após INSERTs com IDs manuais
SELECT setval('movimentacao_id_seq', (SELECT MAX(id) FROM movimentacao));
SELECT setval('usuario_id_seq', (SELECT MAX(id) FROM usuario));
SELECT setval('atividade_canteiro_id_seq', (SELECT COALESCE(MAX(id), 1) FROM atividade_canteiro));
SELECT setval('atividade_has_material_id_seq', (SELECT COALESCE(MAX(id), 1) FROM atividade_has_material));