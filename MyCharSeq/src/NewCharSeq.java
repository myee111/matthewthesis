
public class NewCharSeq implements CharSequence{
	private String s;

	@Override
	public char charAt(int arg0) {
		for (int i = arg0; i<= s.length(); i++) {
			return s.charAt(i);
		}
		return 0;
	}

	@Override
	public int length() {
		return s.length();
	}

	@Override 
	public CharSequence subSequence(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return s.subSequence(arg0, arg1);
	}
	
}
