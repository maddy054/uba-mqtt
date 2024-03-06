package persistance;

import utilities.OwnException;
import utilities.User;

public interface Connector {
	
	public User getUserDetails(String userName) throws OwnException ;
	
	public void addUser(User user) throws OwnException;
	
}
