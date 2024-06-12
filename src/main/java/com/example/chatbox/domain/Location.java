package com.example.chatbox.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {
    private double latitude;
    private double longitude;
}
