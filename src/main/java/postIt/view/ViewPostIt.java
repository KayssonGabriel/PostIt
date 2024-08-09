package postIt.view;

import postIt.DAO.ExceptionDAO;
import postIt.DAO.PostItDAO;
import postIt.model.PostIt;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class ViewPostIt extends JFrame {

    private final JPanel postItArea;

    public ViewPostIt() {
        // Configurações da Interface
        setTitle("Interface Post It");
        setSize(510, 550);  // Aumenta o tamanho da janela para acomodar o campo de data
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Texto "Título:"
        JLabel titulo = new JLabel("Título:");
        titulo.setBounds(230, 10, 200, 50);
        add(titulo);

        // Configurações do campo de texto para o Título do Post It
        JTextField campoTitulo = new JTextField(20);
        campoTitulo.setBounds(165, 50, 175, 20);
        add(campoTitulo);

        // Texto "Descrição do Post It:"
        JLabel descricao = new JLabel("Descrição do Post It:");
        descricao.setBounds(195, 70, 200, 50);
        add(descricao);

        // Configurações do campo de texto para a Descrição do Post It
        JTextField campoDescricao = new JTextField(100);
        campoDescricao.setBounds(155, 110, 200, 50);
        add(campoDescricao);

        // Texto "Data:"
        JLabel dataLabel = new JLabel("Data:");
        dataLabel.setBounds(230, 160, 200, 50);
        add(dataLabel);

        // Configurações do campo de data
        Calendar calendar = Calendar.getInstance();
        Date hoje = calendar.getTime();

        SpinnerDateModel dateModel = new SpinnerDateModel(hoje, hoje, null, Calendar.DAY_OF_MONTH);
        JSpinner campoData = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(campoData, "dd/MM/yyyy");
        campoData.setEditor(dateEditor);
        campoData.setBounds(165, 200, 175, 20);
        add(campoData);

        // Configurações do botão para adicionar Post It
        JButton botao = new JButton("Adicionar Post It");
        botao.setBounds(175, 240, 150, 35);
        add(botao);

        // Área para os Post Its
        postItArea = new JPanel();
        postItArea.setLayout(null);
        postItArea.setBounds(50, 290, 400, 200);
        add(postItArea);

        // Listener para o botão
        botao.addActionListener(_ -> adicionarPostIt(campoTitulo.getText(), campoDescricao.getText(), (Date) campoData.getValue()));

        // Deixa todas as interações passadas visíveis
        setVisible(true);
    }

    private void adicionarPostIt(String titulo, String descricao, Date data) {
        int y = postItArea.getComponentCount() * 110; // Incrementa a posição Y para cada novo Post It

        // Cria um novo painel para o Post It
        JPanel postIt = new JPanel();
        postIt.setLayout(null);
        postIt.setBounds(0, y, 400, 100);

        // Área de texto para o Post It
        JTextArea postItTextArea = new JTextArea();
        postItTextArea.setText("Título: " + titulo + "\n\nDescrição: " + descricao + "\n\nData: " + new java.text.SimpleDateFormat("dd/MM/yyyy").format(data));
        postItTextArea.setBounds(10, 10, 380, 80); // Ajusta o tamanho e a posição da área de texto
        postItTextArea.setLineWrap(true);
        postItTextArea.setWrapStyleWord(true);
        postItTextArea.setEditable(false);

        // Adiciona a área de texto ao painel do Post It
        postIt.add(postItTextArea);

        // Adiciona o novo Post It à área de Post Its
        postItArea.add(postIt);
        postItArea.revalidate();
        postItArea.repaint();

        // Salva o Post It no banco de dados
        salvarPostIt(titulo, descricao, data);
    }

    private void salvarPostIt(String titulo, String descricao, Date data) {
        try {
            PostIt postItModel = new PostIt(titulo, descricao, data);
            PostItDAO postItDAO = new PostItDAO();
            postItDAO.savePostIt(postItModel);
        } catch (ExceptionDAO e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewPostIt();
    }
}