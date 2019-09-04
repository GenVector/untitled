import java.io.*;

public class FileToDB {
    public static void main(String args[]) {
        String fName = "/Users/guoshiyu/Documents/实有人口/实有人口/";
        File file = new File(fName);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (!tempList[i].isHidden()) {
                File file2 = new File(fName + tempList[i].getName());
                File[] tempList2 = file2.listFiles();
                for (int j = 0; j < tempList2.length; j++) {
                    if (!tempList2[j].isHidden())
                        renameFile(tempList2[j], fName + tempList[i].getName());
                }
            }

        }
    }

    public static void renameFile(File file, String dictName) {
        String name = file.getName();
        if (file.getName().split("_").length > 2) {
            String newName = name.split("_")[3] + ".jpg";
            File newFile = new File(dictName + "/" + newName);
            file.renameTo(newFile);
        }
    }

    static int num = 0;

    public static void txt2String(File file, String fileName) {
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader inp = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br = new BufferedReader(inp);
            String chapter = "";
            String s = null;
            while ((s = br.readLine()) != null) {
                s = s.trim();
                if (s.length() > 0) {
                    if (s.substring(0, 1).equals("第")) {
                        if (result.length() > 3) {
                            //保存逻辑
                            System.out.println(fileName + "::" + chapter + "::" + result);
                            num++;
                        }
                        if (s.substring(0, 5).contains("章")) {
                            chapter = s.toString();
                        } else {
                            result = new StringBuilder();
                            result.append(s);
                        }
                    } else {
                        result.append(s);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
