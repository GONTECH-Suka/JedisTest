package Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisTX {
	
	private static Jedis jedis = null;
	private static String ADDR = "127.0.0.1";
	private static int PORT = 6379;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jedis = new Jedis(ADDR,PORT);
		jedis.flushDB();
		
		//开启事务
		Transaction trans = jedis.multi();
		try {
			trans.rpush("list", "Russia");
			trans.rpush("list", "cyka");
			trans.rpush("list", "blyat");
			
			trans.exec();  //执行事务
		} catch (Exception e) {
			// TODO: handle exception
			trans.discard();  //若出现错误则取消执行事务
			e.printStackTrace();
		}finally {
			System.out.println(jedis.lrange("list",0,-1));
			jedis.close();  //关闭连接
		}
		
	}

}
