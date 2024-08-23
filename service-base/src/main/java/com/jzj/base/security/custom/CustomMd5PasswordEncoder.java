package com.jzj.base.security.custom;

import com.jzj.base.utils.sign.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 密码处理
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utils.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5Utils.encrypt(charSequence.toString()));
    }
}
