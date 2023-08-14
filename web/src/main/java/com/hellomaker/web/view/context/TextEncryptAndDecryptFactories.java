package com.hellomaker.web.view.context;

import com.hellomaker.web.security.TextEncipher;
import com.hellomaker.web.security.impl.AESTextEncipher;

public final class TextEncryptAndDecryptFactories {
    private TextEncryptAndDecryptFactories(){}

    public static DelegateTextEncipher buildDelegateTextEncipher() {
        TextEncipher aesEncipher = new AESTextEncipher();
        DelegateTextEncipher delegateTextEncipher = new DelegateTextEncipher(aesEncipher);
        return delegateTextEncipher;
    }
}
