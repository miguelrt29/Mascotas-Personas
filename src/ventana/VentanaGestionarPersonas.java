package ventana;

import javax.swing.*;
import procesos.ProcesosPersona;

public class VentanaGestionarPersonas extends JFrame {

    private JTextField txtDoc, txtNombre, txtTel;
    private JTextArea area;

    public VentanaGestionarPersonas() {
        setTitle("GESTIONAR PERSONAS");
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblDoc = new JLabel("Documento:");
        lblDoc.setBounds(30, 20, 100, 25);
        add(lblDoc);

        txtDoc = new JTextField();
        txtDoc.setBounds(140, 20, 300, 25);
        add(txtDoc);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 300, 25);
        add(txtNombre);

        JLabel lblTel = new JLabel("Teléfono:");
        lblTel.setBounds(30, 100, 100, 25);
        add(lblTel);

        txtTel = new JTextField();
        txtTel.setBounds(140, 100, 300, 25);
        add(txtTel);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(30, 150, 100, 30);
        add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(140, 150, 100, 30);
        add(btnConsultar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(250, 150, 100, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(360, 150, 100, 30);
        add(btnEliminar);

        JButton btnConsultarLista = new JButton("Consultar lista");
        btnConsultarLista.setBounds(30, 190, 430, 30);
        add(btnConsultarLista);

        area = new JTextArea();
        area.setBounds(30, 230, 430, 140);
        area.setEditable(false);
        add(area);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 400, 90, 30); 
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            new VentanaPrincipal();
            dispose();
        });

        ProcesosPersona logica = new ProcesosPersona(txtDoc, txtNombre, txtTel, area);

        btnRegistrar.addActionListener(e -> logica.registrar());
        btnConsultar.addActionListener(e -> logica.consultar());
        btnActualizar.addActionListener(e -> logica.actualizar());
        btnEliminar.addActionListener(e -> logica.eliminar());
        btnConsultarLista.addActionListener(e -> logica.consultarLista());

        setVisible(true);
    }
}
