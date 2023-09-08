/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archiv_text;

/**
 *
 * @author MARTI
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

public class EDITOR_BUENO extends JFrame {
    private JTextPane textPane;
    private JFileChooser fileChooser;
    private JComboBox<String> fontComboBox;
    private JComboBox<Integer> fontSizeComboBox;
    private JButton fontColorButton;
    
    public EDITOR_BUENO() {
        setTitle("Editor de Texto con Formato");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textPane = new JTextPane();
        textPane.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textPane);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        createMenuBar();
        

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        fontComboBox = new JComboBox<>(fontNames);
        fontComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFont((String) fontComboBox.getSelectedItem());
            }
        });
        

        fontSizeComboBox = new JComboBox<>();
        for (int i = 8; i <= 72; i += 2) {
            fontSizeComboBox.addItem(i);
        }
        fontSizeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFontSize((int) fontSizeComboBox.getSelectedItem());
            }
        });
        

        fontColorButton = new JButton("Color de Fuente");
        fontColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFontColor();
            }
        });
        
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Fuente de Texto:"));
        controlPanel.add(fontComboBox);
        controlPanel.add(new JLabel("Tamaño de Fuente:"));
        controlPanel.add(fontSizeComboBox);
        controlPanel.add(fontColorButton);
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        
        fileChooser = new JFileChooser();
        
        setVisible(true);
    }
    
    private void createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu fileMenu = new JMenu("Archivo");
    menuBar.add(fileMenu);

    JMenuItem openMenuItem = new JMenuItem("Abrir");
    openMenuItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            openFile();
        }
    });

    JMenuItem saveMenuItem = new JMenuItem("Guardar");
    saveMenuItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            saveFile();
        }
    });

    fileMenu.add(openMenuItem);
    fileMenu.add(saveMenuItem);
}

    private void openFile() {
    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            FileReader reader = new FileReader(selectedFile);
            textPane.read(reader, null);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

private void saveFile() {
    int returnValue = fileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            FileWriter writer = new FileWriter(selectedFile);
            textPane.write(writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    
    private void setFont(String fontName) {
        Font currentFont = textPane.getFont();
        Font newFont = new Font(fontName, currentFont.getStyle(), currentFont.getSize());
        textPane.setFont(newFont);
    }
    
    private void setFontSize(int size) {
        Font currentFont = textPane.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), size);
        textPane.setFont(newFont);
    }
    
    private void selectFontColor() {
        Color selectedColor = JColorChooser.showDialog(this, "Seleccionar Color de Fuente", textPane.getForeground());
        if (selectedColor != null) {
            textPane.setForeground(selectedColor);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EDITOR_BUENO();
            }
        });
    }
}
