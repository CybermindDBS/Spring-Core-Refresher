package org.example.topic1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Demo1
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("topic1/topic1-application-context.xml");
        Data data = (Data) context.getBean("bean1");
        System.out.println(data);
        Data data2 = (Data) context.getBean("bean1");
        System.out.println(data2);
        System.out.println(data.hashCode() + " : " + data2.hashCode());
    }
}

class Data
{
    String val1, val2;
    int n;

    Data(String val1, String val2)
    {
        this.val1 = val1;
        this.val2 = val2;
    }


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString()
    {
        return "(" + val1 + " " + val2 + " : " + n + ")";
    }
}
