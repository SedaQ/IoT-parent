package cz.vutbr.feec.iot.apiclasses;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.*;


public class OptionalTest {

  // creating Optional
  @Test
  public void whenCreatesEmptyOptional_thenCorrect() {
    Optional<String> empty = Optional.empty();
    assertFalse(empty.isPresent());
  }

  @Test
  public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
    String name = "seda";
    Optional.of(name);
  }

  @Test(expected = NullPointerException.class)
  public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
    String name = null;
    Optional<String> opt = Optional.of(name);
  }

  @Test
  public void givenNonNull_whenCreatesOptional_thenCorrect() {
    String name = "seda";
    Optional<String> opt = Optional.of(name);
    assertEquals("Optional[seda]", opt.toString());
  }

  @Test
  public void givenNonNull_whenCreatesNullable_thenCorrect() {
    String name = "seda";
    Optional<String> opt = Optional.ofNullable(name);
    assertEquals("Optional[seda]", opt.toString());
  }

  @Test
  public void givenNull_whenCreatesNullable_thenCorrect() {
    String name = null;
    Optional<String> opt = Optional.ofNullable(name);
    assertEquals("Optional.empty", opt.toString());
  }
  // Checking Value With isPresent()

  @Test
  public void givenOptional_whenIsPresentWorks_thenCorrect() {
    Optional<String> opt = Optional.of("seda");
    assertTrue(opt.isPresent());

    opt = Optional.ofNullable(null);
    assertFalse(opt.isPresent());
  }
  
  @Test
  public void givenOptionalOfNullable_handleNullPointerException(){
    Optional<?> opt = Optional.ofNullable(new NullPointerException());
    assertFalse(opt.isPresent());
    assertNull(opt.isPresent());
  }

  // returning Value With get()
  @Test
  public void givenOptional_whenGetsValue_thenCorrect() {
    Optional<String> opt = Optional.of("seda");
    String name = opt.get();
    assertEquals("seda", name);
  }

  @Test(expected = NoSuchElementException.class)
  public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
    Optional<String> opt = Optional.ofNullable(null);
    String name = opt.get();
  }

}
