package org.example.topic2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Demo1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("topic2/demo1/topic2-application-context-demo1.xml");

//      Example for null injection
//      Example for collection injection
//      Example for bean inheritance
        Data2 data2  = (Data2) context.getBean("bean2");
        System.out.println(data2);
    }
}

class Data {
    List<Integer> list;
    Map<Integer, String> map;
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }
}

class Data2 extends Data{
    Integer val;

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Data2{" +
                "val=" + val +
                ", list=" + list +
                ", map=" + map +
                ", str='" + str + '\'' +
                '}';
    }
}
