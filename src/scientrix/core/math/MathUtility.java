package scientrix.core.math;

import java.math.*;
import java.util.*;
import java.util.Collections;

/* This class brings some static functions that perform several mathematical operations.
 * It has been re-designed specifically for the Scientrix Calculator.
 * All the algorithms are designed by Mohamed Adam Chaieb 
 * @author Mohamed Adam Chaieb*/

public class MathUtility
{ 
  //Finds all the primes up to the number passed as parameter
  public static synchronized ArrayList<Integer> getPrimes(int limit)
  {
    int currentPrime = 2;
    ArrayList<Integer> integers = new ArrayList<Integer>(limit/2);
    integers.add(2);
    for(int i = 3;i<=limit;i+=2)
      integers.add(i);
    int currentPrimeIndex = integers.indexOf(2);
    do
    {
      for(int i = 0;i<integers.size();i++)
      {
        if(integers.get(i)%currentPrime == 0 && integers.get(i) != currentPrime)
        {
          integers.remove(i);
        }
        else
          continue;
      }
      currentPrimeIndex++;
      currentPrime = integers.get(currentPrimeIndex);
    }while(Math.pow(currentPrime,2)<=limit);
    return integers;
  }
  
  //methods used in Utility Calculator
  public static BigInteger factorial(BigInteger input)
  {
    BigInteger output = new BigInteger("1");
    if(input.equals(new BigInteger("0")) || input.equals(new BigInteger("1")))
      return output;
    return input.multiply(factorial(input.subtract(new BigInteger("1"))));
  }
  
  public static BigInteger combinations(BigInteger n, BigInteger r)//assuming n>=r>=0
  {
    return (factorial(n).divide(factorial(r).multiply(factorial(n.subtract(r)))));
  }
  
  public static BigInteger permutations(BigInteger n, BigInteger r)//assuming n>=r>=0
  {
    return (factorial(n).divide(factorial(n.subtract(r))));
  }
  
  public static boolean isDivisor(BigInteger divisor, BigInteger test)
  {
    if(test.remainder(divisor).equals(BigInteger.ZERO))
      return true;
    else
      return false;
  }
  
  public static BigInteger pow(int input, int power)
  {
    if(power==0)
      return BigInteger.ONE;
    else if(power%2 == 0)
      return pow(input,power/2).multiply(pow(input,power/2));
    else
      return pow(input,power/2).multiply(pow(input,power/2)).multiply(new BigInteger(input+""));
  }
  
  public static ArrayList<Integer> convertFromDecimal(int decInput, int base)// converts the input from decimal to any base
  {
    int input = decInput;
    ArrayList<Integer> output = new ArrayList<Integer>();
    while(input>0)
    {
      output.add(input%base);
      input = input/base;
    }
    Collections.reverse(output);
    return output;
  }
  
  public static BigInteger convertToDecimal(List<BigInteger> input, int base)//converts from the specified base to decimal
  {
    BigInteger output = BigInteger.ZERO;
    for(int i = 0;i<input.size();i++)
      output.add(input.get(i).multiply(MathUtility.pow(base,(input.size()-i-1))));
    return output;
  }
  
  //Tells whether or not the two first numbers passed as parameters are nearly equal, the third parameter defines the precision factor
  public static synchronized boolean nearEquals(double double1,double double2, double epsilon)
  {
    double diff = Math.abs(double1-double2);
    if(diff<=epsilon)
      return true;
    else
      return false;
  }
}