package util;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author:tangmq
 * @Date:2017/9/5
 * @Note:
 */
public class MultiFileMergeUtil {
    private MultiFileMergeUtil() {}

    /**
     * @param path          目录路径
     * @param multiFilePath 合并后的文件存储地址
     * @param isDelAll      是否删除各个小文件
     * @return 返回合并后文件路径
     */
    public static String multiFileMerge(String path, String multiFilePath, boolean isDelAll) {
        //如果为空,给个默认文件名
        multiFilePath = StringUtils.isBlank(multiFilePath) ? path + "/merge.txt" : multiFilePath;
        //获得文件列表
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".txt")) {
                    return true;
                }
                return false;
            }
        });
        //对获得的文件进行排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int fromIndex = Integer.parseInt(o1.getName().split("-")[0]);
                int toIndex = Integer.parseInt(o2.getName().split("-")[0]);
                if (fromIndex < toIndex) {
                    return 1;
                } else if (fromIndex == toIndex) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        //进行合并
        PrintWriter writer=null;
        try {
            writer = new PrintWriter(new File(multiFilePath),"UTF-8");
            for (File file : files) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                String line =null;
                while ((line = buf.readLine()) != null) {
                    writer.println(line);
                }
                buf.close();
                if(isDelAll){
                    file.delete();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            writer.close();
        }
        return multiFilePath;

    }
}
