import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MyTest {



    @Test
    public void test(){
        //加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        //获得连接池对象
        JedisPool pool = (JedisPool) context.getBean("redisClient");
        //从连接池中获得jedis对象
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("4e798e38-7edd-4742-9d8d-c70ee0c80672"));

        jedis.del("4e798e38-7edd-4742-9d8d-c70ee0c80672");



    }

}
