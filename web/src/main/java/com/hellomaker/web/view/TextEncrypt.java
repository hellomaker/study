package com.hellomaker.web.view;

import com.hellomaker.web.security.TextEncipher;

public interface TextEncrypt {
    String rawContent();

    TextEncipher getTextEncipher();
}
