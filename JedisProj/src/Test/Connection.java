package Test;

import redis.clients.jedis.Jedis;

public class Connection {
	
	private static Jedis jedis = null;
	
	public static void main(String[] args) {
		jedis = new Jedis("127.0.0.1",6379);
		typeString();
	}
	
	public static void typeString() {
		
		System.out.println("切换到第3个数据库"+jedis.select(2));
		System.out.println("清空当前数据库数据:"+jedis.flushDB());
		System.out.println("判断某个键是否存在:"+jedis.exists("name"));
		System.out.println("新增<'name,'zhangsan'>键值对:"+jedis.set("name", "zhangsan"));
		System.out.println("新增<'password,'123456'>键值对:"+jedis.set("pwd", "114514"));
		System.out.println("系统中所有键:"+jedis.keys("*"));
		System.out.println("重命名pwd为password:"+jedis.rename("pwd","password"));
		System.out.println("删除键password:"+jedis.del("password"));
		System.out.println("判断pwd是否存在:"+jedis.exists("pwd"));
		System.out.println("查看键name储存值的类型:"+jedis.type("name"));
		System.out.println("查看键name中存储的数据:"+jedis.get("name"));
		System.out.println("查看当前数据库key的数量:"+jedis.dbSize());
		
		System.out.println("获取值后再设置的组合命令："+jedis.getSet("name", "Lisi"));
		System.out.println("查看键name修改后储存的值:"+jedis.get("name"));
		System.out.println("新增<'salary,'50000'>键值对:"+jedis.set("salary", "50000"));
		System.out.println("数值+1:"+jedis.incr("salary"));
		System.out.println("数值-1:"+jedis.decr("salary"));
		System.out.println("数值+50:"+jedis.incrBy("salary",50));
		System.out.println("数值-50:"+jedis.decrBy("salary",50));
		
		System.out.println("在键name的值后追加字符串:"+jedis.append("name","WSNGG"));
		System.out.println("查看键name修改后储存的值:"+jedis.get("name"));
		System.out.println("获取键name值的字符长度:"+jedis.strlen("name"));
		System.out.println("获取键name值下标1到4的字符:"+jedis.getrange("name",1,4));
		System.out.println("在指定下标设置替代的字符,超出的字符自动向后填充:"+jedis.setrange("name",4,"ChiBaBa"));
		System.out.println("查看键name修改后储存的值:"+jedis.get("name"));
		
		System.out.println("若sex之前没有存在则设置成功:"+jedis.setnx("sex","male"));
		System.out.println("sex现已存在，设置失败:"+jedis.setnx("sex","female"));
		System.out.println("一次性设置多个键值对:"+jedis.mset("address","xxxxxxx","age","21"));
		System.out.println("一次性获取多个键值对:"+jedis.mget("name","age","salary","address"));
	}
	
	
}
