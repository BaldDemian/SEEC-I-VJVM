package vjvm.runtime;

import java.util.Optional;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  private Object[] slots;
  private int size;
  public Slots(int slotSize) {
    slots = new Object[slotSize];
    size = slotSize;
  }
  public Object getSlot(int index) {
    return slots[index];
  }
  public void setSlot(Object obj, int index) {
    slots[index] = obj;
  }
  public int int_(int index) {
    // return the int at index
    assert slots[index] instanceof Integer;
    Integer i = (Integer) slots[index];
    int res = i; // auto unboxxing
    return res;
  }

  public void int_(int index, int value) {
    // set the int at index
    Integer i = value; //auto boxxing
    slots[index] = i;
  }

  public long long_(int index) {
    // return the long at index
    assert slots[index] instanceof Long;
    Long l = (Long) slots[index];
    long res = l;
    return res;
  }

  public void long_(int index, long value) {
    // set the long at index
    Long l = value;
    slots[index] = l;
  }

  public float float_(int index) {
    // return the float at index
    assert slots[index] instanceof Float;
    Float f = (Float) slots[index];
    float res = f;
    return res;
  }

  public void float_(int index, float value) {
    // set the float at index
    Float f = value;
    slots[index] = f;
  }

  public double double_(int index) {
    // return the double at index
    assert slots[index] instanceof Double;
    Double d = (Double) slots[index];
    double res = d;
    return res;
  }

  public void double_(int index, double value) {
    // set the double at index
    Double d = value;
    slots[index] = d;
  }

  public byte byte_(int index) {
    // return the byte at index
    assert slots[index] instanceof Integer;
    int i = (Integer) slots[index];
    byte res = (byte) i;
    return res;
  }

  public void byte_(int index, byte value) {
    // set the byte at index
    Integer i = (int) value;
    slots[index] = i;
  }

  public char char_(int index) {
    // return the char at index
    assert slots[index] instanceof Integer;
    int i = (Integer) slots[index];
    char res = (char) i;
    return res;
  }

  public void char_(int index, char value) {
    // set the char at index
    Integer i = (int) value;
    slots[index] = i;
  }

  public short short_(int index) {
    // return the short at index
    assert slots[index] instanceof Integer;
    int i = (Integer) slots[index];
    short res = (short) i;
    return res;
  }

  public void short_(int index, short value) {
    // set the short at index
    Integer i = (int) value;
    slots[index] = i;
  }


  public Optional<Object> value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    return Optional.empty();
  }

  public int size() {
    // return the size in the number of slots
    return size;
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    // copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)
    for (int i = 0; i < length; i++) {
      dest.setSlot(this.slots[begin + i], destBegin + i);
    }
  }
}
