package pl.touk.sputnik.review.filter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class JavaScriptFilter extends FileExtensionFilter {

    public JavaScriptFilter() {
        this(emptyList());
    }

    public JavaScriptFilter(List<String> additionalExtensions) {
        super(includeJs(additionalExtensions));
    }

    private static List<String> includeJs(List<String> additionalExtensions) {
        List<String> mergedExtensions = new ArrayList<>();
        mergedExtensions.add("js");
        mergedExtensions.addAll(additionalExtensions);
        return unmodifiableList(mergedExtensions);
    }
}