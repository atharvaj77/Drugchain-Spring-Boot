package com.atharvaj77.drugchain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private @Getter @Setter String email;
    private @Getter @Setter String id;
    private @Getter @Setter String publicAddress;
    private @Getter @Setter String userType;
    private @Getter @Setter String userName;
}
