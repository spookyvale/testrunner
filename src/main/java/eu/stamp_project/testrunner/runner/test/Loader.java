package eu.stamp_project.testrunner.runner.test;

import eu.stamp_project.testrunner.EntryPoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 * Created by Benjamin DANGLOT
 * benjamin.danglot@inria.fr
 * on 21/12/17
 */
public class Loader<T> {

    public T load(String name) {
        T object;
        String outputDirectoryPath = EntryPoint.workingDirectory != null ?
                EntryPoint.workingDirectory.getAbsolutePath() + TestRunner.FILE_SEPARATOR : "";
        File f = new File(outputDirectoryPath + TestListener.OUTPUT_DIR + name + TestListener.EXTENSION);
        if (!f.exists()) {
            throw new RuntimeException(new FileNotFoundException(f.getAbsolutePath() + " does not exist."));
        } else {
            System.out.println("Loading " + f.getAbsolutePath());
        }
        try (FileInputStream fin = new FileInputStream(f);) {
            try (ObjectInputStream ois = new ObjectInputStream(fin)) {
                object = (T) ois.readObject();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        f.delete();
        return object;
    }

}
