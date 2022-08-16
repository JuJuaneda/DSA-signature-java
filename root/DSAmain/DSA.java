package DSAmain;

import java.math.BigInteger;

public class DSA {

  public static void main(String[] args) {
    
    optionParser optionParser = new optionParser (args);
    
    int mode = optionParser.mode;
    byte[] data = optionParser.input.getBytes();
               
    switch (mode){
      case 1: signature signature = new signature(data);
              System.out.println("r: " + signature.r.toString(16));
              System.out.println("s: " + signature.s.toString(16));
              System.out.println("public key: " + signature.publicKey.toString(16));
              break;
      
      case 2: BigInteger r = new BigInteger(optionParser.rHex,16);
              BigInteger s = new BigInteger(optionParser.sHex,16);
              BigInteger publicKey = new BigInteger(optionParser.publicKeyHex,16);
              signature signatureToVerify = new signature (data, r, s, publicKey);
              System.out.println(signatureToVerify.verif());
              break;
      
      case 3: executionTime executionTime = new executionTime(10000, data);
              System.out.println("time for 10000 signatures: "
                                 + executionTime.signatureTime);
              System.out.println("time for 10000 verifications: "
                                 + executionTime.verifTime);
              break;
      
      default: System.err.println("Invalid mode value" + mode);
               System.exit(1); 
    }
  }
}
