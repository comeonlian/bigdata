	//公鸡
	var cock = 0 ;
	while(cock <= 20){
		//母鸡
		var hen = 0 ;
		while(hen <= 100/3){
			var chicken = 0 ;
			while(chicken <= 100){
				//钱数
				var money = cock * 5 + hen * 3 + chicken / 3 ;
				//个数
				var mount = cock + hen + chicken ;
				if(money == 100 && mount == 100){
					printf("cock : %d , hen : %d , chicken : %d",cock,hen,chicken) ;
					println();
				}
				chicken += 3 ;
			}
			hen += 1 ;
		}
		cock += 1 ;
	}
