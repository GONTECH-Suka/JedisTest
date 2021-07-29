package Test;

import redis.clients.jedis.Jedis;

public class Connection {
	
	private static Jedis jedis = null;
	
	public static void main(String[] args) {
		jedis = new Jedis("127.0.0.1",6379);
		typeString();
	}
	
	public static void typeString() {
		
		System.out.println("�л�����3�����ݿ�"+jedis.select(2));
		System.out.println("��յ�ǰ���ݿ�����:"+jedis.flushDB());
		System.out.println("�ж�ĳ�����Ƿ����:"+jedis.exists("name"));
		System.out.println("����<'name,'zhangsan'>��ֵ��:"+jedis.set("name", "zhangsan"));
		System.out.println("����<'password,'123456'>��ֵ��:"+jedis.set("pwd", "114514"));
		System.out.println("ϵͳ�����м�:"+jedis.keys("*"));
		System.out.println("������pwdΪpassword:"+jedis.rename("pwd","password"));
		System.out.println("ɾ����password:"+jedis.del("password"));
		System.out.println("�ж�pwd�Ƿ����:"+jedis.exists("pwd"));
		System.out.println("�鿴��name����ֵ������:"+jedis.type("name"));
		System.out.println("�鿴��name�д洢������:"+jedis.get("name"));
		System.out.println("�鿴��ǰ���ݿ�key������:"+jedis.dbSize());
		
		System.out.println("��ȡֵ�������õ�������"+jedis.getSet("name", "Lisi"));
		System.out.println("�鿴��name�޸ĺ󴢴��ֵ:"+jedis.get("name"));
		System.out.println("����<'salary,'50000'>��ֵ��:"+jedis.set("salary", "50000"));
		System.out.println("��ֵ+1:"+jedis.incr("salary"));
		System.out.println("��ֵ-1:"+jedis.decr("salary"));
		System.out.println("��ֵ+50:"+jedis.incrBy("salary",50));
		System.out.println("��ֵ-50:"+jedis.decrBy("salary",50));
		
		System.out.println("�ڼ�name��ֵ��׷���ַ���:"+jedis.append("name","WSNGG"));
		System.out.println("�鿴��name�޸ĺ󴢴��ֵ:"+jedis.get("name"));
		System.out.println("��ȡ��nameֵ���ַ�����:"+jedis.strlen("name"));
		System.out.println("��ȡ��nameֵ�±�1��4���ַ�:"+jedis.getrange("name",1,4));
		System.out.println("��ָ���±�����������ַ�,�������ַ��Զ�������:"+jedis.setrange("name",4,"ChiBaBa"));
		System.out.println("�鿴��name�޸ĺ󴢴��ֵ:"+jedis.get("name"));
		
		System.out.println("��sex֮ǰû�д��������óɹ�:"+jedis.setnx("sex","male"));
		System.out.println("sex���Ѵ��ڣ�����ʧ��:"+jedis.setnx("sex","female"));
		System.out.println("һ�������ö����ֵ��:"+jedis.mset("address","xxxxxxx","age","21"));
		System.out.println("һ���Ի�ȡ�����ֵ��:"+jedis.mget("name","age","salary","address"));
	}
	
	
}
