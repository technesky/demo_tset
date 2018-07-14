package com.sky.util;
import com.sky.jsoup.bean.Book;

import java.io.*;
import java.util.*;

/**
 * 文件工具类
 * Created by sky on 2016/7/5.
 */
public class FileUtils {
    public static Set<String> sets = new HashSet<String>();

    /**
     * 文件缓存
     * @param filePath  路径
     * @param list		写入内容
     * @param encoding	文件编码
     */
    public static void writeFile( String filePath, List<Book> list,String encoding ) {
        long time = new Date().getTime();
        try {
            File file = new File(filePath+time+".txt");

            createFile(filePath+time+".txt");
            if (file.isFile() && file.exists()) {

                OutputStreamWriter write = new OutputStreamWriter(
                        new FileOutputStream(file), encoding);
                BufferedWriter bufferedWriter = new BufferedWriter(write);
               for(Book book:list){
                   
                   bufferedWriter.write(book.getName());
                   bufferedWriter.newLine();
                   bufferedWriter.write(book.getContent());
                   bufferedWriter.newLine();
               }
                bufferedWriter.flush();
                write.close();

            } else {
                System.out.println("file error");
            }
        } catch (Exception e) {
            System.out.println("writeFile error");
            e.printStackTrace();
        }
    }
    /**
     * 创建单个文件
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            System.out.println("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            System.out.println(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件
        try {
            if (file.createNewFile()) {
                System.out.println(descFileName + " 文件创建成功!");
                return true;
            } else {
                System.out.println(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(descFileName + " 文件创建失败!");
            return false;
        }

    }


    public static void main(String[] args) {
        System.out.println(createFile("D://3/2/1.txt"));
    }

    /**
     * 过滤MP3文件
     *
     * @param strPath
     */
    public static void refreshFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath());
            } else {
                String strFilePath = files[i].getAbsolutePath().toLowerCase();
                String strName = files[i].getName();
                if (strName.endsWith(".mp3")) {
                    boolean bFlag = sets.add(strName);
                    if (bFlag == Boolean.FALSE) {
                        // 删除重复文件
                        removeFile(strFilePath);
                    }
                }
                // System.out.println("FILE_PATH:" + strFilePath + "|strName:" +
                // strName);
            }
        }
    }

    /**
     * 创建文件夹
     *
     * @param strFilePath
     *            文件夹路径
     */
    public boolean mkdirFolder(String strFilePath) {
        boolean bFlag = false;
        try {
            File file = new File(strFilePath.toString());
            if (!file.exists()) {
                bFlag = file.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return bFlag;
    }

    public boolean createFile(String strFilePath, String strFileContent) {
        boolean bFlag = false;
        try {
            File file = new File(strFilePath.toString());
            if (!file.exists()) {
                bFlag = file.createNewFile();
            }
            if (bFlag == Boolean.TRUE) {
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(strFileContent.toString());
                pw.close();
            }
        } catch (Exception e) {
            System.out.println("新建文件操作出错" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return bFlag;
    }

    /**
     * 删除文件
     *
     * @param strFilePath
     * @return
     */
    public static boolean removeFile(String strFilePath) {
        boolean result = false;
        if (strFilePath == null || "".equals(strFilePath)) {
            return result;
        }
        File file = new File(strFilePath);
        if (file.isFile() && file.exists()) {
            result = file.delete();
            if (result == Boolean.TRUE) {
                System.out.println("[REMOE_FILE:" + strFilePath + "删除成功!]");
            } else {
                System.out.println("[REMOE_FILE:" + strFilePath + "删除失败]");
            }
        }
        return result;
    }

    /**
     * 删除文件夹(包括文件夹中的文件内容，文件夹)
     *
     * @param strFolderPath
     * @return
     */
    public static boolean removeFolder(String strFolderPath) {
        boolean bFlag = false;
        try {
            if (strFolderPath == null || "".equals(strFolderPath)) {
                return bFlag;
            }
            File file = new File(strFolderPath.toString());
            bFlag = file.delete();
            if (bFlag == Boolean.TRUE) {
                System.out.println("[REMOE_FOLDER:" + file.getPath() + "删除成功!]");
            } else {
                System.out.println("[REMOE_FOLDER:" + file.getPath() + "删除失败]");
            }
        } catch (Exception e) {
            System.out.println("FLOADER_PATH:" + strFolderPath + "删除文件夹失败!");
            e.printStackTrace();
        }
        return bFlag;
    }

    /**
     * 移除所有文件
     *
     * @param strPath
     */
    public static void removeAllFile(String strPath) {
        File file = new File(strPath);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] fileList = file.list();
        File tempFile = null;
        for (int i = 0; i < fileList.length; i++) {
            if (strPath.endsWith(File.separator)) {
                tempFile = new File(strPath + fileList[i]);
            } else {
                tempFile = new File(strPath + File.separator + fileList[i]);
            }
            if (tempFile.isFile()) {
                tempFile.delete();
            }
            if (tempFile.isDirectory()) {
                removeAllFile(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件
                removeFolder(strPath + "/" + fileList[i]);// 删除文件夹
            }
        }
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                System.out.println("[COPY_FILE:" + oldfile.getPath() + "复制文件成功!]");
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错 ");
            e.printStackTrace();
        }
    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/ " + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                    System.out.println("[COPY_FILE:" + temp.getPath() + "复制文件成功!]");
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/ " + file[i], newPath + "/ "
                            + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错 ");
            e.printStackTrace();
        }
    }

    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        //removeFile(oldPath);
    }

    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        //removeFolder(oldPath);
    }
}
