package cfx.webservice.client.cfxwebserviceclient;

/**
 * @Description
 * @Author yinzuomei
 * @Date 2020/1/2 18:50
 */
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


/**
 * @author Administrator
 * @date 2019/01/30
 */
public class Test {
    public static void main(String[] args){
        //在一个方法中连续调用多次WebService接口,每次调用前需要重置上下文
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //在调用第二个webservice前，需要重置上下文
        Thread.currentThread().setContextClassLoader(cl);
        printUserList(dcf);
    }

    private static void printUserList(JaxWsDynamicClientFactory dcf){
        Client client = dcf.createClient("  http://localhost:8080/demowebservice/services/webservices?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("test", "测试");
           System.out.println(objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
