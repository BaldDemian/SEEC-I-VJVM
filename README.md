# SEEC-I-VJVM
NJU 2021 级`软件工程与计算I`课程大作业：一个简化版的 JVM
> 20 级重修老狗在此，~~弥补去年图灵机摆烂的遗憾~~ 21级 的 JVM 看上去好好玩<br>
> 为了追踪每次 Lab ~~的进步~~，每次 Lab 分成一个文件夹
## 作题过程

### Lab1.1

这个 Lab 是实现 JVM 类加载的**双亲委派**机制，主要是要完成对类文件的搜索。一开始觉得很难上手，但好在老师和助教最后给了代码:rofl:

对文件的搜索主要是调用了 `java.nio.file` 和 `java.util.jar.JarFile` 中的一些方法（我不会 :cry:）

平常写代码就写点力扣那种算法题，基本没碰路径啊文件啊这些，反思中！

代码主要是在 `src/main/java/vjvm/classLoader/searchpath/JarSearchpath` 和 `src/main/java/vjvm/classLoader/searchpath/DirSearchpath` 中

### Lab1.2

这个 Lab 是实现一个简单版的 javap，即将全是 16 进制串的 `.class` 文件解析出类信息，比如常量池、接口、方法、属性等

刚上手《Java 虚拟机规范》不太适应，但是发现有大纲可以跳转以后阅读体验就丝滑起来了

巩固了对于 `DataInput` 的使用

代码主要是在 `src/main/java/vjvm/runtime` 中

### Lab2.1

这个 Lab 要做的事感觉挺多的。

0. 大概用时 6H，分三天做的，因为有时候做着做着脑壳就昏了。与往常的经验一致，耗时主要是在看文档和理解代码逻辑上，实现上不复杂

1. 将 `Slots` 和 `OprandStack` 两个类中的方法填充完整。类型检查是使用 `instanceof`，它拯救了我的上个 lab 谢谢谢谢:pray:

2. 把上一次遗留的 `attributes` 中的 `Code` 属性进行解析，这里存放了方法的全部**字节码**（最开始以为 .class 文件就叫字节码文件，好菜:cry:

3. 实现一个 `invokestatic`。唤醒我支离破碎的计组知识，Java 的字节码也是与 X86 的指令是类似的，最开始是操作码，标记这条指令的类型；然后后面是操作数，不过字节码中的操作数一般是索引，指向常量池中的某个常量或是引用

4. 这次代码一遍写完倒是没什么粗心导致的 bug，比如 C++ 考试中嵌套几层 for 循环最终导致写出了这样的代码（也许可以责怪 CLion 的提示没有 IDEA 做得好）

   ```c++
   for (int i = 0; i < num0; i++) {
       //中间是一段比较长的代码，直接导致我忘了变量名
       for (int k = 0; k < num1; i++) {
           // 小丑本丑，就这么一个变量名导致我丢了24分
           // 不过想想我 C++ 签到分直接被怒砍 10 分，好像又释然了起来
       }
   }
   ```

5. 唯一的一个理解上的 bug 是读取 `invokestatic` 的后两个操作数时，应该要用 `pc.byte()`，我用成了 `pc.short()`

6. 关于求取 `argc` 的实现，即要通过方法的**描述符**解析出要预留的 slots 大小，感觉这次 Lab 那个孤零零的用例没怎么用到这个，也许下次还是会踩到这个雷。我的实现是这样的，不是很优雅

   ```java
   import java.util.*;
   
   public class Main {
       public static int argc(String descriptor) {
           char[] arr = new char[]{
                   'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z'
           };
           Set<Character> helper = new HashSet<>();
           for (char ch : arr) {
               helper.add(ch);
           }
           // descriptor is like "(IDLjava/lang/Thread;)Ljava/lang/Object;"
           assert descriptor.startsWith("(");
   
           // calculate arguments size in slots
           int count = 0;
           int endIndex = descriptor.indexOf(')');
           int i = 1;
           while (i != endIndex) {
               char c = descriptor.charAt(i);
               if (helper.contains(c)) {
                   if (c == 'D' || c == 'J') {
                       count += 2;
                   } else {
                       count += 1;
                   }
                   i++;
               } else if (c == 'L') {
                   int nextIndex = descriptor.indexOf(';', i) + 1;
                   count += 1;
                   i = nextIndex;
               } else if (c == '[') {
                   count += 1;
                   int nextIndex = i + 1;
                   // just in case multidimentional array like [[[D
                   while (true) {
                       if (descriptor.charAt(nextIndex) == '[') {
                           nextIndex++;
                       } else {
                           break;
                       }
                   }
                   if (helper.contains(descriptor.charAt(nextIndex))) {
                       i = nextIndex + 1;
                   } else {
                       i = descriptor.indexOf(';', nextIndex) + 1;
                   }
               } else {
                   i++;
               }
           }
           return count;
       }
   
       public static void main(String[] args) {
           System.out.println(argc("(I[[[[B[JLjava/lang/String;)V"));
       }
   }
   ```

7. 提供了调试器，但是我没用到，全程使用 `sout` 调试 :rofl:
8. 某个指令中埋下了一个 bug，`sout` 后成功追杀到 bug
9. 最后才发现打印出的是 `Hello, Wor1d`，没消灭那个 bug 前打印出的是 `Hello, Wor-1d`
10. 反思：对于反复~~横跳~~跳转的程序还是有畏难心理。另外这个期末真的感觉要活不出来了:cry:

### Lab2.2

完结撒花:tada:，并且成功活过了期末，但是软工II快活不下去啦

这次 Lab 要实现 JVM 中几乎所有的指令，虽说绝大部分的操作很简单，但是由于本人学艺不佳，全程 if-else，复制粘贴好痛苦，算是修炼成了 CV 战神:rofl:

这次 Lab 前前后后写了快 10H 吧，有点痛苦，但好在对着 JVM 规范写还是算容易的，第一遍写完只出了几行的 bug，值得表扬！并且测试用例不是很坑人。惊讶的是本地一个用例一直过不去但是提交以后就通过了

## 大作业总结

1. 经过了一年的学习终于可以拿捏曾经拿捏我的软工 I 大作业了:rofl:
2. 比较深入学习了 JVM
3. 助教给的几个字节码的实现里用了函数式编程的技巧，但是由于时间太赶我直接用的 if-else，之后有时间可能用函数式编程重构以下（虽然知道几乎没可能了，懒人）

4. 调试的技巧还需要学习，用 JAR 运行的程序怎么 debug 啊，一行行写 sout 再一行行注释掉真的好痛苦
5. ~~写软工 II~~睡觉去了（
