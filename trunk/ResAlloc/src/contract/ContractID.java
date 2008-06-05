package contract;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Calendar;

/**
 * The 'goal' of this class is to create a unique ID for each contract.
 * I'm hoping to do this by generating an MD5 hash of the date and time.  
 * Maybe internet time would suffice. 
 * Code stolen from: http://snippets.dzone.com/posts/show/3686
 * @author Walter
 *
 */
public class ContractID {
	//First, get the date.
	public int ContractIDHash() {
		Calendar myDate = Calendar.getInstance();
		int timeHash = myDate.hashCode();
		return timeHash;
	}
	//Second, calculate the MD5 hash of the date.
    public String createID() throws Exception {
    	int mytimeHash = ContractIDHash();
    	String mytimeHashString = Integer.toString(mytimeHash);
       	MessageDigest m=MessageDigest.getInstance("MD5");
    	m.update(mytimeHashString.getBytes(),0,mytimeHashString.length());
    	return new BigInteger(1,m.digest()).toString(16);
    }
}

