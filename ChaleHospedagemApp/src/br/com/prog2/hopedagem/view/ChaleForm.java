package br.com.prog2.hopedagem.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.prog2.hopedagem.model.Chale;
import br.com.prog2.hopedagem.persistencia.ChaleDAO;

public class ChaleForm {
    private JFrame frame;
    private JTextField localizacaoField;
    private JTextField capacidadeField;
    private JTextField valorAltaEstacaoField;
    private JTextField valorBaixaEstacaoField;

    public ChaleForm() {
        frame = new JFrame("Cadastro de Chalé");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel localizacaoLabel = new JLabel("Localização:");
        localizacaoLabel.setBounds(20, 20, 100, 25);
        frame.add(localizacaoLabel);

        localizacaoField = new JTextField();
        localizacaoField.setBounds(120, 20, 200, 25);
        frame.add(localizacaoField);

        JLabel capacidadeLabel = new JLabel("Capacidade:");
        capacidadeLabel.setBounds(20, 50, 100, 25);
        frame.add(capacidadeLabel);

        capacidadeField = new JTextField();
        capacidadeField.setBounds(120, 50, 200, 25);
        frame.add(capacidadeField);

        JLabel valorAltaEstacaoLabel = new JLabel("Valor Alta Estação:");
        valorAltaEstacaoLabel.setBounds(20, 80, 150, 25);
        frame.add(valorAltaEstacaoLabel);

        valorAltaEstacaoField = new JTextField();
        valorAltaEstacaoField.setBounds(170, 80, 150, 25);
        frame.add(valorAltaEstacaoField);

        JLabel valorBaixaEstacaoLabel = new JLabel("Valor Baixa Estação:");
        valorBaixaEstacaoLabel.setBounds(20, 110, 150, 25);
        frame.add(valorBaixaEstacaoLabel);

        valorBaixaEstacaoField = new JTextField();
        valorBaixaEstacaoField.setBounds(170, 110, 150, 25);
        frame.add(valorBaixaEstacaoField);

        JButton saveButton = new JButton("Salvar");
        saveButton.setBounds(150, 150, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chale chale = new Chale();
                chale.setLocalizacao(localizacaoField.getText());
                chale.setCapacidade(Integer.parseInt(capacidadeField.getText()));
                chale.setValorAltaEstacao(Double.parseDouble(valorAltaEstacaoField.getText()));
                chale.setValorBaixaEstacao(Double.parseDouble(valorBaixaEstacaoField.getText()));

                ChaleDAO chaleDAO = new ChaleDAO();
                chaleDAO.inserirChale(chale);

                JOptionPane.showMessageDialog(frame, "Chalé salvo com sucesso!");
            }
        });
        frame.add(saveButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ChaleForm();
    }
}