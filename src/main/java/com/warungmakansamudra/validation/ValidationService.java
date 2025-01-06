package com.warungmakansamudra.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class ValidationService {

    public void validate(@Valid @NotNull Object request) {
        // Custom validation logic can be added here if needed
    }
}