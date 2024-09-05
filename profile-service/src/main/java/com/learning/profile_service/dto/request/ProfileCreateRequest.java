package com.learning.profile_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreateRequest {
    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
