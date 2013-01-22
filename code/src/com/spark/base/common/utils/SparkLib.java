package com.spark.base.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang3.RandomStringUtils;
/**
 * �����ṩЩ��ֵ��ʽ��Ϊ�ַ����ľ�̬����
 */
public class SparkLib {
	/**
	 * ���ַ���
	 */
  public static final String     EMPTY_STRING          = "";
  /**
   * �ַ���"0"
   */
  public static final String     SQL_FALSE             = "0";
  /**
   * �ַ���"1"
   */
  public static final String     SQL_TRUE              = "1";
  
  /**
   * ʮ�������ַ�
   */
  public final static char[] HEX_CHARS={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
 /**
  * base64�ַ�
  */
  public final static char[] BASE64_CHARS = {
      'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
      '0','1','2','3','4','5','6','7','8','9','+','/','='};
  /**
   * �ж��ַ����Ƿ�Ϊ��ĸ������
   * @param s �ַ���
   * @return false:������ĸ������������ַ� true:ȫ����ĸ������
   */
  public static boolean isSymbol(String s){
    if((s==null)||(s.length()==0)) return false;
    for(int i=0;i<s.length();i++)
      if(!Character.isLetterOrDigit(s.charAt(i))) return false;
    return true;
  }
  /**
   * ��rep�滻�ַ���s������sub�ַ���
   * @param s ĸ�ַ���
   * @param sub ���滻�����ַ���
   * @param rep �����滻���ַ���
   * @return �滻����ַ���
   */
  public static String replaceString(String s, String sub, String rep){
	    if (rep==null) rep = "";
	    StringBuffer sb = new StringBuffer();
	    int fromIndex = 0, pos =0;
	    do{
	      pos = s.indexOf(sub,fromIndex);
	      if (pos>=0){
	        if (pos>fromIndex)
	          sb.append(s.substring(fromIndex,pos));
	        sb.append(rep);
	        fromIndex = pos + sub.length();
	      }
	    } while (pos >= 0);
	    if (fromIndex<s.length())
	      sb.append(s.substring(fromIndex,s.length()));
	    return sb.toString();
	  }
  
  public static boolean isIdentifier(String s){
    if((s==null)||(s.length()==0)) return false;
    for(int i=0;i<s.length();i++)
      if(!Character.isLetterOrDigit(s.charAt(i))) return false;
    return Character.isLetter(s.charAt(0));
  }
  /**
   * �ж��ַ����Ƿ�����˿հ��ַ�
   * @param s �ַ���
   * @return true:�����հ׷� false:������
   */
  public static boolean hasSpaceChar(String s){
    for(int i=0;i<s.length();i++)
      if(Character.isSpaceChar(s.charAt(i))) return true;
    return false;
  }
  /**
   * ��ȡISO8859_1�������ַ���
   * @param input �ַ���
   * @return �������ַ���
   */
  public static String convInput(String input){
    try {
      return new String(input.getBytes("ISO8859_1"));
    }
    catch (Exception ex) {
      return input;
    }
  }
  /**
   * �ַ������˫����
   * @param s Ҫ���˫���ŵ��ַ���
   * @return ���˫���ź���ַ���
   */
  public static String quoteString(String s){
    return '"'+s+'"';
  }
  /**
   * �ַ�����ӵ�����
   * @param s Ҫ��ӵ����ŵ��ַ���
   * @return ��ӵ����ź���ַ���
   */
  public static String quoteStringSingle(String s){
    return "'"+s+"'";
  }
  /**
   * ��ʽ����ֵΪ�ַ���
   * @param iv ��ֵ
   * @param width �������ֵ�λ��
   * @param seperator �Ƿ�����ʽ��
   * @return ��ʽ����������ַ���
   */
  public static String toFixedString(long iv, int width, boolean seperator){
    NumberFormat fmt=new DecimalFormat();
    fmt.setGroupingUsed(seperator);
    fmt.setMaximumIntegerDigits(width);
    fmt.setMinimumIntegerDigits(width);
    return fmt.format(iv);
  }
  /**
   * ��ʽ����ֵΪ�ַ���
   * @param iv ��ֵ
   * @param seperator �Ƿ�����ʽ��
   * @return ��ʽ����������ַ���
   */
  public static String toFixedString(long iv, boolean seperator){
    NumberFormat fmt=new DecimalFormat();
    fmt.setGroupingUsed(seperator);
    return fmt.format(iv);
  }
  /**
   * ��ʽ����ֵΪ�ַ���
   * @param value ��ֵ
   * @param seperator �Ƿ�����ʽ��
   * @return ��ʽ����������ַ���
   */
  public static String toFixedString(double value, boolean seperator){
    NumberFormat fmt=new DecimalFormat();
    fmt.setGroupingUsed(seperator);
    return fmt.format(value);
  }
  /**
   * ���ַ����а�ĳ���ַ�ȫ���޳���
   * @param str �ַ���
   * @param ch Ҫ�޳������ַ�
   * @return �޳�ĳ���ַ�����ַ���
   */
  public static String strExclude(String str, char ch){
    if(str==null) return null;
    char[] chars=new char[str.length()];
    int index=0;
    for (int i=0; i<str.length(); i++){
      char c=str.charAt(i);
      if(c!=ch)chars[index++]=c;
    }
    return new String(chars,0,index);
  }
  /**
   * ���ַ����ж���ָ������������
   * @param text �ַ���
   * @param linenum ����
   * @return ����������
   */
  public static String getLine(String text, int linenum){
    BufferedReader buffer=new BufferedReader(new StringReader(text));
    try{
      String line=null;
      while(linenum-->=0)
        line=buffer.readLine();
      return line;
    }catch(IOException ex){
      return text;
    }
  }

  public static String encodePassword(String password,int maxlength){
    int s1=97;
    int s2=76;
    int len=password==null?0:password.length();

    if(len>maxlength) len=maxlength;
    StringBuffer pw=new StringBuffer(2*maxlength);
    for(int i=0;i<maxlength;i++){
      int ch,ch1,ch2;
      ch=i>=len?0:password.charAt(i);
      ch=(s1+++ch)^s2++;
      ch1=ch/26+(int)'I';ch2=ch%26+(int)'A';
      if((i & 1)==1) s2+=ch1&0xf;
      else s2-=ch1&0xf;
      pw.append((char)ch1);
      pw.append((char)ch2);
    }
    return new String(pw);
  }

  public static String decodePassword(String password){
    int s1=97;
    int s2=76;
    int len=password==null?0:password.length()/2;

    StringBuffer pw=new StringBuffer();
    for(int i=0;i<len;i++){
      int ch,ch1,ch2;
      ch1=password.charAt(2*i);
      ch2=password.charAt(2*i+1);
      ch=(ch1-(int)'I')*26+(ch2-(int)'A');
      ch=(ch^s2++)-(s1++);
      if(ch==0) break;
      pw.append((char)ch);
      if((i & 1)==1) s2+=ch1&0xf;
      else s2-=ch1&0xf;
    }
    return new String(pw);
  }

  public static int[] stringToColRow(String str){
    int[] pos=new int[2];
    pos[0]=0;pos[1]=0;
    for (int i=0; i<str.length(); i++){
      char ch=str.charAt(i);
      if(ch>='A' && ch<='Z'){
        pos[0]=pos[0]*26+(byte)ch-(byte)'A';
      }else{
        if(i>0) pos[0]+=1;
        try{
          pos[1]=Integer.parseInt(str.substring(i));
        }catch(NumberFormatException ex){
          pos[1]=0;
        }
        break;
      }
    }
    if(pos[0]>0 && pos[1]>0) return pos;
    else return null;
  }
  public static String colRowToString(int x, int y){
    char[] chrArray = new char[64];
    int len = 0;
    while(x > 0){
      char ch = (char)(x % 26 + (byte)'A' - 1);
      x = x / 26;
      if(ch < 'A'){
        ch = 'Z';
        x -= 1;
      }
      chrArray[len] = ch;
      len += 1;
    }
    String colStr = "";
    for(int i=0;i<len;i++)
      colStr = colStr + chrArray[len-i-1];
    return colStr+Integer.toString(y);
  }
  /**
   * ʮ������ת��λʮ������
   * @param iv ʮ������
   * @return ת�����ʮ�����������ַ���
   */
  public static String makeHexString(int iv) {
     char[] buf = new char[8];
     int charPos = 7;
     while(iv!=0){
       buf[charPos--]=HEX_CHARS[iv & 15];
       iv>>>=4;
     };
     while(charPos>=0) buf[charPos--]=HEX_CHARS[0];
     return new String(buf);
 }
  
 public static void main(String args[]){
	 for(int i=0;i<10000;i++){
		 System.out.println(makeHexString(new BigDecimal(RandomStringUtils.randomNumeric(6)).intValue()));
	 }
	
 }
}
