package clientesDB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AppClientesAccesoDB {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox comboBox;//sin uso 
	private JComboBox comboBox2;
	//private JTextField textField_5;
	
	private JLabel labelResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppClientesAccesoDB window = new AppClientesAccesoDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppClientesAccesoDB() {
		initialize();
	}
	/* 
	insert into tablaAgenda(nombre, apellidos, telf, fecha_nacimiento) values('victor2', "cruz2", "604276382", "1996-05-20");,
	("juan", "perez1", "604276372", "1997-05-20"),
	("maria", "carmen1", "604276362", "1998-05-20"),
	("antonio", "garcia1", "604276352", "1999-05-20");
*/
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 748, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		labelResultado = new JLabel("resultado");
	    labelResultado.setBounds(52, 362, 600, 33);
	    labelResultado.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    labelResultado.setForeground(Color.GRAY);
	    frame.getContentPane().add(labelResultado);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 labelResultado.setText(""); 
				 try {
			          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/clientes","root" ,"_123Kuroneko");
			          Statement comando=conexion.createStatement();
			          comando.executeUpdate("insert into clientes(id_cliente,nif_cliente,nombre_cliente,apellido_cliente,direccion_cliente,nacionalidad_cliente) values "
			          		+ "('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+comboBox2.getSelectedItem()+"')");//mirar comillas 
			          conexion.close();
			          labelResultado.setForeground(Color.GREEN);
			          labelResultado.setText("se registraron los datos mira tu base de datos");
			          textField_1.setText("");//
			          textField_2.setText("");//
			          textField_3.setText("");//
			          textField_4.setText("");//
			          //comboBox.setSelectedIndex(0);
			         comboBox2.setSelectedIndex(0);
			          /* nombre de la tabla clientes
			          // id_cliente
			         	textField_1.setText(registro.getString("nif_cliente"));
			        	  textField_2.setText(registro.getString("nombre_cliente"));
			        	  textField_3.setText(registro.getString("apellido_cliente"));
			        	  textField_4.setText(registro.getString("direccion_cliente"));
			        	  comboBox2.setSelectedItem(registro.getString("nacionalidad_cliente"));
			        	  labelResultado.setText("Listo!!");*/

		        } catch (Exception ex) {
					System.out.println("no conecta");
					ex.printStackTrace();
					// TODO: handle exception
				} 
			}
		});
		btnNewButton.setBounds(10, 317, 151, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modificar por id");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelResultado.setText("");
		        try {
		          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/clientes","root" ,"_123Kuroneko");
		          Statement comando=conexion.createStatement();
		          int cantidad = comando.executeUpdate("UPDATE clientes SET nif_cliente='" + textField_1.getText() + "'," +
                          "nombre_cliente='" + textField_2.getText() + "' WHERE id_cliente="+textField.getText());
		          if (cantidad==1) {
		            textField_1.setText("");
		            textField_2.setText("");  
		            labelResultado.setForeground(Color.GREEN);
		            labelResultado.setText("Se modificoron los datos dados por c�digo id");
		          } else {
		        	labelResultado.setForeground(Color.RED);
		            labelResultado.setText("No existen datos con dicho c�digo id");
		          }
		          conexion.close();
		        } catch (Exception ex) {
					System.out.println("no conecta");
					ex.printStackTrace();
					// TODO: handle exception
				} 
			}
		});
		btnNewButton_1.setBounds(183, 318, 151, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar por id");//suprimir
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelResultado.setText("");
		        try {
		          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/clientes","root" ,"_123Kuroneko");
		          Statement comando=conexion.createStatement();
		          int cantidad = comando.executeUpdate("delete from clientes where id_cliente="+textField.getText());
		          
		          if (cantidad==1) {
		        	  textField_1.setText("");//
			          textField_2.setText("");//
			          textField_3.setText("");//
			          textField_4.setText("");//
			          //comboBox.setSelectedIndex(0);
			         comboBox2.setSelectedIndex(0);      
			         labelResultado.setForeground(Color.GREEN);
		            labelResultado.setText("Se borraron de la base de datos por el codigo ID");
		          } else {
		        	 labelResultado.setForeground(Color.RED);
		            labelResultado.setText("No existeN con dc�digo");
		          }
		          conexion.close();
		        } catch (Exception ex) {
					System.out.println("no conecta");
					ex.printStackTrace();
					// TODO: handle exception
				} 
			}
		});
		btnNewButton_2.setBounds(356, 317, 151, 33);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("visualizar por id");
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBackground(Color.BLUE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//labelResultado.setText("");
				labelResultado.setText("");
			    textField_1.setText("");
			    textField_2.setText("");
			    textField_3.setText("");
			    textField_4.setText("");
			    
			   // comboBox.addSetItems("");
			  
			    try {
			          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/clientes","root" ,"_123Kuroneko");
			          Statement comando=conexion.createStatement();
			          ResultSet registro = comando.executeQuery("SELECT nif_cliente,nombre_cliente,apellido_cliente,direccion_cliente,nacionalidad_cliente from clientes WHERE id_cliente="+textField.getText());
			          if (registro.next()==true) {
			        	  textField_1.setText(registro.getString("nif_cliente"));
			        	  textField_2.setText(registro.getString("nombre_cliente"));
			        	  textField_3.setText(registro.getString("apellido_cliente"));
			        	  textField_4.setText(registro.getString("direccion_cliente"));
			        	  comboBox2.setSelectedItem(registro.getString("nacionalidad_cliente"));
					      labelResultado.setForeground(Color.GREEN);
			        	  labelResultado.setText("Listo! puedes ver los datos por el codigo dado!!");
			          } else {
			        	 labelResultado.setForeground(Color.RED);
			            labelResultado.setText("No existe nada con el codigo id dado");
			          }
			          conexion.close();
		        } catch (Exception ex) {
					System.out.println("no conecta");
					ex.printStackTrace();
					// TODO: handle exception
				} 
			}
		});
		btnNewButton_3.setBounds(521, 317, 151, 33);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Identificador del cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 38, 259, 33);
		frame.getContentPane().add(lblNewLabel);
		// campo de texto 0	
		textField = new JTextField();
		textField.setBounds(306, 38, 259, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblRa = new JLabel("Nif");
		lblRa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRa.setBounds(10, 70, 259, 33);
		frame.getContentPane().add(lblRa);
		//campo de texto 1	
		textField_1 = new JTextField();
		textField_1.setBounds(306, 70, 259, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDomicilioFiscal = new JLabel("Nombre");
		lblDomicilioFiscal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDomicilioFiscal.setBounds(10, 104, 259, 33);
		frame.getContentPane().add(lblDomicilioFiscal);
		//campo de texto 2	
		textField_2 = new JTextField();
		textField_2.setBounds(306,104,259,33);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDomicilioDelAlmacen = new JLabel("Apellido");
		lblDomicilioDelAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDomicilioDelAlmacen.setBounds(10, 137, 259, 33);
		frame.getContentPane().add(lblDomicilioDelAlmacen);
		//campo de texto 3
		textField_3 =  new JTextField();
		textField_3.setBounds(306, 137, 259, 33);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Direccion");
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelfono.setBounds(10, 171, 259, 33);
		frame.getContentPane().add(lblTelfono);
		//campo de texto 4
		textField_4 = new JTextField();
		textField_4.setBounds(306,171,259,33);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
		JLabel lblIdioma = new JLabel("Nacionalidad");
		lblIdioma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdioma.setBounds(10, 205, 259, 33);
		frame.getContentPane().add(lblIdioma);
		
		
		String[] nacionalidades ={"Nacional","CEE","Internacional"};
		comboBox2 = new JComboBox(nacionalidades);
		comboBox2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox2.setBounds(306, 210, 259, 33);
		frame.getContentPane().add(comboBox2);
		

		
	
		
	}
}
