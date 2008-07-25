package slack;
/**
 * Dynamically calculates a standard deviation based slack value.
 * @author Walter
 *
 */
public class stdDev {
	/**
	   * Calculates the standard deviation of an array
	   * of numbers.
	   * see Knuth's The Art Of Computer Programming
	   * Volume II: Seminumerical Algorithms
	   * This algorithm is slower, but more resistant to error propagation.
	   *
	   * @param data Numbers to compute the standard deviation of.
	   * Array must contain two or more numbers.
	   * @return standard deviation estimate of population
	   * ( to get estimate of sample, use n instead of n-1 in last line )
	   */
	   public static double sdKnuth ( double[] data ){
		   final int n = data.length;
		   if ( n < 2 ){
			   return Double.NaN;
		   }
		   double avg = data[0];
		   double sum = 0;
		   for ( int i = 1; i < data.length; i++ ){
			   double newavg = avg + ( data[i] - avg ) / ( i + 1 );
			   sum += ( data[i] - avg ) * ( data [i] -newavg ) ;
			   avg = newavg;
		   }
		   return Math.sqrt( sum / ( n - 1 ) );
	   }
}
