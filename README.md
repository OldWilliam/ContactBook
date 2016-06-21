##实现功能
基于SQLite的通讯录。增加、修改、删除功能。
##技术点
###已实现
1. 使用MVP架构
2. 使用ButterKnife，快速视图绑定，简洁代码
3. UserModel采用单例模式，UserShow和UserAdd共用UserModel，在UserModel中寄放了缓存的联系人列表，在UserAdd中
的操作要同步到缓存中，采用单例模式（懒汉，不用线程安全）

###未实现
1. 使用Universal-Image-Loader,加载联系人头像
2. 使用RecycleView代替ListView







##出现的问题


1. 非法访问类
	>`java.lang.RuntimeException: Unable to instantiate activity ComponentInfo`
	>`{com.lesson.ein.androidlesson4com.lesson.ein.androidlesson4.view.UserAddActivity}:`
	>`java.lang.IllegalAccessException: access to class not allowed`
	
		原因：
		跳转目标UserAddActivity不知何把public弄没了~~
