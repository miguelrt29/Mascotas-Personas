package ventana;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("SISTEMA VETERINARIA ABC");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("SISTEMA VETERINARIA ABC");
        titulo.setBounds(100, 20, 250, 30);
        add(titulo);

        JButton btnPersonas = new JButton("Gestionar Personas");
        btnPersonas.setBounds(100, 70, 200, 40);
        add(btnPersonas);

        JButton btnMascotas = new JButton("Gestionar Mascotas");
        btnMascotas.setBounds(100, 130, 200, 40);
        add(btnMascotas);

        btnPersonas.addActionListener(e -> {
            new VentanaGestionarPersonas();
            dispose();
        });

        btnMascotas.addActionListener(e -> {
            new VentanaGestionarMascota();
            dispose();
        });

        setVisible(true);
    }
}
