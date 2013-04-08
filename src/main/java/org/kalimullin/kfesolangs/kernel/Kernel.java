package org.kalimullin.kfesolangs.kernel;

import com.beust.jcommander.JCommander;
import org.apache.commons.lang.StringUtils;

import java.io.File;

public class Kernel {

    public static void main(String[] args) {
        KernelProperties properties = new KernelProperties();
        JCommander jCommander = new JCommander(properties, args);
        if (0 == args.length || properties.isHelp()) {
            jCommander.usage();
        } else {
            File sourceFile = new File(properties.getSourceToInterpret().get(0));
            Interpreter interpreter = InterpreterFactory.getInterpreter(properties.getLanguage());
            if (sourceFile.exists()) {
                interpreter.interpret(sourceFile);
            } else {
                interpreter.interpret(StringUtils.join(properties.getSourceToInterpret(), " "));
            }
        }
    }

}
