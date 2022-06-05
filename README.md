# SEEC-I-VJVM
NJU2 021 级`软件工程与计算I`课程大作业：一个简化版的JVM
> 20 级重修老狗在此，~~弥补去年图灵机摆烂的遗憾~~ 21级 的JVM看上去好好玩<br>
> 为了追踪每次 Lab ~~的进步~~，每次 Lab 分成一个文件夹
## 作题过程

### Lab1.1

这个Lab是实现 JVM 类加载的**双亲委派**机制，主要是要完成对类文件的搜索。一开始觉得很难上手，但好在老师和助教最后给了代码:rofl:

对文件的搜索主要是调用了 `java.nio.file` 和 `java.util.jar.JarFile` 中的一些方法（我不会 :cry:）

平常写代码就写点力扣那种算法题，基本没碰路径啊文件啊这些，反思中！

代码主要是在`src/main/java/vjvm/classLoader/searchpath/JarSearchpath` 和 `src/main/java/vjvm/classLoader/searchpath/DirSearchpath` 中

### Lab1.2

这个Lab是实现一个简单版的 javap，即将全是 16 进制串的字节码文件解析出类信息，比如常量池、接口、方法、属性等

刚上手《Java 虚拟机规范》不太适应，但是发现有大纲可以跳转以后阅读体验就丝滑起来了

巩固了对于 `DataInput` 的使用

代码主要是在 `src/main/java/vjvm/runtime` 中