package io.aashay.reflectiveLantern;

import java.lang.reflect.Constructor;

public class runTest {

    static annotated annotationP = null;

    public static void setTest(annotated annotated) {
        annotationP = annotated;

        Class<?> anno_Class = annotated.getClass();
        Constructor<?>[] constructors = anno_Class.getConstructors();

        try {
            if (constructors[0].isAnnotationPresent(use.class)) {
                System.out.println("Annottation is present");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

}