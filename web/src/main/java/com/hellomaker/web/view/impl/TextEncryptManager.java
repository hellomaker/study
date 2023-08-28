package com.hellomaker.web.view.impl;

import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.view.context.TextEncryptAndDecryptFactories;

public class TextEncryptManager {
    private static TextEncipher delegateTextEncrypt;
    public static TextEncipher getDelegateTextEncrypt() {
        if (delegateTextEncrypt == null) {
            delegateTextEncrypt = TextEncryptAndDecryptFactories.buildDelegateTextEncipher();
        }
        return delegateTextEncrypt;
    }

    public static void registerTextEncipher(TextEncipher textEncipher) {
        delegateTextEncrypt = textEncipher;
    }
}
