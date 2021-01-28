package com.example;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

public class Example implements HttpFunction {
    private static final Logger logger = Logger.getLogger(Example.class.getName());
    BufferedWriter writer = null;

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        writer = response.getWriter();

        // System.setProperty("socksProxyHost", "10.146.0.3");
        // System.setProperty("socksProxyPort", "1080");
        // System.setProperty("socksProxyVersion", "5");
        System.setProperty("socksProxyHost", System.getenv("socksProxyHost"));
        System.setProperty("socksProxyPort", System.getenv("socksProxyPort"));
        System.setProperty("socksProxyVersion", System.getenv("socksProxyVersion"));
        logger.info("socksProxyHost [" + System.getProperty("socksProxyHost") + "]");
        logger.info("socksProxyPort [" + System.getProperty("socksProxyPort") + "]");
        logger.info("socksProxyVersion [" + System.getProperty("socksProxyVersion") + "]");

        try (Socket socket = new Socket("172.31.51.22", 50050);
             OutputStream outs = socket.getOutputStream();
             BufferedInputStream ins = new BufferedInputStream(socket.getInputStream())) {

            // Greeting
            byte[] line = waitResult(ins);
            logger.info("[Response] size [" + line.length + "] response [" + bin2hex(line) + "]");

            // 本体（機器）故障状態取得
            byte[] result1 = executeCommand("03800000000A80000000", outs, ins);
            // 本体（データ）故障状態取得
            byte[] result2 = executeCommand("03810000000A80000000", outs, ins);
            // 本体（端子）故障状態取得
            byte[] result3 = executeCommand("03820000000A80000000", outs, ins);
            // 本体（アンプ）故障状態取得
            byte[] result4 = executeCommand("03830000000A80000000", outs, ins);
            // 本体（SP回線）故障状態取得
            byte[] result5 = executeCommand("03840000000A80000000", outs, ins);
            // DS故障状態取得
            byte[] result6 = executeCommand("03850000000A80000000", outs, ins);
            // RM故障状態取得
            byte[] result7 = executeCommand("03860000000A80000000", outs, ins);
            // 音声ライン故障状態取得
            byte[] result8 = executeCommand("03870000000A80000000", outs, ins);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] executeCommand(String command, OutputStream outs, InputStream ins) throws IOException, InterruptedException {
        logger.info("[Request] " + command);
        writer.write("[Request] " + command);

        outs.write(hex2bin(command));
        outs.flush();

        byte[] result = waitResult(ins);
        logger.info("[Response] size [" + result.length + "] response [" + bin2hex(result) + "]");
        writer.write("[Response] size [" + result.length + "] response [" + bin2hex(result) + "]");

        return result;
    }

    public byte[] waitResult(InputStream ins) throws IOException, InterruptedException {
        int waiMaxSecond = 5000;  //5sec
        int waitInterval = 500;   //0.5set
        int waitTime = 0;

        do {
            waitTime += waitInterval;
            Thread.sleep(waitTime);
        } while (ins.available() == 0 && waitTime <= waiMaxSecond);

        byte[] buf = new byte[1024];
        int n = ins.read(buf);
        return Arrays.copyOfRange(buf,0, n);
    }

    public String bin2hex(byte[] data) {
        return Hex.encodeHexString(data);
    }

    public byte[] hex2bin(String hex) {
        try {
            byte[] bytes = Hex.decodeHex(hex);
            return bytes;
        } catch (DecoderException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}