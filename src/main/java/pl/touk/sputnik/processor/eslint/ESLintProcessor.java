package pl.touk.sputnik.processor.eslint;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import pl.touk.sputnik.processor.tools.externalprocess.ExternalProcessResultParser;
import pl.touk.sputnik.processor.tools.externalprocess.ProcessorRunningExternalProcess;
import pl.touk.sputnik.review.filter.FileFilter;
import pl.touk.sputnik.review.filter.JavaScriptFilter;

import java.io.File;
import java.util.List;

@Slf4j
class ESLintProcessor extends ProcessorRunningExternalProcess {

    private final ESLintExecutor executor;
    private final List<String> extensions;

    ESLintProcessor(@NotNull ESLintExecutor eslintExecutor, @NotNull List<String> additionalExtensions) {
        executor = eslintExecutor;
        extensions = additionalExtensions;
    }

    @Override
    public FileFilter getReviewFileFilter() {
        return new JavaScriptFilter(extensions);
    }

    @Override
    public ExternalProcessResultParser getParser() {
        return new ESLintResultParser();
    }

    @Override
    public String processFileAndDumpOutput(File fileToReview) {
        return executor.runOnFile(fileToReview.getAbsolutePath());
    }

    @NotNull
    @Override
    public String getName() {
        return "ESLint";
    }
}
