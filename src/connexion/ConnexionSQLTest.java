package connexion;

import junit.framework.TestCase;



public class ConnexionSQLTest extends TestCase {
	ConnexionSQL connexion;
	
	public void testConnexion() throws Exception {
		connexion.openConnection();
	}
	
	public void testtestConnection() throws Exception {
		connexion.testConnection();
	}
	
	public void testCloseConnexion() throws Exception {
		connexion.closeConnection();
	}




/* (non-Javadoc)
 * @see junit.framework.TestCase#setUp()
 */
@Override
protected void setUp() throws Exception {
	connexion = new ConnexionSQL("163.172.232.206", "michel", "sqlmichel", "nesti");
}
	
	
}
