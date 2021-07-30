package Test;

import redis.clients.jedis.Jedis;

public class Connection {
	
	private static Jedis jedis = null;
	private static String ADDR = "127.0.0.1";
	private static int PORT = 6379;
	
	public static void main(String[] args) {
		jedis = new Jedis(ADDR,PORT);
		//typeString();
		//typeList();
		//typeSet();
		//typeHash();
		typeZset();
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
	
	public static void typeList() {   //可视为链队
		
		System.out.println("切换到第1个数据库:"+jedis.select(0));
		System.out.println("清空当前数据库数据:"+jedis.flushDB());
		System.out.println("头插法:"+jedis.lpush("name", "cyka"));
		System.out.println("尾插法:"+jedis.rpush("name", "blyat"));
		System.out.println("尾插法:"+jedis.rpush("name", "pipe"));
		System.out.println("尾插法:"+jedis.rpush("name", "water"));
		System.out.println("头插法:"+jedis.lpush("name", "maozi"));
		System.out.println("遍历所有元素:"+jedis.lrange("name",0,-1));
		System.out.println("遍历下标为0到1的元素:"+jedis.lrange("name",0,1));
		System.out.println("队头出队:"+jedis.lpop("name"));
		
		System.out.println("通过下标索引查询元素:"+jedis.lindex("name", 2));
		System.out.println("查询链队的大小:"+jedis.llen("name"));
		System.out.println("移除一个元素数值为pipe的元素（贪婪模式）:"+jedis.lrem("name",1,"pipe"));
		System.out.println("截取下标1到2的元素，其他元素删除:"+jedis.ltrim("name",1,2));
		
		System.out.println("组合命令先将list的队尾元素踢出，再头插到otherlist里面:"+jedis.rpoplpush("name", "otherlist"));
		System.out.println("修改下标为0的元素为Russia:"+jedis.lset("name",0,"Russia"));
		System.out.println("在Russia的前面插入一个叫zard的元素:"+jedis.linsert("name",ListPosition.BEFORE,"Russia","zard"));
		System.out.println("在zard的后面插入一个叫love的元素:"+jedis.linsert("name",ListPosition.AFTER,"zard","love"));
		System.out.println("遍历所有元素:"+jedis.lrange("name",0,-1));
		
		
	}
	
	public static void typeSet() {    //Set不允许重复成员
	
		System.out.println("切换到第16个数据库:"+jedis.select(15));
		System.out.println("清空当前数据库数据:"+jedis.flushDB());
		System.out.println("添加新成员（无序插入）:"+jedis.sadd("myset","cyka"));
		System.out.println("添加新成员:"+jedis.sadd("myset","blyat"));
		System.out.println("添加新成员:"+jedis.sadd("myset","Russia"));
		System.out.println("遍历所有成员:"+jedis.smembers("myset"));
		System.out.println("查询blyat成员是否存在:"+jedis.sismember("myset","blyat"));
	
		System.out.println("查询myset有几个成员:"+jedis.scard("myset"));
		System.out.println("删除名为cyka的成员:"+jedis.srem("myset","cyka"));
		System.out.println("随机抽取一个成员输出:"+jedis.srandmember("myset"));
		System.out.println("随机抽取两个成员输出:"+jedis.srandmember("myset",2));
		System.out.println("将myset的成员blyat移动到myset2上:"+jedis.smove("myset","myset2","blyat"));
	
		System.out.println("以myset为基准找差集:"+jedis.sdiff("myset","myset2"));
		System.out.println("以myset为基准找交集:"+jedis.sinter("myset","myset2"));
		System.out.println("以myset为基准找并集:"+jedis.sunion("myset","myset2"));
	
	}
	
	public static void typeHash() {
		System.out.println("切换到第3个数据库:"+jedis.select(2));
		System.out.println("清空当前数据库数据:"+jedis.flushDB());
		System.out.println("新增<'name,'zhangsan'>键值对:"+jedis.hset("myhash","name","zhangsan"));
		System.out.println("新增<'number,'114514'>键值对:"+jedis.hset("myhash", "number","114514"));
		System.out.println("myhash里所有键值对:"+jedis.hgetAll("myhash"));
		System.out.println("删除键name:"+jedis.hdel("myhash","name"));
		System.out.println("获取指定哈希的长度:"+jedis.hlen("myhash"));
		System.out.println("查看指定key->myhash是否存在:"+jedis.hexists("myhash","number"));
		System.out.println("查看指定哈希表所有的key:"+jedis.hkeys("myhash"));
		System.out.println("查看指定哈希表所有的value:"+jedis.hvals("myhash"));
		
		System.out.println("指定键number值＋10:"+jedis.hincrBy("myhash","number",10));
		System.out.println("指定键number值-5:"+jedis.hincrBy("myhash","number",-5));
	}

	public static void typeZset() {  //有序序列，排序依据是第一个成员
		
		System.out.println("新增<'15000,'zhangsan'>键值对:"+jedis.zadd("salary", 15000,"zhangsan"));
		System.out.println("新增<'35000,'lisi'>键值对:"+jedis.zadd("salary", 35000,"lisi"));
		System.out.println("新增<'50000,'wanger'>键值对:"+jedis.zadd("salary", 50000,"wanger"));
		System.out.println("新增<'8000,'dbc'>键值对:"+jedis.zadd("salary", 8000,"dbc"));
		System.out.println("输出所有键值对:"+jedis.zrange("salary", 0,-1));
		System.out.println("移除名为dbc的数据:"+jedis.zrem("salary", "dbc"));
		System.out.println("所有数据升序排列:"+jedis.zrangeByScore("salary", 0, 99999999));
		System.out.println("20000-50000的数据升序排列:"+jedis.zrangeByScore("salary", 20000, 50000));
		System.out.println("所有数据降序排列:"+jedis.zrevrange("salary", 0, -1));
		
		System.out.println("计算15000-40000范围内的数据数量:"+jedis.zcount("salary", 15000, 40000));
		
	}
	
	public static void typeHyperloglog() {   //基数统计类型（基数就是不重复的数字）
		
		System.out.println("切换到第8个数据库:"+jedis.select(7));
		System.out.println("清空当前数据库数据:"+jedis.flushDB());
		System.out.println("给mykey1添加多个数据："+jedis.pfadd("mykey1", "a","b","c","d","b","d"));
		System.out.println("给mykey2添加多个数据："+jedis.pfadd("mykey2", "e","f","g","a","c","d"));
		
		System.out.println("计算mykey1的基数数量："+jedis.pfcount("mykey1"));
		System.out.println("合并两个mykey成一个mykey3,会去重："+jedis.pfmerge("mykey3","mykey1","mykey2"));
		System.out.println("计算mykey3的基数数量："+jedis.pfcount("mykey3"));
				
	}
	
}
