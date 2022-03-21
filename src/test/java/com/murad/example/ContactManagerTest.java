package com.murad.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public void setupAll() {
        System.out.println("Should print before all tests");
    }

    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();

    }

    @Test
    @DisplayName("it should create contact successfully")
    public void shouldCreateContact() {

        contactManager.addContact("John", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact ->
                        contact.getFirstName().equals("John") &&
                                contact.getLastName().equals("Doe") &&
                                contact.getPhoneNumber().equals("0123456789"))
                .findAny()
                .isPresent());

    }

    @Test
    @DisplayName("test contact creation on Developer Machine")
    public void shouldTestContactCreationOnDev() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Doe", "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create contact when last name is null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create contact when phone number is null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", "Doe", null);
        });
    }

    @AfterEach
    void tearDown() {
        System.out.println("Should execute after each test");
    }

    @AfterAll
    void afterAll() {
        System.out.println("Should execute after all");
    }
}