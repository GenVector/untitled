package fileTest.nio;

import java.nio.file.*;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PathInfo {
    static void show(String id, Object p) {
        System.out.println(id + "------" + p + "  ");
    }

    static void info(Path p) {
        show("toString", p);
        show("Exists", Files.exists(p));
        show("RegularFile", Files.isRegularFile(p));
        show("Directory", Files.isDirectory(p));
        show("Absolute", p.isAbsolute());
        show("FileName", p.getFileName());
        show("Parent", p.getParent());
        show("Root", p.getRoot());
        System.out.println("******************");
    }

    //string... test
    public static String addStr(String first, String... more) {
        StringBuilder stringBuilder = new StringBuilder(first);
        Arrays.stream(more).forEach(s -> {
            stringBuilder.append(s);
        });
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println("os.name" + ":----" + System.getProperty("os.name"));
        //获取一个新path实例
        Path path = Paths.get("C:", "path", "to", "nowhere", "NoFile.txt");
        info(path);
        Path p = Paths.get("PathInfo.java");
        info(p);
        Path ap = p.toAbsolutePath();
        info(ap);
        info(ap.getParent());

        try {
            info(p.toRealPath());
        } catch (IOException e) {
            System.out.println("exception:" + e);
        }
        URI u = p.toUri();
        System.out.println("URI: " + u);
        Path puri = Paths.get(u);
        System.out.println(Files.exists(puri));
        File f = ap.toFile(); // Don't be fooled
        Path p1 = Paths.get("/Users/guoshiyu/IdeaProjects/untitled/src/fileTest/PathInfo.java");
        p1.toFile();
        info(p1);
    }
}
