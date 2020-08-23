package org.ivangrod.needlehack.domain.post;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.ivangrod.needlehack.domain.shared.StringValueObject;

public class PostId extends StringValueObject {

    private PostId(String value) {
        super(value);
    }

    public static PostId buildFromUri(String uri) {

        String identifier = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(uri.getBytes("UTF-8"), 0, uri.length());
            identifier = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new PostId(identifier);
    }
}
