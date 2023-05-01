package Factory;

import DAO.HuespedDAO;

public class Pruebas {
	
	public static void main(String[] args) {
		
		HuespedDAO usersDao = new HuespedDAO(new ConnectionFactory().recuperateConnection());
		
		//Huesped user1 = new Huesped("Graciela", "Flores", LocalDate.of(2002, 8, 02), "Argentina", 224567667);
		
		System.out.println("\n Listar Huéspedes: ");
		//usersDao.listar();
		
		System.out.println("\n Ingresar un nuevo huésped: ");
		//usersDao.guardar(user1);
		
		System.out.println("\n Modificar un huésped: ");
		//usersDao.modificar(4, "Vanina Inés", "Gutierrez", LocalDate.of(2003, 8, 02), "Colombia", 24567655);
		
		System.out.println("Se eliminó el usuario con éxito!");
		//usersDao.eliminar(4);
	}

}
