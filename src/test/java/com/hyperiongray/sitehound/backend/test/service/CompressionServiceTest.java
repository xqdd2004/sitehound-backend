package com.hyperiongray.sitehound.backend.test.service;

import com.hyperiongray.sitehound.backend.service.CompressionService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompressionServiceTest {

    private CompressionService compressionService = new CompressionService();
//    private String initialString = "Mögen Sie Sprechen Deutsch?";
//    private String initialString = "Hello World!";


    @Test
    public void b64compress() throws Exception {
        String initialString = "Hello World!";
        String s = compressionService.b64compress(initialString);
        Assert.assertEquals("H4sIAAAAAAAAAPNIzcnJVwjPL8pJUQQAoxwpHAwAAAA=", s);
    }
    @Test
    public void b64compressNotAnsi() throws Exception {
        String initialString = "Mögen Sie Sprechen Deutsch?";
        String s = compressionService.b64compress(initialString);
        Assert.assertEquals("H4sIAAAAAAAAAPM9vC09NU8hODNVIbigKDU5A8hxSS0tKU7OsAcA5ovMNhwAAAA=", s);
    }

    @Test
    public void b64decompresAnsi() throws Exception {
        String originalString = "Hello World!";
//        String initialString = "Mögen Sie Sprechen Deutsch?";
        String b64compressed = "H4sIAAAAAAAAAPNIzcnJVwjPL8pJUQQAoxwpHAwAAAA=";
        String b64decompressed = compressionService.b64decompress(b64compressed);
        Assert.assertEquals(originalString, b64decompressed);
    }

    @Test
    public void b64decompress() throws Exception {
        String originalString = "Mögen Sie Sprechen Deutsch?";
        String b64compressed = "H4sIAAAAAAAAAPM9vC09NU8hODNVIbigKDU5A8hxSS0tKU7OsAcA5ovMNhwAAAA=";
        String b64decompressed = compressionService.b64decompress(b64compressed);
        Assert.assertEquals(originalString, b64decompressed);
    }

    @Test
    public void compressAndDecompress() throws Exception {
        String initialString = "Hello world!";
        String s = compressionService.b64compress(initialString);
        String b64decompress = compressionService.b64decompress(s);
        Assert.assertEquals(initialString, b64decompress);
    }
    @Test
    public void compressAndDecompressDIf() throws Exception {
        String initialString = "Mögen Sie Sprechen Deutsch?";
        String s = compressionService.b64compress(initialString);
        String b64decompress = compressionService.b64decompress(s);
        Assert.assertEquals(initialString, b64decompress);
    }




}