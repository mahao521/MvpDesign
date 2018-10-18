# MvpDesign
mvp + eventbus + rxjava + dragger
/**
 *   mvp  v层 定义需要的数据接口，更新数据，展示
 *
 *       P层： 持有view和model层的引用对象   p层操作moule层请求数据，返回到p层。 view引用传递给view层；
 *
 *       model 请求数据，定义接口，返回给p层
 *
 *  mvp + rxbus + daggger  + retofit + rxjava
 *
 *       rxbus : 定义在P层， 通过注解，反射P层需要回调的方法。----------目的解耦
 *
 *       rxjava : 传递modle的数据直接给 P 层 -----------------目的： 配合rxbus解耦 ，形成事件流。
 *
 *       dagger : 解耦
 *       理解： 1 ： 注入一个组件；
 *              2 ： 提供一个moudle到容器中component中。
 *              3 ： 从容器component取出这个组件；
 *
 *        retrofit调用流程:
 *        1 : 定义一个接口，动态代理接口中的方法，获取返回值，获取注解；
 *        2 : rxjava驱动事件执行 subscibe（）执行 subscribeActual(observer);
 *        3 ：在CallExecuteObservable类中，retrofit中的originalCall.exec()
 *        4 : 继续okhttp3中的exec()；getResponseWithInterceptorChain();
 
 *
 *        自定义请求拦截器 可以获取请求链接
 *        interceptors.addAll(client.interceptors());
 *        重试拦截器 网络请求，请求失败，重定向
         interceptors.add(retryAndFollowUpInterceptor);
          桥接拦截器 拼接请求头，keep-alive 配置请求cook 和保存返回的cook
         interceptors.add(new BridgeInterceptor(client.cookieJar()));
          缓存拦截器    配置请求是否缓存cache_control
         interceptors.add(new CacheInterceptor(client.internalCache()));
         连接拦截器    通过socket链接，获取输入流，输出流
         interceptors.add(new ConnectInterceptor(client));
         网络拦截器    自定义的网络拦截器，可以处理返回数据
         interceptors.addAll(client.networkInterceptors());
         网络拦截器  封装response返回给客户端 分为http1.http2的封装
         interceptors.add(new CallServerInterceptor(forWebSocket));
         

         自定义拦截器： client.interceptors()  和client.networkInterceptors;
         可以获取到 request 和 response();
          前者的拦截器在第一步，后者的拦截器在最后一步，所以二者的区别：
        1 ： 前者： 获取的response可能来自网络，可能来自缓存
        2 ： 后者:  resoponse一定来自网络
        3 ： url前者获取的是真实的url，后者url可能是重定向的url;
        4 : 前者不关心header的拼接；


        思考： 可以利用http的文件缓存response。替代前端的数据缓存；
               直接拿到http的缓存。加载数据到页面；
               图片缓存也用图片的框架，不用在前端做缓存了;
               

        默认使用Cache ，如果自己要使用，可以参考okhttp源码的例子，实现InternalCache接口；

        cache-control :
        maxAge ： 请求如果超过缓存时间，就从网络获取；（针对读取网络）
        maxstale :  请求如果超过缓存时间，返回一个过时的比没有的返回好；（针对强制读取缓存）

        vollery 使用的是阻塞队列  blockDeque 默认只有4个线程，其他请求都放在阻塞队列中。 mqueen.take()取出；
        
        okhttp  异步请求 :  定义的缓冲池64个，并且直接加入缓冲池，直接执行； 超过定义的最大线程数量之后，放入准备线程池；使用的ArrayDequeue;
        
                同步请求 ： 直接加入线程池，但是没有使用到线程，直接返回response();
                

 */
