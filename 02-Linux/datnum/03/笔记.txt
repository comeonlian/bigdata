权限
-----------------
	r		//100 = 4
			//文件  :读取内容，
			//文件夹:是查看文件夹的内容
	w		//文件  :写数据到文件
			//文件夹:增删文件.
			//10 = 2

	x		//文件  : 运行程序
			//文件夹: 进入该目录.
			// 1 = 1

权限控制涉及范围
----------------
	U:user	,rwx r-x ---
	G:group ,
	O:other , 


修改文件的owner,change owner
------------------------------
	chown -R root:root a.txt		//递归修改owner
	chmod -R 777 xxx				//递归修改权限.


Linux文件夹
--------------------------
	/				//根目录

	/bin			//祖先
	/sbin			//祖先
	/usr/bin		//厂商
	/usr/sbin		//厂商
	/usr/local/bin	//用户
	/usr/local/sbin	//用户

	/etc			//配置目录
	/mnt			//挂载目录
	/boot			//引导目录
	/dev			//设备目录
	/lib[64]		//库目录


-:文件
d:目录
l:link
b:block,块设备
c:charactor,字符文件


创建连接文件
------------------
	1.硬链接
		两个完全相同文件，类似于实时备份。两个文件之间完全同步。
		删除时，只删一个。
		目录不能使用硬链接。
		ln a.txt alink			//a.txt:目标文件, alink:连接名称.

	2.符号连接
		相当于快捷方式.
		可以对文件，也可以对文件夹创建符号连接。
		符号连接存在的时候，可以删除目标文件。
		$>ln -s a.txt alink		//a.txt: 目标文件  alink:连接名称(symbolic)

sudo
-------------------
	临时借用root的权限执行命令,只在当前命令下有效。命令结束后，还是原来用户。
	1.配置当前用户具有sudo的执行权利
		[/etc/sudoers]
		...
		root ALL=(ALL) ALL
		centos ALL=(ALL) ALL
		...
	$>sudo chown -R centos:centos .


job
---------------------
	放到后台运行的进程.
	1.将程序放到后台运行,以&结尾.
		$>nano b.txt &
	
	2.查看后台运行的jobs数
		$>jobs
	
	3.切换后台作业到前台来.
		$>fg %n				//n是job编号.
	
	4.前台正在的进程，放到后台。
		ctrl + z

	5.让后作业运行
		$>bg %1		//
	
	6.杀死作业
		$>kill %1	//

进程查看,prcess show
---------------------
	$>ps -Af |grep gnome		//-A:所有进程  -f:所有列格式.
	
	$>top						//动态显示进程信息。含有cpu、内存的使用情况.
								//q,按照q退出。


cut
---------------------
	剪切显示文件的每一行。
	$>cut -c 1-5 a.txt					//从第一个字符开始,下标从1开始。
	$>ps -Af | cut -c 45-80 | more		//


查看帮助
-----------------
	$>help			//查看os内置的命令
	$>man ifconfig	//查看特定命令
	$>ifconfig --help
	$>ifconfig -h
	$>info ifconfig	//

磁盘分区使用
---------------------------
	$>fdisk -l /dev/sda
	
查看磁盘使用情况(disk free)
---------------------------
	$>df -ah /home/centos		//查看


dirname
-------------
	取出指定地址的上级目录.
	$>dirname /a/b/c/d
	$>/a/b/c

basename
--------------
	取出当前地址的上级目录.
	$>dirname /a/b/c/d
	$>d


主机名
---------------
	$>hostname		//显式主机名

	$>修改主机名(sudo)
		[/etc/hostname]
		s200
	
配置主机名和ip地址的映射
--------------------------
	[/etc/hosts]
	127.0.0.1 localhost
	192.168.231.200 s200


关机重启命令
----------------
	$>reboot		//重启
	$>halt			//停止,黑屏
					//halt -p  === poweroff
					//halt -r  === reboot

	$>poweroff		//关机
	$>shutdown		//shutdown now,

命令嵌套
----------------
	1.使用``
		$>echo `cat b.txt`	//命令解析,无法嵌套
		$>$(... $())		//支持命令的嵌套
	2.



创建用户
---------------
	0.用户和组之间，一个用户可以属于多个组。
	  但是有一个首要组。

	1.adduser,等同于useradd
		符号链接。
		/usr/sbin/adduser --> /usr/sbin/useradd.

	2.useradd
		$>su root
		$>useradd -m centos2
			输入新密码.
			重复输入。
		
	3.使用方法
		$>su root
		$>userdel -r centos2

访问文件(夹)物理位置
----------------------
	$>cd -P /t			//进入/t的物理位置
	$>pwd -P			//显式当前目录的物理位置

访问环境变量
-----------------
	echo ${PATH}				//ok
	echo $PATH					//ok
	echo "$PATH"				//ok
	echo '$PATH'				//''原样输出

export定义环境变量,只在session中有效
---------------------------------
	$>export name=${PATH}:tom
	$>export name=${Var1:-${Var2}}	//设置name为${Var1}的值，Var1没有设置为${Var2}的值。


命令执行过程
--------------------------------
	$?		//命令的返回值存储变量,0:成功 1:失败。
	$#		//参数个数
	$1		//第几个参数
	$0		//当前脚本(命令)名称
	$@		//取出所有参数
	shift	//参数左移
	${a/b/c}	//


if
---------------
	语法:
	if COMMANDS; then COMMANDS; [ elif COMMANDS; then COMMANDS; ]... [ else COMMANDS; ] fi

	if [ $# -lt 3 ]; then xx ; fi

	3,5


使用for循环输出1 - 100个数
----------------------------
	for NAME [in WORDS ... ] ; do COMMANDS; done
	for x in a b c d ; do echo $x ; done ;

命令组合
-------------
	a && b ;		//a成功后执行b，
	a || b ;		//a失败后之后b；
	a ; b			//无逻辑关系,改变目录
	(a ; b)			//无逻辑关系,不改变目录


netcat
-------------------
	瑞士军刀。
	[server]
	nc -lk 8888		//-l : 监听
					//-k : 接受多个连接


	[client]
	nc ip 8888 ;	//客户端指定服务器端

windows下nc
----------------
	1.配置环境变量path
	2.常用命令
		cmd>nc -h							//看帮助
		
	3.启动服务器端
		cmd>nc -l -p 8888 -s 0.0.0.0		//通配ip


通过nc实现文件传输
----------------------
	0.使用重定向(输入|输出)。
	1.server(centos)
		nc -l 8888 > a.txt ;

查看端口
------------------
	netstat -anop	//显式网络情况
					//-a : 所有socket
					//-n : 显式数字地址
					//-p : pid
					//-o : timer

YUM
----------------------
	Yellow dog updater manager.

安装min版的centos
-------------------
	1.在vmware中新建虚拟机
	2.插入iso镜像文件:CentOS-7-x86_64-Minimal-1511.iso
	3.
	4.
	5.
