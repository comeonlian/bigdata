class Person{
	var id = 1 ;
	var name = "tom" ;
	var age = 12;

	//辅助构造
	def this(name:String){
		this();
		this.name = name ;
	}
	
	//辅助构造
	def this(name:String,age:Int){
		//调用辅助构造
		this(name) ;
		this.age = age ;
	}
}