package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while(!"HARVEST".equals(command)) {


            if ("private".equals(command)) {
                Arrays.stream(declaredFields).filter(f -> {
                    int modifiers = f.getModifiers();
                    if (Modifier.isPrivate(modifiers)) {
                        return true;
                    } else return false;
                }).forEach(f -> System.out.printf("%s %s %s\n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
            } else if ("public".equals(command)) {
                Arrays.stream(declaredFields).filter(f -> {
                    int modifiers = f.getModifiers();
                    if (Modifier.isPublic(modifiers)) {
                        return true;
                    } else return false;
                }).forEach(f -> System.out.printf("%s %s %s\n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
            } else if ("protected".equals(command)) {
                Arrays.stream(declaredFields).filter(f -> {
                    int modifiers = f.getModifiers();
                    if (Modifier.isProtected(modifiers)) {
                        return true;
                    } else return false;
                }).forEach(f -> System.out.printf("%s %s %s\n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
            } else if ("all".equals(command)) {
                Arrays.stream(declaredFields)
                        .forEach(f ->
                                System.out.printf("%s %s %s\n",
                                        Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
            }

            command = scanner.nextLine();
        }
    }
}
