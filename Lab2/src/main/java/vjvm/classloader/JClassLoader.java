package vjvm.classloader;

import lombok.var;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.vm.VMContext;
import vjvm.utils.UnimplementedError;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class JClassLoader implements Closeable {
  private final JClassLoader parent;
  private final ClassSearchPath[] searchPaths;
  private final HashMap<String, JClass> definedClass = new HashMap<>();
  @Getter
  private final VMContext context;

  public JClassLoader(JClassLoader parent, ClassSearchPath[] searchPaths, VMContext context) {
    this.context = context;
    this.parent = parent;
    this.searchPaths = searchPaths;
  }

  /**
   * Load class
   *
   * If a class is found, construct it using the data returned by ClassSearchPath.findClass and return it.
   *
   * Otherwise, return null.
   */
  public JClass loadClass(String descriptor) {
    // ordinary class
    assert descriptor.charAt(descriptor.length() - 1) == ';';
    // find in parent first
    JClass jClass;
    if (parent != null
      && (jClass = parent.loadClass(descriptor)) != null) {
      return jClass;
    }

    // find in loaded classes
    if ((jClass = definedClass.get(descriptor)) != null)
      return jClass;

    // not loaded
    var name = descriptor.substring(1, descriptor.length() - 1);
    for (var p : searchPaths) {
      var iStream = p.findClass(name);
      // if the class was found
      if (iStream != null)
        return defineNonarrayClass(descriptor, iStream);
    }

    return null;
  }

  private JClass defineNonarrayClass(String descriptor, InputStream data) {
    var ret = new JClass(new DataInputStream(data), this);
    definedClass.put(descriptor, ret);
    return ret;
  }

  @Override
  @SneakyThrows
  public void close() {
    for (var s : searchPaths)
      s.close();
  }
}
