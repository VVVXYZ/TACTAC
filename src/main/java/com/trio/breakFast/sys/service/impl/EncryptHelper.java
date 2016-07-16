package com.trio.breakFast.sys.service.impl;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class EncryptHelper {
    private AesCipherService aesCipherService = new AesCipherService();
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    final private String algorithmName = "md5";
    final private int hashIterations = 2;

    public String getSalt() {
        return randomNumberGenerator.nextBytes().toHex();
    }

    public String md5Hash(String source, String salt) {
        return new SimpleHash(algorithmName, source, ByteSource.Util.bytes(salt), hashIterations).toHex();
    }

    public String base64Encode(String source){
        return Base64.encodeToString(source.getBytes());
    }

    public String base64Decode(String source){
        return Base64.decodeToString(source);
    }

    public String hexEncode(String source){
        return Hex.encodeToString(source.getBytes());
    }

    public String hexDecode(String source){
        return new String(Hex.decode(source));
    }

    public Key getAesKey(int size){
        aesCipherService.setKeySize(size);
        return aesCipherService.generateNewKey();
    }

    public String aesEncrypt(String source, Key key){
        return aesCipherService.encrypt(source.getBytes(), key.getEncoded()).toHex();
    }

    public String aesDecrypt(String source, Key key){
        return new String(aesCipherService.decrypt(Hex.decode(source), key.getEncoded()).getBytes());
    }
}
