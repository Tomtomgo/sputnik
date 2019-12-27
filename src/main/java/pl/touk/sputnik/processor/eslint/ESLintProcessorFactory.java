package pl.touk.sputnik.processor.eslint;

import pl.touk.sputnik.configuration.Configuration;
import pl.touk.sputnik.configuration.GeneralOption;
import pl.touk.sputnik.processor.ReviewProcessorFactory;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class ESLintProcessorFactory implements ReviewProcessorFactory<ESLintProcessor> {

    @Override
    public boolean isEnabled(Configuration configuration) {
        return Boolean.valueOf(configuration.getProperty(GeneralOption.ESLINT_ENABLED));
    }

    @Override
    public ESLintProcessor create(Configuration configuration) {
        final ESLintExecutor eslintExecutor = new ESLintExecutor(configuration.getProperty(GeneralOption.ESLINT_RCFILE));
        final List<String> additionalExtensions = Arrays.asList(ofNullable(configuration.getProperty(GeneralOption.ESLINT_ADDITIONAL_EXTENSIONS))
                .map(extensions -> extensions.split(","))
                .orElse(new String[0]));

        return new ESLintProcessor(eslintExecutor, additionalExtensions);
    }
}