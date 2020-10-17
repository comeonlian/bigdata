class Person1{
	private var id = 0 ;
	
	//默认修饰符是public
	def incre(a:Int) = {id += a ;}
	
	//如果定义时，没有(),调用就不能加()
	def current() = id 
}