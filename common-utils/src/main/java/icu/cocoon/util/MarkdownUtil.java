package icu.cocoon.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.pegdown.PegDownProcessor;

/**
 * 处理markdown工具类.
 *
 * @author xce
 */
public class MarkdownUtil {

  private final static PegDownProcessor peg = new PegDownProcessor(Integer.MAX_VALUE);


  /**
   * markdown 转纯文本
   *
   * @param md md字符串
   * @return String
   */
  public static String toText(String md) {
    String html = toHtml(md);
    return cleanHtmlTag(html);
  }

  /**
   * markdown 转HTML
   *
   * @param md md字符串
   * @return html 字符串
   */
  public static String toHtml(String md) {
    return peg.markdownToHtml(md);
  }

  /**
   * 清理HTML标签，还原原始文档。
   *
   * @param html HTML字符串
   * @return 原始文档
   */
  public static String cleanHtmlTag(String html) {
    return Jsoup.clean(html, new Whitelist());
  }
}
