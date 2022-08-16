DSA-signature-java
==================

This repository contains a java implementation of a DSA Signature based on NIST recommendations from FIPS 186-3.

Usage
-----

To Download this repository use   `git clone https://github.com/JuJuaneda/DSA-signature-java`

Then Use `cd DSA-signature-java/root` to get to the root folder.

Compile the project with `javac DSAmain/DSA.java`

Do `java DSAmain.DSA` for default execution of the program. This returns a signature of "SylvainDuquesne" and the associated public key.

This program can be used with four different options : 

    -s or --sign <data> returns a signature and its associated public key for the given data. 
                        "SylvainDuquesne" if no data was given. 
  
    -v or --verif <data r s publicKey> verifies that the given signature is valid for the given data and public key. 
                                       Arguments r,s and publicKey must be given as hexadecimals.
  
    -t or --time <data> returns the time needed to do 10000 signatures and the time needed to do 10000 verifications of data. 
                        "SylvainDuquesne" if no data was given.
  
    -h or --help opens helper  
