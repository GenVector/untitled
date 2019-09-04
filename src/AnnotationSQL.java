import lombok.Data;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
@interface DBTable {
    String name() default "";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constraints {
    boolean primaryKey() default false;

    boolean allowNull() default true;

    boolean unique() default false;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLString {
    int value() default 0;

    String name() default "";

    Constraints constraints() default @Constraints;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLInteger {
    String name() default "";

    Constraints constraints() default @Constraints;
}

@interface Uniqueness {
    Constraints constraints()
            default @Constraints(unique = true);
}

@Data
@DBTable(name = "MEMBER")
class Member {
    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;
    @SQLInteger
    Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String reference;
    static int memberCount;

    @Override
    public String toString() {
        return reference;
    }
}

class TableCreator {
    public static void createTable(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("arguments: annotated classes");
            return;
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            // If the name is empty, use the Class name:
            if (tableName.length() < 1)
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getAnnotations();
                if (anns.length < 1)
                    continue; // Not a db table column
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified.
                    if (sString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
                }
                StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
                for (String columnDef : columnDefs)
                    createCommand.append("\n " + columnDef + ",");
                // Remove trailing comma
                String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ");";
                System.out.println("Table Creation SQL for " + className + " is:\n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if (con.primaryKey())
            constraints += " PRIMARY KEY";
        if (con.unique())
            constraints += " UNIQUE";
        return constraints;
    }

    public static Object getInstance(Map<String,Object> param, Class cl) throws Exception {
        Member member = (Member)cl.newInstance();
        return member;
    }
}

public class AnnotationSQL {
    public static void main(String[] args) throws Exception {
        String className = "Member";
        String[] arr = {className};
        Class cl = Class.forName(className);
        TableCreator.createTable(arr);
        Map<String,Object> param=new HashMap<>();
        param.put("firstName","G");
        param.put("lastName","shiny");
        param.put("age",24);
        param.put("reference","king");

        TableCreator.getInstance(param, cl);
    }
}

