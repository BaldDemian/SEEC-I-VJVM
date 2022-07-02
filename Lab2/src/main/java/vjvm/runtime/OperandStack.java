package vjvm.runtime;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.Getter;

import java.util.Arrays;

public class OperandStack {

  @Getter
  private final Slots slots;

  @Getter
  private int top; // point to the top of the stack
  private int maxDepth; // max depth of this stack
  public OperandStack(int stackSize) {
    // initialize data structures
    slots = new Slots(stackSize);
    top = 0; // the index that next element will be stored at
    maxDepth = stackSize;
  }

  public void pushInt(int value) {
    // push value
    slots.int_(top, value);
    top++;
  }

  public int popInt() {
    // pop value
    int res = slots.int_(top - 1);
    //System.out.println("popping int");
    slots.setSlot(null, top - 1);
    top--;
    return res;
  }

  public void pushFloat(float value) {
    // push value
    slots.float_(top, value);
    top++;
  }

  public float popFloat() {
    // pop value
    //System.out.println("popping float");
    float res = slots.float_(top - 1);
    slots.setSlot(null, top - 1);
    top--;
    return res;
  }

  public void pushLong(long value) {
    // push value
    //System.out.println("push long in OperandStack is running!");
    //System.out.println("top is " + top);
    slots.long_(top, value);
    top = top + 2;
  }

  public long popLong() {
    // pop value
    //System.out.println("popping long");
    //System.out.println("top is " + top);
    long res = slots.long_(top - 2);
    slots.setSlot(null, top - 2);
    top = top - 2;
    return res;
  }

  public void pushDouble(double value) {
    // push value
    slots.double_(top, value);
    top = top + 2;
  }

  public double popDouble() {
    // pop value
    //System.out.println("popping double");
    double res = slots.double_(top - 2);
    slots.setSlot(null, top - 2);
    top = top - 2;
    return res;
  }

  public void pushByte(byte value) {
    // push value
    slots.byte_(top, value);
    top++;
  }

  public byte popByte() {
    // pop value
    //System.out.println("popping byte");
    byte res = slots.byte_(top - 1);
    slots.setSlot(null, top - 1);
    top--;
    return res;
  }

  public void pushChar(char value) {
    // push value
    slots.char_(top, value);
    top++;
  }

  public char popChar() {
    // pop value
    //System.out.println("popping char");
    char res = slots.char_(top - 1);
    slots.setSlot(null, top - 1);
    top--;
    return res;
  }

  public void pushShort(short value) {
    // push value
    slots.short_(top, value);
    top++;
  }

  public short popShort() {
    // pop value
    //System.out.println("popping short");
    short res = slots.short_(top - 1);
    slots.setSlot(null, top - 1);
    top--;
    return res;
  }

  public void pushSlots(Slots slots) {
    // push slots
    int size = slots.size();
    for (int i = size - 1; i >= 0; i--) {
      this.slots.setSlot(slots.getSlot(i), top);
      top = top + 1;
    }
  }

  public Slots popSlots(int count) {
    // pop count slots and return
    // System.out.println("popping slots!! count is " + count);
    Slots res = new Slots(count);
    for (int i = 0; i < count; i++) {
      top = top - 1;
      res.setSlot(this.slots.getSlot(top), count - i - 1);
      slots.setSlot(null, top);
    }
    return res;
  }

  public void clear() {
    // pop all slots
    for (int i = 0; i < slots.size(); i++) {
      slots.setSlot(null, i);
      top = 0;
    }
  }
}
