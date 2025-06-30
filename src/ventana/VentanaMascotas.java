package ventana;

import dao.MascotaDAO;
import dao.PersonaDAO;
import vo.MascotaVO;
import vo.PersonaVO;

import javax.swing.*;

public class VentanaMascotas extends JFrame {
    private JTextField txtNombre, txtEspecie, txtRaza, txtEdad, txtDocumentoDueno, txtId;
    private JTextArea areaResultado;
    private MascotaDAO mascotaDAO = new MascotaDAO();
    private PersonaDAO personaDAO = new PersonaDAO();

    public VentanaMascotas() {
        setTitle("Gestión de Mascotas");
        setSize(550, 570);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 20, 150, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 60, 150, 25);
        add(txtNombre);

        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(20, 100, 100, 25);
        add(lblEspecie);

        txtEspecie = new JTextField();
        txtEspecie.setBounds(130, 100, 150, 25);
        add(txtEspecie);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setBounds(20, 140, 100, 25);
        add(lblRaza);

        txtRaza = new JTextField();
        txtRaza.setBounds(130, 140, 150, 25);
        add(txtRaza);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(20, 180, 100, 25);
        add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(130, 180, 150, 25);
        add(txtEdad);

        JLabel lblDueno = new JLabel("Doc. Dueño:");
        lblDueno.setBounds(20, 220, 100, 25);
        add(lblDueno);

        txtDocumentoDueno = new JTextField();
        txtDocumentoDueno.setBounds(130, 220, 150, 25);
        add(txtDocumentoDueno);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(310, 20, 200, 25);
        add(btnRegistrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(310, 60, 200, 25);
        add(btnConsultar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(310, 100, 200, 25);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(310, 140, 200, 25);
        add(btnEliminar);

        JButton btnLista = new JButton("Consultar lista");
        btnLista.setBounds(20, 260, 490, 25);
        add(btnLista);

        areaResultado = new JTextArea();
        areaResultado.setBounds(20, 300, 490, 200);
        areaResultado.setEditable(false);
        add(areaResultado);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBounds(20, 510, 490, 25);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> {
            PersonaVO dueno = personaDAO.consultar(txtDocumentoDueno.getText());
            if (dueno == null) {
                areaResultado.setText("❌ Dueño no registrado.");
                return;
            }

            MascotaVO mascota = new MascotaVO(
                    txtNombre.getText(),
                    txtEspecie.getText(),
                    txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    txtDocumentoDueno.getText()
            );
            if (mascotaDAO.insertar(mascota)) {
                areaResultado.setText("✅ Mascota registrada para " + dueno.getNombre());
            } else {
                areaResultado.setText("❌ Error al registrar mascota.");
            }
        });

        btnConsultar.addActionListener(e -> {
            MascotaVO mascota = mascotaDAO.consultar(txtId.getText());
            if (mascota != null) {
                PersonaVO dueno = personaDAO.consultar(mascota.getDocumentoDueno());
                txtNombre.setText(mascota.getNombre());
                txtEspecie.setText(mascota.getEspecie());
                txtRaza.setText(mascota.getRaza());
                txtEdad.setText(String.valueOf(mascota.getEdad()));
                txtDocumentoDueno.setText(mascota.getDocumentoDueno());
                areaResultado.setText("✅ Mascota encontrada:\n" + mascota.mostrarInfo(dueno != null ? dueno.getNombre() : "Desconocido"));
            } else {
                areaResultado.setText("❌ Mascota no encontrada.");
            }
        });

        btnActualizar.addActionListener(e -> {
            MascotaVO mascota = new MascotaVO(
                    Integer.parseInt(txtId.getText()),
                    txtNombre.getText(),
                    txtEspecie.getText(),
                    txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    txtDocumentoDueno.getText()
            );
            if (mascotaDAO.actualizar(mascota)) {
                areaResultado.setText("✅ Mascota actualizada.");
            } else {
                areaResultado.setText("❌ Error al actualizar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (mascotaDAO.eliminar(txtId.getText())) {
                areaResultado.setText("✅ Mascota eliminada.");
            } else {
                areaResultado.setText("❌ Error al eliminar mascota.");
            }
        });

        btnLista.addActionListener(e -> {
            var lista = mascotaDAO.listar();
            if (lista.isEmpty()) {
                areaResultado.setText("📭 No hay mascotas registradas.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (MascotaVO m : lista) {
                    PersonaVO p = personaDAO.consultar(m.getDocumentoDueno());
                    sb.append(m.mostrarInfo(p != null ? p.getNombre() : "Desconocido")).append("\n------------------\n");
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
