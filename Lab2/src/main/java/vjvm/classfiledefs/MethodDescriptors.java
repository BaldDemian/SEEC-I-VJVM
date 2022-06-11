package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.var;

import java.util.HashSet;
import java.util.Set;

import static vjvm.classfiledefs.Descriptors.DESC_array;
import static vjvm.classfiledefs.Descriptors.DESC_reference;

public class MethodDescriptors {
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

  public static char returnType(String descriptor) {
    assert descriptor.startsWith("(");

    var i = descriptor.indexOf(')') + 1;
    assert i < descriptor.length();
    return descriptor.charAt(i);
  }
}
