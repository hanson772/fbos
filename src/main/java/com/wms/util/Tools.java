package com.wms.util;

import com.wms.bean.enums.EProperty;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author max.chen
 * @class
 */
public class Tools {
    private static Logger log = LoggerFactory.getLogger(Tools.class);

    public static String Datetime_Format = "yyyy-MM-dd HH:mm:ss";
    public static String Date_Format = "yyyy-MM-dd";
    /**
     * id解密
     *
     * @param idStr
     * @return
     */
    public static int idDecode(String idStr) {
        if(idStr.equalsIgnoreCase("0")){
            return 0;
        }
        String idEncodeKey = EProperty.PROJECT_ID_ENCODE.value("sadwer23");
        String idPre = EProperty.PROJECT_ID_PRE.value("sad235b");

        String decode = decode(idStr, idEncodeKey);
        if (StringUtils.hasText(decode) && decode.startsWith(idPre)) {
            decode = decode.replaceFirst(idPre, "");
            return Integer.valueOf(decode);
        }
        return 0;
    }
    public static List<Integer> idDecodeBatch(List<String> ids){
        List<Integer> results = new ArrayList<>();
        ids.stream().forEach(x->{
            int i = idDecode(x);
            if(i > 0){
                results.add(i);
            }
        });
        return results;
    }

    /**
     * 解密
     * @param object  解密对象
     * @param missKey 解密key
     * @return
     */
    public static String decode(String object, String missKey) {
        String decode = "";
        if (StringUtils.hasText(object)) {
            try {
                DESKeySpec desKeySpec = new DESKeySpec(missKey.getBytes());
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey generateKey = secretKeyFactory.generateSecret(desKeySpec);

                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, generateKey);
                byte[] result = Hex.decodeHex(object.toCharArray());
                decode = new String(cipher.doFinal(result));
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("解密-转码异常");
            }
        }
        return decode;
    }

    /**
     * ID加密
     *
     * @param i
     * @return
     */
    public static String idEncode(Integer i) {
        if (i != null) {
            String idEncodeKey = EProperty.PROJECT_ID_ENCODE.value("sadwer23");
            String idPre = EProperty.PROJECT_ID_PRE.value("sad235b");

            String str = idPre + i;
            return encode(str, idEncodeKey);
        }

        return "";
    }

    /**
     * 加密
     * @param object  加密对象
     * @param missKey 加密key
     * @return
     */
    public static String encode(String object, String missKey) {
        String encode = "";
        if (StringUtils.hasText(object)) {
            try {
                DESKeySpec desKeySpec = new DESKeySpec(missKey.getBytes());
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey generateKey = secretKeyFactory.generateSecret(desKeySpec);

                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, generateKey);
                byte[] resultBytes = cipher.doFinal(object.getBytes());

                encode = Hex.encodeHexString(resultBytes);
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("加密-转码异常");
            }
        }
        return encode;
    }
    public static Date dateFrom(String format, String dateStr){
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static long dateToTime(Date dt){
        return dt == null ? 0L : dt.getTime();
    }
    public static Date timeToDate(long time){
        return new Date(time < 10000000000L ? time * 1000L : time);
    }
    public static String formatDate(String format, Date dt){
        return new SimpleDateFormat(format).format(dt);
    }




    public static boolean regexNumber(String number){
        return StringUtils.hasText(number) && number.trim().matches("^[0-9]*$");
    }
    // 正则匹配中国手机号码
    public static boolean regexPhone(String phone) {
        return StringUtils.hasText(phone) && phone.trim().matches("^[1][3456789][0-9]{9}$");
    }
    /**
     * 校验座机
     *
     * @param tel
     * @return
     */
    public static boolean regexTel(String tel) {
        return StringUtils.hasText(tel) && tel.trim().matches("^(0[1-9][0-9]{1,2}-)?[2-9][0-9]{6,7}$");
    }
    // 匹配邮箱格式
    public static boolean regexEmail(String email){
        return StringUtils.hasText(email) && email.trim().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }

    /**
     * 校验对象的某些字段是否为空
     * @param obj
     * @param props
     */
    public static Map<String, Object> checkFieldsIsBlank(Object obj, String... props){
        Map<String, Object> map = new HashMap<String, Object>();
        // 校验对象为空，跑出异常
        if(obj == null){
            Assert.isTrue(false, "当前对象为空");
        }
        // 校验字段列表为空，返回空Map
        if(props == null || props.length == 0){
            return map;
        }
        // props转list
        List<String> checkList = Arrays.asList(props);
        // 获取f对象对应类中的所有属性域
        Class clazz = obj.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            // 当前Field需要校验
            if(checkList.contains(varName)) {
                try {
                    // 获取原来的访问控制权限
                    boolean accessFlag = fields[i].isAccessible();
                    // 修改访问控制权限
                    fields[i].setAccessible(true);
                    // 获取在对象f中属性fields[i]对应的对象中的变量
                    Object o = fields[i].get(obj);
                    if (o == null) {
                        String desc = varName;
                        // 获取字段属性的描述
                        //desc = fields[i];
                        map.put(varName, desc);
                    }
                    // 恢复访问控制权限
                    fields[i].setAccessible(accessFlag);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return map;
    }

}
