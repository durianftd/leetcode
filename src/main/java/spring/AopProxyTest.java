package spring;

import java.util.ArrayList;
import java.util.List;

public class AopProxyTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        AOP_Proxy aopProxy = new AOP_Proxy();
        List<Integer> proxyList = (List) aopProxy.getInstance(list);
        proxyList.add(0, 111);
        System.out.println(proxyList.size());
        Integer i = proxyList.get(0);
        System.out.println(i);
        System.out.println(proxyList);
//        proxyList.size();
//        System.out.println(proxyList);

    }
}
