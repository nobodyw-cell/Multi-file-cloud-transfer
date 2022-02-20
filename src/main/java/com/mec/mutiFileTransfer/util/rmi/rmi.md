# rmi服务器
1. 讨论 \
   我们有时候有这样一种需求,我们希望将跨jvm调用方法的过程抽象,让用户用起来只觉得是调用了自己机子上的方法
   我们可以考虑用代理技术来实现这一抽象.

   过程如下

   我们让一个对象的代理做这样一件事与服务器建立连接,在服务器上调用方法的实体,返回方法返回值的json

3. 功能
   1. 客户端
      1. 得到对象的代理
      2. 代理中做的事
         1. 连接至服务器
         2. 发送方法
         3. 得到返回值返回返回值
   2. 服务器
      1. 接受客户端的连接
      2. 执行客户端的方法
      3. 发送返回值