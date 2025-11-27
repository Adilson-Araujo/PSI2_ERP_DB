package br.edu.ifsp.hto.cooperativa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Cooperativa extends JFrame{

	private JDesktopPane desktop; //Janela PAI para uso InternalFrame


	// Construtor para inicializar o ArrayList 
	public Cooperativa(){
		super( "SGCC - Sistema Gest�o Coopasul-Cooperativa de Produ��o e Comercializa��o Assentamento de Sumar� " );     

		menu();
	}

	// menu da aplicacao
	public void menu(){
		//Menus da barra:cadastro / Relatorios / Ajuda
		JMenu planejamentoMenu = new JMenu( "Planejamento" ); // cria opcao menu Planejamento

		JMenu producaoMenu = new JMenu( "Produ��o" ); // cria opcao menu Producao

		JMenu vendaMenu = new JMenu( "Vendas" ); // cria opcao menu Producao

		JMenu estoqueMenu = new JMenu( "Estoque" ); 

		JMenu financeiroMenu = new JMenu( "Financeira" ); // cria opcao menu Producao
		
		JMenu notaFiscalMenu = new JMenu( "Nota-Fiscal" ); // cria opcao menu Producao
		
		JMenu ajudaMenu = new JMenu( "Ajuda" ); // cria menu ajuda

		JMenuItem sairItem = new JMenuItem( "Sair" ); // cria item sair

		
		//Grupo Planejamento adicionar as opcoes do subMneu (JMenuItem) de Planejamento aqui
		JMenuItem planejamentoItemMenu1 = new JMenuItem( "Janela1" );
		JMenuItem planejamentoItemMenu2 = new JMenuItem( "Janela2" );
		JMenuItem planejamentoItemMenu3 = new JMenuItem( "Janela3" );
		JMenuItem planejamentoItemMenu4 = new JMenuItem( "Janela4" );
		
		//adcionar os itens de menu do Planejamento
		planejamentoMenu.add(planejamentoItemMenu1);
		planejamentoMenu.add(planejamentoItemMenu2);
		planejamentoMenu.add(planejamentoItemMenu3);
		planejamentoMenu.add(planejamentoItemMenu4);
		planejamentoMenu.addSeparator();
		//Adicionar o Sair na Primeira Opcao do Menu
		planejamentoMenu.add(sairItem);

		
		//Grupo Producao adicionar as opcoes do subMneu (JMenuItem) aqui
		JMenuItem producaoItemMenu1 = new JMenuItem( "Janela1" );
		JMenuItem producaoItemMenu2 = new JMenuItem( "Janela2" );
		JMenuItem producaoItemMenu3 = new JMenuItem( "Janela3" );
		JMenuItem producaoItemMenu4 = new JMenuItem( "Janela4" );

		producaoMenu.add(producaoItemMenu1);
		producaoMenu.add(producaoItemMenu2);
		producaoMenu.add(producaoItemMenu3);
		producaoMenu.add(producaoItemMenu4);

		//Grupo Vendas adicionar as opcoes do subMneu (JMenuItem) aqui
		JMenuItem vendaItemMenu1 = new JMenuItem( "Janela1" );
		JMenuItem vendaItemMenu2 = new JMenuItem( "Janela2" );
		JMenuItem vendaItemMenu3 = new JMenuItem( "Janela3" );
		JMenuItem vendaItemMenu4 = new JMenuItem( "Janela4" );

		vendaMenu.add(vendaItemMenu1);
		vendaMenu.add(vendaItemMenu2);
		vendaMenu.add(vendaItemMenu3);
		vendaMenu.add(vendaItemMenu4);
		

		//Grupo Estoque adicionar as opcoes do subMneu (JMenuItem) aqui
		JMenuItem estoqueItemMenu1 = new JMenuItem( "Janela1" );
		JMenuItem estoqueItemMenu2 = new JMenuItem( "Janela2" );
		JMenuItem estoqueItemMenu3 = new JMenuItem( "Janela3" );
		JMenuItem estoqueItemMenu4 = new JMenuItem( "Janela4" );

		estoqueMenu.add(estoqueItemMenu1);
		estoqueMenu.add(estoqueItemMenu2);
		estoqueMenu.add(estoqueItemMenu3);
		estoqueMenu.add(estoqueItemMenu4);

		//Grupo Financeiro adicionar as opcoes do subMneu (JMenuItem) aqui
		JMenuItem financeiroItemMenu1 = new JMenuItem( "Janela1" );
		JMenuItem financeiroItemMenu2 = new JMenuItem( "Janela2" );
		JMenuItem financeiroItemMenu3 = new JMenuItem( "Janela3" );
		JMenuItem financeiroItemMenu4 = new JMenuItem( "Janela4" );

		financeiroMenu.add(financeiroItemMenu1);
		financeiroMenu.add(financeiroItemMenu2);
		financeiroMenu.add(financeiroItemMenu3);
		financeiroMenu.add(financeiroItemMenu4);

		//Grupo Nota Fiscal adicionar as opcoes do subMneu (JMenuItem) aqui
		JMenuItem notaFiscalItemMenu1 = new JMenuItem( "Janela1" );
		JMenuItem notaFiscalItemMenu2 = new JMenuItem( "Janela2" );
		JMenuItem notaFiscalItemMenu3 = new JMenuItem( "Janela3" );
		JMenuItem notaFiscalItemMenu4 = new JMenuItem( "Janela4" );

		notaFiscalMenu.add(notaFiscalItemMenu1);
		notaFiscalMenu.add(notaFiscalItemMenu2);
		notaFiscalMenu.add(notaFiscalItemMenu3);
		notaFiscalMenu.add(notaFiscalItemMenu4);

		
		//Item de AJUDA
		JMenuItem sobreItem = new JMenuItem( "Sobre..." );
		ajudaMenu.add( sobreItem ); //adiciona o item no Menu

		// CRIANDO A BARRA E ADICIONANDO OS MENU
		JMenuBar barra = new JMenuBar(); // cria a barra de menu
		setJMenuBar( barra );          // adiciona a barra de menu bar para na janela
		barra.add( planejamentoMenu ); // adiciona o menu Planejamento para a barra menu
		barra.add( producaoMenu );     // adiciona o menu Producao para a barra menu
		barra.add( vendaMenu );        // adiciona o menu Venda para a barra menu
		barra.add( estoqueMenu );      // adiciona o menu Estoque para a barra menu
		barra.add( financeiroMenu );   // adiciona o menu Financeiro para a barra menu
		barra.add( notaFiscalMenu );   // adiciona o menu Nota-Fiscal para a barra menu
		barra.add(ajudaMenu);

		desktop = new JDesktopPane();
		add(desktop); // adiciona o Painel Pai no no frame
		
		
		//GRUPOS - Adicionar os eventos de menu para chamar as InternalFrame
		//Exemplo - TRATAMENTO DOS EVENTOS DOS ITENS DO MENU PARA A SUA CHANELA
		planejamentoItemMenu1.addActionListener(
				new ActionListener(){ // classe interna anonima
					public void actionPerformed( ActionEvent event ){
						
						// Aqui cria a o InternalFrame
						//ModuloVisaoInternalFrame moduloAddInterFrame = new ModuloVisaoInternalFrame();
						//Configurar o Internal Frame conforme a necessidade
						//Nao permite alterar tamanho
						//moduloAddInterFrame.setResizable(false);
						//Tamanho da Janela
						//moduloAddInterFrame.setSize( 300, 300 ); // set frame size
						//Fechar a janela
						//moduloAddInterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						//Adiciona a janela para ser gerenciado pelo desktop
						//desktop.add(moduloAddInterFrame);
						//moduloAddInterFrame.setVisible(true);
					} // fim  metodo actionPerformed
				} // fim classe interna anonima 
				); // fim chamada para addActionListener


		sairItem.addActionListener(
				new ActionListener() {
					public void actionPerformed( ActionEvent event ){
						JOptionPane.showMessageDialog( Cooperativa.this,
								"Saindo da aplica��o",
										"Fechando aplica��o", JOptionPane.PLAIN_MESSAGE );

						System.exit( 0 ); // encerra aplica��o
					} // fim  metodo actionPerformed
				} // fim classe interna anonima 
				); // fim chamada para addActionListener

		sobreItem.addActionListener(
				new ActionListener(){ // anonymous inner class
					public void actionPerformed( ActionEvent event ){
						JOptionPane.showMessageDialog( Cooperativa.this,
								"Nesta op��o gostaria de colocar os grupos , componentes"
								+ "e o email",
								"Sobre", JOptionPane.PLAIN_MESSAGE );
					} // fim  metodo actionPerformed
				} // fim classe interna anonima 
				); // fim chamada para addActionListener


	} // fim menu 

	
}
