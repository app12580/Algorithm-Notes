import org.junit.Test;

import java.util.*;

public class TestHashCollection {

    HashAndEquals hae1 = new HashAndEquals("hae1。。1", 1);
    HashAndEquals hae2 = new HashAndEquals("hae1。。2", 1);
    HashAndEquals hae3 = new HashAndEquals("hae2", 2);
    HashAndEquals hae4 = new HashAndEquals("hae3。。1", 3);
    HashAndEquals hae5 = new HashAndEquals("hae3。。2", 3);

    OnlyHash onlyHash1 = new OnlyHash("onlyHash1。。1", 1);
    OnlyHash onlyHash2 = new OnlyHash("onlyHash1。。2", 1);
    OnlyHash onlyHash3 = new OnlyHash("onlyHash2", 2);
    OnlyHash onlyHash4 = new OnlyHash("onlyHash3。。1", 3);
    OnlyHash onlyHash5 = new OnlyHash("onlyHash3。。2", 3);

    /**
     * 遇到相同key时候的策略：
     * set无视重复的提交
     * map会重复操作，更新value值，但不会更新key值
     */
    @Test
    public void testHashAndEquals() {
        Set set = new HashSet();
        set.add(hae1);  //√
        set.add(hae2);  //×
        set.add(hae3);  //√
        set.add(hae4);  //√
        set.add(hae5);  //×

        Map map = new HashMap();
        map.put(hae1, "1");  //×
        map.put(hae2, "2");  //√
        map.put(hae3, "3");  //√
        map.put(hae4, "4");  //×
        map.put(hae5, "5");  //√

        System.out.println("set:" + set);
        System.out.println("map:" + map);
    }

    @Test
    public void testOnlyHash() {
        Set set = new HashSet();
        set.add(onlyHash1);  //√
        set.add(onlyHash2);  //√
        set.add(onlyHash3);  //√
        set.add(onlyHash4);  //√
        set.add(onlyHash5);  //√

        Map map = new HashMap();
        map.put(onlyHash1, "1");  //√
        map.put(onlyHash2, "2");  //√
        map.put(onlyHash3, "3");  //√
        map.put(onlyHash4, "4");  //√
        map.put(onlyHash5, "5");  //√

        System.out.println("set:" + set);
        System.out.println("map:" + map);
    }

}

class MyHashTest {
    private String desc;
    private Integer code;

    public MyHashTest(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyHashTest{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }
}

class OnlyHash extends MyHashTest {
    public OnlyHash (String desc, Integer code) {
        super(desc, code);
    }

    @Override
    public int hashCode() {
        return getCode();
    }
}



class HashAndEquals extends MyHashTest {
    public HashAndEquals (String desc, Integer code) {
        super(desc, code);
    }

    @Override
    public int hashCode() {
        return getCode();
    }

    @Override
    public boolean equals(Object obj) {
        //自反性
        if (this == obj) return true;
        //任何对象不等于null，比较是否为同一类型
        if (!(obj instanceof HashAndEquals)) return false;
        //强制类型转换
        HashAndEquals other = (HashAndEquals) obj;
        //比较属性值
        return Objects.equals(getCode(), other.getCode());
    }

}
