package com.nightsky.linkedlist;

import com.github.javafaker.Faker;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author ckane
 */
@RunWith(JUnit4.class)
public class LinkedListTest {

    private final Faker faker;

    public LinkedListTest() {
        faker = new Faker();
    }

    @Test
    public void shouldBeEmptyAfterInitialization() {
        LinkedList<String> list = new LinkedList<>();
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void shouldHaveZeroSizeAfterInitialization() {
        LinkedList<String> list = new LinkedList<>();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void shouldNotBeEmptyAfterAddingElement() {
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    public void shouldHaveSizeOfOneAfterAddingElement() {
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnRemovedElement() {
        String elementToRemove = faker.backToTheFuture().character();
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(elementToRemove);
        String removedElement = list.remove(1);
        assertThat(removedElement).isEqualTo(elementToRemove);
    }

    @Test
    public void shouldBeEmptyAfterRemovingLastElement() {
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.remove(0);
        list.remove(0);
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void shouldHaveExpectedSizeAfterRemovingElement() {
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.remove(0);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetRequestedElement() {
        String elementToRequest = faker.backToTheFuture().character();
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(elementToRequest);
        String retrievedElement = list.get(1);
        assertThat(retrievedElement).isEqualTo(elementToRequest);
    }

    @Test
    public void shouldSetElementAtOffset() {
        String newValue = faker.backToTheFuture().character();
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.set(1, newValue);
        assertThat(list.get(1)).isEqualTo(newValue);
    }

    @Test
    public void shouldBeEmptyAfterClearingTheList() {
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.clear();
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    public void shouldHaveZeroSizeAfterClearingTheList() {
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.clear();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnTrueIfListContainsExpectedValue() {
        String valueToFind = faker.backToTheFuture().character();
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(valueToFind);
        list.add(faker.backToTheFuture().character());
        assertThat(list.contains(valueToFind)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfListDoesNotContainsExpectedValue() {
        String valueToFind = faker.random().hex(64);
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        assertThat(list.contains(valueToFind)).isFalse();
    }

    @Test
    public void shouldReturnIndexOfValue() {
        String valueToFind = faker.backToTheFuture().character();
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(valueToFind);
        list.add(faker.backToTheFuture().character());
        assertThat(list.indexOf(valueToFind)).isEqualTo(1);
    }

    @Test
    public void shouldReturnNegativeOneWhenRequestingTheIndexOfAMissingValue() {
        String valueToFind = faker.random().hex(64);
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        assertThat(list.indexOf(valueToFind)).isEqualTo(-1);
    }

    @Test
    public void shouldAddValueAtGivenIndex() {
        String valueToAdd = faker.random().hex(64);
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.add(1, valueToAdd);
        assertThat(list.get(1)).isEqualTo(valueToAdd);
    }

    @Test
    public void shouldHaveExpectedSizeAfterAddingValueAtGivenIndex() {
        String valueToAdd = faker.random().hex(64);
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.add(faker.backToTheFuture().character());
        list.add(2, valueToAdd);
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    public void shouldShiftExistingElementsWhenAddingValueAtGivenIndex() {
        String valueToAdd = faker.random().hex(64);
        String valueToShift = faker.random().hex(64);
        LinkedList<String> list = new LinkedList<>();
        list.add(faker.random().hex(64));
        list.add(faker.random().hex(64));
        list.add(valueToShift);
        list.add(2, valueToAdd);
        assertThat(list.get(2)).isEqualTo(valueToAdd);
        assertThat(list.get(3)).isEqualTo(valueToShift);
    }

}
