package com.xwl.sample.manager;


import com.xwl.sample.base.consts.Consts;
import com.xwl.sample.enums.EnumAppLanguage;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHeaderManager {

  @Autowired
  private HttpServletRequest request;

  public Integer getHeaderLanguage() {
    String language = request.getHeader(Consts.LANGUAGE);
    if (StringUtils.isEmpty(language)) {
      return EnumAppLanguage.JT.getType();
    }
    return Integer.parseInt(language);
  }

}
