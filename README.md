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
                        "SylvainDuquesne" if no data was given. Time is returned as nanoseconds.
  
    -h or --help opens helper  


Detailed Workings
-----------------

In the root folder are two packages:

org.apache.commons.cli which is a package from the Apache API used for the parsing of the command line. For more informations about this package got to https://commons.apache.org/proper/commons-cli/

DSAmain is the main package of this program, it contains the following classes :

DSA is the main class of the program, it contains the main method that runs the program.

optionParser is a class that only contains a building method that parses the command line and returns an optionParser object that contains the values of the different arguments given as inputs.

executionTime is also a class that only contains a building method. This building method creates an execution Time object that is made of the time needed for <int> signature and the Time needed for <int> verification of <data> were <int> and <data> are the inputs given to this method.
