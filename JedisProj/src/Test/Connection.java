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
	
	public static void typeList() {   //����Ϊ����
		
		System.out.println("�л�����1�����ݿ�:"+jedis.select(0));
		System.out.println("��յ�ǰ���ݿ�����:"+jedis.flushDB());
		System.out.println("ͷ�巨:"+jedis.lpush("name", "cyka"));
		System.out.println("β�巨:"+jedis.rpush("name", "blyat"));
		System.out.println("β�巨:"+jedis.rpush("name", "pipe"));
		System.out.println("β�巨:"+jedis.rpush("name", "water"));
		System.out.println("ͷ�巨:"+jedis.lpush("name", "maozi"));
		System.out.println("��������Ԫ��:"+jedis.lrange("name",0,-1));
		System.out.println("�����±�Ϊ0��1��Ԫ��:"+jedis.lrange("name",0,1));
		System.out.println("��ͷ����:"+jedis.lpop("name"));
		
		System.out.println("ͨ���±�������ѯԪ��:"+jedis.lindex("name", 2));
		System.out.println("��ѯ���ӵĴ�С:"+jedis.llen("name"));
		System.out.println("�Ƴ�һ��Ԫ����ֵΪpipe��Ԫ�أ�̰��ģʽ��:"+jedis.lrem("name",1,"pipe"));
		System.out.println("��ȡ�±�1��2��Ԫ�أ�����Ԫ��ɾ��:"+jedis.ltrim("name",1,2));
		
		System.out.println("��������Ƚ�list�Ķ�βԪ���߳�����ͷ�嵽otherlist����:"+jedis.rpoplpush("name", "otherlist"));
		System.out.println("�޸��±�Ϊ0��Ԫ��ΪRussia:"+jedis.lset("name",0,"Russia"));
		System.out.println("��Russia��ǰ�����һ����zard��Ԫ��:"+jedis.linsert("name",ListPosition.BEFORE,"Russia","zard"));
		System.out.println("��zard�ĺ������һ����love��Ԫ��:"+jedis.linsert("name",ListPosition.AFTER,"zard","love"));
		System.out.println("��������Ԫ��:"+jedis.lrange("name",0,-1));
		
		
	}
	
	public static void typeSet() {    //Set�������ظ���Ա
	
		System.out.println("�л�����16�����ݿ�:"+jedis.select(15));
		System.out.println("��յ�ǰ���ݿ�����:"+jedis.flushDB());
		System.out.println("�����³�Ա��������룩:"+jedis.sadd("myset","cyka"));
		System.out.println("�����³�Ա:"+jedis.sadd("myset","blyat"));
		System.out.println("�����³�Ա:"+jedis.sadd("myset","Russia"));
		System.out.println("�������г�Ա:"+jedis.smembers("myset"));
		System.out.println("��ѯblyat��Ա�Ƿ����:"+jedis.sismember("myset","blyat"));
	
		System.out.println("��ѯmyset�м�����Ա:"+jedis.scard("myset"));
		System.out.println("ɾ����Ϊcyka�ĳ�Ա:"+jedis.srem("myset","cyka"));
		System.out.println("�����ȡһ����Ա���:"+jedis.srandmember("myset"));
		System.out.println("�����ȡ������Ա���:"+jedis.srandmember("myset",2));
		System.out.println("��myset�ĳ�Աblyat�ƶ���myset2��:"+jedis.smove("myset","myset2","blyat"));
	
		System.out.println("��mysetΪ��׼�Ҳ:"+jedis.sdiff("myset","myset2"));
		System.out.println("��mysetΪ��׼�ҽ���:"+jedis.sinter("myset","myset2"));
		System.out.println("��mysetΪ��׼�Ҳ���:"+jedis.sunion("myset","myset2"));
	
	}
	
	public static void typeHash() {
		System.out.println("�л�����3�����ݿ�:"+jedis.select(2));
		System.out.println("��յ�ǰ���ݿ�����:"+jedis.flushDB());
		System.out.println("����<'name,'zhangsan'>��ֵ��:"+jedis.hset("myhash","name","zhangsan"));
		System.out.println("����<'number,'114514'>��ֵ��:"+jedis.hset("myhash", "number","114514"));
		System.out.println("myhash�����м�ֵ��:"+jedis.hgetAll("myhash"));
		System.out.println("ɾ����name:"+jedis.hdel("myhash","name"));
		System.out.println("��ȡָ����ϣ�ĳ���:"+jedis.hlen("myhash"));
		System.out.println("�鿴ָ��key->myhash�Ƿ����:"+jedis.hexists("myhash","number"));
		System.out.println("�鿴ָ����ϣ�����е�key:"+jedis.hkeys("myhash"));
		System.out.println("�鿴ָ����ϣ�����е�value:"+jedis.hvals("myhash"));
		
		System.out.println("ָ����numberֵ��10:"+jedis.hincrBy("myhash","number",10));
		System.out.println("ָ����numberֵ-5:"+jedis.hincrBy("myhash","number",-5));
	}

	
	
}