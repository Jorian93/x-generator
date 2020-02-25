# x-generator
几行配置即可进行代码自动生成


## 子项目名称  
x-generator.module=  
## 模块所属包路径  
x-generator.pkg.parent=cn.jorian.core  
## 模块名称  
x-generator.pkg.module=system  

## 数据源配置  
x-generator.ds.driver=com.mysql.jdbc.Driver  
x-generator.ds.url=jdbc:mysql://127.0.0.1:3306/jorian_framework?useUnicode=true&characterEncoding=utf-8  
x-generator.ds.username=root  
x-generator.ds.password=root  

## 要生成的表名  
x-generator.table.names=sys_user,sys_log  
## 生成时要去掉的表前缀  
x-generator.table.prefix=  

## 实体类要继承的父类  
x-generator.base.entity=cn.jorian.generator.BaseModel  
## 所继承父类中的属性对应的表字段  
x-generator.base.entity.fields=id,createTime,updateTime  
## 控制器要继承的父控制器  
x-generator.base.controller=  
## 实体类名称  
x-generator.entity.name=%sEntity  
## 作者  
x-generator.author=jorian  
