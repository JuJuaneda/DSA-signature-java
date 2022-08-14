package DSAmain;

import java.math.BigInteger;

public class executionTime{
    public long signatureTime;
    public long verifTime;

    public executionTime(int iteration, byte[] data){
      long signatureTime = 0;
      long verifTime = 0;
      for (int i = 0; i < 10000; i++) {
        long t1 = System.nanoTime();
        signature signature = new signature(data);
        signatureTime += System.nanoTime() - t1;
        long t2 = System.nanoTime();
        signature.verif();
        verifTime += System.nanoTime() - t2;
      }
      this.signatureTime = signatureTime;
      this.verifTime = verifTime;
    }
}
