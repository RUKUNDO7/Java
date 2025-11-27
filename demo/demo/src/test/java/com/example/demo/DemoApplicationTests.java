package com.example.demo;

import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

    @Mock
    private PersonRepository personRepository; // Mockito mock

    @Test
    void contextLoads() {
        // Your test logic here
    }
}
