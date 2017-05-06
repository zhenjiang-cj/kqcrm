/**
 * 
 */
package demo;

/**
 * @author Administrator
 *
 */
public class DemoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername("chenjia");
		user.setPassword("123456");
		System.out.println(">>>加密前>>>"+user.getPassword());
		PasswordHelper ph = new PasswordHelper();
		ph.encryptPassword(user);
		System.out.println(">>>加密后>>>"+user.getPassword());
	}

}
