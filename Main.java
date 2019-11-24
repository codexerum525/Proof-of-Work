package ProofOfWork;

import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {
		// hash of block
		String dec1 = "000000fffff3ba27aa200b1cecaad478d2b00432346c3f1f3986da1afd33e506";
		String dec2 = "00000000000000000000000000000000000000000000000000000000fd33e506";
		// target
		String target = "000ffffffff4864c000000000000000000000000000000000000000000000000";
		String target1 = "000000000004864c000000000000000000000000000000000000000000000000";
		

	    
	    
	    BigInteger DEC1toHex=new BigInteger(dec1,16);
	    BigInteger DEC1bigInt = new BigInteger(dec1, 16);
	    
	    BigInteger DEC2toHex=new BigInteger(dec2,16);
	    BigInteger DEC2bigInt = new BigInteger(dec2, 16);
	    
	    BigInteger TARGETtoHex=new BigInteger(target,16);
	    BigInteger TARGETbigInt = new BigInteger(target, 16);
	    
	    // DEC1
	    String s = DEC1toHex.toString(16);
	    System.out.println("The value in Hex of DEC1 is: "+ s);
	    System.out.println("The value in Int of DEC1 is: "+ DEC1bigInt);
	    System.out.println("Length is: " + DEC1toHex.toString(16).length());
	    
	    System.out.println();
	    
	    // Targetx1!
	    String x = TARGETtoHex.toString(16);
	    System.out.println("The value in Hex of Targetx1 is: "+ x);
	    System.out.println("The value in Int of Targetx1 is: "+ TARGETbigInt);
	    System.out.println("Length is: " + TARGETtoHex.toString(16).length());
	    
	    System.out.println();
	    
	    /* 
	     -1 	-> menor que  
	      0  	-> igual a
	      1  	-> mayor que 
	     	
	    */
	    
	    //calculateHexTarget("0404cb");
	   
	    returnPdiff(calculateHexTarget("0ffff").doubleValue());
	    returnBdiff(calculateHexTarget("0ffff").doubleValue());
	    System.out.println("Comparacion 1: " + DEC1bigInt.compareTo(TARGETbigInt));
	    System.out.println("Comparacion 2: " + DEC2bigInt.compareTo(TARGETbigInt));
	    
	    
	    int nonce = 0;
	    BigInteger DEC3bigInt = new BigInteger(StringUtil.applySha256(String.valueOf(nonce)), 16);
	    while(DEC3bigInt.compareTo(TARGETbigInt) != -1) {
	    	DEC3bigInt = new BigInteger(StringUtil.applySha256(String.valueOf(nonce)), 16);
	    	System.out.println(DEC3bigInt.toString(16));
	    	nonce++;
	    }
	    System.out.println(nonce);
	    System.out.println("Comparacion 3: " + DEC3bigInt.compareTo(TARGETbigInt));

	    String x1 = DEC3bigInt.toString(16);
	    System.out.println("The value in Hex of Targetx1 is: "+ StringUtil.applySha256(String.valueOf(nonce-1)));
	    
	    
	    System.out.println("Testing valid hash: " + checkValidNonce(nonce - 1, "0000000399c6aea5ad0c709a9bc331a3ed6494702bd1d129d8c817a0257a1462", "000000fffff4864c000000000000000000000000000000000000000000000000"));
	}
	
	public static boolean checkValidNonce(int nonce, String hex, String target) {
		// String maxTarget

	    BigInteger TARGETbigInt = new BigInteger(target, 16);
		BigInteger DEC3bigInt = new BigInteger(String.valueOf(hex), 16);
		if(DEC3bigInt.compareTo(TARGETbigInt) == -1) {
			System.out.println("linea 80: " + DEC3bigInt.toString());
			return true;
		} else {
			return false;
		}
	}
				// second part
	//	bits    int2 eight (part1)
	// 0x0404cb * 2**(8*(0x1b - 3))
	public static BigInteger calculateHexTarget(String bits) {
		BigInteger bitsBigInt = new BigInteger(bits, 16);
		BigInteger int2 = BigInteger.valueOf(2);
		BigInteger eight = BigInteger.valueOf(8);
		BigInteger part1 = BigInteger.valueOf(24);
		int x = eight.multiply(part1).intValue();
		
		
		// this is the result
		BigInteger Target = bitsBigInt.multiply(int2.pow(x));
		System.out.println("target puta " + Target);
		return Target;
	}
	
	
	public static double returnBdiff (double calculateHexTarget) {
		String maxDiff = "00000000FFFF0000000000000000000000000000000000000000000000000000";
		BigInteger maxDiffBigInt = new BigInteger(maxDiff, 16);
		double pdiff = maxDiffBigInt.doubleValue() / calculateHexTarget;
		System.out.println("bdiff: " + pdiff);
		return pdiff;
	}
	
	
	public static double returnPdiff (double calculateHexTarget) {
		String maxDiff = "00000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
		BigInteger maxDiffBigInt = new BigInteger(maxDiff, 16);
		double pdiff = maxDiffBigInt.doubleValue() / calculateHexTarget;
		System.out.println("pdiff: " + pdiff);
		return pdiff;
	}
}
