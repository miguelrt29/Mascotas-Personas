package ventana;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Clínica ABC - Sistema");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnPersonas = new JButton("Gestionar Personas");
        btnPersonas.setBounds(100, 40, 200, 30);
        add(btnPersonas);

        JButton btnMascotas = new JButton("Gestionar Mascotas");
        btnMascotas.setBounds(100, 90, 200, 30);
        add(btnMascotas);

        btnPersonas.addActionListener(e -> new VentanaPersonas());
        btnMascotas.addActionListener(e -> new VentanaMascotas());

        setVisible(true);
    }
}
