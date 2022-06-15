package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped();
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
	public RegistroHuesped() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/persona.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBounds(576, 150, 255, 33);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBounds(576, 217, 255, 33);
		contentPane.add(txtApellido);
		
		JDateChooser txtFechaN = new JDateChooser();
		txtFechaN.setBounds(576, 281, 255, 33);
		contentPane.add(txtFechaN);
		
		JComboBox txtNacionalidad = new JComboBox();
		txtNacionalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan – Afeganistão", "Afghan – afegão", "Andorra – Andorra", "Andorran – andorrano", "Angola – Angola", "Angolan – angolano", "Antigua e Barbuda – Antígua e Barbuda", "Antiguan/Barbudan – antiguano", "Algeria – Argélia", "Algerian – argelino", "Argentina – Argentina", "Argentinian – argentino", "Armenia – Armênia", "Armenian – armênio", "Australia – Austrália", "Australian – australiano", "Austria – Áustria", "Austrian – austríaco", "Azerbaijan – Azerbaijão", "Azerbaijani – azeri", "The Bahamas – Bahamas", "Bahamian – bahamense", "Bangladesh – Bangladesh", "Bangladeshi – bangladês", "Barbados – Barbados", "Barbadian – barbadiano", "Bahrain – Barém", "Bahraini – baremita", "Belarus – Bielorrússia", "Belarusian – bielorrusso", "Belgium – Bélgica", "Belgian – belga", "Belize – Belize", "Belizean – belizenho", "Benin – Benim", "Beninese – beninense", "Bolivia – Bolívia", "Bolivian – boliviano", "Bosnia; Bosnia and Herzegovina – Bósnia; Bósnia e Herzegovina", "Bosnian – bósnio", "Botswana – Botsuana", "Motswana – bechuano", "Brazil – Brasil", "Brazilian – brasileiro", "Brunei – Brunei", "Bruneian – bruneano", "Bulgaria – Bulgária", "Bulgarian – búlgaro", "BurkinaFaso – BurkinaFaso", "Burkinabé – burquinense", "Burundi – Burundi", "Burundian – burundês", "Bhutan – Butão", "Bhutanese – butanense", "Cape Verde – Cabo Verde", "Cape Verdean – cabo-verdiano", "Cameroon – Camarões", "Cameroonian – camaronense", "Cambodia – Camboja", "Cambodian – cambojano", "", "Canada – Canadá", "Canadian – canadense", "", "Central African Republic – República Centro-Africana", "Central-african – centroafricano", "", "Chad – Chade", "Chadian – chadiano", "", "China – China", "Chinese – chinês", "", "Chile – Chile", "Chilean – chileno", "", "Cook Islands – Ilhas Cook", "Cook Islander – cookiano", "", "Colombia – Colômbia", "Colombian – colombiano", "", "Comoros – Comores", "Comoran – comoriano", "", "Costa Rica – Costa Rica", "Costa Rican – costa-riquenho", "", "Croatia – Croácia", "Croatian – croata", "", "Cuba – Cuba", "Cuban – Cubano", "", "Cyprus – Chipre", "Cypriot – cipriota", "", "Czech Republic – República Tcheca", "Czech – tcheco", "", "Democratic Republic of Congo – República Democrática do Congo", "Congolese – congolense", "", "Denmark – Dinamarca", "Danish – dinamarquês", "", "Djibouti – Djibuti", "Djiboutian – djibutiense", "", "Dominica – Dominica", "Dominican – dominiquense", "", "Dominican Republic – República Dominicana", "Dominican – dominicano", "", "East Timor – Timor Leste", "East Timorese – timorense", "", "Ecuador – Equador", "Ecuadorian – equatoriano", "", "Egypt – Egito", "Egyptian – egípcio", "", "El Salvador – El Salvador", "Salvadorean – salvadorenho", "", "England – Inglaterra", "English – inglês", "", "Equatorial Guinea – Guiné Equatorial", "Equatoguinean – guinéu-equatoriano", "", "Eritrea – Eritreia", "Eritrean – eritreu", "", "Estônia – Estônia", "Estonian – estoniano", "", "Fiji – Fiji", "Fijian – fijiano", "", "Finland – Finlândia", "Finnish – finlandês", "", "France – França", "French – francês", "", "Gabon – Gabão", "Gabonese – gabonense", "", "Gambia – Gâmbia", "Gambian – gambiano", "", "Georgia – Geórgia", "Georgian – geórgico", "", "Germany – Alemanha", "German – alemão", "", "Grenada – Granada", "Grenadian – granadino", "", "Greece – Grécia", "Greek – grego", "", "Guatemala – Guatemala", "Guatemalan – guatemalteco", "", "Guinea – Guiné", "Guinean – guineano", "", "Guinea–Bissau – GuinéBissau", "Bissau–guinean – guineense", "", "Guyana – Guiana", "Guyanese – guianense", "", "Haiti – Haiti", "Haitian – haitiano", "", "Holland – Holanda", "Dutch – holandês", "", "Honduras – Honduras", "Honduran – hondurenho", "", "Hungary – Hungria", "Hungarian – húngaro", "", "Iceland – Islândia", "Icelander – islandês", "", "India – Índia", "Indian – indiano", "", "Indonesia – Indonésia", "Indonesian – indonésio", "", "Iran – Irã", "Iranian – iraniano", "", "Ireland – Irlanda", "Irish – irlandês", "", "Israel – Israel", "Israeli – israelita", "", "Italy – Itália", "Italian – italiano", "", "Ivory Coast – Costa do Marfim", "Ivorian– costa-marfinense", "", "Jamaica – Jamaica", "Jamaican – jamaicano", "", "Japan – Japão", "Japanese – japonês", "", "Jordan – Jordânia", "Jordanian – jordão", "", "Kazakhstan – Cazaquistão", "Kazakh – cazaque", "", "Kenya – Quênia", "Kenyan – queniano", "", "Kiribati – Quiribati", "I-kiribati – quiribatiano", "", "Kyrgyzstan – Quirguistão", "Kyrgyzstani – quirguistanês", "", "Kwait – Kuwait", "Kwaiti – kuwaitiano", "", "Laos – Laos", "Laotian – laosiano", "", "Latvia – Letônia", "Latvian – letoniano", "", "Lebanon – Líbano", "Lebanese – libanês", "", "Lesotho – Lesoto", "Basotho – lesotiano", "", "Liberia – Libéria", "Liberian – liberiano", "", "Liechtenstein – Liechtenstein", "Liechtensteiner – liechtensteinense", "", "Lithuania – Lituânia", "Lithuanian – lituano", "", "Luxembourg – Luxemburgo", "Luxembourgish – luxemburguês", "", "Lybia – Líbia", "Lybian – líbio", "", "Macedonia – Macedônia", "Macedonian – macedônio", "", "Madagascar – Madagascar", "Malagasy – madagascarense", "", "Malaysia – Malásia", "Malaysian – malaio", "", "Malawi – Malaui", "Malawian – malauiano", "", "Maldives – Maldivas", "Maldivian – maldivo", "", "Mali – Máli", "Malian – maliano", "", "Malta – Malta", "Maltese – maltês", "", "Mauritius – Maurício", "Mauritian – mauriciano", "", "Mauritia – Mauritânia", "Mauritanian – mauritano", "", "Marshall Island – Ilhas Marshall", "Marshall Islander – marshallino", "", "Micronesia/Federated States of Micronesia – Estados Federados da Micronésia", "Micronesian – micronésio", "", "Mexico – México", "Mexican – mexicano", "", "Morocco – Marrocos", "Moroccan – marroquino", "", "Moldova – Moldavia", "Moldovan – moldávio", "", "Monaco – Mônaco", "Monacan – monegasco", "", "Mongolia – Mongólia", "Mongolian – mongol", "", "Montenegro – Montenegro", "Montenegrin – montenegrino", "", "Mozambique – Moçambique", "Mozambican – moçambicano", "", "Myanmar – Myanmar", "Burmese – birmanês", "", "Namibia – Namíbia", "Namibian – namibiano", "", "Nauru – Nauru", "Nauruan – nauruano", "", "Nepal – Nepal", "Nepali – nepalês", "", "New Zealand – Nova Zelândia", "NewZealander – neozelandês", "", "Nicaragua – Nicarágua", "Nicaraguan – nicaraguense", "", "Niger – Níger", "Nigerien – nigerino", "", "Nigeria – Nigéria", "Nigerian – nigeriano", "", "Niue – Niue", "Niuean – niuano", "", "North Korea – Coréia do Norte", "North korean – norte-coreano", "", "Norway – Noruega", "Norwegian – norueguês", "", "Oman – Omã", "Omani – omanense", "", "Palestine – Palestina", "Palestinian – palestino", "", "Pakistan – Paquistão", "Pakistanese – paquistanês", "", "Palau – Palau", "Palauan – palauense", "", "Panama – Panamá", "Panamanian – panamenho", "", "Papua New Guinea – Papua Nova Guiné", "Papua New Guinean – papuásio", "", "Paraguay – Paraguai", "Paraguayan – paraguaio", "", "Peru – Peru", "Peruvian – peruano", "", "Philippines – Philippines", "Philippine – filipino", "", "Poland – Polônia", "Polish – polonês", "", "Portugal – Portugal", "Portuguese – português", "", "Qatar – Catar", "Qatari – catarense", "", "Romania – Romênia", "Romanian – romeno", "", "Russia – Rússia", "Russian – russo", "", "Rwanda – Ruanda", "Rwandan – ruandês", "", "Samoa – Samoa", "Samoan – samoano", "", "Saint Lucia – Santa Lúcia", "Saint Lucian – santa-lucense", "", "Saint Kitts and Nevis – São Cristóvão e Nevis", "Kittian – são-cristovense", "", "San Marino – São Marino", "San Marinan – são-marinense", "", "Sao Tomé and Principe – São Tomé e Príncipe", "Sao Tomean – são-tomense", "", "Saint Vincent and the Grenadines – São Vicente e Granadinas", "Vicentinian – são-vicentino", "", "Scotland – Escócia", "Scottish – escocês", "", "Senegal – Senegal", "Senegalese – senegalense", "", "Serbia – Sérvia", "Serbian – sérvio", "", "Seychelles – Seicheles", "Seychellois – seichelense", "", "Sierra Leone – Serra Leoa", "Sierra Leonean – serra-leonês", "", "Singapore – Singapura", "Singaporean – singapurense", "", "Slovakia – Eslováquia", "Slovak – eslovaco", "", "Solomon Islands – Ilhas Salomão", "Solomon Islander – salomônico", "", "Somalia – Somália", "Somali – somali", "", "South Africa – África do Sul", "South African – sul–africano", "", "South Korea – Coréia do Sul", "Korean – coreano", "", "South Sudan – Sudão do Sul", "South Sudanese – sul-sudanense", "", "Spain – Espanha", "Spanish – espanhol", "", "Sri Lanka – Sri Lanka", "Sri Lankan – srilankês", "", "Sudan – Sudão", "Sudanese – sudanense", "", "Suriname – Suriname", "Surinamese – surinamês", "", "Swaziland – Suazilândia", "Swazi – suazi", "", "Sweden – Suécia", "Swedish – sueco", "", "Switzerland – Suíça", "Swiss – suíço", "", "Syria – Síria", "Syrian – sírio", "", "Tajikistan – Tadiquistão", "Tajiki – tajique", "Tanzanian – tanzaniano", "Thailand – Tailândia", "Thai – tailandês", "Togo – Togo", "Togolese – togolês", "Tonga – Tonga", "Tongan – tonganês", "Trinidad and Tobago – Trindade e Tobago", "Trinidadian – trinitário", "", "Tunisia – Tunísia", "Tunisian – tunisiano", "Turkmenistan – Turcomenistão", "Turkmen – turcomeno", "Turkey – Turquia", "Turkish – turco", "Tuvalu – Tuvalu", "Tuvaluan – tuvaluano", "Ukraine – Ucrânia", "Ukrainian – ucraniano", "Uganda – Uganda", "Ugandan – ugandês", "Uruguay – Uruguai", "Uruguayan – uruguaio", "United Arab Emirates – Emirados Árabes Unidos", "Emirati – árabe", "United Kingdom – Reino Unido", "British – britânico", "United States of America – Estados Unidos", "American – americano", "Uzbekistan – Usbequistão", "Uzbek – uzbeque", "Vanuatu – Vanuatu", "Ni-vanuatu – vanuatuano", "Venezuela – Venezuela", "Venezuelan – venezuelano", "Vietnam – Vietnã", "Vietnamese – vietnamita", "Wales – País de Gales", "Welsh – galês", "Yemen – Iêmen", "Yemeni – iemenita", "Zambia – Zâmbia", "Zambian – zambiano", "Zimbabwe – Zimbábue", "Zimbabwean – zimbabueano"}));
		txtNacionalidad.setBounds(576, 350, 255, 33);
		contentPane.add(txtNacionalidad);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(578, 125, 253, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(576, 194, 255, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha de Nascimiento");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(576, 256, 255, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nacionalidad");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(576, 325, 255, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		lblNewLabel.setBounds(0, 0, 502, 556);
		contentPane.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(764, 543, 54, 41);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exito exito = new Exito();
				exito.setVisible(true);
				dispose();
			}
		});
		btnGuardar.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/disquete.png")));
		btnGuardar.setBackground(SystemColor.menu);
		btnGuardar.setBounds(700, 543, 54, 41);
		contentPane.add(btnGuardar);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setBackground(SystemColor.menu);
		btnSalir.setBounds(828, 543, 54, 41);
		contentPane.add(btnSalir);
		
		JLabel lblNewLabel_1_2 = new JLabel("Teléfono");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(578, 394, 253, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBounds(576, 419, 255, 33);
		contentPane.add(txtTelefono);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(780, 11, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Registro de Huésped");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4.setBounds(576, 74, 198, 42);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Número de Reserva");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(578, 455, 253, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtNreserva = new JTextField();
		txtNreserva.setEnabled(false);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBounds(576, 480, 255, 33);
		contentPane.add(txtNreserva);
	}
}
