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
		
		//��������
		Transaction trans = jedis.multi();
		try {
			trans.rpush("list", "Russia");
			trans.rpush("list", "cyka");
			trans.rpush("list", "blyat");
			
			trans.exec();  //ִ������
		} catch (Exception e) {
			// TODO: handle exception
			trans.discard();  //�����ִ�����ȡ��ִ������
			e.printStackTrace();
		}finally {
			System.out.println(jedis.lrange("list",0,-1));
			jedis.close();  //�ر�����
		}
		
	}

}
