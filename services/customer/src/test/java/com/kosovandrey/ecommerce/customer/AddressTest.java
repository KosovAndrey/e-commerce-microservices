package com.kosovandrey.ecommerce.customer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressTest {

    @Test
    void testAllArgsConstructor() {
        Address address = new Address("Main Street", "10A", "123456");
        assertThat(address.getStreet()).isEqualTo("Main Street");
        assertThat(address.getHouseNumber()).isEqualTo("10A");
        assertThat(address.getZipCode()).isEqualTo("123456");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Address address = new Address();
        address.setStreet("Broadway");
        address.setHouseNumber("21B");
        address.setZipCode("654321");

        assertThat(address.getStreet()).isEqualTo("Broadway");
        assertThat(address.getHouseNumber()).isEqualTo("21B");
        assertThat(address.getZipCode()).isEqualTo("654321");
    }

    @Test
    void testBuilder() {
        Address address = Address.builder()
                .street("High Street")
                .houseNumber("5C")
                .zipCode("789123")
                .build();

        assertThat(address.getStreet()).isEqualTo("High Street");
        assertThat(address.getHouseNumber()).isEqualTo("5C");
        assertThat(address.getZipCode()).isEqualTo("789123");
    }

    @Test
    void testSettersAndGetters() {
        Address address = new Address();
        address.setStreet("Elm Street");
        address.setHouseNumber("13");
        address.setZipCode("987654");

        assertThat(address.getStreet()).isEqualTo("Elm Street");
        assertThat(address.getHouseNumber()).isEqualTo("13");
        assertThat(address.getZipCode()).isEqualTo("987654");
    }

    @Test
    void testInequality() {
        Address address1 = new Address("Oak Street", "1B", "111111");
        Address address2 = new Address("Maple Street", "2C", "222222");

        assertThat(address1).isNotEqualTo(address2);
    }
}
