package br.edu.ifsp.hto.cooperativa;

import br.edu.ifsp.hto.cooperativa.estoque.controle.ControleEstoque;
import br.edu.ifsp.hto.cooperativa.financeiro.visao.VisaoDespesa;
import br.edu.ifsp.hto.cooperativa.financeiro.visao.VisaoEstoque;
import br.edu.ifsp.hto.cooperativa.financeiro.visao.VisaoGeralGrafica;
import br.edu.ifsp.hto.cooperativa.financeiro.visao.VisaoGeralTabela;
import br.edu.ifsp.hto.cooperativa.financeiro.visao.VisaoPlanejamento;
import br.edu.ifsp.hto.cooperativa.financeiro.visao.VisaoProducao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoAreas;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoHome;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.telas.VisaoMateriais;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.TelaCadastroAssociado;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.TelaCadastroVenda;
import br.edu.ifsp.hto.cooperativa.notafiscal.visao.TelaGerarNotaFiscalVenda;

import br.edu.ifsp.hto.cooperativa.vendas.visao.CriarPedidoView;
import br.edu.ifsp.hto.cooperativa.vendas.visao.ConsultaPedidosView;
import br.edu.ifsp.hto.cooperativa.vendas.visao.CriarProjetoView;
import br.edu.ifsp.hto.cooperativa.vendas.visao.ConsultaProjetosView;
import br.edu.ifsp.hto.cooperativa.vendas.visao.ProdutorMainView;
import br.edu.ifsp.hto.cooperativa.vendas.visao.AssociacaoMainView;

import br.edu.ifsp.hto.cooperativa.sessao.modelo.negocios.Sessao;

public class Cooperativa extends JFrame {

    private JDesktopPane desktop; //Janela PAI para uso InternalFrame


    // Construtor para inicializar o ArrayList
    public Cooperativa() {
        super("SGCC - Sistema Gestão Coopasul-Cooperativa de Produção e Comercialização Assentamento de Sumaré ");

        menu();
    }

    // menu da aplicacao
    public void menu() {
        //Menus da barra:cadastro / Relatorios / Ajuda
        JMenu planejamentoMenu = new JMenu("Planejamento"); // cria opcao menu Planejamento

        JMenu producaoMenu = new JMenu("Produção"); // cria opcao menu Producao

        JMenu vendaMenu = new JMenu("Vendas"); // cria opcao menu Producao

        JMenu estoqueMenu = new JMenu("Estoque");

        JMenu financeiroMenu = new JMenu("Financeira"); // cria opcao menu Producao

        JMenu notaFiscalMenu = new JMenu("Nota-Fiscal"); // cria opcao menu Producao

        JMenu ajudaMenu = new JMenu("Ajuda"); // cria menu ajuda

        JMenuItem sairItem = new JMenuItem("Sair"); // cria item sair


        //Grupo Planejamento adicionar as opcoes do subMneu (JMenuItem) de Planejamento aqui
        JMenuItem planejamentoItemMenu1 = new JMenuItem("Home");
        JMenuItem planejamentoItemMenu2 = new JMenuItem("Areas");
        JMenuItem planejamentoItemMenu3 = new JMenuItem("Materiais");

        //adcionar os itens de menu do Planejamento
        planejamentoMenu.add(planejamentoItemMenu1);
        planejamentoMenu.add(planejamentoItemMenu2);
        planejamentoMenu.add(planejamentoItemMenu3);
        planejamentoMenu.addSeparator();
        //Adicionar o Sair na Primeira Opcao do Menu
        planejamentoMenu.add(sairItem);


        //Grupo Producao adicionar as opcoes do subMneu (JMenuItem) aqui
        JMenuItem producaoItemTelaInicial = new JMenuItem("Tela Inicial");
        JMenuItem producaoItemGerenciarArea = new JMenuItem("Gerenciar Área");
        JMenuItem producaoItemRegistrarProblemas = new JMenuItem("Registrar Problemas");
        JMenuItem producaoItemRelatorioProducao = new JMenuItem("Relatório de Produção");

        producaoMenu.add(producaoItemTelaInicial);
        producaoMenu.add(producaoItemGerenciarArea);
        producaoMenu.add(producaoItemRegistrarProblemas);
        producaoMenu.add(producaoItemRelatorioProducao);

        // 1. Criar todos os itens de menu
        JMenuItem vendaItemPainelProdutor = new JMenuItem("Painel do Produtor");
        JMenuItem vendaItemPainelAssociacao = new JMenuItem("Painel da Associação");
        
        JMenuItem vendaItemCriarPedido = new JMenuItem("Criar Pedido");
        JMenuItem vendaItemConsultarPedidos = new JMenuItem("Consultar Pedidos");
        
        JMenuItem vendaItemCriarProjeto = new JMenuItem("Criar Projeto");
        JMenuItem vendaItemConsultarProjetos = new JMenuItem("Consultar Projetos");

        // 2. Adicionar na barra (Adicionamos todos, depois desabilitamos conforme a regra)
        vendaMenu.add(vendaItemPainelProdutor);
        vendaMenu.add(vendaItemPainelAssociacao);
        vendaMenu.addSeparator();
        vendaMenu.add(vendaItemCriarPedido);
        vendaMenu.add(vendaItemConsultarPedidos);
        vendaMenu.addSeparator();
        vendaMenu.add(vendaItemCriarProjeto);
        vendaMenu.add(vendaItemConsultarProjetos);

        // 3. Lógica de Permissões (Regras Tipo 1 vs Tipo 2)
        try {
            long tipoUsuario = Sessao.getUsuarioLogado().usuarioVO.getTipoUsuario();

            if (tipoUsuario == 1) {
                // === REGRAS TIPO 1 (PRODUTOR) ===
                // Pode: Painel Produtor, Criar Pedido, Consultar Pedidos
                vendaItemPainelProdutor.setEnabled(true);
                vendaItemCriarPedido.setEnabled(true);
                vendaItemConsultarPedidos.setEnabled(true);

                // Bloqueado: O resto
                vendaItemPainelAssociacao.setEnabled(false); // ou .setVisible(false) se preferir sumir
                vendaItemCriarProjeto.setEnabled(false);
                vendaItemConsultarProjetos.setEnabled(false);

            } else if (tipoUsuario == 2) {
                // === REGRAS TIPO 2 (ASSOCIAÇÃO/ADMIN) ===
                // Bloqueado: Painel Produtor
                vendaItemPainelProdutor.setEnabled(false);

                // Liberado: Todo o resto
                vendaItemPainelAssociacao.setEnabled(true);
                vendaItemCriarPedido.setEnabled(true);
                vendaItemConsultarPedidos.setEnabled(true);
                vendaItemCriarProjeto.setEnabled(true);
                vendaItemConsultarProjetos.setEnabled(true);
            }

        } catch (Exception e) {
            System.err.println("Erro ao verificar permissões de venda: " + e.getMessage());
            // Por segurança, em caso de erro, você pode optar por bloquear tudo ou deixar padrão
        }

        //Grupo Estoque adicionar as opcoes do subMneu (JMenuItem) aqui
        JMenuItem estoqueItemEstoqueEntrada = new JMenuItem("Entrada de Estoque");
        JMenuItem estoqueItemEstoqueSaida = new JMenuItem("Saída de Estoque");
        JMenuItem estoqueItemEstoqueTranferencia = new JMenuItem("Transferência de Estoque");
        JMenuItem estoqueItemIndicacao = new JMenuItem("Indicação de Saída por Venda");

        JMenuItem estoqueItemGerenciarArmazem = new JMenuItem("Gerenciar Armazéns");
        JMenuItem estoqueItemGerenciarCategoria = new JMenuItem("Gerenciar Categorias");
        JMenuItem estoqueItemGerenciarEspecie = new JMenuItem("Gerenciar Espécies");
        JMenuItem estoqueItemGerenciarProduto = new JMenuItem("Gerenciar Produtos");
        
        JMenuItem estoqueItemRelatorio = new JMenuItem("Relatório");
        JMenuItem estoqueItemHistorico = new JMenuItem("Histórico");
        JMenuItem estoqueItemInventario = new JMenuItem("Inventário");
        JMenuItem estoqueItemEstoque = new JMenuItem("Estoque");

        estoqueMenu.add(estoqueItemEstoqueEntrada);
        estoqueMenu.add(estoqueItemEstoqueSaida);
        estoqueMenu.add(estoqueItemEstoqueTranferencia);
        estoqueMenu.add(estoqueItemIndicacao);
        estoqueMenu.add(estoqueItemGerenciarArmazem);
        estoqueMenu.add(estoqueItemGerenciarCategoria);
        estoqueMenu.add(estoqueItemGerenciarEspecie);
        estoqueMenu.add(estoqueItemGerenciarProduto);
        estoqueMenu.add(estoqueItemRelatorio);
        estoqueMenu.add(estoqueItemHistorico);
        estoqueMenu.add(estoqueItemInventario);
        estoqueMenu.add(estoqueItemEstoque);

        //Grupo Financeiro adicionar as opcoes do subMneu (JMenuItem) aqui
        JMenuItem financeiroVisaoGeralGraph = new JMenuItem("Visão Geral em Gráfico");
        JMenuItem financeiroVisaoGeralTab = new JMenuItem("Visão Geral em Tabela");
        JMenuItem financeiroEstoque = new JMenuItem("Sobre Estoque");
        JMenuItem financeiroPlan = new JMenuItem("Sobre Planejamentos");
        JMenuItem financeiroProd = new JMenuItem("Sobre Produção");
        JMenuItem financeiroDesp = new JMenuItem("Registro de Despesas");

        financeiroMenu.add(financeiroVisaoGeralGraph);
        financeiroMenu.add(financeiroVisaoGeralTab);
        financeiroMenu.add(financeiroEstoque);
        financeiroMenu.add(financeiroPlan);
        financeiroMenu.add(financeiroProd);
        financeiroMenu.add(financeiroDesp);

        //Grupo Nota Fiscal adicionar as opcoes do subMneu (JMenuItem) aqui
        JMenuItem notaFiscalItemCadAssociado = new JMenuItem("Cadastrar Associado");
        JMenuItem notaFiscalItemCadVenda = new JMenuItem("Cadastrar NF-e");
        JMenuItem notaFiscalItemGerarNF = new JMenuItem("Gerar Nota Fiscal");

        notaFiscalMenu.add(notaFiscalItemCadAssociado);
        notaFiscalMenu.add(notaFiscalItemCadVenda);
        notaFiscalMenu.add(notaFiscalItemGerarNF);


        //Item de AJUDA
        JMenuItem sobreItem = new JMenuItem("Sobre...");
        ajudaMenu.add(sobreItem); //adiciona o item no Menu

        // CRIANDO A BARRA E ADICIONANDO OS MENU
        JMenuBar barra = new JMenuBar(); // cria a barra de menu
        setJMenuBar(barra);          // adiciona a barra de menu bar para na janela
        barra.add(planejamentoMenu); // adiciona o menu Planejamento para a barra menu
        barra.add(producaoMenu);     // adiciona o menu Producao para a barra menu
        barra.add(vendaMenu);        // adiciona o menu Venda para a barra menu
        barra.add(estoqueMenu);      // adiciona o menu Estoque para a barra menu
        barra.add(financeiroMenu);   // adiciona o menu Financeiro para a barra menu
        barra.add(notaFiscalMenu);   // adiciona o menu Nota-Fiscal para a barra menu
        barra.add(ajudaMenu);

        desktop = new JDesktopPane();
        add(desktop); // adiciona o Painel Pai no no frame


        //GRUPOS - Adicionar os eventos de menu para chamar as InternalFrame
        //Exemplo - TRATAMENTO DOS EVENTOS DOS ITENS DO MENU PARA A SUA CHANELA
        planejamentoItemMenu1.addActionListener(ev -> new VisaoHome(desktop));
        planejamentoItemMenu2.addActionListener(ev -> new VisaoAreas(desktop));
        planejamentoItemMenu3.addActionListener(ev -> new VisaoMateriais(desktop));

        // --- AÇÕES DO GRUPO PRODUÇÃO ---
        producaoItemTelaInicial.addActionListener(ev -> {
            br.edu.ifsp.hto.cooperativa.producao.visao.TelaInicial tela = 
                new br.edu.ifsp.hto.cooperativa.producao.visao.TelaInicial(desktop);
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (java.beans.PropertyVetoException e) {}
        });

        producaoItemGerenciarArea.addActionListener(ev -> {
            br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea tela = 
                new br.edu.ifsp.hto.cooperativa.producao.visao.TelaGerenciarArea(desktop);
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (java.beans.PropertyVetoException e) {}
        });

        producaoItemRegistrarProblemas.addActionListener(ev -> {
            br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel model = 
                new br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel();
            br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController controller = 
                new br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController(model);
            br.edu.ifsp.hto.cooperativa.producao.visao.TelaRegistrarProblemas tela = 
                new br.edu.ifsp.hto.cooperativa.producao.visao.TelaRegistrarProblemas(desktop, controller);
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (java.beans.PropertyVetoException e) {}
        });

        producaoItemRelatorioProducao.addActionListener(ev -> {
            br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel model = 
                new br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel();
            br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController controller = 
                new br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController(model);
            br.edu.ifsp.hto.cooperativa.producao.visao.TelaRelatorioProducao tela = 
                new br.edu.ifsp.hto.cooperativa.producao.visao.TelaRelatorioProducao(desktop, controller);
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (java.beans.PropertyVetoException e) {}
        });

        // --- AÇÕES DO GRUPO ESTOQUE ---
        estoqueItemEstoqueEntrada.addActionListener(ev -> ControleEstoque.telaEstoqueEstrada(desktop));
        estoqueItemEstoqueSaida.addActionListener(ev -> ControleEstoque.telaEstoqueSaida(desktop));
        estoqueItemEstoqueTranferencia.addActionListener(ev -> ControleEstoque.telaEstoqueTranferencia(desktop));
        estoqueItemIndicacao.addActionListener(ev -> ControleEstoque.telaEstoqueIndicacao(desktop));
        estoqueItemGerenciarArmazem.addActionListener(ev -> ControleEstoque.telaEstoqueGerenciarArmazem(desktop));
        estoqueItemGerenciarCategoria.addActionListener(ev -> ControleEstoque.telaEstoqueGerenciarCategoria(desktop));
        estoqueItemGerenciarEspecie.addActionListener(ev -> ControleEstoque.telaEstoqueGerenciarEspecie(desktop));
        estoqueItemGerenciarProduto.addActionListener(ev -> ControleEstoque.telaEstoqueGerenciarProduto(desktop));
        estoqueItemRelatorio.addActionListener(ev -> ControleEstoque.telaEstoqueRelatorio(desktop));
        estoqueItemHistorico.addActionListener(ev -> ControleEstoque.telaEstoqueHistorico(desktop));
        estoqueItemInventario.addActionListener(ev -> ControleEstoque.telaEstoqueInventario(desktop));
        estoqueItemEstoque.addActionListener(ev -> ControleEstoque.telaEstoqueEstoque(desktop));
        
        // --- AÇÕES DO GRUPO VENDAS ---
        // Ação: Painel Produtor
        vendaItemPainelProdutor.addActionListener(ev -> {
            ProdutorMainView tela = new ProdutorMainView();
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (Exception e) {}
        });

        vendaItemPainelAssociacao.addActionListener(ev -> {
            AssociacaoMainView tela = new AssociacaoMainView();
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (Exception e) {}
        });

        vendaItemCriarPedido.addActionListener(ev -> {
            CriarPedidoView tela = new CriarPedidoView();
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (Exception e) {}
        });

        vendaItemConsultarPedidos.addActionListener(ev -> {
            ConsultaPedidosView tela = new ConsultaPedidosView();
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (Exception e) {}
        });

        vendaItemCriarProjeto.addActionListener(ev -> {
            CriarProjetoView tela = new CriarProjetoView();
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (Exception e) {}
        });

        vendaItemConsultarProjetos.addActionListener(ev -> {
            ConsultaProjetosView tela = new ConsultaProjetosView();
            desktop.add(tela);
            tela.setVisible(true);
            try { tela.setSelected(true); } catch (Exception e) {}
        });
        
                // -- Ações do grupo FINANCEIRO
        financeiroVisaoGeralGraph.addActionListener(ev -> new VisaoGeralGrafica(desktop));
        financeiroVisaoGeralTab.addActionListener(ev -> new VisaoGeralTabela(desktop));
        financeiroEstoque.addActionListener(ev -> new VisaoEstoque(desktop));
        financeiroPlan.addActionListener(ev -> new VisaoPlanejamento(desktop));
        financeiroProd.addActionListener(ev -> new VisaoProducao(desktop));
        financeiroDesp.addActionListener(ev -> new VisaoDespesa(desktop));

        // --- AÇÕES DO GRUPO NOTA FISCAL ---

        notaFiscalItemCadAssociado.addActionListener(ev -> {
            TelaCadastroAssociado tela = new TelaCadastroAssociado(desktop);
            desktop.add(tela); // Adiciona na área de trabalho
            tela.setVisible(true); // Mostra
            try {
                tela.setSelected(true);
            } catch (Exception e) {
            } // Traz pra frente
        });

        notaFiscalItemCadVenda.addActionListener(ev -> {
            TelaCadastroVenda tela = new TelaCadastroVenda(desktop);
            desktop.add(tela);
            tela.setVisible(true);
            try {
                tela.setSelected(true);
            } catch (Exception e) {
            }
        });

        notaFiscalItemGerarNF.addActionListener(ev -> {
            TelaGerarNotaFiscalVenda tela = new TelaGerarNotaFiscalVenda(desktop);
            desktop.add(tela);
            tela.setVisible(true);
            try {
                tela.setSelected(true);
            } catch (Exception e) {
            }
        });


        sairItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(Cooperativa.this,
                                "Saindo da aplicação",
                                "Fechando aplicação", JOptionPane.PLAIN_MESSAGE);

                        System.exit(0); // encerra aplicação
                    } // fim  metodo actionPerformed
                } // fim classe interna anonima
        ); // fim chamada para addActionListener

        sobreItem.addActionListener(
                new ActionListener() { // anonymous inner class
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(Cooperativa.this,
                                "Nesta opção gostaria de colocar os grupos , componentes"
                                        + "e o email",
                                "Sobre", JOptionPane.PLAIN_MESSAGE);
                    } // fim  metodo actionPerformed
                } // fim classe interna anonima
        ); // fim chamada para addActionListener

    } // fim menu
}