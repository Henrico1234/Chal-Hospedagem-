package br.com.prog2.hopedagem.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.prog2.hopedagem.model.Cliente;
import br.com.prog2.hopedagem.persistencia.ClienteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteForm {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField rgField;
    private JTextField enderecoField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JTextField cepField;
    private JTextField nascimentoField;

    public ClienteForm() {
        frame = new JFrame("Cadastro de Cliente");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Labels and Fields
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(20, 20, 100, 25);
        frame.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(120, 20, 200, 25);
        frame.add(nomeField);

        JLabel rgLabel = new JLabel("RG:");
        rgLabel.setBounds(20, 50, 100, 25);
        frame.add(rgLabel);

        rgField = new JTextField();
        rgField.setBounds(120, 50, 200, 25);
        frame.add(rgField);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setBounds(20, 80, 100, 25);
        frame.add(enderecoLabel);

        enderecoField = new JTextField();
        enderecoField.setBounds(120, 80, 200, 25);
        frame.add(enderecoField);

        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(20, 110, 100, 25);
        frame.add(bairroLabel);

        bairroField = new JTextField();
        bairroField.setBounds(120, 110, 200, 25);
        frame.add(bairroField);

        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(20, 140, 100, 25);
        frame.add(cidadeLabel);

        cidadeField = new JTextField();
        cidadeField.setBounds(120, 140, 200, 25);
        frame.add(cidadeField);

        JLabel estadoLabel = new JLabel("Estado(sigla):");
        estadoLabel.setBounds(20, 170, 100, 25);
        frame.add(estadoLabel);

        estadoField = new JTextField();
        estadoField.setBounds(120, 170, 200, 25);
        frame.add(estadoField);

        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(20, 200, 100, 25);
        frame.add(cepLabel);

        cepField = new JTextField();
        cepField.setBounds(120, 200, 200, 25);
        frame.add(cepField);

        JLabel nascimentoLabel = new JLabel("Nascimento:");
        nascimentoLabel.setBounds(20, 230, 100, 25);
        frame.add(nascimentoLabel);

        nascimentoField = new JTextField();
        nascimentoField.setBounds(120, 230, 200, 25);
        frame.add(nascimentoField);

       
        JButton saveButton = new JButton("Salvar");
        saveButton.setBounds(150, 270, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setNomeCliente(nomeField.getText());
                cliente.setRgCliente(rgField.getText());
                cliente.setEnderecoCliente(enderecoField.getText());
                cliente.setBairroCliente(bairroField.getText());
                cliente.setCidadeCliente(cidadeField.getText());
                cliente.setEstadoCliente(estadoField.getText());
                cliente.setCepCliente(cepField.getText());

               
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                    Date nascimento = sdf.parse(nascimentoField.getText());
                    cliente.setNascimentoCliente(nascimento);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Data de nascimento inválida. usar: yyyy-MM-dd ");
                    return;
                }

                ClienteDAO clienteDAO = new ClienteDAO();

               
                if (clienteDAO.existeRg(cliente.getRgCliente())) {
                    JOptionPane.showMessageDialog(frame, "RG já cadastrado.");
                    return; 
                }

                clienteDAO.inserirCliente(cliente);

                JOptionPane.showMessageDialog(frame, "Cliente salvo com sucesso!");
            }
        });
        frame.add(saveButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClienteForm();
    }
}