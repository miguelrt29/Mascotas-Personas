package ventana;

import dao.PersonaDAO;
import vo.PersonaVO;

import javax.swing.*;

public class VentanaPersonas extends JFrame {
    private JTextField txtDocumento, txtNombre, txtTelefono, txtCorreo;
    private JTextArea areaResultado;
    private PersonaDAO personaDAO = new PersonaDAO();

    public VentanaPersonas() {
        setTitle("Gestión de Personas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(20, 20, 100, 25);
        add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(130, 20, 150, 25);
        add(txtDocumento);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 60, 150, 25);
        add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 100, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(130, 100, 150, 25);
        add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 140, 100, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(130, 140, 150, 25);
        add(txtCorreo);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(300, 20, 150, 25);
        add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(300, 60, 150, 25);
        add(btnConsultar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(300, 100, 150, 25);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(300, 140, 150, 25);
        add(btnEliminar);

        JButton btnLista = new JButton("Consultar lista");
        btnLista.setBounds(20, 180, 430, 25);
        add(btnLista);

        areaResultado = new JTextArea();
        areaResultado.setBounds(20, 220, 430, 180);
        areaResultado.setEditable(false);
        add(areaResultado);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBounds(20, 420, 430, 25);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> {
            PersonaVO persona = new PersonaVO(
                    txtDocumento.getText(),
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText()
            );
            if (personaDAO.insertar(persona)) {
                areaResultado.setText("✅ Persona registrada:\n" + persona.mostrarInformacion());
            } else {
                areaResultado.setText("❌ Error al registrar persona.");
            }
        });

        btnConsultar.addActionListener(e -> {
            PersonaVO persona = personaDAO.consultar(txtDocumento.getText());
            if (persona != null) {
                txtNombre.setText(persona.getNombre());
                txtTelefono.setText(persona.getTelefono());
                txtCorreo.setText(persona.getCorreo());
                areaResultado.setText("✅ Persona encontrada:\n" + persona.mostrarInformacion());
            } else {
                areaResultado.setText("❌ Persona no encontrada.");
            }
        });

        btnActualizar.addActionListener(e -> {
            PersonaVO persona = new PersonaVO(
                    txtDocumento.getText(),
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText()
            );
            if (personaDAO.actualizar(persona)) {
                areaResultado.setText("✅ Persona actualizada:\n" + persona.mostrarInformacion());
            } else {
                areaResultado.setText("❌ Error al actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (personaDAO.eliminar(txtDocumento.getText())) {
                areaResultado.setText("✅ Persona eliminada.");
                txtNombre.setText("");
                txtTelefono.setText("");
                txtCorreo.setText("");
            } else {
                areaResultado.setText("❌ Error al eliminar.");
            }
        });

        btnLista.addActionListener(e -> {
            var lista = personaDAO.listar();
            if (lista.isEmpty()) {
                areaResultado.setText("📭 No hay personas registradas.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (PersonaVO p : lista) {
                    sb.append(p.mostrarInformacion()).append("\n------------------\n");
                }
                areaResultado.setText(sb.toString());
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaPrincipal();
        });

        setVisible(true);
    }
}
