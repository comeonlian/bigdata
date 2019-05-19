	class Person(val name:String,var age:Int , id :Int){
		def hello() = println(id)
	}

	object Person{
		def main(args:Array[String]) = println("hello world") ;
	}
