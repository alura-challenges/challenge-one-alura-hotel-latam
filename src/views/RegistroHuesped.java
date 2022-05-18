package views;

import java.awt.BorderLayout;
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

public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\persona.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);
		textField.setBounds(576, 130, 255, 33);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(576, 197, 255, 33);
		contentPane.add(textField_1);
		
		JDateChooser FechaE = new JDateChooser();
		FechaE.setBounds(576, 261, 255, 33);
		contentPane.add(FechaE);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan – Afeganistão", "Afghan – afegão", "Andorra – Andorra", "Andorran – andorrano", "Angola – Angola", "Angolan – angolano", "Antigua e Barbuda – Antígua e Barbuda", "Antiguan/Barbudan – antiguano", "Algeria – Argélia", "Algerian – argelino", "Argentina – Argentina", "Argentinian – argentino", "Armenia – Armênia", "Armenian – armênio", "Australia – Austrália", "Australian – australiano", "Austria – Áustria", "Austrian – austríaco", "Azerbaijan – Azerbaijão", "Azerbaijani – azeri", "The Bahamas – Bahamas", "Bahamian – bahamense", "Bangladesh – Bangladesh", "Bangladeshi – bangladês", "Barbados – Barbados", "Barbadian – barbadiano", "Bahrain – Barém", "Bahraini – baremita", "Belarus – Bielorrússia", "Belarusian – bielorrusso", "Belgium – Bélgica", "Belgian – belga", "Belize – Belize", "Belizean – belizenho", "Benin – Benim", "Beninese – beninense", "Bolivia – Bolívia", "Bolivian – boliviano", "Bosnia; Bosnia and Herzegovina – Bósnia; Bósnia e Herzegovina", "Bosnian – bósnio", "Botswana – Botsuana", "Motswana – bechuano", "Brazil – Brasil", "Brazilian – brasileiro", "Brunei – Brunei", "Bruneian – bruneano", "Bulgaria – Bulgária", "Bulgarian – búlgaro", "BurkinaFaso – BurkinaFaso", "Burkinabé – burquinense", "Burundi – Burundi", "Burundian – burundês", "Bhutan – Butão", "Bhutanese – butanense", "Cape Verde – Cabo Verde", "Cape Verdean – cabo-verdiano", "Cameroon – Camarões", "Cameroonian – camaronense", "Cambodia – Camboja", "Cambodian – cambojano", "", "Canada – Canadá", "Canadian – canadense", "", "Central African Republic – República Centro-Africana", "Central-african – centroafricano", "", "Chad – Chade", "Chadian – chadiano", "", "China – China", "Chinese – chinês", "", "Chile – Chile", "Chilean – chileno", "", "Cook Islands – Ilhas Cook", "Cook Islander – cookiano", "", "Colombia – Colômbia", "Colombian – colombiano", "", "Comoros – Comores", "Comoran – comoriano", "", "Costa Rica – Costa Rica", "Costa Rican – costa-riquenho", "", "Croatia – Croácia", "Croatian – croata", "", "Cuba – Cuba", "Cuban – Cubano", "", "Cyprus – Chipre", "Cypriot – cipriota", "", "Czech Republic – República Tcheca", "Czech – tcheco", "", "Democratic Republic of Congo – República Democrática do Congo", "Congolese – congolense", "", "Denmark – Dinamarca", "Danish – dinamarquês", "", "Djibouti – Djibuti", "Djiboutian – djibutiense", "", "Dominica – Dominica", "Dominican – dominiquense", "", "Dominican Republic – República Dominicana", "Dominican – dominicano", "", "East Timor – Timor Leste", "East Timorese – timorense", "", "Ecuador – Equador", "Ecuadorian – equatoriano", "", "Egypt – Egito", "Egyptian – egípcio", "", "El Salvador – El Salvador", "Salvadorean – salvadorenho", "", "England – Inglaterra", "English – inglês", "", "Equatorial Guinea – Guiné Equatorial", "Equatoguinean – guinéu-equatoriano", "", "Eritrea – Eritreia", "Eritrean – eritreu", "", "Estônia – Estônia", "Estonian – estoniano", "", "Fiji – Fiji", "Fijian – fijiano", "", "Finland – Finlândia", "Finnish – finlandês", "", "France – França", "French – francês", "", "Gabon – Gabão", "Gabonese – gabonense", "", "Gambia – Gâmbia", "Gambian – gambiano", "", "Georgia – Geórgia", "Georgian – geórgico", "", "Germany – Alemanha", "German – alemão", "", "Grenada – Granada", "Grenadian – granadino", "", "Greece – Grécia", "Greek – grego", "", "Guatemala – Guatemala", "Guatemalan – guatemalteco", "", "Guinea – Guiné", "Guinean – guineano", "", "Guinea–Bissau – GuinéBissau", "Bissau–guinean – guineense", "", "Guyana – Guiana", "Guyanese – guianense", "", "Haiti – Haiti", "Haitian – haitiano", "", "Holland – Holanda", "Dutch – holandês", "", "Honduras – Honduras", "Honduran – hondurenho", "", "Hungary – Hungria", "Hungarian – húngaro", "", "Iceland – Islândia", "Icelander – islandês", "", "India – Índia", "Indian – indiano", "", "Indonesia – Indonésia", "Indonesian – indonésio", "", "Iran – Irã", "Iranian – iraniano", "", "Ireland – Irlanda", "Irish – irlandês", "", "Israel – Israel", "Israeli – israelita", "", "Italy – Itália", "Italian – italiano", "", "Ivory Coast – Costa do Marfim", "Ivorian– costa-marfinense", "", "Jamaica – Jamaica", "Jamaican – jamaicano", "", "Japan – Japão", "Japanese – japonês", "", "Jordan – Jordânia", "Jordanian – jordão", "", "Kazakhstan – Cazaquistão", "Kazakh – cazaque", "", "Kenya – Quênia", "Kenyan – queniano", "", "Kiribati – Quiribati", "I-kiribati – quiribatiano", "", "Kyrgyzstan – Quirguistão", "Kyrgyzstani – quirguistanês", "", "Kwait – Kuwait", "Kwaiti – kuwaitiano", "", "Laos – Laos", "Laotian – laosiano", "", "Latvia – Letônia", "Latvian – letoniano", "", "Lebanon – Líbano", "Lebanese – libanês", "", "Lesotho – Lesoto", "Basotho – lesotiano", "", "Liberia – Libéria", "Liberian – liberiano", "", "Liechtenstein – Liechtenstein", "Liechtensteiner – liechtensteinense", "", "Lithuania – Lituânia", "Lithuanian – lituano", "", "Luxembourg – Luxemburgo", "Luxembourgish – luxemburguês", "", "Lybia – Líbia", "Lybian – líbio", "", "Macedonia – Macedônia", "Macedonian – macedônio", "", "Madagascar – Madagascar", "Malagasy – madagascarense", "", "Malaysia – Malásia", "Malaysian – malaio", "", "Malawi – Malaui", "Malawian – malauiano", "", "Maldives – Maldivas", "Maldivian – maldivo", "", "Mali – Máli", "Malian – maliano", "", "Malta – Malta", "Maltese – maltês", "", "Mauritius – Maurício", "Mauritian – mauriciano", "", "Mauritia – Mauritânia", "Mauritanian – mauritano", "", "Marshall Island – Ilhas Marshall", "Marshall Islander – marshallino", "", "Micronesia/Federated States of Micronesia – Estados Federados da Micronésia", "Micronesian – micronésio", "", "Mexico – México", "Mexican – mexicano", "", "Morocco – Marrocos", "Moroccan – marroquino", "", "Moldova – Moldavia", "Moldovan – moldávio", "", "Monaco – Mônaco", "Monacan – monegasco", "", "Mongolia – Mongólia", "Mongolian – mongol", "", "Montenegro – Montenegro", "Montenegrin – montenegrino", "", "Mozambique – Moçambique", "Mozambican – moçambicano", "", "Myanmar – Myanmar", "Burmese – birmanês", "", "Namibia – Namíbia", "Namibian – namibiano", "", "Nauru – Nauru", "Nauruan – nauruano", "", "Nepal – Nepal", "Nepali – nepalês", "", "New Zealand – Nova Zelândia", "NewZealander – neozelandês", "", "Nicaragua – Nicarágua", "Nicaraguan – nicaraguense", "", "Niger – Níger", "Nigerien – nigerino", "", "Nigeria – Nigéria", "Nigerian – nigeriano", "", "Niue – Niue", "Niuean – niuano", "", "North Korea – Coréia do Norte", "North korean – norte-coreano", "", "Norway – Noruega", "Norwegian – norueguês", "", "Oman – Omã", "Omani – omanense", "", "Palestine – Palestina", "Palestinian – palestino", "", "Pakistan – Paquistão", "Pakistanese – paquistanês", "", "Palau – Palau", "Palauan – palauense", "", "Panama – Panamá", "Panamanian – panamenho", "", "Papua New Guinea – Papua Nova Guiné", "Papua New Guinean – papuásio", "", "Paraguay – Paraguai", "Paraguayan – paraguaio", "", "Peru – Peru", "Peruvian – peruano", "", "Philippines – Philippines", "Philippine – filipino", "", "Poland – Polônia", "Polish – polonês", "", "Portugal – Portugal", "Portuguese – português", "", "Qatar – Catar", "Qatari – catarense", "", "Romania – Romênia", "Romanian – romeno", "", "Russia – Rússia", "Russian – russo", "", "Rwanda – Ruanda", "Rwandan – ruandês", "", "Samoa – Samoa", "Samoan – samoano", "", "Saint Lucia – Santa Lúcia", "Saint Lucian – santa-lucense", "", "Saint Kitts and Nevis – São Cristóvão e Nevis", "Kittian – são-cristovense", "", "San Marino – São Marino", "San Marinan – são-marinense", "", "Sao Tomé and Principe – São Tomé e Príncipe", "Sao Tomean – são-tomense", "", "Saint Vincent and the Grenadines – São Vicente e Granadinas", "Vicentinian – são-vicentino", "", "Scotland – Escócia", "Scottish – escocês", "", "Senegal – Senegal", "Senegalese – senegalense", "", "Serbia – Sérvia", "Serbian – sérvio", "", "Seychelles – Seicheles", "Seychellois – seichelense", "", "Sierra Leone – Serra Leoa", "Sierra Leonean – serra-leonês", "", "Singapore – Singapura", "Singaporean – singapurense", "", "Slovakia – Eslováquia", "Slovak – eslovaco", "", "Solomon Islands – Ilhas Salomão", "Solomon Islander – salomônico", "", "Somalia – Somália", "Somali – somali", "", "South Africa – África do Sul", "South African – sul–africano", "", "South Korea – Coréia do Sul", "Korean – coreano", "", "South Sudan – Sudão do Sul", "South Sudanese – sul-sudanense", "", "Spain – Espanha", "Spanish – espanhol", "", "Sri Lanka – Sri Lanka", "Sri Lankan – srilankês", "", "Sudan – Sudão", "Sudanese – sudanense", "", "Suriname – Suriname", "Surinamese – surinamês", "", "Swaziland – Suazilândia", "Swazi – suazi", "", "Sweden – Suécia", "Swedish – sueco", "", "Switzerland – Suíça", "Swiss – suíço", "", "Syria – Síria", "Syrian – sírio", "", "Tajikistan – Tadiquistão", "Tajiki – tajique", "Tanzanian – tanzaniano", "Thailand – Tailândia", "Thai – tailandês", "Togo – Togo", "Togolese – togolês", "Tonga – Tonga", "Tongan – tonganês", "Trinidad and Tobago – Trindade e Tobago", "Trinidadian – trinitário", "", "Tunisia – Tunísia", "Tunisian – tunisiano", "Turkmenistan – Turcomenistão", "Turkmen – turcomeno", "Turkey – Turquia", "Turkish – turco", "Tuvalu – Tuvalu", "Tuvaluan – tuvaluano", "Ukraine – Ucrânia", "Ukrainian – ucraniano", "Uganda – Uganda", "Ugandan – ugandês", "Uruguay – Uruguai", "Uruguayan – uruguaio", "United Arab Emirates – Emirados Árabes Unidos", "Emirati – árabe", "United Kingdom – Reino Unido", "British – britânico", "United States of America – Estados Unidos", "American – americano", "Uzbekistan – Usbequistão", "Uzbek – uzbeque", "Vanuatu – Vanuatu", "Ni-vanuatu – vanuatuano", "Venezuela – Venezuela", "Venezuelan – venezuelano", "Vietnam – Vietnã", "Vietnamese – vietnamita", "Wales – País de Gales", "Welsh – galês", "Yemen – Iêmen", "Yemeni – iemenita", "Zambia – Zâmbia", "Zambian – zambiano", "Zimbabwe – Zimbábue", "Zimbabwean – zimbabueano"}));
		comboBox.setBounds(576, 330, 255, 33);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(578, 105, 253, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(576, 174, 255, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha de Nascimiento");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(576, 236, 255, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nacionalidad");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(576, 305, 255, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\registro.png"));
		lblNewLabel.setBounds(0, -9, 502, 529);
		contentPane.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\cancelar.png"));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(765, 451, 54, 41);
		contentPane.add(btnCancelar);
		
		JButton btnCancelar_1 = new JButton("");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exito exito = new Exito();
				exito.setVisible(true);
			}
		});
		btnCancelar_1.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\disquete.png"));
		btnCancelar_1.setBackground(SystemColor.menu);
		btnCancelar_1.setBounds(700, 451, 54, 41);
		contentPane.add(btnCancelar_1);
		
		JButton btnCancelar_2 = new JButton("");
		btnCancelar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnCancelar_2.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\cerrar-sesion 32-px.png"));
		btnCancelar_2.setBackground(SystemColor.menu);
		btnCancelar_2.setBounds(830, 451, 54, 41);
		contentPane.add(btnCancelar_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Teléfono");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(578, 374, 253, 14);
		contentPane.add(lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(576, 399, 255, 33);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\Ha-100px.png"));
		lblNewLabel_2.setBounds(780, 11, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Registro Huéspedes");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4.setBounds(576, 52, 198, 42);
		contentPane.add(lblNewLabel_4);
	}
}
