package ventana;

import javax.swing.*;
import procesos.ProcesosMascota;

public class VentanaGestionarMascota extends JFrame {

    private JTextField txtNombre, txtDuenio, txtRaza, txtSexo;
    private JTextArea area;

    public VentanaGestionarMascota() {
        setTitle("GESTIONAR MASCOTAS");
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 20, 120, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(160, 20, 280, 25);
        add(txtNombre);

        JLabel lblDuenio = new JLabel("Documento Dueño:");
        lblDuenio.setBounds(30, 60, 120, 25);
        add(lblDuenio);

        txtDuenio = new JTextField();
        txtDuenio.setBounds(160, 60, 280, 25);
        add(txtDuenio);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setBounds(30, 100, 120, 25);
        add(lblRaza);

        txtRaza = new JTextField();
        txtRaza.setBounds(160, 100, 280, 25);
        add(txtRaza);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(30, 140, 120, 25);
        add(lblSexo);

        txtSexo = new JTextField();
        txtSexo.setBounds(160, 140, 280, 25);
        add(txtSexo);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(30, 190, 100, 30);
        add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(140, 190, 100, 30);
        add(btnConsultar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(250, 190, 100, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(360, 190, 100, 30);
        add(btnEliminar);

        JButton btnConsultarLista = new JButton("Consultar lista");
        btnConsultarLista.setBounds(30, 230, 430, 30);
        add(btnConsultarLista);

        area = new JTextArea();
        area.setBounds(30, 270, 430, 140);
        area.setEditable(false);
        add(area);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 430, 90, 30); 
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            new VentanaPrincipal();
            dispose();
        });

        ProcesosMascota logica = new ProcesosMascota(txtNombre, txtDuenio, txtRaza, txtSexo, area);

        btnRegistrar.addActionListener(e -> logica.registrar());
        btnConsultar.addActionListener(e -> logica.consultar());
        btnActualizar.addActionListener(e -> logica.actualizar());
        btnEliminar.addActionListener(e -> logica.eliminar());
        btnConsultarLista.addActionListener(e -> logica.consultarLista());

        setVisible(true);
    }
}
