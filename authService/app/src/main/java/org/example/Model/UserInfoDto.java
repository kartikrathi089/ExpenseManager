package org.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.example.entities.UsersInfo;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserInfoDto extends UsersInfo {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Long phoneNumber;
    @NonNull
    private String email;

}
