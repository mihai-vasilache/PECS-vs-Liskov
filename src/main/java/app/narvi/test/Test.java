package app.narvi.test;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.zip.ZipException;

//Collection -> Set -> SortedSet -> TreeSet || ConcurrentSkipListSet
//Exception -> IOException -> ZipException

// Liskov:
// "expect no more, provide no less"
//1) The precondition can only become weaker than in the inherited contract.
//2) The postcondition can only become stronger than in the inherited contract.

// PECS:
// 1) Producer Extends
// 2) Consumer Super

public class Test {


  class Liskov {

    SortedSet produces() {
      return new ConcurrentSkipListSet();
    }

    void consume(SortedSet sortedSet) {

    }

    void exception() throws IOException {

    }

  }

  class ExtendsLiskov extends Liskov {

    @Override
    TreeSet produces() {
      return new TreeSet();
    }

// should be allowed:
//  @Override
//  void consume(Collection collection) {
//
//  }

// OK - NOT ALLOWED
//  @Override
//  void consume(TreeSet treeSet) {
//
//  }

    //same as returning a subclass
    void exception() throws ZipException {

    }

  }

  class GenericType<GT> {}

  abstract class Generics<G> {
    <E extends G> GenericType<E> produces() {
      return null;
    }

    abstract <E> GenericType<E> produces2();

    void consume(GenericType<? super G> consumed) {

    }
    void consume2(GenericType<G> consumed) {

    }
  }

  class ExtendsGenerics<G> extends Generics<G> {
  //  @Override
    <E> GenericType<E> produces2() {
      return null;
    }

    @Override
    void consume(GenericType<? super G> consumed) {

    }
  }

}


