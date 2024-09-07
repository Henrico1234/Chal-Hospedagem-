package br.com.prog2.hopedagem.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.prog2.hopedagem.model.Hospedagem;
import br.com.prog2.hopedagem.persistencia.HospedagemDAO;
import br.com.prog2.hopedagem.persistencia.ChaleDAO;

public class HospedagemForm {
    private JFrame frame;
    private JTextField codChaleField;
    private JTextField codClienteField;
    private JTextField estadoField;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JTextField qtdPessoasField;
    private JTextField descontoField;
    private JTextField valorFinalField;

    public HospedagemForm() {
        frame = new JFrame("Cadastro de Hospedagem");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

     
        setupForm();

        JButton saveButton = new JButton("Salvar");
        saveButton.setBounds(150, 270, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Obter informações da interface
                    int codChale = Integer.parseInt(codChaleField.getText());
                    int codCliente = Integer.parseInt(codClienteField.getText());
                    String estado = estadoField.getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dataInicio = sdf.parse(dataInicioField.getText());
                    Date dataFim = sdf.parse(dataFimField.getText());
                    int qtdPessoas = Integer.parseInt(qtdPessoasField.getText());
                    double desconto = Double.parseDouble(descontoField.getText());

                     // Verificar se o desconto esta > que 100%
                    if (desconto > 100) {
                        JOptionPane.showMessageDialog(frame, "Limite de 100% de desconto!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    //Verificar disponibilidade do chalé
                    ChaleDAO chaleDAO = new ChaleDAO();
                    boolean chaleDisponivel = chaleDAO.isChaleDisponível(codChale, dataInicio, dataFim);
                    if (!chaleDisponivel) {
                        JOptionPane.showMessageDialog(frame, "O chalé não está disponível para as datas selecionadas.");
                        return;
                    }

                    //Verificar capacidade do chalé
                    int capacidadeChale = chaleDAO.getCapacidadeChale(codChale);
                    if (qtdPessoas > capacidadeChale) {
                        JOptionPane.showMessageDialog(frame, "A quantidade de pessoas excede a capacidade do chalé.");
                        return;
                    }

                    //Obter valor do chalé
                    double valorChale = chaleDAO.getValorChale(codChale, dataInicio, dataFim);

                    //Calcular o valor final com desconto
                    double valorFinal = valorChale * (1 - (desconto / 100));
                    valorFinalField.setText(String.format("%.2f", valorFinal));

                    //Mostrar mensagem com valor final e opções para confirmar ou cancelar
                    int resposta = JOptionPane.showConfirmDialog(frame, 
                            "Hospedagem salva com sucesso! Valor Final: " + valorFinal + 
                            "\nDeseja confirmar a reserva?", 
                            "Confirmação", 
                            JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                       
                        Hospedagem hospedagem = new Hospedagem();
                        hospedagem.setCodChale(codChale);
                        hospedagem.setCodCliente(codCliente);
                        hospedagem.setEstado(estado);
                        hospedagem.setDataInicio(dataInicio);
                        hospedagem.setDataFim(dataFim);
                        hospedagem.setQtdPessoas(qtdPessoas);
                        hospedagem.setDesconto(desconto);
                        hospedagem.setValorFinal(valorFinal);

                        HospedagemDAO hospedagemDAO = new HospedagemDAO();
                        hospedagemDAO.inserirHospedagem(hospedagem);

                        JOptionPane.showMessageDialog(frame, "Hospedagem confirmada com sucesso!");
                        frame.dispose(); 
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Data inválida.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Formato de número inválido.");
                }
            }
        });
        frame.add(saveButton);

        frame.setVisible(true);
    }

    private void setupForm() {
        JLabel codChaleLabel = new JLabel("Código do Chalé:");
        codChaleLabel.setBounds(20, 20, 120, 25);
        frame.add(codChaleLabel);

        codChaleField = new JTextField();
        codChaleField.setBounds(150, 20, 200, 25);
        frame.add(codChaleField);

        JLabel codClienteLabel = new JLabel("Código do Cliente:");
        codClienteLabel.setBounds(20, 50, 120, 25);
        frame.add(codClienteLabel);

        codClienteField = new JTextField();
        codClienteField.setBounds(150, 50, 200, 25);
        frame.add(codClienteField);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(20, 80, 100, 25);
        frame.add(estadoLabel);

        estadoField = new JTextField();
        estadoField.setBounds(150, 80, 200, 25);
        frame.add(estadoField);

        JLabel dataInicioLabel = new JLabel("Data Início:");
        dataInicioLabel.setBounds(20, 110, 100, 25);
        frame.add(dataInicioLabel);

        dataInicioField = new JTextField();
        dataInicioField.setBounds(150, 110, 200, 25);
        frame.add(dataInicioField);

        JLabel dataFimLabel = new JLabel("Data Fim:");
        dataFimLabel.setBounds(20, 140, 100, 25);
        frame.add(dataFimLabel);

        dataFimField = new JTextField();
        dataFimField.setBounds(150, 140, 200, 25);
        frame.add(dataFimField);

        JLabel qtdPessoasLabel = new JLabel("Quantidade Pessoas:");
        qtdPessoasLabel.setBounds(20, 170, 150, 25);
        frame.add(qtdPessoasLabel);

        qtdPessoasField = new JTextField();
        qtdPessoasField.setBounds(170, 170, 180, 25);
        frame.add(qtdPessoasField);

        JLabel descontoLabel = new JLabel("Desconto:");
        descontoLabel.setBounds(20, 200, 100, 25);
        frame.add(descontoLabel);

        descontoField = new JTextField();
        descontoField.setBounds(150, 200, 200, 25);
        frame.add(descontoField);

        JLabel valorFinalLabel = new JLabel("Valor Final:");
        valorFinalLabel.setBounds(20, 230, 100, 25);
        frame.add(valorFinalLabel);

        valorFinalField = new JTextField();
        valorFinalField.setBounds(150, 230, 200, 25);
        valorFinalField.setEditable(false); // O campo valor final não vai ser editavel pelo usuario 
        frame.add(valorFinalField);
    }

    public static void main(String[] args) {
        new HospedagemForm();
    }
}
