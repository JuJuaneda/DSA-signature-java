package DSAmain;

import org.apache.commons.cli.*;

public class optionParser {

  public int mode;
  public String input;
  public String rHex;
  public String sHex;
  public String publicKeyHex;

  public optionParser(String[] args) {
    Options options = new Options();
    
    Option sign = Option.builder("s").longOpt("sign")
                        .numberOfArgs(1)
                        .optionalArg(true)
                        .desc("signs the given data, returns the signature (r,s) and the public_key").build();                        
    options.addOption(sign);
    
    Option verif = Option.builder("v").longOpt("verif")
                         .numberOfArgs(4)
                         .valueSeparator(',')
                         .desc("verifies the given signature").build();    
    options.addOption(verif);
    
    Option time = Option.builder("t").longOpt("time")
                        .numberOfArgs(1)
                        .optionalArg(true)
                        .desc("computes the time needed for 10000 signatures and the time needed for 10000 verifications of the given data (or Sylvain Duquesne if no given data)").build();
    options.addOption(time);
    
    Option help = new Option("h", "help", false, "prints helper");
    options.addOption(help);
    
    CommandLine cmd;
    CommandLineParser parser = new DefaultParser();
    HelpFormatter helper = new HelpFormatter();
    int mode = 1;
    String input = "SylvainDuquesne";
    String rStringHex = "";
    String sStringHex = "";
    String publicKeyHex = "";
    
    try {
      cmd = parser.parse(options, args);
      Option[] opts = cmd.getOptions();
      if(opts.length == 0) {
        this.mode = 1;
        this.input = input;
      } else if(opts.length > 1) {
        System.err.println("Too many options, choose only one option");
      } else{ 
        switch (opts[0].getOpt()){
      
          case "s": mode = 1;
                    input = cmd.getOptionValue('s',"SylvainDuquesne");
                    break;
      
          case "v": mode = 2;
                    String[] vals = cmd.getOptionValues('v');
                    if (vals.length != 4){
                      System.err.println(vals.length + "arguments when 4 are required");
                    }
                    input = vals[0];
                    rHex = vals[1];
                    sHex = vals[2];
                    publicKeyHex = vals[3];
                    break;
        
          case "t": mode = 3;
                    input = cmd.getOptionValue('t', "SylvainDuquesne");
                    break;
        
          case "h": helper.printHelp("Usage:", options);
                  System.exit(0);
                  break;
                  
          default: mode = 1;
                   input = "SylvainDuquesne";
                   break;
        }   
      }  
    }catch (ParseException e){
      System.err.println(e.getMessage());
      helper.printHelp("Usage:", options);
      System.exit(1);
    }
    
    this.mode = mode;
    this.input = input;
    this.rHex = rHex;
    this.sHex = sHex;
    this.publicKeyHex = publicKeyHex;
  }

}
