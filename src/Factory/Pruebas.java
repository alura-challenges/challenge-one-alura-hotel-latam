package Factory;

import DAO.HuespedesDAO;

public class Pruebas {
	
	public static void main(String[] args) {
		
		HuespedesDAO usersDao = new HuespedesDAO();
		usersDao.listar();
		
	}

}
