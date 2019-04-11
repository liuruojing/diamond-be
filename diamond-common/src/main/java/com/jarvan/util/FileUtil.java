/*
 * 广州丰石科技有限公司拥有本软件版权2018并保留所有权利。
 * Copyright 2018, Guangzhou Rich Stone Data Technologies Company Limited,All rights reserved.
 */

package com.jarvan.util;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

/**
 * <b><code>FileUtil</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/12/14 10:26.
 *
 * @author liuruojing
 * @since nile-szcst-be 0.1.0
 */
@Slf4j
public final class FileUtil {
    /**
     * 下载文件.
     *
     * @param fileUrl file
     * @param request request
     * @param response response
     * @throws IOException e
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static final void download(String fileUrl,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fileName = fileUrl.substring(
                fileUrl.lastIndexOf(File.separator) + 1, fileUrl.length());
        // 获得请求头中的User-Agent
        String agent = request.getHeader("User-Agent");
        // 根据不同的客户端进行不同的编码
        String filenameEncoder = "";
        if (agent.contains("MSIE")) {
            // IE浏览器
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
            filenameEncoder = filenameEncoder.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?"
                    + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
        }
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + filenameEncoder);
        InputStream in = new FileInputStream(fileUrl);
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

    /**
     * 将byte[]写入文件url,当父目录不存在时创建父目录.
     *
     * @param bytes 要写入文件的字节流
     * @param url 准备写入的文件
     * @param append 是否追加
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static void writeByteToFile(byte[] bytes, String url, boolean append)
            throws IOException {
        String directory = url.substring(0, url.lastIndexOf('\\'));
        mkDirsIfNotExist(directory);
        OutputStream os = null;
        try {
            os = new FileOutputStream(url, append);
            os.write(bytes);
        } finally {
            IOUtils.closeQuietly(os);
        }

    }

    /**
     * 将字符串写入文件,当父目录不存在时创建父目录.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static void writeCharToFile(String content, String url,
            boolean append, String charset) throws IOException {
        String directory = url.substring(0, url.lastIndexOf('\\'));
        mkDirsIfNotExist(directory);
        OutputStream os;
        OutputStreamWriter writer = null;
        try {
            os = new FileOutputStream(url, append);
            writer = new OutputStreamWriter(os, charset);
            writer.write(content);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 从文件中读取byte[].
     *
     * @param fileUrl 文件路径
     * @return bytes
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static void readBytesFromFile(String fileUrl) throws IOException {
        File file = new File(fileUrl);
        byte[] buffer = new byte[100];
        FileInputStream in = null;
        try {
            if (file.exists() && file.isFile()) {
                in = new FileInputStream(file);
                while (in.read(buffer) != -1) {
                    System.out.print(new String(buffer));
                }
            } else {
                throw new IllegalArgumentException("not a exist file");
            }
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    /**
     * 如果目录不存在则创建目录.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static synchronized void mkDirsIfNotExist(String directory) {
        File file = new File(directory);
        file.mkdirs();
    }

    public static void download(InputStream in, HttpServletRequest request,
            HttpServletResponse response, String fileName) throws IOException {

        // 获得请求头中的User-Agent
        String agent = request.getHeader("User-Agent");
        // 根据不同的客户端进行不同的编码
        String filenameEncoder = "";
        if (agent.contains("MSIE")) {
            // IE浏览器
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
            filenameEncoder = filenameEncoder.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?"
                    + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
        }
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + filenameEncoder);
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);

    }
}
