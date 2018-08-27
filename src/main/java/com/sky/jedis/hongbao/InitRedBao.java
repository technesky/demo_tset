package com.sky.jedis.hongbao;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertEquals;

/**
 * @program: demo_tset
 * @description: 红包初始化
 * @author: suznhg
 * @create: 2018-08-13
 **/
public class InitRedBao {
    private Jedis jedis=new Jedis("39.108.7.26",6379);//非切片额客户端连接
    static String tryGetHongBaoScript =
//          "local bConsumed = redis.call('hexists', KEYS[3], KEYS[4]);\n"
//          + "print('bConsumed:' ,bConsumed);\n"
            "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"
                    + "return nil\n"
                    + "else\n"
                    + "local hongBao = redis.call('rpop', KEYS[1]);\n"
//          + "print('hongBao:', hongBao);\n"
                    + "if hongBao then\n"
                    + "local x = cjson.decode(hongBao);\n"
                    + "x['userId'] = KEYS[4];\n"
                    + "local re = cjson.encode(x);\n"
                    + "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
                    + "redis.call('lpush', KEYS[2], re);\n"
                    + "return re;\n"
                    + "end\n"
                    + "end\n"
                    + "return nil";
    private String getHongBaoScript="if redis.call('hexists',KEYS[3],KEYS[4]) ~= 0 then\n" +
                                        "return nil\n" +
                                    "else\n" +
                                        "local hongbao= redis.call('rpop',KEYS[1]);\n" +
                                        "if hongbao then\n" +
                                            "local x=cjson.decode(hongbao);\n"+
                                            "x['userId']=KEYS[4];\n"+
                                            "local re=cjson.encode(x);\n"+
                                            "redis.call('hset',KEYS[3],KEYS[4],'1');\n"+
                                            "redis.call('lpush',KEYS[2],re);\n"+
                                            "return re;\n"+
                                        "end\n"+
                                    "end\n"+
                                    "return nil";

    private String hongBaoPoolKey="hongBaoPoolKey";
    private String hongBaoDetailListKey="hongBaoDetailListKey";
    private String userIdRecordKey="userIdRecordKey";

    @Test
    public void initRedBao() throws InterruptedException, JSONException {
        jedis.flushAll();
        JSONObject jsonpObject=new JSONObject();
        Random random =new Random();

        for (int i = 1; i <=100 ; i++) {
            jsonpObject.put("id","rid_"+i);
            jsonpObject.put("money",(random.nextInt()*100));
            jedis.lpush(hongBaoPoolKey,jsonpObject.toString());
        }

        System.out.println("元素的个数："+jedis.llen(hongBaoPoolKey));

    }

    @Test
    public void evalBulk() {

        String script = "return KEYS[1]";
        List<String> keys = new ArrayList<String>();
        keys.add("key1");

        List<String> args = new ArrayList<String>();
        args.add("first");

        String response = (String) jedis.eval(script, keys, args);
        System.out.println(response);

        assertEquals("key1", response);

    }

    @Test
    public void test(){
        String SCRIPT="local resultKeys={};\n" +
                " local tmp = redis.call('hget', 'user_001', ARGV[1]);\n " +
                "table.insert(resultKeys,'user_001');\n" +
                "return resultKeys;";
        String[] allUserKeys={};
        List<String> keys = Arrays.asList(allUserKeys);
    List<String> params = new ArrayList<String>();
    String shaFuncKey = jedis.scriptLoad(SCRIPT);//加载脚本，
    List<String> resultKeys=new ArrayList<String>();
    try {
        resultKeys = (List<String>)jedis.evalsha(shaFuncKey, keys , params);
    } catch (Exception e) {
    e.printStackTrace();
    System.out.println("lua 脚本执行报错："+e);
    }
    for (String str : resultKeys) {
    System.out.println(str);
    }

    }
    @Test
    public void getNum() {
        System.out.println("元素的个数："+jedis.llen(hongBaoPoolKey));


        return;
    }

    @Test
    public void getRedBao() throws InterruptedException {

        CountDownLatch countDownLatch=new CountDownLatch(1);
        for (int i = 1; i <=1 ; i++) {

            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"启动线程！");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"开始抢红包！");
                String userId= "userId_"+Thread.currentThread().getName();

                System.out.println(userId);
                Object object=jedis.eval(tryGetHongBaoScript,4,hongBaoPoolKey,hongBaoDetailListKey,userIdRecordKey,userId);
                if (null!= object){
                    System.out.println("红包详情："+object);
                }else{
                    System.out.println("红包抢光了！！！");
                }

            },String.valueOf(i)).start();

            countDownLatch.countDown();

        }
        System.out.println("主线程最后执行！");


    }


}
