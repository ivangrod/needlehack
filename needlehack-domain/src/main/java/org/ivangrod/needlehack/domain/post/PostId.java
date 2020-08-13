package org.ivangrod.needlehack.domain.post;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.ivangrod.needlehack.domain.shared.StringValueObject;

public class PostId extends StringValueObject {

    private String idFromUri;

    private PostId(String idFromUri) {
        super(null);
        this.idFromUri = idFromUri;
    }

    private PostId(String value, String idFromUri) {
        super(value);
        this.idFromUri = idFromUri;
    }

    public static PostId buildFromUri(String uri) {

        String identifier = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(uri.getBytes());
            identifier = new String(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new PostId(identifier);
    }

    public static PostId buildFromIdentifier(String identifier) {
        return new PostId(identifier);
    }

    public String getIdFromUri() {
        return idFromUri;
    }
}
