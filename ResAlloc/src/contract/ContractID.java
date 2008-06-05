package contract;
import java.security.*;
import java.math.*;

/**
 * The 'goal' of this class is to create a unique ID for each contract.
 * I'm hoping to do this by generating an MD5 hash of the date and time.  
 * Maybe internet time would suffice. 
 * Code stolen from: http://snippets.dzone.com/posts/show/3686
 * @author Walter
 *
 */
public class ContractID {
	String myDate;
    public void myMD5hash() throws Exception{
    	String s="This is a test";
    	MessageDigest m=MessageDigest.getInstance("MD5");
    	m.update(s.getBytes(),0,s.length());
    	System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
    }
}