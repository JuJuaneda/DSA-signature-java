package DSAmain;

import java.lang.Math;
import java.math.BigInteger;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class signature{
  
  private BigInteger data;
  final private BigInteger L = new BigInteger("1461501637330902918203684832716283019655932542983");
  final private BigInteger P = new BigInteger("179769313486231590772930519078902473361797697895091679363492034617707607939283528783618600406672541482734628805199178046312464214935489203347628476046233087730023393521734309703439091276450992547913800395079641970136256117132046113665999419588981076786318927391581493102440871475057229874484789591744284984823");
  final private BigInteger G = new BigInteger("2").modPow((P.subtract(BigInteger.ONE)).divide(L), P);
  public BigInteger r;
  public BigInteger s;
  public BigInteger publicKey;
  
  public signature (byte[] data){
    Random rnd = new Random();
    BigInteger privateKey = new BigInteger (160,rnd);
    this.publicKey = this.G.modPow(privateKey,this.P);    
    byte[] digest = new byte[16];
    try{
      MessageDigest hashfunction = MessageDigest.getInstance("MD5");
      hashfunction.update(data);
      digest = hashfunction.digest(data);
    } catch(NoSuchAlgorithmException e) {
      System.err.println("Invalid Algorithm");
      System.exit(4);
    }
    BigInteger hash = new BigInteger(digest);
    this.data = hash;
    this.r = BigInteger.ZERO;
    this.s = BigInteger.ZERO;
    while (this.r.compareTo(BigInteger.ZERO) == 0 || this.s.compareTo(BigInteger.ZERO) == 0){
      BigInteger k = new BigInteger (1024,rnd);
      this.r = this.G.modPow(k,this.P).mod(this.L);
      this.s = k.modInverse(this.L).multiply(hash.add(privateKey.multiply(this.r))).mod(this.L);
    }
  }
  
  public signature (byte[] data, BigInteger r, BigInteger s, BigInteger publicKey){
    this.r = r;
    this.s = s;
    this.publicKey = publicKey;
    byte[] digest = new byte[16];
    try{
      MessageDigest hashfunction = MessageDigest.getInstance("MD5");
      hashfunction.update(data);
      digest = hashfunction.digest(data);
    } catch(NoSuchAlgorithmException e) {
      System.err.println("Invalid Algorithm");
      System.exit(4);
    }
    BigInteger hash = new BigInteger(digest);
    this.data = hash;
  }
    
  public boolean verif(){
  
    if (!(this.L.compareTo(this.r) == 1 & this.r.compareTo(BigInteger.ZERO) == 1 
          & this.L.compareTo(this.s) == 1 & this.s.compareTo(BigInteger.ZERO) == 1)){
      return false;
    }
    
    BigInteger g_inter = this.G.modPow(this.s.modInverse(this.L).multiply(this.data).mod(this.L),this.P);
    BigInteger public_key_inter = this.publicKey.modPow(this.r.multiply(this.s.modInverse(this.L)).mod(this.L),this.P);
    return this.r.compareTo(g_inter.multiply(public_key_inter).mod(this.P).mod(this.L)) == 0;
  }
}
