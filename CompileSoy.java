package main;

import java.io.File;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.SoyModule;
import com.google.template.soy.error.SoyCompilationException;
import com.google.template.soy.error.SoyError;

public class CompileSoy {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new SoyModule());
        SoyFileSet.Builder fsBuilder =
            injector
                .getInstance(SoyFileSet.Builder.class)
                .setAllowExternalCalls(false)
            .add(new File(args[0]));
        try {
            fsBuilder.build().compileTemplates();
        } catch (SoyCompilationException e) {
            for (SoyError err : e.getErrors()) {
                System.out.println(err);
            }
        }
    }
}
