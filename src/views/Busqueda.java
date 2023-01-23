package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.HuespedController;
import controllers.ReservaController;
import models.Huesped;
import models.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservaController reservaController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		modelo = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return !(column == 0);
		    }
		};
		tbReservas = new JTable(modelo);
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas,
				null);
		
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		cargarTablaReservas();

		modeloH = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return !(column == 0 || column == getColumnCount() - 1);
		    }
		};
		tbHuespedes = new JTable(modeloH);
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes,
				null);
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelo.setRowCount(0);
				modeloH.setRowCount(0);
				cargarBusqueda(txtBuscar.getText());

			}

		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = panel.getSelectedIndex();
				
				if (selectedIndex == 0) {
					if (tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Por favor, elija un registro.");
			            return;
					}
					
					Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						//Date fechaEntrada = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
						//Date fechaSalida = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
						Date fechaEntrada = null;
						try {
							fechaEntrada = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
						} catch (ClassCastException e2) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							try {
								fechaEntrada = sdf.parse((String) modelo.getValueAt(tbReservas.getSelectedRow(), 1));
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(contentPane, "Formato no válido");
								return;
							}
						}
						Date fechaSalida = null;
						try {
							fechaSalida = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
						} catch (ClassCastException e2) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							try {
								fechaSalida = sdf.parse((String) modelo.getValueAt(tbReservas.getSelectedRow(), 2));
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(contentPane, "Formato no válido");
								return;
							}
						}
						
						Double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());;
						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
						
						int filasModificadas = reservaController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
						JOptionPane.showMessageDialog(contentPane, String.format("%d item modificado con éxito!", filasModificadas));
					}
							, () -> JOptionPane.showMessageDialog(contentPane, "Por favor, elije un registro"));
					
				} else {
					if (tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Por favor, elija un registro.");
			            return;
					}
					Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2);
						//Date fechaNacimiento = (Date) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3);
						Date fechaNacimiento = null;

						try {
							fechaNacimiento = (Date) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3);
						} catch (ClassCastException e2) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							try {
								fechaNacimiento = sdf.parse((String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3));
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(contentPane, "Formato no válido");
								return;
							}
						}

						String nacionalidad = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4);
						String telefono = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5);
						Integer idReserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
						
						int filasModificadas = huespedController.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
						JOptionPane.showMessageDialog(contentPane, String.format("%d item modificado con éxito!", filasModificadas));
					}
					, () -> JOptionPane.showMessageDialog(contentPane, "Por favor, elije un registro"));
					
				}
			}
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = panel.getSelectedIndex();
				
				if (selectedIndex == 0) {
					if (tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Por favor, elija un registro.");
			            return;
					}

					Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						
						int filasModificadas = reservaController.eliminar(id);
						
						//modelo.removeRow(tbReservas.getSelectedRow());
						modelo.setRowCount(0);
						modeloH.setRowCount(0);
						cargarBusqueda(txtBuscar.getText());
						
						JOptionPane.showMessageDialog(contentPane, String.format("%d item modificado con éxito!", filasModificadas));
					}
							, () -> JOptionPane.showMessageDialog(contentPane, "Por favor, elije un registro"));

				} else {
					if (tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Por favor, elija un registro.");
			            return;
					}
					
					Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						
						int filasModificadas = huespedController.eliminar(id);

						modelo.setRowCount(0);
						modeloH.setRowCount(0);
						cargarBusqueda(txtBuscar.getText());
						
						JOptionPane.showMessageDialog(contentPane, String.format("%d item modificado con éxito!", filasModificadas));
					}
							, () -> JOptionPane.showMessageDialog(contentPane, "Por favor, elije un registro"));
					
				}
				
			}
		});

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	protected void cargarBusqueda(String text) {
		List<Reserva> resultados = this.reservaController.cargarBusqueda(text);
		resultados.forEach(resultado -> {
			resultado.getHuespedes().forEach(huespedR -> modeloH.addRow(new Object[] {
					huespedR.getId(),
					huespedR.getNombre(),
					huespedR.getApellido(),
					huespedR.getFechaNacimiento(),
					huespedR.getNacionalidad(),
					huespedR.getTelefono(), 
					huespedR.getIdReserva() }));
				modelo.addRow(new Object[] {
						resultado.getId(),
						resultado.getFechaEntrada(),
						resultado.getFechaSalida(),
						resultado.getValor(),
						resultado.getFormaPago() });			
			});
		
	}

	private void cargarTablaHuespedes() {
		List<Huesped> huespedes = this.huespedController.listar();
		
		huespedes.forEach(huesped -> modeloH.addRow(
				new Object[] {
						huesped.getId(),
						huesped.getNombre(),
						huesped.getApellido(),
						huesped.getFechaNacimiento(),
						huesped.getNacionalidad(),
						huesped.getTelefono(), 
						huesped.getIdReserva() }));
		
	}

	private void cargarTablaReservas() {
		List<Reserva> reservas = this.reservaController.listar();
		
		reservas.forEach(reserva -> modelo.addRow(
				new Object[] {
						reserva.getId(),
						reserva.getFechaEntrada(),
						reserva.getFechaSalida(),
						reserva.getValor(),
						reserva.getFormaPago() }));

	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
