package cn.geekzone.oxygenBar.utils;

import java.util.UUID;

/**
 * 文件名工具
 * Created by Sunny  An on 2017/1/11.
 */
public class FileNameUtils {

    public enum FileType {

        /**
         * 图片
         */
        IMAGE(new String[]{".jpg", ".jpeg", ".png", ".gif", ".pdf", ".doc", ".docx"});

        private String[] types;

        FileType(String[] types) {
            this.types = types;
        }

        public String[] getTypes() {
            return types;
        }

    }

    /**
     * <pre>
     * 获取文件的类型，按文件名最后一个.右侧所有的字符为准，转换为小写字母返回
     * 返回值包含"."
     * 如  .jpg
     * </pre>
     *
     * @return 文件类型，无返回null
     */
    public static String getFileType(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        // "." 不存在，或在文件末尾
        if (index == -1 || index == fileName.length() - 1) {
            return null;
        }
        return fileName.substring(index, fileName.length()).toLowerCase();
    }

    /**
     * 是否是指定类型的文件
     * @param fileName
     * @param type
     * @return
     */
    public static boolean isFileType(String fileName, FileType type) {
        if (fileName == null || type == null) {
            return false;
        }
        String fileType = getFileType(fileName);
        if (fileType == null) {
            return false;
        }

        String[] types = type.getTypes();
        for (String i : types) {
            if (fileType.equals(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(getFileType("aaa.jpg"));
        System.out.println(isFileType("aa.JPEG", FileType.IMAGE));
        System.out.println(UUID.randomUUID().toString());
    }

}
