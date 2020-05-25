package icu.cocoon.system.controller;

import icu.cocoon.core.resp.IResp;
import icu.cocoon.security.service.VerificationCodeService;
import icu.cocoon.dao.base.Resp;
import icu.cocoon.util.ValidatorUtil;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xce
 * @date 2020/3/13  17:48
 */
@Slf4j
@RestController
@RequestMapping( "/public/sms" )
public class SmsController {

  @Resource
  private VerificationCodeService verificationCodeService;

  @ApiOperation( "短信发送接口" )
  @PostMapping( "/sendSmsCode/{phone}" )
  public IResp<String> sendSmsCode(@PathVariable String phone) {
    if (!ValidatorUtil.checkPhone(phone)){
      return Resp.error("请输入正确手机号");
    }
    verificationCodeService.sendVerificationCode(phone,"123456");
    return Resp.success();
  }



}
