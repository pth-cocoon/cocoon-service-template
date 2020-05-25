package icu.cocoon.security.service;

/**
 * 验证码功能实现.
 *
 * @author xce
 * @date 2020/3/19  11:37
 */
public interface VerificationCodeService {

  /**
   * 发送验证码.
   *
   * @param address 验证码地址.
   */
  void sendVerificationCode(String address,String code);


  /**
   * 校验.
   *
   * @param address 验证码地址.
   * @param code    验证码.
   * @return true = 通过.
   */
  boolean verify(String address, String code);

}