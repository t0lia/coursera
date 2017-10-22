package com.firefly.crypto;

import org.apache.commons.cli.*;

public class Application {
    public static void main(String[] args) throws Exception {

        Options options = new Options();

        Option problem = new Option("p", "problem", true, "problem number");
        problem.setRequired(true);
        options.addOption(problem);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        }

        String problemNumber = cmd.getOptionValue("problem");
        try {
            int num = Integer.parseInt(problemNumber);
            System.out.println(new Crypto().resolve(num));
        } catch (NumberFormatException e) {
            formatter.printHelp("utility-name", options);
        }
    }
}
