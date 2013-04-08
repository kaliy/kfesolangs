package org.kalimullin.kfesolangs.kernel;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;
import java.util.List;

public class KernelProperties {

    @Parameter(
            names = {"--help", "-h"},
            help = true,
            description = "Display this help message"
    )
    private boolean help;

    @Parameter(
            description = "Source file or source code to interpret",
            descriptionKey = "source"
    )
    private List<String> sourceToInterpret;

    @Parameter(
            names = {"--language", "-l"},
            description = "Esoteric language to interpret",
            required = true
    )
    private String language;


    public boolean isHelp() {
        return help;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getSourceToInterpret() {
        return sourceToInterpret;
    }

}
